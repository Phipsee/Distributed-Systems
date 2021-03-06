package exercise_4_2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RoomManagerImpl extends UnicastRemoteObject implements RoomManager {

	List<List<Room>> rooms;

	public RoomManagerImpl() throws RemoteException {
		rooms = new ArrayList<>();

		initRooms();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String checkAvailability(int arrivalDay, int departureDay) throws RemoteException {
		StringBuilder strb = new StringBuilder();

		for (List<Room> l : rooms) {
			for (Room r : l) {
				if (r.isAvailable(arrivalDay, departureDay)) {
					strb.append("Room: Type: " + r.getRoomType() + " Price: "
							+ r.getPrice() * (departureDay - arrivalDay) + "\n");
				}
			}
		}
		return strb.toString();
	}

	@Override
	public String book(int roomType, int arrivalDay, int departureDay, String guest) throws RemoteException {
		for (Room r : rooms.get(roomType)) {
			if (r.isAvailable(arrivalDay, departureDay)) {
				r.bookRoom(arrivalDay, departureDay, guest);
				return "room booked";
			}
		}
		return checkAvailability(arrivalDay, departureDay);
	}

	@Override
	public String summary() throws RemoteException {
		StringBuilder strb = new StringBuilder();
		
		for(List<Room> l : rooms) {
			for(Room r : l) {
					for(Reservation re : r.reservation) {
						strb.append("Roomtype: "+r.getRoomType()+": "+re.toString()+"\n"+" Price: "+(r.getPrice()*(re.depart-re.arrival)));
				}
			}
		}
		return strb.toString();
	}

	private void initRooms() {
		for (int i = 0; i < 5; i++) {
			rooms.add(new ArrayList<>());
		}

		for (int i = 0; i < 10; i++) {
			rooms.get(0).add(Room.createRoom(0, 55));
		}

		for (int i = 0; i < 20; i++) {
			rooms.get(1).add(Room.createRoom(1, 75));
		}
		for (int i = 0; i < 5; i++) {
			rooms.get(2).add(Room.createRoom(2, 90));
		}
		for (int i = 0; i < 3; i++) {
			rooms.get(3).add(Room.createRoom(3, 130));
		}
		for (int i = 0; i < 2; i++) {
			rooms.get(4).add(Room.createRoom(4, 250));
		}
	}

}
