import java.text.DecimalFormat;

public class Car 
{
	
	String make,model, color, trim;
	int year;
	double price;
	DecimalFormat decimalFormat = new DecimalFormat(".##");
	
	Car (String make, String model, String color, String trim, int year, double price)
	{
		this.make = make;
		this.model = model;
		this.color = color;
		this.trim = trim;
		this.year = year;
		this.price = price;
	}//end of constructor
	
	public double getPrice()
	{
		return Double.parseDouble(decimalFormat.format(price * 1.13));
	}//end of checkPrice
	
	public double estimatePrice()
	{
		return price;
	}//end of checkTrim
}//end of class
