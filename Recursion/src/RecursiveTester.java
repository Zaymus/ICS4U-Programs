import java.util.Scanner;

public class RecursiveTester {

	static Recursive r = new Recursive();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println(" 1. square\n 2. cube\n 3. factorial\n ");
			int input = sc.nextInt();

			switch (input) {
			case 1:
				System.out.println("select a number to be squared");
				int result = r.square(input = sc.nextInt());
				System.out.println("Square(" + input + ") is " + result + "\n\n");
				break;

			case 2:
				System.out.println("select a number to be cubed");
				int result2 = r.cube(input = sc.nextInt());
				System.out.println("Cube(" + input + ") is " + result2 + "\n\n");
				break;

			case 3:
				long result3 = r.factorial(input = sc.nextInt());
				System.out.println("Factorial(" + input + ") is " + result3 + "\n\n");
				break;
			}
		}
	}
}