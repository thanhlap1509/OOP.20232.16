package sourcecode.Frame;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;
public class GameFrame extends JFrame implements MouseListener {
    final int FRAME_WIDTH = 825;
    final int HEADER_SIZE = 115;
    final Color HEADER_COLOR = Color.black;
    final Color TEXT_COLOR = Color.white;
    final Color BOARD_COLOR = Color.green;
    private JLabel player1Info;
    private JLabel player2Info;
    private JLabel timerLabel;
    private JLabel turnDisplayed;
    private JMenuBar menuBar;
    private JMenu exitMenu;
    private Border paddingBorder1;
    private Border paddingBorder2;
    private Border indicatorBorder;
    private Border compoundBorder1;
    private Border compoundBorder2;
    private int secondCountDown;
    private Timer timer;
    private int turn;
    GameFrame() {
        // try-catch reading png file into image instances then convert to image icon
        BufferedImage p1BuffImg = null;
        BufferedImage p2BuffImg = null;
        try{
            p1BuffImg = ImageIO.read(new File("sourcecode/Frame/players1.png"));
            p2BuffImg = ImageIO.read(new File("sourcecode/Frame/players2.png"));
        } catch (IOException e){
            System.out.println("read again");
        }
        Image player1Img = p1BuffImg.getScaledInstance(HEADER_SIZE, HEADER_SIZE, Image.SCALE_DEFAULT);
        ImageIcon player1ImgIcon = new ImageIcon(player1Img);
        Image player2Img = p2BuffImg.getScaledInstance(HEADER_SIZE, HEADER_SIZE, Image.SCALE_DEFAULT);
        ImageIcon player2ImgIcon = new ImageIcon(player2Img);
        paddingBorder1 =  BorderFactory.createEmptyBorder(0, 0, 0, 5);
        paddingBorder2 =  BorderFactory.createEmptyBorder(0, 5, 0, 0);
        indicatorBorder = BorderFactory.createLineBorder(Color.yellow, 2);
        compoundBorder1 = BorderFactory.createCompoundBorder(indicatorBorder, paddingBorder1);
        compoundBorder2 = BorderFactory.createCompoundBorder(indicatorBorder, paddingBorder2);
        //player 1 container
        player1Info = new JLabel();
        player1Info.setText("<html>Name_1<br>Points: 0</html>");
        player1Info.setForeground(TEXT_COLOR);
        player1Info.setIcon(player1ImgIcon);
        player1Info.setHorizontalTextPosition(JLabel.RIGHT);
        player1Info.setVerticalTextPosition(JLabel.CENTER);
        //player 2 container
        player2Info = new JLabel();
        player2Info.setText("<html>Name_2<br>Points: 0</html>");
        player2Info.setForeground(TEXT_COLOR);
        player2Info.setIcon(player2ImgIcon);
        player2Info.setHorizontalTextPosition(JLabel.LEFT);
        player2Info.setVerticalTextPosition(JLabel.CENTER);
        //game status container
        JPanel gameStatus = new JPanel();
        gameStatus.setLayout(new GridLayout(6, 1));
        turn = 1;
        turnDisplayed = new JLabel();
        turnDisplayed.setBorder(new EmptyBorder(5, 5, 5, 5));
        turnDisplayed.setForeground(TEXT_COLOR);
        turnDisplayed.setHorizontalAlignment(SwingConstants.CENTER);
        turnDisplayed.setVerticalAlignment(SwingConstants.TOP);

        timerLabel = new JLabel();
        timerLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        timerLabel.setForeground(TEXT_COLOR);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setVerticalAlignment(SwingConstants.TOP);
        timerCountDown();
        gameStatus.add(turnDisplayed);
        gameStatus.add(timerLabel);
        gameStatus.setBackground(HEADER_COLOR);
        //header container
        JPanel gameInfo = new JPanel();
        gameInfo.setLayout(new BorderLayout());
        gameInfo.setBackground(HEADER_COLOR);
        gameInfo.setPreferredSize(new Dimension(HEADER_SIZE, HEADER_SIZE));
        gameInfo.add(player1Info,BorderLayout.WEST);
        gameInfo.add(gameStatus, BorderLayout.CENTER);
        gameInfo.add(player2Info, BorderLayout.EAST);
        //menu bar
        menuBar = new JMenuBar();
        exitMenu = new JMenu("Exit");
        exitMenu.addMouseListener(this);
        menuBar.add(exitMenu);
        //Board game

        JPanel boardGameContainer = new JPanel();
        //left tiles
        MyPanel leftTile = new MyPanel("left");
        //right tiles
        MyPanel rightTile = new MyPanel("right");
        //center tiles
        JPanel centerTiles = new JPanel();
        centerTiles.setLayout(new GridLayout(2, 1));
        //upper row
        JPanel upper = new JPanel();
        upper.setLayout(new GridLayout(1, 5));
        MyPanel[] upperTiles = new MyPanel[5];
        for (int i = 0; i < 5; i++){
            upperTiles[i] = new MyPanel("center", "upper", 10 - i);
            upper.add(upperTiles[i]);
        }
        //lower row
        JPanel lower = new JPanel();
        lower.setLayout(new GridLayout(1, 5));
        MyPanel[] lowerTiles = new MyPanel[5];
        for (int i = 0; i < 5; i++){
            lowerTiles[i] = new MyPanel("center", "lower", i);
            lower.add(lowerTiles[i]);
        }
        //add upper and lower row in center tiles
        centerTiles.add(upper);
        centerTiles.add(lower);
        //add all container in one board game container
        boardGameContainer.setLayout(new BorderLayout());
        boardGameContainer.add(leftTile, BorderLayout.WEST);
        boardGameContainer.add(centerTiles, BorderLayout.CENTER);
        boardGameContainer.add(rightTile, BorderLayout.EAST);
        boardGameContainer.setBorder(BorderFactory.createLineBorder(BOARD_COLOR));
        //frame
        this.setTitle("Game Frame");
        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(FRAME_WIDTH, HEADER_SIZE + (int)(MyPanel.SIZE*1.6));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(gameInfo, BorderLayout.NORTH);
        this.add(boardGameContainer, BorderLayout.CENTER);
        this.setVisible(true);
    }
    private void timerCountDown(){
        timer = new Timer();
        secondCountDown = 3;
        if (turn == 1){
            player2Info.setBorder(null);
            player1Info.setBorder(compoundBorder1);
            turnDisplayed.setText("Current turn: " + "Name_1");
        }
        else {
            player1Info.setBorder(null);
            player2Info.setBorder(compoundBorder2);
            turnDisplayed.setText("Current turn: " + "Name_2");
        }
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (secondCountDown >= 0) {
                    String string = String.format("Timer: %02d:%02d", secondCountDown / 60, secondCountDown % 60);
                    timerLabel.setText(string);
                    //System.out.println(secondCountDown);
                    secondCountDown--;
                } else {
                    timer.cancel();
                    //System.out.println("Finish turn of player " + turn);
                    if (turn == 1) turn = 2;
                    else turn = 1;
                    timerCountDown();
                }
            }
        }, 0, 1000); // Run the task every second
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == exitMenu){
            System.out.println("Exit");
            timer.cancel();
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}