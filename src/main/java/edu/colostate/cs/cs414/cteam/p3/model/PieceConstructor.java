package edu.colostate.cs.cs414.cteam.p3.model;

public class PieceConstructor {
	private int y;
	private int x;
	public int getY() {
		return y;
	}
	
	public PieceConstructor(int x, int y, PieceType type, int team) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.setTeam(team);
	}
	
	public GamePiece construct(Tile[][] board) {
		GamePiece piece;
		if(type.equals(PieceType.LION) || type.equals(PieceType.TIGER)) {
			piece = new Liger(x, y, board, type, team);
		}
		else if(type.equals(PieceType.RAT))
			piece = new Rat(x, y, board, team);
		else
			piece = new GamePiece(x, y, board, type, team);
		board[x][y].setCharacter(piece);
		return piece;
	}

	
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public PieceType getType() {
		return type;
	}
	public void setType(PieceType type) {
		this.type = type;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	private PieceType type;
	private int team;
}
