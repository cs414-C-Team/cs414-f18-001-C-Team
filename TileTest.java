package gameJungle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {
	
	GamePiece piece1 = new GamePiece(0,0,null,PieceType.RAT ,1);
	Tile T= new Tile(TileType.BLANK,piece1);


	@Test
	final void testTileTileTypeGamePiece() {
		assertTrue(T.getCharacter()==piece1);
		assertTrue(T.getType()==TileType.BLANK);
		assertTrue(T.hasCharacter()==true);
		
	}

	@Test
	final void testTileTileType() {
	Tile T1= new Tile(TileType.DEN);
	assertTrue(T1.getType()==TileType.DEN);
	
	}

	@Test
	final void testHasCharacter() {
		assertTrue(T.hasCharacter()==true);
	}

	@Test
	final void testRemoveCharacter() {
		T.removeCharacter();
		assertTrue(T.hasCharacter()==false);
	}

	@Test
	final void testToString() {
		 assertTrue(T.toString()=="BLANK");
	}

}