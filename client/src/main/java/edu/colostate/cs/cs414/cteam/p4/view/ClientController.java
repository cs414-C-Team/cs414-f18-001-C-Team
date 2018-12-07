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

	public void sendInvitation(String players) {
		client.sendInvitation(players);
		client.getMessage();
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

	public int newMatch(String players) {
		client.getLatestMatchID();
		String result = client.getMessage();
		int id;
		if(result != null) {
			id = Integer.parseInt(result);
			match_control.createMatch(id, Integer.parseInt(players.split("-")[0]), Integer.parseInt(players.split("-")[1]));
			client.storeMatch(match_control.getMatchString());
			client.getMessage();
			return id;
		}
		
		return -1;
	}
	
	public void newLocalMatch() {
		match_control.createMatch(0,1, 2);
		match_control.startMatch();
	}
	
	public void startMatch() {
		match_control.startMatch();
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
	
	public void submitTurn() {
		client.submitTurn(match_control.getMatchString());
		client.getMessage();
	}

	public String queryGames(int user) {
		client.queryGames(Integer.toString(user));
		
		//Wait to receive response indicating a turn was successfully received, but only for 10 seconds
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(cltl.inputStatus() == false) {
			if((System.currentTimeMillis()-startTime)>10000) {
				System.out.println("ClientController:ERROR: turn retrieval timed out.");
				return "";
			} 
		}
		
		try {
			String message = cltl.getMessage();
			return message;
		} catch (InterruptedException e) {
			System.out.println("ClientController:ERROR: invalid games message format");
			e.printStackTrace();
			return "";
		}
	}

	public boolean retrieveGame(int matchID) {
		client.getMatch(Integer.toString(matchID));
	
		String result = client.getMessage();
		if(result != null) {
			System.out.println("Retrieved match: " + result );
			match_control.loadMatch(result);
			return true;
		}
		
		return false;
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
