package edu.colostate.cs.cs414.cteam.p3.model;

public class GamePiece {
	protected int y;
	protected int x;
	protected Tile[][] board;
	protected PieceType type;
	protected int team;

	public GamePiece(int x, int y, Tile[][] board, PieceType type, int team) {
		this.x = x;
		this.y = y;
		this.board = board;
		this.type = type;
		this.setTeam(team);
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

	protected boolean nextTo(int x, int y) {// checks for legal move locations
		if (x > 8 || x < 0 || y < 0 || y > 6)
			return false;
		if (x == this.getX()) {
			if (y == this.getY() + 1 || y == this.getY() - 1)
				return true;
			else
				return false;
		} else if (y == this.getY()) {
			if (x == this.getX() + 1 || x == this.getX() - 1)
				return true;
			else
				return false;
		} else
			return false;
	}

	// todo test
	public boolean move(int x, int y) {
		if (!nextTo(x, y))
			return false;
		Tile to = board[x][y];
		if (to.getType().equals(TileType.WATER))
			return false;
		if (team == 1 && to.getType().equals(TileType.TRAP1))
			return false;// can't move into own trap
		if (team == 2 && to.getType().equals(TileType.TRAP2))
			return false;// can't move into own trap
		if (to.hasCharacter)
			return false;// attacking requires a seperate method
		board[this.x][this.y].removeCharacter();
		this.setX(x);
		this.setY(y);
		board[x][y].setCharacter(this);
		return true;
	}

	// todo test
	public boolean attack(int x, int y) {
		// if other == killable, move to their position
		if (!nextTo(x, y))
			return false;
		Tile to = board[x][y];
		if (board[this.getX()][this.getY()].getType().equals(TileType.TRAP1)
				|| board[this.getX()][this.getY()].getType().equals(TileType.TRAP2))
			return false; // can't attack from trap
		if (to.getType().equals(TileType.WATER))// can't attack in water
			return false;
		if (!to.hasCharacter)// can't attack nonexistent character
			return false;
		GamePiece gp = to.getCharacter();
		if (gp.getTeam() == this.getTeam())// can't attack friend
			return false;
		if (gp.getType().ordinal() > this.getType().ordinal() && !to.getType().equals(TileType.TRAP1)
				&& !to.getType().equals(TileType.TRAP2))
			return false; // must be lower rank or trapped
		board[x][y].removeCharacter();
		// move, throw error if fail:
		if (to.getType().equals(TileType.TRAP1) || to.getType().equals(TileType.TRAP2))
			return true;// don't move
		if (!move(x, y))
			throw new RuntimeException("Move failed, remove character did not");

		return true;
	}

	public String toString() {
		return type.toString();
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

}
