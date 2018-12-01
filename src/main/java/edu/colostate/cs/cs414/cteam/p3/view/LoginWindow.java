package edu.colostate.cs.cs414.cteam.p3.view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import edu.colostate.cs.cs414.cteam.p3.model.LoginRequest;
import edu.colostate.cs.cs414.cteam.p3.model.RegisterUser;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.SystemColor;

// This is the main launcher for the program which contains to the login and
//  register screens. Upon login, the main game window is launched.

public class LoginWindow {
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel loginMsg;
	private JLabel regErrorMsg;
	private CardLayout cardLayout;
	
	
	public LoginWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 450);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel card1;   // login panel
		JPanel card2;   // registration panel
		card1 = loginFrame();
		card2 = registerFrame();
		frame.getContentPane().add(card1, "login screen");
		frame.getContentPane().add(card2, "register screen");
		this.cardLayout = (CardLayout) frame.getContentPane().getLayout();
		this.cardLayout.show(frame.getContentPane(), "login screen");   // displays the login screen on startup
        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}	
	}
		
	
	public boolean handleLogin() {
		loginMsg.setVisible(false);
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();
		LoginRequest req = new LoginRequest(username, password);
		// verify login info

		// for testing
		if (username.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	// Closes the login/register window and opens the main game
	public static void launchGame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow launcher = new GameWindow();
                launcher.display();
            }
        });
    }
	
	public JPanel registerFrame() {
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(null);
		
		// Center panel for username/password textboxes and labels
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(SystemColor.window);
		centerPanel.setBounds(12, 87, 430, 215);
		centerPanel.setPreferredSize(new Dimension(430, 165));
		centerPanel.setLayout(null);
		registerPanel.add(centerPanel);
		
		// Username panel
		JPanel panel_1 = new JPanel();    
		JLabel emailLabel = new JLabel("Email Address:");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		emailLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JTextField email = new JTextField();
		email.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 0, 430, 50);
		panel_1.setLayout(null);
		emailLabel.setBounds(0, 20, 163, 30);
		emailLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		email.setBounds(182, 22, 212, 30);
		panel_1.add(emailLabel);
		panel_1.add(email);
		centerPanel.add(panel_1);
		
		// Email panel
		JPanel panel_2 = new JPanel();    
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		usernameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JTextField username = new JTextField();
		username.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 49, 430, 50);
		panel_2.setLayout(null);
		usernameLabel.setBounds(0, 20, 163, 30);
		usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		username.setBounds(182, 22, 212, 30);
		panel_2.add(usernameLabel);
		panel_2.add(username);
		centerPanel.add(panel_2);
		
		// Password panel
		JPanel panel_3 = new JPanel();    
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		passwordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JPasswordField password = new JPasswordField();
		password.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_3.setOpaque(false);
		panel_3.setBounds(0, 101, 430, 50);
		panel_3.setLayout(null);
		passwordLabel.setBounds(0, 20, 163, 30);
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		password.setBounds(182, 22, 212, 30);
		panel_3.add(passwordLabel);
		panel_3.add(password);
		centerPanel.add(panel_3);
		
		// Confirm password panel
		JPanel panel_4 = new JPanel();    
		JLabel confirmLabel = new JLabel("Confirm Password:");
		confirmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		confirmLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		confirmLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JPasswordField confirm = new JPasswordField();
		confirm.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_4.setOpaque(false);
		panel_4.setBounds(0, 150, 430, 50);
		panel_4.setLayout(null);
		confirmLabel.setBounds(0, 20, 163, 30);
		confirmLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		confirm.setBounds(182, 22, 212, 30);
		panel_4.add(confirmLabel);
		panel_4.add(confirm);
		centerPanel.add(panel_4);

		// Panel for login/register buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(12, 337, 430, 73);
		buttonPanel.setPreferredSize(new Dimension(430, 165));
		buttonPanel.setOpaque(false);
		registerPanel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton regButton = new JButton("Register");
		regButton.setBounds(45, 12, 140, 49);
		buttonPanel.add(regButton);
		
		JButton backButton = new JButton("Cancel");
		backButton.setBounds(245, 12, 140, 49);
		buttonPanel.add(backButton);		
		
		JLabel titleLabel = new JLabel("Register An Account");
		titleLabel.setBounds(12, 1, 426, 52);
		registerPanel.add(titleLabel);
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel descLabel = new JLabel("Enter the following information to create an account");
		descLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		descLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descLabel.setBounds(0, 49, 450, 41);
		registerPanel.add(descLabel);
		
		regErrorMsg = new JLabel("Error label");
		regErrorMsg.setFont(new Font("Dialog", Font.PLAIN, 15));
		regErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		regErrorMsg.setForeground(Color.RED);
		regErrorMsg.setBounds(12, 314, 430, 15);
		regErrorMsg.setVisible(false);
		registerPanel.add(regErrorMsg);
		
		
		regButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clear all fields
				String newEmail = email.getText();
				String newUsername = username.getText();
				String newPassword = password.getPassword().toString();
				String newConfirm = confirm.getPassword().toString();
				email.setText("");
				username.setText("");
				password.setText("");
				confirm.setText("");
				
				if (newPassword != newConfirm) {
					regErrorMsg.setText("Passwords do not match");
					regErrorMsg.setVisible(true);
					return;
				}
				RegisterUser req = new RegisterUser(newEmail, newUsername, newPassword);

				// attempt registration
				regErrorMsg.setVisible(false);
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clear all fields
				
				
				// display login screen
				cardLayout.show(frame.getContentPane(), "login screen");
			}
		});
		
		
		
		return registerPanel;
	}
	
	
	public JPanel loginFrame() {
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		JLabel titleLabel = new JLabel("Login");
		titleLabel.setBounds(10, 0, 430, 70);
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel.add(titleLabel);
		
		// Center panel for username/password textboxes and labels
		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(10, 105, 430, 165);
		centerPanel.setOpaque(false);
		centerPanel.setLayout(null);
		loginPanel.add(centerPanel);
		
		// Username panel
		JPanel panel_1 = new JPanel();    
		JLabel usernameLabel = new JLabel("Username:");
		this.usernameField = new JTextField();
		usernameField.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 5, 430, 80);
		panel_1.setLayout(null);
		usernameLabel.setBounds(12, 25, 130, 30);
		usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		usernameField.setBounds(160, 25, 240, 36);
		panel_1.add(usernameLabel);
		panel_1.add(usernameField);
		centerPanel.add(panel_1);
		
		// Password panel
		JPanel panel_2 = new JPanel();    
		JLabel passwordLabel = new JLabel("Password:");
		this.passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 85, 430, 80);
		panel_2.setLayout(null);
		passwordLabel.setBounds(12, 25, 130, 30);
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField.setBounds(160, 25, 241, 36);
		panel_2.add(passwordLabel);
		panel_2.add(passwordField);
		centerPanel.add(panel_2);
		
		// Panel for login/register buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 276, 430, 136);
		buttonPanel.setOpaque(false);
		loginPanel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton button_1 = new JButton("Log in");
		button_1.setBounds(30, 36, 100, 49);
		buttonPanel.add(button_1);
		
		JButton button_2 = new JButton("Register");
		button_2.setBounds(162, 36, 100, 49);
		buttonPanel.add(button_2);
		
		JButton button_3 = new JButton("Cancel");
		button_3.setBounds(289, 36, 100, 49);
		buttonPanel.add(button_3);
				
		loginMsg = new JLabel("Username or password is incorrect");
		loginMsg.setBounds(10, 82, 430, 23);
		loginMsg.setVisible(false);
		loginMsg.setFont(new Font("Dialog", Font.PLAIN, 15));
		loginMsg.setForeground(Color.RED);
		loginMsg.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel.add(loginMsg);
		
		
		// Login event handler
		button_1.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if (handleLogin()) {
			    	launchGame();    // launches new window
			        frame.dispose(); // closes login window
		    	} else {
		    		loginMsg.setVisible(true);
		    		usernameField.setText("");
		    		passwordField.setText("");
		    	}
		    }
		});
		
		// Register button handler
		button_2.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		    	// switches to registration panel
		    	cardLayout.show(frame.getContentPane(), "register screen");
		    }
		});
		
		// Cancel button - exits program
		button_3.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		    	System.exit(0);
		    }
		});

		return loginPanel;
	}	


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}


/* TODO

- break into 3 classes- 

*/