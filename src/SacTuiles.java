import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SacTuiles {
    int nbTuilesDominos;
    ArrayList <Tuiles> sacTuiles;
    int r = 3;


    //on cree un sac tuiles avec un nbTuiles et en utilisant la méthode creerTuiles qui permet de creer des tuiles randomly
    public SacTuiles(int n, String typTuile) {
        nbTuilesDominos =n;
        ArrayList <Tuiles> sacT = new ArrayList <Tuiles> ();
        for(int j=0; j<n;j++) {
            Tuiles t = creerTuile(typTuile);
            sacT.add( t );

        }
        sacTuiles = sacT;
    }

    //typTuile== soit Dominos soit Carcassonne
    public Tuiles creerTuile(String typTuile) {
        if (Objects.equals(typTuile, "Carcassonne")){
            String[][] tuileC = creerTC();
            Tuiles t= new TuilesCarcassonne(tuileC[0],tuileC[1],tuileC[2],tuileC[3]); //on utilise notre tuile temporaire pour passer les cotes en argument du constructeur tuileDomino
            return t;
            //}
        }
        else if (Objects.equals(typTuile, "Domino")){

            int [][] tuileD= new int[4][]; //tuile temporaire pour afin le passer en constructeur d'une tuile domino
            Random random= new Random();
            for(int i=0; i<4;i++) {
                int [] c = new int[3];//on crée une liste de 3 int pour  chaque cote de la tuile
                for(int k=0; k<3;k++) {
                    c[k]=random.nextInt(1,r); //on choisit au hasard trois chiffres par cote
                }
                tuileD[i]=c;// on ajoute ces listes a la liste de la tuile temporaire
            }
            Tuiles t= new TuilesDomino(tuileD[0],tuileD[1],tuileD[2],tuileD[3]); //on utilise notre tuile temporaire pour passer les cotes en argument du constructeur tuileDomino
            return t;
            //}
        }
        else {
            return null;
        }
    }

    public String[][] creerTC() {
        String[][] tuileC = new String[4][]; //tuile temporaire pour afin le passer en constructeur d'une tuile carcassonne
        String[] list = {"P", "V", "R"};
        String[] list_wo_v = {"P", "R"};
        Random random = new Random();
        boolean est_valide = false;
        while (!est_valide) {
            for (int i = 0; i < 4; i++) {
                String[] c = new String[3];//on crée une liste de 3 int pour  chaque cote de la tuile
                int k = 0;
                while (k < 3) {
                    if (k == 0) {
                        String u = list[random.nextInt(list.length - 1)];
                        c[k] = u;
                        if (u == "V") {
                            c[1] = "V";
                            c[2] = "V";
                            k = 3;
                        }
                    } else if (k == 1) {
                        String u = list_wo_v[random.nextInt(list_wo_v.length)];
                        c[k] = u;
                    } else {
                        c[k] = "P";
                    }
                    k++;
                }
                tuileC[i] = c;// on ajoute ces listes a la liste de la tuile temporaire
            }
            est_valide = (tuileC[0][1] != "R" &&tuileC[1][1] != "R" &&tuileC[2][1] != "R" &&tuileC[3][1] != "R" )||tuileC[0][1] == "R" && tuileC[1][1] == "R" || tuileC[0][1] == "R" && tuileC[2][1] == "R"
                    || tuileC[0][1] == "R" && tuileC[3][1] == "R" || tuileC[1][1] == "R" && tuileC[2][1] == "R"
                    || tuileC[1][1] == "R" && tuileC[3][1] == "R" || tuileC[2][1] == "R" && tuileC[3][1] == "R";

        }
        return tuileC;
    }

    //permet d'afficher toutes les tuiles dans  le sac créé
    public String toString() {
        String f="";
        for(Tuiles tuile:sacTuiles) {
            f+=tuile.toString();
            f+="\n";

        }
        return f;
    }

    //permet de piocher une tuile de notre sac au hasard
    public Tuiles pickRandom() {
        Random random= new Random();
        if (!estVide()) {
            int r = random.nextInt(nbTuilesDominos);// on choisit un numero random entre 0 et nbTuiles
            Tuiles t = sacTuiles.get(r);//on pioche l'objet a l'index de numero random
            enleveTuile(t);//on enleve la tuile du sac
            return t;
        }
        else { //s'il y a plus de tuiles dans le sac on affiche le message :
            System.out.println("Il n y a plus de tuiles dans le sac");
            return null;
        }
    }

    //returns true si sac est vide
    public boolean estVide() {
        return(nbTuilesDominos==0);
    }

    //enleve une tuile du sac (ATTENTION:au hasard)
    public void enleveTuile(Tuiles t) {
        sacTuiles.remove(t);
        nbTuilesDominos--;

    }

	public static void main(String[]args) {
		SacTuiles test=new SacTuiles(30,"Carcassone");
        Tuiles t = test.pickRandom();
		System.out.println(t.toString());


	}
}
