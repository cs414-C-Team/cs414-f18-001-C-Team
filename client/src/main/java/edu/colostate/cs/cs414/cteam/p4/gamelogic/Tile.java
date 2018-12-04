package edu.colostate.cs.cs414.cteam.p4.gamelogic;

public class Tile {

	private GamePiece character;
	private TileType type;
	boolean hasCharacter;

	public Tile(TileType type, GamePiece character) {
		this.character = character;
		this.type = type;
		hasCharacter = true;
	}

	public Tile(TileType type) {
		character = null;
		this.type = type;
		hasCharacter = false;
	}

	public boolean hasCharacter() {
		return hasCharacter;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public GamePiece getCharacter() {
		return character;
	}

	public void setCharacter(GamePiece character) {
		this.character = character;
		hasCharacter = true;
	}

	// has to get called during a move, don't forget this
	public void removeCharacter() {
		character = null;
		hasCharacter = false;
	}

	public String toString() {
		if(hasCharacter())
			return getCharacter().toString();
		return type.toString();
	}
}
