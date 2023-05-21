import java.awt.*;

public class Score extends Rectangle {
    int width;
    int height;
    int player1;
    int player2;

    Score(int width, int height) {
        this.height = height;
        this.width = width;

    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawString(String.valueOf(this.player1), this.width / 2 - 60, 100);
        g.drawString(String.valueOf(this.player2), this.width / 2 + 20, 100);
    }

}
