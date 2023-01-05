import java.util.ArrayList;

public class JeuInterface {

    ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    Plateau pJeu;
    int nbJoueursIA;
    int nbJoueursH;
    JeuInterface jeu;
    String typeJeu;

    void setTypeJoueur(String type, String nom, int age) {
        Joueur j;
        if (type == "Humain") {
            j = new JoueurHumain(nom, null, age);
        } else {
            j = new JoueurIA("JoueurIA", null, 1);
        }
        joueurs.add(j);
    }

    void setJeu(String t) {
        typeJeu=t;
        if (t == "Dominos") {
            pJeu = new Plateau(30, "Domino");
        } else if (t == "Carcassonne") {
            pJeu = new Plateau(72, "Carcassonne");
        }
    }

    boolean poserPion(TuilesCarcassonne t, Joueur j) {
        if (j.limiteNbPion > 0) {
            t.pion = new Pion(j);
            j.limiteNbPion--;
            return t.pion.pose;
        }
        return false;
    }

    public Joueur gagnant() {
        int m = 0;
        Joueur gagnant = new JoueurHumain("default", null, 0);
        for (Joueur j_f : joueurs) {
            if (j_f.score >= m) {
                m = j_f.score;
                gagnant = j_f;
            }
        }
        return gagnant;
    }




}
