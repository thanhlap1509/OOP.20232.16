package sourcecode.Frame;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

public class GameFrame extends JFrame implements MouseListener {
    final int FRAME_WIDTH = 950;
    final int PLAYER_INFO_WIDTH = 175;
    final int HEADER_SIZE = 115;
    final Color HEADER_COLOR = Color.black;
    final Color TEXT_COLOR = Color.white;
    private final String name1;
    private final String name2;
    private final JLabel player1Info;
    private final JLabel player2Info;
    private final JLabel timerLabel;
    private final JMenu exitMenu;
    private MyPanel[] upperTiles;
    private MyPanel[] lowerTiles;
    private final Border compoundBorder1;
    private final Border compoundBorder2;
    private int secondCountDown;
    private Timer timer;
    private int turn;
    private int point1;
    private int point2;

    GameFrame() {
        //getting name for two player and initiate point = 0
        name1 = JOptionPane.showInputDialog(this, "Input name for first player", "", JOptionPane.INFORMATION_MESSAGE);
        name2 = JOptionPane.showInputDialog(this, "Input name for second player", "", JOptionPane.INFORMATION_MESSAGE);
        point1 = 0;
        point2 = 0;
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
        //create two border, one for apply padding, one to paint with color and combine into compound border to indicate turn
        Border paddingBorder1 = BorderFactory.createEmptyBorder(0, 0, 0, 5);
        Border paddingBorder2 = BorderFactory.createEmptyBorder(0, 0, 0, 5);
        Border indicatorBorder = BorderFactory.createLineBorder(Color.yellow, 2);
        compoundBorder1 = BorderFactory.createCompoundBorder(indicatorBorder, paddingBorder1);
        compoundBorder2 = BorderFactory.createCompoundBorder(indicatorBorder, paddingBorder2);
        //player 1 container
        player1Info = new JLabel();
        player1Info.setText("<html><div style='text-align:left;'>"+ name1 + "<br>Points:"  + point1 + "</div></html>");
        player1Info.setForeground(TEXT_COLOR);
        player1Info.setIcon(player1ImgIcon);
        player1Info.setHorizontalTextPosition(JLabel.RIGHT);
        player1Info.setVerticalTextPosition(JLabel.CENTER);
        player1Info.setBounds((int) (PLAYER_INFO_WIDTH * 1.85) + PLAYER_INFO_WIDTH - HEADER_SIZE - 10, 0, PLAYER_INFO_WIDTH + name1.length()*5, HEADER_SIZE);
        //player 2 container
        player2Info = new JLabel();
        player2Info.setText("<html><div style='text-align:left;'>" + name2 + "<br>Points:" + point2 + "</div></html>");
        player2Info.setForeground(TEXT_COLOR);
        player2Info.setIcon(player2ImgIcon);
        player2Info.setHorizontalTextPosition(JLabel.RIGHT);
        player2Info.setVerticalTextPosition(JLabel.CENTER);
        player2Info.setBounds((int) (PLAYER_INFO_WIDTH * 1.85) + PLAYER_INFO_WIDTH - HEADER_SIZE - 10,0, PLAYER_INFO_WIDTH + name2.length()*5, HEADER_SIZE);
        //initiate timer label
        turn = 1;
        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setBorder(new EmptyBorder(5, 5 , 0, 0));
        timerLabel.setForeground(TEXT_COLOR);
        timerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        timerLabel.setVerticalAlignment(SwingConstants.TOP);
        timerLabel.setHorizontalTextPosition(JLabel.LEFT);
        timerLabel.setBounds(0, 0, 150, 50);
        timerCountDown();
        //players container
        JPanel player2Container = new JPanel();
        player2Container.setLayout(null);
        player2Container.setBackground(HEADER_COLOR);
        player2Container.setPreferredSize(new Dimension(HEADER_SIZE, HEADER_SIZE));
        player2Container.add(timerLabel);
        player2Container.add(player2Info);
        // -----
        JPanel player1Container = new JPanel();
        player1Container.setLayout(null);
        player1Container.setBackground(HEADER_COLOR);
        player1Container.setPreferredSize(new Dimension(HEADER_SIZE, HEADER_SIZE));
        player1Container.add(player1Info);
            //menu bar
        JMenuBar menuBar = new JMenuBar();
        exitMenu = new JMenu("Exit");
        exitMenu.addMouseListener(this);
        menuBar.add(exitMenu);
        //Board game
        JPanel boardGameContainer = new JPanel();
            //left tiles
        MyPanel leftTile = new MyPanel("left", 0, 1);
        leftTile.addMouseListener(this);
            //right tiles
        MyPanel rightTile = new MyPanel("right", 0, 1);
        rightTile.addMouseListener(this);
            //center tiles
        JPanel centerTiles = new JPanel();
        centerTiles.setLayout(new GridLayout(2, 1));
                //upper row
        JPanel upper = new JPanel();
        upper.setLayout(new GridLayout(1, 5));
        upperTiles = new MyPanel[5];
        for (int i = 0; i < 5; i++){
            upperTiles[i] = new MyPanel("center", "upper", 10 - i, 5, 0);
            upperTiles[i].addMouseListener(this);
            upper.add(upperTiles[i]);
        }
                //lower row
        JPanel lower = new JPanel();
        lower.setLayout(new GridLayout(1, 5));
        lowerTiles = new MyPanel[5];
        for (int i = 0; i < 5; i++){
            lowerTiles[i] = new MyPanel("center", "lower", i, 5, 0);
            lowerTiles[i].addMouseListener(this);
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
        //frame
        this.setTitle("Game Frame");
        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(FRAME_WIDTH, HEADER_SIZE * 2 + (int)(MyPanel.SIZE*1.6));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(player2Container, BorderLayout.NORTH);
        this.add(boardGameContainer, BorderLayout.CENTER);
        this.add(player1Container, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    private void timerCountDown(){
        //create timer, changing header display according to turn
        timer = new Timer();
        secondCountDown = 60;
        if (turn == 1){
            player2Info.setBorder(null);
            player1Info.setBorder(compoundBorder1);
        }
        else {
            player1Info.setBorder(null);
            player2Info.setBorder(compoundBorder2);
        }
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (secondCountDown >= 0) {
                    String string = String.format("Timer: %02d:%02d", secondCountDown / 60, secondCountDown % 60);
                    timerLabel.setHorizontalTextPosition(JLabel.LEFT);
                    timerLabel.setText(string);
                    secondCountDown--;
                } else {
                    timer.cancel();
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
        //only accept center square
        else if (e.getSource() instanceof MyPanel && ((MyPanel) e.getSource()).getOrientation().equals("center")){
            // enable lower tiles access for player 1 and upper tiles for player 2
            if ((turn == 1 && ((MyPanel) e.getSource()).getUoL().equals("lower"))
                    || (turn == 2 && ((MyPanel) e.getSource()).getUoL().equals("upper"))){
                timer.cancel();
                if (e.getX() >= 0 && e.getX() <= ((MyPanel) e.getSource()).arrowWidth){
                    //dummy feature, increase gem in said tile by 1
                    ((MyPanel) e.getSource()).setDan(((MyPanel) e.getSource()).getDan() + 1);
                    System.out.println("Go left in tile " + ((MyPanel) e.getSource()).getI());
                    passingRock();
                }
                else if (e.getX() >= ((MyPanel)e.getSource()).getWidth() - ((MyPanel) e.getSource()).arrowWidth){
                    //dummy feature, increase gem in said tile by 1
                    ((MyPanel) e.getSource()).setDan(((MyPanel) e.getSource()).getDan() + 1);
                    System.out.println("Go right in tile " + ((MyPanel) e.getSource()).getI());
                    passingRock();
                }
                timerCountDown();
            }

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
        if (e.getSource() instanceof MyPanel && ((MyPanel) e.getSource()).getOrientation().equals("center")){
            // enable lower tiles access for player 1 and upper tiles for player 2
            if ((turn == 1 && ((MyPanel) e.getSource()).getUoL().equals("lower"))
                    || (turn == 2 && ((MyPanel) e.getSource()).getUoL().equals("upper"))){
                ((MyPanel) e.getSource()).setArrow(true);
                ((JPanel) e.getSource()).repaint();
                ((JPanel) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof MyPanel && ((MyPanel) e.getSource()).getOrientation().equals("center")){
                ((MyPanel) e.getSource()).setArrow(false);
                ((JPanel) e.getSource()).repaint();
                ((JPanel) e.getSource()).setCursor(Cursor.getDefaultCursor());
            }
    }
    public void passingRock(){
        //dummy method, will change later
        this.setEnabled(false);
        System.out.println("Passing rock");
        for (int i = 0; i < 4; i++){
            try {
                Thread.sleep(1000); // Sleeping for 0.5 seconds, total 2 seconds
                System.out.println(".");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Done");
        this.setEnabled(true);
        if (turn == 1) {
            //update point 1, for now I will leave it to update by 1 for demonstration’s sake
            point1 += 1;
            updateText1();
            turn = 2;
        }
        else {
            //update point 2, for now I will leave it to update by 1 for demonstration’s sake
            point2 += 1;
            updateText2();
            turn = 1;
        }
    }
    private void updateText1() {
        player1Info.setText("<html><div style='text-align:left;'>"+ name1 + "<br>Points:"  + point1 + "</div></html>");
    }
    private void updateText2(){
        player2Info.setText("<html><div style='text-align:left;'>"+ name2 + "<br>Points:"  + point2 + "</div></html>");
    }
}