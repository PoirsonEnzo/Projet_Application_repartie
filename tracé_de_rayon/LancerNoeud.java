import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerNoeud {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Nop");
                return;
        }
        try{
            Registry annuaire = LocateRegistry.getRegistry(args[0],1099);
            ServiceDistributeurNoeud central = (ServiceDistributeurNoeud) annuaire.lookup("servicecentralenoeuds");
            NoeudCalcul noeud = new NoeudCalcul();
            ServiceNoeud servNoeud = (ServiceNoeud) UnicastRemoteObject.exportObject(noeud, 0);
            central.inscriptionNoeud(servNoeud);
        } catch (Exception e) {
            System.err.println("Erreur lors du lancement du nœud :");
            e.printStackTrace();
        }
    }
}   