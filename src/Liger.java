
public class Liger extends GamePiece {
	
	private int y;
	private int x;
	private Tile[][] board;
	private PieceType type;
	private int team;

	public Liger(int x, int y, Tile[][] board, PieceType type, int team) {
		super(x, y, board, type, team);
		if(!(type.equals(PieceType.LION) || type.equals(PieceType.TIGER))) 
			throw new IllegalArgumentException();
		}

	// todo, can jump over water if no rat in way
	public boolean move(int x, int y) {

		return true;
	}
}
