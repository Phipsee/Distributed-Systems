package client;

public class CalculatorClient {

	public static void main(String[] args) {
		CalculatorService service = new CalculatorService();
		Calculator c = service.getCalculatorPort();
		int r = 5;
		int h = 2;
		
		System.out.println(c.add(c.multiply(c.multiply(2, (int) Math.PI),5), c.multiply(c.multiply(c.multiply(2, (int) Math.PI),5), h)));
	}

}
