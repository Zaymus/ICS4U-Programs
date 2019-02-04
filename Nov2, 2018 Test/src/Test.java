/*
 * Description:
 * Author: Carson Brown
 * Date: November 2nd 2018
 * Version: V1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	static String[][] marks = new String[100][2]; // initializes array for the info to be sorted and searched
	
	static File file = new File("marks.txt"); // imports all names and marks from the file
	
	static int searchTerm = 62; //searches for this term after importing info and sorting

	public static void main(String[] args) {
		System.out.println("read file");
		readFile();//runs the read file function
		
		display(); // displays the array
		
		System.out.println("binary search");
		binarySearch(marks, searchTerm);// searches the marks array for the search term using the binary search algorithm
	}// end of main
	
	// the binarySearch function takes an array and a search term to look for. the algorithm will divide the array in half
	// by using greater than or less than statements to determine what half to look in. this function will keep doing this
	// until the number is found or it determines that the number is not in the array.
	public static void binarySearch(String[][] a, int x) {
		int min, max, mid;
		String k = "" + x; // search term
		min = 0; //min index of the array
		max = a.length - 1; // max index of the array
		mid = (a.length - 1) / 2; // middle index of the array
		boolean found = false;
		while (!found) {
			mid = (min + max) / 2; // averages the index's to find the middle index
			if (a[mid][1].equals(k)) {
				System.out.println(x + " is located at row: " + (mid + 1));
				found = true;
			} else if (a[mid][1].compareTo(k) < 0) {
				max = mid - 1;
			} else {
				min = mid + 1;
			} // end of if
			if (min > max) {
				System.out.println(k + " was not found in the array.");
				found = true;
			} // end of if
		} // end of while
	}// end of binarySearch()

	
	// the bubbleSort function takes an array and sorts the values in descending order via marks
	//this function takes an inputted array 
	//and outputs the array sorted in either ascending or descending order
	public static void bubbleSort(String[][] a) {
		int i, k;
		for (i = 1; i < a.length; i++) {
			for (k = 0; k < a.length - i; k++) {
				if (a[k][1].compareTo(a[k + 1][1]) < 0) {
					swap(k, k + 1); // runs the swap function
				}//end of if
			}//end of for
		}//end of for
	}// end of bubbleSort()
	
	// the swap function takes to index values and swaps the rows assosiated with said index's
	// the swap function takes to int variables and uses them as indexes for the rows to swap the row
	// the output is 2 rows swaped in an array
	public static void swap(int a, int b) {
		String[] temp = marks[a];
		marks[a] = marks[b];
		marks[b] = temp;
	}//end of swap
	
	// the display function prints the entire array when called
	public static void display() {
		for(int i = 0; i < marks.length; i++) {
			System.out.println(marks[i][0] + " , " + marks[i][1]);
		}//end of for
	}// end of display()
	
	// the readFile function scans each line of the file given and imports the values into the marks array
	//the function takes a file and 
	//outputs the information read from it 
	@SuppressWarnings("resource")
	public static void readFile() {
		try {
			Scanner sc = new Scanner(file); // initializes the scanner
			for (int i = 0; i < marks.length; i++) {
				String line = sc.nextLine(); // saves the line of the file to a variable
				marks[i][0] = line.substring(0, line.indexOf(',')); // splits and saves the name of the index
				marks[i][1] = line.substring(line.indexOf(',') + 1, line.length() ); // saves the mark of the name above
			}// end of for
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}// end of catch
		System.out.println("bubble sort");
		bubbleSort(marks);// runs the bubbleSort function
	}//end of readFile()
}// end of class