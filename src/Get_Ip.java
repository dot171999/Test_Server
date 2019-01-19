import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

 class Get_Ip {

    String[] Ipv4_Ip() throws SocketException {
        String ip[]=new String[10],temp;
        int i=0;
        Enumeration e= NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements()){
            NetworkInterface networkInterface= (NetworkInterface) e.nextElement();
            Enumeration ee=networkInterface.getInetAddresses();
            while(ee.hasMoreElements()){
                InetAddress inetAddress=(InetAddress) ee.nextElement();
                temp=inetAddress.getHostAddress();
                if((temp.charAt(1) == '7' || temp.charAt(1) == '9') && (temp.charAt(2) == '2')){
                    ip[i]=temp;
                    i++;
                }else if(temp.charAt(0) == '1' && temp.charAt(1) == '0'){
                    ip[i]=temp;
                    i++;
                }
            }
        }
        return ip;
    }
}
