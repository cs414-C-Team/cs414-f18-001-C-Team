package edu.colostate.cs.cs414.cteam.p3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatchTest {
	   
	@Test
	void testMatchBoard() {
		Match m = new Match();
		Tile[][] board = m.getBoard();
		assertTrue(board[0][3].getType() == TileType.DEN);
		assertTrue(board[8][3].getType() == TileType.DEN);
		assertTrue(board[0][2].getType() == TileType.TRAP1);
		assertTrue(board[0][4].getType() == TileType.TRAP1);
		assertTrue(board[1][3].getType() == TileType.TRAP1);
		assertTrue(board[8][2].getType() == TileType.TRAP2);
		assertTrue(board[8][4].getType() == TileType.TRAP2);
		assertTrue(board[7][3].getType() == TileType.TRAP2);
		for (int i = 3; i < 6; i++) {
			for (int j = 1; j < 3; j++) {
				assertTrue(board[i][j].getType() == TileType.WATER);
				assertTrue(board[i][j + 3].getType() == TileType.WATER);
			}
		}	
		assertTrue(board[0][0].getCharacter().getType() == PieceType.LION);
		assertTrue(board[8][6].getCharacter().getType() == PieceType.LION);
		assertTrue(board[0][6].getCharacter().getType() == PieceType.TIGER);
		assertTrue(board[8][0].getCharacter().getType() == PieceType.TIGER);
		assertTrue(board[1][1].getCharacter().getType() == PieceType.DOG);
		assertTrue(board[7][5].getCharacter().getType() == PieceType.DOG);
		assertTrue(board[1][5].getCharacter().getType() == PieceType.CAT);
		assertTrue(board[7][1].getCharacter().getType() == PieceType.CAT);
		assertTrue(board[2][0].getCharacter().getType() == PieceType.RAT);
		assertTrue(board[6][6].getCharacter().getType() == PieceType.RAT);
		assertTrue(board[2][2].getCharacter().getType() == PieceType.LEOPARD);
		assertTrue(board[6][4].getCharacter().getType() == PieceType.LEOPARD);
		assertTrue(board[2][4].getCharacter().getType() == PieceType.WOLF);
		assertTrue(board[6][2].getCharacter().getType() == PieceType.WOLF);
		assertTrue(board[2][6].getCharacter().getType() == PieceType.ELEPHANT);
		assertTrue(board[6][0].getCharacter().getType() == PieceType.ELEPHANT);

	}

	
	@Test
	void testWin() {
		fail("Test yet implemented");
	}

	@Test
	void testPrintBoard() {
		fail("Test yet implemented");
	}

}
