package sourcecode.Frame;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EndFrame extends JFrame{
    final int WIDTH = 700;
    final int HEIGHT = 500;
    final Color BACKGROUND_COLOR = Color.black;
    final Color TEXT_COLOR = Color.white;
    final Border indicatorBorder = BorderFactory.createLineBorder(Color.black, 2);
    private JLabel winnerDisplay;
    private JPanel playerInfo;
    private JLabel player1Info;
    private JLabel player2Info;
    EndFrame(ImageIcon player1Icon, ImageIcon player2Icon, String name1, String name2, int point1, int point2){
        winnerDisplay = new JLabel();
        winnerDisplay.setHorizontalAlignment(JLabel.CENTER);
        winnerDisplay.setFont(new Font("Arial", Font.BOLD, 30));
        winnerDisplay.setBackground(BACKGROUND_COLOR);
        winnerDisplay.setOpaque(true);
        winnerDisplay.setForeground(TEXT_COLOR);
        if (point1 > point2){
            winnerDisplay.setText(name1 + " win");
        }else if (point2 > point1){
            winnerDisplay.setText(name2 + " win");
        } else winnerDisplay.setText("Tie");
        winnerDisplay.setBorder(indicatorBorder);
        winnerDisplay.setBounds(0, 0, 688, 100);

        playerInfo = new JPanel();
        playerInfo.setBorder(indicatorBorder);
        playerInfo.setBounds(0, 100, 688, 365);
        playerInfo.setLayout(new GridLayout(1, 2));
        playerInfo.setBackground(BACKGROUND_COLOR);

        player1Info = new JLabel();
        player1Info.setIcon(player1Icon);
        player1Info.setText("<html><div style='text-align:center;'>"+ name1 + "<br>Points:"  + point1 + "</div></html>");
        player1Info.setHorizontalAlignment(JLabel.CENTER);
        player1Info.setHorizontalTextPosition(JLabel.CENTER);
        player1Info.setVerticalTextPosition(JLabel.BOTTOM);
        player1Info.setForeground(TEXT_COLOR);
        playerInfo.add(player1Info);

        player2Info = new JLabel();
        player2Info.setIcon(player2Icon);
        player2Info.setText("<html><div style='text-align:center;'>"+ name2 + "<br>Points:"  + point2 + "</div></html>");
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
