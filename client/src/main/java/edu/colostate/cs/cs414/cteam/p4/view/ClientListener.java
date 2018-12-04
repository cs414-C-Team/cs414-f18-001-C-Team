package edu.colostate.cs.cs414.cteam.p4.view;

public class ClientListener{
	boolean status = false;
	String message = "";
	
	public void unknownHost() {
		
	}
	
	public void couldNotConnect() {
		
	}
	
	public synchronized void reciveInput(String msg) throws InterruptedException {
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(status == true) {
			; //Wait until the previous message is processed
			if((System.currentTimeMillis()-startTime)>10000) {
				System.out.println("Client:WARNING: message retrieval timed out, overwriting with new message.");
				break;
			} 
		}
		System.out.println("Client: recieved new message: " + msg);
		status = true;
		System.out.println("Status set to " + status);
		message = msg;
	}
	
	public boolean inputStatus() {
		return status;
	}
	
	public synchronized String getMessage() throws InterruptedException {
		while(status == false) {
			Thread.sleep(100); //Ensure there is a message to retrieve
		}
		System.out.println("Retrieving message: " + message);
		status = false;
		System.out.println("Status set to " + status);
		return message;
	}
	
	public void serverClosed() {
		
	}
	
	public void disconnected() {
		
	}
	
	public void connectedToServer() {
		
	}
}