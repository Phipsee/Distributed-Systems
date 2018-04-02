package exercise_1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.1.113", 1234);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			String input;
			
			while (socket.isConnected()) {
				System.out.println(in.readLine());
				
				if ((input = stdIn.readLine()) != null) {
					out.println(input);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
