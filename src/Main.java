import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.util.Enumeration;

public class Main extends JFrame {
    static boolean close_loop=false;

    private Main() throws Exception {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(0,1));

        setLocationRelativeTo(null);

        setSize(500,300);
        setTitle("DOT");

        JTextArea jTextArea=new JTextArea();
        jTextArea.setBounds(0,0,600,300);
        test(jTextArea);
        add(jTextArea);

        JTextArea jTextArea1=new JTextArea();
        jTextArea1.append("###  Connect Server & Client on the same Network Over Wifi  ###"+System.lineSeparator()+
                "###  Always start Server before Client  ###"+System.lineSeparator()+
                "###  Select Your Wireless Connection IP from above & enter in the Client's IP field  ###"+System.lineSeparator()+
                "**(Eg: 192.168.43.18)**"+System.lineSeparator()+
                "### If unable to connect kindly Restart Server & Client or Reconnect all"+System.lineSeparator()+""+System.lineSeparator()+
                "###-- Service is Online --###");
        add(jTextArea1);

        JButton close_btn=new JButton("CLOSE Service");
        close_btn.addActionListener(e -> {
            close_loop=true;
            System.exit(0);
        });
        add(close_btn);

        pack();
        setVisible(true);
    }

    private void test(JTextArea jTextArea) throws Exception {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();

            if (!networkInterface.isUp())
                continue;

            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while(addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                jTextArea.append(String.format("Interface: %s, IP ## %s,%s",
                        networkInterface.getDisplayName(), addr.getHostAddress(), System.lineSeparator()));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main();
        new Thread(new Server()).start();
    }
}