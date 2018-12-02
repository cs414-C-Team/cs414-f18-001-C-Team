package edu.colostate.cs.cs414.cteam.p4.view;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;


// A special JPanel that represents a tile on a gameboard
public class TilePanel extends JPanel {

	JLabel icon;
	int x;
	int y;
	boolean hasPiece;
	
	public TilePanel(int y, int x) {
		super();
		this.x = x;
		this.y = y;
		this.hasPiece = false;
		icon = new JLabel();
		icon.setBounds(0, 0, 58, 58);
		this.setOpaque(false);
		this.add(icon);
	}
	
	public Icon getIcon() {
		return icon.getIcon();
	}
	
	public void setPiece(Icon animal) {
		icon.setIcon(animal);
		icon.revalidate();
		hasPiece = true;
	}
	
	public void clear() {
		icon.setIcon(null);
		icon.revalidate();
		hasPiece = false;
	}
	
	public boolean hasPiece() {
		return hasPiece;
	}
}
