package Exercise_3;

public class Car_2Tester {
	public static void main(String[] args) {
		Car_2 car = new Car_2(100, 700, 10);
		
		car.fillUp(500, 15);	
		car.fillUp(1000, 20);
		
		System.err.println("Miles per Gallon: " + car.calculateMPG());
		
		if (car.gasHog() == true) {System.err.println("GAS HOG!");}//end of if
		
		else if (car.economyCar() == true) {System.err.println("ECONOMY CAR!");} // end of else
		
	}//end of main
}//end of class
