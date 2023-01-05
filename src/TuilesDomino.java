import java.util.Objects;

public class TuilesDomino extends Tuiles {

    protected int[][] tuile;


    // creer une tuile: avec une liste de trois int pour chaque coté de la tuile et on met ces
    // listes dans une autre liste
    public TuilesDomino(int[]  c1, int[] c2, int[] c3, int[] c4 ) {


        this.tuile= new int[][] {c1,c2,c3,c4};
        super.tuileDomino =new int[][] {c1,c2,c3,c4};
    }

    // afficher une tuile
    public String toString() {
        String f=" ";

        for(int j=0; j<3;j++) {
            f+=tuile[0][j];
        }
        f+=" \n";

        for(int j=0; j<3;j++) {
            f+=tuile[3][j];
            f+="   ";
            f+=tuile[1][j];
            f+="\n";

        }
        f+=" ";
        for(int j=0; j<3;j++) {
            f+=tuile[2][j];
        }
        f+=" ";
        return f;
    }
    // pour afficher ligne de tuile par ligne de tuile (utiliser dans plateau.toString())
    public String toString_ligne(int k) {
        String f ="";
        if (k == 0 ) {
            f+=" ";
            for(int j=0; j<3;j++) {
                f+=tuile[0][j];
            }
            f+=" ";

        }
        else if (k == 4 ) {
            f+=" ";
            for(int j=0; j<3;j++) {
                f+=tuile[2][j];
            }
            f+=" ";

        }
        else {


            f+=tuile[3][k-1];
            f+="   ";
            f+=tuile[1][k-1];

        }

        return f;
    }

    //methode pour checker si deux liste de int sont identique ; compare les trois chiffres d'un coté avec les trois chiffres d'un autre côté
    public boolean sontEgaux(int[] a, int[]b)  {return Objects.equals(a[0],b[0])&&Objects.equals(a[1],b[1])&&Objects.equals(a[2],b[2]);}

    //méthode pour sommer les chiffres d'un côté (on l'utilise pour calculer le score
    public int somme(int[] a)  {return a[0]+a[1]+a[2];}
    //methode pour comparer une tuile au coté d'une autre tuile et retourne int qui dit pour quel côté la tuile correspond
    //@Override
    public int[] correspondance(Tuiles t,int cote) { //t= tuile que je cherche à correspondre  cote= cote de ma tuile que je veux verifier
        int [] cote_score = new int[2]; //liste avec cote de l'autre tuile qui correspond À ma tuile et score de ce coté
        if (cote==1) {
            if (sontEgaux(tuile[cote-1],((Tuiles) t).tuileDomino[2])){
                cote_score[0] = 3;
                cote_score[1] = somme(tuile[cote-1]);
            }
        }
        else if (cote==3){
            if (sontEgaux(tuile[cote-1],((Tuiles) t).tuileDomino[0])){
                cote_score[0] = 1;
                cote_score[1] = somme(tuile[cote-1]);
            }
        }
        else if (cote==2){
            if (sontEgaux(tuile[cote-1],((Tuiles) t).tuileDomino[3])){
                cote_score[0] = 4;
                cote_score[1] = somme(tuile[cote-1]);
            }
        }
        else if (cote==4){
            if (sontEgaux(tuile[cote-1],((Tuiles) t).tuileDomino[1])){
                cote_score[0] = 2;
                cote_score[1] = somme(tuile[cote-1]);
            }
        }
        return cote_score; //la liste est [0,0]
    }


    //méthode pour tourner une tuile dans le sens d'un montre
    public void tournerTime() {
        // on crée des cotes temporaires pour storer les listes pour chaque côté
        int[] temp4 = tuile[0] ;
        int[] temp1 = tuile[1] ;
        int[] temp2 = tuile[2];
        int[] temp3 = tuile[3] ;

        int temp3temp0 = temp3[0]; // on gere les cotes qui doivent changer de l'ordre quand on tourne
        temp3[0]=temp3[2];
        temp3[2]=temp3temp0;
        int temp1temp0 = temp1[0];
        temp1[0]=temp1[2];
        temp1[2]=temp1temp0;


        //on change la tuile avec ses nouveaux cotes
        this.tuile= new int[][] {temp3,temp4,temp1,temp2};
        super.tuileDomino= this.tuile;

    }


}

