package exercise_2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

	public CalculatorImpl() throws RemoteException {
	}

	private static final long serialVersionUID = 1376639862095632134L;

	@Override
	public String getCommands() {
		return "add <number,number,...>; subtract <number,number,...>; multiply <number,number,...>";
	}

	@Override
	public int add(List<Integer> numbers) {
		return numbers.stream().mapToInt(i -> i.intValue()).sum();
	}

	@Override
	public int multipyNumbers(List<Integer> numbers) {
		return numbers.stream().mapToInt(i -> i.intValue()).reduce(1, (a, b) -> a * b);
	}

	@Override
	public int subtract(List<Integer> numbers) {
		return numbers.stream().mapToInt(i -> i.intValue()).reduce((a,b) -> a-b).getAsInt();
	}

}
