package Exercise_1;
import java.util.Scanner;

public class BoxTester 
{
	static Scanner sc = new Scanner(System.in);
	
	static double l, w, h;
	static Box Box;
	public static void main(String[] args) 
	{
		System.out.println("input a length");
		l = sc.nextDouble();
		
		System.out.println("input a width");
		w = sc.nextDouble();
		
		System.out.println("input a height");
		h = sc.nextDouble();
		
		System.out.println("calculating...");
		System.out.println("--------------");
		
		Box = new Box(l, w, h);
		
		System.err.println("Volume: " + Box.Volume());
		System.err.println("Surface Area: " + Box.SurfaceArea());
	}//end of main
}//end of class
