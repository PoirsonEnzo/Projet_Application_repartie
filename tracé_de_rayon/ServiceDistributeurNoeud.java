import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceDistributeurNoeud extends Remote {
    void inscriptionNoeud(ServiceNoeud noeud) throws RemoteException;
    ServiceNoeud getNoeud() throws RemoteException;

    void libererNoeud(ServiceNoeud noeud) throws RemoteException;
    void suppressionNoeud(ServiceNoeud noeud) throws RemoteException;
}