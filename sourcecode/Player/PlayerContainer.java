package sourcecode.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PlayerContainer extends JPanel {
    final Color HEADER_COLOR = Color.black;
    final int HEADER_SIZE = 115;
    final int PLAYER_INFO_WIDTH = 175;
    private Player player;
    private JLabel playerInfo;
    final Color TEXT_COLOR = Color.white;

    public PlayerContainer(Player player){
        this.player = player;
        this.setLayout(null);
        this.setBackground(HEADER_COLOR);
        this.setPreferredSize(new Dimension(HEADER_SIZE, HEADER_SIZE));
        playerInfo = new JLabel();
        playerInfo.setText("<html><div style='text-align:left;'>"+ player.getName() + "<br>Points:"  + player.getPoint() + "</div></html>");
        playerInfo.setForeground(TEXT_COLOR);
        playerInfo.setIcon(player.getPlayerImgIcon());
        playerInfo.setHorizontalTextPosition(JLabel.RIGHT);
        playerInfo.setVerticalTextPosition(JLabel.CENTER);
        playerInfo.setBounds((int) (PLAYER_INFO_WIDTH * 1.9) + PLAYER_INFO_WIDTH - HEADER_SIZE - 13, 0, PLAYER_INFO_WIDTH + player.getName().length()*5, HEADER_SIZE);
    }
    public void addComponent(JComponent component){
        this.add(component);
    }
    public void addPlayerInfo(){
        this.add(playerInfo);
    }
    public void setPlayerInfoBorder(Border border){
        playerInfo.setBorder(border);
    }

    public void setPlayerInfoText() {
        playerInfo.setText("<html><div style='text-align:left;'>"+ player.getName() + "<br>Points:"  + player.getPoint() + "</div></html>");
    }
}
