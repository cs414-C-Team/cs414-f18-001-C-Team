package edu.colostate.cs.cs414.cteam.p4.gamelogic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchTest {

	Tile[][] board = new Tile[7][9];
	
	@BeforeEach
	void setUp() throws Exception {
		
		//setting up own board to test on 
		
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 9; j++) {
					board[i][j] = new Tile(TileType.BLANK);
				}
			}
			// add water tiles
			for (int i = 1; i < 3; i++) {
				for (int j = 3; j < 6; j++) {
					board[i][j].setType(TileType.WATER);
					board[i + 3][j].setType(TileType.WATER);
				}
			}
			// add dens
			board[3][0].setType(TileType.DEN1);
			board[3][8].setType(TileType.DEN2);
			// add traps
			// 0,2 0,4 1,3
			// 8,2 8,4 7,3
			board[2][0].setType(TileType.TRAP);
			board[4][0].setType(TileType.TRAP);
			board[3][1].setType(TileType.TRAP);
			board[2][8].setType(TileType.TRAP);
			board[4][8].setType(TileType.TRAP);
			board[3][7].setType(TileType.TRAP);
		
		board[0][2].setCharacter(new GamePiece(0, 2, PieceType.RAT,1));
		board[6][6].setCharacter(new GamePiece(6, 6, PieceType.RAT,2));
		board[5][1].setCharacter(new GamePiece(5, 1, PieceType.CAT, 1));
		board[1][7].setCharacter(new GamePiece(1, 7, PieceType.CAT, 2));
		board[4][2].setCharacter(new GamePiece(4, 2, PieceType.WOLF, 1));
		board[2][6].setCharacter(new GamePiece(2, 6, PieceType.WOLF, 2));
		board[1][1].setCharacter(new GamePiece(1, 1, PieceType.DOG, 1));
		board[5][7].setCharacter(new GamePiece(5, 7, PieceType.DOG, 2));
		board[2][2].setCharacter(new GamePiece(2, 2, PieceType.LEOPARD, 1));
		board[4][6].setCharacter(new GamePiece(4, 6, PieceType.LEOPARD, 2));
		board[6][0].setCharacter(new GamePiece(6, 0, PieceType.TIGER, 1));
		board[0][8].setCharacter(new GamePiece(0, 8, PieceType.TIGER, 2));
		board[0][0].setCharacter(new GamePiece(0, 0, PieceType.LION, 1));
		board[6][8].setCharacter(new GamePiece(6, 8, PieceType.LION, 2));
		board[6][2].setCharacter(new GamePiece(6, 2, PieceType.ELEPHANT, 1));
		board[0][6].setCharacter(new GamePiece(0, 6, PieceType.ELEPHANT, 2));
		
	}

	@Test
	final void testMatchIntIntInt() {
		Match m1= new Match(12, 1, 2);
		assertTrue(m1.getMatchID()==12);
		assertTrue(m1.getPlayers().equals("1-2"));
	}

	@Test
	final void testPieceSetup() {
		assertTrue(board[0][2].getCharacter().getType()==PieceType.RAT);
		assertTrue(board[6][6].getCharacter().getType()==PieceType.RAT);
		assertTrue(board[5][1].getCharacter().getType()==PieceType.CAT);
		assertTrue(board[1][7].getCharacter().getType()==PieceType.CAT);
		assertTrue(board[4][2].getCharacter().getType()==PieceType.WOLF);
		assertTrue(board[2][6].getCharacter().getType()==PieceType.WOLF);
		assertTrue(board[1][1].getCharacter().getType()==PieceType.DOG);
		assertTrue(board[5][7].getCharacter().getType()==PieceType.DOG);
		assertTrue(board[2][2].getCharacter().getType()==PieceType.LEOPARD);
		assertTrue(board[4][6].getCharacter().getType()==PieceType.LEOPARD);
		assertTrue(board[6][0].getCharacter().getType()==PieceType.TIGER);
		assertTrue(board[0][8].getCharacter().getType()==PieceType.TIGER);
		assertTrue(board[0][0].getCharacter().getType()==PieceType.LION);
		assertTrue(board[6][8].getCharacter().getType()==PieceType.LION);
		assertTrue(board[6][2].getCharacter().getType()==PieceType.ELEPHANT);
		assertTrue(board[0][6].getCharacter().getType()==PieceType.ELEPHANT);		
		
		
	}
/*
	@Test
	final void testMatchString() {
		
	}*/

	@Test
	final void testCreateBoard() {
		
		for (int i = 1; i < 3; i++) {
			for (int j = 3; j < 6; j++) {
				assertTrue(board[i][j].getType()==TileType.WATER);
				assertTrue(board[i + 3][j].getType()==(TileType.WATER));
			}
		}
		
		assertTrue(board[3][0].getType()==TileType.DEN1);
		assertTrue(board[3][8].getType()==TileType.DEN2);
		assertTrue(board[2][0].getType()==TileType.TRAP);
		assertTrue(board[4][0].getType()==TileType.TRAP);
		assertTrue(board[3][1].getType()==TileType.TRAP);
		assertTrue(board[2][8].getType()==TileType.TRAP);
		assertTrue(board[4][8].getType()==TileType.TRAP);
		assertTrue(board[3][7].getType()==TileType.TRAP);
		
	}


	@Test
	final void testCheckStatus() {
		Match m1= new Match(12, 1, 2);
		m1.checkStatus();
		assertTrue(m1.status()==1111);             //game not started yet
		m1.start();
		assertTrue(m1.status()==0);             //game begins	
	}


	@Test
	final void testGetPlayers() {
		Match m1= new Match(12, 1, 2);
		assertTrue(m1.getPlayers().equals("1-2"));
	}


	@Test
	final void testValidMove() {
		Match m1 = new Match(12,1,2);
		assertTrue(m1.validMove(0, 2, 0, 3, 1));  //moving piece of team 1 by player1 
		assertFalse(m1.validMove(0,2, 0, 4, 1));   //moving to tiles , making an invalid move
		assertTrue(m1.validMove(0, 2, 1, 2, 1));    
		assertFalse(m1.validMove(0, 2, 0, 3, 2));  //moving piece of team 1 by player 2
		assertFalse(m1.validMove(0, 2, 3, 4, 1));     //cannot move diagonally
		
		//to check if Game pieces can enter water
		
		assertFalse(m1.validMove(2, 2, 2, 3, 1));
		assertFalse(m1.validMove(4, 2, 4, 3, 1));
		board[2][2].removeCharacter();
		board[2][2].setCharacter(new GamePiece(2,2,PieceType.LION,1));  //placing them near water , to test
		assertFalse(m1.validMove(2, 2, 2, 3, 1));
		board[2][2].removeCharacter();
		board[2][2].setCharacter(new GamePiece(2,2,PieceType.TIGER,1));
		assertFalse(m1.validMove(2, 2, 2, 3, 1));
		board[2][2].removeCharacter();
		board[2][2].setCharacter(new GamePiece(2,2,PieceType.ELEPHANT,1));
		assertFalse(m1.validMove(2, 2, 2, 3, 1));
		board[2][2].removeCharacter();
		board[2][2].setCharacter(new GamePiece(2,2,PieceType.CAT,1));
		assertFalse(m1.validMove(2, 2, 2, 3, 1));
		board[2][2].removeCharacter();
		board[2][2].setCharacter(new GamePiece(2,2,PieceType.DOG,1));
		assertFalse(m1.validMove(2, 2, 2, 3, 1));
		
		//cannot attack their own team Gamepiece
		board[1][2].setCharacter(new GamePiece(1,2,PieceType.LION,1));
		assertFalse(m1.validMove(1, 2, 2, 2, 1));

	}

	@Test
	final void testUpdate() {
		Match m1= new Match(12,1,2);
		board[0][3].setCharacter(new GamePiece(0,3,PieceType.LION,2));
		// checking if a Gamepiece can update , when being attacked by another .
		//validAttack is checked before making an update.
		m1.update(0, 3, 0, 2);
		assertTrue(board[0][2].getCharacter().getType()==PieceType.RAT);      //Gamepiece updated
	
	}
	

	@Test
	final void testNextTo() {
		//checks if moves our beyond game area
		Match m1= new Match(12,1,2);
		assertFalse(m1.nextTo(0,8,0,9));          //outside game area horizontally
		assertFalse(m1.nextTo(0,8,0,6));		//cannot move two tiles vertically
		assertFalse(m1.nextTo(6,8,7,8));		//outside game area vertically
		assertFalse(m1.nextTo(0, 8,2, 8));			//cannot move two tiles horizontally
		
		
	}

	@Test
	final void testValidAttack() {
		Match m1= new Match(12,1,2);
		//To check if upper rank animals can be attacked by lower ranked animals except for RAT
		board[0][5].setCharacter(new GamePiece(0,5, PieceType.LION,1));
		assertFalse(m1.validAttack(0, 5, 0, 6));   //Lion cannot attack Elephant
		board[0][5].removeCharacter();
		board[0][5].setCharacter(new GamePiece(0,5, PieceType.RAT,1));
		//System.out.println(board[0][5].hasCharacter);
		//System.out.println(board[0][6].hasCharacter);
		
		//assertTrue(m1.validAttack(0, 5, 0, 6));       
		
		//Check if upper rank animal can attack lower rank animal
		//board[0][5].setCharacter(new GamePiece(0,5, PieceType.LION,1));
		//assertTrue(m1.validAttack(0, 6, 0, 5));		
		
	}


	

	@Test
	final void testStart() {
		Match m1 = new Match(12,1,2);
		m1.checkStatus();
		assertTrue(m1.status()==1111);
		m1.start();
		m1.checkStatus();
		assertTrue(m1.status()==0);
		
	}

}
