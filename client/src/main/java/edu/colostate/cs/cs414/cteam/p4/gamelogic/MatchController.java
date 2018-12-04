package edu.colostate.cs.cs414.cteam.p4.gamelogic;

public class MatchController {
	private Match current_match;
	private int current_turn;
	
	public void createMatch(int matchID, int player) {
		current_match = new Match(matchID, player);
	}
	
	//Match string is in this format: <matchID>-<date>-<status>-<opponent>-<active player>-<pos1:pos2:po3...>
	public void loadMatch(String match_string) {
		current_match = new Match(match_string);
		current_turn = current_match.getActivePlayer();
	}
	
	public void startMatch(int player2) {
		current_turn = current_match.getActivePlayer();
		current_match.setPlayer2(player2);
	}
	
	public String getMatchString() {
		return current_match.toString();
	}
	
	//Return cases:
	// -1 : Invalid move
	//  0 : Move finished, game is not finished
	//  1 : Move finished, player 1 wins
	//  2 : Move finished, player 2 wins
	public int processMove(int fromX, int fromY, int toX, int toY, int player) {
		
		if(current_match.validMove(fromX, fromY, toX, toY, player)) {
			current_match.update(fromX, fromY, toX, toY);
			System.out.println("Match controller: Current game status: " + current_match.status());
			return current_match.status();
		} else {
			System.out.println("Invalid move");
			return -1;
		}
	}
	
	public String[] getPositions() {
		return current_match.getBoard();
	}

	public int getActivePlayer() {
		return current_match.getActivePlayer();
	}
	
	public String getPlayers() {
		return current_match.getPlayers();
	}
	
	public int nextTurn(int current_turn) {		
		if(current_turn == 1) {
			return 2;
		} else {
			return 1;
		}
			
	}
	
}
