import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;
import java.rmi.RemoteException;

public class NoeudCalcul implements ServiceNoeud {

    public void compute(Disp fenetre, Scene scene, int x, int y, int l, int h) throws RemoteException {
        Image image = scene.compute(x,y,l,h);
        synchronized(fenetre){
            fenetre.setImage(image,x,y);
        }
    }
}