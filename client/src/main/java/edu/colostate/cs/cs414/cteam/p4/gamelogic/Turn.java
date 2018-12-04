package edu.colostate.cs.cs414.cteam.p4.gamelogic;

import javafx.util.Pair;

public class Turn {
	private int turn_number;
	private int match_id;
	private int player;
	private Pair<Integer, Integer> current_location;
	private Pair<Integer, Integer> new_location;
	
	public Turn(int match_id, int turn_number, int player) {
		this.turn_number = turn_number;
		this.match_id = match_id;
		this.player = player;
	}
	
	public Turn(String turn_string) {
		String[] parsed_turn = turn_string.split("-");
		this.match_id = Integer.parseInt(parsed_turn[0]);
		this.turn_number = Integer.parseInt(parsed_turn[1]);
		this.player = Integer.parseInt(parsed_turn[2]);
		
		String[] location = parsed_turn[3].split(":");
		this.current_location = new Pair<Integer, Integer>(Integer.parseInt(location[0]), Integer.parseInt(location[1]));
		
		location = parsed_turn[4].split(":");
		this.new_location = new Pair<Integer, Integer>(Integer.parseInt(location[0]), Integer.parseInt(location[1]));

	}
	
	public void moveFrom(int x, int y) {
		this.current_location = new Pair<Integer, Integer>(x, y);
	}
	
	public void moveTo(int x, int y) {
		this.new_location = new Pair<Integer, Integer>(x, y);
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getTurnNumber() {
		return turn_number;
	}
	
	public int getMatchID() {
		return match_id;
	}
	
	public Pair<Integer, Integer> getFrom() {
		return current_location;
	}
	
	public Pair<Integer, Integer> getTo() {
		return new_location;
	}
	
	public String toString() {
		return match_id + "-" + turn_number + "-" + player + "-" + current_location.getKey() + ":" + current_location.getValue() + "-" + new_location.getKey() + ":" + new_location.getValue();
	}
}
