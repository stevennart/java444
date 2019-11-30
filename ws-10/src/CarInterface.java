import java.rmi.Remote;

public interface CarInterface extends Remote {
	
	public Car registerPlate(Car myCar) throws java.rmi.RemoteException;
	
     
}
