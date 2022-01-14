import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class Paddels extends Rectangle{

    int id;
    int yVelocity; // hur snabbt paddelsen rör sig upp och ner
    int speed = 12;



    Paddels(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){ // konstruktor
        super(x,y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;

    }

    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    setYDircetion(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    setYDircetion(speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDircetion(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDircetion(speed);
                    move();
                }
                break;
        }

    }
    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDircetion(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDircetion(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDircetion(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDircetion(0);
                    move();
                }
                break;
        }
    }
    public void setYDircetion(int yDircetion) {
        yVelocity = yDircetion;

    }
    public void move() {
        y = y+ yVelocity;


    }
    public void draw(Graphics g) { // färg på spelare
        if (id == 1) {
            g.setColor(Color.PINK);
        }
        else {
            g.setColor(Color.BLUE);
        }
        g.fillRect(x,y,width,height);


    }
}
