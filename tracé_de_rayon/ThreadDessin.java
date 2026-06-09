import java.lang.Thread;
import java.rmi.RemoteException;
import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;


public class ThreadDessin extends Thread {

    private ServiceDistributeurNoeud service;
    private ServiceNoeud noeud;
    private Disp fenetre;
    private Scene scene;
    private int x,y,l,h;
    //Objet de calcul a mettre en attribut

    public ThreadDessin(ServiceDistributeurNoeud serv, Disp f, ServiceNoeud n, Scene s, int x, int y, int l, int h){
        this.noeud = n;
        this.fenetre = f;
        this.scene = s;
        this.x = x;
        this.y = y;
        this.l = l;
        this.h = h;
        this.service = serv;
    }

    @Override
    public void run(){
        Image image = null;
        try {
            // Lancement normal
            image = noeud.compute(scene,x,y,l,h);
        } catch (RemoteException ex) {
            
            // Erreur coté noeud
            try {

                // Suppression du noeud de la liste des noeuds dispo
                service.suppressionNoeud(noeud);

                // Nouveau noeud
                ServiceNoeud nouvNoeud = service.getNoeud();

                // Lancement d'un nouveau noeud
                Thread nouvThread = new ThreadDessin(service,fenetre,nouvNoeud,scene,x,y,l,h);
                nouvThread.start();

                // Interruption du thread actuel pour laisser le thread fils afficher l'image
                return;
            } catch (RemoteException e){
                System.out.println("Erreur : Déconnecté du point central");
            }
        }
        synchronized(fenetre){
            fenetre.setImage(image,x,y);
        }
        try {
            service.libererNoeud(noeud);
        } catch (RemoteException e ){
            System.out.println("Erreur : Déconnecté du point central");
        }
    }
}