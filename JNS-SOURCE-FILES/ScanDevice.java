import java.io.IOException;
import java.net.UnknownHostException;

public class ScanDevice {
    static String ip;
    ScanDevice(String ip_address) {
        // ScanDevice class structure
        ip = ip_address;
        // done
    }
    static void start_Scan() throws UnknownHostException {
        // create object from Ping Class
        Ping ping_Object = new Ping(ip);
        Boolean Value_End;
        while(true) {
            Value_End = ping_Object.Send_Ping();
            if(Value_End != true) {
                System.out.println("Search for " + ip);
            } else {
                System.out.println("start executing shell.sh file and what is contain.");
                try{
                    Runtime.getRuntime().exec("sh -c ~/shell.sh");
                } catch (IOException e) {
                    System.out.println("ERROR MESSAGE: " + e);
                }
                break;
            }
        }
    }
}
