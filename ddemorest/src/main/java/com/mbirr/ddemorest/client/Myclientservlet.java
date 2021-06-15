package com.mbirr.ddemorest.client;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Timer;

import com.mbirr.ddemorest.Customer;
import com.mbirr.ddemorest.CustomerRepository;
//import com.mbirr.ddemorest.Countdowntimer.DisplayCountdown;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

//import java.util.Timer;
/**
 * Servlet implementation class Myclientservlet
 */
public class Myclientservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int DATABASE_URL = 1;
	//int count = 0;
	CustomerRepository customerrepository = new CustomerRepository();
	List<Customer> CL=customerrepository.getList();
	
	Timer timer;

    /**
     * Default constructor. 
     */
    public Myclientservlet() {
        // TODO Auto-generated constructor stub
    }
    private static String getClientIp(HttpServletRequest request) {
    	String clientip="";
    	clientip = request.getHeader("X-FORWARDED-FOR");
    	if (request != null) {
    		
    		if (clientip == null || "".equals(clientip)) {
    			clientip = request.getRemoteAddr();
    		}
    	}
    		return clientip;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	    PrintWriter pwriter=response.getWriter();
	    String Phone=request.getParameter("Phone");
	    String Amount=request.getParameter("Amount");
	    pwriter.println("User Details Page:");
	    pwriter.println("Phone "+Phone);
	    pwriter.println("Amount requested"+Amount);
	    pwriter.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();

		PrintWriter printWriter = response.getWriter();
		
		String Phone=request.getParameter("Phone");
		String Amount = request.getParameter("Amount");
		String Receiver = request.getParameter("Receiver");
		int phone=Integer.parseInt(Phone); 
		int amount = Integer.parseInt(Amount);
		int receiver = Integer.parseInt(Receiver);
		int countr = CL.get(0).getCouter();

		String req = "<TransferRequest><phone>" + phone + "</phone><receiver>" + receiver + "</receiver><amount>" + amount + "</amount><counter>" + countr+"</counter></TransferRequest>";

		URL url = new URL("http://localhost:8080/ddemorest/webapi/myresource/phone");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(20000);

		connection.setDoOutput(true);

		connection.setUseCaches(true);
		connection.setRequestMethod("POST");

		connection.setRequestProperty("Accept", "application/xml");
		connection.setRequestProperty("Content-Type", "application/xml");

		// Write XML
		OutputStream outputStream = connection.getOutputStream();
		byte[] b = req.getBytes("UTF-8");
		outputStream.write(b);
		outputStream.flush();
		outputStream.close();
	
		// Read XML
		InputStream inputStream = connection.getInputStream();
		byte[] res = new byte[2048];
		int i = 0;
		StringBuilder responsee = new StringBuilder();
		while ((i = inputStream.read(res)) != -1) {
			responsee.append(new String(res, 0, i));
		}
		

		System.out.println("Response= " + responsee.toString());
		printWriter.print("Response= " + responsee.toString());
		inputStream.close();
		
	}

}
