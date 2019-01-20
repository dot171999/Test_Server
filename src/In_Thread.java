import com.dot.ss.PacketModel;

import java.awt.*;
import java.io.IOException;

public class In_Thread implements Runnable {
    @Override
    public void run() {

        while(true){
            try {
                PacketModel packetModel=(PacketModel) Server.din.readObject();
                if(packetModel.getKey().equals("ZOOM")){
                    Server.ZoomScale=packetModel.getFloat();
                    System.out.println(packetModel.getFloat());
                }
                else if(packetModel.getKey().equals("CURSOR")){
                    new MouseCursor(packetModel.getX(),packetModel.getY());
                }
            } catch (IOException | ClassNotFoundException | AWTException e) {
                    e.printStackTrace();
            }
        }
    }
}
