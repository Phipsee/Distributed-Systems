package exercise_2_3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

public class Exercise_2_3 {

	public static int PORT_NO = 1234;
	public static int TIME_SERVERS = 10000;

	public static void main(String[] args) {

		for (int i = 0; i < TIME_SERVERS; i++) {
			TimeServer.createTimeServer("TS-" + i);
		}

		try {
			MulticastSocket client = new MulticastSocket(PORT_NO);

			String msg = "REQ#" + PORT_NO + "#";
			DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
					InetAddress.getByName("224.0.0.0"), 3333);
			client.send(request);

			
			List<Long> times = new ArrayList<>();

			getResponses(times, client);
			
			System.out.println(times.stream().mapToLong(i -> i).sum() / TIME_SERVERS);
			System.out.println("got "+times.size()+"/"+TIME_SERVERS+" answers");
			System.exit(0);

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public static void getResponses(List<Long> results, MulticastSocket socket) {
		try {
			OneSecondRunner osr = new OneSecondRunner(results, socket, TIME_SERVERS);
			osr.start();
			osr.join(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
