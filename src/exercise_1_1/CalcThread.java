package exercise_1_1;

import java.util.HashMap;

public class CalcThread implements Runnable, Comparable<CalcThread> {

	private int divisors;
	private int highestNumberWithMostDivisors;
	private int endNumber;
	private int startNumber;
	HashMap<Integer, Integer> d;

	private CalcThread(int number) {
		this.endNumber = number;
	}

	private CalcThread(int startNumber, int endNumber) {
		this.endNumber = endNumber;
		this.startNumber = startNumber;
	}

	public void run() {
		System.out.println("Start Thread "+ Thread.currentThread().getId());
		divide();
		System.out.println("End Thread "+ Thread.currentThread().getId());
	}

	private void divide() {
		int amountDivisors = 0;
		for (int actualNumber = startNumber == 0 ? 1 : startNumber; actualNumber <= endNumber; actualNumber++) {
			for (int n = 1; n <= actualNumber; n++) {
				if (actualNumber % n == 0) {
					amountDivisors++;
				}
			}
			if (amountDivisors > divisors) {
				highestNumberWithMostDivisors = actualNumber;
				divisors = amountDivisors;
			}
			amountDivisors = 0;
		}
	}

	public int getDivisors() {
		return divisors;
	}

	public int getNumberWithMostDivisors() {
		return highestNumberWithMostDivisors;
	}

	public int getNumber() {
		return endNumber;
	}

	public static CalcThread getInstance(int number) {
		return getInstance(0, number);
	}

	public static CalcThread getInstance(int startNumber, int endNumber) {
		CalcThread c = new CalcThread(startNumber, endNumber);
		return c;
	}

	@Override
	public int compareTo(CalcThread o) {
		if(o.highestNumberWithMostDivisors == this.highestNumberWithMostDivisors) {
			return 0;
		}
		return o.highestNumberWithMostDivisors > this.highestNumberWithMostDivisors ? -1 : 1;
	}
}
