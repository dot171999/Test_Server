import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements Runnable{
    static ObjectInputStream din;
    static float ZoomScale=1;
    @Override
    public void run() {
        try{
            Image cursor = ImageIO.read(this.getClass().getResource("cursor.png"));
            Robot r=new Robot();
            ServerSocket ss=new ServerSocket(3333);
            System.out.println("1");
            Socket s=ss.accept();//establishes connection
            System.out.println("2");

            DataOutputStream dis=new DataOutputStream(s.getOutputStream());
            din=new ObjectInputStream(s.getInputStream());

            new Thread(new In_Thread()).start();

            int x,y;
            Graphics2D graphics2D;
            BufferedImage image;
            ByteArrayOutputStream baos;

            while(!Main_Frame.close_loop){
                image=r.createScreenCapture(new Rectangle(0,0,(int)(683*ZoomScale),(int)(384*ZoomScale)));
                //System.out.println("3");

                baos = new ByteArrayOutputStream();

                x = MouseInfo.getPointerInfo().getLocation().x;
                y = MouseInfo.getPointerInfo().getLocation().y;

                graphics2D = image.createGraphics();
                graphics2D.drawImage(cursor, x, y, 20, 20, null);

                ImageIO.write(image, "jpg", baos);

                byte[] bytes = baos.toByteArray();
                int len=bytes.length;

                dis.writeInt(len);

                dis.write(bytes);

                dis.flush();

            }
            dis.close();
            ss.close();
        }catch(Exception e){
            System.out.print(e);

        }
    }
}
