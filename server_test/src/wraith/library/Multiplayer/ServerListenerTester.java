package wraith.library.Multiplayer;

import java.io.PrintWriter;

public class ServerListenerTester implements ServerListener{

	public void ServerListenerTest() {
		
	}
	
	@Override
	public void clientConnected(ClientInstance client, PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientDisconnected(ClientInstance client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recivedInput(ClientInstance client, String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
		
	}

	@Override
	public void serverClosed() {
		// TODO Auto-generated method stub
		
	}

}
