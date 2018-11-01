package edu.colostate.cs.cs414.cteam.p3.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.colostate.cs.cs414.cteam.p3.model.*;
public class SwingTest {

    
	JFrame frame;
	JLabel boardImage;
	JLayeredPane gamePanel;
	JPanel tileContainer;
	JPanel[][] tiles;
	JLabel[][] icons;
	Match game;
	int currentPlayer;
	boolean moveInProgress;
	int fromX;
	int fromY;
	Toolkit tk;
	JPanel panel1;
	JTextField textfield1;
	
	public SwingTest() {
        frame = new JFrame("Jungle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 800));
        tk = Toolkit.getDefaultToolkit();
        Dimension screen = tk.getScreenSize();
        int xPos = (screen.width / 2) - frame.getWidth();
        int yPos = (screen.height / 2) - frame.getHeight();
        frame.setLocation(xPos,  yPos);
        frame.setLayout(null);
        moveInProgress = false;
	}
	
	public void createBoard() {
        gamePanel = new JLayeredPane();  // layered panel for putting pieces over the game board
        gamePanel.setPreferredSize(new Dimension(500, 600));
        gamePanel.setLayout(null);
        gamePanel.setBounds(50, 0, 500, 600);
        ImageIcon icon = createImageIcon("boardtransparent.png", "gameboard"); // game board image
        boardImage = new JLabel(icon);
        boardImage.setBounds(0, 0, 500, 600);

        tileContainer = new JPanel();		 // panel holding all 63 tiles
        tileContainer.setLayout(new GridLayout(9, 7));
        tileContainer.setBounds(16, 8, 465, 585);
        tileContainer.setOpaque(false);
        gamePanel.add(this.boardImage, 1, 0);
        gamePanel.add(tileContainer, 2, 0);    // tileContainer (2) is displayed on top of gamePanel (1)
        
        tiles = new JPanel[9][7];
        icons = new JLabel[9][7];
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 7; j++) {
        		ci = i;
        		cj = j;
        		tiles[i][j] = new JPanel();
        		icons[i][j] = new JLabel();
        		JPanel tile  = tiles[i][j];
        		tile.setOpaque(false);
        		tile.add(icons[i][j]);
            	tileContainer.add(tile);
        	}
        }
        frame.add(gamePanel);
        gamePanel.addMouseListener(new MouseClickListener() {
        	public void mouseClicked(MouseEvent e) {
  			   int x = (e.getX() - 19) / 70;
  			   int y = (e.getY() - 8) / 65;
  			   Tile[][] board = game.getBoard();
  			   if (board[y][x].hasCharacter() && !moveInProgress) {
  				   fromX = x;	   
  				   fromY = y;
  				   moveInProgress = true;
  				   // a click on a second square indicating move destination
  			   } else if (moveInProgress) {
  				   move(fromY, fromX, y, x);
  				   moveInProgress = false;

  			   }
 		   }
        });
	}

	private int ci;
	private int cj;
	
	public void newGame() {
		game = new Match();
		ImageIcon lion1 = createImageIcon("lion1.png", "gameboard");
		ImageIcon lion2 = createImageIcon("lion2.png", "gameboard");
		ImageIcon tiger1 = createImageIcon("tiger1.png", "gameboard");
		ImageIcon tiger2 = createImageIcon("tiger2.png", "gameboard");
		ImageIcon dog1 = createImageIcon("dog1.png", "gameboard");
		ImageIcon dog2 = createImageIcon("dog2.png", "gameboard");
		ImageIcon cat1 = createImageIcon("cat1.png", "gameboard");
		ImageIcon cat2 = createImageIcon("cat2.png", "gameboard");
		ImageIcon rat1 = createImageIcon("rat1.png", "gameboard");
		ImageIcon rat2 = createImageIcon("rat2.png", "gameboard");
		ImageIcon cheetah1 = createImageIcon("cheetah1.png", "gameboard");
		ImageIcon cheetah2 = createImageIcon("cheetah2.png", "gameboard");
		ImageIcon wolf1 = createImageIcon("wolf1.png", "gameboard");
		ImageIcon wolf2 = createImageIcon("wolf2.png", "gameboard");
		ImageIcon elephant1 = createImageIcon("elephant1.png", "gameboard");
		ImageIcon elephant2 = createImageIcon("elephant2.png", "gameboard");//		lion1.setBounds(0, 0, 60, 60);
		setPiece(lion1, 0, 0);
		setPiece(lion2, 8, 6);
		setPiece(tiger1, 0, 6);
		setPiece(tiger2, 8, 0);
		setPiece(dog1, 1, 1);
		setPiece(dog2, 7, 5);
		setPiece(cat1, 1, 5);
		setPiece(cat2, 7, 1);
		setPiece(rat1, 2, 0);
		setPiece(rat2, 6, 6);
		setPiece(cheetah1, 2, 2);
		setPiece(cheetah2, 6, 4);
		setPiece(wolf1, 2, 4);
		setPiece(wolf2, 6, 2);
		setPiece(elephant1, 2, 6);
		setPiece(elephant2, 6, 0);
	}
	
	public void setPiece(Icon animal, int y, int x) {
		JLabel current = icons[y][x];
		current.setBounds(0, 0, 58, 58);
		current.setIcon(animal);
		current.revalidate();
	}
	
	public void move(int startY, int startX, int toY, int toX) {
		System.out.println("Move recorded: " + startY + "," + startX + " to " + toY + "," + toX);
		
		
		boolean success = game.makeMove(currentPlayer, startX, startY, toX, toY);
		if (success) {
			System.out.println("success");
			Icon animal = icons[startY][startX].getIcon();
			icons[startY][startX].setIcon(null);
			icons[toY][toX].setIcon(animal);
			icons[startY][startX].revalidate();
			icons[toY][toX].revalidate();

		} else {
			System.out.println("move failed");
		}
		
	}
	

	
	public void display() {       
        frame.pack();
        frame.setVisible(true);
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } 
        return null;
	}
    

	
	private class MouseClickListener implements MouseListener {
		   
	       // Called when a mouse button is clicked
	        
		public void mouseClicked(MouseEvent e) {
	           System.out.println(e.paramString());
	            
		}
	        
		public void mouseEntered(MouseEvent arg0) {
	           // TODO Auto-generated method stub
		}
	 
		public void mouseExited(MouseEvent arg0) {
	           // TODO Auto-generated method stub
		}
	 
     	public void mousePressed(MouseEvent arg0) {
	         // TODO Auto-generated method stub
     	}
	 
	       public void mouseReleased(MouseEvent arg0) {
	           // TODO Auto-generated method stub
	            
	       }
	        
	    }

	
	public static void main(String[] args) {

	
		SwingTest a = new SwingTest();
		a.createBoard();
		a.newGame();
		a.display();
        
        
       
        

		
	}

//	private class ButtonListener implements ActionListener {
//		
//		
//	}
	
}