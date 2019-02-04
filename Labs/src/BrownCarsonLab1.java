import java.text.DecimalFormat;
import java.util.Scanner;

public class BrownCarsonLab1 {
	public static DecimalFormat decimalFormat = new DecimalFormat(".#");
	public static int[] array = new int[10];
	public static String[][] dialog = new String[][] { { "1. Initialize the array", "2. Input data into the array",
			"3. Display the array", "4. Calculate the sum of the data", "5. Calculate the average of the array",
			"6. search the array for a number" } };
	public static int numPressed;
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		for (int i = 0; i < dialog[0].length; i++) {
			dialog(dialog[0][i]);
		}
		numPressed = sc.nextInt();
		switch (numPressed) {
		case 1:
			initializeArray(array);
			break;
		case 2:
			enterFromKeyboard(array);
			break;
		case 3:
			displayArray(array);
			break;
		case 4:
			sum(array);
			break;
		case 5:
			average(array);
			break;
		case 6:
			search(array);
			break;
		}
	}

	// this method fills the array with a value of -1 and the calls the main method
	public static void initializeArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = (-1);
		}
		main(null);
	}

	// this method takes what the user inputs and fills the array with those inputs
	public static void enterFromKeyboard(int[] a) {
		for (int i = 0; i < array.length; i++) {
			dialog("input a number for index at: " + i);
			a[i] = sc.nextInt();
		}
		main(null);
	}

	// this method prints out the array
	public static void displayArray(int[] a) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(a[i]);
		}
		main(null);
	}

	// this method adds up all the values in the array
	public static void sum(int[] a) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += a[i];
		}
		System.out.println(sum);
		main(null);
	}

	// this method takes the sum of the array and then divides the value by the
	// length of the array
	public static void average(int[] a) {
		int avg = 0;
		for (int i = 0; i < array.length; i++) {
			avg += a[i];
		}
		System.out.println(decimalFormat.format(avg / array.length));
		main(null);
	}

	// this method takes the value the user is looking for and compares it to the
	// values in the array
	public static void search(int[] a) {
		dialog("input a number to search.");
		int temp = sc.nextInt();
		boolean found = false;
		boolean looped = false;
		for (int i = 0; i < array.length; i++) {
			if (a[i] == temp) {
				if (looped == false) {
					System.out.print(temp + " is located at: ");
					looped = true;
				} else if (looped == true) {
					System.out.print(i + ",");
				}
				found = true;
			}
		}
		if (found == false) {
			System.out.println(temp + " was not found in the array");
			main(null);
		}
		main(null);
	}

	// this method shortens the command to print to the console
	public static void dialog(String message) {
		System.out.println(message);
	}
}
