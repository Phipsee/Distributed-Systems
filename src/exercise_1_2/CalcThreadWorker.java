package exercise_1_2;

import java.util.concurrent.ConcurrentLinkedQueue;

import exercise_1_1.CalcThread;

public class CalcThreadWorker extends CalcThread {

	private ConcurrentLinkedQueue<Integer> tasks;

	protected CalcThreadWorker(ConcurrentLinkedQueue<Integer> tasks) {
		this.tasks = tasks;
	}

	public void run() {
		Integer number;
		while ((number = tasks.poll()) != null) {
			int amountDivisors = calculateAmountDivisors(number);

			if (amountDivisors > getDivisors()) {
				setNumberWithMostDivisors(number);
				setDivisors(amountDivisors);
			}
		}
	}

	
	public static CalcThreadWorker getInstance(ConcurrentLinkedQueue<Integer> tasks) {
		return new CalcThreadWorker(tasks);
	}

	public ConcurrentLinkedQueue<Integer> getTasks() {
		return tasks;
	}
}
