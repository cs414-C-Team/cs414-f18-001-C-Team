package edu.colostate.cs.cs414.cteam.p4.gamelogic;

public class GamePiece {
	protected int y;
	protected int x;
	protected PieceType type;
	protected int team;

	public GamePiece(int x, int y, PieceType type, int team) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.team = team;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public PieceType getType() {
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	public String toString() {
		return type.toString();
	}

	public int getTeam() {
		return team;
	}

}
