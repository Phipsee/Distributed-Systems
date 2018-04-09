package exercise_2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Scanner scn;
	private BufferedReader in;
	private PrintWriter out;
	
	public void startClient() {	
		scn = new Scanner(System.in);
		
		System.out.println("Enter port to connect: ");
		Integer port = Integer.parseInt(scn.nextLine());
		
		try {
			Socket socket = new Socket("192.168.1.109", port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(socket.isConnected()) {
				String input = in.readLine();
				writeLine();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	private  void writeLine() {
		String in = scn.nextLine();
		out.println(in);
	}
	
	public static Client getClient() {
		return new Client();
	}

}
