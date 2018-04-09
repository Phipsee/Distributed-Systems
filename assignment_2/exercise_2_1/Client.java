package exercise_2_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Server.startServer();

		try {
			DatagramSocket client = new DatagramSocket();
			Scanner scn = new Scanner(System.in);
			
			while (true) {
				String in = scn.nextLine();
				client.send(new DatagramPacket(in.getBytes(), in.getBytes().length, InetAddress.getLocalHost(), 1234));
				
				byte[] answerBuf = new byte[1024];
				
				DatagramPacket answer = new DatagramPacket(answerBuf, answerBuf.length);
				client.receive(answer);
				String text = new String(answer.getData());

				System.out.println(text);
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
