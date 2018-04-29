package exercise_4_1;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

/**
 * 
 * @author johannesholzl
 *
 */
public class Exercise1 {

	private static final String CALC_NAME = "TABLE";
	
	
	public static void main(String[] args) throws NotBoundException, AlreadyBoundException, IOException, InterruptedException {

		int n = 5;

		Server server = new Server(n);
		server.startServer(CALC_NAME);
		
		
		for (int i = 0; i < n; i++) {
			Client client = new Client(i, (i + 1) % n);
			client.initClient(CALC_NAME);
		}
	}

}
