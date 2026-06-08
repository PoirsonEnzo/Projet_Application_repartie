import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PointCentral implements ServiceDistributeurNoeud {
    private List<ServiceNoeud> noeuds = new ArrayList<>();
    private int index = 0;
 
    public void inscriptionNoeud(ServiceNoeud noeud) throws RemoteException {
        noeuds.add(noeud);
    }

    public ServiceNoeud getNoeud() throws RemoteException {
        
        if (noeuds.isEmpty()){
            throw new RemoteException("Aucun noeud disponible");
        } 

        ServiceNoeud n = noeuds.get(index);
        index = (index + 1) % noeuds.size();
        return n;
    }
}