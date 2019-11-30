import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CarClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			Car myCar = new Car("Sedan", "Black", 133.7); 
			System.out.println(myCar);

			CarInterface remoteCar = (CarInterface) Naming.lookup("rmi://localhost:1099/CarService");
			// Car is the remote object being used.
			// myCar is the client's car object that is going to be registered.


			System.out.println("Registering plate for your car......"); 
			myCar = remoteCar.registerPlate(myCar);        
 
			System.out.println("\n***AFTER REGISTRATION***\n"); 
			System.out.println(myCar);

		} catch (MalformedURLException murle) {
			System.err.println(murle);
		} catch (RemoteException re) {
			System.err.println(re);
		} catch (NotBoundException nbe) {
			System.err.println(nbe);
		}

	}

}
