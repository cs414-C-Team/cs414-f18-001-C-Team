
public class Rat extends GamePiece {

	private int y;
	private int x;
	private Tile[][] board;
	private PieceType type;
	private int team;

	public Rat(int x, int y, Tile[][] board, int team) {
		super(x, y, board, PieceType.RAT, team);
	}

	// todo test
	public boolean move(int x, int y) {
		// can move in water
		if (!nextTo(x, y))
			return false;
		if (board[x][y].hasCharacter)
			return false;// attacking requires a seperate method
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
		if (board[this.getX()][this.getY()].getType().equals(TileType.TRAP))
			return false;
		GamePiece gp = to.getCharacter();
		if (board[this.getX()][this.getY()].getType().equals(TileType.WATER) && !gp.getType().equals(PieceType.RAT))
			return false;// has to be attacking another rat
		if (gp.getTeam() == this.getTeam())// can't attack friend
			return false;
		if (gp.getType().ordinal() > this.getType().ordinal() && !to.getType().equals(TileType.TRAP)
				&& !gp.getType().equals(PieceType.ELEPHANT))
			return false; // must be lower rank, trapped, or elephant
		board[x][y].removeCharacter();
		// move, throw error if fail:
		if (!move(x, y))
			throw new RuntimeException("Move failed, remove character did not");

		return true;
	}
}
