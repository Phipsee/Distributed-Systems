package exercise_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	public static void main(String[] args) {

			Registry registry;
			try {
				registry = LocateRegistry.getRegistry();
				StringOps stub = (StringOps) registry.lookup("StringOps");
				
				FileReader fr = new FileReader(new File("text.txt"));
				BufferedReader br = new BufferedReader(fr);
				
				
				String in;
				
				int id = stub.getId();
				
				long timeStart = System.currentTimeMillis();
				
				while((in = br.readLine()) != null) {
					System.out.println(stub.uniueReverse(in)+"\n");
					
				}
				
				System.out.println("It took: "+(System.currentTimeMillis()-timeStart)+" ms");
				
				System.out.println("Start at: "+id+", End at "+stub.getId());
				
				
			} catch (NotBoundException | IOException e) {
				e.printStackTrace();
			}

		
	}
}
