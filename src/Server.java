import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements Runnable{
    static DataInputStream din;
    static int xx=0,yy=0;

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
            din=new DataInputStream(s.getInputStream());

            new Thread(new In_Thread()).start();

            int x,y;
            Graphics2D graphics2D;
            BufferedImage image;
            ByteArrayOutputStream baos;

            while(!Main_Frame.close_loop){

                image=r.createScreenCapture(new Rectangle(xx,yy,800,600));
                System.out.println("3");

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
