package exercise_4_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Table extends Remote {

	public void takeFork(int id) throws RemoteException;

	public void putDownFork(int id) throws RemoteException;

}
