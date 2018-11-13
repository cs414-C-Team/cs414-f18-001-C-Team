package wraith.library.Multiplayer;

public class ClientListenerTester implements ClientListener{
	private String message;
	private boolean recievedNewMessage = false;
	
	@Override
	public void unknownHost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void couldNotConnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recivedInput(String msg) {
		// TODO Auto-generated method stub
		message = msg;
		recievedNewMessage = true;
	}

	@Override
	public void serverClosed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectedToServer() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean recievedMessage() {
		return recievedNewMessage;
	}
	
	@Override
	public String getCurrentMessage() {
		recievedNewMessage = false;
		return message;
	}

}
