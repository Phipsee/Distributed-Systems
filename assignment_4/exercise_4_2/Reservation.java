package exercise_4_2;

public class Reservation {
	
	int arrival;
	int depart;
	String guest;
	
	public Reservation(int arrival, int depart, String guest) {
		super();
		this.arrival = arrival;
		this.depart = depart;
		this.guest = guest;
	}
	public int getArrival() {
		return arrival;
	}
	public void setArrival(int arrival) {
		this.arrival = arrival;
	}
	public int getDepart() {
		return depart;
	}
	public void setDepart(int depart) {
		this.depart = depart;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	};
	
	

}
