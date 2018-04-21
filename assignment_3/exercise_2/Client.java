package exercise_2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

		try {

			Registry registry = LocateRegistry.getRegistry();
			Calculator stub = (Calculator) registry.lookup("Calculator");

			System.out.println(stub.getCommands());
			Scanner scn = new Scanner(System.in);

			while (true) {
				String in = scn.nextLine();

				String[] values = in.split(",");

				if (values[0].contains("add")) {
					values[0] = values[0].replace("add ", "");
					System.out.println(stub.add(toNumber(values)));

				} else if (values[0].contains("subtract")) {
					values[0] = values[0].replace("subtract ", "");
					System.out.println(stub.subtract(toNumber(values)));

				} else if (values[0].contains("multiply")) {
					values[0] = values[0].replace("multiply ", "");
					System.out.println(stub.multipyNumbers(toNumber(values)));

				}
				System.out.println(stub.getCommands());
			}

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

	}

	public static List<Integer> toNumber(String[] val) {
		List<Integer> result = new ArrayList<>();

		for (String s : val) {
			result.add(Integer.parseInt(s));
		}

		return result;
	}
}
