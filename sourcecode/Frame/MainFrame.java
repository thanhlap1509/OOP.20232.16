package sourcecode.Frame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener{
    JButton startButton;
    JButton helpButton;
    JButton exitButton;
    public MainFrame(){
        JLabel title = new JLabel("O An Quan");
        title.setForeground(new Color(57, 255, 20));
        title.setFont(new Font("MV Boli", Font.PLAIN, 40));
        title.setBounds(150, 50, 250,  50);

        startButton = new JButton("Start");
        startButton.setBounds(200, 200, 100, 30);
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        helpButton = new JButton("Help");
        helpButton.setBounds(200, 250, 100, 30);
        helpButton.setFocusable(false);
        helpButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setBounds(200, 300, 100, 30);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        this.setTitle("Game Window");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.add(title);
        this.add(startButton);
        this.add(helpButton);
        this.add(exitButton);
    }
    public void showGuideline() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("sourcecode/guideline.txt"));
        String buffer;
        String final_text = "";
        while ((buffer = br.readLine()) != null){
            final_text += buffer + "\n";
        }
        JOptionPane.showMessageDialog(this, final_text, "Game Rule", JOptionPane.PLAIN_MESSAGE);
        br.close();
        return;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton){
            int result = JOptionPane.showConfirmDialog(this,"Do you really want to exit", "Exit confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                this.dispose();
            }
        }
        else if (e.getSource() == helpButton){
            try {
                this.showGuideline();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
