package exercise_2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Calculator extends Remote {

	public int add(List<Integer> numbers) throws RemoteException;

	public int multipyNumbers(List<Integer> numbers) throws RemoteException;

	public int subtract(List<Integer> numbers) throws RemoteException;

	public String getCommands() throws RemoteException;

}
