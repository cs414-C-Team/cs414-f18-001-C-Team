package gameJungle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LigerTest {


	GamePiece piece1 = new GamePiece(0,0,null,PieceType.LION ,1);
	GamePiece piece2 = new GamePiece(1,5,null,PieceType.TIGER ,2);
	
	Tile T= new Tile(TileType.BLANK, piece1);
	

	

/*	@Test
	final void testMove() {
		fail("Not yet implemented"); // TODO
	} */

	@Test
	final void testLiger() {
		assertTrue(piece1.getTeam()==1);
		assertTrue(piece1.getX()==0);
		assertTrue(piece1.getY()==0);
		assertTrue(piece1.getType()==PieceType.LION);
		
		assertTrue(piece2.getTeam()==2);
		assertTrue(piece2.getX()==1);
		assertTrue(piece2.getY()==5);
		assertTrue(piece2.getType()==PieceType.TIGER);
		
		//assertThrows(piece1.getType()==PieceType.TIGER);
		//assertThrows(piece2.getType()==PieceType.LION);
	}
	
	@Test 
	final void LigerExceptionTest() {
		
		
		assertThrows(IllegalArgumentException.class , () -> {
			@SuppressWarnings("unused")
			Liger piece3= new Liger(2,3,null,PieceType.CAT,1);
			
		});
		
		
	}
	
	
	

}
