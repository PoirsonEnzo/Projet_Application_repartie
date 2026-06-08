import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;
import java.rmi.RemoteException;

public class NoeudCalcul implements ServiceNoeud {

    public Image compute(Scene scene, int x, int y, int l, int h) throws RemoteException {
        return scene.compute(x,y,l,h);   
    }
}