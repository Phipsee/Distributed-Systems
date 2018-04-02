package exercise_1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import utils.DistributedUtils;

public class ClientThread extends Thread {

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
		while (socket.isConnected()) {
			if ((input = readFromInput()) != null) {
				executeCommand(input);
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
			return s != null ? s : "";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void writeToStream(String msg) {
		out.println(msg);
	}

	private void executeCommand(String s) {
		if (DistributedUtils.isStringEmptyOrNull(s)) {
			writeError();
		}
		if (s.toLowerCase().contains(Commands.PERIMETER.command)) {
			Double r = parseDouble(s);
			if (r != null) {
				writeToStream("radius is " + calcCirclePerimeter(r));
			}
		} else if (s.toLowerCase().contains(Commands.PRIME_NUMBERS.command)) {
			Integer n = parseInteger(s);
			if (n == null) {
				return;
			}
			List<Integer> primes = new ArrayList<>();
			for (int i = 1; i <= n; i++) {
				if (DistributedUtils.getAmountDivisors(i) <= 2) {
					primes.add(i);
				}
			}
			writeToStream(primes.toString());
		} else if (s.toLowerCase().contains(Commands.ROOT.command)) {
			Integer n = parseInteger(s);
			if (n == null) {
				return;
			}
			writeToStream("root of " + n + " is: " + Math.sqrt(n));
		} else if (s.toLowerCase().contains(Commands.OPERATIONS.command)) {
			writeToStream("Commands are " + Commands.allToString());
		} else if (s.toLowerCase().contains(Commands.INFO.command)) {
			writeToStream("Serveraddress is " + socket.getLocalSocketAddress().toString());
		}

		else {
			writeError();
		}

	}

	private Double calcCirclePerimeter(double n) {
		return n * Math.PI;
	}

	private Double parseDouble(String s) {
		try {
			return Double.parseDouble(s.replace("radius", "").replace(" ", ""));
		} catch (NumberFormatException e) {
			writeToStream("No valid number found");
			return null;
		}
	}

	private Integer parseInteger(String s) {
		try {
			return Integer.parseInt(s.replace("prime ", "").replace("root ", "").replace(" ", ""));
		} catch (NumberFormatException e) {
			writeToStream("No valid number found");
			return null;
		}
	}

	public enum Commands {
		PERIMETER("radius "), ROOT("root "), INFO("info"), PRIME_NUMBERS("prime "), OPERATIONS("operation"),;
		private String command;

		private Commands(String s) {
			command = s;
		}

		public String getCommand() {
			return command;
		}

		public static String allToString() {
			StringBuilder stb = new StringBuilder();
			for (Commands c : Commands.values()) {
				stb.append(c.getCommand() + " ");
			}
			return stb.toString();
		}

	}

	private void writeError() {
		writeToStream("Command unknown");
	}

}
