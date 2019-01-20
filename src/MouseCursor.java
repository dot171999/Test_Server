import java.awt.*;

public class MouseCursor {
    Robot r=new Robot();
    int x,y;

    public MouseCursor(int X,int Y) throws AWTException {
        //System.out.println("PC X "+MouseInfo.getPointerInfo().getLocation().getX());
        //System.out.println("PC Y "+MouseInfo.getPointerInfo().getLocation().getY());
        //System.out.println("T X "+X*1.3);
        //System.out.println("T Y "+Y*1.2);
        x=(int)(MouseInfo.getPointerInfo().getLocation().getX()+(X*1.3));
        y=(int)(MouseInfo.getPointerInfo().getLocation().getY()+(Y*1.2));
        //System.out.println("new PC X "+x);
        //System.out.println("new PC Y "+y);
        r.mouseMove(x,y);
    }
}
