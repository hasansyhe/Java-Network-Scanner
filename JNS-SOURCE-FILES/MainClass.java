import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

class MainClass {
    static ArrayList<String> ip_trusted_list = new ArrayList<>();
    static ArrayList<String> ip_reachable_list = new ArrayList<>();
    static ArrayList<String> root_info = new ArrayList<>();
    static ArrayList<String> ip_address_rebuild = new ArrayList<>();
    static String machine_ip;

    static {
        try {
            machine_ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    static void setup_root(ArrayList<String> arraylist) {
        arraylist.add("hasan Seyhmuhammed");
        arraylist.add("hasanalshik65@gmail.com");
    }

    static void wait_until_press() {
        System.out.print("press enter to continue...");
        try {
            System.in.read();
        } catch(Exception e) {
            System.out.println("ERROR: "+ e);
        }
    }
    static void convert_array_(ArrayList<String> arraylist) throws UnknownHostException {
        ArrayList<String> empty_arraylist = new ArrayList<>();
        String ip;
        int reachable_devices = 0;
        // get range value
        Scanner range_object = new Scanner(System.in);
        System.out.print("Enter Rnage: ");
        int range_end = range_object.nextInt();
        for(String item: arraylist) {
            empty_arraylist.add(item);
            empty_arraylist.add(".");
        }
        String[] ip_array = new String[empty_arraylist.size()];
        for(int i = 0; i < empty_arraylist.size(); i++) {
            ip_array[i] = empty_arraylist.get(i);
        }
        // create Ping object
        String gateway = String.join("", ip_array) + 1;
        for(int range = 1; range <= range_end; range++) {
            if(range == 1) {
                System.out.println("The Network Gateway: " + gateway);
                System.out.println("The Machine IP: " + machine_ip);
            } else {
                ip = String.join("", ip_array) + range;
                Ping status = new Ping(ip);
                if(status.Send_Ping()) {
                    reachable_devices++;
                    System.out.println("IP: " + ip + " is reachable [reachable " + reachable_devices + "]");
                    ip_reachable_list.add(ip);
                } else {
                    System.out.println("IP: " + ip + " is not reachable [reachable " + reachable_devices + "]");
                }
            }
        }
    }
    static void scan_multi_ip() throws UnknownHostException {
        int range_ = 0;
        String[] ip_str = machine_ip.split("[.]", 0);
        for(String s: ip_str) {
            if(range_ != 3) {
                ip_address_rebuild.add(s);
            } else {
                break;
            }
            range_++;
        }
        convert_array_(ip_address_rebuild);
    }
    static void getMachine_ip() {
        System.out.println("Your Machine IP: " + machine_ip);
    }
    static void Scan_single_ip() throws UnknownHostException {
        // this method scan single ip address and print is this ip is reachable or not
        Scanner Scanner_object_ip = new Scanner(System.in);
        System.out.print("enter ip address: ");
        String single_ip_address = Scanner_object_ip.nextLine();
        Ping ping_object = new Ping(single_ip_address);
        Boolean Status_Code = ping_object.Send_Ping();
        if(Status_Code) {
            System.out.println(single_ip_address + " is online and reachable");
            wait_until_press();
        } else {
            System.out.println(single_ip_address + " is offline and unreachable");
            wait_until_press();
        }
    }
    static void display_reachable_device(ArrayList<String> arraylist) {
        int index = 0;
        // print arraylist size
        System.out.println("total reachable devices: " + arraylist.size());
        if(arraylist.size() == 0) {
            // if arraylist is empty
            System.out.println("[!] you should make some scanning to display some reachable devices.");
            wait_until_press();
        } else {
            // if arraylist is not empty
            for(String device_rech: arraylist){
                System.out.println("\t" + index + " IP: " + device_rech);
                index++;
            }
        }
    }
    static void display_option() {
        System.out.println("\t[+] add            to add new trusted device");
        System.out.println("\t[+] ip             to get local ip address of your machine");
        System.out.println("\t[+] remove         to remove device from trusted list");
        System.out.println("\t[+] normalScan     to scan all ip on the network");
        System.out.println("\t[+] extraScan      to scan for any untrusted ip");
        System.out.println("\t[+] singleScan     to scan specific ip address");
        System.out.println("\t[+] display        to display all trusted devices");
        System.out.println("\t[+] reachable      to display all reachable devices on network");
        System.out.println("\t[+] empty          to make trusted devices empty");
        System.out.println("\t[+] help           to display help message and options");
        System.out.println("\t[+] option         to display this message");
        System.out.println("\t[+] exit           to stop everything and exit");
    }
    static void display_trusted_list(ArrayList<String> arraylist) {
        // this method to display all items inside ip_trusted_list
        if(arraylist.size() == 0) {
            System.out.println("list of trusted devices is empty.");
            wait_until_press();
        } else {
            int list_index = arraylist.size();
            for(int index = 0; index != arraylist.size(); index++) {
                System.out.println("Trusted IP: " + arraylist.get(index));
            }
            System.out.println("The number of trusted devices: " + list_index);
            wait_until_press();
        }
        // done
    }

    // this function { to add new element to add_trusted_device }
    static void add_trusted_device(ArrayList<String> arraylist) {
        Scanner Scanner_Object = new Scanner(System.in);
        System.out.println("add new ip to our trusted devices list");
        System.out.print("enter new ip address: ");
        String newDevice = Scanner_Object.nextLine();
        arraylist.add(newDevice);
        System.out.println(newDevice + " trusted now");
    }
    // done
    static void make_arraylisy_empty(ArrayList<String> arraylist) {
        if(arraylist.size() == 0) {
            System.out.println("Trusted list already empty.");
        } else {
            Scanner ask_object = new Scanner(System.in);
            System.out.print("[?] are you sure (y/n)? ");
            String answer = ask_object.nextLine();
            if(answer.equals("y") || answer.equals("yes")) {
                arraylist.clear();
                System.out.println("Trusted list is empty now.");
            } else {
                System.out.println("Operation cancelled.");
            }
        }
    }
    static void Start_Scan_Device() throws UnknownHostException {
        Scanner get_Object = new Scanner(System.in);
        System.out.print("Enter IP Address: ");
        String ip_ = get_Object.nextLine();
        ScanDevice ScanObject = new ScanDevice(ip_);
        ScanObject.start_Scan();
    }
    static void Scan_multi_extra(ArrayList<String> arraylist) throws UnknownHostException {
        // create object from extraScan
        extraScan extra_scan = new extraScan(arraylist);
        extra_scan.startExtraScanning();
    }
    public static void main(String[] args) throws UnknownHostException {
        setup_root(root_info);
        String user_input;
        Scanner newScanner = new Scanner(System.in);
        label:
        while(true) {
            System.out.print("jns> ");
            user_input = newScanner.nextLine();
            switch (user_input) {
                case "option":
                    display_option();
                    break;
                case "exit":
                    System.out.println("Good Bye");
                    break label;
                case "help":
                    System.out.println("[!] Java Network Scanner Project - 1.17 Beta Version - " + root_info.get(1));
                    System.out.println("[!] Project on Github github.com/hasansyhe/Java-Network-Scanner");
                    System.out.println("[!] Author " + root_info.get(0));
                    System.out.println("[!] License GPL 2 Version");
                    break;
                case "add":
                    add_trusted_device(ip_trusted_list);
                    break;
                case "ifThere":
                    Start_Scan_Device();
                    break;
                case "singleScan":
                    Scan_single_ip();
                    break;
                case "extraScan":
                    Scan_multi_extra(ip_trusted_list);
                    break;
                case "display":
                    display_trusted_list(ip_trusted_list);
                    break;
                case "empty":
                    make_arraylisy_empty(ip_trusted_list);
                    break;
                case "reachable":
                    display_reachable_device(ip_reachable_list);
                    break;
                case "normalScan":
                    scan_multi_ip();
                    break;
                case "ip":
                    getMachine_ip();
                    break;
            }
        }
    }
}