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
            image = noeud.compute(scene,x,y,l,h);
        } catch (RemoteException ex) {
            service.suppressionNoeud(noeud);
        }
        synchronized(fenetre){
            fenetre.setImage(image,x,y);
        }
        service.libererNoeud(noeud);
    }
}