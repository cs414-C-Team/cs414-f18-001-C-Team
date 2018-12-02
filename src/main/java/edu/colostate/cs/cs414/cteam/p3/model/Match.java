package edu.colostate.cs.cs414.cteam.p3.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.util.Pair;

public class Match {

	private Tile[][] board;
	private int player1Pieces; // player 1 starts at the top of the board
	private int player2Pieces; // player 2 starts at the bottom
	private int status;
	private Date date;

	public Match(Tile[][] board, int player1Peices, int player2Pieces) {
		this.board = board;
		this.player1Pieces = player1Pieces;
		this.player2Pieces = player2Pieces;
		status = 0;
	}

	public Match() { 
		player1Pieces = player2Pieces = 8;
		status = 0;
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
		// add animals
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

	// use to check for a win
	// 1 means player 1 wins, 2 means 2 wins, 0 means nobody has yet
	public void checkStatus() {
		if (board[3][7].hasCharacter() || player2Pieces == 0) {
			status = 1;
		}
		else if (board[3][8].hasCharacter() || player1Pieces == 0) {
			status = 2;
		}
	}

	public int status() {
		return status;
	}
	
	public Date getDate() {
		// Uses this to display: 	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return date;
	}
	
	// For JUnit test purposes
	public Tile[][] getBoard(){
		return board;
	}
	
	// Two possible return cases:
	//  0 : Move not possible
	//  1 : Moving (and attacking, if applicable) are possible
	public boolean validMove(Pair<Integer, Integer> from, Pair<Integer, Integer> to, int player) {
		boolean valid = false;
		
		//If the destination contains the other player's piece, it is an attack. Otherwise, it is a move
		if(board[to.getKey()][to.getValue()].hasCharacter() && board[to.getKey()][to.getValue()].getCharacter().getTeam() != player) {
			System.out.println("System.Match: Move Type: Attack");
			if (!validAttack(from, to)) {
				//Attack is invalid
				return valid;
			}
			
			System.out.println("System.Match: Valid Attack");
		} else {
			System.out.println("System.Match: Move Type: Move");
		}
		
		if (!board[from.getKey()][from.getValue()].hasCharacter()) { 	//Check if there is a valid board piece			
			System.out.println("System.Match: No piece selected");
			System.out.println("System.Match: " + from.getKey() + ", " + from.getValue() + " : " + board[from.getKey()][from.getValue()].hasCharacter());
			return valid;
		} else if(board[from.getKey()][from.getValue()].getCharacter().getTeam() != player) { // Check if it the correct player's piece
			System.out.println("System.Match: Selected other player's piece");
			return valid;
			
		} else if(board[to.getKey()][to.getValue()].hasCharacter()) { // Check if space is occupied by the current player
			return valid;
		} else if(board[to.getKey()][to.getValue()].getType().equals(TileType.DEN1) && player == 1) {
			return valid;
			
		} else if(board[to.getKey()][to.getValue()].getType().equals(TileType.DEN2) && player == 2) {
			return valid;
			
		} else if(nextTo(from, to) || validRiverJump(from, to, board[from.getKey()][from.getValue()].getCharacter().getType())) {
			if(!board[to.getKey()][to.getValue()].getType().equals(TileType.WATER) || board[from.getKey()][from.getValue()].getCharacter().getType().equals(PieceType.RAT)) {
				valid = true; //Move is valid
				System.out.println("System.Match: Valid Move");
			}
		}
		
		return valid;
	}
	
	//Since it is possible to attack without moving, a key is used to determined if a piece is being moved or not
	public void update(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
		System.out.print(to.getKey() + ", " + to.getValue());
		if(board[to.getKey()][to.getValue()].hasCharacter()) {
			if(board[to.getKey()][to.getValue()].getCharacter().getTeam() == 1) {
				player1Pieces--;
			} else {
				player2Pieces--;
			}
			
			board[to.getKey()][to.getValue()].removeCharacter();
		}
		
		//Move the piece
		board[to.getKey()][to.getValue()].setCharacter(board[from.getKey()][from.getValue()].getCharacter());
		board[from.getKey()][from.getValue()].removeCharacter();
		System.out.println("System.Match: Updated tile: " + board[to.getKey()][to.getValue()].hasCharacter());
		
		
		checkStatus();
	}
	
	public boolean nextTo(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
		if (to.getKey() > 6 || to.getKey() < 0 || to.getValue() < 0 || to.getValue() > 8) {
			return false;
		}
		
		if(Math.hypot(from.getKey() - to.getKey(), from.getValue() - to.getValue()) == 1.0) {
			return true;
		}
		
		System.out.println(from.getKey() + ", " + from.getValue());
		System.out.println(to.getKey() + ", " + to.getValue());
		
		System.out.println(Math.abs(from.getKey() - to.getKey()));
		System.out.println(Math.abs(from.getValue() - to.getValue()));
		
		return false;
	}
	
	//Tiger, Lion, and Leopard pieces can jump over the river, so a special case must be made
	private boolean validRiverJump(Pair<Integer, Integer> from, Pair<Integer, Integer> to, PieceType type) {
		 // Piece is on a bank above or below the water - Only applicable for lions and tigers
		if(type.equals(PieceType.LION) || type.equals(PieceType.TIGER)) {
			if(from.getValue() == 6) { 
				if(to.getValue() == 2 && from.getKey() == to.getKey()) {
					return true;
				}
			} else if (from.getValue() == 2) {
				if(to.getValue() == 6 && from.getKey() == to.getKey()) {
					return true;
				}
			}
		}
		
		// If piece is on the side of the water
		if (from.getKey() == 0) {
			if(to.getKey() == 3 && from.getValue() == to.getValue()) {
				return true;
			}
		}
		
		if (from.getKey() == 3) {
			if((to.getKey() == 0 || to.getKey() == 6)&& from.getValue() == to.getValue()) {
				return true;
			}
		}
		
		if (from.getKey() == 6) {
			if(to.getKey() == 3 && from.getValue() == to.getValue()) {
				return true;
			}
		}
		
		return false;
	}
	
	//Checks if an attack is valid
	public boolean validAttack(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
		if(!board[to.getKey()][to.getValue()].hasCharacter() || !board[from.getKey()][from.getValue()].hasCharacter()) {
			return false;
		}
		
		int def_level = board[to.getKey()][to.getValue()].getCharacter().getType().ordinal();
		int att_level = board[from.getKey()][from.getValue()].getCharacter().getType().ordinal();
		
		if(board[from.getKey()][from.getValue()].getType().equals(TileType.TRAP)) {
			return false; // can't attack from trap
		}
		
		if(board[from.getKey()][from.getValue()].getType().equals(TileType.WATER) && board[to.getKey()][to.getValue()].getType().equals(TileType.BLANK)) {
			return false; // can't attack from water to land
		}
		
		if(board[to.getKey()][to.getValue()].getCharacter().getTeam() == board[from.getKey()][from.getValue()].getCharacter().getTeam()) {
			return false; // can't attack friend
		}
		
		if(board[from.getKey()][from.getValue()].getCharacter().getType().equals(PieceType.ELEPHANT) && board[to.getKey()][to.getValue()].getCharacter().getType().equals(PieceType.RAT)) {
			return false; //Opponent is an rat, and attacker is a elephant
		}
		
		if(board[to.getKey()][to.getValue()].getType().equals(TileType.TRAP)) {
			return true; //Opponent is in a trap
		}
		
		if(board[from.getKey()][from.getValue()].getCharacter().getType().equals(PieceType.RAT) && board[to.getKey()][to.getValue()].getCharacter().getType().equals(PieceType.ELEPHANT)) {
			return true; //Opponent is an elephant, and attacker is a rat
		} 
		
		if(att_level > def_level) {
			return true;
		}
		
		return false;
	}
	
}
