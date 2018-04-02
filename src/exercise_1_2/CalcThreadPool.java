package exercise_1_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CalcThreadPool {

	private static int THREAD_AMOUNT = 15;
	private static int NUMBER = 100000;
	private static ConcurrentLinkedQueue<Integer> tasks;
	private static List<CalcThreadWorker> threads;
	private static ExecutorService executor;

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		
		
		tasks = new ConcurrentLinkedQueue<>();
		fillList();
		threads = createThreads();
		executor = Executors.newFixedThreadPool(THREAD_AMOUNT);
		
		startThreads();
		
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

	public static List<CalcThreadWorker> createThreads() {
		List<CalcThreadWorker> list = new ArrayList<>();
		for (int i = 0; i < THREAD_AMOUNT; i++) {
			list.add(CalcThreadWorker.getInstance(tasks));
		}
		return list;
	}

	public static void fillList() {
		for (int i = 1; i <= NUMBER; i++) {
			tasks.add(i);
		}
	}
	
	public static void startThreads() {
		for(CalcThreadWorker c : threads) {
			executor.submit(c);
			
		}
		executor.shutdown();
	}

	public static void printHighestNumber(List<CalcThreadWorker> list) {
		Collections.sort(list);
		System.out.println("Highest Number: "+list.get(list.size()-1).getNumberWithMostDivisors()+" with "+list.get(list.size()-1).getDivisors()+" divisors");
	}
}
