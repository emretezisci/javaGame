
import java.util.Scanner;

public class DataValidator {

	public static int getInt(Scanner sc, String prompt) {
		boolean isValid = false;
		int i = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return i;
	}

	public static String getString(Scanner sc, String prompt) {
		String s = null;
		System.out.print(prompt);
		if (sc.hasNext()) {
			s = sc.next(); // read the first string on the line
		}
		sc.nextLine(); // discard any other data entered on the line
		return s;
	}
}