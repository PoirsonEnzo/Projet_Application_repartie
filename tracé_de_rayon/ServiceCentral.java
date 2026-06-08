import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;


public interface ServiceCentral extends Remote {

    void inscriptionNoeud(ServiceNoeud s) throws RemoteException;

    List<ServiceNoeud> requeteNoeuds(/*A modifier en fonction de la logique de répartition*/) throws RemoteException; 
}