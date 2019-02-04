package Exercise_1;

public class Box 
{
	static double length, width, height, volume, surfaceArea;
	
	public Box(double l, double w, double h) 
	{
		length = l;
		width = w;
		height = h;
		
	}//end of Box()
	
	public double Volume() 
	{
		volume = length * width * height;
		return volume;
	}//end of Volume()
	
	public double SurfaceArea()
	{
		surfaceArea = (length * width * 2) + (length * height * 2) + (height * width * 2);
		return surfaceArea;
	}//end of SurfaceArea
}// end of class