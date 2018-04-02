package exercise_1_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer {

	private static int THREAD_AMOUNT = 15;
	private static int NUMBER = 100000;
	private static ConcurrentLinkedQueue<Integer> tasks;
	private static LinkedBlockingQueue<Integer[]> results;
	private static List<CalcProducer> producers;
	private static List<Thread> threads;

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		tasks = new ConcurrentLinkedQueue<>();
		fillList();
		producers = createThreads();

		startThreads();

		while (results.size() < NUMBER) {
			sleep();
		}

		printHighestNumber();
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime) / 1000000 + "ms");
	}

	@SuppressWarnings("static-access")
	private static void sleep() {
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static List<CalcProducer> createThreads() {
		List<CalcProducer> list = new ArrayList<>();
		results = new LinkedBlockingQueue<>(NUMBER);
		for (int i = 0; i < THREAD_AMOUNT; i++) {
			list.add(CalcProducer.getInstance(results, tasks));
		}
		return list;
	}

	public static void fillList() {
		for (int i = 1; i <= NUMBER; i++) {
			tasks.add(i);
		}
	}

	public static void startThreads() {
		threads = new ArrayList<>();
		for (CalcProducer c : producers) {
			Thread t = new Thread(c);
			t.start();
			threads.add(t);
		}
	}

	public static void printHighestNumber() {
		Integer[] result;
		int highestDivior = 0;
		int number = 0;
		for (int i = 0; i < NUMBER; i++) {
			result = results.poll();
			if (result[1] > highestDivior) {
				number = result[0];
				highestDivior = result[1];
			}
		}
		System.out.println("Highest Number: " + number + " with " + highestDivior + " divisors");
	}

}
