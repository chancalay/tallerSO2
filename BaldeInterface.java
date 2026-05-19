import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BaldeInterface extends Remote {
    // El albañil llamará a este método
    void bajarBaldeVacio() throws RemoteException, InterruptedException;
    
    // El cadete llamará a este método
    void llenarYSubirBalde() throws RemoteException, InterruptedException;
}