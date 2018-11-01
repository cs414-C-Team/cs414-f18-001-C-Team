package edu.colostate.cs.cs414.cteam.p3.model;

public class Liger extends GamePiece {

	public Liger(int x, int y, Tile[][] board, PieceType type, int team) {
		super(x, y, board, type, team);
		if (!(type.equals(PieceType.LION) || type.equals(PieceType.TIGER)))
			throw new IllegalArgumentException();
	}

	private boolean legalJump(int x, int y) {
		// check that liger is on a shore, to is on the other shore, and there's nothing
		// in between
		if (this.x == x) {
			if (this.y > y) {
				for (int i = y + 1; i < this.y; i++) {
					if (!board[x][i].getType().equals(TileType.WATER) || board[x][i].hasCharacter)
						return false;// can't jump over non-water tiles or occupied tiles
				}
			} else {
				for (int i = y - 1; i > this.y; i--) {
					if (!board[x][i].getType().equals(TileType.WATER) || board[x][i].hasCharacter)
						return false;// can't jump over non-water tiles or occupied tiles
				}
			}
		} else if (this.y == y) {
			if (this.x > x) {
				for (int i = x + 1; i < this.x; i++) {
					if (!board[i][y].getType().equals(TileType.WATER) || board[i][y].hasCharacter)
						return false;// can't jump over non-water tiles or occupied tiles
				}
			} else {
				for (int i = x - 1; i > this.x; i--) {
					if (!board[i][y].getType().equals(TileType.WATER) || board[i][y].hasCharacter)
						return false;// can't jump over non-water tiles or occupied tiles
				}
			}
		}
		return true;
	}

	// todo, can jump over water if no rat in way
	public boolean move(int x, int y) {
		if (x > 8 || x < 0 || y < 0 || y > 6)
			return false;// have to check twice since liger doesn't rely on nextTo
		if (nextTo(x, y))
			return super.move(x, y);
		Tile to = board[x][y];
		if (to.hasCharacter)
			return false;// attack in a different method
		if (to.getType().equals(TileType.WATER))
			return false;// can't jump into water, am assuming there are no traps on the shore
		if (!legalJump(x, y))
			return false;// x != x && y != y ==> diagonal (illegal) move

		board[this.x][this.y].removeCharacter();
		this.setX(x);
		this.setY(y);
		board[x][y].setCharacter(this);
		return true;
	}

	// todo test
	public boolean attack(int x, int y) {
		if (x > 8 || x < 0 || y < 0 || y > 6)
			return false;// have to check twice since liger doesn't rely on nextTo
       /* what is this for?
		if (nextTo(x, y))
			return super.move(x, y); */
		Tile to = board[x][y];
		if (!to.hasCharacter)
			return false;// can't attack empty space
		GamePiece gp = to.getCharacter();

		if (to.getCharacter().getTeam() == this.getTeam())
			return false;// can't attack own team
		if (to.getType().equals(TileType.WATER))
			return false;// can't jump into water, am assuming there are no traps on the shore
		if (!legalJump(x, y))
			return false;// x != x && y != y ==> diagonal (illegal) move
		if (gp.getType().ordinal() > this.getType().ordinal())
			return false; // must be lower rank or trapped
		board[x][y].removeCharacter();
		// move, throw error if fail:
		if (!move(x, y))
			throw new RuntimeException("Move failed, remove character did not");

		return true;
	}
}
