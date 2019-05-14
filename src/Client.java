import java.rmi.*;
import java.util.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        int port = 0;
        try {
            if (args.length == 2) {
                port = Integer.parseInt(args[1]);
                if (port < 5001 || port > 65535)
                    throw new Exception();
            } else
                throw new Exception();
        } catch (Exception e) {
            System.err.println("usage: java Client serverIP port");
            System.exit(-1);
        }
        String serverIP = args[0];

        try {
            ServerInterface serverObject = (ServerInterface)
                    Naming.lookup("rmi://" + serverIP + ":" + port + "/server");
            System.out.println("connected to server: " +
                    serverObject.greetings());

            ClientInterface clientObject = new ClientImplementation();
            serverObject.registerForCallback(clientObject);
            String hostName;

            try {
                hostName = InetAddress.getLocalHost().getHostName();
            } catch (Exception e) {
                e.printStackTrace();
                hostName = "unknown";
            }

            Scanner scan = new Scanner(System.in);
            while (scan.hasNextLine()) {
                String message = scan.nextLine();
                serverObject.broadcast(hostName + ": " + message);
            }
            serverObject.unregisterForCallback(clientObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}