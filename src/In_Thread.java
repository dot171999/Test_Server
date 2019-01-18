import java.io.IOException;

public class In_Thread implements Runnable {
    @Override
    public void run() {

        while(true){
            try {
                Server.xx=(int)Server.din.readFloat();
                Server.yy=(int) Server.din.readFloat();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
