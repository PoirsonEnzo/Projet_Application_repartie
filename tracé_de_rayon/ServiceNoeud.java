import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceNoeud extends Remote {

    void compute(/*Type de l'objet a calculer*/) throws RemoteException;
}