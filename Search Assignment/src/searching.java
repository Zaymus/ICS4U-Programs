
/*sorts and searches for a term provided by the user
 * Carson Brown
 * september 30
 */
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class searching {
	public static boolean sorted = false;
	public static String input;
	public static float time;
	public static int timeSent;
	public static int size = 10;
	public static DecimalFormat decimalFormat = new DecimalFormat(".#");
	public static DecimalFormat timeFormat = new DecimalFormat(".##");
	public static int[] array = new int[size];
	public static String[][] dialog = new String[][] { { "1. Initialize the array", "2. Display the array",
			"3. Calculate the sum of the data", "4. Calculate the average of the array",
			"5. search the array for a number", "6. Sort_Ascending (Bubble)", "7. Sort_Descending (Bubble)",
			"8. Sort_Ascending (Selection)", "9. Sort_Descending (Selection)" } };
	public static int numPressed;
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			for (int i = 0; i < dialog[0].length; i++) {
				dialog(dialog[0][i]);
			} // end cof for
			try {
				input = sc.next();
				Integer num = Integer.parseInt(input);
				if (num >= 1 && num <= 10) {
					switch (num) {
					case 1:
						initializeArray(array);
						break;
					case 2:
						displayArray(array);
						break;
					case 3:
						sum(array);
						break;
					case 4:
						average(array);
						break;
					case 5:
						boolean loop = true;
						while (loop) {
							dialog("Would you like to use linear or binary searching?");
							input = sc.next();
							if (input.equalsIgnoreCase("LINEAR")) {
								timeSent = (int) System.currentTimeMillis();
								linearSearching(array);
								loop = false;
							} else if (input.equalsIgnoreCase("BINARY")) {
								timeSent = (int) System.currentTimeMillis();
								binarySearching(array);
								loop = false;
							} else {
								dialog("select linear or binary searching");
							} // end of if
						} // end of while
						break;
					case 6:
						timeSent = (int) System.currentTimeMillis();
						ascendBubbleSort(array);
						break;
					case 7:
						timeSent = (int) System.currentTimeMillis();
						descendBubbleSort(array);
						break;
					case 8:
						timeSent = (int) System.currentTimeMillis();
						ascendSelectSort(array);
						break;
					case 9:
						timeSent = (int) System.currentTimeMillis();
						descendSelectSort(array);
						break;
					}// end of switch
				} else {
					dialog("Please select an option from the menu");
				}
			} catch (NumberFormatException e) {
				dialog("Input a valid number selection");
			} // end of catch
		} // end of while
	}// end of main

	// this method fills the array with a value of -1 and the calls the main method
	public static void initializeArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 1000) + 1;
		} // end of main
	}// end of initializeArray

	// this method prints out the array
	public static void displayArray(int[] a) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(a[i]);
		} // end of for
	}// end of displayArray

	// this method adds up all the values in the array
	public static void sum(int[] a) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += a[i];
		} // end of for
		System.out.println(sum);
	}// end of sum

	// this method takes the sum of the array and then divides the value by the
	// length of the array
	public static void average(int[] a) {
		int avg = 0;
		for (int i = 0; i < array.length; i++) {
			avg += a[i];
		} // end of for
		System.out.println(decimalFormat.format(avg / array.length));
	}// end of average

	// this method takes the value the user is looking for and compares it to the
	// values in the array
	public static void linearSearching(int[] a) {
		dialog("input a number to search.");
		try {
			Integer temp = Integer.parseInt(sc.next());
			boolean found = false;
			boolean looped = false;
			for (int i = 0; i < array.length; i++) {
				if (a[i] == temp) {
					if (looped == false) {
						dialog(temp + " is located at: " + i + ",");
						looped = true;
					} else if (looped == true) {
						System.out.print(i + ",");
					} // end of if
					found = true;
				} // end of if
			} // end of for
			if (found == false) {
				System.out.println(temp + " was not found in the array");
			} // end of if
		} catch (InputMismatchException e) {
			dialog("Input was not a number");
			linearSearching(array);
		} // end of catch
	}// end of linearSearching

	public static void binarySearching(int[] a) {
		int k, min, max, mid;
		min = 0;
		max = a.length - 1;
		mid = (a.length - 1) / 2;
		ascendBubbleSort(array);
		dialog("Enter a number to search for");
		k = sc.nextInt();
		boolean found = false;
		while (!found) {
			mid = (min + max) / 2;
			dialog("" + mid);
			if (a[mid] == k) {
				dialog(k + " is located at index: " + mid);
				found = true;
			} else if (a[mid] > k) {
				max = mid - 1;
				dialog("" + max);
			} else {
				min = mid + 1;
				dialog("" + min);
			} // end of if
		} // end of while
		if (min > max) {
			dialog(k + " was not found in the array.");
		} // end of if
	}// end of binarySearching

	// this method uses a bubble sort algorithm to sort the array from smallest to
	// largest.
	public static void ascendBubbleSort(int[] a) {
		int i, k, temp;
		for (i = 1; i < a.length; i++) {
			for (k = 0; k < a.length - i; k++) {
				if (a[k] > a[k + 1]) {
					temp = a[k];
					a[k] = a[k + 1];
					a[k + 1] = temp;
				} // end of if
			} // end of for
		} // end of for
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}// end of ascendBubbleSort

	// this method uses a bubble sort algorithm to sort the array from largest to
	// smallest.
	public static void descendBubbleSort(int[] a) {
		int i, k, temp;
		for (i = 1; i < a.length; i++) {
			for (k = 0; k < a.length - i; k++) {
				if (a[k] < a[k + 1]) {
					temp = a[k];
					a[k] = a[k + 1];
					a[k + 1] = temp;
				} // end of if
			} // end of for
		} // end of for
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}// end of descendBubbleSort

	// this method uses a selection sort algorithm to sort the array from smallest
	// to largest.
	public static void ascendSelectSort(int[] a) {
		int i, k, min, temp;
		for (i = 0; i < a.length; i++) {
			min = i;
			for (k = i + 1; k < a.length; k++) {
				if (a[k] < a[min]) {
					min = k;
				} // end of if
			} // end of for
			if (min != i) {
				temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			} // end of if
		} // end of for
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}// end of ascendSelectSort

	// this method uses a selection sort algorithm to sort the array from largest to
	// smallest.
	public static void descendSelectSort(int[] a) {
		int i, k, max, temp;
		for (i = 0; i < a.length; i++) {
			max = i;
			for (k = i + 1; k < a.length; k++) {
				if (a[k] > a[max]) {
					max = k;
				} // end of if
			} // end of for
			if (max != i) {
				temp = a[i];
				a[i] = a[max];
				a[max] = temp;
			} // end of if
		} // end of for
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}// end of descendSelectSort

	// this method shortens the command to print to the console
	public static void dialog(String message) {
		System.out.println(message);
	}// end of dialog
}// end of searching