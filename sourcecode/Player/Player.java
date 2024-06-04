package sourcecode.Player;

import javax.swing.*;

public class Player {
    private String name;
    private int point;
    private ImageIcon playerImgIcon;
    public Player(String name, int point, ImageIcon playerImgIcon){
        this.name = name;
        this.point = point;
        this.playerImgIcon = playerImgIcon;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ImageIcon getPlayerImgIcon() {
        return playerImgIcon;
    }
}
