package exercise_2_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class Server extends Thread{

	private DatagramSocket socket;
	 private byte[] buf;
	 private static Server instance;
	 private String[] oneliners;

	public Server() throws SocketException {
		socket = new DatagramSocket(1234);
		buf = new byte[256];
		oneliners = Oneliners.getOneliners();
		start();
	}
	
	
	public void listening() {
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		while(true) {
			try {
				socket.receive(packet);
				String hello = oneliners[new Random().nextInt(oneliners.length)];
				DatagramPacket answer = new DatagramPacket(hello.getBytes(), hello.getBytes().length, packet.getAddress(), packet.getPort());
				socket.send(answer);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		listening();
	}
	
	public static void startServer() {
		if(instance == null) {
			try {
				instance = new Server();
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
	}

}
