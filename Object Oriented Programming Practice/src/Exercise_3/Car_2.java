package Exercise_3;

public class Car_2 {
static double startMiles, endMiles, gallons;
	
	public Car_2(double first, double last, double gals)
	{
		startMiles = first;
		endMiles = last;
		gallons = gals;
	}//end of car()
	
	void fillUp(int m, double g) 
	{
		startMiles = endMiles;
		endMiles = m;
		gallons = g;
	}//end of fillUp()
	
	double calculateMPG() {return (endMiles - startMiles) / gallons;}//end of calculateMPG()
	
	boolean gasHog()
	{
		if (calculateMPG() <= 15) {return true;}
		
		else {return false;}
		
	}//end of gasHog()
	
	boolean economyCar()
	{
		if (calculateMPG() >= 30) {return true;}
		
		else {return false;}
		
	}//end of economyCar()
}//end of class
