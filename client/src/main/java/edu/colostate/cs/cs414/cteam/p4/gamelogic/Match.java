package edu.colostate.cs.cs414.cteam.p4.gamelogic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Match {

	private int matchID;
	private int player1ID;
	private int player2ID;
	private int activeplayer;
	private Tile[][] board;
	private int player1Pieces; // player 1 starts at the top of the board
	private int player2Pieces; // player 2 starts at the bottom
	private int status;
	private Date date;

	public Match(int matchID, int player1ID, int player2ID) {
		this.matchID = matchID;
		this.player1ID = player1ID;
		this.player2ID = player2ID;
		this.activeplayer = player1ID;
		
		date = new Date();
		player1Pieces = player2Pieces = 8;
		status = 1111; // game not started
		createBoard();
		
		pieceSetup();
	}

	public void pieceSetup() {
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

	public Match(String match_string) {
		//Match string is in this format: <matchID>-<player1>-<player2>-<active player>-<date>-<status>-<pos1:pos2:po3...>
		String[] match_parts = match_string.split("-");
		this.matchID = Integer.parseInt(match_parts[0]);
		this.player1ID = Integer.parseInt(match_parts[1]);
		this.player2ID = Integer.parseInt(match_parts[2]);
		this.activeplayer = Integer.parseInt(match_parts[3]);
		
		 //this is necessary for the rest of the game logic, which only deals with player numbers of 1 and 2
		if(this.activeplayer == player1ID) {
			this.activeplayer = 1;
		} else {
			this.activeplayer = 2;
		}
		
		this.player1Pieces = 0;
		this.player2Pieces = 0;
		
		try {
			this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(match_parts[4]);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Match: Date corrupted");
		} 
		
		this.status = Integer.parseInt(match_parts[5]);
		loadBoard(match_parts[6].split(":"));
		
	}
	
	public void createBoard() {
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
	}
	
	public void loadBoard(String[] piece_positions) {
		createBoard();
		
		for(int i = 0; i < piece_positions.length; i++) {

			switch(piece_positions[i].charAt(0)) {
				case 'l':
					if(piece_positions[i].charAt(1) == '1') {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.LION,1));
						player1Pieces++;
					} else {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.LION,2));
						player2Pieces++;
					}
					break;
				case 't':
					if(piece_positions[i].charAt(1) == '1') {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.TIGER,1));
						player1Pieces++;
					} else {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.TIGER,2));	
						player2Pieces++;
					}
					break;
				case 'd':
					if(piece_positions[i].charAt(1) == '1') {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.DOG,1));	
						player1Pieces++;
					} else {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.DOG,2));
						player2Pieces++;
					}
					break;
				case 'c':
					if(piece_positions[i].charAt(1) == '1') {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.CAT,1));	
						player1Pieces++;
					} else {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.CAT,2));	
						player2Pieces++;
					}
					break;
				case 'r':
					if(piece_positions[i].charAt(1) == '1') {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.RAT,1));	
						player1Pieces++;
					} else {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.RAT,2));	
						player2Pieces++;
					}
					break;
				case 'h':
					if(piece_positions[i].charAt(1) == '1') {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.LEOPARD,1));
						player1Pieces++;
					} else {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.LEOPARD,2));	
						player2Pieces++;
					}
					break;
				case 'w':
					if(piece_positions[i].charAt(1) == '1') {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.WOLF,1));
						player1Pieces++;
					} else {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.WOLF,2));	
						player2Pieces++;
					}
					break;
				case 'e':
					if(piece_positions[i].charAt(1) == '1') {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.ELEPHANT,1));
						player1Pieces++;
					} else {
						board[Character.getNumericValue(piece_positions[i].charAt(2))][Character.getNumericValue(piece_positions[i].charAt(3))].setCharacter(new GamePiece(Character.getNumericValue(piece_positions[i].charAt(2)), Character.getNumericValue(piece_positions[i].charAt(3)), PieceType.ELEPHANT,2));
						player2Pieces++;
					}
					break;
			}		
		}

		
	}
	
	// use to check for a win
	// 0 means nobody has won yet, otherwise return playerID
	public void checkStatus() {
		if (board[3][0].hasCharacter() || player1Pieces == 0) {
			status = player2ID;
		}
		else if (board[3][8].hasCharacter() || player2Pieces == 0) {
			status = player1ID;
		}
		System.out.println(status);
	}

	public int status() {
		return status;
	}
	
	public String getDate() {
		// Uses this to display: 	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}
	
	public int getMatchID() {
		return matchID;
	}

	public int getStartPlayerID() {
		return this.player1ID;
	}

	public int getActivePlayer() {
		return activeplayer;
	}
	
	public String getPlayers() {
		//System.out.println(this.player1ID + "-" + this.player2ID);
		return this.player1ID + "-" + this.player2ID;
	}
	
	public String[] getBoard(){
		String[] string_board = new String[player1Pieces + player2Pieces];
		int counter = 0;
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[0].length; j++) {
				if(board[i][j].hasCharacter()) {
					string_board[counter] = this.board[i][j].getCharacter().toString() + this.board[i][j].getCharacter().getTeam() + i + j;
					counter++;
				}
			}
		}
		return string_board;
	}
	
	// Two possible return cases:
	//  0 : Move not possible
	//  1 : Moving (and attacking, if applicable) are possible
	public boolean validMove(int fromX, int fromY, int toX, int toY, int player) {
		boolean valid = false;
		
		//If the destination contains the other player's piece, it is an attack. Otherwise, it is a move
		if(board[toX][toY].hasCharacter() && board[toX][toY].getCharacter().getTeam() != player) {
			System.out.println("System.Match: Move Type: Attack");
			if (!validAttack(fromX, fromY, toX, toY)) {
				//Attack is invalid
				return valid;
			}
			
			System.out.println("System.Match: Valid Attack");
		} else {
			System.out.println("System.Match: Move Type: Move");
		}
		
		if (!board[fromX][fromY].hasCharacter()) { 	//Check if there is a valid board piece			
			System.out.println("System.Match: No piece selected");
			System.out.println("System.Match: " + fromX + ", " + fromY + " : " + board[fromX][fromY].hasCharacter());
			return valid;
		} else if(board[fromX][fromY].getCharacter().getTeam() != player) { // Check if it the correct player's piece
			System.out.println("System.Match: Selected other player's piece");
			return valid;
			
		} else if(board[toX][toY].hasCharacter() && board[toX][toY].getCharacter().getTeam() == board[fromX][fromY].getCharacter().getTeam()) { // Check if space is occupied by the current player
			return valid;
		} else if(board[toX][toY].getType().equals(TileType.DEN1) && player == 1) {
			return valid;
			
		} else if(board[toX][toY].getType().equals(TileType.DEN2) && player == 2) {
			return valid;
			
		} else if(nextTo(fromX, fromY, toX, toY) || validRiverJump(fromX, fromY, toX, toY, board[fromX][fromY].getCharacter().getType())) {
			if(!board[toX][toY].getType().equals(TileType.WATER) || board[fromX][fromY].getCharacter().getType().equals(PieceType.RAT)) {
				valid = true; //Move is valid
				System.out.println("System.Match: Valid Move");
			}
		}
		
		return valid;
	}
	
	//Since it is possible to attack without moving, a key is used to determined if a piece is being moved or not
	public void update(int fromX, int fromY, int toX, int toY) {
		if(board[toX][toY].hasCharacter()) {
			if(board[toX][toY].getCharacter().getTeam() == 1) {
				player1Pieces--;
			} else {
				player2Pieces--;
			}
			
			board[toX][toY].removeCharacter();
		}
		
		//Move the piece
		board[toX][toY].setCharacter(board[fromX][fromY].getCharacter());
		board[fromX][fromY].removeCharacter();
		System.out.println("System.Match: Updated tile: " + board[toX][toY].hasCharacter());
		
		checkStatus();
	}
	
	public boolean nextTo(int fromX, int fromY, int toX, int toY) {
		if (toX > 6 || toX < 0 || toY < 0 || toY > 8) {
			return false;
		}
		
		if(Math.hypot(fromX - toX, fromY - toY) == 1.0) {
			return true;
		}
		
		System.out.println(fromX + ", " + fromY);
		System.out.println(toX + ", " + toY);
		
		System.out.println(Math.abs(fromX - toX));
		System.out.println(Math.abs(fromY - toY));
		
		return false;
	}
	
	//Tiger, Lion, and Leopard pieces can jump over the river, so a special case must be made
	private boolean validRiverJump(int fromX, int fromY, int toX, int toY, PieceType type) {
		 // Piece is on a bank above or below the water - Only applicable for lions and tigers
		if(type.equals(PieceType.LION) || type.equals(PieceType.TIGER)) {
			if(fromY == 6) { 
				if(toX == 2 && fromX == toY) {
					return true;
				}
			} else if (fromY == 2) {
				if(toY == 6 && fromX == toX) {
					return true;
				}
			}
		}
		
		// If piece is on the side of the water
		if (fromX == 0) {
			if(toX == 3 && fromY == toY) {
				return true;
			}
		}
		
		if (fromX == 3) {
			if((toX == 0 || toX == 6)&& fromY == toY) {
				return true;
			}
		}
		
		if (fromX == 6) {
			if(toX == 3 && fromY == toY) {
				return true;
			}
		}
		
		return false;
	}
	
	//Checks if an attack is valid
	public boolean validAttack(int fromX, int fromY, int toX, int toY) {
		if((!board[toX][toY].hasCharacter()) || (!board[fromX][fromY].hasCharacter())) {
			//System.out.println(board[toX][toY].hasCharacter());
			//System.out.println(board[0][5].hasCharacter());
			
			//System.out.println("tox, toy, fromx, fromy" + toX + "," + toY + "," + fromX + "," + fromY);

			//System.out.println("1**");
			return false;
		}
		
		int def_level = board[toX][toY].getCharacter().getType().ordinal();
		int att_level = board[fromX][fromY].getCharacter().getType().ordinal();
		
		if(board[fromX][fromY].getType().equals(TileType.TRAP)) {
			System.out.println("2**");
			return false; // can't attack from trap
		}
		
		if(board[fromX][fromY].getType().equals(TileType.WATER) && board[toX][toY].getType().equals(TileType.BLANK)) {
			System.out.println("3**");
			return false; // can't attack from water to land
		}
		
		if(board[toX][toY].getCharacter().getTeam() == board[fromX][fromY].getCharacter().getTeam()) {
			System.out.println("4**");
			return false; // can't attack friend
		}
		
		if(board[fromX][fromY].getCharacter().getType().equals(PieceType.ELEPHANT) && board[toX][toY].getCharacter().getType().equals(PieceType.RAT)) {
			System.out.println("5**");
			return false; //Opponent is an rat, and attacker is a elephant
		}
		
		if(board[toX][toY].getType().equals(TileType.TRAP)) {
			System.out.println("6**");
			return true; //Opponent is in a trap
		}
		
		if(board[fromX][fromY].getCharacter().getType().equals(PieceType.RAT) && board[toX][toY].getCharacter().getType().equals(PieceType.ELEPHANT)) {
			System.out.println("7**");
			return true; //Opponent is an elephant, and attacker is a rat
		} 
		
		if(att_level > def_level) {
			System.out.println("8**");
			return true;
		}
		
		return false;
	}
	
	//Match string format: <matchID>-<player1>-<player2>-<active player>-<date>-<status>-<pos1:pos2:po3...>
	
	public String toString() {
		String result = "";
		result = this.matchID + "-" + this.player1ID + "-" + this.player2ID + "-" + this.activeplayer + "-" +getDate() + "-" + this.status + "-";

		String[] board = getBoard();

		for(int i = 0; i < board.length; i++) {
			result += board[i];
			if(i != board.length -1) {
				result += ":";
			}
		}
		
		return result;
	}

	public int start() {
		this.status = 0;
		return this.activeplayer;
	}
}
