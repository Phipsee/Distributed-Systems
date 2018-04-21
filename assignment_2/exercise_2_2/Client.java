package exercise_2_2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client {

	private Scanner scn;
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;

	public void startClient() {
		scn = new Scanner(System.in);

		System.out.println("Enter port to connect: ");
		Integer port = Integer.parseInt(scn.nextLine());

		try {
			 socket = new Socket("localhost", port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			while (socket.isConnected()) {
				String input = in.readLine();
				System.out.println("server: " + input);
				
				if (input.startsWith("file incoming")) {
					downloadFile(input);
				}
				
				writeLine();

			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void writeLine() {
		String in = scn.nextLine();
		out.println(in);
		out.flush();
	}

	public static Client getClient() {
		return new Client();
	}
	
	public void downloadFile(String s) throws IOException, InterruptedException {
		File nf = new File("recieved" + new Date().getTime());

		int size = Integer.parseInt(s.replace("file incoming ", ""));
		byte [] buffer = new byte[size];
		DataInputStream dataIn = new DataInputStream(socket.getInputStream());
		dataIn.readFully(buffer, 0, size);

		FileOutputStream fos = new FileOutputStream(nf);
		fos.write(buffer);
		fos.flush();
		fos.close();
		
		System.out.println("Finished downloading file");
		nf.renameTo(new File(in.readLine()));
	}

}
