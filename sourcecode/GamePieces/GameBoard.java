package sourcecode.GamePieces;

import sourcecode.Frame.GameFrame;
import sourcecode.Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameBoard {
    final int SECOND_TO_SLEEP = 400;
    final int HEADER_SIZE = 115;
    final int QUAN_POINT = 5;
    private int gemToSpread = 0;
    private int index = 0;
    private int step = 0;
    private int lastIndex;
    public Tile[] tiles;
    private int turn;
    public Player player1;
    public Player player2;
    private GameFrame parent;
    public GameBoard(GameFrame parent){
        this.parent = parent;
        tiles = new Tile[12];
        for (int i = 0; i < 12; i++){
            if (i == 5 || i == 11) tiles[i] = new Tile(i, 0, 1);
            else tiles[i] = new Tile(i, 5, 0);
        }
        turn = 1;
        initializePlayer();
    }
    private void initializePlayer(){
        //getting name,imageicon for two player and initiate point = 0
        String name1= null;
        String name2 = null;
        while (name1 == null|| name1.isEmpty()) name1 = JOptionPane.showInputDialog(null, "Input name for first player", "", JOptionPane.QUESTION_MESSAGE);
        name1 = name1.toUpperCase();
        while (name2 == null || name2.isEmpty()) name2 = JOptionPane.showInputDialog(null, "Input name for second player", "", JOptionPane.QUESTION_MESSAGE);
        name2 = name2.toUpperCase();
        // try-catch reading png file into image instances then convert to image icon
        BufferedImage p1BuffImg = null;
        BufferedImage p2BuffImg = null;
        try{
            p1BuffImg = ImageIO.read(new File("Picture/players1.png"));
            p2BuffImg = ImageIO.read(new File("Picture/players2.png"));
        } catch (IOException e){
            System.out.println("lỗi rồi ông giáo ơi");
        }
        Image player1Img = p1BuffImg.getScaledInstance(HEADER_SIZE, HEADER_SIZE, Image.SCALE_DEFAULT);
        ImageIcon player1ImgIcon = new ImageIcon(player1Img);
        Image player2Img = p2BuffImg.getScaledInstance(HEADER_SIZE, HEADER_SIZE, Image.SCALE_DEFAULT);
        ImageIcon player2ImgIcon = new ImageIcon(player2Img);
        player1 = new Player(name1, 0, player1ImgIcon);
        player2 = new Player(name2, 0, player2ImgIcon);
    }
    public void spreadGems(int i, String direction){
        index = i;
        step = 0;
        //skip outer tiles
        if (index == 11 || index == 5) return;
        //get gem from tile and clear gem in said tile
        gemToSpread = tiles[index].getDan();
        setGem(gemToSpread);
        tiles[index].setDan(0);
        //get step from location and direction
        if (index <= 4){
            if (direction.equals("left")) step = -1;
            else if (direction.equals("right")) step = 1;
        }
        else if (index <= 10){
            if (direction.equals("left")) step = 1;
            else if (direction.equals("right")) step = -1;
        }
        //create holder for initial starting point and previous point
        int startIndex = index;
        lastIndex = index;
        // starting from next tile
        index += step;
        checkIndexForward();
        //spread gem from next tile
        parent.timer = new Timer(SECOND_TO_SLEEP, e -> {
            if (gemToSpread > 0){
                //add one gem to tile, and set index to next tile
                tiles[lastIndex].setIsPointed(0);
                tiles[index].setDan(tiles[index].getDan() + 1);
                tiles[index].setIsPointed(1);
                gemToSpread--;
                setGem(gemToSpread);
                lastIndex = index;
                index += step;
                checkIndexForward();
            } else {
                parent.timer.stop();
                tiles[lastIndex].setIsPointed(0);
                recursiveSpreadGems(startIndex, direction);
            }
        });
        parent.timer.setInitialDelay(10);
        parent.timer.start();
    }
    private void recursiveSpreadGems(int startIndex, String direction){
        // TODO:SPREAD GEM RECURSIVELY AND/OR GET POINT
        // we check if the next tile is outer tile or not
        // if not, we check if next tile has gem or not, if yes then spread gem again
        if (index != 11 && index != 5 && tiles[index].getDan() > 0) {
            if (startIndex <= 4) {
                if (direction.equals("left")) {
                    if (index <= 4) spreadGems(index, "left");
                    else if (index <= 10) spreadGems(index, "right");
                } else if (direction.equals("right")) {
                    if (index <= 4) spreadGems(index, "right");
                    else if (index <= 10) spreadGems(index, "left");
                }
            } else if (startIndex <= 10) {
                if (direction.equals("left")) {
                    if (index <= 4) spreadGems(index, "right");
                    else if (index <= 10) spreadGems(index, "left");
                } else if (direction.equals("right")) {
                    if (index <= 4) spreadGems(index, "left");
                    else if (index <= 10) spreadGems(index, "right");
                }
            }
        } else {
            addPoint();
            parent.setEnabled(true);
        }
    }
    private void addPoint(){
        final int[] iter = {0};
        parent.timer = new Timer(SECOND_TO_SLEEP, e -> {
            if (iter[0] < 6 && tiles[index].getDan() == 0){
                index+= step;
                checkIndexForward();
                tiles[lastIndex].setIsCollected(0);
                lastIndex = index;
                if (tiles[index].getDan() > 0){
                    tiles[index].setIsCollected(1);
                    int dan = tiles[index].getDan();
                    int quan = tiles[index].getQuan();
                    tiles[index].setDan(0);
                    tiles[index].setQuan(0);
                    if (turn == 1){
                        player1.setPoint(player1.getPoint() + dan + quan*QUAN_POINT);
                    }
                    else if (turn == 2){
                        player2.setPoint(player2.getPoint() + dan + quan*QUAN_POINT);
                    }
                }
                else {
                    parent.timer.stop();
                    tiles[lastIndex].setIsCollected(0);
                    parent.afterTurnAction();
                }
                index += step;
                checkIndexForward();
                iter[0]++;
            }else {
                parent.timer.stop();
                tiles[lastIndex].setIsCollected(0);
                parent.afterTurnAction();
            }
        });
        parent.timer.setInitialDelay(10);
        parent.timer.start();
    }
    private void checkIndexForward(){
        if (step == 1 && index == 12) index = 0;
        if (step == -1 && index == -1) index = 11;
    }
    private void setGem(int gems){
        parent.setText("Gem: " + gems);
    }
    public void changeTurn(){
        // change turn
        if (turn == 1) {
            turn = 2;
        }
        else {
            turn = 1;
        }
    }
    public int getTurn(){return turn;}
}
