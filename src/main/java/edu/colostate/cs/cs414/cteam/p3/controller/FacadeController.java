package edu.colostate.cs.cs414.cteam.p3.controller;

public class FacadeController {
	
	private MatchController match_control = new MatchController();
	
	public void newMatch() {
		match_control.createMatch();
		match_control.startMatch();
	}
	
	public Turn getTurn() {
		return match_control.getTurn();
	}
	
	public int processTurn(Turn turn) {
		int result = match_control.processTurn(turn);
		if(result == 0) {
			//Placeholder, here would be a method call to assign the turn to a user
		}
		
		System.out.println("System: returning " + result);
		return result;
	}
}
