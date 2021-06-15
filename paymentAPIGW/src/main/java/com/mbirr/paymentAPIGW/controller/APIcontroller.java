package com.mbirr.paymentAPIGW.controller;

import java.io.PrintWriter;
import java.util.List;

import com.mbirr.paymentAPIGW.Countdowntimer;
import com.mbirr.paymentAPIGW.Customer;
import com.mbirr.paymentAPIGW.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;


//import com.mbirr.ddemorest.client.Myclientservlet;

public class APIcontroller {
	static CustomerRepository customerrepository = new CustomerRepository();
	public static List<Customer> CL=customerrepository.getList();
	private String status = "";
	/**
	 * 
	 * @param phone
	 * @param amount
	 * @param receiver
	 * This method returns the status of payment requested
	 * @return
	 */
	public String getstatus (int phone, int amount,int receiver){

		for(int i=0; i<CL.size(); i++) {
			int phoner=CL.get(i).getPhone();

			if (phoner==phone) {
				int countr=CL.get(i).getCouter();
				int timerr=CL.get(i).getTimer();
				int h=0;

				System.out.println("Counter " + countr);
				System.out.println("Timer  " + timerr);
				if(timerr==0) {
					
					if(countr<2) {
						if(countr==0) {
							countr=countr+1;
							CL.get(i).setCouter(countr);
							status = "Transfer successful";
						}
						else if (countr==1) {
							countr=countr+1;
							CL.get(i).setCouter(countr);
							status = "Transfer successful----->Last transfer";
							CL.get(i).setTimer(25);
							new Countdowntimer(i,25);
							
							System.out.println("Now " + Countdowntimer.getter(i));
						}

						System.out.println(CL.get(i).getCouter());
						break;
					 }
					
				}
				
				else if(timerr!=0) {
					h=Countdowntimer.a;
					if (h==0) {
					timerr=CL.get(i).getTimer();
					status = "Maximum transaction reached try again in few minutes";
					}
					else if(h==1) {
						h=0;
						System.out.println(Countdowntimer.a);
						CL.get(i).setTimer(0);
						CL.get(i).setCouter(1);
						status = "Transfer successful";
						timerr=CL.get(i).getTimer();
						System.out.println(CL.get(i).getTimer());
						Countdowntimer.a=0;						
						break;
					}
					h=0;
					Countdowntimer.a=0;
					break;
					
				}
				
				System.out.println(CL.get(i).getTimer());	
				System.out.println(CL.get(i).getCouter());	
			}
			
		}
	return status;
	
	}
	public String getstatus() {
		return status;
		
	}

}
