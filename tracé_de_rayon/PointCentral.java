import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PointCentral implements ServiceDistributeurNoeud {
    private List<ServiceNoeud> noeuds = new ArrayList<>();
    private int index = 0;
    private Set<ServiceNoeud> occupes = new HashSet<>();

    
 
    public void inscriptionNoeud(ServiceNoeud noeud) throws RemoteException {
        noeuds.add(noeud);
    }

    public ServiceNoeud getNoeud() throws RemoteException {
        if (!noeuds.isEmpty()){
            // Parcours jusqu'a ce qu'un noeud soit disponible
            while (true){

                for (int i = 0; i < noeuds.size(); i++) {
                    // Recherche d'un noeud libre
                    ServiceNoeud n = noeuds.get(index);
                    index = (index + 1) % noeuds.size();
                    if (!occupes.contains(n)) {
                        occupes.add(n);
                        return n;
                    }
                }
            }
        }
        throw new RemoteException("Aucun noeud disponible");
    }

    public synchronized void libererNoeud(ServiceNoeud noeud) throws RemoteException {
        occupes.remove(noeud);
    }

    public synchronized void suppressionNoeud(ServiceNoeud noeud) throws RemoteException {
        noeuds.remove(noeud);
        occupes.remove(noeud);
    }
}