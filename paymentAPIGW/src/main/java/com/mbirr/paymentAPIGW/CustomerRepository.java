package com.mbirr.paymentAPIGW;
//import com.mbirr.ddemorest.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
	Customer c1 = new Customer(1111,0,0);
	Customer c2 = new Customer(2222,0,0);
	Customer c3 = new Customer(3333,0,0);
	List<Customer> CL = new ArrayList<Customer>();
	//Arrays.stream(new Customer[]{c1, c2, c3});
	public CustomerRepository() {
	
	CL.add(c1);
	CL.add(c2);
	CL.add(c3);
	
	}
	public List<Customer> getList(){
		return CL;
	}
}
