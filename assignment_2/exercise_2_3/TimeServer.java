package exercise_2_3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class TimeServer extends Thread {

	MulticastSocket socket;
	String identifier;
	int port = 3333;

	public TimeServer(String id) {
		identifier = id;
	}

	public static TimeServer createTimeServer(String id) {
		TimeServer s = new TimeServer(id);
		try {
			s.socket = new MulticastSocket(s.port);
			s.socket.joinGroup(InetAddress.getByName("224.0.0.0"));
			s.start();
			return s;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void run() {
		
	//	System.out.println("Started TS-"+identifier);
		
		byte[] rec;
		while (!socket.isClosed()) {
			try {
				rec = new byte[100];
				DatagramPacket request = new DatagramPacket(rec, rec.length);
				socket.receive(request);
				int clientPort = Integer.parseInt(new String(rec).substring(4, 8));
				
				String answer = "TS-"+identifier+"#"+new Date().getTime()+"#";
				rec = answer.getBytes();
				
				DatagramPacket response = new DatagramPacket(rec, rec.length, request.getAddress(), clientPort);
				socket.send(response);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
