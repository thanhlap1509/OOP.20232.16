package sourcecode.Frame;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    String orientation;
     static final int SIZE = 200;
    static final int MARGIN = 7;
    private int i;
    final Color COLOR = Color.black;
    private String UoL;
    private boolean showArrow;
    public int arrowWidth;
    MyPanel(String ori){
        showArrow = false;
        this.setLayout(new BorderLayout());
        setBackground(COLOR);
        this.setPreferredSize(new Dimension(SIZE / 2 + MARGIN + 2, (int) (SIZE*1.6)));
        this.orientation = ori;
        this.setVisible(true);
    }
    MyPanel(String ori, String uol, int i){
        showArrow = false;
        setBackground(COLOR);
        setBackground(COLOR);
        this.setPreferredSize(new Dimension(SIZE / 2 + MARGIN + 2, (int) (SIZE*1.6)));
        this.orientation = ori;
        this.UoL = uol;
        this.i = i;
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2));
        switch (orientation) {
            case "left" -> {
                this.i = 11;
                g2D.setPaint(Color.white);
                g2D.fillArc(MARGIN / 2, MARGIN, SIZE, (int) (SIZE * 1.2), 90, 180);
                g2D.setBackground(getBackground());
            }
            case "right" -> {
                this.i = 5;
                g2D.setPaint(Color.white);
                g2D.fillArc(-SIZE / 2 + MARGIN / 2, MARGIN, SIZE, (int) (SIZE * 1.2), 270, 180);
                g2D.setBackground(getBackground());
            }
            case "center" -> {
                if (UoL.equals("upper")) {
                    g2D.setPaint(Color.white);
                    g2D.fillRect(MARGIN, MARGIN, (getWidth() - MARGIN * 2), (int) (SIZE * 0.55) + MARGIN / 2);
                    g2D.setBackground(getBackground());
                } else if (UoL.equals("lower")) {
                    g2D.setPaint(Color.white);
                    g2D.fillRect(MARGIN, MARGIN / 2, (getWidth() - MARGIN * 2), (int) (SIZE * 0.55) + MARGIN / 2);
                    g2D.setBackground(getBackground());
                }
            }
        }
        g2D.setPaint(Color.black);
        // draw arrow if I let it
        if (showArrow){
            arrowWidth = (int)(getWidth() / 4);
            int height = getHeight();
            // draw left arrow
            g2D.drawLine(MARGIN / 2, (int)(height / 2), arrowWidth, (int)(height / 4));
            g2D.drawLine(MARGIN / 2, (int)(height / 2), arrowWidth, (int)(height * 3 / 4));
            g2D.drawLine(arrowWidth, (int)(height / 4), arrowWidth, (int)(height * 3 / 4));
            //draw right arrow
            g2D.drawLine(arrowWidth * 3, (int)(height / 4), arrowWidth * 4 - MARGIN / 2, (int)(height / 2));
            g2D.drawLine(arrowWidth * 3, (int)(height * 3 / 4), arrowWidth * 4 - MARGIN / 2, (int)(height / 2));
            g2D.drawLine(arrowWidth * 3, (int)(height / 4), arrowWidth * 3, (int)(height * 3 / 4));
        }
    }
    public int getI() {
        return i;
    }
    public void setArrow(boolean value){
        showArrow = value;
    }
}
