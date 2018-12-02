package edu.colostate.cs.cs414.cteam.p3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.util.Pair;

class MatchTest {
	Tile[][] board;
	Match m;
	
	@BeforeEach
	final void initializeMatch() {
		m = new Match();
		board = m.getBoard();
	}
	
	@Test
	// tests the game tiles and pieces of a starting match game board
	void testStartBoard() {

		assertTrue(board[3][0].getType() == TileType.DEN1);
		assertTrue(board[3][8].getType() == TileType.DEN2);
		assertTrue(board[2][0].getType() == TileType.TRAP);
		assertTrue(board[4][0].getType() == TileType.TRAP);
		assertTrue(board[3][1].getType() == TileType.TRAP);
		assertTrue(board[2][8].getType() == TileType.TRAP);
		assertTrue(board[4][8].getType() == TileType.TRAP);
		assertTrue(board[3][7].getType() == TileType.TRAP);
		for (int i = 1; i < 3; i++) {
			for (int j = 3; j < 6; j++) {
				assertTrue(board[i][j].getType() == TileType.WATER);
				assertTrue(board[i + 3][j].getType() == TileType.WATER);
			}
		}	
		assertTrue(board[0][0].getCharacter().getType() == PieceType.LION);
		assertTrue(board[6][8].getCharacter().getType() == PieceType.LION);
		assertTrue(board[6][0].getCharacter().getType() == PieceType.TIGER);
		assertTrue(board[0][8].getCharacter().getType() == PieceType.TIGER);
		assertTrue(board[1][1].getCharacter().getType() == PieceType.DOG);
		assertTrue(board[5][7].getCharacter().getType() == PieceType.DOG);
		assertTrue(board[5][1].getCharacter().getType() == PieceType.CAT);
		assertTrue(board[1][7].getCharacter().getType() == PieceType.CAT);
		assertTrue(board[0][2].getCharacter().getType() == PieceType.RAT);
		assertTrue(board[6][6].getCharacter().getType() == PieceType.RAT);
		assertTrue(board[2][2].getCharacter().getType() == PieceType.LEOPARD);
		assertTrue(board[4][6].getCharacter().getType() == PieceType.LEOPARD);
		assertTrue(board[4][2].getCharacter().getType() == PieceType.WOLF);
		assertTrue(board[2][6].getCharacter().getType() == PieceType.WOLF);
		assertTrue(board[6][2].getCharacter().getType() == PieceType.ELEPHANT);
		assertTrue(board[0][6].getCharacter().getType() == PieceType.ELEPHANT);
	}

	@Test
	void testNextTo() {
		assertTrue(m.nextTo(new Pair<Integer, Integer>(1,2), new Pair<Integer, Integer>(1,3)));
		assertTrue(m.nextTo(new Pair<Integer, Integer>(2,0), new Pair<Integer, Integer>(1,0)));
		
		assertFalse(m.nextTo(new Pair<Integer, Integer>(3,2), new Pair<Integer, Integer>(4,5)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(2,6), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(1,5), new Pair<Integer, Integer>(2,6)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(5,4), new Pair<Integer, Integer>(3,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(-1,6), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(2,-6), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(15,6), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(15,3), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(-2,-6), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(12,16), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(12,-6), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(-2,16), new Pair<Integer, Integer>(2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(2,6), new Pair<Integer, Integer>(-2,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(2,6), new Pair<Integer, Integer>(2,-4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(2,6), new Pair<Integer, Integer>(12,4)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(2,6), new Pair<Integer, Integer>(2,14)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(2,6), new Pair<Integer, Integer>(12,14)));
		assertFalse(m.nextTo(new Pair<Integer, Integer>(2,6), new Pair<Integer, Integer>(-2,-4)));

	}
	
	@Test
	// tests land movement
	final void testLandMove() {
		m.update(new Pair<Integer, Integer>(6,0), new Pair<Integer, Integer>(3,7));

		assertTrue(m.validMove(new Pair<Integer, Integer>(0,0), new Pair<Integer, Integer>(0,1), 1));
		assertTrue(m.validMove(new Pair<Integer, Integer>(0,0), new Pair<Integer, Integer>(1,0), 1));
		assertTrue(m.validMove(new Pair<Integer, Integer>(0,8), new Pair<Integer, Integer>(0,7), 2));
		assertTrue(m.validMove(new Pair<Integer, Integer>(0,8), new Pair<Integer, Integer>(1,8), 2));
		
		assertTrue(m.validMove(new Pair<Integer, Integer>(1,1), new Pair<Integer, Integer>(1,0), 1));
		assertTrue(m.validMove(new Pair<Integer, Integer>(1,1), new Pair<Integer, Integer>(0,1), 1));
		assertTrue(m.validMove(new Pair<Integer, Integer>(3,7), new Pair<Integer, Integer>(3,8), 1));
		
		assertFalse(m.validMove(new Pair<Integer, Integer>(0,0), new Pair<Integer, Integer>(0,2), 1));
		assertFalse(m.validMove(new Pair<Integer, Integer>(0,0), new Pair<Integer, Integer>(2,0), 1));
		assertFalse(m.validMove(new Pair<Integer, Integer>(0,8), new Pair<Integer, Integer>(0,6), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(0,8), new Pair<Integer, Integer>(2,8), 2));
		
		m.update(new Pair<Integer, Integer>(0,8), new Pair<Integer, Integer>(2,8));
		m.update(new Pair<Integer, Integer>(6,8), new Pair<Integer, Integer>(6,7));
		
		assertFalse(m.validMove(new Pair<Integer, Integer>(6,7), new Pair<Integer, Integer>(6,8), 1));
		assertFalse(m.validMove(new Pair<Integer, Integer>(6,7), new Pair<Integer, Integer>(6,6), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(4,4), new Pair<Integer, Integer>(4,5), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(2,8), new Pair<Integer, Integer>(3,8), 2));
	}
	
	@Test
	// test water movement
	final void testWaterMove() {
		m.update(new Pair<Integer, Integer>(5,7), new Pair<Integer, Integer>(5,6));
		m.update(new Pair<Integer, Integer>(6,6), new Pair<Integer, Integer>(6,4));
		m.update(new Pair<Integer, Integer>(0,6), new Pair<Integer, Integer>(0,4));
		m.update(new Pair<Integer, Integer>(0,8), new Pair<Integer, Integer>(0,5));
		m.update(new Pair<Integer, Integer>(1,7), new Pair<Integer, Integer>(1,6));
		m.update(new Pair<Integer, Integer>(6,8), new Pair<Integer, Integer>(6,5));
		
		assertFalse(m.validMove(new Pair<Integer, Integer>(5,6), new Pair<Integer, Integer>(5,5), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(0,4), new Pair<Integer, Integer>(1,4), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(0,5), new Pair<Integer, Integer>(1,5), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(1,6), new Pair<Integer, Integer>(1,6), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(2,6), new Pair<Integer, Integer>(2,5), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(4,6), new Pair<Integer, Integer>(4,5), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(6,5), new Pair<Integer, Integer>(5,5), 2));

		assertTrue(m.validMove(new Pair<Integer, Integer>(6,4), new Pair<Integer, Integer>(5,4), 2));
	}
	
	@Test
	//Test pieces that can jump over water
	final void testWaterJump() {
		//Vertical
		m.update(new Pair<Integer, Integer>(6,8), new Pair<Integer, Integer>(5,6)); //Lion
		m.update(new Pair<Integer, Integer>(0,8), new Pair<Integer, Integer>(1,6)); //Tiger
		
		assertTrue(m.validMove(new Pair<Integer, Integer>(1,6), new Pair<Integer, Integer>(1,2), 2));
		assertTrue(m.validMove(new Pair<Integer, Integer>(5,6), new Pair<Integer, Integer>(5,2), 2));
		assertFalse(m.validMove(new Pair<Integer, Integer>(4,6), new Pair<Integer, Integer>(4,2), 2));
		
		//Horizontal
		m.update(new Pair<Integer, Integer>(5,7), new Pair<Integer, Integer>(6,5));
		m.update(new Pair<Integer, Integer>(5,6), new Pair<Integer, Integer>(6,4));
		m.update(new Pair<Integer, Integer>(1,6), new Pair<Integer, Integer>(0,5));
		
		assertTrue(m.validMove(new Pair<Integer, Integer>(0,5), new Pair<Integer, Integer>(3,5), 2));
		assertTrue(m.validMove(new Pair<Integer, Integer>(6,4), new Pair<Integer, Integer>(3,4), 2));
		assertTrue(m.validMove(new Pair<Integer, Integer>(6,5), new Pair<Integer, Integer>(3,5), 2));

	}
	
	@Test
	// test attacks
	final void testValidAttack() {
		// test normal valid attack
		m.update(new Pair<Integer, Integer>(5,7), new Pair<Integer, Integer>(3,2));
		assertTrue(m.validAttack(new Pair<Integer, Integer>(3,2), new Pair<Integer, Integer>(4,2)));
		
		// tests rat vs. elephant
		m.update(new Pair<Integer, Integer>(0,6), new Pair<Integer, Integer>(0,3));
		assertFalse(m.validAttack(new Pair<Integer, Integer>(0,3), new Pair<Integer, Integer>(0,2)));
		assertTrue(m.validAttack(new Pair<Integer, Integer>(0,2), new Pair<Integer, Integer>(0,3)));
		
		//test with piece in trap
		m.update(new Pair<Integer, Integer>(0,0), new Pair<Integer, Integer>(3,7));
		m.update(new Pair<Integer, Integer>(4,6), new Pair<Integer, Integer>(3,6));
		
		assertFalse(m.validAttack(new Pair<Integer, Integer>(3,7), new Pair<Integer, Integer>(3,6)));
		assertTrue(m.validAttack(new Pair<Integer, Integer>(3,6), new Pair<Integer, Integer>(3,7)));
		
		// tests illegal same rank attack
		m.update(new Pair<Integer, Integer>(6,8), new Pair<Integer, Integer>(1,0));
		assertFalse(m.validAttack(new Pair<Integer, Integer>(1,0), new Pair<Integer, Integer>(6,8)));
		
		// test an illegal attack of a higher rank
		m.update(new Pair<Integer, Integer>(2,2), new Pair<Integer, Integer>(0,7));
		assertFalse(m.validAttack(new Pair<Integer, Integer>(0,7), new Pair<Integer, Integer>(0,8)));
		
		// tests illegal move to an already occupied square of one's own team
		m.update(new Pair<Integer, Integer>(1,7), new Pair<Integer, Integer>(1,8));
		assertFalse(m.validAttack(new Pair<Integer, Integer>(1,8), new Pair<Integer, Integer>(0,8)));
	} 


	
}
