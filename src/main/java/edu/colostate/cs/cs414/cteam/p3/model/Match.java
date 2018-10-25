package edu.colostate.cs.cs414.cteam.p3.model;

public class Match {

	private Tile[][] board;
	// player 1 starts at the top of the board
	// player 2 starts at the bottom
	private int player1Pieces;
	private int player2Pieces;

	public static void main(String[] args) {
		Match m = new Match();
		m.run();

	}

	public Match() {
		player1Pieces = player2Pieces = 8;
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
		board[0][0].setCharacter(new Liger(0, 0, board, PieceType.LION, 1));
		board[8][6].setCharacter(new Liger(8, 0, board, PieceType.LION, 2));
		board[0][6].setCharacter(new Liger(0, 6, board, PieceType.TIGER, 1));
		board[8][0].setCharacter(new Liger(8, 6, board, PieceType.TIGER, 2));
		board[1][1].setCharacter(new GamePiece(1, 1, board, PieceType.DOG, 1));
		board[7][5].setCharacter(new GamePiece(7, 5, board, PieceType.DOG, 2));
		board[1][5].setCharacter(new GamePiece(1, 5, board, PieceType.CAT, 1));
		board[7][1].setCharacter(new GamePiece(7, 1, board, PieceType.CAT, 2));
		board[2][0].setCharacter(new Rat(2, 0, board, 1));
		board[6][6].setCharacter(new Rat(6, 6, board, 2));
		board[2][2].setCharacter(new GamePiece(2, 2, board, PieceType.LEOPARD, 1));
		board[6][4].setCharacter(new GamePiece(6, 4, board, PieceType.LEOPARD, 2));
		board[2][4].setCharacter(new GamePiece(2, 4, board, PieceType.WOLF, 1));
		board[6][2].setCharacter(new GamePiece(6, 2, board, PieceType.WOLF, 2));
		board[2][6].setCharacter(new GamePiece(2, 6, board, PieceType.ELEPHANT, 1));
		board[6][0].setCharacter(new GamePiece(6, 0, board, PieceType.ELEPHANT, 2));
	}

	// use to check for a win
	// 1 means player 1 wins, 2 means 2 wins, 0 means nobody has yet
	public int win() {
		if (board[0][3].getCharacter() != null || player2Pieces == 0)
			return 1;
		else if (board[8][3].getCharacter() != null || player1Pieces == 0)
			return 2;
		else
			return 0;
	}

	// todo test
	public void run() {
		// basically just instantiate this class, then
		// take turns asking each player for their move, trying
		// the move, and then letting them try again if fail,
		// letting the other player move if success
		// check for a win after each move, if win, then print who won
		// you'll have to print the board after each successful turn for now
		// make sure to decrement pieces every time someone is killed

		int player = 1;
		int win = 0;
		while (win == 0) {
			printBoard();
			// ask(player) only allows moves on "player"'s pieces, returns from location,
			// toLocation, attack or move
			Move move = null;
			while (move == null) {
				move = Ask.ask(player, board, "Make a move!");
			}

			boolean passed = false;
			if (!board[move.getFromY()][move.getFromX()].hasCharacter)
				passed = false;// I know it's redundant, it's just for clarity
			else if (board[move.getFromY()][move.getFromX()].getCharacter().getTeam() != player)
				passed = false;// I know it's redundant, it's just for clarity
			else if (move.isAttack()) {
				passed = board[move.getFromY()][move.getFromX()].getCharacter().attack(move.getToY(), move.getToX());
				if (passed) {
					if (player == 1)
						player2Pieces--;
					else
						player1Pieces--;
				}
			} else {
				passed = board[move.getFromY()][move.getFromX()].getCharacter().move(move.getToY(), move.getToX());
			}
			if (!passed) {
				System.out.println("Move or attack failed!");
			}

			if (passed)
				player = player == 2 ? 1 : 2;
			// else stay on the same player, they'll need to move again
			win = win();
		}
		System.out.println("Player " + win + " wins!!!");
	}

	// todo make less terrible
	public void printBoard() {
		System.out.print(" ");
		for (int j = 0; j < board[0].length; j++) {
			System.out.print(" " + j);
		}
		System.out.println(" ");
		for (int j = 0; j < board[0].length; j++) {
			System.out.print("--");
		}
		System.out.println("-");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j].toString() + "|");
			}
			System.out.print(i + "\n -");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("--");
			}
			System.out.println();
		}
		System.out.print(" ");
		for (int j = 0; j < board[0].length; j++) {
			System.out.print(" " + j);
		}
		System.out.println(" ");
	}
}