package com.mbirr.ddemorest.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mbirr.paymentAPIGW.Customer;
import com.mbirr.paymentAPIGW.CustomerRepository;
import com.mbirr.paymentAPIGW.controller.APIcontroller;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class APIcontrollerTest {
	static CustomerRepository customerrepository = new CustomerRepository();
	public static List<Customer> CL=customerrepository.getList();
	static APIcontroller AC = new APIcontroller();
//	public static String expected ="";
	@Test
	void test() throws InterruptedException {
		
//		List<Customer> CL=AC.CL;
		int max=CL.size()-1;
		Random random = new Random();
		int x=random.nextInt(max);
//		int phone=CL.get(x).getPhone();
//		CL.get(1).setCouter(2);
		int phone = 1111;
		//CL.get(x).setTimer(25);
		int counter= CL.get(0).getCouter();
		int amount =1000;
		int receiver = 5555;
		String actual = AC.getstatus(phone,amount, receiver);
		String expected ="";

		
		int numberOfThreads = 1;
	    ExecutorService service = Executors.newFixedThreadPool(10);
	    CountDownLatch latch = new CountDownLatch(0);
	    for (int i = 0; i < numberOfThreads; i++) {
//	    	counter++;
	    	if(counter==0) {
	    		
				expected = "Transfer successful";
				CL.get(0).setCouter(1);
			}
			else if (counter==1) {
				expected = "Transfer successful----->Last transfer";
				CL.get(0).setCouter(2);
				}
			else if (counter==2) {
				expected = "Maximum transaction reached try again in few minutes";
				CL.get(0).setCouter(0);
			}
	        service.execute(() -> {
	        	AC.getstatus(phone,amount, receiver);
	        	
	            latch.countDown();
	            
	        });
	    }
	    latch.await();
	    System.out.println(expected + AC.getstatus());
		assertEquals(expected,AC.getstatus());
		
		
	}
	


}
