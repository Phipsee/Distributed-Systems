package exercise_1;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMulti {
	public static void main(String [] args) {
		
		
		try {
			
			Registry registry = LocateRegistry.getRegistry();
			RMIMultipli stub = (RMIMultipli) registry.lookup("Multi");
			
			System.out.println("Result: "+stub.mult(3, 5));
			
			
		} catch ( RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
	}
}
