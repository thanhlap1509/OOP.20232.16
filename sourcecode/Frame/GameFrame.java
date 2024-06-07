package sourcecode.Frame;
import sourcecode.Panel.HalfCircleSquare;
import sourcecode.Panel.MyPanel;
import sourcecode.Panel.SquarePanel;
import sourcecode.Player.Player;
import sourcecode.Player.PlayerContainer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import javax.imageio.ImageIO;
public class GameFrame extends JFrame implements MouseListener {
    final int FRAME_WIDTH = 889;
    final int HEADER_SIZE = 115;
    final int SECOND_TO_SLEEP = 400;
    final Color TEXT_COLOR = Color.white;
    final int QUAN_POINT = 5;
    private Player player1;
    private Player player2;
    private JLabel timerLabel;
    private JLabel gemInHand;
    private MyPanel[] tiles;
    private PlayerContainer player2Container;
    private PlayerContainer player1Container;
    private Border compoundBorder1;
    private Border compoundBorder2;
    private int secondCountDown;
    private Timer timer = null;
    private int turn;
    private int gemToSpread = 0;
    private int index = 0;
    private int step = 0;
    private int lastIndex;
    private JPanel boardGameContainer;
    GameFrame() {
        //set initial turn to player 1
        turn = 1;
        createGameBoard();
        createTimer();
        createGemIndicator();
        initializePlayer();
        timerCountDown();
        //frame
        this.setTitle("Game Frame");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH,HEADER_SIZE*2 + (int)(MyPanel.SIZE*1.6) + 5);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(player2Container, BorderLayout.NORTH);
        this.add(boardGameContainer, BorderLayout.CENTER);
        this.add(player1Container, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    private void createGameBoard(){
        //Board game
        boardGameContainer = new JPanel();
        tiles = new MyPanel[12];
        //left tiles
        tiles[11] = new HalfCircleSquare("left",11, 0, 1);
        tiles[11].addMouseListener(this);
        //right tiles
        tiles[5] = new HalfCircleSquare("right",6, 0, 1);
        tiles[5].addMouseListener(this);
        //center tiles
        JPanel centerTiles = new JPanel();
        centerTiles.setLayout(new GridLayout(2, 1));
        //upper row
        JPanel upper = new JPanel();
        upper.setLayout(new GridLayout(1, 5));
        for (int i = 10; i > 5; i--){
            tiles[i] = new SquarePanel("upper", i, 5, 0);
            tiles[i].addMouseListener(this);
            upper.add(tiles[i]);
        }
        //lower row
        JPanel lower = new JPanel();
        lower.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++){
            tiles[i] = new SquarePanel("lower", i, 5, 0);
            tiles[i].addMouseListener(this);
            lower.add(tiles[i]);
        }
        //add upper and lower row in center tiles
        centerTiles.add(upper);
        centerTiles.add(lower);
        //add all container in one board game container
        boardGameContainer.setLayout(new BorderLayout());
        boardGameContainer.add(tiles[11], BorderLayout.WEST);
        boardGameContainer.add(centerTiles, BorderLayout.CENTER);
        boardGameContainer.add(tiles[5], BorderLayout.EAST);
    }
    private void initializePlayer(){
        //getting name,imageicon for two player and initiate point = 0
        String name1= null;
        String name2 = null;
        while (name1 == null|| name1.isEmpty()) name1 = JOptionPane.showInputDialog(this, "Input name for first player", "", JOptionPane.QUESTION_MESSAGE);
        name1 = name1.toUpperCase();
        while (name2 == null || name2.isEmpty()) name2 = JOptionPane.showInputDialog(this, "Input name for second player", "", JOptionPane.QUESTION_MESSAGE);
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
        //create two border, one for apply padding, one to paint with color and combine into compound border to indicate turn
        Border paddingBorder1 = BorderFactory.createEmptyBorder(0, 0, 0, 5);
        Border paddingBorder2 = BorderFactory.createEmptyBorder(0, 0, 0, 5);
        Border indicatorBorder = BorderFactory.createLineBorder(Color.yellow, 2);
        compoundBorder1 = BorderFactory.createCompoundBorder(indicatorBorder, paddingBorder1);
        compoundBorder2 = BorderFactory.createCompoundBorder(indicatorBorder, paddingBorder2);
        //players container
        player2Container = new PlayerContainer(player2);
        player2Container.addComponent(timerLabel);
        player2Container.addPlayerInfo();
        player2Container.addComponent(gemInHand);
        // -----
        player1Container = new PlayerContainer(player1);

        player1Container.addPlayerInfo();
    }
    private void createTimer(){
        //initiate timer label
        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setBorder(new EmptyBorder(5, 5 , 0, 0));
        timerLabel.setForeground(TEXT_COLOR);
        timerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        timerLabel.setVerticalAlignment(SwingConstants.TOP);
        timerLabel.setHorizontalTextPosition(JLabel.LEFT);
        timerLabel.setBounds(0, 0, 150, 50);
    }
    private void createGemIndicator(){
        //set up gem indicator
        gemInHand = new JLabel();
        gemInHand.setFont(new Font("Arial", Font.BOLD, 18));
        gemInHand.setBorder(new EmptyBorder(5, 0, 0, 10));
        gemInHand.setForeground(TEXT_COLOR);
        gemInHand.setHorizontalAlignment(SwingConstants.RIGHT);
        gemInHand.setVerticalAlignment(SwingConstants.TOP);
        gemInHand.setHorizontalTextPosition(JLabel.RIGHT);
        gemInHand.setBounds(0, 0, FRAME_WIDTH - 15, 50);
        setGem(0);
    }
    private void timerCountDown(){
        checkEndGame();
        //create timer, changing header display according to turn
        secondCountDown = 60;
        if (turn == 1){
            player2Container.setPlayerInfoBorder(null);
            player1Container.setPlayerInfoBorder(compoundBorder1);
        }
        else {
            player1Container.setPlayerInfoBorder(null);
            player2Container.setPlayerInfoBorder(compoundBorder2);
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondCountDown >= 0) {
                    String string = String.format("Timer: %02d:%02d", secondCountDown / 60, secondCountDown % 60);
                    timerLabel.setText(string);
                    secondCountDown--;
                } else {
                    timer.stop();
                    changeTurn();
                    timerCountDown();
                }
            }
        });
        timer.setInitialDelay(10);
        timer.start();
    }
    private void setGem(int gems){
        gemInHand.setText("Gem: " + gems);
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
         timer = new Timer(SECOND_TO_SLEEP, new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
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
                     timer.stop();
                     tiles[lastIndex].setIsPointed(0);
                     recursiveSpreadGems(startIndex, direction);
                 }
             }
         });
         timer.setInitialDelay(10);
        timer.start();
    }
    public void recursiveSpreadGems(int startIndex, String direction){
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
            this.setEnabled(true);
        }
    }
    private void addPoint(){
        final int[] iter = {0};
        timer = new Timer(SECOND_TO_SLEEP, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                            updateText1();
                        }
                        else if (turn == 2){
                            player2.setPoint(player2.getPoint() + dan + quan*QUAN_POINT);
                            updateText2();
                        }
                    }
                    else {
                        timer.stop();
                        tiles[lastIndex].setIsCollected(0);
                        afterTurnAction();
                    }
                    index += step;
                    checkIndexForward();
                    iter[0]++;
                }else {
                    timer.stop();
                    tiles[lastIndex].setIsCollected(0);
                    afterTurnAction();
                }
            }
        });
        timer.setInitialDelay(10);
        timer.start();
    }
    public void checkIndexForward(){
        if (step == 1 && index == 12) index = 0;
        if (step == -1 && index == -1) index = 11;
    }
    public void checkEndGame(){
        if (tiles[11].getDan() == 0 && tiles[11].getQuan() == 0 && tiles[5].getQuan() == 0 && tiles[5].getDan() == 0){
            timer.stop();
            this.dispose();
            // get remaining dan in lower belong to player1
            for (int i = 0; i < 5; i++){
                player1.setPoint(player1.getPoint() + tiles[i].getDan());
            }
            //get remaining dan in upper belong to player 2
            for (int i = 6; i < 11; i++){
                player2.setPoint(player2.getPoint() + tiles[i].getDan());
            }
            new EndFrame(player1, player2);
        }
    }
    private void afterTurnAction(){
        changeTurn();
        checkUpperRow();
        checkLowerRow();
        timerCountDown();
    }
    private void updateText1() {
        player1Container.setPlayerInfoText();
    }
    private void updateText2(){
        player2Container.setPlayerInfoText();
    }
    private void checkUpperRow(){
        int full0 = 1;
        for (int i = 6; i < 11; i++){
            if (tiles[i].getDan() != 0) {
                full0 = 0;
                break;
            }
        }
        if (full0 == 1){
            for (int i = 6; i < 11; i++){
                tiles[i].setDan(tiles[i].getDan() + 1);
            }
            player2.setPoint(player2.getPoint() - 5);
            updateText2();
        }
    }
    private void checkLowerRow(){
        int full0 = 1;
        for (int i = 0; i < 5; i++){
            if (tiles[i].getDan() != 0){
                full0 = 0;
                break;
            }
        }
        if (full0 == 1){
            for (int i = 0; i < 5; i++){
                tiles[i].setDan(tiles[i].getDan() + 1);
            }
            player1.setPoint(player1.getPoint() - 5);
            updateText1();
        }
    }
    private void changeTurn(){
        // change turn
        if (turn == 1) {
            updateText1();
            turn = 2;
        }
        else {
            updateText2();
            turn = 1;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //only accept center square
        if (e.getSource() instanceof SquarePanel && ((SquarePanel) e.getSource()).getDan() > 0){
            // enable lower tiles access for player 1 and upper tiles for player 2
            if ((turn == 1 && ((SquarePanel) e.getSource()).getUoL().equals("lower"))
                    || (turn == 2 && ((SquarePanel) e.getSource()).getUoL().equals("upper"))){
                //if player click in left arrow
                if (e.getX() >= 0 && e.getX() <= ((SquarePanel) e.getSource()).getArrowWidth()){
                    //timer cancel when click left arrow
                    timer.stop();
                    int index = ((MyPanel) e.getSource()).getI();
                    //stop user from interact with tile anymore
                    this.setEnabled(false);
                    spreadGems(index, "left");
                }
                //if player click in right arrow
                else if (e.getX() >= ((SquarePanel)e.getSource()).getWidth() - ((SquarePanel) e.getSource()).getArrowWidth()){
                    // timer cancel when click right arrow
                    timer.stop();
                    int index = ((SquarePanel) e.getSource()).getI();
                    //stop user from interact with tile anymore
                    this.setEnabled(false);
                    spreadGems(index, "right");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //paint arrow if mouse enter arrow
        //if player click in left arrow
        if (e.getSource() instanceof SquarePanel && ((MyPanel) e.getSource()).getDan() > 0){
            if (e.getX() >= 0 && e.getX() <= ((SquarePanel) e.getSource()).getArrowWidth()){
                ((SquarePanel) e.getSource()).setPaintLeft(true);
            }
            //if player click in right arrow
            else if (e.getX() >= ((MyPanel)e.getSource()).getWidth() - ((SquarePanel) e.getSource()).getArrowWidth()) {
                ((SquarePanel) e.getSource()).setPaintRight(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof SquarePanel && ((SquarePanel) e.getSource()).getDan() > 0){
            // enable lower tiles access for player 1 and upper tiles for player 2
            if ((turn == 1 && ((SquarePanel) e.getSource()).getUoL().equals("lower"))
                    || (turn == 2 && ((SquarePanel) e.getSource()).getUoL().equals("upper"))){
                ((SquarePanel) e.getSource()).setArrow(true);
                ((JPanel) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof SquarePanel){
            ((SquarePanel) e.getSource()).setArrow(false);
            ((SquarePanel) e.getSource()).setPaintLeft(false);
            ((SquarePanel) e.getSource()).setPaintRight(false);
            ((JPanel) e.getSource()).setCursor(Cursor.getDefaultCursor());
        }
    }
}