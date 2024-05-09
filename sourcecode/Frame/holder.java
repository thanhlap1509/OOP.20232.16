package sourcecode.Frame;

import javax.swing.*;
import java.awt.*;

public class holder {
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (e.getSource() == exitMenu){
//            System.out.println("Exit");
//            timer.cancel();
//            this.dispose();
//        }
//        //only accept center square
//        else if (e.getSource() instanceof MyPanel && ((MyPanel) e.getSource()).getOrientation().equals("center") && ((MyPanel) e.getSource()).getDan() > 0){
//            // enable lower tiles access for player 1 and upper tiles for player 2
//            if ((turn == 1 && ((MyPanel) e.getSource()).getUoL().equals("lower"))
//                    || (turn == 2 && ((MyPanel) e.getSource()).getUoL().equals("upper"))){
//                timer.cancel();
//                //if player click in left arrow
//                if (e.getX() >= 0 && e.getX() <= ((MyPanel) e.getSource()).arrowWidth){
//                    int index = ((MyPanel) e.getSource()).getI();
//                    System.out.println("Go left in tile " + index);
//                    //stop user from interact with tile anymore
//                    this.setEnabled(false);
//                    spreadGems(index, "left");
//                }
//                //if player click in right arrow
//                else if (e.getX() >= ((MyPanel)e.getSource()).getWidth() - ((MyPanel) e.getSource()).arrowWidth){
//                    int index = ((MyPanel) e.getSource()).getI();
//                    System.out.println("Go right in tile " + index);
//                    //stop user from interact with tile anymore
//                    this.setEnabled(false);
//                    spreadGems(index, "right");
//                }
//            }
//        }
//        //dummy feature, increase gem in outer left and right tile by 1
//        else if (e.getSource() instanceof MyPanel && (((MyPanel) e.getSource()).getOrientation().equals("left") || ((MyPanel) e.getSource()).getOrientation().equals("right"))){
//            ((MyPanel) e.getSource()).setDan(((MyPanel) e.getSource()).getDan() + 1);
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        //paint arrow if mouse enter arrow
//        //if player click in left arrow
//        if (e.getSource() instanceof MyPanel && ((MyPanel) e.getSource()).getDan() > 0){
//            if (e.getX() >= 0 && e.getX() <= ((MyPanel) e.getSource()).arrowWidth){
//                ((MyPanel) e.getSource()).setPaintLeft(true);
//            }
//            //if player click in right arrow
//            else if (e.getX() >= ((MyPanel)e.getSource()).getWidth() - ((MyPanel) e.getSource()).arrowWidth) {
//                ((MyPanel) e.getSource()).setPaintRight(true);
//            }
//        }
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//        if (e.getSource() instanceof MyPanel && ((MyPanel) e.getSource()).getOrientation().equals("center") && ((MyPanel) e.getSource()).getDan() > 0){
//            // enable lower tiles access for player 1 and upper tiles for player 2
//            if ((turn == 1 && ((MyPanel) e.getSource()).getUoL().equals("lower"))
//                    || (turn == 2 && ((MyPanel) e.getSource()).getUoL().equals("upper"))){
//                ((MyPanel) e.getSource()).setArrow(true);
//                ((JPanel) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            }
//        }
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//        if (e.getSource() instanceof MyPanel && ((MyPanel) e.getSource()).getOrientation().equals("center")){
//            ((MyPanel) e.getSource()).setArrow(false);
//            ((MyPanel) e.getSource()).setPaintLeft(false);
//            ((MyPanel) e.getSource()).setPaintRight(false);
//            ((JPanel) e.getSource()).setCursor(Cursor.getDefaultCursor());
//        }
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (gemToSpread > 0){
//            //add one gem to tile, and set index to next tile
//            tiles[lastIndex].setIsPointed(0);
//            tiles[index].setDan(tiles[index].getDan() + 1);
//            tiles[index].setIsPointed(1);
//            gemToSpread--;
//            setGem(gemToSpread);
//            lastIndex = index;
//            index += step;
//            checkIndex();
//        } else {
//            timer1.stop();
//            tiles[lastIndex].setIsPointed(0);
//            recursiveSpreadGems(startIndex, direction);
//        }
//    }
}
