import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu {

    public static Rectangle playButton = new Rectangle(Panel.GAME_WIDTH / 2 - 60, 200, 100, 50);
    public static Rectangle quitButton = new Rectangle(Panel.GAME_WIDTH / 2 - 60, 300, 100, 50);


    public static void gameMenu(Graphics g) {
        Graphics2D g2d =(Graphics2D) g;

        Font fnt0 = new Font("Consoles", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("PING PONG", (Panel.GAME_WIDTH/2-140), 150);

        Font fnt1 = new Font("Consoles", Font.BOLD, 30);
        g.setFont(fnt1);

        g.drawString("Play", playButton.x +19 ,playButton.y + 35);
        g2d.draw(playButton);

        g.drawString("Quit", quitButton.x +19 ,quitButton.y + 35);
        g2d.draw(quitButton);

    }
    public static class mouseInput implements MouseListener{


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {



            /*public static Rectangle playButton = new Rectangle(Panel.GAME_WIDTH / 2 - 60, 200, 100, 50);
            public static Rectangle helpButton = new Rectangle(Panel.GAME_WIDTH / 2 - 60, 300, 100, 50);
            public static Rectangle quitButton = new Rectangle(Panel.GAME_WIDTH / 2 - 60, 400, 100, 50);
           */
            int mx = e.getX();
            int my = e.getY();

            if(mx >= Panel.GAME_WIDTH / 2 - 60 && mx <= Panel.GAME_WIDTH / 2 - 60 + 200){
                if (my >= 50 && my <= 250 ){
                    //Playbutton
                    Panel.State = Panel.STATE.GAME;
                }
            }
            if(mx >= Panel.GAME_WIDTH / 2 - 60 && mx <= Panel.GAME_WIDTH / 2 - 60 + 200){
                if (my >= 300 && my <= 320 ){
                    //QUIT Button
                    System.exit(1);

                }
            }


        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
