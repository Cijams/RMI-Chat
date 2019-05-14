import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class ClientImplementation extends UnicastRemoteObject
        implements ClientInterface {

    ClientImplementation() throws RemoteException {
        super();
    }

    @Override
    public String callback(String message) {
        System.out.println(message);
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            e.printStackTrace();
            hostname = "unknown";
        }
        return "Call back receive at " + hostname;
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(message);
    }
}