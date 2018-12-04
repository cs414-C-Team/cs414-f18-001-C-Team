package edu.colostate.cs.cs414.cteam.p3.view;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import javax.swing.UIManager;



public class UserProfile extends JFrame {

	private JPanel userPane;
	private GameWindow window;
	private JTextField userSearchField;
	private JList userList;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JList gameList;
	
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
				window.newGame();
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
		panel_1.setOpaque(false);
		panel_1.setBounds(12, 507, 576, 253);
		userPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSearchUsers = new JLabel("Search Users");
		lblSearchUsers.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSearchUsers.setBounds(34, 24, 114, 19);
		panel_1.add(lblSearchUsers);
		
		userSearchField = new JTextField();
		userSearchField.setFont(new Font("Dialog", Font.PLAIN, 14));
		userSearchField.setBounds(34, 45, 316, 32);
		userSearchField.setColumns(10);
		panel_1.add(userSearchField);
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		searchButton.setBounds(362, 48, 81, 25);
		panel_1.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		userList = new JList<String>();
		userList.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane1 = new JScrollPane(userList);
		scrollPane1.setBounds(34, 86, 508, 128);
		panel_1.add(scrollPane1);
		
		JButton btnSendInvitation_1 = new JButton("Send Invitation");
		btnSendInvitation_1.setEnabled(false);
		btnSendInvitation_1.setBounds(34, 216, 139, 25);
		panel_1.add(btnSendInvitation_1);
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
		gamePanel.setOpaque(false);
		gamePanel.setLayout(null);
		gamePanel.setBounds(12, 306, 576, 201);
		userPane.add(gamePanel);
		
		JLabel label = new JLabel("Current Games");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(35, 12, 114, 19);
		gamePanel.add(label);
		
		/* Query user's current games */
		
		String[] currentGames = {"Game with user JohnDoe", "Game with user FuckYou"};
		gameList = new JList(currentGames);
		gameList.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane2 = new JScrollPane(gameList);
		scrollPane2.setBounds(35, 36, 508, 128);
		gamePanel.add(scrollPane2);

		JButton button_1 = new JButton("Launch Game");
		button_1.setEnabled(false);
		button_1.setBounds(35, 166, 139, 25);
		gameList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				button_1.setEnabled(true);
			}
		});
		gamePanel.add(button_1);
	}
	
	
	
	public void search() {
		String query = userSearchField.getText();
		DefaultListModel<String> model = new DefaultListModel();

		/* search user database for query */
		String[] results = {"user1", "user2", "user3", "user4", "user5", "user6"};  // test
		
		for (int i = 0; i < results.length; i++) {
			model.addElement(results[i]);
		}
		userList.setModel(model);
		scrollPane1.setViewportView(userList);
	}
}

	

