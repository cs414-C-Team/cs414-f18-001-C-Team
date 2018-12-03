package edu.colostate.cs.cs414.cteam.p3.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** A special JPanel that represents a tile on a gameboard. */
public class TilePanel extends JPanel {
	JLabel icon;
	int x;
	int y;
	boolean hasPiece;
	GameWindow game;
		public TilePanel(int y, int x, GameWindow game) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.hasPiece = false;
		icon = new JLabel();
		setOpaque(false);
		icon.setBounds(0, 0, 58, 58);  
		add(icon);
		addMouseListener(new MouseClickListener());
	}
			public int getXPos() {
		return x;
	}
		public int getYPos() {
		return y;
	}
		public Icon getIcon() {
		return icon.getIcon();
	}
		public void highlight() {
		setOpaque(true);
		setBackground(new Color(255, 135, 135, 125));
	}
		public void unHighlight() {
		setOpaque(false);
		setBackground(null);
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
	    		/** Mouse click on board handler. */
	private class MouseClickListener implements MouseListener {
	   /** Called when a mouse button is clicked. */
		@Override
		public void mouseClicked(MouseEvent e) {
		   game.clickHandler(getYPos(), getXPos());
		}
				@Override
		public void mouseEntered(MouseEvent arg0) {
			highlight();
		}
	 		@Override 
		public void mouseExited(MouseEvent arg0) {
			unHighlight();
		}
	 		@Override
    	public void mousePressed(MouseEvent arg0) {}
	 		@Override
    	public void mouseReleased(MouseEvent arg0) {}
	            }
		}
