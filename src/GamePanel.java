import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends Panel implements Runnable {
    int Width = 1000;
    int Height = (int) (this.Width * (0.555));
    Dimension screen = new Dimension(this.Width, this.Height);

    int Paddle_Width = 25;
    int Paddle_Height = 100;

    int ball_diameter = 20;

    Image image;

    Graphics graphics;

    Paddle p1, p2;

    Ball ball;

    Score score = new Score(this.Width, this.Height);

    Thread GameThread;

    GamePanel() {
        this.setPreferredSize(this.screen);
        this.GameThread = new Thread(this);
        this.addKeyListener(new AL());

        this.setFocusable(true);

        this.GameThread.start();
        this.newPaddle();
        this.newBall();

    }

    private void newBall() {
        Random random = new Random();
        this.ball = new Ball(this.Width / 2, random.nextInt(this.Height - this.ball_diameter), this.ball_diameter, this.ball_diameter);
    }

    private void newPaddle() {
        this.p1 = new Paddle(0, (this.Height - this.Paddle_Height) / 2, this.Paddle_Width, this.Paddle_Height, 1);
        this.p2 = new Paddle(this.Width - this.Paddle_Width, (this.Height - this.Paddle_Height) / 2, this.Paddle_Width, this.Paddle_Height, 2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.image = this.createImage(this.getWidth(), this.getHeight());
        this.graphics = this.image.getGraphics();
        this.draw(this.graphics);
        g.drawImage(this.image, 0, 0, this);

    }

    private void draw(Graphics g) {
        this.p1.draw(g);
        this.p2.draw(g);
        this.ball.draw(g);
        this.score.draw(g);

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long Now = System.nanoTime();
            delta += (Now - lastTime) / ns;
            lastTime = Now;
            if (delta >= 1) {
                this.repaint();
                this.move();
                this.CheckCollision();
                delta--;
            }
        }
    }

    private void CheckCollision() {
        if (this.ball.y <= 0) {
            this.ball.setYDirection(-this.ball.yVelocity);
        }
        if (this.ball.y >= this.Height - this.ball_diameter) {
            this.ball.setYDirection(-this.ball.yVelocity);
        }
        if (this.ball.intersects(this.p1)) {
            this.ball.xVelocity = -this.ball.xVelocity;
            this.ball.xVelocity++;
            if (this.ball.yVelocity > 0) {
                this.ball.yVelocity++;
            } else {
                this.ball.yVelocity--;
            }
            this.ball.setYDirection(this.ball.yVelocity);
            this.ball.setXDirection(this.ball.xVelocity);
        }

        if (this.ball.intersects(this.p2)) {
            this.ball.xVelocity = -this.ball.xVelocity;
            this.ball.xVelocity--;
            if (this.ball.yVelocity > 0) {
                this.ball.yVelocity++;
            } else {
                this.ball.yVelocity--;
            }
            this.ball.setYDirection(this.ball.yVelocity);
            this.ball.setXDirection(this.ball.xVelocity);

        }

        if (this.p1.y <= 0) {
            this.p1.y = 0;
        }
        if (this.p1.y >= this.Height - this.Paddle_Height) {
            this.p1.y = this.Height - this.Paddle_Height;
        }
        if (this.p2.y <= 0) {
            this.p2.y = 0;
        }
        if (this.p2.y >= this.Height - this.Paddle_Height) {
            this.p2.y = this.Height - this.Paddle_Height;
        }
        if (this.ball.x >= this.Width - this.ball_diameter) {
            this.newBall();
            this.newPaddle();
            this.score.player1++;
        }
        if (this.ball.x <= 0) {
            this.newPaddle();
            this.newBall();
            this.score.player2++;
        }

    }

    private void move() {
        this.p1.move();
        this.p2.move();
        this.ball.move();
    }

    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            GamePanel.this.p1.KeyPressed(e);
            GamePanel.this.p2.KeyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

            GamePanel.this.p1.KeyReleased(e);
            GamePanel.this.p2.KeyReleased(e);
        }
    }

}