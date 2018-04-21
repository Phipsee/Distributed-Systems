package exercise_3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringOps extends Remote {

	public String uniueReverse(String input) throws RemoteException;

	public int getId() throws RemoteException;
}
