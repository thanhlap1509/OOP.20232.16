package sourcecode.Frame;
import sourcecode.BoardGame.Board;
import sourcecode.Player.Player;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    final int length = 12;
    final int width = 220;
    final int height = 180;
    final int padding = 140 + 300;
    final int top_padding = 190;
    Board board;
    Player player1;
    Player player2;
    public GameFrame(){
        board = new Board();
        String name1 = JOptionPane.showInputDialog(null, "Enter name of first player: ");
        String name2 = JOptionPane.showInputDialog(null, "Enter name of second player: ");
        ImageIcon semi_left = new ImageIcon("sourcecode/Frame/semi_left.png");
        ImageIcon semi_right = new ImageIcon("sourcecode/Frame/semi_right.png");
        ImageIcon square = new ImageIcon("sourcecode/Frame/square.png");

        JLabel[] labels = new JLabel[length];
        labels[0] = new JLabel();
        labels[0].setIcon(semi_left);
        labels[0].setBounds(180, 200, 300, 350);
        for (int i = 1; i < 6; i++){
            labels[i] = new JLabel();
            labels[i].setIcon(square);
            labels[i].setBounds(padding + (i - 1)*width, top_padding, width, height);
            labels[length - i] = new JLabel();
            labels[length - i].setIcon(square);
            labels[length - i].setBounds(padding + (i - 1)*width, top_padding + height, width, height);
        }
        labels[6] = new JLabel();
        labels[6].setIcon(semi_right);
        labels[6].setBounds(padding + 5*width, 200, 300, 350);
        this.setTitle("Game Window");
        this.setBackground(new Color(255, 255, 255));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(null);
        this.setVisible(true);
        for (int i = 0; i < length;i++){
            this.add(labels[i]);
        }
    }

}
