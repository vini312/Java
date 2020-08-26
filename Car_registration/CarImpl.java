package workshop10;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CarImpl extends UnicastRemoteObject implements CarInterface {
	
	public CarImpl() throws RemoteException {
		super();
	}
	
	@Override
	public String register(Car car) throws RemoteException {
		return Integer.toString(car.hashCode());
	}
}