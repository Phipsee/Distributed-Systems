package exercise_5_2;

import javax.xml.ws.Endpoint;

public class CalculatorPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:1234/calc", new Calculator());
	}
}
