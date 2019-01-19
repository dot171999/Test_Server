import javax.swing.*;
import java.awt.*;
import java.net.*;


public class Main_Frame extends JFrame {
    static boolean close_loop=false;

    private Main_Frame() throws SocketException {

        JTextPane jTextArea1=new JTextPane();
        jTextArea1.setText("* INSTRUCTIONS *" +System.lineSeparator()+
                " 1) Connect Server & Client on the same Network Over Wifi."+System.lineSeparator()+System.lineSeparator()+
                " 2) Always start Server before Client."+System.lineSeparator()+System.lineSeparator()+
                " 3) Select a IP from below & enter in the Client's IP entry field."+System.lineSeparator()+System.lineSeparator()+
                " 4) If unable to connect kindly Restart Server & Client or Reconnect all."+
                System.lineSeparator()+""+System.lineSeparator());

        JScrollPane jScrollPane=new JScrollPane(jTextArea1);
        jTextArea1.setCaretColor(jTextArea1.getBackground());
        add(jScrollPane);

        JTextArea jTextArea=new JTextArea();
        setIp(jTextArea);
        JLabel jLabel=new JLabel("STATUS");
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.TOP);
        JPanel jPanel1=new JPanel(new GridLayout(2,1));
        jPanel1.add(jTextArea);
        jPanel1.add(jLabel);

        JButton close_btn=new JButton("CLOSE Service");
        close_btn.addActionListener(e -> {
            close_loop=true;
            System.exit(0);
        });
        JButton reConnect_btn=new JButton("Reconnect");
        JPanel jPanel2=new JPanel(new GridLayout(2,1));
        jPanel2.add(reConnect_btn);
        jPanel2.add(close_btn);

        JPanel jPanel=new JPanel(new GridLayout(1,2));
        add(jPanel);
        jPanel.add(jPanel1);
        jPanel.add(jPanel2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250,350);
        setLayout(new GridLayout(2,1));
        setResizable(false);
        setTitle("SS (DOT)");
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void setIp(JTextArea jTextArea) throws SocketException {
        String ip[]=new Get_Ip().Ipv4_Ip();
        int i=0;
        while(i<ip.length && ip[i]!=null){
            jTextArea.append(" ("+(i+1)+")  "+ip[i]+System.lineSeparator());
            i++;
        }
    }

    public static void main(String[] args) throws Exception {
        new Main_Frame();
        new Thread(new Server()).start();
    }
}