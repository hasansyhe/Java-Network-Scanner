import java.net.InetAddress;
import java.net.UnknownHostException;

// create PingIp class
public class Ping {
    // create final_ip_address attribute
    static String final_ip_address;
    // create PingIp structure
    Ping(String ip_address) {
        final_ip_address = ip_address;
    }
    // create send_ping methods to check if ip is reachable or not
    static Boolean Send_Ping() throws UnknownHostException {
        InetAddress IP_Address = InetAddress.getByName(final_ip_address);
        try {
            return IP_Address.isReachable(500);
        } catch (Exception e) {
            // print the error Exception if [was there]
            System.out.println("ERROR: " + e);
        }
        return true;
    }
}