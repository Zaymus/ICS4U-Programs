
public class optionsCar extends Car{
	boolean nav;
	boolean dvd;
	boolean towPac;
	optionsCar(String make, String model, String color, String trim, int year, double price, 
			boolean navigation, boolean DVD, boolean towPac)
	{
		super(make, model, color, trim, year, price);
		this.nav = navigation;
		this.dvd = DVD;
		this.towPac = towPac;
	}//end of constructor
	
	public double optionsCost()
	{
		if (nav == true)	price += 2000;
		if (dvd == true)	price += 1500;
		if (towPac == true)	price += 500;
		
		return price;
	}//end of optionsCost
}//end of class
