package com.mbirr.ddemorest;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
//import javax.swing.Timer;


import com.mbirr.ddemorest.client.Myclientservlet;
public class Countdowntimer {
	public static int a=0;
	
	Timer timer;
//	timer = new Timer();
	static CustomerRepository customerrepository = new CustomerRepository();
	static List<Customer> CL=customerrepository.getList();
	public Countdowntimer() {
		
	}
	public Countdowntimer(int i, int sec) {
		timer = new Timer();
		CL.get(i).setTimer(sec);
		a=0;
		timer.schedule(new DisplayCountdown(), sec*1000);
		System.out.println(CL.get(i).getTimer());
		
	}
//	public List<Customer> setter(int i,int time){
//		CL.get(i).setTimer(time);
//		return CL;
//	}
	public static int getter(int i) {
		return CL.get(i).getTimer();
	}
//	public List<Customer> timer(int sec, int i){
//		timer = new Timer();
//		CL.get(i).setTimer(sec);
//		System.out.println(CL.get(i).getTimer());
//		timer.schedule(new DisplayCountdown(i,sec), sec*1000);
////		CL.get(i).setTimer(0);
//		System.out.println(CL.get(i).getTimer());
//		return CL;
//	}
	class DisplayCountdown extends TimerTask { 
//		int se = 10;
//		@Override
		
		public void run() {
//			for (int i=se;i>=0;i--) {
//				System.out.println(i + " " + getter(1));
//			}
			CL.get(1).setTimer(0);
			a = 1;
			System.out.println("Timer	" + CL.get(1).getTimer());
			timer.cancel(); //Terminate the timer thread
		}
		
	}
}
//public static void main(String args[]) {
//	new Countdowntimer(1,10); 
//	
//	System.out.println("Timer set to 10sec"); 
////	System.out.println(x.CL.get(1).getTimer());
////	List<Customer> q=x.timer(10, 1);
//	
////	System.out.println(q.get(1).getTimer());
//	
//	} 
//}
