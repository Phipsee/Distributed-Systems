package exercise_4_2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HotelServer extends Thread{

	
	@Override
	public void run() {
	try {
			
			RoomManagerImpl roomManager = new RoomManagerImpl();
			
			UnicastRemoteObject.unexportObject(roomManager, true);
			RoomManager stub = (RoomManager) UnicastRemoteObject.exportObject(roomManager, 0);
			
			Registry registry = LocateRegistry.createRegistry(3434);
			registry.rebind("RoomManager", stub);
			
			System.out.println("Server is ready");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
