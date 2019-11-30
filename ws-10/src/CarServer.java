import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

public class CarServer {
	
	public static void main(String args[]) {
		
		try {
			
			CarInterface remoteCarObj = new CarImplement(); 
			// carobj has the remote methods (registerPlate). 
			
			Registry registry = LocateRegistry.createRegistry(1099); 
          
			// binds it to the registry. 
			registry.rebind("CarService", remoteCarObj); 
			
			System.out.println("CarService has been bound to registry");
			
			  
			
			System.out.println("Car Server is now running on: " + Registry.REGISTRY_PORT);
			
		} catch (Exception ex) {
			System.err.println("Problem: " + ex);
		}
		
		
	}
	
	
	
	
	
}
