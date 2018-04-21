package exercise_1;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMulti {

	public static void main(String[] args) {
		try {
			
			Multiplicator m = new Multiplicator();
			UnicastRemoteObject.unexportObject(m, true);
			RMIMultipli stub = (RMIMultipli) UnicastRemoteObject.exportObject(m, 0);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Multi", stub);
			
			
			System.out.println("Server is ready");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
