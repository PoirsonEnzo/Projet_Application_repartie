import java.lang.Thread;
import java.rmi.RemoteException;
import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;


public class ThreadDessin extends Thread {

    private NoeudCalcul noeud;
    private Disp fenetre;
    private Scene scene;
    private int x,y,l,h;
    //Objet de calcul a mettre en attribut

    public ThreadDessin(Disp f, NoeudCalcul n, Scene s, int x, int y, int l, int h){
        this.noeud = n;
        this.fenetre = f;
        this.scene = s;
        this.x = x;
        this.y = y;
        this.l = l;
        this.h = h;
    }

    @Override
    public void run(){
        Image image;
        try {
            image = noeud.compute(scene,x,y,l,h);
        } catch (RemoteException ex) {
            System.getLogger(ThreadDessin.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        synchronized(fenetre){
            fenetre.setImage(image,x,y);
        }
    }
}