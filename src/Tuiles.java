import java.util.function.Supplier;
public abstract class Tuiles {
    protected int [][] tuileDomino;
    protected TuilesCarcassonne.ElmtTuile[][] tuileCarcassonne;


    public abstract String toString();
    public abstract String toString_ligne(int k);

     public abstract int[] correspondance(Tuiles t, int cote);
    public abstract void tournerTime();
}
