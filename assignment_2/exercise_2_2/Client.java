package exercise_2_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
			 socket = new Socket("192.168.1.109", port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			while (socket.isConnected()) {
				String input = in.readLine();
				System.out.println("server: " + input);
				
				if (input.startsWith("file incoming")) {
					downloadFile();
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
	
	public void downloadFile() throws IOException, InterruptedException {
		File nf = new File("recieved" + new Date().getTime());
		System.out.println( Files.copy(socket.getInputStream(), nf.toPath(),StandardCopyOption.REPLACE_EXISTING));
		System.out.println("Finished downloading file");
		System.out.println(nf.renameTo(new File(in.readLine())));
	}

}
