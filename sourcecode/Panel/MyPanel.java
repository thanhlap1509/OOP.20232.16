package sourcecode.Panel;

import sourcecode.GamePieces.Tile;

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
    public static final int MARGIN = 7;
    private Tile tile;

    public JLabel gemsIndicator;
    MyPanel(Tile tile){
        gemsIndicator = new JLabel();
        this.setLayout(null);
        setBackground(COLOR);
        this.setPreferredSize(new Dimension(SIZE / 2 + MARGIN + 2, (int) (SIZE*1.6)));
        this.tile = tile;
        this.add(gemsIndicator);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        if (tile.getIsPointed() == 1) g2D.setPaint(INDICATING_COLOR);
        else if (tile.getIsCollected() == 1) g2D.setPaint(COLLECTING_COLOR);
        else g2D.setPaint(BACKGROUND_COLOR);
        drawTiles(g2D);
        drawGems(g2D);
    }
    public void drawTiles(Graphics2D g2D){
    }
    public void drawGems(Graphics2D g2D){
    }

    public void setGemsIndicator(){
        if (tile.getIsPointed() == 1 || tile.getIsCollected() == 1) gemsIndicator.setForeground(BACKGROUND_COLOR);
        else gemsIndicator.setForeground(Color.black);
        gemsIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        gemsIndicator.setText(String.valueOf((getDan() + getQuan())));
        repaint();
    }
    public int getI() {
        return tile.getI();
    }
    public int getDan() {
        return tile.getDan();
    }
    public int getQuan() {
        return tile.getQuan();
    }
    public void setDan(int passDan) {
        tile.setDan(passDan);
    }
    public int getIsPointed() {
        return tile.getIsPointed();
    }

    public int getIsCollected() {
        return tile.getIsCollected();
    }

}
