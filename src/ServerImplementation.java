import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;


public class ServerImplementation extends UnicastRemoteObject
        implements ServerInterface {

    private Vector<ClientInterface> clientList;
    ServerImplementation() throws  RemoteException {
        super();
    }

    @Override
    public String greetings() throws RemoteException {
        return "hello";
    }

    @Override
    public synchronized void registerForCallback(ClientInterface client)
            throws RemoteException {
        if (!clientList.contains(client)) {
            clientList.addElement(client);
            System.out.println("Registered: " + client);
            callbacks();
        }
    }

    @Override
    public synchronized void unregisterForCallback(ClientInterface client)
            throws RemoteException {
        if(clientList.removeElement(client)) {
            System.out.println("Unregistered: " + client);
        } else {
            System.out.println("Did not unregister: " + client);
        }
    }

    @Override
    public synchronized void broadcast(String message) throws RemoteException {
        System.out.println("Broadcast...");
        for(int i = 0; i < clientList.size(); i++) {
            ClientInterface client = clientList.elementAt(i);
            client.receiveMessage(message);
        }
    }

    private void callbacks() throws RemoteException {
        System.out.println("Callbacks...");
        for(int i = 0; i < clientList.size(); i++) {
            ClientInterface client = clientList.elementAt(i);
            System.out.println(client.callback("Number of registered clients="
                + clientList.size()));
        }
    }
}

