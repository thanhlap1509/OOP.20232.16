package sourcecode.Panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
public abstract class MyPanel extends JPanel{
    final int GEM_SIZE = 8;
    final int LARGE_GEM_SIZE = 12;
    final int MAX_GEM_PER_SQUARE = 7;
    final Color COLOR = Color.black;
    final Color BACKGROUND_COLOR = Color.white;
    final Color INDICATING_COLOR = Color.gray;
    final Color COLLECTING_COLOR = new Color(212, 175, 55);
    public static final int SIZE = 200;
    static final int MARGIN = 7;
    private final int i;
    public JLabel gemsIndicator;
    public int arrowWidth;
    private int dan;
    private int quan;
    private int isPointed = 0;
    private int isCollected = 0;
    MyPanel(int i, int dan, int quan){
        gemsIndicator = new JLabel();
        this.setLayout(null);
        setBackground(COLOR);
        this.setPreferredSize(new Dimension(SIZE / 2 + MARGIN + 2, (int) (SIZE*1.6)));
        this.i = i;
        this.dan = dan;
        this.quan = quan;
        this.add(gemsIndicator);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        if (isPointed == 1) g2D.setPaint(INDICATING_COLOR);
        else if (isCollected == 1) g2D.setPaint(COLLECTING_COLOR);
        else g2D.setPaint(BACKGROUND_COLOR);
        drawTiles(g2D);
        drawGems(g2D);
    }
    public void drawTiles(Graphics2D g2D){
    }
    public void drawGems(Graphics2D g2D){
    }

    public int getIsPointed() {
        return isPointed;
    }

    public int getIsCollected() {
        return isCollected;
    }

    public void setGemsIndicator(){
        if (isPointed == 1 || isCollected == 1) gemsIndicator.setForeground(BACKGROUND_COLOR);
        else gemsIndicator.setForeground(Color.black);
        gemsIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        gemsIndicator.setText(String.valueOf((dan + quan)));
        repaint();
    }
    public int getI() {
        return i;
    }
    public int getDan() {
        return dan;
    }
    public int getQuan() {
        return quan;
    }
    public void setDan(int passDan) {
        dan = passDan;
    }
    public void setQuan(int passQuan){
        quan = passQuan;
    }
    public void setIsPointed(int i){isPointed = i;}
    public void setIsCollected(int i){isCollected = i;}

}
