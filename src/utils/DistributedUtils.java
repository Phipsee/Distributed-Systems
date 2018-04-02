package utils;

public class DistributedUtils {

	public static boolean isStringEmptyOrNull(String s) {
		if (s == null) {
			return true;
		}
		if (s.length() == 0) {
			return true;
		}
		return false;
	}

	public static int getAmountDivisors(int number) {
		int amountDivisors = 0;
		for (int n = 1; n <= number; n++) {
			if (number % n == 0) {
				amountDivisors++;
			}
		}
		return amountDivisors;
	}
}
