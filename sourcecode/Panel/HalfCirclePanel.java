package sourcecode.Panel;

import sourcecode.GamePieces.Tile;

import java.awt.*;

public class HalfCirclePanel extends MyPanel{
    private String orientation;
    final int MAX_GEM_PER_CIRCLE = 6;
    private int surplus = 0;
    private int mark = 0;
    private int range;
    private int reachMax = 0;
    public HalfCirclePanel(String ori, Tile tile) {
        super(tile);
        this.orientation = ori;
    }
    @Override
    public void drawTiles(Graphics2D g2D){
        switch(orientation){
            case "left" -> {
                setGemsIndicator();
                g2D.fillArc(MARGIN / 2, MARGIN, getWidth() * 2 - MARGIN, getHeight() - MARGIN*2, 90, 180);
                g2D.setBackground(getBackground());
            }
            case "right" -> {
                setGemsIndicator();
                g2D.fillArc(-(SIZE / 2 + (MARGIN / 2) + 1), MARGIN, getWidth() *2 - (int)(MARGIN*1.5), getHeight() - MARGIN*2, 270, 180);
                g2D.setBackground(getBackground());
            }
        }
    }
    @Override
    public void drawGems(Graphics2D g2D){
        surplus = 0;
        mark = 0;
        range = 0;
        reachMax = 0;
        if (this.getIsCollected() == 1 || this.getIsPointed() == 1) g2D.setPaint(BACKGROUND_COLOR);
        else g2D.setPaint(Color.black);
        int dan = getDan();
        int quan = getQuan();
        switch(orientation){
            case "left" -> {
                //draw circle indicating number of small gems
                for (int j = 0; j < dan; j++){
                    range = j - mark;
                    g2D.fillArc((int)(MARGIN*2.5 + 14 + (range % (MAX_GEM_PER_CIRCLE + surplus))*1.5*GEM_SIZE - surplus*1.5*GEM_SIZE), (int)(getHeight() / 4 + MARGIN + 20 + (reachMax == 2 ? MAX_GEM_PER_CIRCLE - 1 - surplus : surplus)*1.5*GEM_SIZE), GEM_SIZE, GEM_SIZE, 0, 360);
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
                //draw circle indicating number of small gems
                for (int j = 0; j < dan; j++){
                    range = j - mark;
                    //System.out.println("J is " + j + " surplus is " + surplus + " mark is " + mark);
                    g2D.fillArc((int)(MARGIN + (range % (MAX_GEM_PER_CIRCLE + surplus))*1.5*GEM_SIZE), (int)(getHeight() / 4 + MARGIN + 20 + (reachMax == 2 ? MAX_GEM_PER_CIRCLE -1- surplus : surplus)*1.5*GEM_SIZE), GEM_SIZE, GEM_SIZE, 0, 360);
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
        }
    }
    @Override
    public void setGemsIndicator(){
        if (this.getIsPointed() == 1 || this.getIsCollected() == 1) gemsIndicator.setForeground(BACKGROUND_COLOR);
        else gemsIndicator.setForeground(Color.black);
        gemsIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        gemsIndicator.setText(String.valueOf((this.getDan() + this.getQuan())));
        if (orientation.equals("left")){
            gemsIndicator.setBounds(getWidth() - 20, 5, 20, 20);
        }
        else if (orientation.equals("right")){
            gemsIndicator.setBounds(5, 5, 20, 20);
        }
        repaint();

    }
}
