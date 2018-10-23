package gameJungle;

public class Cat extends GamePiece {

	public Cat(int x, int y, Tile[][] board, PieceType type, int team) {
		super (x,y,board,PieceType.CAT,team);
		
	}
	public boolean move(int x, int y) {
		// cannot move in water anywhere else is allowed
		return true;
	}

	// todo
	public boolean attack(int x, int y) {
		// can attack only animals with rank lower than 2 
		//Only kill rat but not if rat is in water
		// basically if it's in water, attack, if it's not in water, call super.attack
		return false;
	}
}
	
}
