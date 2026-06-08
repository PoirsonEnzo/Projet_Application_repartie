import java.rmi.Remote;
import java.rmi.RemoteException;
import raytracer.Image;

public interface ServiceNoeud extends Remote {

    Image compute(Scene scene, int x, int y, int l, int h) throws RemoteException;
}