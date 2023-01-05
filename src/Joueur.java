
import java.awt.*;
import java.util.LinkedList;

public abstract class Joueur {
    protected String nom;
    protected Color couleur;
    protected int age;
    protected int score;

    int limiteNbPion = 8;



    public Joueur(String n, Color c, int a) {
        this.nom=n;
        this.couleur=c;
        this.age=a;
        this.score=0;
    }

    public void setCouleur(Color c){
        this.couleur=c;
    }

}
