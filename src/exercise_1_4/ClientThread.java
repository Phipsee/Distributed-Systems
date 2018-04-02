package exercise_1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	private ClientThread(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	@Override
	public void run() {
		String input;
		while(socket.isConnected()) {
			
			if((input = readFromInput()) != "") {
				System.out.println(input);
			}
		}
	}
	public static ClientThread getInstance(Socket socket) {
		try {
			return new ClientThread(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String readFromInput() {
		try {
			String s = in.readLine();
			return s != null ? s:"";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void writeToStream(String msg) {
			out.print(msg);
	}

}
