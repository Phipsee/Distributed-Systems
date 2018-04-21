package exercise_3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StringOpsImpl extends UnicastRemoteObject implements StringOps {

	private static final long serialVersionUID = -8454772404046581888L;

	public static int identifier = 1;

	public StringOpsImpl() throws RemoteException {
		super();
	}

	@Override
	public String uniueReverse(String input) throws RemoteException {

		String[] line = input.split(" ");

		StringBuilder strb = new StringBuilder();
		for (int i = line.length - 1; i >= 0; i--) {
			strb.append(line[i] + " ");
		}

		return (identifier++) + ": " + strb.toString();
	}

	public int getId() throws RemoteException {
		return identifier;
	}

}
