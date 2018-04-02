package exercise_1_4;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			List<ClientThread> clients = new ArrayList<>();
			while (true) {
				ClientThread newClient = ClientThread.getInstance(serverSocket.accept());
				newClient.start();
				newClient.writeToStream("hello ");
				clients.add(newClient);
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
