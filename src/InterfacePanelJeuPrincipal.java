
import javax.swing.*;
import java.awt.*;

import java.util.Objects;

public class InterfacePanelJeuPrincipal extends JPanel {
   int w_tuile=100;
   int h_tuile=100;
    Plateau p;
    int spacing = 20;


    public InterfacePanelJeuPrincipal(Plateau p) {

        this.p = p;


    }



    public void drawTuileDomino(Graphics g,int x,int y, Tuiles t) {

        int spacing_tuile = 5;
        int w = w_tuile;
        int h = h_tuile;
        g.drawRect(x, y, w, h);

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                int place_x = x + (w / 5) * j + w / 20;
                int place_y = y + (h / 5) * j + h / 20;
                if (i == 0) {//haut
                    g.drawString( Integer.toString(t.tuileDomino[0][j-1]), place_x, y + 3*spacing_tuile);
                }
                if (i == 2) {//bas
                    g.drawString(Integer.toString(t.tuileDomino[2][j-1]), place_x, y - spacing_tuile + h);
                }
                if (i == 3) {//gauche
                    g.drawString(Integer.toString(t.tuileDomino[3][j-1]),x + spacing_tuile, place_y +spacing_tuile );
                } else {//droite i=1
                    g.drawString(Integer.toString(t.tuileDomino[1][j-1]),x - 3*spacing_tuile + w, place_y +spacing_tuile);
                }
            }


        }
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
    public void drawTuileCarcassonne(Graphics g,int x,int y, Tuiles t) {
        int spacing_tuile = 5;
        int w = w_tuile;
        int h = h_tuile;
        g.drawRect(x, y, w, h);

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                int place_x = x + (w / 5) * j + w / 20;
                int place_y = y + (h / 5) * j + h / 20;
                if (i == 0) {//haut
                    drawElmt(g,t.tuileCarcassonne[0][j - 1].toString(),place_x-w/20,y );
                    g.drawString(t.tuileCarcassonne[0][j - 1].toString(), place_x, y + 3 * spacing_tuile);
                }
                if (i == 2) {//bas
                    drawElmt(g,t.tuileCarcassonne[2][j - 1].toString(),place_x-w/20,y - 4*spacing_tuile + h);
                    g.drawString(t.tuileCarcassonne[2][j - 1].toString(), place_x, y - spacing_tuile + h);
                }
                if (i == 3) {//gauche
                    drawElmt(g,t.tuileCarcassonne[3][j - 1].toString(),x,place_y -h/20);
                    g.drawString(t.tuileCarcassonne[3][j - 1].toString(), x + spacing_tuile, place_y + spacing_tuile);
                } else {//droite i=1
                    drawElmt(g,t.tuileCarcassonne[1][j - 1].toString(),x - 4 * spacing_tuile + w,place_y -h/20);
                    g.drawString(t.tuileCarcassonne[1][j - 1].toString(), x - 3 * spacing_tuile + w, place_y + spacing_tuile);
                }
            }


        }
    }

    public void paint(Graphics g){
        g.drawRect(spacing, spacing , spacing+p.largeur*w_tuile, spacing+p.hauteur*h_tuile);
        // Create a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the stroke of the copy, not the original
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        for (int i =0;i<p.hauteur;i++){
            for (int k = 0;k<p.largeur;k++){if(p.contientTuile(k,i)) {
                if (p.plateau.get(i).get(k) instanceof TuilesDomino) {
                    drawTuileDomino(g, spacing + (k) * w_tuile, spacing + (i) * h_tuile, p.plateau.get(i).get(k));
                }
                else {
                    TuilesCarcassonne t_temp = (TuilesCarcassonne) p.plateau.get(i).get(k);

                    drawTuileCarcassonne(g, spacing + (k) * w_tuile, spacing + (i) * h_tuile, p.plateau.get(i).get(k));
                    if (!Objects.equals(t_temp.pion,null) ){
                        g.drawOval(spacing + (k) * w_tuile+w_tuile/2,spacing + (i) * h_tuile+h_tuile/2,10,10);
                        g.setColor(t_temp.pion.couleur);
                        g.fillOval(spacing + (k) * w_tuile+w_tuile/2,spacing + (i) * h_tuile+h_tuile/2,10,10);
                        g.setColor(Color.BLACK);
                    }
                }
            }
            }
        }
        g.drawString(Integer.toString(0), spacing-10, h_tuile/2+spacing-5);
        g.drawString(Integer.toString(0), w_tuile/2+spacing-5, spacing-5);

        for (int k = 1; k<p.largeur;k++){
            g.drawString(Integer.toString(k), (k)*w_tuile+w_tuile/2+spacing-5, spacing-5);
            g2d.drawLine(spacing + (k) * w_tuile, spacing, spacing + (k) * w_tuile, 2 * spacing + p.hauteur * h_tuile);
        }
        for (int k = 1; k<p.hauteur;k++){
            g.drawString(Integer.toString(k), spacing-10, (k)*h_tuile+h_tuile/2+spacing-5);
            g2d.drawLine(spacing, spacing + k * h_tuile, 2 * spacing + p.largeur * w_tuile, spacing + k * h_tuile);

        }

        g2d.dispose();

    }



}