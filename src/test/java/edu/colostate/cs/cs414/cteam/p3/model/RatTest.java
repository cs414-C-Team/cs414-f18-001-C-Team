package edu.colostate.cs.cs414.cteam.p3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class RatTest {
	
	GamePiece piece4=new GamePiece(0,6,null,PieceType.RAT,2);
	

/*	@Test
	final void testAttack() {
		fail("Not yet implemented"); // TODO
	}*/

	@Test
	final void testRat() {
		assertTrue(piece4.getType()==PieceType.RAT);
	}

}
