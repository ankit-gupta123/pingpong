import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GameFrame() {
        this.setTitle("Pong Game");
        // setLayout(null);
        this.getContentPane().setBackground(Color.black);
        GamePanel panel = new GamePanel();
        this.add(panel);
        // setSize(1000,555);
        // setFocusable(true);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        GameFrame g = new GameFrame();
    }
}