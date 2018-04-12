package exercise_2_3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.List;

public class OneSecondRunner extends Thread{

	MulticastSocket ms;
	List<Long> results;
	boolean running = true;
	int max;
	
	public void stopThread() {
		running = false;
	}
	
	
	
	 public OneSecondRunner(List<Long> l, MulticastSocket m, int servers) {
		 results = l;
		 ms = m;
		 max = servers;
	}
	
	@Override
	public void run() {
		byte[] buf = new byte[100];
		DatagramPacket answer = new DatagramPacket(buf, buf.length);
		int count = 0;
		while (running && count < max) {
			try {
				ms.receive(answer);
				String time = new String(answer.getData());
				results.add(Long.parseLong(time.split("#")[1]));
				System.out.println(time);
			} catch (IOException e) {
				e.printStackTrace();
			}
			count++;
		}
	}
}
