import java.rmi.RemoteException;
import java.util.*;

public class ServiceCentralNoeuds implements ServiceCentral {
    
    List<ServiceNoeud> noeuds = new ArrayList<>();

    void inscriptionNoeud(ServiceNoeud s) throws RemoteException{
        this.noeuds.add(s);
    }

    List<ServiceNoeud> requeteNoeuds() throws RemoteException{
        
    }

}