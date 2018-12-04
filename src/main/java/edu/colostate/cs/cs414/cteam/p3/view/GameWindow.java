package edu.colostate.cs.cs414.cteam.p3.view;
import edu.colostate.cs.cs414.cteam.p3.controller.FacadeController;
import edu.colostate.cs.cs414.cteam.p3.controller.Turn;
import edu.colostate.cs.cs414.cteam.p3.model.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class GameWindow {
	/** UI Elements. */
	private JFrame frame;
	private JLabel message;
	private TilePanel[][] tiles;
	private CardLayout cardLayout;
	private JPanel cards;
	private JButton submitTurnButton;
	
	/** Game components. */
	private FacadeController system;
	private int currentPlayer;
	private boolean moveInProgress;
	private int fromX;
	private int fromY;
	private UserProfile profile;
	private boolean localGame;
	private boolean myTurn;
	private String opponent;
	 
	
	/** starts a new game window */
	public GameWindow() {
		moveInProgress = false;
		system = new FacadeController(); //This is a placeholder and will be replaced once server-client relationship is set up
		profile = new UserProfile(this);
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

	/** Sets up the gameboard display and creates all the game tiles (without pieces). */
	public void createBoard() {
		JLayeredPane gamePanel = new JLayeredPane();  // layered panel for putting pieces over the game board
        gamePanel.setPreferredSize(new Dimension(500, 600));
        gamePanel.setLayout(null);
        gamePanel.setBounds(50, 63, 500, 600);
        ImageIcon icon = createImageIcon("../resources/boardtransparent.png", "gameboard"); // game board image
        JLabel boardImage = new JLabel(icon);
        boardImage.setBounds(0, 0, 500, 600);
        JPanel tileContainer = new JPanel();		 // panel holding all 63 tiles
        tileContainer.setLayout(new GridLayout(9, 7));
        tileContainer.setBounds(16, 8, 465, 585);
        tileContainer.setOpaque(false);
        gamePanel.add(boardImage, 1, 0);
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
        returnBurron.addActionListener(new ReturnButtonListener());
        frame.getContentPane().add(cards);
        
        submitTurnButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
    			/* handle submit turn */
    			
//        		if (turn_result != 0) {   // win
//					message.setText("You win!");
//				} else {
					message.setText("It's " + opponent + "'s Turn");
//				}
        	}
        });
	}
		
	public void changeCard(int card) {
		switch (card) {
		case 0:
			cardLayout.show(cards, "user profile");
			break;
		case 1:
			cardLayout.show(cards, "game");
			break;
		}
	}


	/** Makes the gui visible. */
	public void display() {       
        frame.pack();
        frame.setVisible(true);
	}
	
	public void localGame() {
		this.localGame = true;
		newGame();
	}

	/** Starts a new game, setting up gamepiece icons and adding starting pieces to board. */
	public void newGame() {
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 7; j++) {
        		tiles[i][j].clear();
        	}
        }
		system.newMatch();
		currentPlayer = 1;  // 2 is the top player maybe
		
		// Local game
		if (localGame) {
	        submitTurnButton.setVisible(false);
			message.setText("Player " + currentPlayer + ": Make a move");
			if (currentPlayer == 1) {
				message.setForeground(new Color(226, 34, 34));
			} else {
				message.setForeground(new Color(42, 91, 224));
			}
		// Create a new multiplayer game
		} else {
	        submitTurnButton.setVisible(true);
		}
		setUpPieces();
	}
	
	/** Loads a preexisting game, setting up gamepieces accordingly */
	public void loadGame(String game, String opponent, boolean myTurn) {
		this.myTurn = myTurn;
		this.opponent = opponent;
		this.localGame = false;
		
		for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 7; j++) {
        		tiles[i][j].clear();
        	}
        }
		if (myTurn) {  // it is our player's turn to make a move
			message.setText("It's Your Turn!");
			
		} else {   // view the board only
			message.setText("It's " + opponent + "'s Turn");
		}
		message.setForeground(Color.BLACK);
		
		/* load the game pieces */
		newGame(); // temporary
	}
	
	
	public void setUpPieces() {
		ImageIcon lion1 = createImageIcon("../resources/lion1.png", "lion");
		ImageIcon lion2 = createImageIcon("../resources/lion2.png", "lion");
		ImageIcon tiger1 = createImageIcon("../resources/tiger1.png", "tiger");
		ImageIcon tiger2 = createImageIcon("../resources/tiger2.png", "tiger");
		ImageIcon dog1 = createImageIcon("../resources/dog1.png", "dog");
		ImageIcon dog2 = createImageIcon("../resources/dog2.png", "dog");
		ImageIcon cat1 = createImageIcon("../resources/cat1.png", "cat");
		ImageIcon cat2 = createImageIcon("../resources/cat2.png", "cat");
		ImageIcon rat1 = createImageIcon("../resources/rat1.png", "rat");
		ImageIcon rat2 = createImageIcon("../resources/rat2.png", "rat");
		ImageIcon cheetah1 = createImageIcon("../resources/cheetah1.png", "cheetah");
		ImageIcon cheetah2 = createImageIcon("../resources/cheetah2.png", "cheetah");
		ImageIcon wolf1 = createImageIcon("../resources/wolf1.png", "wolf");
		ImageIcon wolf2 = createImageIcon("../resources/wolf2.png", "wolf");
		ImageIcon elephant1 = createImageIcon("../resources/elephant1.png", "elephant");
		ImageIcon elephant2 = createImageIcon("../resources/elephant2.png", "elephant");
		tiles[0][0].setPiece(lion1);  
		tiles[8][6].setPiece(lion2);  
		tiles[0][6].setPiece(tiger1);  
		tiles[8][0].setPiece(tiger2);  
		tiles[1][1].setPiece(dog1);  
		tiles[7][5].setPiece(dog2);  
		tiles[1][5].setPiece(cat1);  
		tiles[7][1].setPiece(cat2);  
		tiles[2][0].setPiece(rat1);  
		tiles[6][6].setPiece(rat2);  
		tiles[2][2].setPiece(cheetah1);  
		tiles[6][4].setPiece(cheetah2);  
		tiles[2][4].setPiece(wolf1);  
		tiles[6][2].setPiece(wolf2);  
		tiles[2][6].setPiece(elephant1);  
		tiles[6][0].setPiece(elephant2);  
	}
	
	public void clickHandler(int y, int x) {
	   System.out.println("Clicked: " + x + ", " + y);
	   System.out.println("Move in progress: " + moveInProgress);
   	   // a click on a piece, starting a move
	   if (myTurn || localGame) {
		   if (tiles[y][x].hasPiece() && !moveInProgress) {
			   fromX = x;	   
			   fromY = y;
			   tiles[fromY][fromX].setSelected(true);
			   tiles[fromY][fromX].highlight();  // highlights the selected square
			   moveInProgress = true;
		   // a click on a second square indicating move destination
		   } else if (moveInProgress) {
			   move(fromX, fromY, x, y);
			   moveInProgress = false;
			   tiles[fromY][fromX].setSelected(false);
			   tiles[fromY][fromX].unHighlight();
		   }
	   }
	}
	
	public void move(int startX, int startY, int toX, int toY) {
		Turn turn = system.getTurn();
		currentPlayer = turn.getPlayer();
		turn.moveFrom(startX, startY);
		turn.moveTo(toX, toY);
		int turn_result = system.processTurn(turn);
		
		if (turn_result != -1 ) {
			/* change board */
			System.out.println("updating board");
			Icon animal = tiles[startY][startX].getIcon();
			tiles[startY][startX].clear();
			tiles[toY][toX].setPiece(animal);
			System.out.println("success");
			
			if (localGame) {
				// Change displayed message
				if (turn_result != 0) {   // win
					message.setForeground(new Color(51,51,51));
					message.setText("Player " + turn_result + " wins!");
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
				message.setText("Submit turn?");
				myTurn = false;
			}
			// nonlocal game handled in submit turn event
			
		} else {
			System.out.println("move failed");
		}
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } 
	    System.out.println("Can't find " + path);
        return null;
	}
    
	/** Return button handler. */
	private class ReturnButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			changeCard(0);
		}
	}
}