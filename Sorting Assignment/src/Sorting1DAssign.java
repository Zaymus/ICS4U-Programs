import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sorting1DAssign {
	public static boolean sorted = false;
	public static String input;
	public static float time;
	public static int timeSent;
	public static int size = 100000;
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
			}
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
						search(array);
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
					}
				} else {
					dialog("Please select an option from the menu");
				}
			} catch (NumberFormatException e) {
				dialog("Input a valid number selection");
			}
		}
	}

	// this method fills the array with a value of -1 and the calls the main method
	public static void initializeArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 1000) + 1;
		}
	}

	// this method prints out the array
	public static void displayArray(int[] a) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(a[i]);
		}
	}

	// this method adds up all the values in the array
	public static void sum(int[] a) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += a[i];
		}
		System.out.println(sum);
	}

	// this method takes the sum of the array and then divides the value by the
	// length of the array
	public static void average(int[] a) {
		int avg = 0;
		for (int i = 0; i < array.length; i++) {
			avg += a[i];
		}
		System.out.println(decimalFormat.format(avg / array.length));
	}

	// this method takes the value the user is looking for and compares it to the
	// values in the array
	public static void search(int[] a) {
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
					}
					found = true;
				}
			}
			if (found == false) {
				System.out.println(temp + " was not found in the array");
			}
		} catch (InputMismatchException e) {
			dialog("Input was not a number");
			search(array);
		}
	}

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
				}
			}
		}
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}

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
				}
			}
		}
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}

	// this method uses a selection sort algorithm to sort the array from smallest
	// to largest.
	public static void ascendSelectSort(int[] a) {
		int i, k, min, temp;
		for (i = 0; i < a.length; i++) {
			min = i;
			for (k = i + 1; k < a.length; k++) {
				if (a[k] < a[min]) {
					min = k;
				}
			}
			if (min != i) {
				temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
		}
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}

	// this method uses a selection sort algorithm to sort the array from largest to
	// smallest.
	public static void descendSelectSort(int[] a) {
		int i, k, max, temp;
		for (i = 0; i < a.length; i++) {
			max = i;
			for (k = i + 1; k < a.length; k++) {
				if (a[k] > a[max]) {
					max = k;
				}
			}
			if (max != i) {
				temp = a[i];
				a[i] = a[max];
				a[max] = temp;
			}

		}
		time = (int) System.currentTimeMillis() - timeSent;
		dialog("Sorting took: " + time + " Ms");
	}

	// this method shortens the command to print to the console
	public static void dialog(String message) {
		System.out.println(message);
	}

}
