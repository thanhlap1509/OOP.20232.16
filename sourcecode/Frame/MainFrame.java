package sourcecode.Frame;
import javax.swing.*;
import java.awt.*;
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
        this.setTitle("Main Window");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.add(startButton);
        this.add(helpButton);
        this.add(exitButton);
    }
    //help window
    public void showGuideline() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("sourcecode/guideline.txt"));
        String buffer;
        String final_text = "";
        while ((buffer = br.readLine()) != null){
            final_text += buffer + "\n";
        }
        JOptionPane.showMessageDialog(this, final_text, "Game Rule", JOptionPane.INFORMATION_MESSAGE);
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
        else if (e.getSource() == startButton){
            this.dispose();
            GameFrame gameframe = new GameFrame();
        }
    }
}
