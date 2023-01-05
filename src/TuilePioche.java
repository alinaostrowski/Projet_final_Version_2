import javax.swing.*;
import java.awt.*;

public class TuilePioche extends JPanel{
    Tuiles t;
    int w_tuile=200;
    int h_tuile=200;

    int x;
    int y;
    int spacing;
    public TuilePioche(Tuiles t,int x,int y){
        this.t=t;
        this.x = x;
        this.y = y;
        this.spacing=5;

    }
    public void setT(Tuiles t_new){
        this.t= t_new;
    }

    public void drawElmt(Graphics g, String e,int x,int y){

        if (e == "R") {

            g.setColor(Color.GRAY);
            g.fillRect(x,y,w_tuile/5,h_tuile/5);
            g.setColor(Color.BLACK);
        }
        else if (e == "P") {

            g.setColor(Color.GREEN);
            g.fillRect(x,y,w_tuile/5,h_tuile/5);
            g.setColor(Color.BLACK);
        }
        else if (e == "V") {

            g.setColor(Color.orange);
            g.fillRect(x,y,w_tuile/5,h_tuile/5);
            g.setColor(Color.BLACK);
        }
        g.drawRect(x,y,w_tuile/5,h_tuile/5);
    }

    public void paint( Graphics g){

        int w = w_tuile;
        int h = h_tuile;
        g.drawRect(x, y, w, h);
        //g.setColor(Color.PINK);
        //g.fillRect(x, y, w, h);
        if (t instanceof TuilesDomino) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    int place_x = x + (w / 5) * j + w / 20;
                    int place_y = y + (h / 5) * j + h / 20;
                    if (i == 0) {//haut
                        g.drawString(Integer.toString(t.tuileDomino[0][j - 1]), place_x, y + 3 * spacing);
                    }
                    if (i == 2) {//bas
                        g.drawString(Integer.toString(t.tuileDomino[2][j - 1]), place_x, y - spacing + h);
                    }
                    if (i == 3) {//gauche
                        g.drawString(Integer.toString(t.tuileDomino[3][j - 1]), x + spacing, place_y + spacing);
                    } else {//droite i=1
                        g.drawString(Integer.toString(t.tuileDomino[1][j - 1]), x - 3 * spacing + w, place_y + spacing);
                    }
                }


            }
        }
        else {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    int place_x = x + (w / 5) * j + w / 20;
                    int place_y = y + (h / 5) * j + h / 20;
                    if (i == 0) {//haut
                        drawElmt(g,t.tuileCarcassonne[0][j - 1].toString(),place_x-w/20,y );
                        g.drawString(t.tuileCarcassonne[0][j - 1].toString(), place_x, y + 3 * spacing);
                    }
                    if (i == 2) {//bas
                        drawElmt(g,t.tuileCarcassonne[2][j - 1].toString(),place_x-w/20,y - 8*spacing + h);
                        g.drawString(t.tuileCarcassonne[2][j - 1].toString(), place_x, y - spacing + h);
                    }
                    if (i == 3) {//gauche
                        drawElmt(g,t.tuileCarcassonne[3][j - 1].toString(),x,place_y -h/20);
                        g.drawString(t.tuileCarcassonne[3][j - 1].toString(), x + spacing, place_y + spacing);
                    } else {//droite i=1
                        drawElmt(g,t.tuileCarcassonne[1][j - 1].toString(),x - 8 * spacing + w,place_y -h/20);
                        g.drawString(t.tuileCarcassonne[1][j - 1].toString(), x - 3 * spacing + w, place_y + spacing);
                    }
                }


            }
        }

    }

}
