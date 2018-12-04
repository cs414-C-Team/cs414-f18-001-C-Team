package edu.colostate.cs.cs414.cteam.p3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.SystemColor;


public class UserProfile extends JFrame {

	private JPanel userPane;
	private GameWindow window;
	private JTextField userSearchField;
	private JList userList;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JList<ActiveGame> gameList;
	private JButton launchGameButton;
	private JLabel turnLabel;
	
	public UserProfile(GameWindow window) {
		this.window = window;
		initialize();
	}
	
	public JPanel get() { return userPane; }
	
	public void initialize() {
		userPane = new JPanel();
		userPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		userPane.setLayout(null);
		setContentPane(userPane);

		initializeTopPanel();
		initializeSearchPanel();
		initializeGamePanel();
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
				window.changeCard(1);  // starts a game
				window.localGame();
			}
		});

		JButton btnNewButton = new JButton("View Invites");
		btnNewButton.setBounds(321, 108, 160, 27);
		topPanel.add(btnNewButton);
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("Match History");
		btnNewButton_1.setBounds(101, 108, 160, 27);
		topPanel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 13));
	}
	
	
	private void initializeSearchPanel() {
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setBounds(12, 473, 576, 287);
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
		
		JButton btnSendInvitation_1 = new JButton("Send Invitation");
		btnSendInvitation_1.setEnabled(false);
		btnSendInvitation_1.setBounds(34, 250, 139, 25);
		panel_1.add(btnSendInvitation_1);
		
		JLabel label = new JLabel("Start A New Game");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.PLAIN, 20));
		label.setBounds(180, 0, 215, 29);
		panel_1.add(label);
		btnSendInvitation_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSendInvitation_1.setEnabled(false);       
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

		gamePanel.setBounds(12, 224, 576, 237);
		userPane.add(gamePanel);
		
		JLabel label = new JLabel("Current Games");
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(46, 34, 114, 19);
		gamePanel.add(label);
		
		gameList = new JList<ActiveGame>();
		gameList.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane2 = new JScrollPane(gameList);
		scrollPane2.setBounds(35, 60, 508, 128);
		gamePanel.add(scrollPane2);
		launchGameButton = new JButton("Launch Game");
		launchGameButton.setEnabled(false);
		launchGameButton.setBounds(35, 200, 154, 25);
		gameList.addListSelectionListener(new GameSelectionListener());
		gamePanel.add(launchGameButton);
		
		turnLabel = new JLabel("");
		turnLabel.setFont(new Font("Dialog", Font.PLAIN, 19));
		turnLabel.setBounds(218, 200, 154, 21);
		gamePanel.add(turnLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Multiplayer Games");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(180, 0, 215, 29);
		gamePanel.add(lblNewLabel_1);
		
		updateCurrentGames();
		
		launchGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActiveGame selected = (ActiveGame) gameList.getSelectedValue();
				window.loadGame("game representation", selected.getOpponent(), !selected.isTheirTurn());
				window.changeCard(1);  
			}
		});
	}
	
	
	public void updateCurrentGames() {
		DefaultListModel<ActiveGame> model = new DefaultListModel<ActiveGame>();
		
		/* Query user's current games - TODO */
//		ResultSet[] results = 
//		for (int i = 0; i < results.length; i++) {
//			current = results[i];
//			int id =  
//			String name = 
//			ActiveGame newGame = new ActiveGame(id, name);
//			model.addElement(newGame);
//		}
		
		ActiveGame game1 = new ActiveGame(1, "jack", true);
		ActiveGame game2 = new ActiveGame(2, "jill", false);
		model.addElement(game1);
		model.addElement(game2);
		
		gameList.setModel(model);
		scrollPane2.setViewportView(gameList);
	}

	
	public class ActiveGame {
		private int id;
		private String opponent;
		private boolean turn;
		
		public ActiveGame(int id, String opponent, boolean turn) {
			this.id = id;
			this.opponent = opponent;
			this.turn = turn;
		}
		
		public String toString() {
			return "Game with user " + getOpponent();
		}
		
		public int getID() {
			return id;
		}
		
		public String getOpponent() {
			return opponent;
		}
		
		public boolean isTheirTurn() {
			return turn;
		}
	}
	
	public class GameSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			ActiveGame selected = (ActiveGame) gameList.getSelectedValue();

			if (selected.isTheirTurn()) {
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
	
	
	
	public void search() {
		String query = userSearchField.getText();
		DefaultListModel<String> model = new DefaultListModel<String>();

		/* search user database for query */
		String[] results = {"user1", "user2", "user3", "user4", "user5", "user6"};  // test
		
		for (int i = 0; i < results.length; i++) {
			model.addElement(results[i]);
		}
		userList.setModel(model);
		scrollPane1.setViewportView(userList);
	}
}

	

