import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    String callback(String message) throws RemoteException;
    void receiveMessage(String message) throws RemoteException;
}
