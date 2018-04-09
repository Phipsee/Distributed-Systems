package exercise_2_2;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileServer extends Thread {

	private ServerSocket socket;
	private static FileServer instance;
	private Scanner in;
	private List<FileClientThread> client;
	
	@Override
	public void run() {
		try {
			socket = new ServerSocket(readPort());
			File directory = readDirectoryToUse();
			
			while (true) {
				client.add(FileClientThread.createClient(socket.accept(), directory));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FileServer() {
		in = new Scanner(System.in);
		client = new ArrayList<>();	
	}

	public static FileServer startServer() {
		if (instance == null) {
			instance = new FileServer();
			instance.start();
		}
		return instance;
	}


	private Integer readPort() {
		System.out.println("Enter port to use: ");
		String input = in.nextLine();
		int port;
		try {
			port = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("not a valid number");
			return null;
		}
		return port;
	}

	private File readDirectoryToUse() {
		System.out.println("Enter directory to use: ");
		String input = in.nextLine();
		File f = new File(input);
		if (f.exists() && f.isDirectory()) {
			return f;
		}
		System.out.println("not a valid directory");
		return null;
	}

	
}
