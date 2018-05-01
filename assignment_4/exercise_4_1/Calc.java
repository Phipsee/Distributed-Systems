package exercise_4_1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Calc extends UnicastRemoteObject implements Table {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int maxIdentifier = 1;

	private Fork[] forks;

	protected Calc(int n) throws RemoteException {

		forks = new Fork[n];

		for (int i = 0; i < n; i++) {
			forks[i] = new Fork(i);
		}

	}

	@Override
	public void takeFork(int id) throws RemoteException {
		try {
			Fork f = forks[id];
			synchronized (f) {
				if (f.taken) {
					f.wait();
				}

				f.taken = true;
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void putDownFork(int id) throws RemoteException {
		Fork f = forks[id];
		synchronized (f) {
			f.notify();
			f.taken = false;
		}

	}

}
