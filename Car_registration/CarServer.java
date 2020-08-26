package workshop10;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CarServer {
	public static void main(String args[]) {
		try {
			CarInterface carObj = new CarImpl();
			Registry registry = LocateRegistry.createRegistry(8000);
			registry.rebind("CarRegistersServer", carObj);
			
		    System.out.println("Car Register Server Started!");
		    
		} catch (Exception e) {
			System.out.println("Server error: " + e);
		}
	}
}