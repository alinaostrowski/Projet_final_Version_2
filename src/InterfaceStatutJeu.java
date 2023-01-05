import javax.swing.*;
import java.awt.*;

public class InterfaceStatutJeu extends JPanel {
    int spacing = 5;

    int h_tableau = 50;
    JeuInterface jeu ;
    int w_tableau = 100;

    public InterfaceStatutJeu(JeuInterface jeu){
        this.jeu = jeu;

    }

    public void paint(Graphics g){
        int nbr_total_J = jeu.joueurs.size();
        g.drawRect(spacing, spacing , spacing+nbr_total_J*w_tableau, spacing+2*h_tableau);
        for (int i = 0;i<nbr_total_J;i++){
            g.drawLine(spacing+i*w_tableau,spacing,spacing+i*w_tableau,spacing + 2*h_tableau);
            g.drawString(Integer.toString( jeu.joueurs.get(i).score),spacing+i*w_tableau+w_tableau/2,spacing+3*h_tableau/2);
            g.drawString(jeu.joueurs.get(i).nom,2*spacing+i*w_tableau,spacing+h_tableau/2);

        }
        g.drawLine(spacing,spacing+h_tableau,spacing+nbr_total_J*w_tableau,spacing+h_tableau);
        g.drawLine(spacing,spacing+h_tableau,spacing+nbr_total_J*w_tableau,spacing+h_tableau);

    }

}
