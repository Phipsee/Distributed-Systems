package exercise_1_3;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import exercise_1_2.CalcThreadWorker;

public class CalcProducer extends CalcThreadWorker{

	private LinkedBlockingQueue<Integer[]> results;
	
	private CalcProducer(LinkedBlockingQueue<Integer[]> results, ConcurrentLinkedQueue<Integer> tasks) {
		super(tasks);
		this.results= results;
	}
	
	public void run() {
		Integer task;
		while((task = getTasks().poll()) != null) {
			results.add(new Integer[]{task, calculateAmountDivisors(task)});
		}
	}
	
	public static CalcProducer getInstance(LinkedBlockingQueue<Integer[]> results, ConcurrentLinkedQueue<Integer> tasks) {
		return new CalcProducer(results, tasks);
	}
	
}
