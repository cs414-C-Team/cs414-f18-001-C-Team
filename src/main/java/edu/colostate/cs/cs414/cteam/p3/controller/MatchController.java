package edu.colostate.cs.cs414.cteam.p3.controller;

import edu.colostate.cs.cs414.cteam.p3.model.Match;
import edu.colostate.cs.cs414.cteam.p3.model.Tile;

public class MatchController {
	private Match selected_match;
	private Turn current_turn;
	
	public void createMatch() {
		selected_match = new Match();
	}
	
	public void startMatch() {
		current_turn = firstTurn();
	}
	
	//Returns cases:
	// -1 : Invalid move
	//  0 : Move finished, game is not finished
	//  1 : Move finished, player 1 wins
	//  2 : Move finished, player 2 wins
	public int processTurn(Turn turn) {
		if(selected_match.status() != 0) {
			return -1;
		}
		
		if(selected_match.validMove(turn.getFrom(), turn.getTo(), turn.getPlayer()) ) {
			selected_match.update(turn.getFrom(), turn.getTo());
		} else {
			System.out.println("Invalid move");
			return -1;
		}
		
		if(selected_match.status() == 0) {
			this.current_turn = nextTurn(turn);
		}
		return selected_match.status();
	}
	
	public Turn nextTurn(Turn turn) {
		int player = turn.getPlayer();
		
		if(player == 1) {
			player = 2;
		} else {
			player = 1;
		}
		
		return new Turn(turn.getMatchID(), turn.getTurnNumber() + 1, player);
	}
	
	public Turn firstTurn() {
		//return new Turn(getLastMatchID() + 1, 1, 1);
		return new Turn(1, 1, 1);
	}
	
	public Turn getTurn() {
		return current_turn;
	}
}
