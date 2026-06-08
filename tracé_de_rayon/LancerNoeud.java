import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerNoeud {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java LancerNoeud <adresse_serveur> <nom_service_central>");
                return;
        }
        try{
            Registry annuaire = LocateRegistry.getRegistry(args[0],1099);
            ServiceCentral central = (ServiceCentral) annuaire.lookup(args[1]);
            NoeudCalcul noeud = new NoeudCalcul();
            ServiceNoeud servNoeud = (ServiceNoeud) UnicastRemoteObject.exportObject(noeud, 0);
            central.inscriptionNoeud(servNoeud);
        } catch (Exception e) {
            System.err.println("Erreur lors du lancement du nœud :");
            e.printStackTrace();
        }
    }
}   