import java.io.Serializable;

public class Car implements Serializable {

	private String model;
	private String color;
	private double mileage;
	private String plate; // calculated by RMI server.
	
	public Car() {
		
		// default constructor sets empty state for the car object. 
	
		this.model = "";
		this.color = "";
		this.mileage = 0.0;
		this.plate = "UN-REGISTERED";
	}
	
             
	
	// Does not take PLATE inside params b/c it defaults to unregistered. 
	public Car(String model, String color, double mileage) {
		
		
		
		this.model = model;
		this.color = color;
		this.mileage = mileage;
		this.plate = "UN-REGISTERED";
		
	}
	
	
	// SETTER
	public void setRegistration(String plate) {
		this.plate = plate;
	}
	
	// GETTER
	public String getRegistration() {
		return this.plate;
	}
	
	
	
	// PRINTS ENTIRE OBJECT 
	@Override
	public String toString() {
		//return "Car [model=" + model + ", color=" + color + ", mileage=" + mileage + ", plate=" + plate + "]";
		
		return 
		String.format("---CAR INFORMATION---\n") + 
		String.format("Car Model: %s\n", this.model) +
		String.format("Car Color: %s\n", this.color) +
		String.format("Car Mileage: %.2f\n", this.mileage) +
		String.format("Car Registration: [%s]\n", this.plate);   
		
	}
	
	
	
	
	
	
}
