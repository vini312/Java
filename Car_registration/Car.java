package workshop10;

import java.io.Serializable;

public class Car implements Serializable {

	private String model;
	private String color;
	private double mileage;
	private String plate;
	
	public Car(String model, String color, double mileage) {
		super();
		this.model = model;
		this.color = color;
		this.mileage = mileage;
		this.plate = "unregistered";
	}

	public String toString() {
		return "Model: " + model + "\t| " +
				"Color: " + color + "\t| " +
				"Mileage: " +  mileage + "\t| " +
				"Plate: " + plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

}