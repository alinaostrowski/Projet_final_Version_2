import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class JoueurIA extends Joueur{

    public JoueurIA(String n, Color c, int a) {
        super(n, c, a);
    }

    public boolean jouerTour(Tuiles t, Plateau p){

        ArrayList<int[]> c_possible = p.frontieres();
        Random random= new Random();
        for (int[] c_f:c_possible) {
            for (int k = 0; k < 4; k++) {
                boolean posable_IA = p.placerTuile(t,c_f[0],c_f[1],this);
                if (posable_IA){
                    if (t instanceof TuilesCarcassonne && this.limiteNbPion>0){
                        int r_pion = random.nextInt(3);
                        if (r_pion==0){
                            ((TuilesCarcassonne) t).pion = new Pion(this);
                            limiteNbPion--;
                        }
                    }
                    return true;
                }
                else{
                    t.tournerTime();
                }
            }
        }
        return false;
    }
}
