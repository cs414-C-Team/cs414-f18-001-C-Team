package edu.colostate.cs.cs414.cteam.p4.gamelogic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatchControllerTest {
	Match m1 = new Match(23,1,2);
	Match previous_match;
	
	@Test
	final void testCreateMatch() {
		
		assertTrue(m1.getMatchID()==23);
		assertTrue(m1.getPlayers().equals("1-2"));
		
	}
	
	//Test after serialization implemented
	/*@Test
	final void testLoadMatch() {
		previous_match= new Match();
		
	}*/

	@Test
	final void testStartMatch() {
		m1.start();
		int activePlayer_m1=m1.getActivePlayer();
		int currentTurn=m1.getActivePlayer();
		assertTrue(currentTurn==activePlayer_m1);
	}

	
	//to be tested after match serialization
	/*@Test
	final void testGetMatchString() {
		fail("Not yet implemented"); // TODO
	}*/

	@Test
	final void testProcessMove() {
		//int activePlayer_m1=m1.getActivePlayer();
		//int current_turn=m1.start();
		boolean valid_move=m1.validMove(0, 2, 0,3, 1);
		assertTrue(valid_move);
		boolean invalid_move=m1.validMove(0, 2, 0,4, 1);
		assertFalse(invalid_move);
			
	}


}
