package edu.colostate.cs.cs414.cteam.p3.view;
import edu.colostate.cs.cs414.cteam.p3.controller.FacadeController;
import edu.colostate.cs.cs414.cteam.p3.controller.Turn;
import edu.colostate.cs.cs414.cteam.p3.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class GameWindow {
	
	/** UI Elements **/
	JFrame frame;
	JLabel boardImage;
	JLabel message;
	JLayeredPane gamePanel;
	JPanel tileContainer;
	TilePanel[][] tiles;
	Toolkit tk;
	
	/** Game components **/
	private FacadeController system;
	private int currentPlayer;
	private boolean moveInProgress;
	private int fromX;
	private int fromY;
	
	
	// starts a new game window
	
	public GameWindow() {
		moveInProgress = false;
		system = new FacadeController(); //This is a placeholder and will be replaced once server-client relationship is set up
		startFrame();
		createBoard();
		display();
	}
	
	private void startFrame() {
		frame = new JFrame("Jungle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 800));
		tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		int xPos = (screen.width / 2) - frame.getWidth();
		int yPos = (screen.height / 2) - frame.getHeight();
		frame.setLocation(xPos,  yPos);
		frame.setLayout(null);
	}
	
	// sets up the gameboard display and creates all the game tiles (without pieces)
	public void createBoard() {
        gamePanel = new JLayeredPane();  // layered panel for putting pieces over the game board
        gamePanel.setPreferredSize(new Dimension(500, 600));
        gamePanel.setLayout(null);
        gamePanel.setBounds(50, 0, 500, 600);
        ImageIcon icon = createImageIcon("../resources/boardtransparent.png", "gameboard"); // game board image
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
        		tiles[i][j] = new TilePanel(i, j);
        		tileContainer.add(tiles[i][j]);   // adds game tile to ui

        	}
        }
        JButton start = new JButton("New Game");
        message = new JLabel("", null, JLabel.CENTER);
        message.setVerticalTextPosition(JLabel.CENTER);
        message.setHorizontalTextPosition(JLabel.CENTER); 
        message.setBounds(50, 590, 500, 100);
        message.setFont(new Font("SansSerif", Font.PLAIN, 22));
        start.setBounds(225, 680, 150, 50);
        frame.add(gamePanel);
        frame.add(message);
        frame.add(start);
        start.addActionListener(new StartButtonListener());
        gamePanel.addMouseListener(new MouseClickListener());        	
        
	}

	// makes the gui visible
	public void display() {       
        frame.pack();
        frame.setVisible(true);
	}

	
	// starts a new game, setting up gamepiece icons and adding starting pieces to board
	public void newGame() {
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 7; j++) {
        		tiles[i][j].clear();
        	}
        }
		system.newMatch();
		currentPlayer = 1;  // 2 is the top player
		message.setText("Player " + currentPlayer + ": Make a move");
		setUpPieces();
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
	
	public void move(int startX, int startY, int toX, int toY) {
		
		System.out.println(startX + ", " + startY + " to " + toX + ", " + toY);
		Turn turn = system.getTurn();
		System.out.println(turn.getPlayer());
		currentPlayer = turn.getPlayer();
		
		turn.moveFrom(startX, startY);
		turn.moveTo(toX, toY);
		int turn_result = system.processTurn(turn);
		
		if (turn_result != -1 ) {
			System.out.println("updating board");
			Icon animal = tiles[startY][startX].getIcon();
			tiles[startY][startX].clear();
			tiles[toY][toX].setPiece(animal);
			System.out.println("success");
			if (turn_result != 0) {
				message.setText("Player " + turn_result + " wins!");
				currentPlayer = 0;
			} else {
				currentPlayer = currentPlayer == 2 ? 1 : 2;
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
	private class StartButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			newGame();
		}
	}

	// mouse click on board handler
	private class MouseClickListener implements MouseListener {
	   // Called when a mouse button is clicked
		@Override
		public void mouseClicked(MouseEvent e) {
		   int y = (e.getY() - 19) / 70;
		   int x = (e.getX() - 8) / 65;
		   // a click on a piece, starting a move
		   System.out.println("Clicked: " + x + ", " + y);
		   System.out.println("Move in progress: " + moveInProgress);
		   System.out.println("Has piece: " + tiles[y][x].hasPiece());
		   if (tiles[y][x].hasPiece() && !moveInProgress) {
			   fromX = x;	   
			   fromY = y;
			   moveInProgress = true;
			   System.out.println("Changed moveInProgress to true");
		   // a click on a second square indicating move destination
		   } else if (moveInProgress) {
			   move(fromX, fromY, x, y);
			   moveInProgress = false;
	
		   }
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
	 
		@Override
		public void mouseExited(MouseEvent arg0) {}
	 
		@Override
    	public void mousePressed(MouseEvent arg0) {}
	 
		@Override
    	public void mouseReleased(MouseEvent arg0) {}
	        
    }

	
	
//	tile.addMouseListener(new MouseClickListener2() {
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//			tile.setBackground(new Color(1f, 0f, 0f, 0.5f));
//		tile.setOpaque(true);
//		}
//	@Override
//		public void mouseExited(MouseEvent arg0) {
//			tile.setOpaque(false);            			
//			tile.setBackground(new Color(0,0,0,0));
//		}
//});

}