package wraith.library.Multiplayer;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerRunner {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerListener slt = new ServerListenerTester();
		Server testServer = new Server(8085, slt);
		System.out.println(testServer.getIp());
		
		ClientListener cltl = new ClientListenerTester();
		Client test_client = new Client(testServer.getIp(), 8084, cltl);
		
		while(true) {
			;
		}
	}

}
