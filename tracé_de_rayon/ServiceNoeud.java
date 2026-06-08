import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceNoeud extends Remote {

    void compute(Disp fenetre, Scene scene, int x, int y, int l, int h) throws RemoteException;
}