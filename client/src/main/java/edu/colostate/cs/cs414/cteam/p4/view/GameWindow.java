package edu.colostate.cs.cs414.cteam.p4.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;



public class GameWindow {
	/** Controller **/
	ClientController controller;
	
	/** UI Elements **/
	private JFrame frame;
	private JLabel message;
	private TilePanel[][] tiles;
	private CardLayout cardLayout;
	private JPanel cards;
	private JButton submitTurnButton;
	JLabel boardImage;
	JLayeredPane gamePanel;
	JPanel tileContainer;
	
	/** Game components **/
	private int currentPlayer;
	private boolean moveInProgress;
	private int fromX;
	private int fromY;
	private UserProfile profile;
	private int opponent;
	private boolean local = false;
	
	// starts a new game window
	
	public GameWindow(ClientController controller, int user) {
		this.controller = controller;
		this.currentPlayer = user;
		moveInProgress = false;
		profile = new UserProfile(this, controller, user);		
		startFrame();
		createBoard();
		display();
	}
	
	private void startFrame() {
		frame = new JFrame("Jungle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 800));
		frame.setResizable(false);
		GraphicsConfiguration gc = frame.getGraphicsConfiguration();
		Rectangle bounds = gc.getBounds();
		frame.setLocation((int) ((bounds.width / 2) - (600 / 2)),
                          (int) ((bounds.height / 2) - (800 / 2))); 
		frame.getContentPane().setLayout(null);
		try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}	
	}
	
	// sets up the gameboard display and creates all the game tiles (without pieces)
	public void createBoard() {
        gamePanel = new JLayeredPane();  // layered panel for putting pieces over the game board
        gamePanel.setPreferredSize(new Dimension(500, 600));
        gamePanel.setLayout(null);
        gamePanel.setBounds(50, 78, 500, 600);
        ImageIcon icon = createImageIcon("/boardtransparent.png", "gameboard"); // game board image
        boardImage = new JLabel(icon);
        boardImage.setBounds(0, 0, 500, 600);
        tileContainer = new JPanel();		 // panel holding all 63 tiles
        tileContainer.setLayout(new GridLayout(9, 7));
        tileContainer.setBounds(16, 8, 465, 585);
        tileContainer.setOpaque(false);
        gamePanel.add(this.boardImage, 1, 0);
        gamePanel.add(tileContainer, 2, 0);    // tileContainer (2) is displayed on top of gamePanel (1)
        tiles = new TilePanel[9][7];
        
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 7; j++) {
        		tiles[i][j] = new TilePanel(i, j, this);
        		tileContainer.add(tiles[i][j]);   // adds game tile to ui

        	}
        }
        
        message = new JLabel("It's Your Turn", null, JLabel.CENTER);
        message.setVerticalTextPosition(JLabel.CENTER);
        message.setHorizontalTextPosition(JLabel.CENTER); 
        message.setBounds(50, 670, 500, 44);
        message.setFont(new Font("SansSerif", Font.PLAIN, 22));
        JPanel board = new JPanel(null);
        cards = new JPanel();
        cards.setLayout(new CardLayout(0, 0));
        cards.setPreferredSize(new Dimension(600, 800));
        cards.setBounds(0, 0, 600, 800); 
        board.setPreferredSize(new Dimension(600, 800));
        board.setBounds(0, 0, 600, 800); 
        board.add(gamePanel);
        board.add(message);
        JPanel card2 = profile.get();

		this.cardLayout = (CardLayout) cards.getLayout();
		cards.add(card2, "user profile");
        cards.add(board, "game");
        JButton returnBurron = new JButton("Return");
        returnBurron.setFont(new Font("Dialog", Font.PLAIN, 13));
        returnBurron.setBounds(28, 21, 114, 25);
        board.add(returnBurron);
        
        submitTurnButton = new JButton("Submit Turn");
        submitTurnButton.setFont(new Font("DejaVu Sans", Font.PLAIN, 12));
        submitTurnButton.setBounds(236, 732, 131, 25);
        board.add(submitTurnButton);
        
        JButton btnNewButton = new JButton("Game Rules");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		JFrame jp1 = new JFrame();
				 JTextArea field = new JTextArea();
				field.setText(" Movement\r\n" + 
						"1.Players alternate moves with Blue moving first. \r\n" + 
						"2.During their turn, a player must move.\r\n" + 
						"3. All pieces can move one square horizontally or vertically (not diagonally).\r\n" + 
						"4. A piece may not move into its own den.\r\n" + 
						"5. Animals of either side can move into and out of any trap square.\r\n" + 
						"6.There are special rules related to the water squares:\r\n" + 
						"The rat is the only animal that may go onto a water square.\r\n" + 
						"The lion and tiger can jump over a river horizontally or vertically. \r\n" + 
						"They jump from a square on one edge of the river to the next non-water square on the other side.\r\n" + 
						" If that square contains an enemy piece of equal or lower rank, the lion or tiger capture it as part of their jump.\r\n" + 
						" A jumping move is blocked (not permitted) if a rat of either colour currently occupies any of the intervening water squares.\r\n" + 
						"Capturing\r\n" + 
						"1.Animals capture opponent pieces by \"killing/eating\" them (the attacking piece replaces the captured piece on its square; The captured piece is removed from the game. \r\n" + 
						"A piece can capture any enemy piece that has the same or lower rank, with the following exceptions:\r\n" + 
						" The rat can \"kill\" (capture) an elephant, but only from a land square, not from a water square. \r\n" + 
						"Many published versions of the game say the rat kills the elephant by \"running into its ear and gnawing into its brain\".\r\n" + 
						"3. A rat in the water is invulnerable to capture by any piece on land.\r\n" + 
						" (Therefore, a rat in the water can only be killed by another rat in the water.)\r\n" + 
						"4. A piece that enters one of the opponent's trap squares is reduced in rank to 0.\r\n" + 
						" Thus, the trapped piece may be captured by the defending side with any piece, regardless of rank. \r\n" + 
						"A trapped piece has its normal rank restored when it exits an opponent's trap square.\r\n" + 
						"\r\n" + 
						"");
				 field.setEditable(false);
				 jp1.add(field);
				 jp1.pack();
				 jp1.setVisible(true);
				 jp1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				 
        	}
        });
        btnNewButton.setBounds(427, 21, 120, 25);
        board.add(btnNewButton);
        returnBurron.addActionListener(new ReturnButtonListener());
        frame.getContentPane().add(cards);
        
        submitTurnButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
    			/* handle submit turn */
				message.setText("It's " + opponent + "'s Turn");
				submitTurnButton.setEnabled(false);
//        		if (turn_result != 0) {   // win
//					message.setText("You win!");
//				} else {
//					message.setText("It's " + opponent + "'s Turn");
//				}
        	}
        });
	}
	
	public void clearBoard() {
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 7; j++) {
        		tiles[i][j].clear();
        	}
        }
	}
	
	public void changeCard(int card) {
		switch (card) {
		case 0:
			if(local == false) {
				profile.updateCurrentGames();
			}
			local = false;
			cardLayout.show(cards, "user profile");
			break;
		case 1:
			cardLayout.show(cards, "game");
			break;
		}
	}
	
	// makes the gui visible
	public void display() {       
        frame.pack();
        frame.setVisible(true);
	}

	
	// starts a new local game, setting up gamepiece icons and adding starting pieces to board
	public void newLocalGame() {
		local = true;
		clearBoard();
		
		currentPlayer = 1;  // 1 is the top player
		message.setText("Player " + currentPlayer + ": Make a move");
		if (currentPlayer == 1) {
			message.setForeground(new Color(226, 34, 34));
		} else {
			message.setForeground(new Color(42, 91, 224));
		}
		
		String[] default_setup = new String[16];
		default_setup[0] = "l100";
		default_setup[1] = "l268";
		default_setup[2] = "t160";
		default_setup[3] = "t208";
		default_setup[4] = "d111";
		default_setup[5] = "d257";
		default_setup[6] = "c151";
		default_setup[7] = "c271";
		default_setup[8] = "r102";
		default_setup[9] = "r266";
		default_setup[10] = "h122";
		default_setup[11] = "h246";
		default_setup[12] = "w142";
		default_setup[13] = "w226";
		default_setup[14] = "e162";
		default_setup[15] = "e206";
		
		setUpPieces(default_setup);
		controller.newLocalMatch();
	}
	
	public void loadGame(int user, int matchID) {
		if(controller.retrieveGame(matchID)) {
			String[] positions = controller.getPositions();
			this.currentPlayer = controller.getActivePlayer();
			
			String[] players = controller.getPlayers().split("-");
			if(user == Integer.parseInt(players[0])) {
				this.opponent = Integer.parseInt(players[1]);
			} else {
				this.opponent = Integer.parseInt(players[0]);
			}
	        
			message.setText("Player " + currentPlayer + ": Make a move");
			if (currentPlayer == 1) {
				message.setForeground(new Color(226, 34, 34));
			} else {
				message.setForeground(new Color(42, 91, 224));
			}
			
			clearBoard();	
			setUpPieces(positions);
		}
	}
	
	public void setUpPieces(String[] piece_positions) {
		ImageIcon lion1 = createImageIcon("/lion1.png", "lion");
		ImageIcon lion2 = createImageIcon("/lion2.png", "lion");
		ImageIcon tiger1 = createImageIcon("/tiger1.png", "tiger");
		ImageIcon tiger2 = createImageIcon("/tiger2.png", "tiger");
		ImageIcon dog1 = createImageIcon("/dog1.png", "dog");
		ImageIcon dog2 = createImageIcon("/dog2.png", "dog");
		ImageIcon cat1 = createImageIcon("/cat1.png", "cat");
		ImageIcon cat2 = createImageIcon("/cat2.png", "cat");
		ImageIcon rat1 = createImageIcon("/rat1.png", "rat");
		ImageIcon rat2 = createImageIcon("/rat2.png", "rat");
		ImageIcon cheetah1 = createImageIcon("/cheetah1.png", "cheetah");
		ImageIcon cheetah2 = createImageIcon("/cheetah2.png", "cheetah");
		ImageIcon wolf1 = createImageIcon("/wolf1.png", "wolf");
		ImageIcon wolf2 = createImageIcon("/wolf2.png", "wolf");
		ImageIcon elephant1 = createImageIcon("/elephant1.png", "elephant");
		ImageIcon elephant2 = createImageIcon("/elephant2.png", "elephant");  
		
		System.out.println("Setting piece positions");
		System.out.println(piece_positions[0]);
		for(int i = 0; i < piece_positions.length; i++) {
			int y = Character.getNumericValue(piece_positions[i].charAt(2));
			int x = Character.getNumericValue(piece_positions[i].charAt(3));
			System.out.println("Piece: " + piece_positions[i].charAt(0));
			switch(piece_positions[i].charAt(0)) {
				case 'l':
					if(piece_positions[i].charAt(1) == '1') {
						tiles[x][y].setPiece(lion1);
					} else {
						tiles[x][y].setPiece(lion2);			
					}
					break;
				case 't':
					if(piece_positions[i].charAt(1) == '1') {
						tiles[x][y].setPiece(tiger1);
					} else {
						tiles[x][y].setPiece(tiger2);			
					}
					break;
				case 'd':
					if(piece_positions[i].charAt(1) == '1') {
						tiles[x][y].setPiece(dog1);
					} else {
						tiles[x][y].setPiece(dog2);			
					}
					break;
				case 'c':
					if(piece_positions[i].charAt(1) == '1') {
						tiles[x][y].setPiece(cat1);
					} else {
						tiles[x][y].setPiece(cat2);			
					}
					break;
				case 'r':
					if(piece_positions[i].charAt(1) == '1') {
						tiles[x][y].setPiece(rat1);
					} else {
						tiles[x][y].setPiece(rat2);			
					}
					break;
				case 'h':
					if(piece_positions[i].charAt(1) == '1') {
						tiles[x][y].setPiece(cheetah1);
					} else {
						tiles[x][y].setPiece(cheetah2);			
					}
					break;
				case 'w':
					if(piece_positions[i].charAt(1) == '1') {
						tiles[x][y].setPiece(wolf1);
					} else {
						tiles[x][y].setPiece(wolf2);			
					}
					break;
				case 'e':
					if(piece_positions[i].charAt(1) == '1') {
						tiles[x][y].setPiece(elephant1);
					} else {
						tiles[x][y].setPiece(elephant2);
						System.out.println(tiles[6][0].hasPiece());
					}
					break;
			}
				
		}
	}

	public void clickHandler(int y, int x) {

	   System.out.println("Clicked: " + y + ", " + x);
	   System.out.println("Move in progress: " + moveInProgress);
	   
	   // a click on a piece, starting a move
	   if (tiles[y][x].hasPiece() && !moveInProgress) {
		   fromX = x;	   
		   fromY = y;
		   moveInProgress = true;
	   // a click on a second square indicating move destination
	   } else if (moveInProgress) {
		   move(fromX, fromY, x, y); //swapped because the board uses y,x and everything else uses x,y
		   moveInProgress = false;
	   }
		
	}
	
	public void move(int startX, int startY, int toX, int toY) {
		
		System.out.println(startX + ", " + startY + " to " + toX + ", " + toY);
		int result = controller.processMove(startX, startY, toX, toY, currentPlayer);
		
		if (result != -1 ) {
			System.out.println("updating board");
			Icon animal = tiles[startY][startX].getIcon();
			tiles[startY][startX].clear();
			tiles[toY][toX].setPiece(animal);
			System.out.println("success");
			if(result == 1111) {
				message.setForeground(new Color(51,51,51));
				message.setText("Game not started yet.");
			} else if (result != 0) {
				message.setForeground(new Color(51,51,51));
				message.setText("Player " + result + " wins!");
				currentPlayer = 0;
			} else {
				currentPlayer = currentPlayer == 2 ? 1 : 2;
				if (currentPlayer == 1) {
					message.setForeground(new Color(226, 34, 34));
				} else {
					message.setForeground(new Color(42, 91, 224));
				}
				message.setText("Player " + currentPlayer + ": Make a move");
			}
			
		} else {
			System.out.println("move failed");
		}
	}
	
	// returns an ImageIcon, or null if the path was invalid. 
	protected ImageIcon createImageIcon(String path, String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } 
	    System.out.println("Can't find " + path);
        return null;
	}
    
	// start game button handler
	private class ReturnButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clearBoard();
			changeCard(0);
		}
	}
}