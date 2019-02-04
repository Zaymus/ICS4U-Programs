/*
 * Description: Using the robot class, I made a robot that uses a pathfinding AI to find the opening and picks up all the "things"
 * and will place them back to their exact positions.
 * Author: Carson Brown
 * Date: November 2nd 2018
 * Version: 1
 */
package clearMines;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;
import becker.robots.Thing;
import becker.robots.Wall;

public class clearMines 
{

	static Random r = new Random(); 
	static City Newmarket = new City(); // initializes a city called Newmarket
	static Robot karel = new Robot(Newmarket, 0, 0, Direction.EAST);// initializes and creates a robot in Newmarket 
	
	static File kLF = new File("karelThingLocations.txt"); // creates a file that will store the thing locations that karel has picked up
	static File tLF = new File("thingLocations.txt"); // creates a file that will store the thing locations

	static PrintWriter outputStream;
	
	static int[][] karelThingLocations= new int[625][2];//temporary arrays that are used for testing
	static int[][] thingLocations = new int[625][2];

	static int things, length, row, openingX, openingY, numThings, yTarget, xTarget, time, timeSent; // creates global integer variables

	static boolean complete = false; //boolean variables for looping
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException 
	{
		length = r.nextInt(45) + 5; //randomizes the side lengths of the city 
		numThings = (int)((length * length) * 0.25); //changes the number of things depending on the number of spaces available
		
		karel.setSpeed(25); // sets the speed of the robot
		karel.setLabel("Karel"); // sets the label of the robot
		
		build(3, 1, length); // calls the build method
		spawnThings(openingX, openingY, length, thingLocations);//calls the spawnThings method
		
		System.out.println("Entrance: " + openingX + " , " + openingY);
		System.out.println("Karel: " + karel.getAvenue() + " , " + karel.getStreet());

		goToEntrance(openingX, openingY);//calls the goToEntrance method
		timeSent = (int) System.currentTimeMillis();
		run();//calls the run method
	}//end of main
	
	/* inputs an x, y, and length variable
	 * outputs 4 walls in the city
	 * uses the inputs to build 4 walls with an opening and uses the coordinates as the top left corner of the 
	 */
	@SuppressWarnings("unused")
	public static void build(int sX, int sY, int len) 
	{
		openingX = sX;
		openingY = sY;
		int i;
		len -= 1;
		for (i = len; i >= 0; i--) 
		{
			Wall nWall = new Wall(Newmarket, sY, sX + i, Direction.NORTH);//creates the northern wall
			Wall eWall = new Wall(Newmarket, sY + i, sX + len, Direction.EAST);//creates the eastern wall
			Wall sWall = new Wall(Newmarket, sY + len, sX + len - i, Direction.SOUTH);//creates the southern Wall
			if (i > 0) 
			{
				Wall wWall = new Wall(Newmarket, (sY + 1) + len - i, sX, Direction.WEST);//creates the western wall and leaves an opening
			}//end of if
		}//end of for
	}//end of method

	/*
	 * inputs a minimum x and y coordinate, a maximum length and an array to temporarily store the locations
	 * outputs x amount of things inside the dimensions of the walls.
	 * uses the inputs to randomly generate origional coordinates and checks each coordinate to make sure that there are no locations with multiple things
	 */
	@SuppressWarnings("unused")
	public static void spawnThings(int sX, int sY, int len, int[][] a) 
	{	
		try 
		{
			outputStream = new PrintWriter(tLF);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}//end of try
		outputStream.println("X \t | \t Y");
		outputStream.println("-----------");
		int x, y;
	for (int i = 0; i < numThings; i++) 
	{
			a[i][0] = r.nextInt(len - 1) + sX;
			a[i][1] = r.nextInt(len - 1) + sY;
			
			for(int k = 0; k < i; k++) 
			{
				if (i == k) 
				{
					continue;
				}else 
				{
					if (a[i][0] == a[k][0] && a[i][1] == a[k][1]) 
					{
							x = r.nextInt(len - 1) + sX;
							y = r.nextInt(len - 1) + sY;
					}//end of if
				}//end of if
			}//end of for
		}//end of for
		for(int i = 0; i < numThings; i++) 
		{
		    outputStream.println(a[i][0] + " \t | \t " + a[i][1]);
		    Thing t = new Thing(Newmarket, a[i][1], a[i][0]);
		}//end of for
		outputStream.close();
		System.out.println(numThings + " Things");
	}//end of method

	/*
	 * this method moves the robot to the entrance while only using its and the openings coordinates
	 * the method takes the openings coordinates
	 * this method outputs commands to the robot to move forward or turn depending on the conditions
	 */
	public static void goToEntrance(int x, int y) 
	{
		// avenue = x
		// street = y

		if (karel.getAvenue() > x) 
		{
			if (karel.getStreet() > y) 
			{
				//quadrant 4
				System.out.println("Quadrant 4");

				turn(Direction.WEST);//calls the turn method
				while((karel.getAvenue() > x - 1)) 
				{
					if (karel.frontIsClear()) 
					{
						karel.move();
						}else 
						{
							turn(Direction.NORTH);//calls the turn method

							while(karel.getStreet() > y - 1) {karel.move();}

							turn(Direction.WEST);//calls the turn method
						}//end of else
					}//end of while
					if (karel.getStreet() > y) 
					{
							turn(Direction.NORTH);//calls the turn method
							while(karel.getStreet() > y - 1) {karel.move();}
						}//end of if
				turn(Direction.SOUTH);//calls the turn method

				karel.move();

				turn(Direction.EAST);//calls the turn method
			}else 
			{
			//quadrant 1
				System.out.println("Quadrant 1");

				turn(Direction.WEST);//calls the turn method
				while((karel.getAvenue() > x - 1 )&& karel.frontIsClear()) {karel.move();}

				turn(Direction.SOUTH);//calls the turn method
				while(karel.getStreet() < y) {karel.move();}
				
				turn(Direction.EAST);//calls the turn method
			}//end of else

		}else 
		{
			if (karel.getStreet() > y) 
			{
				//quadrant 3
				System.out.println("Quadrant 3");

				turn(Direction.EAST);//calls the turn method
				while(karel.getAvenue() < x - 1) {karel.move();}

				turn(Direction.NORTH);//calls the turn method
				while(karel.getStreet() > y) {karel.move();}
				
				turn(Direction.EAST);//calls the turn method
			}else 
			{
				//quadrant 2
				System.out.println("Quadrant 2");

				turn(Direction.EAST);//calls the turn method
				while(karel.getAvenue() > x - 1) {karel.move();}

				turn(Direction.SOUTH);//calls the turn method
				while(karel.getStreet() < y) {karel.move();}

				turn(Direction.EAST);//calls the turn method
			}//end of else
		}//end of else
	}//end of method

	/*
	 * this method moves the robot throughout the city and collects any things it comes across
	 * this method outputs commands to the robot like turning when it reaches the end of a row
	 */
	public static void run() 
	{
		while (!complete) 
		{
			if (karel.frontIsClear()) 
			{
				move();//calls the move method
			} else 
			{
				row++;
				if (row == length) 
				{
					if (length % 2 == 0) 
					{
						turn(Direction.NORTH);//calls the turn method
						while (karel.frontIsClear())
							move();//calls the move method
							turn(Direction.WEST);//calls the turn method
							move();//calls the move method
							karel.setLabel("Complete");
							complete = true;
					} else 
					{
						turn(Direction.WEST);//calls the turn method
						while (karel.frontIsClear()) 
						{
							move();//calls the move method
							turn(Direction.NORTH);//calls the turn method
						}
						while (karel.frontIsClear()) 
						{
							move();//calls the move method
							turn(Direction.WEST);//calls the turn method
							move();//calls the move method
							karel.setLabel("Complete");
						}//end of while
						complete = true;
					}//end of else   
				} else
					nextRow(row);//calls the nextRow method
			}//end of else
		}//end of while
		time = (int) System.currentTimeMillis() - timeSent;
		System.err.println("Karel took " + time + " Ms to clear and place the objects.");
		try 
		{
			outputStream = new PrintWriter(kLF);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}//end of try
		
		for(int i = 0; i < numThings; i++) 
		{
			outputStream.println(karelThingLocations[i][0] + "," + karelThingLocations[i][1]);
		}//end of for
		outputStream.close();
		placeThings(karelThingLocations);//calls the placeThings Method
	}//end of method

	/*
	 * this method moves the robot forward and checks if it can pick up an object
	 * outputs a number of things karel has picked up and saves the location it picked up an object to an array
	 */
	public static void move() 
	{
		karel.move();
		if (karel.canPickThing()) 
		{
			karel.pickThing();
			
			karelThingLocations[things][0] = karel.getAvenue();
			karelThingLocations[things][1] = karel.getStreet();
			things++;
			karel.setLabel("karel - " + things);
		}//end of if
	}//end of method

	/*
	 * this method was created just for ease of use of karel while turning.
	 * the method takes a direction enum
	 * it will output a turn command until karel's direction matches the input
	 */
	public static void turn(Direction d) 
	{
			while(karel.getDirection() != d) {karel.turnLeft();}
	}//end of method

	/*
	 * the nextRow method determines which way karel needs to turn after it finishes a row of the city
	 * the input is the row number it is currently on
	 * the output is a two turn directions and a movement
	 */
	public static void nextRow(int r) 
	{
		if (r % 2 == 0) 
		{
			turn(Direction.SOUTH);//calls the turn method
			move();//calls the move method
			turn(Direction.EAST);//calls the turn method
		}//end of if

		else 
		{
			turn(Direction.SOUTH);//calls the turn method
			move();//calls the move method
			turn(Direction.WEST);//calls the turn method
		}//end of else
	}//end of method

	/*
	 * the method placeThings makes karel use its stored locations of the objects and puts them back to where it found them 
	 * this method inputs an array
	 * the output is a direction to travel and tells karel to go to the location of the next object
	 */
	public static void placeThings(int[][] a) 
	{
		readFile(a);//calls the readFile method
		
		turn(Direction.EAST);//calls the turn method
		karel.move();
		
		for(int i = 0; i < numThings; i++) 
		{
			xTarget = a[i][0];
			yTarget = a[i][1];
			
			goTo(xTarget, yTarget);//calls the goTo method
			
			if (karel.getAvenue() == xTarget && karel.getStreet() == yTarget) {karel.putThing();}
		}//end of for

		goTo(openingX, openingY);//calls the goTo method
		turn(Direction.WEST);//calls the turn method
		karel.move();
	}//end of method

	
	/* the readFile method uses the java scanner to read the lines of the text file and splits the string to use as coordinates in the array
	 * this method inouts an array that will be used to import all the data from the file
	 * the method will output the data from the file to each row in the array
	 */
	@SuppressWarnings("resource")
	public static void readFile(int[][] a) 
	{
		try 
		{
			Scanner sc = new Scanner(kLF); // initializes the scanner
			for (int i = 0; i < numThings; i++) 
			{
				String line = sc.nextLine(); // saves the line of the file to a variable
				a[i][0] = Integer.parseInt(line.substring(0, line.indexOf(','))); // splits and saves the name of the index
				a[i][1] = Integer.parseInt(line.substring(line.indexOf(',') + 1, line.length())); // saves the mark of the name above
			}// end of for
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}// end of catch
	}//end of readFile()
	
	/*
	 * goTo is a method that turns and moves karel to a specific coordinate however it does not check what is in front of karel
	 * the input is a coordinate
	 * the output is a direction and movement instructions
	 */
	public static void goTo(int x, int y) 
	{
		if (x >= openingX && y >= openingY) 
		{
			if (x > karel.getAvenue()) 
			{
				turn(Direction.EAST);//calls the turn method
				while(x > karel.getAvenue()) {karel.move();}
			}//end of if

			if (x < karel.getAvenue()) 
			{
				turn(Direction.WEST);//calls the turn method
				while(x < karel.getAvenue()) {karel.move();}
			}//end of if

			if (y > karel.getStreet()) 
			{
				turn(Direction.SOUTH);//calls the turn method
				while(y > karel.getStreet()) {karel.move();}
			}//end of if

			if (y < karel.getStreet()) 
			{
				turn(Direction.NORTH);//calls the turn method
				while(y < karel.getStreet()) {karel.move();}
			}//end of if
		}//end of if
	}//end of method
}//end of class