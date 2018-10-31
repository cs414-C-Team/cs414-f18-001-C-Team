package edu.colostate.cs.cs414.cteam.p3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class LigerTest {
	
	Tile[][] board;

	@BeforeEach
	public void initialiseTestBoard() {
	board = new Tile[9][7];
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 7; j++) {
			board[i][j] = new Tile(TileType.BLANK);
		}
	}
	// add water tiles
	for (int i = 3; i < 6; i++) {
		for (int j = 1; j < 3; j++) {
			board[i][j].setType(TileType.WATER);
			board[i][j + 3].setType(TileType.WATER);
		}
	}

	// there should really be a better way to do this, but
	// I have no idea what that would be

	// add dens
	board[0][3].setType(TileType.DEN);
	board[8][3].setType(TileType.DEN);
	// add traps
	// 0,2 0,4 1,3
	// 8,2 8,4 7,3
	board[0][2].setType(TileType.TRAP1);
	board[0][4].setType(TileType.TRAP1);
	board[1][3].setType(TileType.TRAP1);
	board[8][2].setType(TileType.TRAP2);
	board[8][4].setType(TileType.TRAP2);
	board[7][3].setType(TileType.TRAP2);
	// add animals
     //myTeam
	board[0][0].setCharacter(new Liger(0, 0, board, PieceType.LION, 1));
	board[0][6].setCharacter(new Liger(0, 6, board, PieceType.TIGER, 1));
	
	//oppositeTeam
	board[1][6].setCharacter(new Rat(1, 6, board, 2));
	board[8][0].setCharacter(new Liger(8, 6, board, PieceType.TIGER, 2));
	
	
	
	
	}
	
	
	@Test
	final void moveTest() {
        
		GamePiece gp1=board[0][0].getCharacter();   //to check if Lion can move
		gp1.move(1,0);
		assertTrue(board[0][0].hasCharacter==false);
		assertTrue(board[1][0].getCharacter()==gp1);
		
		GamePiece gp2 =board[0][6].getCharacter();
		gp2.move(6,1);
		assertTrue(board[0][6].hasCharacter==false);
		assertTrue(board[6][1].getCharacter()==gp2);
		
		
	} 
	
/*	@Test 
	final void attackTest() {
		GamePiece gp= board[0][6].getCharacter();
		gp.attack(1, 6);
		assertTrue(gp.attack(1, 6)==true);
		
		
	}*/


	
	@Test 
	final void LigerExceptionTest() {
		
		
		assertThrows(IllegalArgumentException.class , () -> {
			@SuppressWarnings("unused")
			Liger piece = new Liger(2,3,board,PieceType.CAT,1);
		
			
		});
		
		
	}

	
	
	

}
