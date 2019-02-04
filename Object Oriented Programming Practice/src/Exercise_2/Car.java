package Exercise_2;

public class Car 
{
	static double startMiles, endMiles, gallons, loop = 6;
	
	public Car(double first, double last, double gals) 
	{
		startMiles = first;
		endMiles = last;
		gallons = gals;
	}//end of car()
	
	public static double calculateMPG()
	{
		return (endMiles - startMiles) / gallons;
	}//end of calculateMPG()
	
	public static boolean gasHog()
	{
		if (calculateMPG() <= 15) {return true;}
		
		else {return false;}
		
	}//end of gasHog()
	
	public static boolean economyCar()
	{
		if (calculateMPG() >= 30) {return true;}
		
		else {return false;}
		
	}//end of economyCar()
}//end of class
