import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceDistributeurNoeud extends Remote {
    void inscriptionNoeud(ServiceNoeud noeud) throws RemoteException;
    ServiceNoeud getNoeud() throws RemoteException;
}