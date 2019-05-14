import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    String greetings() throws RemoteException;

    void registerForCallback(ClientInterface client)
            throws RemoteException;

    void unregisterForCallback(ClientInterface client)
        throws RemoteException;

    void broadcast(String message)
        throws RemoteException;
}