
public class refresher {

	public static void main(String[] args) {
		int[] array = new int[10];
		int temp;

		for (int i = 0; i < array.length; i++) {
			array[i] = (int) ((Math.random() * 10) + 1);
			System.out.println(i + " : " + array[i]);
		}

		System.out.println("\n");

		for (int i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - i - 1];
			array[array.length - i - 1] = temp;
		}

		for (int i = 0; i < array.length; i++) {
			System.out.println(i + " : " + array[i]);
		}
	}
}
