package exercise_4_2;

import java.util.ArrayList;
import java.util.List;

public class Room {

	private int roomType;
	private int price;

	List<Reservation> reservation;

	private Room(int roomType, int roomPrice) {
		this.roomType = roomType;
		this.price = roomPrice;
		reservation = new ArrayList<>();
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	public int getRoomType() {
		return roomType;
	}

	public int getPrice() {
		return price;
	}

	public static Room createRoom(int roomType, int roomPrice) {
		return new Room(roomType, roomPrice);
	}
	
	public boolean bookRoom(int arrival, int depart, String guest) {
		reservation.add(new Reservation(arrival, depart, guest));
		return true;
	}

	public boolean isAvailable(int arrival, int depart) {
		for(Reservation r : reservation) {
			if(arrival <= r.depart && arrival >= r.arrival) {
				return false;
			}
			if(depart <= r.depart && depart >= r.arrival) {
				return false;
			}
		}
		return true;
	}

}
