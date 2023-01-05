import java.util.Objects;

public class TuilesCarcassonne extends Tuiles{

    protected class ElmtTuile {
        String f;
        Boolean Rue= false;
        Boolean Pelouse= false;
        Boolean Ville= false;

        private ElmtTuile(String e){
            f =e;
            if (e == "R") {
                Rue = true;
            }
            else if (e == "P") {
                Pelouse = true;
            }
            else if (e == "V") {
                Ville = true;
            }
        }
        private boolean isEqual(ElmtTuile other){
            return Objects.equals(other.Rue,Rue)&&Objects.equals(other.Pelouse,Pelouse)&&Objects.equals(other.Ville,Ville);
        }
        @Override
        public String toString() {
            return f;
        }
    }

    protected ElmtTuile[][] tuile= new ElmtTuile[4][3];
    public Pion pion;
    public TuilesCarcassonne(String[]  c1, String[] c2, String[] c3, String[] c4 ) {
        int i =0;
        for (String[] x: new String[][] {c1,c2,c3,c4}){
            int j=0;
            for (String y:x){
                tuile[i][j]=new ElmtTuile(y);
                j++;
            }
            i++;
        }
        super.tuileCarcassonne=tuile;
    }

    public String toString() {
        String f=" ";

        for(int j=0; j<3;j++) {
            f+=tuile[0][j].toString();
        }
        f+=" \n";

        for(int j=0; j<3;j++) {
            f+=tuile[3][j].toString();
            f+="   ";
            f+=tuile[1][j].toString();
            f+="\n";

        }
        f+=" ";
        for(int j=0; j<3;j++) {
            f+=tuile[2][j].toString();
        }
        f+=" ";
        return f;
    }
    public String toString_ligne(int k) {
        String f ="";
        if (k == 0 ) {
            f+=" ";
            for(int j=0; j<3;j++) {
                f+=tuile[0][j].toString();
            }
            f+=" ";

        }
        else if (k == 4 ) {
            f+=" ";
            for(int j=0; j<3;j++) {
                f+=tuile[2][j].toString();
            }
            f+=" ";

        }
        else {


            f+=tuile[3][k-1].toString();
            if (!Objects.equals(pion,null)&&k==2){
                f+=" o ";
            }
            else{
                f+="   ";
            }
            f+=tuile[1][k-1].toString();

        }

        return f;
    }
    public boolean sontEgaux(ElmtTuile[] a, ElmtTuile[]b)  {return a[0].isEqual(b[0])&&a[1].isEqual(b[1])&&a[2].isEqual(b[2]);}

    @Override
    public int[] correspondance(Tuiles t,int cote) {
        int [] cote_score = new int[2];
        if (cote==1) {
            if (sontEgaux(tuile[cote-1],((Tuiles) t).tuileCarcassonne[2])){
                cote_score[0] = 3;
            }
        }
        else if (cote==3){
            if (sontEgaux(tuile[cote-1],((Tuiles) t).tuileCarcassonne[0])){
                cote_score[0] = 1;
            }
        }
        else if (cote==2){
            if (sontEgaux(tuile[cote-1],((Tuiles) t).tuileCarcassonne[3])){
                cote_score[0] = 4;
            }
        }
        else if (cote==4){
            if (sontEgaux(tuile[cote-1],((Tuiles) t).tuileCarcassonne[1])){
                cote_score[0] = 2;

            }
        }

        return cote_score; //la liste est [0,0]
    }

    @Override
    public void tournerTime() {
        // on crée des cotes temporaires pour storer les listes pour chaque côté
        ElmtTuile[] temp4 = tuile[0] ;
        ElmtTuile[] temp1 = tuile[1] ;
        ElmtTuile[] temp2 = tuile[2];
        ElmtTuile[] temp3 = tuile[3] ;

        ElmtTuile temp3temp0 = temp3[0]; // on gere les cotes qui doivent changer de l'ordre quand on tourne
        temp3[0]=temp3[2];
        temp3[2]=temp3temp0;
        ElmtTuile temp1temp0 = temp1[0];
        temp1[0]=temp1[2];
        temp1[2]=temp1temp0;


        //on change la tuile avec ses nouveaux cotes
        this.tuile= new ElmtTuile[][] {temp3,temp4,temp1,temp2};
        super.tuileCarcassonne= this.tuile;

    }

    public static void main(String [] args){
        String [] c1= new String[]{"P","R","P"};
        String [] c2= new String[]{"V","V","V"};
        String [] c3= new String[]{"P","P","P"};
        //type1
        TuilesCarcassonne tuile1= new TuilesCarcassonne(c3,c3,c3,c3);
        TuilesCarcassonne tuile2= new TuilesCarcassonne(c3,c3,c3,c3);
        TuilesCarcassonne tuile3= new TuilesCarcassonne(c3,c3,c3,c3);
        TuilesCarcassonne tuile4= new TuilesCarcassonne(c3,c3,c3,c3);
        //type2
        TuilesCarcassonne tuile5= new TuilesCarcassonne(c3,c3,c1,c3);
        TuilesCarcassonne tuile6= new TuilesCarcassonne(c3,c3,c1,c3);
        //type3
        TuilesCarcassonne tuile7= new TuilesCarcassonne(c2,c2,c2,c2);
        //type4
        TuilesCarcassonne tuile8= new TuilesCarcassonne(c2,c2,c3,c2);
        TuilesCarcassonne tuile9= new TuilesCarcassonne(c2,c2,c3,c2);
        TuilesCarcassonne tuile10= new TuilesCarcassonne(c2,c2,c3,c2);
        TuilesCarcassonne tuile11= new TuilesCarcassonne(c2,c2,c3,c2);
        //type5
        TuilesCarcassonne tuile12= new TuilesCarcassonne(c2,c2,c1,c2);
        TuilesCarcassonne tuile13= new TuilesCarcassonne(c2,c2,c1,c2);
        TuilesCarcassonne tuile14= new TuilesCarcassonne(c2,c2,c1,c2);
        //type 6
        TuilesCarcassonne tuile15= new TuilesCarcassonne(c2,c3,c3,c2);
        TuilesCarcassonne tuile16= new TuilesCarcassonne(c2,c3,c3,c2);
        TuilesCarcassonne tuile17= new TuilesCarcassonne(c2,c3,c3,c2);
        TuilesCarcassonne tuile18= new TuilesCarcassonne(c2,c3,c3,c2);
        TuilesCarcassonne tuile19= new TuilesCarcassonne(c2,c3,c3,c2);
        //type 7
        TuilesCarcassonne tuile20= new TuilesCarcassonne(c2,c1,c1,c2);
        TuilesCarcassonne tuile21= new TuilesCarcassonne(c2,c1,c1,c2);
        TuilesCarcassonne tuile22= new TuilesCarcassonne(c2,c1,c1,c2);
        TuilesCarcassonne tuile23= new TuilesCarcassonne(c2,c1,c1,c2);
        TuilesCarcassonne tuile24= new TuilesCarcassonne(c2,c1,c1,c2);
        TuilesCarcassonne tuile25= new TuilesCarcassonne(c2,c1,c1,c2);
        //type 8
        TuilesCarcassonne tuile26= new TuilesCarcassonne(c3,c2,c3,c2);
        TuilesCarcassonne tuile27= new TuilesCarcassonne(c3,c2,c3,c2);
        TuilesCarcassonne tuile28= new TuilesCarcassonne(c3,c2,c3,c2);
        TuilesCarcassonne tuile29= new TuilesCarcassonne(c3,c2,c3,c2);
        TuilesCarcassonne tuile30= new TuilesCarcassonne(c3,c2,c3,c2);
        TuilesCarcassonne tuile31= new TuilesCarcassonne(c3,c2,c3,c2);
        //type 9
        TuilesCarcassonne tuile32= new TuilesCarcassonne(c2,c3,c3,c2);
        TuilesCarcassonne tuile33= new TuilesCarcassonne(c2,c3,c3,c2);
        TuilesCarcassonne tuile34= new TuilesCarcassonne(c2,c3,c3,c2);
        TuilesCarcassonne tuile35= new TuilesCarcassonne(c2,c3,c3,c2);
        //type 10
        TuilesCarcassonne tuile36= new TuilesCarcassonne(c2,c3,c2,c3);
        TuilesCarcassonne tuile37= new TuilesCarcassonne(c2,c3,c2,c3);
        TuilesCarcassonne tuile38= new TuilesCarcassonne(c2,c3,c2,c3);
        TuilesCarcassonne tuile39= new TuilesCarcassonne(c2,c3,c2,c3);
        //type11
        TuilesCarcassonne tuile40= new TuilesCarcassonne(c2,c3,c3,c3);
        TuilesCarcassonne tuile41= new TuilesCarcassonne(c2,c3,c3,c3);
        TuilesCarcassonne tuile42= new TuilesCarcassonne(c2,c3,c3,c3);
        TuilesCarcassonne tuile43= new TuilesCarcassonne(c2,c3,c3,c3);
        TuilesCarcassonne tuile44= new TuilesCarcassonne(c2,c3,c3,c3);
        //type 12
        TuilesCarcassonne tuile45= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile46= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile47= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile48= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile49= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile50= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile51= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile52= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile53= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile54= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile55= new TuilesCarcassonne(c1,c3,c1,c3);
        TuilesCarcassonne tuile56= new TuilesCarcassonne(c1,c3,c1,c3);
        //type13
        TuilesCarcassonne tuile57= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile58= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile59= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile60= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile61= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile62= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile63= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile64= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile65= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile66= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile67= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile68= new TuilesCarcassonne(c3,c3,c1,c1);
        TuilesCarcassonne tuile69= new TuilesCarcassonne(c3,c3,c1,c1);
        //type 14
        TuilesCarcassonne tuile70= new TuilesCarcassonne(c1,c1,c1,c1);
        TuilesCarcassonne tuile71= new TuilesCarcassonne(c1,c1,c1,c1);
        TuilesCarcassonne tuile72= new TuilesCarcassonne(c1,c1,c1,c1);









    }
}
