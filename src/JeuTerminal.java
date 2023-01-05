import java.util.*;

public class JeuTerminal {
    int nbJoueurs;
    ArrayList<Joueur> joueurs= new ArrayList <Joueur>();
    String jeu;
    Plateau p_Jeu;


    Comparator<Joueur> comparator = new Comparator<Joueur>(){
        @Override
        public int compare(final Joueur o1, final Joueur o2){
            return o1.age-o2.age;
        }
    };

    void setNbJoueurs(Scanner sc) {
        String message_input = "Combien de joueurs ?";
        System.out.println(message_input);
        String i_s= sc.next();
        int i =Integer.parseInt(i_s);
        nbJoueurs = i;
        for (int j=0; j<i;j++) {
            String message_IA = "Joueur humain ou IA ? H/I";
            System.out.println(message_IA);
            String decision_IA= sc.next();
            System.out.println( "Nom:");
            String nom=sc.next();
            System.out.println("Age:");
            String age=sc.next();
            int ageS =Integer.parseInt(age);
            if (Objects.equals(decision_IA,"I")) {
                Joueur x_IA = new JoueurIA(nom, null, ageS);
                joueurs.add(x_IA);
            }
            else {
                Joueur x_H = new JoueurHumain(nom, null, ageS);
                joueurs.add(x_H);
            }

        }
        Collections.sort(joueurs, comparator );;
    }
    void setJeu(Scanner sc){
        String jeu_input = "Quel Jeu ? Carcassonne ou Domino";
        System.out.println(jeu_input);
        jeu= sc.next();

        p_Jeu= new Plateau(30,jeu);
    }
    // méthode qui gÈre decisions du joueur
    public int decisionJoueur(Tuiles t_j,Scanner sc) {

        String message_input = "0: placer la tuile,  1: tourner la tuile, 2: passer tour, 3: montrer tuile, 4: montrer plateau"; //5: abandonner jeu
        System.out.println(message_input);
        String i_s= sc.next();
        int i =Integer.parseInt(i_s);
        while(i!=0&&i!=2) {
            if(i==1) {
                //System.out.println(t_d.toString());
                t_j.tournerTime();
                System.out.println(t_j.toString());
            }
            else if (i==3) {
                System.out.println(t_j.toString());
            }
            else if (i==4) {
                System.out.println(p_Jeu.toString());
            }
            System.out.println(message_input);
            i=sc.nextInt();
        }
        return i;
    }
    boolean poserPion(TuilesCarcassonne t,Joueur j,Scanner sc){
        System.out.println("Il reste "+j.limiteNbPion+" pions au joueur "+j.nom);
        System.out.println("Souhaitez vous poser un pions ? Oui/Non");
        String decision = sc.next();
        if (Objects.equals(decision,"Oui")){
        if (j.limiteNbPion>0){
            t.pion = new Pion(j);
            j.limiteNbPion--;
            System.out.println("Pion pose");
            return t.pion.pose;
        }
        System.out.println("Le joueur n a plus de Pions");
        }
        return false;
    }
    //TODO créer des try et catch pour les entrées pour que le programme ne crash pas en erreur mais demande le joueur a entrer a nouveau (p.ex. si mauvais format)
    public void gererJeuTerminal() {
        Scanner sc= new Scanner(System.in);
        setJeu(sc);
        setNbJoueurs(sc);
        while (p_Jeu.ST.nbTuilesDominos>0) {
            for (int n_j=0;n_j<nbJoueurs;n_j++) {
                System.out.println("Joueur "+joueurs.get(n_j).nom);
                System.out.println(p_Jeu.toString());//Afficher plateau au joueur pour voir premiere tuile

                Tuiles t_j=p_Jeu.ST.pickRandom();

                if(t_j==null) {
                    System.out.println("Le jeu est fini!");
                    int m = 0;
                    Joueur gagnant = new JoueurHumain("default",null,0) ;
                    for (Joueur j_f : joueurs){
                        if(j_f.score>=m){
                            m=j_f.score;
                            gagnant = j_f;
                        }
                    }
                    System.out.println("Le joueur gagnant est :"+gagnant.nom);
                    break;
                }
                else {
                    System.out.println("Il reste " + p_Jeu.ST.nbTuilesDominos + " Tuiles dans le sac");
                    System.out.println("Tuile piochée: \n" + t_j.toString());//montrer tuile piochée au joueur

                    if (joueurs.get(n_j) instanceof JoueurIA) {
                        ((JoueurIA) joueurs.get(n_j)).jouerTour(t_j,p_Jeu);
                        System.out.println("Le score de " + joueurs.get(n_j).nom + " est de " + joueurs.get(n_j).score);
                    }
                    else {
                        int i = decisionJoueur(t_j, sc);
                        while (i == 0) {
                            System.out.println("Tapez les coordonnees sous forme: l,h");
                            String coordonnees = sc.next();
                            String[] coordonneesSplit = coordonnees.split(",");
                            boolean placerT = p_Jeu.placerTuile(t_j, Integer.parseInt(coordonneesSplit[0]), Integer.parseInt(coordonneesSplit[1]), joueurs.get(n_j));

                            if (!placerT) {
                                i = decisionJoueur(t_j, sc);
                            } else {
                                System.out.println("Le score de " + joueurs.get(n_j).nom + " est de " + joueurs.get(n_j).score);
                                i = 2;
                                if (Objects.equals(jeu, "Carcassonne")) {
                                    poserPion((TuilesCarcassonne) t_j, joueurs.get(n_j), sc);
                                }
                            }
                        }
                        if (i == 2) {
                            System.out.println("Tour passe");
                        }
                    }
                }
            }
        }
        sc.close();
    }


    public static void main(String[]args) {
        JeuTerminal j = new JeuTerminal();
        j.nbJoueurs = 2;
        j.gererJeuTerminal();
    }
}