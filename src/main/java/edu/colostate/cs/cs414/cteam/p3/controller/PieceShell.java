package edu.colostate.cs.cs414.cteam.p3.controller;

import edu.colostate.cs.cs414.cteam.p3.model.PieceType;
import javafx.util.Pair;

public class PieceShell {
	private int team;
	private Pair<Integer, Integer> location;
	private PieceType type;
	
	public int getX() {
		return location.getKey();
	}
	
	public int getY() {
		return location.getValue();
	}
	
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public Pair<Integer, Integer> getLocation() {
		return location;
	}
	public void setLocation(Pair<Integer, Integer> location) {
		this.location = location;
	}
	public PieceType getType() {
		return type;
	}
	public void setType(PieceType type) {
		this.type = type;
	}
	public PieceShell(int team, Pair<Integer, Integer> location, PieceType type) {
		super();
		this.team = team;
		this.location = location;
		this.type = type;
	}
	
	
}
