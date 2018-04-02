package exercise_1_1;

import utils.DistributedUtils;

public class CalcThread implements Runnable, Comparable<CalcThread> {

	private int divisors;
	private int endNumber;
	private int startNumber;
	private int highestNumberWithMostDivisors;

	protected CalcThread() {
	}

	private CalcThread(int startNumber, int endNumber) {
		this.endNumber = endNumber;
		this.startNumber = startNumber;
	}

	public void run() {
		System.out.println("Start Thread " + Thread.currentThread().getId());
		divide();
		System.out.println("End Thread " + Thread.currentThread().getId());
	}

	private void divide() {
		int amountDivisors;
		for (int actualNumber = startNumber == 0 ? 1 : startNumber; actualNumber <= endNumber; actualNumber++) {
			amountDivisors = calculateAmountDivisors(actualNumber);
			if (amountDivisors > divisors) {
				setNumberWithMostDivisors(actualNumber);
				setDivisors(amountDivisors);

			}
		}
	}

	protected int calculateAmountDivisors(int number) {
		return DistributedUtils.getAmountDivisors(number);
	}

	public int getDivisors() {
		return divisors;
	}

	protected void setDivisors(int n) {
		divisors = n;
	}

	public int getNumberWithMostDivisors() {
		return highestNumberWithMostDivisors;
	}

	protected void setNumberWithMostDivisors(int n) {
		highestNumberWithMostDivisors = n;
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
		if (o.highestNumberWithMostDivisors == this.highestNumberWithMostDivisors) {
			return 0;
		}
		return o.highestNumberWithMostDivisors > this.highestNumberWithMostDivisors ? -1 : 1;
	}
}
