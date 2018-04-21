package exercise_1;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Multiplicator extends UnicastRemoteObject implements RMIMultipli{
	private static final long serialVersionUID = -7174261840995019755L;

	 public Multiplicator() throws RemoteException {
	}
	
	
	@Override
	public int mult(int a, int b) throws RemoteException {
		return a*b;
	}

}
