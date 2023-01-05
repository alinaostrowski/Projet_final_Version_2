import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Plateau {
    int largeur;
    int hauteur;
    ArrayList <ArrayList <Tuiles>> plateau;
    SacTuiles ST;


    ArrayList<int[]> emplacementsFull = new ArrayList<int[]>();

    //on commence par un plateau 3x3 (qu'on va propager au fur et à mesure quand on pose des tuiles au bord du plateau)
    public Plateau(int n, String typeTuile) {
        this.hauteur=3;
        this.largeur=3;
        plateau=new ArrayList <ArrayList<Tuiles>> (hauteur);
        for (int i =0;i<hauteur;i++){
            Tuiles[] temp_largeur = new Tuiles[largeur];
            for (int k =0;k<largeur;k++) {
                temp_largeur[k]=null; //au debut tous les cases sont null
            }
            ArrayList<Tuiles> temp = new ArrayList<Tuiles>(Arrays.asList(temp_largeur));

            plateau.add(temp);//on rajoute cette ArrayList a notre plateau
        }
        ST = new SacTuiles(n,typeTuile); // apres on crée un sacTuiles directement avec notre plateau parce que au debut il y aura deja une tuile pioché dans le sac et placée au milieu de notre plateau


        Tuiles t_0 = ST.pickRandom(); //on pioche la première tuile
        plateau.get(1).set(1,t_0);//et la place au milieu du plateau

        emplacementsFull.add(new int[]{1, 1}); //liste à laquelle on rajoute au fur et à mesure les cases remplies du plateau

    }

    //méthode pour savoir si une case du plateau contient une tuile
    public boolean contientTuile(int l, int h) { //on passe les coordonnées de la case a verifier en argument
        if (!Objects.isNull( plateau.get(h))) {
            return (!Objects.isNull( plateau.get(h).get(l))) ; //returns true si la case contient une tuile
        }
        return false;
    }

    public ArrayList<int[]> frontieres(){ // contoures des tuiles sur le plateau pour IA
        ArrayList<int[]> frontieres= new ArrayList<int[]>();
        ArrayList<int[]> to_remove = new ArrayList<int[]>(); //liste pour enlever des emplacements qui sont déjà entouré des tuiles pour ne plus le parcourir la prochaine fois qu'on appelle la méthode
        for (int[] c : emplacementsFull){
            ArrayList<Boolean> supp_c = new ArrayList<Boolean>(); // initialisé pour chaque emplacement, true si contient tuile pour un emplacement
            try {
                if (!contientTuile(c[0] + 1, c[1])) {
                    frontieres.add(new int[]{c[0] + 1, c[1]});
                }
                else {
                    supp_c.add(Boolean.TRUE);
                }
            }
            catch (Exception e) {
            }
            try {
            if (c[0]>0&&!contientTuile(c[0]-1,c[1])){
                frontieres.add(new int[]{c[0]-1,c[1]});
            }
            else{
                supp_c.add(Boolean.TRUE);
            }
        }
            catch (Exception e) {
        }
            try {
            if (!contientTuile(c[0],c[1]+1)){
                frontieres.add(new int[]{c[0],c[1]+1});
            }
            else{
                supp_c.add(Boolean.TRUE);
            }
    }
            catch (Exception e) {
    }
            try {
            if (c[1]>0&&!contientTuile(c[0],c[1]+1)){
                frontieres.add(new int[]{c[0],c[1]-1});
            }
            else{
                supp_c.add(Boolean.TRUE);
            }
}
            catch (Exception e) {
                    }
            if (supp_c.size()==4&&!supp_c.contains(Boolean.FALSE)) { //si tous les cotés de mon emplacement contient deja des tuiles alors je le rajoute a to remove
                to_remove.add(c);
            }
        }
        for (int[] c_r:to_remove){
            emplacementsFull.remove(c_r); // j'enleve les emplacements de mon liste emplacementFull parce qu'on ne veut plus verifier ses frontières la prochaine fois --> comme ca notre IA ne doit pas parcourir des tuiles qu'il a déjà parcouru
        }
        return frontieres; // retourne les frontieres des tuiles sur le plateau qui sont disponibles à poser une tuile
    }

    //on cherche pour tous les voisins d'une case du plateau
    public ArrayList <ArrayList <Integer>> voisins(int l, int h){// on passe en argument les coordonnées de la case de laquelle on veut chercher ses voisins
        ArrayList <ArrayList <Integer>> v = new ArrayList <ArrayList <Integer>>();
        try {
            if (contientTuile(l+1,h)) { //a droite
                ArrayList <Integer> v_temp = new ArrayList <Integer> (Arrays.asList(l+1,h,2));//a chaque fois on ajoute trois informations a notre liste de voisins: largeur et hauteur de la tuile voisins, et la coté de la tuile quon veut poser qui va etre en contact avec cette tuile
                v.add(v_temp);
            }
        }
        catch (Exception e) {
//			System.out.print("Plateau ne contient pas de tuile a largeur "+Integer.toString(l+1)+" hauteur "+Integer.toString(h)+"\n");
        }
        try {
            if (contientTuile(l-1,h)) { //a gauche
                ArrayList <Integer> v_temp = new ArrayList <Integer> (Arrays.asList(l-1,h,4));
                v.add(v_temp);
            }
        }
        catch (Exception e) {
//			System.out.print("Plateau ne contient pas de tuile a largeur "+Integer.toString(l-1)+" hauteur "+Integer.toString(h)+"\n");
        }
        try {
            if (contientTuile(l,h+1)) { //en bas
                ArrayList <Integer> v_temp = new ArrayList <Integer> (Arrays.asList(l,h+1,3));
                v.add(v_temp);
            }
        }
        catch (Exception e) {
//			System.out.print("Plateau ne contient pas de tuile a largeur "+Integer.toString(l)+" hauteur "+Integer.toString(h+1)+"\n");
        }
        try {
            if (contientTuile(l,h-1)) { // en haut
                ArrayList <Integer> v_temp = new ArrayList <Integer> (Arrays.asList(l,h-1,1));
                v.add(v_temp);
            }
        }
        catch (Exception e) {
//			System.out.print("Plateau ne contient pas de tuile a largeur "+Integer.toString(l)+" hauteur "+Integer.toString(h-1)+"\n");
        }

        return v;


    }

    //boolean qui retourne true si on peut placer la tuile t aux coordonnées passer en argument;false sinon
    public int[] possibleDePoser(Tuiles t,int l,int h) { //tuile a poser + endroit sur le plateau
        int[] isposable_int = new int[2];
        ArrayList <Integer> cotePossibles =new ArrayList <Integer> (); //
        if (!contientTuile(l,h)) { //on cheque si la case est libre (null)
            ArrayList <ArrayList <Integer>> v = voisins(l,h); // on cree notre liste de voisins
            int score_temp = 0;
            for (ArrayList <Integer> t_v:v) { //pour chaque voisins dans notre liste
                int[] p = t.correspondance(plateau.get(t_v.get(1)).get(t_v.get(0)),t_v.get(2));//plateau.get(t_v.get(0)).get(t_v.get(1))--> acceder a la tuile voisin dans notre plateau|| t_v.get(2)--> acceder a index 2 de voisins (coté a verifier) //pour rappel: correspondance retourne le int de la coté de notre tuile a verifier
                if (p[0]!=0) {
                    if (!cotePossibles.contains(p[0])) {//si notre liste de cotesPossibles ne contient pas encore le cote que vient d'être verifier on le rajoute
                        //System.out.print(plateau.get(t_v.get(0)).get(t_v.get(1)).toString());
                        cotePossibles.add(p[0]);
                        score_temp+=p[1];
                    }
                    else {
                        return new int[]{0,0};//si aucun cote de notre tuile est possible on rajoute rien
                    }
                }
            }
            boolean isposable = !cotePossibles.isEmpty()&& cotePossibles.size()== v.size() ;
            if (isposable){
                isposable_int[0]=1;
                isposable_int[1]=score_temp;
            }
            return isposable_int;
        }
        return isposable_int;// on retourne
    }



    //méthode pour agrandir notre plateau , on passe en argument les coordonnées de la tuile qu'on pose
    public void propagerPlateau(int l,int h) {
        if (l==largeur-1) {//si l est un element au bord droit de notre plateau
            for (int i=0;i<hauteur;i++) {
                plateau.get(i).add(null);// on ajoute une case a chaque liste
            }
            largeur++;//on augmente largeur
        }
        else if (l==0) { //si l  est un element au bord gauche de notre plateau
            for (int i=0;i<hauteur;i++) {
                plateau.get(i).add(0,null);//on ajoute une case au debut de chaque liste
            }
            largeur++;//on augmente largeur
        }
        if (h==hauteur-1) { //si element et en bas de notre plateau
            Tuiles[] temp_largeur = new Tuiles[largeur];
            for (int k =0;k<largeur;k++) {
                temp_largeur[k]=null;// on ajoute une liste a la fin de notre plateau qui contient des nulls
            }
            ArrayList<Tuiles> temp = new ArrayList<Tuiles>(Arrays.asList(temp_largeur));
            plateau.add(temp);
            hauteur++; //on augmente hauteur
        }
        else if (h==0) { //si element est en haut de notre plateau
            Tuiles[] temp_largeur = new Tuiles[largeur];
            for (int k =0;k<largeur;k++) {
                temp_largeur[k]=null;// on ajoute liste au debut de notre plateau qui contient des nulles
            }
            ArrayList<Tuiles> temp = new ArrayList<Tuiles>(Arrays.asList(temp_largeur));

            plateau.add(0,temp);
            hauteur++; //on augmente hauteur
        }
    }


    //méthodes pour placer la tuile
    public boolean placerTuile(Tuiles t,int l, int h,Joueur j) {
        int [] possibleposable = possibleDePoser(t,l,h);
        if (possibleposable[0]==1) { //on verifie que la tuile passé en argument et possible de poser a l'endroit passé en argument
            plateau.get(h).set(l, t);//on place la tuile

            j.score+=possibleposable[1];
            emplacementsFull.add(new int[]{l,h});

            this.propagerPlateau(l, h); //on aggrandit le plateau (si nécessaire)
            return true; //on retourne true si on a pu placer la tuile
//			System.out.println(Objects.isNull(plateau.get(h).get(l)));
        }
        else {
            if (j instanceof JoueurHumain){
            System.out.println("Impossible de poser la tuile ici");}
            return false;
        }
    }
    //TODO rajouter des chiffres au bord du plateau pour faciliter la pose des tuiles a l'endroit souhaité
    public String ligneBlanc() {
        return "     ";
    }
    public String ligneCroix() {
        return "  x  ";
    }
    public String ligneBarre() {
        return "/   \\";
    }
    public String ligneBarreinv() {
        return "\\   /";
    }
    //le plateau est representé sous forme d'un string
    public String toString() {
        String f="";
        for (int i=0;i<hauteur;i++) {
            for (int ligne =0 ; ligne <5;ligne ++) {
                for(int j=0; j<largeur;j++) {
                    if(contientTuile(j,i)) {
//						System.out.println("hauteur : "+Integer.toString(i)+" largeur : "+Integer.toString(j));
//						System.out.println(Objects.isNull(plateau.get(i).get(j)));
                        f+=plateau.get(i).get(j).toString_ligne(ligne);
                    }
                    else {
                        if (ligne == 0 ) {
                            f+=ligneBarre();
                        }
                        else if (ligne == 4) {
                            f+=ligneBarreinv();
                        }
                        else if (ligne == 1 || ligne == 3) {
                            f+=ligneBlanc();
                        }
                        else {
                            f+=ligneCroix();
                        }
                    }
                }
                f+="\n";
            }
            f+="\n";
        }
        return f;
    }
}
