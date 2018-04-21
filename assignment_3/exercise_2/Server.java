package exercise_2;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import exercise_3.StringOps;
import exercise_3.StringOpsImpl;

public class Server {

	public static void main(String[] args) {
		try {
			
			CalculatorImpl calculator = new CalculatorImpl();
			
			UnicastRemoteObject.unexportObject(calculator, true);
			Calculator stubCalc = (Calculator) UnicastRemoteObject.exportObject(calculator, 0);
			
			StringOpsImpl stringOps = new StringOpsImpl();
			
			UnicastRemoteObject.unexportObject(stringOps, true);
			StringOps stubOps = (StringOps) UnicastRemoteObject.exportObject(stringOps, 0);
			
			
			
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Calculator", stubCalc);
			registry.rebind("StringOps", stubOps);
			
			
			System.out.println("Server is ready");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
