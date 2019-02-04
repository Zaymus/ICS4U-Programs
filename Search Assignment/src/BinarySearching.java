
/* sorts a 2D array in ascending order by using a selection algorithm based on a selected search criteria by the user
 * Carson Brown
 * October 1 2018
 */
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BinarySearching {

	public static boolean search = true;
	public static float time;// used to keep track of the amount of time taken to sort the array
	public static int timeSent;// used to subtract from the current time to solve how long the sorting took
	public static int enter = 0;
	public static int Students = 6; // creates the amount of rows at the start of the program
	public static boolean numeric = true; // used to determine if a value is numeric or not
	public static DecimalFormat decimalFormat = new DecimalFormat(".##"); // formats a float to 2 decimal places
	public static String[][] studentInfo = new String[Students][4]; // declares the 2D array with x amunt of rows and 4
																	// colomns
	public static float[] studentMarks = new float[Students];// declares a 1D array to store student marks
	public static String[][] test = new String[][] { { "number", "first", "last", "90" }, // declares a full testing
																							// array
			{ "number", "first", "last", "85" }, { "number", "first", "last", "80" },
			{ "number", "first", "last", "75" }, { "number", "first", "last", "70" },
			{ "number", "first", "last", "65" } };
	public static Scanner sc = new Scanner(System.in);// declares the scanner
	public static int intSelected;// used for user inputs
	public static String input; // used for user inputs
	public static int temp; // used for swapping

	public static void main(String[] args) {
		for (int i = 0; i < studentInfo.length; i++) {
			for (int k = 0; k < studentInfo[i].length; k++) {
				studentInfo[i][k] = "";
			} // end of for loop
		} // end of for loop

		String letter = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < studentInfo.length; i++) {
			for (int k = 0; k < studentInfo[i].length; k++) {
				if (k == 0) {
					for (int j = 0; j < 10; j++) {
						studentInfo[i][0] += "" + ((int) (Math.random() * 9) + 1);
					} // end of for loop
				} else if (k == 3) {
					for (int j = 0; j < 2; j++) {
						studentInfo[i][3] += "" + ((int) (Math.random() * 9) + 1);
					} // end of for loop
					decimalFormat.format(Float.parseFloat(studentInfo[i][3]));

				} else {
					for (int l = 0; l < 8; l++) {
						studentInfo[i][k] += letter.charAt((int) (Math.random() * letter.length()) + 0);
					} // end of for loop
				} // end of if statements
			} // end of for loop
		} // end of for loop
		while (true) {
			try {
				dialog("1. Enter student Information\n" + "2. Display all student information\n"
						+ "3. Find the highest mark\n" + "4. Find the lowest mark\n" + "5. Display class average\n"
						+ "6. Edit the amount of students\n" + "7. Sort by first name\n" + "8. Sort by last name\n"
						+ "9. Sort by mark\n" + "10. Search");
				Integer num = Integer.parseInt(sc.next());
				if (num >= 1 && num <= 10) {
					switch (num) {
					case 1:
						enterInfo(studentInfo);
						break;
					case 2:
						displayAll(studentInfo);
						break;
					case 3:
						highestMark(studentMarks);
						break;
					case 4:
						lowestMark(studentMarks);
						break;
					case 5:
						displayAvg(studentMarks);
						break;
					case 6:
						dialog("how large would you like the array to be?(currently: " + "(" + Students + " rows) ("
								+ Students + " col" + ")");
						try {
							Students = sc.nextInt();
							ResizeArray(Students, Students);
						} catch (InputMismatchException e) {
							dialog("Input was not a number");
						} // end of catch statement
						break;
					case 7:
						selectionSort(studentInfo, 1);
						timeSent = (int) System.currentTimeMillis();
						break;
					case 8:
						selectionSort(studentInfo, 2);
						timeSent = (int) System.currentTimeMillis();
						break;
					case 9:
						selectionSort(studentInfo, 3);
						timeSent = (int) System.currentTimeMillis();
						break;
					case 10:
						while (search) {
							try {
								try {
									dialog("What would you like to search for?\n " + "1. Student Number\n "
											+ "2. First Name\n " + "3. Last Name\n " + "4. Mark");
									intSelected = sc.nextInt();
									selectionSort(studentInfo, intSelected - 1);
									binarySearch(studentInfo, intSelected - 1);
									timeSent = (int) System.currentTimeMillis();
									search = false;
								} catch (ArrayIndexOutOfBoundsException e) {
									dialog("input a valid selection ");
								} // end of catch
							} catch (InputMismatchException e) {
								dialog("input a number selection");
							} // end of catch
						} // end of while
					}// end of switch
				} else {
					dialog("Select a number form the menu.");
				} // end of if statements
			} catch (NumberFormatException e) {
				dialog("Input a valid number selection");
			} // end of catch
		} // end of while
	}// end of main

	public static void binarySearch(String[][] a, int col) {
		int min, max, mid;
		String k = "";
		min = 0;
		max = a.length - 1;
		mid = (a.length - 1) / 2;
		dialog("Enter a search term");
		try {
			k = sc.next();
		} catch (InputMismatchException e) {
			dialog("enter a proper search term");
		} // end of catch
		boolean found = false;
		while (!found) {
			mid = (min + max) / 2;

			if (a[mid][col].equals(k)) {
				dialog(k + " is located at row: " + (mid + 1));
				found = true;
			} else if (a[mid][col].compareTo(k) > 0) {
				max = mid - 1;
			} else {
				min = mid + 1;
			} // end of if
		} // end of while
		if (min > max) {
			dialog(k + " was not found in the array.");
		} // end of if
	}// end of binarySearch

	public static void swap(int a, int b) {
		String[] temp = studentInfo[a];
		studentInfo[a] = studentInfo[b];
		studentInfo[b] = temp;
	}// end of swap

	public static void selectionSort(String[][] a, int col) {
		int i, k, min;
		for (i = 0; i < a.length; i++) {
			min = i;
			for (k = i + 1; k < a.length; k++) {
				if (a[k][col].compareTo(a[min][col]) < 0) {
					min = k;
				} // end of if
			} // end of for
			if (min != i) {
				swap(min, i);
			} // end of if
		} // end of for
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}// end of markSelectSort

	// this method finds an open row to input data and the inputs what the user
	// inputs.
	public static void enterInfo(String[][] a) {
		try {
			for (int i = 0; a[i][0] != "-"; i++) {
				if (a[i][0] != "-") {
					temp = i;
				} // end of if
			} // end of for
		} catch (ArrayIndexOutOfBoundsException e) {
			dialog("No available space for another student.");
		} // end of catch
		switch (enter) {
		case 0:
			dialog("Please input a student number.");
			try {
				input = sc.next();
				Integer num = Integer.parseInt(input);
				a[temp][0] = Integer.toString(num);
				if (input.length() > 10) {
					dialog("name is longer than 10 characters");
				} else {
					enter++;
				} // end of else
			} catch (NumberFormatException e) {
				dialog("Input was not a number");
			} // end of catch
			enterInfo(studentInfo);
			break;
		case 1:
			dialog("Please input a student's first name.");
			input = sc.next();
			if (input.contains("`1234567890-=~!@#$%^&*()_+,.//;'[]\\<>?:{}|")) {
				dialog("please input a name without special characters or numbers");
			} else {
				a[temp][1] = input;
				enter++;
			} // end of if
			enterInfo(studentInfo);
			break;
		case 2:
			dialog("Please input a student's last name.");
			input = sc.next();
			if (input.contains("`1234567890-=~!@#$%^&*()_+,.//;'[]\\<>?:{}|")) {
				dialog("please input a name without special characters or numbers");
			} else {
				a[temp][2] = input;
				enter++;
			} // end of if
			enterInfo(studentInfo);
			break;
		case 3:
			dialog("Please input a student's mark.");
			try {
				input = sc.next();
				Integer num = Integer.parseInt(input);
				a[temp][3] = Integer.toString(num);
				if (Integer.parseInt(input) > 100) {
					dialog("input was not a valid percentage");
				} else {
					enter++;
				} // end of if
			} catch (NumberFormatException e) {
				dialog("Input was not a number");
			} // end of catch
			enterInfo(studentInfo);
			break;
		case 4:
			dialog("Press 1 to return to the menu or press 2 to input another student");
			try {
				Integer num = Integer.parseInt(sc.next());
				if (num == 1) {
					enter = 0;
				} else if (num == 2) {
					enter = 0;
					enterInfo(studentInfo);
				} // end of if
			} catch (NumberFormatException e) {
				dialog("Input was not a number");
				enterInfo(studentInfo);
			} // end of catch
			break;
		}// end of switch
	}// end of enterinfo

	// this method formats and displays the array to the user
	public static void displayAll(String[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[i].length; k++) {
				System.out.print(a[i][k] + "\t");
				if (k == 3) {
					dialog("");
				} // end of if
			} // end of for
		} // end of for
	}// end of displayAll

	// this method displays the highest mark that has been inputed
	public static void highestMark(float[] a) {
		float temp = a[0];
		int i, k;
		for (i = 0; i < a.length; i++) {
			studentMarks[i] = Float.parseFloat(studentInfo[i][3]);
		} // end of for
		for (k = 1; i < studentMarks.length; i++) {
			if (a[k] > temp) {
				temp = a[k];
			} // end of if
		} // end of for
		dialog("The highest mark is " + temp + "%");
	}// end of highestMark

	// this method diplays the lowest mark that has been inputed
	public static void lowestMark(float[] a) {
		float temp = a[0];
		int i, k;
		for (i = 0; i < a.length; i++) {
			studentMarks[i] = Float.parseFloat(studentInfo[i][3]);
		} // end of for
		for (k = 1; i < studentMarks.length; i++) {
			if (a[k] > temp) {
				temp = a[k];
			} // end of if
		} // end of for
		dialog("The lowest mark is " + studentMarks[0]);
	}// end of lowestMark

	// this method averages the inputed marks and displays the answer
	public static void displayAvg(float[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			studentMarks[i] = Float.parseFloat(studentInfo[i][3]);
		} // end of for
		for (int k = 0; k < a.length; k++) {
			sum += studentMarks[k];
		} // end of for
		System.out.println("the average mark is: " + decimalFormat.format(sum / studentMarks.length));
	}// end of displayAvg

	private static void ResizeArray(int row, int col) {
		String a[][] = new String[row][col];
		studentInfo = (String[][]) resizeArray(a, row);
		for (int i = 0; i < studentInfo.length; i++) {
			if (studentInfo[i] == null)
				studentInfo[i] = new String[col];
			else
				studentInfo[i] = (String[]) resizeArray(a[i], col);
		} // end of for
	}// end of ResizeArray

	private static Object resizeArray(Object oldArray, int newSize) {
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		@SuppressWarnings("rawtypes")
		Class elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
		int preserveLength = Math.min(oldSize, newSize);
		if (preserveLength > 0) {
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		} // end of if
		return preserveLength;
	}// end of resizeArray

	// this method shortens te command to print to the console
	public static void dialog(String Message) {
		System.out.println(Message);
	}// end of dialog
}// end of BinarySearching