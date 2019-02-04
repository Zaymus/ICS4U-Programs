import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BrownCarsonLab2 {
	public static int enter = 0;
	public static int Students = 6;
	public static boolean numeric = true;
	public static DecimalFormat decimalFormat = new DecimalFormat(".##");
	public static String[][] studentInfo = new String[Students][4];
	public static float[] studentMarks = new float[Students];
	public static String[][] test = new String[][] { 
			{ "number", "first", "last", "90" },
			{ "number", "first", "last", "85" }, 
			{ "number", "first", "last", "80" },
			{ "number", "first", "last", "75" }, 
			{ "number", "first", "last", "70" },
			{ "number", "first", "last", "65" } 
			};
	public static Scanner sc = new Scanner(System.in);
	public static int intSelected;
	public static String input;
	public static int temp;

	public static void main(String[] args) {
		for (int i = 0; i < studentInfo.length; i++) {
			for (int k = 0; k < studentInfo[i].length; k++) {
				if (k == 3) {
					studentInfo[i][k] = "0";
				} else {
					studentInfo[i][k] = "-";
				}
			}
		} 
		while (true) {
			try {
				dialog("1. Enter student Information\n" + "2. Display all student information\n"
						+ "3. Find the highest mark\n" + "4. Find the lowest mark\n" + "5. Display class average\n"
						+ "6. Edit the amount of students");
				Integer num = Integer.parseInt(sc.next());
				if (num >= 1 && num <= 6) {
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
						dialog("how large would you like the array to be?(currently: " + Students + " rows)");
						try {
							Students = sc.nextInt();	
							resizeArray(studentInfo, Students);
							resizeArray(studentMarks, Students);
						}catch(InputMismatchException e) {
							dialog("Input was not a number");
						}
						break;
					}
				} else {
					dialog("Select a number form the menu.");
				}
			} catch (NumberFormatException e) {
				dialog("Input a valid number selection");
			}
		}
	}

	// this method finds an open row to input data and the inputs what the user
	// inputs.
	public static void enterInfo(String[][] a) {
		for (int i = 0; a[i][0] != "-"; i++) {
			if (a[temp][0] != "-") {
			temp = i;
			}
		}
		switch(enter) {
		case 0:
			dialog("Please input a student number.");
			try {
				input = sc.next();
				Integer num = Integer.parseInt(input);
				a[temp][0] = Integer.toString(num);
				if (input.length() > 10) {
					dialog("name is longer than 10 characters");
				}else {
					enter++;
				}
			} catch (NumberFormatException e) {
				dialog("Input was not a number");
			}
			enterInfo(studentInfo);
			break;
		case 1:
			dialog("Please input a student's first name.");
			input = sc.next();
			if (input.contains("`1234567890-=~!@#$%^&*()_+,.//;'[]\\<>?:{}|")) {
				dialog("please input a name without special characters or numbers");
			}else {
				a[temp][1] = input;
				enter++;
			}
			enterInfo(studentInfo);
			break;
		case 2:
			dialog("Please input a student's last name.");
			input = sc.next();
			if (input.contains("`1234567890-=~!@#$%^&*()_+,.//;'[]\\<>?:{}|")) {
				dialog("please input a name without special characters or numbers");
			}else {
				a[temp][2] = input;
				enter++;
			}
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
				}else {
					enter++;
				}
			} catch (NumberFormatException e) {
				dialog("Input was not a number");
			}
			enterInfo(studentInfo);
			break;
		case 4:
			dialog("Press 1 to return to the menu or press 2 to input another student");
			try {
				Integer num = Integer.parseInt(sc.next());
				if (num == 1) {
					enter = 0;
				}else if(num == 2) {
					enter = 0;
					enterInfo(studentInfo);
				}
			} catch (NumberFormatException e) {
				dialog("Input was not a number");
				enterInfo(studentInfo);
			}
			break;
		}
	}
	// this method formats and displays the array to the user
	public static void displayAll(String[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[i].length; k++) {
				System.out.print(a[i][k] + "\t");
				if (k == 3) {
					dialog("");
				}
			}
		}
	}
	
	// this method displays the highest mark that has been inputed
	public static void highestMark(float[] a) {
		float temp = a[0];
		int i,k;
		for (i = 0; i < a.length; i++) {
			studentMarks[i] = Float.parseFloat(studentInfo[i][3]);
		}
		for(k = 1; i < studentMarks.length;i++) {
			if (a[k] > temp) {
				temp = a[k];
			}
		}
		dialog("The highest mark is " + temp + "%");
	}
	
	// this method diplays the lowest mark that has been inputed
	public static void lowestMark(float[] a) {
		float temp = a[0];
		int i,k;
		for (i = 0; i < a.length; i++) {
			studentMarks[i] = Float.parseFloat(studentInfo[i][3]);
		}
		for(k = 1; i < studentMarks.length;i++) {
			if (a[k] > temp) {
				temp = a[k];
			}
		}
		dialog("The lowest mark is " + studentMarks[0]);
	}
	
	// this method averages the inputed marks and displays the answer
	public static void displayAvg(float[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			studentMarks[i] = Float.parseFloat(studentInfo[i][3]);
		}
		for (int k = 0; k < a.length; k++) {
			sum += studentMarks[k];
		}
		System.out.println("the average mark is: " + decimalFormat.format(sum / studentMarks.length));
	}

	 @SuppressWarnings("rawtypes")
	private static Object resizeArray (Object oldArray, int newSize) {
		    int oldSize = java.lang.reflect.Array.getLength(oldArray);
		    Class elementType = oldArray.getClass().getComponentType();
		    Object newArray = java.lang.reflect.Array.newInstance(elementType,newSize);
		    int preserveLength = Math.min(oldSize,newSize);
		    if (preserveLength > 0) {
		      System.arraycopy (oldArray,0,newArray,0,preserveLength);
		    }
			return preserveLength;
	 }

	// this method shortens te command to print to the console
	public static void dialog(String Message) {
		System.out.println(Message);
	}
}