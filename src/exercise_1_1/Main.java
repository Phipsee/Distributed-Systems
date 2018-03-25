package exercise_1_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {

	private static int THREAD_AMOUNT = 15;
	private static int NUMBER = 100000;

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		ExecutorService executor = Executors.newFixedThreadPool(THREAD_AMOUNT);
		List<CalcThread> threads  = new ArrayList<>();
		
		int partialNumber = NUMBER / THREAD_AMOUNT;

		for (int i = 0; i < THREAD_AMOUNT; i++) {
			CalcThread c = CalcThread.getInstance(partialNumber * i + 1, partialNumber * (i + 1));
			threads.add(c);
			executor.execute(c);
		}

		try {
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.MINUTES);
			printHighestNumber(threads);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long endTime = System.nanoTime();
		System.out.println((endTime - startTime) / 1000000 + "ms");
	}
	
	public static void printHighestNumber(List<CalcThread> list) {
		Collections.sort(list);
		System.out.println("Highest Number: "+list.get(list.size()-1).getNumberWithMostDivisors()+" with "+list.get(list.size()-1).getDivisors()+" divisors");
	}

}
