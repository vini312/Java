package workshop10;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CarInterface extends Remote {
	String register(Car car) throws RemoteException;
}