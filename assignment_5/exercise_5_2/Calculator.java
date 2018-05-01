package exercise_5_2;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class Calculator {

    @WebMethod
    public int add(int a, int b){
        return a+b;
    }
    
    @WebMethod
    public int multiply(int a, int b){
        return a*b;
    }
    
    @WebMethod
    public int divide(int a, int b){
        return a/b;
    }
    
    @WebMethod
    public int subtract(int a, int b){
        return a-b;
    }
}