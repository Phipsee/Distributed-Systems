package exercise_4_2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RoomManager extends Remote {

	public void checkAvailability(int arrivalDay, int departureDay) throws RemoteException;

	public void book(int roomType, int arrivalDay, int departureDay, String guests) throws RemoteException;

	public void summary() throws RemoteException;
}
