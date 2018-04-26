package exercise_4_2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RoomManager extends Remote {

	public String checkAvailability(int arrivalDay, int departureDay) throws RemoteException;

	public String book(int roomType, int arrivalDay, int departureDay, String guests) throws RemoteException;

	public String summary() throws RemoteException;
}
