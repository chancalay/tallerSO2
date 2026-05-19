import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BaldeImpl extends UnicastRemoteObject implements BaldeInterface {
    
    // Estado compartido del recurso
    // true = El balde está en el 1er piso / false = El balde está en PB
    private boolean baldeArriba = true; 

    public BaldeImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void bajarBaldeVacio() throws RemoteException, InterruptedException {
        // Si el balde NO está arriba, el albañil debe esperar
        while (!baldeArriba) {
            System.out.println("Albañil esperando: El balde está abajo.");
            wait(); // Bloquea el hilo hasta que el cadete haga un notify()
        }
        
        System.out.println("👷 Albañil: Vacié el balde y lo estoy bajando...");
        Thread.sleep(1500); // Simulamos el tiempo que tarda la acción
        
        baldeArriba = false; // Cambiamos el estado
        notifyAll(); // Despertamos al cadete que pueda estar esperando
    }

    @Override
    public synchronized void llenarYSubirBalde() throws RemoteException, InterruptedException {
        // Si el balde está arriba, el cadete debe esperar a que el albañil lo baje
        while (baldeArriba) {
            System.out.println("Cadete esperando: El balde está arriba.");
            wait(); // Bloquea el hilo hasta que el albañil haga un notify()
        }
        
        System.out.println("👦 Cadete: Llené el balde con cemento y lo estoy subiendo...");
        Thread.sleep(1500); // Simulamos el tiempo de llenado y subida
        
        baldeArriba = true; // Cambiamos el estado
        notifyAll(); // Despertamos al albañil
    }
}