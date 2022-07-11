import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class extraScan {
    static ArrayList<String> ip_rebuild = new ArrayList<String>();
    static ArrayList<String> trusted_devices;
    extraScan(ArrayList<String> arraylist) {
        trusted_devices = arraylist;
    }

    static Boolean ScanSingleHost(String ip) throws UnknownHostException {
        InetAddress Scan = InetAddress.getByName(ip);
        try {
            return Scan.isReachable(2000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void startExtraScanning() throws UnknownHostException {
        if(trusted_devices.size() == 0) {
            System.out.println("[!] please add some trusted devices.");
        } else {
            String machine_ip_ = InetAddress.getLocalHost().getHostAddress();
            int range_ = 0;
            String[] ip_str = machine_ip_.split("[.]", 0);
            for(String s: ip_str) {
                if(range_ != 3) {
                    ip_rebuild.add(s);
                } else {
                    break;
                }
                range_++;
            }
            ArrayList<String> rebuild_ip = new ArrayList<>();
            for(String item: ip_rebuild) {
                rebuild_ip.add(item);
                rebuild_ip.add(".");
            }
            Scanner input_object = new Scanner(System.in);
            System.out.print("enter range: ");
            String ip_toScan = "";
            int Range_value = input_object.nextInt();
            for(int index=1; index <= Range_value; index++) {
                ip_toScan = String.join("", rebuild_ip) + index;
                if(ScanSingleHost(ip_toScan)) {
                    for(String trusted_ip: trusted_devices) {
                        if(ip_toScan.equals(trusted_ip)) {
                            System.out.println(ip_toScan + " is reachable and trusted.");
                        } else {
                            System.out.println(ip_toScan + " is reachable and untrusted.");
                        }
                    }
                } else {
                    System.out.println(ip_toScan + " is not reachable.");
                }
            }
        }
    }
}
