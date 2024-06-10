package sourcecode.Frame;
import sourcecode.GamePieces.GameBoard;
import sourcecode.Panel.HalfCirclePanel;
import sourcecode.Panel.MyPanel;
import sourcecode.Panel.SquarePanel;
import sourcecode.Player.PlayerContainer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class GameFrame extends JFrame implements MouseListener {
    final int FRAME_WIDTH = 889;
    final int HEADER_SIZE = 115;
    final Color TEXT_COLOR = Color.white;
    private JLabel timerLabel;
    private JLabel gemInHand;
    private MyPanel[] tiles;
    private GameBoard gameBoard;
    private PlayerContainer player2Container;
    private PlayerContainer player1Container;
    private Border compoundBorder1;
    private Border compoundBorder2;
    private int secondCountDown;
    public Timer timer = null;
    private JPanel boardGameContainer;
    GameFrame() {
        //create header component
        createTimer();
        createGemIndicator();
        //create center component
        createGameBoard();
        createGameBoardContainer();

        initializePlayerContainer();
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
    public void createGameBoard(){
        gameBoard = new GameBoard(this);
    }
    public void createGameBoardContainer(){
        //Board game
        boardGameContainer = new JPanel();
        tiles = new MyPanel[12];
        //left tiles
        tiles[11] = new HalfCirclePanel("left",gameBoard.tiles[11]);
        tiles[11].addMouseListener(this);
        //right tiles
        tiles[5] = new HalfCirclePanel("right",gameBoard.tiles[5]);
        tiles[5].addMouseListener(this);
        //center tiles
        JPanel centerTiles = new JPanel();
        centerTiles.setLayout(new GridLayout(2, 1));
        JPanel upper = new JPanel();
        upper.setLayout(new GridLayout(1, 5));
        //upper row
        for (int i = 10; i > 5; i--){
            tiles[i] = new SquarePanel("upper", gameBoard.tiles[i]);
            tiles[i].addMouseListener(this);
            upper.add(tiles[i]);
        }
        JPanel lower = new JPanel();
        lower.setLayout(new GridLayout(1, 5));
        //lower row
        for (int i = 0; i < 5; i++){
            tiles[i] = new SquarePanel("lower", gameBoard.tiles[i]);
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
    public void initializePlayerContainer(){
        //create two border, one for apply padding, one to paint with color and combine into compound border to indicate turn
        Border paddingBorder1 = BorderFactory.createEmptyBorder(0, 0, 0, 5);
        Border paddingBorder2 = BorderFactory.createEmptyBorder(0, 0, 0, 5);
        Border indicatorBorder = BorderFactory.createLineBorder(Color.yellow, 2);
        compoundBorder1 = BorderFactory.createCompoundBorder(indicatorBorder, paddingBorder1);
        compoundBorder2 = BorderFactory.createCompoundBorder(indicatorBorder, paddingBorder2);
        //players container
        player2Container = new PlayerContainer(gameBoard.player2);
        player2Container.addComponent(timerLabel);
        player2Container.addPlayerInfo();
        player2Container.addComponent(gemInHand);
        // -----
        player1Container = new PlayerContainer(gameBoard.player1);

        player1Container.addPlayerInfo();
    }
    public void createTimer(){
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
    public void createGemIndicator(){
        //set up gem indicator
        gemInHand = new JLabel();
        gemInHand.setFont(new Font("Arial", Font.BOLD, 18));
        gemInHand.setBorder(new EmptyBorder(5, 0, 0, 10));
        gemInHand.setForeground(TEXT_COLOR);
        gemInHand.setHorizontalAlignment(SwingConstants.RIGHT);
        gemInHand.setVerticalAlignment(SwingConstants.TOP);
        gemInHand.setHorizontalTextPosition(JLabel.RIGHT);
        gemInHand.setBounds(0, 0, FRAME_WIDTH - 15, 50);
        gemInHand.setText("Gem: 0");
    }
    public void timerCountDown(){
        updateText1();
        updateText2();
        checkEndGame();
        //create timer, changing header display according to turn
        secondCountDown = 60;
        if (gameBoard.getTurn() == 1){
            player2Container.setPlayerInfoBorder(null);
            player1Container.setPlayerInfoBorder(compoundBorder1);
        }
        else {
            player1Container.setPlayerInfoBorder(null);
            player2Container.setPlayerInfoBorder(compoundBorder2);
        }
        timer = new Timer(1000, e -> {
            if (secondCountDown >= 0) {
                String string = String.format("Timer: %02d:%02d", secondCountDown / 60, secondCountDown % 60);
                timerLabel.setText(string);
                secondCountDown--;
            } else {
                timer.stop();
                gameBoard.changeTurn();
                timerCountDown();
            }
        });
        timer.setInitialDelay(10);
        timer.start();
    }
    public void checkEndGame(){
        if (tiles[11].getDan() == 0 && tiles[11].getQuan() == 0 && tiles[5].getQuan() == 0 && tiles[5].getDan() == 0){
            timer.stop();
            this.dispose();
            // get remaining dan in lower belong to player1
            for (int i = 0; i < 5; i++){
                gameBoard.player1.setPoint(gameBoard.player1.getPoint() + tiles[i].getDan());
            }
            //get remaining dan in upper belong to player 2
            for (int i = 6; i < 11; i++){
                gameBoard.player2.setPoint(gameBoard.player2.getPoint() + tiles[i].getDan());
            }
            new EndFrame(gameBoard.player1, gameBoard.player2);
        }
    }
    public void afterTurnAction() {
        gameBoard.changeTurn();
        checkUpperRow();
        checkLowerRow();
        timerCountDown();
    }
    public void updateText1() {
        player1Container.setPlayerInfoText();
    }
    public void updateText2(){
        player2Container.setPlayerInfoText();
    }
    public void checkUpperRow(){
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
            gameBoard.player2.setPoint(gameBoard.player2.getPoint() - 5);
            updateText2();
        }
    }
    public void checkLowerRow(){
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
            gameBoard.player1.setPoint(gameBoard.player1.getPoint() - 5);
            updateText1();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //only accept center square
        if (e.getSource() instanceof SquarePanel && ((SquarePanel) e.getSource()).getDan() > 0){
            // enable lower tiles access for player 1 and upper tiles for player 2
            if ((gameBoard.getTurn() == 1 && ((SquarePanel) e.getSource()).getUoL().equals("lower"))
                    || (gameBoard.getTurn() == 2 && ((SquarePanel) e.getSource()).getUoL().equals("upper"))){
                //if player click in left arrow
                if (e.getX() >= 0 && e.getX() <= ((SquarePanel) e.getSource()).getArrowWidth()){
                    //timer cancel when click left arrow
                    timer.stop();
                    int index = ((MyPanel) e.getSource()).getI();
                    //stop user from interact with tile anymore
                    this.setEnabled(false);
                    gameBoard.spreadGems(index, "left");
                }
                //if player click in right arrow
                else if (e.getX() >= ((SquarePanel)e.getSource()).getWidth() - ((SquarePanel) e.getSource()).getArrowWidth()){
                    // timer cancel when click right arrow
                    timer.stop();
                    int index = ((SquarePanel) e.getSource()).getI();
                    //stop user from interact with tile anymore
                    this.setEnabled(false);
                    gameBoard.spreadGems(index, "right");
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
            if ((gameBoard.getTurn() == 1 && ((SquarePanel) e.getSource()).getUoL().equals("lower"))
                    || (gameBoard.getTurn() == 2 && ((SquarePanel) e.getSource()).getUoL().equals("upper"))){
                ((SquarePanel) e.getSource()).setArrow(true);
                ((SquarePanel) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

    public void setText(String s) {
        gemInHand.setText(s);
    }
}