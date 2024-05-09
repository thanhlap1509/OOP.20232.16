package sourcecode.Frame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
public class MyPanel extends JPanel{
    private String orientation;
    final int GEM_SIZE = 8;
    final int LARGE_GEM_SIZE = 10;
    final int MAX_GEM_PER_SQUARE = 7;
    static final int SIZE = 200;
    static final int MARGIN = 7;
    final Color COLOR = Color.black;
    final Color BACKGROUND_COLOR = Color.white;
    final Color INDICATING_COLOR = Color.gray;
    private String UoL;
    private JLabel gemsIndicator;
    private boolean paintLeft;
    private boolean paintRight;
    private boolean showArrow;
    public int arrowWidth;
    private int dan;
    private int quan;
    private int i;
    private int isPointed = 0;
    MyPanel(String ori,int i, int dan, int quan){
        gemsIndicator = new JLabel();
        paintLeft = paintRight = false;
        showArrow = false;
        this.setLayout(null);
        //this.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        setBackground(COLOR);
        this.setPreferredSize(new Dimension(SIZE / 2 + MARGIN + 2, (int) (SIZE*1.6)));
        this.orientation = ori;
        this.i = i;
        this.dan = dan;
        this.quan = quan;
        this.add(gemsIndicator);
        this.setVisible(true);
    }

    MyPanel(String ori, String uol, int i, int dan, int quan){
        gemsIndicator = new JLabel();
        paintLeft = paintRight = false;
        showArrow = false;
        this.setLayout(null);
        //this.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        setBackground(COLOR);
        this.setPreferredSize(new Dimension(SIZE / 2 + MARGIN + 2, (int) (SIZE*1.6)));
        this.orientation = ori;
        this.UoL = uol;
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
        if (isPointed == 0) g2D.setPaint(BACKGROUND_COLOR);
        else if (isPointed ==1) g2D.setPaint(INDICATING_COLOR);
        switch (orientation) {
            case "left" -> {
                setGemsIndicator();
                this.i = 11;
                g2D.fillArc(MARGIN / 2, MARGIN, getWidth() * 2 - MARGIN, getHeight() - MARGIN*2, 90, 180);
                g2D.setBackground(getBackground());
            }
            case "right" -> {
                setGemsIndicator();
                this.i = 5;
                g2D.fillArc(-(SIZE / 2 + (MARGIN / 2) + 1), MARGIN, getWidth() *2 - (int)(MARGIN*1.5), getHeight() - MARGIN*2, 270, 180);
                g2D.setBackground(getBackground());
            }
            case "center" -> {
                setGemsIndicator();
                if (UoL.equals("upper")) {
                    g2D.fillRect(MARGIN/2, MARGIN, (getWidth() - MARGIN), (int) (getHeight() * 0.9) + MARGIN / 2);
                    g2D.setBackground(getBackground());
                } else if (UoL.equals("lower")) {
                    g2D.fillRect(MARGIN/2, MARGIN / 2, (getWidth() - MARGIN), (int) (getHeight() * 0.9) + MARGIN / 2);
                    g2D.setBackground(getBackground());
                }
                g2D.setPaint(Color.black);
                // draw arrow if I let it
                if (showArrow){
                    int height = getHeight();
                    arrowWidth = (int)(getWidth() / 6);
                    // draw left arrow
                    g2D.drawLine(MARGIN, (int)(height / 2), arrowWidth, (int)(height / 4));
                    g2D.drawLine(MARGIN, (int)(height / 2), arrowWidth, (int)(height * 3 / 4));
                    g2D.drawLine(arrowWidth, (int)(height / 4), arrowWidth, (int)(height * 3 / 4));
                        // paint left arrow
                    if (paintLeft){
                        g2D.fillPolygon(new int[]{MARGIN, arrowWidth, arrowWidth}, new int[]{height / 2, height / 4, height *3/4}, 3);
                    }
                    //draw right arrow
                    g2D.drawLine( getWidth() - arrowWidth, (int)(height / 4), getWidth() - MARGIN, (int)(height / 2));
                    g2D.drawLine(getWidth() - arrowWidth, (int)(height * 3 / 4), getWidth() - MARGIN, (int)(height / 2));
                    g2D.drawLine(getWidth() - arrowWidth, (int)(height / 4), getWidth() - arrowWidth, (int)(height * 3 / 4));
                        // paint right arrow
                    if (paintRight){
                        g2D.fillPolygon(new int[]{getWidth() - MARGIN, getWidth() - arrowWidth, getWidth() - arrowWidth}, new int[]{height / 2, height / 4, height * 3 / 4}, 3);
                    }
                }
            }
        }
        drawGems(g2D);
    }
    private void drawGems(Graphics2D g2D){
        if (isPointed == 0) g2D.setPaint(Color.black);
        else if (isPointed == 1) g2D.setPaint(BACKGROUND_COLOR);
        switch(orientation){
            case "left" -> {
                int MAX_GEM_PER_CIRCLE = 6;
                int surplus = 0;
                int mark = 0;
                int RANGE;
                int reachMax = 0;
                //draw circle indicating number of small gems
                for (int j = 0; j < dan; j++){
                        RANGE = j - mark;
                        g2D.fillArc((int)(MARGIN*2.5 + 14 + (RANGE % (MAX_GEM_PER_CIRCLE + surplus))*1.5*GEM_SIZE - surplus*1.5*GEM_SIZE), (int)(getHeight() / 4 + MARGIN + 20 + (reachMax == 2 ? MAX_GEM_PER_CIRCLE - 1 - surplus : surplus)*1.5*GEM_SIZE), GEM_SIZE, GEM_SIZE, 0, 360);
                        if ((j + 1 - mark) % (MAX_GEM_PER_CIRCLE + surplus) == 0) {
                            if (reachMax == 0) surplus++;
                            else if (reachMax == 2){
                                if (surplus > 0) surplus--;
                            }
                            if (surplus == 2) {
                                if (reachMax == 0) reachMax++;
                                else if (reachMax == 1) {
                                    reachMax = 2;
                                    surplus = 2;
                                }
                            }
                            mark = j + 1;
                        }
                }
                //draw circle indicating number of large gems
                for (int i = 0; i < quan; i++){
                    g2D.fillArc((int)(MARGIN*2.5 + 14 + ((dan + i - mark) % (MAX_GEM_PER_CIRCLE + surplus))*1.5*GEM_SIZE - surplus*1.5*GEM_SIZE), (int)(getHeight() / 4 +MARGIN + 20 + ((reachMax == 2 ? MAX_GEM_PER_CIRCLE - 1 - surplus : surplus) + (dan == 50 && i == 1 ? 1 : 0))*1.5*GEM_SIZE), LARGE_GEM_SIZE, LARGE_GEM_SIZE, 0, 360);
                    if ((dan + i + 1 - mark) % (MAX_GEM_PER_CIRCLE + surplus) == 0) {
                        if (reachMax == 0) surplus++;
                        else if (reachMax == 2){
                            if (surplus > 0) surplus--;
                        }
                        if (surplus == 2) {
                            if (reachMax == 0) reachMax++;
                            else if (reachMax == 1) {
                                reachMax = 2;
                                surplus = 2;
                            }
                        }
                        mark = dan + i + 1;
                    }
                }
            }
            case "right" -> {
                int MAX_GEM_PER_CIRCLE = 6;
                int surplus = 0;
                int mark = 0;
                int RANGE;
                int reachMax = 0;
                //draw circle indicating number of small gems
                for (int j = 0; j < dan; j++){
                    RANGE = j - mark;
                    //System.out.println("J is " + j + " surplus is " + surplus + " mark is " + mark);
                    g2D.fillArc((int)(MARGIN + (RANGE % (MAX_GEM_PER_CIRCLE + surplus))*1.5*GEM_SIZE), (int)(getHeight() / 4 + MARGIN + 20 + (reachMax == 2 ? MAX_GEM_PER_CIRCLE -1- surplus : surplus)*1.5*GEM_SIZE), GEM_SIZE, GEM_SIZE, 0, 360);
                    if ((j + 1 - mark) % (MAX_GEM_PER_CIRCLE + surplus) == 0) {
                        if (reachMax == 0) surplus++;
                        else if (reachMax == 2){
                            if (surplus > 0) surplus--;
                        }
                        if (surplus == 2) {
                            if (reachMax == 0) reachMax++;
                            else if (reachMax == 1) {
                                reachMax = 2;
                                surplus = 2;
                            }
                        }
                        mark = j + 1;
                    }
                }
                //draw circle indicating number of large gems
                for (int i = 0; i < quan; i++){
                    g2D.fillArc((int)(MARGIN + ((dan + i - mark) % (MAX_GEM_PER_CIRCLE + surplus))*1.5*GEM_SIZE), (int)(getHeight() / 4 +MARGIN + 20 + ((reachMax == 2 ? MAX_GEM_PER_CIRCLE - 1 - surplus : surplus) + (dan == 50 && i == 1 ? 1 : 0))*1.5*GEM_SIZE), LARGE_GEM_SIZE, LARGE_GEM_SIZE, 0, 360);
                    if ((dan + i + 1 - mark) % (MAX_GEM_PER_CIRCLE + surplus) == 0) {
                        if (reachMax == 0) surplus++;
                        else if (reachMax == 2){
                            if (surplus > 0) surplus--;
                        }
                        if (surplus == 2) {
                            if (reachMax == 0) reachMax++;
                            else if (reachMax == 1) {
                                reachMax = 2;
                                surplus = 2;
                            }
                        }
                        mark = dan + i + 1;
                    }
                }
            }
            case "center" -> {
                //draw circle indicating number of small gems
                for (int i = 0; i < dan; i++){
                    g2D.fillArc((int)(MARGIN*2 + 14 + (i % MAX_GEM_PER_SQUARE)*1.5*GEM_SIZE), (int)(MARGIN + 20 + (i / MAX_GEM_PER_SQUARE - 1)*1.5*GEM_SIZE), GEM_SIZE, GEM_SIZE, 0, 360);
                }
                //draw circle indicating number of large gems
                for (int i = 0; i < quan; i++){
                    g2D.fillArc((int)(MARGIN*2 + 14 + ((dan + i) % MAX_GEM_PER_SQUARE)*1.5*GEM_SIZE), (int)(MARGIN + 20 + ((dan + i) / MAX_GEM_PER_SQUARE - 1)*1.5*GEM_SIZE), LARGE_GEM_SIZE, LARGE_GEM_SIZE, 0, 360);
                }
        }
    }
    }
    private void setGemsIndicator(){
        if (isPointed == 1) gemsIndicator.setForeground(BACKGROUND_COLOR);
        gemsIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        gemsIndicator.setText(String.valueOf((dan + quan)));
        if (orientation.equals("left")){
            gemsIndicator.setBounds(getWidth() - 20, 5, 20, 20);
        }
        else if (orientation.equals("right")){
            gemsIndicator.setBounds(5, 5, 20, 20);
        }
        else gemsIndicator.setBounds(10, 5, 20, 20);
        repaint();
    }
    public int getI() {
        return i;
    }
    public String getOrientation() {
        return orientation;
    }
    public String getUoL() {
        return UoL;
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
    public void setArrow(boolean value){
        showArrow = value;
    }
    public void setIsPointed(int i){isPointed = i;}
    public void setPaintLeft(boolean paintLeft) {this.paintLeft = paintLeft;}
    public void setPaintRight(boolean paintRight) {this.paintRight = paintRight;}
}
