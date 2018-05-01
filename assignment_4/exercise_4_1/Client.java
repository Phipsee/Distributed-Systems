package exercise_4_1;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * @author johannesholzl
 *
 */
public class Client extends Thread {

	private int mealsTaken = 0;

	private int leftFork;
	private int rightFork;

	private Table stub;

	public Client(int leftFork, int rightFork) {
		super();
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	@Override
	public void run() {

		while (true) {
			try {
				dine();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initClient(String calcName) throws NotBoundException, IOException, InterruptedException {

		Registry registry = LocateRegistry.getRegistry(3434);
		stub = (Table) registry.lookup(calcName);
		
		
		start();

	}

	private void dine() throws RemoteException, InterruptedException {
		stub.takeFork(leftFork);
		stub.takeFork(rightFork);
		eat();
		stub.putDownFork(leftFork);
		stub.putDownFork(rightFork);
	}

	private void eat() throws InterruptedException {
		mealsTaken++;
		System.out.println(Thread.currentThread().getId() + ": Eating; count: " + mealsTaken);
		Thread.sleep(100);
	}

}
