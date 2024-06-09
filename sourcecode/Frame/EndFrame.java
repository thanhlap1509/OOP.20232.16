package sourcecode.Frame;
import sourcecode.Player.Player;

import javax.swing.*;
import java.awt.*;

public class EndFrame extends JFrame{
    final int WIDTH = 700;
    final int HEIGHT = 500;
    final Color BACKGROUND_COLOR = Color.black;
    final Color TEXT_COLOR = Color.white;
    private JLabel winnerDisplay;
    private JPanel playerInfo;
    private JLabel player1Info;
    private JLabel player2Info;
    EndFrame(Player player1, Player player2){
        winnerDisplay = new JLabel();
        winnerDisplay.setHorizontalAlignment(JLabel.CENTER);
        winnerDisplay.setFont(new Font("Arial", Font.BOLD, 30));
        winnerDisplay.setBackground(BACKGROUND_COLOR);
        winnerDisplay.setOpaque(true);
        winnerDisplay.setForeground(TEXT_COLOR);
        if (player1.getPoint() > player2.getPoint()){
            winnerDisplay.setText(player1.getName() + " win");
        }else if (player2.getPoint() > player1.getPoint()){
            winnerDisplay.setText(player2.getName() + " win");
        } else winnerDisplay.setText("Tie");
        winnerDisplay.setBounds(0, 0, 688, 100);

        playerInfo = new JPanel();
        playerInfo.setBounds(0, 100, 688, 365);
        playerInfo.setLayout(new GridLayout(1, 2));
        playerInfo.setBackground(BACKGROUND_COLOR);

        player1Info = new JLabel();
        player1Info.setIcon(player1.getPlayerImgIcon());
        player1Info.setText("<html><div style='text-align:center;'>"+ player1.getName() + "<br>Points:"  + player1.getPoint() + "</div></html>");
        player1Info.setHorizontalAlignment(JLabel.CENTER);
        player1Info.setHorizontalTextPosition(JLabel.CENTER);
        player1Info.setVerticalTextPosition(JLabel.BOTTOM);
        player1Info.setForeground(TEXT_COLOR);
        playerInfo.add(player1Info);

        player2Info = new JLabel();
        player2Info.setIcon(player2.getPlayerImgIcon());
        player2Info.setText("<html><div style='text-align:center;'>"+ player2.getName() + "<br>Points:"  + player2.getPoint() + "</div></html>");
        player2Info.setHorizontalAlignment(JLabel.CENTER);
        player2Info.setHorizontalTextPosition(JLabel.CENTER);
        player2Info.setVerticalTextPosition(JLabel.BOTTOM);
        player2Info.setForeground(TEXT_COLOR);
        playerInfo.add(player2Info);

        this.setTitle("End Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(winnerDisplay);
        this.add(playerInfo);
        this.setVisible(true);
    }
}
