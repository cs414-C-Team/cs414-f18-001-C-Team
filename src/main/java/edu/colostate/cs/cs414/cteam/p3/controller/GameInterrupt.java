package edu.colostate.cs.cs414.cteam.p3.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

import edu.colostate.cs.cs414.cteam.p3.model.GamePiece;
import edu.colostate.cs.cs414.cteam.p3.model.PieceType;
import edu.colostate.cs.cs414.cteam.p3.model.Tile;
import edu.colostate.cs.cs414.cteam.p3.model.TileType;

public class GameInterrupt {
	
	private Tile[][]board;
	
	
	
	//locs will be built from the db, it will hold all the locations of the pieces in the order:
	//rat(1,2), cat, wolf, dog, leopard, tiger, lion, elephant(1,2)
	public void buildGame() {
		int player1Pieces = 0;
		int player2Pieces = 0;
		ArrayList<PieceShell> locs = new ArrayList<PieceShell>();
		board = new Tile[7][9];
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
		
		for(int i = 0; i < locs.size(); i++) {
			PieceShell add = locs.get(i);
			board[add.getX()][add.getY()].setCharacter(new GamePiece(add.getX(), add.getY(), add.getType(), add.getTeam()));
			if(add.getTeam() == 1)
				player1Pieces++;
			else player2Pieces++;
		}
		
	}
	
	public void pauseGame() {
		
	}
}
