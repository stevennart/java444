import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// the car implementation of the CarInterface for the REMOTE SERVICE. 
public class CarImplement extends UnicastRemoteObject implements CarInterface {

	// explicit constructor for this class is NEEDED for unicastremoteobject
	public CarImplement() throws RemoteException {
		super();
	}

	// a method from the CarInterface that has to be implemented.
	// throws an exception and be caught when it is being invoked.

	@Override
	public Car registerPlate(Car myCar) throws RemoteException {

		// setter method for the registration plate of the CAR OBJECT. 
		myCar.setRegistration(String.valueOf(myCar.hashCode()));
		
		return myCar; 
		
	}
}
