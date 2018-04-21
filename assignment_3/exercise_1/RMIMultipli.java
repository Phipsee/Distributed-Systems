package exercise_1;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIMultipli extends Remote {
	public int mult(int a, int b) throws RemoteException;
}
