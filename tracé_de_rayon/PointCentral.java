import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PointCentral implements ServiceDistributeurNoeud {
    private List<ServiceNoeud> noeuds = new ArrayList<>();
    private int index = 0;

    
 
    public void inscriptionNoeud(ServiceNoeud noeud) throws RemoteException {
        noeuds.add(noeud);
    }
    private Set<ServiceNoeud> occupes = new HashSet<>();

    public ServiceNoeud getNoeud() throws RemoteException {
        for (int i = 0; i < noeuds.size(); i++) {
            ServiceNoeud n = noeuds.get(index);
            index = (index + 1) % noeuds.size();
            if (!occupes.contains(n)) {
                occupes.add(n);
                return n;
            }
        }
        throw new RemoteException("Aucun noeud disponible");
    }

    public void libererNoeud(ServiceNoeud noeud) throws RemoteException {
        occupes.remove(noeud);
    }

    public void suppressionNoeud(ServiceNoeud noeud) throws RemoteException {
        noeuds.remove(noeud);
        occupes.remove(noeud);
    }
}