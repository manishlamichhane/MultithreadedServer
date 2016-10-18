package sma;

public class DateClient {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage: DateClient host port hostname(eg. www.google.com)");
		}
		String host = args[0];
		/*String amount = args[2];
		String fromCurrency	=	args[3];*/
		String hostName	=	args[2];
		int port;
		try {
			port = Integer.parseInt(args[1]);
		} catch(Exception e) {
			port = DateService.DATE_SERVICE_PORT;
		}
		MessageClient conn;
		try {
			conn = new MessageClient(host,port);
		} catch(Exception e) {
			System.err.println(e);
			return;
		}
		Message m = new Message();
		m.setType(DateService.DATE_SERVICE_MESSAGE);
		/*m.setParam("amount",amount);
		m.setParam("from",fromCurrency);*/
		
		m.setParam("hostname",hostName);
		
		m = conn.call(m);
		System.out.println("Date " + m.getParam("date"));
		System.out.println("Resolved IPs for "+hostName+": "+m.getParam("resolvedAddress"));
		m.setType(75);
		m = conn.call(m);
		System.out.println("Bad reply " + m);
		conn.disconnect();
	}
}