import java.rmi.RemoteException;

public class NoeudDessin implements ServiceNoeud {

    public void compute(Disp fenetre, Scene scene, int x, int y, int l, int h) throws RemoteException {
        Image image = scene.compute(x,y,l,h);
        synchronized(fenetre){
            fenetre.setImage(image,x,y);
        }
    }
}