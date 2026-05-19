import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AlbanilProceso {
    public static void main(String[] args) {
        try {
            // Nos conectamos al servidor RMI (en localhost para pruebas)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BaldeInterface balde = (BaldeInterface) registry.lookup("ObjetoBalde");
            
            System.out.println("Proceso Albañil iniciado.");
            
            // Ciclo de trabajo del albañil
            for (int i = 0; i < 5; i++) {
                balde.bajarBaldeVacio();
                // Simula que usa el cemento antes de volver a bajarlo
                Thread.sleep(2000); 
            }
            System.out.println("Albañil: Terminé mi jornada.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
