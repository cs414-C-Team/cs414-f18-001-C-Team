package edu.colostate.cs.cs414.cteam.p4.controller;

public class FacadeController {
		
	String stored_match = null;
	int latest_match = 0;
	
	public FacadeController() {
		
	}

	public void newMatch(int userID) {
		
	}

	public int processTurn(String turn) {
		stored_match = turn;
		return 0;
	}

	public int register(String substring) {
		return 0;
	}
	
	public int login(String credentials) {
		return 0;
	}

	public String retrieveMatch(String match) {
		return stored_match;
	}

	public int latestMatchID() {
		return ++latest_match;
	}
	
}
