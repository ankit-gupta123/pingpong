import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    int xVelocity;
    int yVelocity;
    int initialSpeed = 5;
    Random random;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.random = new Random();
        int RandomXDirection = this.random.nextInt(2);
        if (RandomXDirection == 0) {
            RandomXDirection--;
        } else {

        }
        this.setXDirection(RandomXDirection);
        int RandomYDirection = this.random.nextInt(2);
        if (RandomYDirection == 0) {
            RandomYDirection--;
        }
        this.setYDirection(RandomYDirection);

    }

    public void setYDirection(int randomYDirection) {
        this.yVelocity = randomYDirection;
    }

    public void setXDirection(int randomXDirection) {
        this.xVelocity = randomXDirection;
    }

    public void move() {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(this.x, this.y, this.width, this.height);

        g.setColor(Color.white);
        g.drawLine(1000 / 2, 0, 1000 / 2, 555);
    }
}