package exercise_4_2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class HotelClient {

	public static void main(String[] args) {
		HotelServer h = new HotelServer();
		h.start();
		
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();

			RoomManager stub = (RoomManager) registry.lookup("RoomManager");

			Scanner scn = new Scanner(System.in);
			
			while (true) {
				String in = scn.nextLine();

				String[] cmd = in.split(" ");

				if (cmd[0].equals("checkAvailability")) {
						stub.checkAvailability(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
				} else if (cmd[0].equals("book")) {
						stub.book(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]), cmd[4]);
				} else if (cmd[0].equals("summary")) {
						stub.summary();
				}

			}
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
