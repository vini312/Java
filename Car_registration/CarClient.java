package workshop10;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class CarClient {
	public static void main(String[] args) throws RemoteException {
		try {
			CarInterface carConnection = (CarInterface) Naming.lookup("rmi://localhost:8000/CarRegistersServer");
			System.out.println("Client Started!");

			Car[] cars = new Car[3];
			cars[0] = new Car("Toyota Corolla", "Silver", 20000);
			cars[1] = new Car("Honda Civic", "Green", 55000);
			cars[2] = new Car("Volvo S70", "Blue", 103000);
			
			for (Car carElement : cars) {
				System.out.println("\nSending this car to the server for registration:\n\t" + carElement);
				carElement.setPlate(carConnection.register(carElement));
				System.out.println("Server response ------> Car registered:\n\t"+ carElement);
			}
			
		} catch (MalformedURLException urlError) {
			System.out.println("Malformed URL Error: " + urlError);
		} catch (RemoteException remoteError) {
			System.out.println("Remote Error: " + remoteError);
		} catch (NotBoundException boundError) {
			System.out.println("Not Bound Error" + boundError);
		}
	}
}