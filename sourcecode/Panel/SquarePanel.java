package sourcecode.Panel;

import java.awt.*;

public class SquarePanel extends MyPanel{
    private boolean paintLeft;
    private boolean paintRight;
    private boolean showArrow;
    private String UoL;
    public SquarePanel(String UoL, int i, int dan, int quan) {
        super( i, dan, quan);
        this.UoL = UoL;
        paintLeft = paintRight = false;
        showArrow = false;
    }
    @Override
    public void drawTiles(Graphics2D g2D){
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
    @Override
    public void drawGems(Graphics2D g2D){
        if (this.getIsCollected() == 1 || this.getIsPointed() == 1) g2D.setPaint(BACKGROUND_COLOR);
        else g2D.setPaint(Color.black);
        int dan = this.getDan();
        //draw circle indicating number of small gems
        for (int i = 0; i < dan; i++){
            g2D.fillArc((int)(MARGIN*2 + 14 + (i % MAX_GEM_PER_SQUARE)*1.5*GEM_SIZE), (int)(MARGIN + 20 + (i / MAX_GEM_PER_SQUARE - 1)*1.5*GEM_SIZE), GEM_SIZE, GEM_SIZE, 0, 360);
        }
    }
    @Override
    public void setGemsIndicator(){
        if (this.getIsPointed() == 1 || this.getIsCollected() == 1) gemsIndicator.setForeground(BACKGROUND_COLOR);
        else gemsIndicator.setForeground(Color.black);
        gemsIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        gemsIndicator.setText(String.valueOf((this.getDan() + this.getQuan())));
         gemsIndicator.setBounds(10, 5, 20, 20);
         repaint();
    }
    public void setArrow(boolean value){
        showArrow = value;
    }
    public void setPaintLeft(boolean paintLeft) {this.paintLeft = paintLeft;}
    public void setPaintRight(boolean paintRight) {this.paintRight = paintRight;}
    public String getUoL() {
        return UoL;
    }
}

