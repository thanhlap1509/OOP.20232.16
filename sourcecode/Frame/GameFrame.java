package sourcecode.Frame;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;
public class GameFrame extends JFrame {
    final int HEADER_SIZE = 120;
    final Color HEADER_COLOR = Color.cyan;
    private int secondCountDown;
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
        Border yellowBorder = BorderFactory.createLineBorder(Color.yellow, 3);
        //player 1 container
        JLabel player1Info = new JLabel();
        player1Info.setText("<html>Name_1<br>Points: 0</html>");
        player1Info.setIcon(player1ImgIcon);
        player1Info.setBorder(yellowBorder);
        player1Info.setHorizontalTextPosition(JLabel.RIGHT);
        player1Info.setVerticalTextPosition(JLabel.CENTER);
        //player 2 container
        JLabel player2Info = new JLabel();
        player2Info.setText("<html>Name_2<br>Points: 0</html>");
        player2Info.setIcon(player2ImgIcon);
        player2Info.setHorizontalTextPosition(JLabel.LEFT);
        player2Info.setVerticalTextPosition(JLabel.CENTER);
        //game status container
        JPanel gameStatus = new JPanel();
        gameStatus.setLayout(new GridLayout(6, 1));
        JLabel turnDisplayed = new JLabel();
        turnDisplayed.setText("Current turn: Player 1");
        turnDisplayed.setBorder(new EmptyBorder(5, 5, 5, 5));
        turnDisplayed.setHorizontalAlignment(SwingConstants.CENTER);
        turnDisplayed.setVerticalAlignment(SwingConstants.TOP);

        JLabel timerLabel = new JLabel();
        timerLabel.setText("Time: 01:00");
        timerLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setVerticalAlignment(SwingConstants.TOP);
        timerCountDown(timerLabel);
        gameStatus.add(turnDisplayed);
        gameStatus.add(timerLabel);
        gameStatus.setBackground(HEADER_COLOR);
        //header
        JPanel gameInfo = new JPanel();
        gameInfo.setLayout(new BorderLayout());
        gameInfo.setBackground(HEADER_COLOR);
        gameInfo.setPreferredSize(new Dimension(HEADER_SIZE, HEADER_SIZE));
        gameInfo.add(player1Info,BorderLayout.WEST);
        gameInfo.add(gameStatus, BorderLayout.CENTER);
        gameInfo.add(player2Info, BorderLayout.EAST);
        //frame
        this.setTitle("Game Frame");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(gameInfo, BorderLayout.NORTH);
        this.setVisible(true);
    }
    private void timerCountDown(JLabel timerLabel){
        Timer timer = new Timer();
        secondCountDown = 60;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (secondCountDown >= 0) {
                    String string = String.format("Timer: %02d:%02d", secondCountDown / 60, secondCountDown % 60);
                    timerLabel.setText(string);
                    System.out.println(secondCountDown);
                    secondCountDown--;
                } else {
                    timer.cancel();
                    System.out.println("Countdown finished.");
                }
            }
        }, 0, 1000); // Run the task every second
    }
}