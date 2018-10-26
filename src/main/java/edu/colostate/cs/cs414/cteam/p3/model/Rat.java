package edu.colostate.cs.cs414.cteam.p3.model;

public class Rat extends GamePiece {

	public Rat(int x, int y, Tile[][] board, int team) {
		super(x, y, board, PieceType.RAT, team);
	}

	// todo test
	public boolean move(int x, int y) {
		// can move in water
		if (!nextTo(x, y))
			return false;
		Tile to = board[x][y];
		if (to.hasCharacter)
			return false;// attacking requires a seperate method
		if (team == 1 && to.getType().equals(TileType.TRAP1))
			return false;// can't move into own trap
		if (team == 2 && to.getType().equals(TileType.TRAP2))
			return false;// can't move into own trap
		board[this.x][this.y].removeCharacter();
		this.setX(x);
		this.setY(y);
		board[x][y].setCharacter(this);
		return true;
	}

	// todo test
	public boolean attack(int x, int y) {
		// can attack from water, but only other rats
		// can attack elephants from land
		if (!nextTo(x, y))
			return false;
		Tile to = board[x][y];
		if (!to.hasCharacter)// can't attack nonexistent character
			return false;
		if (board[this.getX()][this.getY()].getType().equals(TileType.TRAP1)
				|| board[this.getX()][this.getY()].getType().equals(TileType.TRAP2))
			return false; // can't attack from trap
		GamePiece gp = to.getCharacter();
		if (board[this.getX()][this.getY()].getType().equals(TileType.WATER) && !gp.getType().equals(PieceType.RAT))
			return false;// has to be attacking another rat
		if (gp.getTeam() == this.getTeam())// can't attack friend
			return false;
		if (gp.getType().ordinal() > this.getType().ordinal() && !to.getType().equals(TileType.TRAP1)
				&& !to.getType().equals(TileType.TRAP2) && !gp.getType().equals(PieceType.ELEPHANT))
			return false; // must be lower rank, trapped, or elephant
		board[x][y].removeCharacter();
		// move, throw error if fail:
		if (to.getType().equals(TileType.TRAP1) || to.getType().equals(TileType.TRAP2))
			return true;// don't move
		if (!move(x, y))
			throw new RuntimeException("Move failed, remove character did not");

		return true;
	}
}
