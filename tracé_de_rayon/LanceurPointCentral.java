import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LanceurPointCentral {
    public static void main(String[] args) throws Exception {
        PointCentral pc = new PointCentral();
        ServiceDistributeurNoeud service = (ServiceDistributeurNoeud) UnicastRemoteObject.exportObject(pc, 0);
        Registry reg = LocateRegistry.createRegistry(1099);
        reg.rebind("servicecentralenoeuds", service);
        System.out.println("Point central démarré");
    }
}