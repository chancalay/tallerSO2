import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CadeteProceso {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BaldeInterface balde = (BaldeInterface) registry.lookup("ObjetoBalde");
            
            System.out.println("Proceso Cadete iniciado.");
            
            // Ciclo de trabajo del cadete
            for (int i = 0; i < 5; i++) {
                balde.llenarYSubirBalde();
                // Simula que prepara más mezcla
                Thread.sleep(1000);
            }
            System.out.println("Cadete: Terminé mi jornada.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
