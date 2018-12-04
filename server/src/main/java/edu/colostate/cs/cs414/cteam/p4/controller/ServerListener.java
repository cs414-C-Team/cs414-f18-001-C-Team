package edu.colostate.cs.cs414.cteam.p4.controller;

import java.io.PrintWriter;

public class ServerListener {
	
	FacadeController controller = new FacadeController();

	public void ServerListener() {
		
	}
	
	public void clientConnected(ClientInstance client, PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	public void clientDisconnected(ClientInstance client) {
		// TODO Auto-generated method stub
		
	}

	public String recivedInput(ClientInstance client, String msg) {
		// TODO Auto-generated method stub
		System.out.println("System: recieved message: " + msg);

		switch(msg.charAt(0)) {
		case '1':
			System.out.println("System: creating match for client " + client.toString());
			controller.newMatch(Integer.parseInt(msg.substring(1, msg.length())));
			System.out.println("System: created match for client " + client.toString());

			return "System: Match created for client " + client.toString();
		case '2':
			//return controller.getTurn();
			return "Not in use";
		case '3':
			int result = controller.processTurn(msg.substring(1, msg.length()));
			if(result == -1) {
				return "failure";
			} else if (result == 0) {
				return "success";
			} else {
				return Integer.toString(result);
			}
		case '4':
			return Integer.toString(controller.register(msg.substring(1,msg.length())));
		case '5':
			return Integer.toString(controller.login(msg.substring(1,msg.length())));
		case '6':
			return controller.retrieveMatch(msg.substring(1,msg.length()));
		case '7':
			return Integer.toString(controller.latestMatchID());
		default:
			return "System:ERROR: Unrecognizable message format.";
		}
		
	}
	
	public void serverClosed() {
		// TODO Auto-generated method stub
		
	}

}
