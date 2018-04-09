package exercise_2_2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

public class FileClientThread extends Thread {

	private Socket socket;
	private ByteArrayOutputStream boas;
	private PrintWriter out;
	private BufferedReader in;
	private File dir;
	private String command;

	private FileClientThread(Socket socket, File dir) {
		this.socket = socket;
		this.dir = dir;
		try {
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String input;
		out.println("Commands are: \n list \n get <file>");
		while (socket.isConnected()) {
			input = readLine();
			doSomething(getCommand(input));
		}
	}

	private String readLine() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ERROR";
	}

	public static FileClientThread createClient(Socket socket, File dir) {
		FileClientThread f = new FileClientThread(socket, dir);
		f.start();
		return f;
	}

	private Command getCommand(String input) {
		for (Command c : Command.values()) {
			if (input.startsWith(c.getValue())) {
				return c;
			}
		}
		return Command.UNKNOWN;
	}

	private void doSomething(Command cmd) {
		switch (cmd) {
		case LIST:
			for (String f : dir.list()) {
				out.write(f);
			}
			break;

		case GET:
			String filename = dir.getName() + command.replace(Command.GET.value, "");
			System.out.println(filename);
			try {
				Files.copy(new File(filename).toPath(), socket.getOutputStream());
				socket.getOutputStream().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
		default:
			out.println(Command.UNKNOWN.value);
			out.flush();
			break;
		}
	}

	enum Command {
		LIST("list"), GET("get "), UNKNOWN("command not found");

		private String value;

		private Command(String s) {
			this.value = s;
		}

		public String getValue() {
			return this.value;
		}
	}

}
