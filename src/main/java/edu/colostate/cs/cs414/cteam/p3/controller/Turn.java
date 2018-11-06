package edu.colostate.cs.cs414.cteam.p3.controller;

import javafx.util.Pair;

public class Turn {
	private int turn_number;
	private int match_id;
	private int player;
	private Pair<Integer, Integer> current_location;
	private Pair<Integer, Integer> new_location;
	
	public Turn(int turn_number, int match_id, int player) {
		this.turn_number = turn_number;
		this.match_id = match_id;
		this.player = player;
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
}
