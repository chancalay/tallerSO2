import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            // Creamos el objeto distribuido (el monitor)
            BaldeImpl balde = new BaldeImpl();
            
            // Iniciamos el registro RMI en el puerto por defecto (1099)
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Publicamos el objeto con el nombre "ObjetoBalde"
            registry.rebind("ObjetoBalde", balde);
            
            System.out.println("Servidor RMI listo. El balde está disponible.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
