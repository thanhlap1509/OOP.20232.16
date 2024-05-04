package sourcecode.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPanel extends JPanel implements MouseListener {
    String orientation;
     static final int SIZE = 200;
    static final int MARGIN = 7;
    private int i;
    final Color COLOR = Color.black;
    private String UoL;
    private boolean showArrow;
    private int arrowWidth;
    MyPanel(String ori){
        showArrow = false;
        this.addMouseListener(this);
        this.setLayout(new BorderLayout());
        setBackground(COLOR);
        this.setPreferredSize(new Dimension(SIZE / 2 + MARGIN + 2, (int) (SIZE*1.6)));
        this.orientation = ori;
        this.setVisible(true);
    }

    public int getI() {
        return i;
    }

    MyPanel(String ori, String uol, int i){
        showArrow = false;
        this.addMouseListener(this);
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
               // g2D.drawArc(MARGIN / 2, MARGIN, SIZE, (int) (SIZE * 1.2), 90, 180);
                //g2D.drawLine(SIZE / 2 + MARGIN / 2, MARGIN, SIZE / 2 + MARGIN / 2, (int) (SIZE * 1.2) + MARGIN);
                // come inside
                g2D.setPaint(Color.white);
                g2D.fillArc(MARGIN / 2, MARGIN, SIZE, (int) (SIZE * 1.2), 90, 180);
                g2D.setBackground(getBackground());
            }
            case "right" -> {
                this.i = 5;
                //g2D.drawArc(-SIZE / 2 + MARGIN / 2, MARGIN, SIZE, (int) (SIZE * 1.2), 270, 180);
               // g2D.drawLine(MARGIN / 2, MARGIN, MARGIN / 2, (int) (SIZE * 1.2) + MARGIN);
                //come inside
                g2D.setPaint(Color.white);
                g2D.fillArc(-SIZE / 2 + MARGIN / 2, MARGIN, SIZE, (int) (SIZE * 1.2), 270, 180);
                g2D.setBackground(getBackground());
            }
            case "center" -> {
                if (UoL.equals("upper")) {
                    //g2D.drawRect(MARGIN, MARGIN, (getWidth() - MARGIN * 2), (int) (SIZE * 0.55) + MARGIN / 2);
                    g2D.setPaint(Color.white);
                    g2D.fillRect(MARGIN, MARGIN, (getWidth() - MARGIN * 2), (int) (SIZE * 0.55) + MARGIN / 2);
                    g2D.setBackground(getBackground());
                } else if (UoL.equals("lower")) {
                    //g2D.drawRect(MARGIN, MARGIN / 2, (getWidth() - MARGIN * 2), (int) (SIZE * 0.55) + MARGIN / 2);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() >= 0 && e.getX() <= arrowWidth){
            System.out.println("Go left in tile " + this.i);
        }
        else if (e.getX() >= arrowWidth * 3){
            System.out.println("Go right in tile " + this.i);
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
        System.out.println("You are touching " + this.i + " tile");
        ((JPanel) e.getSource()).repaint();
        ((JPanel) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.showArrow = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((JPanel) e.getSource()).repaint();
        ((JPanel) e.getSource()).setCursor(Cursor.getDefaultCursor());
        this.showArrow = false;
    }
}
