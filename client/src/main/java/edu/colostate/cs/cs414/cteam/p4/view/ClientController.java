package edu.colostate.cs.cs414.cteam.p4.view;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.UnknownHostException;

import edu.colostate.cs.cs414.cteam.p4.gamelogic.Match;
import edu.colostate.cs.cs414.cteam.p4.gamelogic.MatchController;
import edu.colostate.cs.cs414.cteam.p4.gamelogic.Turn;

public class ClientController {
	static ClientController self;
	static ClientListener cltl;
	static Client client;
	static LoginWindow lw;
	GameWindow gw;
	
	private static MatchController match_control;

	public boolean connect(String ip) {
		cltl = new ClientListener();
		int port = 51599;
		
		do {
			port++;
			client = new Client(ip, port, cltl);
		}while(!client.isConnected() && port < 90000);
					
		if(client.isConnected()) {
			return client.isConnected();
		}
		return false;
	}

	public int login(String username, String password) {
		System.out.println("connecting");
		if(client.isConnected()) {
			client.login(username, password);
			
			//Wait to receive response indicating a turn was successfully received, but only for 10 seconds
			long startTime = System.currentTimeMillis(); //fetch starting time
			while(cltl.inputStatus() == false) {
				if((System.currentTimeMillis()-startTime)>10000) {
					System.out.println("ClientController:ERROR: turn retrieval timed out.");
					return -2;
				} 
			}
			
			try {
				//Either a -1 for failed authentication, or the user's ID
				return Integer.parseInt(cltl.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("ClientController:ERROR: invalid message received");
				e.printStackTrace();
				return -2;
			} catch (InterruptedException e) {
				System.out.println("ClientController:ERROR: invalid message received");
				e.printStackTrace();
				return -2;
			}
		} else {
			return -2;
		}
	}

	public void launchGame(final int user) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gw = new GameWindow(self, user);
                gw.display();
            }
        });
	}

	public boolean retrieveGame(int user, int matchID) {
		client.retrieveGame(user, matchID);
		
		//Wait to receive response indicating a turn was successfully received, but only for 10 seconds
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(cltl.inputStatus() == false) {
			if((System.currentTimeMillis()-startTime)>10000) {
				System.out.println("ClientController:ERROR: turn retrieval timed out.");
				return false;
			} 
		}
		
		try {
			match_control.loadMatch(cltl.getMessage());
			return true;
		} catch (InterruptedException e) {
			System.out.println("ClientController:ERROR: failure retrieving game.");
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean newMatch(int playerID) {
		client.getLatestMatchID();
		
		//Wait to receive response indicating a turn was successfully received, but only for 10 seconds
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(cltl.inputStatus() == false) {
			if((System.currentTimeMillis()-startTime)>10000) {
				System.out.println("ClientController:ERROR: turn retrieval timed out.");
				return false;
			} 
		}
		
		try {
			match_control.createMatch(Integer.parseInt(cltl.getMessage()), playerID);
			return true;
		} catch (InterruptedException e) {
			System.out.println("ClientController:ERROR: failure retrieving latest match ID.");
			e.printStackTrace();
			return false;
		}
	}
	
	public void newLocalMatch() {
		match_control.createMatch(0,1);
		match_control.startMatch(2);
	}
	
	public void startMatch(int player2) {
		match_control.startMatch(player2);
	}
	
	public int getActivePlayer() {
		return match_control.getActivePlayer();
	}
	
	public String getPlayers() {
		return match_control.getPlayers();
	}
	
	public String[] getPositions() {
		return match_control.getPositions();
	}
	
	public int processMove(int fromX, int fromY, int toX, int toY, int player) {
		System.out.println(fromX + "," + fromY + "," + toX + "," + toY + "," + player);
		return match_control.processMove(fromX, fromY, toX, toY, player);
	}
	
	public boolean submitTurn() {
		client.submitTurn(match_control.getMatchString());
		
		//Wait to receive response indicating a turn was successfully received, but only for 10 seconds
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(cltl.inputStatus() == false) {
			if((System.currentTimeMillis()-startTime)>10000) {
				System.out.println("ClientController:ERROR: turn retrieval timed out.");
				return false;
			} 
		}
		
		try {
			match_control.loadMatch(cltl.getMessage());
			return true;
		} catch (InterruptedException e) {
			System.out.println("ClientController:ERROR: failure retrieving game.");
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		self = new ClientController();
		match_control = new MatchController();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lw = new LoginWindow(self);
					lw.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
