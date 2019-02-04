package Exercise_2;

import java.util.Scanner;

public class CarTester 
{

	static Scanner sc = new Scanner(System.in);

	static double startM, endM, gallons;

	static boolean done = false;
	static Car Car;

	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{
		while (true) {
			if (true) {
				System.out.println("enter start mileage");
				startM = sc.nextDouble();
		
				System.out.println("enter end mileage");
				endM = sc.nextDouble();
		
				System.out.println("enter gallons");
				gallons = sc.nextDouble();
		
				Car = new Car(startM, endM, gallons);
		
				if (Car.gasHog() == true) 
				{
					System.err.println("Miles per Gallon: " + Car.calculateMPG());
					System.err.println("GAS HOG!");
				} else if (Car.economyCar() == true) 
				{
					System.err.println("Miles per Gallon: " + Car.calculateMPG());
					System.err.println("ECONOMY CAR!");
				} // end of if
			}//end of if
		}//end of while
	}// end of main
}// end of class
