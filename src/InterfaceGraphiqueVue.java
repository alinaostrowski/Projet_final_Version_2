import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static java.lang.Integer.parseInt;

public class InterfaceGraphiqueVue extends JFrame{
    int w_tuile = 100;
    int h_tuile = 100;

    JPanel panneauJeu= new JPanel();
    JPanel panneauChoix= new JPanel();
    JPanel panneauChoixJoueurs= new JPanel();
    JPanel nomAgeJoueurs= new JPanel();

    JPanel jeuMenu= new JPanel();
    JButton startJeu;
    JButton carcassonne= new JButton("Carcassonne");
    JButton dominos= new JButton("Dominos");
    JeuInterface jeu= new JeuInterface();
    int n_j =0;
    Tuiles t_j ;
    Color [] colors={Color.BLUE,Color.YELLOW,Color.RED, Color.GREEN,Color.PINK, Color.CYAN,Color.MAGENTA,Color.BLACK};

    TuilePioche tuile_pioche;
    JButton placerTuile= new JButton("Placer Tuile");
    JButton tournerTuile= new JButton("Tourner Tuile");
    JButton passerTour= new JButton("Passer Tour");
    JButton poserPion= new JButton("PoserPion");


    public InterfaceGraphiqueVue() {
        InterfaceStart();
        InterfaceJoueurs();
        }

    public void InterfaceStart(){
        this.setTitle("Start");
        this.setSize(1200,800);
        panneauJeu.setBackground(Color.CYAN);
        this.getContentPane().add(panneauJeu);
        panneauJeu.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panneauChoix.setBackground(Color.DARK_GRAY);
        panneauChoix.add(carcassonne);
        carcassonne.addActionListener((event)-> {jeu.setJeu("Carcassonne");t_j = jeu.pJeu.ST.pickRandom();this.getContentPane().remove(panneauChoix);this.setTitle("Choix Joueurs"); this.getContentPane().add(panneauChoixJoueurs);this.getContentPane().validate();});
        carcassonne.setHorizontalAlignment(carcassonne.CENTER);
        panneauChoix.add(dominos);
        dominos.addActionListener((event)-> {jeu.setJeu("Dominos");t_j = jeu.pJeu.ST.pickRandom();this.getContentPane().remove(panneauChoix);this.setTitle("Choix Joueurs"); this.getContentPane().add(panneauChoixJoueurs);this.getContentPane().validate();});
        dominos.setHorizontalAlignment(dominos.CENTER);
        startJeu = new JButton("START");
        startJeu.setHorizontalAlignment(startJeu.CENTER);
        panneauJeu.add(startJeu);
        startJeu.addActionListener((event)-> {this.getContentPane().remove(panneauJeu);this.setTitle("Choix du Jeu");this.getContentPane().add(panneauChoix);this.getContentPane().validate();});

    }
    public void InterfaceJoueurs(){

        panneauChoixJoueurs.setBackground(Color.GRAY);
        panneauChoixJoueurs.setLayout(new BoxLayout(panneauChoixJoueurs,BoxLayout.Y_AXIS));
        JSlider nbJoueursH= new JSlider(0,4,1);
        nbJoueursH.setMajorTickSpacing(1);

        nbJoueursH.setPaintLabels(true);
        nbJoueursH.setPaintTicks(true);
        nbJoueursH.addChangeListener((event)->jeu.nbJoueursH=nbJoueursH.getValue());
        JLabel sliderTitleH= new JLabel("Combien de joueurs humains?",JLabel.CENTER);

        JSlider nbJoueursIA= new JSlider(0,4,1);
        nbJoueursIA.setMajorTickSpacing(1);

        nbJoueursIA.setPaintLabels(true);
        nbJoueursIA.setPaintTicks(true);
        nbJoueursIA.addChangeListener((event)->jeu.nbJoueursIA=nbJoueursIA.getValue());
        JLabel sliderTitleIA= new JLabel("Combien de joueurs IA?",JLabel.CENTER);
        panneauChoixJoueurs.add(sliderTitleH);
        panneauChoixJoueurs.add(nbJoueursH);
        panneauChoixJoueurs.add(sliderTitleIA);
        panneauChoixJoueurs.add(nbJoueursIA);

        JButton validerNbJoueurs= new JButton("OK");
        panneauChoixJoueurs.add(validerNbJoueurs);
        validerNbJoueurs.addActionListener((event)->{this.getContentPane().remove(panneauChoixJoueurs);this.setTitle("Choix du nb de Joueurs");nomAgeJoueurs();});



    }

    public void nomAgeJoueurs(){
        this.getContentPane().add(nomAgeJoueurs);

        nomAgeJoueurs.setBackground(Color.GRAY);
        nomAgeJoueurs.setLayout(new BoxLayout(nomAgeJoueurs,BoxLayout.Y_AXIS));

        JTextField[][] txtF = new JTextField[jeu.nbJoueursH][2];
        for(int i=0; i<jeu.nbJoueursH;i++) {

            JTextField nomField = new JTextField();
            JTextField ageField = new JTextField();
            JLabel nom = new JLabel("Nom: ", JLabel.CENTER);
            JLabel age = new JLabel("Age: ", JLabel.CENTER);
            txtF[i][0] = nomField;
            txtF[i][1] = ageField;
            nomAgeJoueurs.add(nom);
            nomAgeJoueurs.add(nomField);
            nomAgeJoueurs.add(age);
            nomAgeJoueurs.add(ageField);
    }
        JButton validerNomAgeJoueurs= new JButton("OK");
        nomAgeJoueurs.add(validerNomAgeJoueurs);
        validerNomAgeJoueurs.addActionListener((event)->{for(int i=0; i<jeu.nbJoueursH;i++){
            jeu.joueurs.add(new JoueurHumain(txtF[i][0].getText(),null,parseInt( txtF[i][1].getText())));
        };
            for(int i=0; i<jeu.nbJoueursIA;i++){
                jeu.joueurs.add(new JoueurIA("Joueur"+i,null,i*10));
            };for (int k=0;k<jeu.joueurs.size();k++){
                jeu.joueurs.get(k).setCouleur(colors[k]);
            }this.getContentPane().remove(nomAgeJoueurs);this.setTitle("Jeu");Jeu();});
        this.getContentPane().validate();


    }

    public void  Jeu() {


        JPanel jeuPrincipal= new InterfacePanelJeuPrincipal(jeu.pJeu);
        jeuPrincipal.setBackground(Color.GRAY);

        //scrollPane.setBounds(jeuPrincipal.getX()+jeuPrincipal.getWidth(), 0, 50, jeu.pJeu.hauteur*h_tuile);

        jeuMenu.setBackground(Color.lightGray);
        jeuMenu.setLayout(new BoxLayout(jeuMenu, BoxLayout.Y_AXIS));
        JTextField positionXText= new JTextField(1);
        JLabel positionXLabel = new JLabel("X: ", JLabel.CENTER);
        JTextField positionYText= new JTextField(1);
        JLabel positionYLabel = new JLabel("Y: ", JLabel.CENTER);


        placerTuile.setHorizontalAlignment(placerTuile.CENTER);

        jeuMenu.add(placerTuile);
        jeuMenu.add(tournerTuile);
        jeuMenu.add(passerTour);
        if (jeu.typeJeu=="Carcassonne"){
            jeuMenu.add(poserPion);
        }
        jeuMenu.add(positionXLabel);
        jeuMenu.add(positionXText);
        jeuMenu.add(positionYLabel);
        jeuMenu.add(positionYText);

        tuile_pioche = new TuilePioche(t_j,200,200);
        tuile_pioche.setPreferredSize(new Dimension(w_tuile*10,h_tuile*10));

        //JLabel text_pioche = new JLabel("Tuile Piochée: ", JLabel.CENTER);
        //tuile_pioche.add(text_pioche,BorderLayout.CENTER);
        jeuMenu.add(tuile_pioche,BorderLayout.NORTH);
        JPanel statut_jeu= new InterfaceStatutJeu(jeu);
        statut_jeu.setPreferredSize(new Dimension(300,500));
        jeuMenu.add(statut_jeu);
        //Boutons Fonctions
        tournerTuile.addActionListener((event)->{tournerTuile();this.getContentPane().repaint();});
        placerTuile.addActionListener((event)->{poserTuile(jeuPrincipal,positionXText.getText(),positionYText.getText(),jeu.joueurs.get(n_j));this.getContentPane().repaint();});
        passerTour.addActionListener((event)->{passerTour();this.getContentPane().repaint();});
        poserPion.addActionListener((event)->{poserPion((TuilesCarcassonne) t_j,jeu.joueurs.get(n_j));this.getContentPane().repaint();});


        GridLayout layout= new GridLayout(1,2);
        this.getContentPane().setLayout(layout);

        this.getContentPane().add(jeuMenu);
        jeuPrincipal.setPreferredSize(new Dimension(5000,2000));
        JScrollPane scrollPane = new JScrollPane(jeuPrincipal);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setPreferredSize(new Dimension(10,10));
        //scrollPane.getViewport().setPreferredSize(new Dimension(jeuPrincipal.getPreferredSize()));



        this.getContentPane().add(scrollPane);
        //this.getContentPane().add(jeuPrincipal);
        this.getContentPane().validate();
        this.pack();
        this.setVisible(true);

        jouerIA();

    }

    public void poserTuile(JPanel JP,String x,String y,Joueur J){
        try {
            int x_p = Integer.parseInt(x);
            int y_p = Integer.parseInt(y);

            boolean possible_poser = jeu.pJeu.placerTuile(t_j,x_p,y_p,J);
            JP = new InterfacePanelJeuPrincipal(jeu.pJeu);
            this.getContentPane().revalidate();
            if(possible_poser){
                if (jeu.typeJeu=="Dominos"){
                    passerTour();
                }
                else{
                    tournerTuile.setEnabled(false);
                    placerTuile.setEnabled(false);
                }
            }
            else{
                JOptionPane.showMessageDialog(this.getContentPane(),"Impossible de poser la tuile ici");
            }

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.getContentPane(),"Number");

        }
    }
    public void tournerTuile(){
        t_j.tournerTime();
        tuile_pioche.setT(t_j);
        tuile_pioche.setPreferredSize(new Dimension(w_tuile*10,h_tuile*10));
        jeuMenu.revalidate();
    }
    public void passerTour(){
        n_j = (n_j+1)%jeu.joueurs.size();
        if(!jeu.pJeu.ST.estVide()){
        t_j=jeu.pJeu.ST.pickRandom();
        try {
            tuile_pioche.setT(t_j);
            tuile_pioche.setPreferredSize(new Dimension(w_tuile*10,h_tuile*10));
            tournerTuile.setEnabled(true);
            placerTuile.setEnabled(true);
            jouerIA();
            jeuMenu.revalidate();

        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(this.getContentPane(),"Jeu fini! Le gagnant est: "+ jeu.gagnant().nom);
        }}
        else{
            JOptionPane.showMessageDialog(this.getContentPane(),"Jeu fini! Le gagnant est: "+ jeu.gagnant().nom);
        }

    }

    public void jouerIA(){
        if (jeu.joueurs.get(n_j) instanceof JoueurIA){
                boolean jouerTourIA = ((JoueurIA) jeu.joueurs.get(n_j)).jouerTour(t_j,jeu.pJeu);
                passerTour();
        }
        else{
            JOptionPane.showMessageDialog(this.getContentPane(),"Joueur "+jeu.joueurs.get(n_j).nom);
        }

    }

    public void poserPion(TuilesCarcassonne t_c,Joueur j) {
        if(jeu.poserPion(t_c, j)) {
            passerTour();
        }
        else{
            JOptionPane.showMessageDialog(this.getContentPane(),"Vous n'avez plus de pions!");
        }

    }
        public static void main(String [] args){
        InterfaceGraphiqueVue vue= new InterfaceGraphiqueVue();
        vue.setVisible(true);

        }

}



