import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int id;
    int yVelocity;
    int speed = 10;

    Paddle(int x, int y, int Paddle_Width, int Paddle_Height, int id) {
        super(x, y, Paddle_Width, Paddle_Height);
        this.id = id;

    }

    public void KeyPressed(KeyEvent e) {
        switch (this.id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    this.setYDirection(-this.speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    this.setYDirection(this.speed);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.setYDirection(-this.speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.setYDirection(this.speed);
                }
                break;
        }
    }

    private void setYDirection(int Direction) {
        this.yVelocity = Direction;
    }

    public void KeyReleased(KeyEvent e) {
        switch (this.id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    this.setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    this.setYDirection(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.setYDirection(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.setYDirection(0);
                }
                break;
        }
    }

    public void move() {
        this.y = this.y + this.yVelocity;
    }

    public void draw(Graphics g) {
        if (this.id == 1) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.blue);
        }
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
