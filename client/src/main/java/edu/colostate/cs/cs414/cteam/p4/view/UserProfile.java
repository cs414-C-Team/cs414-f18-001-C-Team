package edu.colostate.cs.cs414.cteam.p4.view;

import javafx.util.Pair; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import javax.swing.UIManager;

public class UserProfile extends JFrame {

	private JPanel userPane;
	private GameWindow game_window;
	private ClientController controller;
	private int user;
	private JTextField userSearchField;
	private JList userList;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JList gameList;
	
	private JButton launchGameButton;
	private JLabel turnLabel;
	private JButton rejectInviteButton;
	private ArrayList<Pair<Integer, String>> currentGames;
	private String[] users;
	private boolean local = false;
	
	public UserProfile(GameWindow window, ClientController controller, int user) {
		this.game_window = window;
		this.controller = controller;
		this.user = user;
		System.out.println("User " + user + " loaded.");
		if(user == 1) {
			local = true;
			initialize(local);

		} else {
			initialize(local);
		}
	}
	
	public JPanel get() { return userPane; }
	
	public void initialize(boolean local) {
		userPane = new JPanel();
		userPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		userPane.setLayout(null);
		setContentPane(userPane);

		currentGames = new ArrayList<Pair<Integer, String>>();
		//currentGames.add(new Pair<Integer, String>(1, "Game with John")); //test
		//currentGames.add(new Pair<Integer, String>(2, "Game with Jane")); //test
		
		/* search user database for query 
		 * PLACEHOLDER
		 * */
		users = new String[1];
		if(user == 3) {
			users[0] = "admin-999";
		} else {
			users[0] = "test-3";
		}
		
		initializeTopPanel();
		if(local == false) {
			initializeSearchPanel();
			initializeGamePanel();
		}
	}
	
	
	private void initializeTopPanel() {
		JLabel lblNewLabel = new JLabel("Welcome to the Jungle");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setBounds(0, 0, 588, 39);
		userPane.add(lblNewLabel);
		
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.setBounds(10, 51, 576, 149);
		userPane.add(topPanel);
		topPanel.setLayout(null);
		
		JButton btnLocalGame = new JButton("Play Local Game");
		btnLocalGame.setBounds(101, 33, 380, 38);
		btnLocalGame.setFont(new Font("Calibri", Font.PLAIN, 13));
		topPanel.add(btnLocalGame);
		btnLocalGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					game_window.changeCard(1);  // starts a game
					game_window.newLocalGame();
			}
		});
		
		/*
		JButton btnNewButton = new JButton("View Invites");
		btnNewButton.setBounds(321, 108, 160, 27);
		topPanel.add(btnNewButton);
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("Match History");
		btnNewButton_1.setBounds(101, 108, 160, 27);
		topPanel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		*/
	}
	
	private void initializeSearchPanel() {
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setBounds(12, 356, 576, 287);
		userPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSearchUsers = new JLabel("Search Users");
		lblSearchUsers.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSearchUsers.setBounds(46, 41, 114, 19);
		panel_1.add(lblSearchUsers);
		
		userSearchField = new JTextField();
		userSearchField.setFont(new Font("Dialog", Font.PLAIN, 14));
		userSearchField.setBounds(34, 66, 316, 32);
		userSearchField.setColumns(10);
		panel_1.add(userSearchField);
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		searchButton.setBounds(362, 69, 81, 25);
		panel_1.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
	
		userList = new JList<String>();
		userList.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane1 = new JScrollPane(userList);
		scrollPane1.setBounds(34, 110, 508, 128);
		panel_1.add(scrollPane1);
		
		final JButton btnSendInvitation_1 = new JButton("Send Invitation");
		btnSendInvitation_1.setEnabled(false);
		btnSendInvitation_1.setBounds(34, 250, 139, 25);
		panel_1.add(btnSendInvitation_1);
		
		JLabel label = new JLabel("Start A New Game");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.PLAIN, 20));
		label.setBounds(180, 12, 215, 29);
		panel_1.add(label);
		btnSendInvitation_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSendInvitation_1.setEnabled(false);
				controller.sendInvitation(Integer.toString(user) + "-" + users[userList.getSelectedIndex()].split("-")[1]);

		    }
	     });
		
		userList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnSendInvitation_1.setEnabled(true);
			}
		});
	}
	
	private void initializeGamePanel() {
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setBackground(new Color(240, 240, 240));
		gamePanel.setBounds(10, 115, 576, 237);
		userPane.add(gamePanel);
		
		JLabel lblNewLabel_1 = new JLabel("Multiplayer Games");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(180, 6, 215, 29);
		gamePanel.add(lblNewLabel_1);
		JLabel label = new JLabel("Current Games");
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(46, 34, 114, 19);
		gamePanel.add(label);
		
		gameList = new JList<GameListObject>();
		gameList.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane2 = new JScrollPane(gameList);
		scrollPane2.setBounds(35, 60, 508, 128);
		gamePanel.add(scrollPane2);
		
		launchGameButton = new JButton("Launch Game");
		launchGameButton.setEnabled(false);
		launchGameButton.setBounds(35, 200, 154, 25);
		gameList.addListSelectionListener(new GameSelectionListener());
		gamePanel.add(launchGameButton);
		rejectInviteButton = new JButton("Deny Invitation");
		rejectInviteButton.setBounds(390, 200, 154, 25);
		rejectInviteButton.setVisible(false);
		gamePanel.add(rejectInviteButton);
		turnLabel = new JLabel("");
		turnLabel.setFont(new Font("Dialog", Font.PLAIN, 19));
		turnLabel.setBounds(218, 200, 154, 21);
		gamePanel.add(turnLabel);
				
		updateCurrentGames();
		
		launchGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameListObject selected = (GameListObject) gameList.getSelectedValue();
				if (selected.isInvite()) {
					// Accept invite, start new game 
					int matchID = controller.newMatch( users[Math.abs(userList.getSelectedIndex()) - 1].split("-")[1]  + "-" + Integer.toString(user));
					// remove invitation list item
					//String opponent = selected.getOpponent();
					//int index = gameList.getSelectedIndex();
					//DefaultListModel<GameListObject> model = (DefaultListModel<GameListObject>) gameList.getModel();
					//gameList.clearSelection();
					//model.remove(index);  
					
					// create new game list item and add it
					//GameListObject newGame = new GameListObject(matchID, opponent, false);
					//model.addElement(newGame);
					updateCurrentGames();
					rejectInviteButton.setVisible(false);
					
				} else {
					/* Load the game board for existing game */
//					window.loadGame("game representation", selected.getOpponent(), !selected.isTheirTurn());
					System.out.println(selected.getID());
					game_window.loadGame(user, selected.getID());
					game_window.changeCard(1);  
				}
			}
		});
		rejectInviteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = gameList.getSelectedIndex();
				
				/* Reject invite in database */
				
				gameList.clearSelection();
				((DefaultListModel<GameListObject>) gameList.getModel()).remove(index);  // removes list item
				rejectInviteButton.setVisible(false);
			}
		});
	}
	
	public void updateCurrentGames() {
		gameList.clearSelection();
		DefaultListModel<GameListObject> model = new DefaultListModel<GameListObject>();
		
		/* Query user's current games 
		String[] gameTitles = new String[currentGames.size()];
		for(int i = 0; i < currentGames.size(); i++) {
			gameTitles[i] = currentGames.get(i).getValue();
		}
		gameList = new JList(gameTitles);
		*/
		
		//Format: <invite>&<invite>...&<game>&<game>
		//Games are returned in this format: <matchID>-<user1ID>-<user2ID>-<player turn>-<date>-<board>
		//Invites are returned in this format: <player1ID>:<player2ID>
		String games_string = controller.queryGames(user);
		//Server returns a "null" instead of null object for some reason I can't figure out
		if(games_string != null && !games_string.equals("null") && games_string.length() > 0) {
			String[] games = games_string.split("&");
			int opponent;
			int current_user;
			boolean turn;
			String[] game;
			
			for(int i = 0; i < games.length; i++) {
				game = games[i].split("-");				
				if(game.length == 1) {
					System.out.println("UserProfile: Retrieved turn: " + game[0]);
					if(user == Integer.parseInt(game[0].split(":")[0])){
						opponent = Integer.parseInt(game[0].split(":")[1]);
					} else {
						opponent = Integer.parseInt(game[0].split(":")[0]);
					}
					
					model.addElement(new GameListObject(user, Integer.toString(opponent)));
				} else {
					System.out.println("UserProfile: Retrieved game:" + games[i]);

					turn = false;
					
					if(user == Integer.parseInt(game[1])){
						opponent = Integer.parseInt(game[2]);
					} else {
						opponent = Integer.parseInt(game[1]);
					}
					
					if(user == Integer.parseInt(game[5])) {
						turn = true;
					}
					model.addElement(new GameListObject(Integer.parseInt(game[0]), Integer.toString(opponent), turn));
					
				}
			}
		}
		gameList.setModel(model);
		scrollPane2.setViewportView(gameList);
	}

	// Represents current games OR current invites in the user profile game panel
	public class GameListObject {
		private int id;
		private String opponent;
		private boolean turn;
		private boolean invite;  // true if object is an invite, false if it's a game
		
		// invite constructor
		public GameListObject(int id, String opponent) {
			this.id = id;
			this.opponent = opponent;
			this.invite = true;
		}
		
		// game constructor
		public GameListObject(int id, String opponent, boolean turn) {
			this.id = id;
			this.opponent = opponent;
			this.turn = turn;
			this.invite = false;
		}

		public int getID() { return id;	}
		public String getOpponent() { return opponent; }
		public boolean isTheirTurn() { return turn; }
		public boolean isInvite() { return invite; }
		
		public String toString() {
			if (invite) {
				return "New game invite from user " + getOpponent();
			} else {
				return "Game with user " + getOpponent();
			}
		}
	}
	
	public class GameSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			GameListObject selected = (GameListObject) gameList.getSelectedValue();
			rejectInviteButton.setVisible(false);
			
			if (selected != null) {
				if (selected.isInvite()) {
					launchGameButton.setText("Accept Invitation");
					turnLabel.setText("");
					rejectInviteButton.setVisible(true);
				} else if (!selected.isTheirTurn()) {
					launchGameButton.setText("View Game Board");
					turnLabel.setText("It's " + selected.getOpponent() + "'s turn");
					turnLabel.setForeground(new Color(255, 62, 32));
	
				} else {
					launchGameButton.setText("Play Your Turn");
					turnLabel.setText("It's your turn!");
					turnLabel.setForeground(new Color(13, 198, 69));
				}
				launchGameButton.setEnabled(true);
			}
		}
	}
	
	public void search() {
		String query = userSearchField.getText();
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < users.length; i++) {
				model.addElement(users[i]);
		}
		userList.setModel(model);
		scrollPane1.setViewportView(userList);
	}
}

	

