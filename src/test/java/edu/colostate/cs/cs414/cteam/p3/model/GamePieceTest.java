package edu.colostate.cs.cs414.cteam.p3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class GamePieceTest {
	
	
	//Match m1= new Match();
	GamePiece piece1 = new GamePiece(0,0,null,PieceType.RAT ,1);
	Tile T= new Tile(TileType.BLANK, piece1);

	@Test
	final void testGamePiece() {
		
		assertTrue(piece1.getX()==0);
		assertTrue(piece1.getY()==0);
		assertTrue(piece1.getType()==PieceType.RAT);
		assertTrue(piece1.getTeam()==1);
	}

	/*@Test
	final void testMove() {
		piece1.move(1, 0);
		assertTrue(piece1.getX()==5);
		assertTrue(piece1.getY()==0);
		
	}*/
	
	

/*	@Test
	final void testAttack() {
		fail("Not yet implemented"); // TODO
	} */
	
	
	@Test
	final void testToString() {
		assertTrue(piece1.toString()=="RAT");
	}



}
