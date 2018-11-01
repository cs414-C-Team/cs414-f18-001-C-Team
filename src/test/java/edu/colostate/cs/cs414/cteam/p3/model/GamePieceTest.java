package edu.colostate.cs.cs414.cteam.p3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GamePieceTest {
	Tile[][] board;
	
	@BeforeEach
	final void initializeTestBoard() {
		board = null;
		board = new Tile[9][7];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 7; j++) {
				board[i][j] = new Tile(TileType.BLANK);
			}
		}
		for (int i = 3; i < 6; i++) {
			for (int j = 1; j < 3; j++) {
				board[i][j].setType(TileType.WATER);
				board[i][j + 3].setType(TileType.WATER);
			}
		}
		board[0][3].setType(TileType.DEN);
		board[8][3].setType(TileType.DEN);
		board[0][2].setType(TileType.TRAP1);
		board[0][4].setType(TileType.TRAP1);
		board[1][3].setType(TileType.TRAP1);
		board[8][2].setType(TileType.TRAP2);
		board[8][4].setType(TileType.TRAP2);
		board[7][3].setType(TileType.TRAP2);
		
		// add test piece
		board[1][1].setCharacter(new GamePiece(1, 1, board, PieceType.DOG, 1));
	}

	@Test
	final void testGamePiece() {
		GamePiece piece1 = new GamePiece(0,0,null, PieceType.RAT, 1);
		assertTrue(piece1.getX()==0);
		assertTrue(piece1.getY()==0);
		assertTrue(piece1.getType()==PieceType.RAT);
		assertTrue(piece1.getTeam()==1);
	}
	
	@Test
	final void testNextTo() {
		GamePiece gp = board[1][1].getCharacter();
		assertTrue(gp.nextTo(1, 2) == true);
		assertTrue(gp.nextTo(1, 0) == true);
		assertTrue(gp.nextTo(0, 1) == true);
		assertTrue(gp.nextTo(2, 3) == false);
		assertTrue(gp.nextTo(3, 3) == false);
		assertTrue(gp.nextTo(12, 14) == false);
	}

	@Test
	// tests normal results for a valid move
	final void testValidMove() {
		GamePiece gp = board[1][1].getCharacter();
		gp.move(1, 2);
		assertTrue(board[1][1].hasCharacter() == false);
		assertEquals(gp, board[1][2].getCharacter());
	}
	
	@Test
	// test illegal water move (as not a rat)
	final void testWaterMove() {
		board[2][1].setCharacter(new GamePiece(2, 1, board, PieceType.DOG, 1));
		GamePiece gp = board[2][1].getCharacter();
		assertFalse(gp.move(3, 1));
	}
	
	//@Test boundaries todo
	
	@Test
	// test a successful attack
	final void testValidAttack() {
		GamePiece gp = board[1][1].getCharacter();  // dog
		board[1][0].setCharacter(new Rat(1, 0, board, 2));  // rat
		boolean result = gp.attack(1, 0);
		assertTrue(result);
		assertTrue(board[1][0].getCharacter() == gp);		
		assertTrue(!board[0][0].hasCharacter());		
	} 
	
	@Test
	// tests same rank attack
	final void testSameAnimalAttack() {
		GamePiece gp = board[1][1].getCharacter();  // dog
		board[1][0].setCharacter(new GamePiece(1, 1, board, PieceType.DOG, 2));  // dog
		boolean result = gp.attack(1, 0);
		assertTrue(result);
		assertTrue(board[1][0].getCharacter() == gp);		
		assertTrue(!board[0][0].hasCharacter());		
	} 
	
	@Test
	// test an illegal attack of a higher rank
	final void testInadequateRank() {
		GamePiece gp = board[1][1].getCharacter();  // dog
		board[1][0].setCharacter(new Liger(1, 0, board, PieceType.LION, 2));  // lion
		assertFalse(gp.attack(1, 0));
	}
	
	@Test
	// tests illegal move to an already occupied square of one's own team
	final void testSameTeam() {
		GamePiece gp = board[1][1].getCharacter();
		board[1][0].setCharacter(new Rat(1, 0, board, 1));
		assertFalse(gp.attack(1, 0));
	}


}
