import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Panel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1200; //static för säkerhet, final gör programmet snabbare
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 28;
    static final int PADDLE_HEIGHT = 90;
    Thread thread;
    Image image;
    Graphics graphics;
    Random random;
    Paddels paddels1;
    Paddels paddels2;
    Ball ball;
    Score score;
    private Menu menu; // NY
    public static STATE State = STATE.MENU; //NY





    Panel(){ // konstruktor
            menu = new Menu(); //NY
            this.addMouseListener(new Menu.mouseInput());
            newPaddels();
            newBall();
            score = new Score(GAME_WIDTH, GAME_HEIGHT);
            this.setFocusable(true);
            this.addKeyListener(new AL());
            this.setPreferredSize(SCREEN_SIZE);

            thread = new Thread(this);
            thread.start();

    }

    public void newBall() {  //tillkalla dessa metoder ifall spelet ska börja om
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);

    }
    public void newPaddels() {
        paddels1 = new Paddels(0, (GAME_HEIGHT/ 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddels2 = new Paddels(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2),  PADDLE_WIDTH,PADDLE_HEIGHT,2);



    }
    public void paint(Graphics g) {
        image= createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }
    public void draw(Graphics g) {

        if (State == STATE.GAME){ // NY
            paddels1.draw(g);
            paddels2.draw(g);
            ball.draw(g);
            score.draw(g);


            Toolkit.getDefaultToolkit().sync();
        } else if (State == STATE.MENU) {
            Menu.gameMenu(g);
            score = new Score(GAME_WIDTH, GAME_HEIGHT);


        }


    }
    public void move() { // gör att spelarna inte "hackar"
        paddels1.move();
        paddels2.move();
        ball.move();


    }
    public void checkCollision() {

        //
        if(ball.y<= 0) {
            ball.setYDircetion(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER)
            ball.setYDircetion(-ball.yVelocity);

        // gör att bollen inte åker igenom spelare
        if(ball.intersects(paddels1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //valfri för svårare nivå
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDircetion(ball.xVelocity);
            ball.setYDircetion(ball.yVelocity);
        }
        if(ball.intersects(paddels2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //valfri för svårare nivå
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDircetion(-ball.xVelocity);
            ball.setYDircetion(ball.yVelocity);
        }

            if(paddels1.y <= 0 ) //stoppar att spelarna åker ur skärmen
            paddels1.y = 0;
            if(paddels1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddels1.y = GAME_HEIGHT-PADDLE_HEIGHT;
            if(paddels2.y <= 0 )
                paddels2.y = 0;
                if(paddels2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
                    paddels2.y = GAME_HEIGHT-PADDLE_HEIGHT;

        //ge player 1 poäng och gör nya spelare och boll
        if(ball.x<=0) {
            score.player2++;
            newPaddels();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }
        // ger player 2 poäng och gör en ny boll och nya spelare
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            newPaddels();
            newBall();
            System.out.println("player 1: " + score.player1);
        }
                }


    public void run() { // loop för spelet
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoSecond = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now -lastTime) / nanoSecond;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }

    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            if (State==STATE.GAME) { //NY
                paddels1.keyPressed(e);
                paddels2.keyPressed(e);
            }

        }
        public void keyReleased(KeyEvent e) {
            paddels1.keyReleased(e);
            paddels2.keyReleased(e);
        }
    }
    public enum STATE { //NY
        MENU,
        GAME
    };

}
