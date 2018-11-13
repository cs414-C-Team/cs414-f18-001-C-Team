package wraith.library.Multiplayer;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientRunner {

	public static void main(String[] args) {
		
		ClientListener cltl = new ClientListenerTester();
		Client test_client = new Client("10.83.142.60", 51601, cltl); 
		//run the server test code to get the ip address
		
		test_client.send("Rusername,password");
		while(test_client.isConnected()) {
			if(cltl.recievedMessage()) {
				System.out.println(cltl.getCurrentMessage());
			}
		}
	}

}
