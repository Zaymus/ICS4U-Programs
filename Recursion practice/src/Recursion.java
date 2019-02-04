public class Recursion {

	public int square(int n) {
		if (n == 1) {
			return 1;
		} else {
			return square(n - 1) + ((n * 2) - 1);
		}
	}

	public int cube(int n) {
		if (n == 1)
			return 1;
		else
			return cube(n - 1) + (3 * square(n) - (3 * n) + 1);
	}

	public long factorial(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}
}
