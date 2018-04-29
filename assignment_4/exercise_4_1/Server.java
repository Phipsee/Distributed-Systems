package exercise_4_1;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	private int n;
	
	public Server(int n) {
		super();
		this.n = n;
	}


	public void startServer(String calcName) throws RemoteException, NotBoundException, AlreadyBoundException {
		// RMIMul stub = (RMIMul) UnicastRemoteObject.exportObject(m, 0);

		Registry registry = LocateRegistry.createRegistry(3434);

		boolean found = false;
		for (String name : registry.list()) {
			if (name.equals(calcName)) {
				found = true;
				break;
			}
		}

		if (found) {
			registry.unbind(calcName);
		}

		registry.bind(calcName, new Calc(n));
	}

}
