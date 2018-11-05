package wraith.library.Multiplayer;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientRunner {

	public static void main(String[] args) {
		
		ClientListener cltl = new ClientListenerTester();
		Client test_client = new Client("<ip_address>", 8085, cltl); 
		//run the server test code to get the ip address
		
		test_client.send("testing");
	}

}
