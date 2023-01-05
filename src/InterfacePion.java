import java.awt.*;

public class InterfacePion {
    Pion p;
    Color color;
    int x;
    int y;
    int spacing;

     public InterfacePion(int x, int y,Color c){
         this.color=c;
         this.x = x;
         this.y = y;
         this.spacing=10;

     }
     public void paint(Graphics g){
        g.drawOval(x,y,50,50);
        g.setColor(color);
        g.fillOval(x,y,50,50);
     }

}
