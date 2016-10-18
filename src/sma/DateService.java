package sma;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class DateService implements Deliverable {

	public static final int DATE_SERVICE_MESSAGE = 100;
	public static final int DATE_SERVICE_PORT = 1999;
	public Message send(Message m) {
		Date today = new Date();
		m.setParam("date", today.toString());
		
		try {
			String hostSentByClient	=	m.getParam("hostname");
			String resolvedAddresses = new String(" ");
			
			for(InetAddress addr : InetAddress.getAllByName(hostSentByClient))
				resolvedAddresses += addr.getHostAddress()+" ";
				
			
			m.setParam("resolvedAddress", resolvedAddresses);			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
	public static void main(String args[]) {
		DateService ds = new DateService();
		MessageServer ms;
		try {
			ms = new MessageServer(DATE_SERVICE_PORT);
		} catch(Exception e) {
			System.err.println("Could not start service " + e);
			return;
		}
		Thread msThread = new Thread(ms);
		ms.subscribe(DATE_SERVICE_MESSAGE, ds);
		msThread.start();
	}
}