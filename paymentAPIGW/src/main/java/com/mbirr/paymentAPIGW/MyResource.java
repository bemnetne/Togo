package com.mbirr.paymentAPIGW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

//import com.mbirr.ddemorest.client.Myclientservlet;
import com.mbirr.paymentAPIGW.controller.APIcontroller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.jsoup.*;     
import org.jsoup.nodes.*;
import org.jsoup.parser.*;
import org.xml.sax.InputSource;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	APIcontroller apicontroller = new APIcontroller();

	String status= "";

    
    /**
     * Payment API
     * Method handling HTTP POST requests. 
     * @return String that will be returned as a text/plain response.
     */
    
    @POST
    @Path("phone")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public String Status(InputStream In) throws IOException  {
    	StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(In));
        String line = in.readLine();
        while (line != null) {
                sb.append(line);
                line=in.readLine();
        } 
            // print result
        	String sbb=sb.toString();
            System.out.println("JSON String Result " + sb.toString());
            in .close();

            Document doc = Jsoup.parse(sbb, "", Parser.xmlParser());
            String Phone =doc.select("phone").text();
            String Amount =doc.select("amount").text();
            String Receiver =doc.select("receiver").text();

            int phone=Integer.parseInt(Phone); 
    		int amount = Integer.parseInt(Amount);
    		int receiver = Integer.parseInt(Receiver);

    		String status = apicontroller.getstatus(phone,amount,receiver); 
    		System.out.println(status);
    	return status;
    }
    
    
}
