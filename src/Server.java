import java.rmi.*;
import java.rmi.registry.*;

public class Server {
    public static void main(String[] args) {
        int port = 0;
        try {
            if (args.length == 1) {
                port = Integer.parseInt(args[0]);
                if(port < 5001 || port > 65535)
                    throw new Exception();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("usage: java Server port");
            System.exit(-1);
        }

        try {
            startRegistry(port);
            ServerImplementation serverObject = new ServerImplementation();
            Naming.rebind("rmi://localhost:" + port + "/server", serverObject);
            System.out.println("Server ready.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    private static void startRegistry(int port) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            registry.list();
        } catch (RemoteException re) {
            Registry registry = LocateRegistry.createRegistry(port);
            registry.list();
        }
    }
}
