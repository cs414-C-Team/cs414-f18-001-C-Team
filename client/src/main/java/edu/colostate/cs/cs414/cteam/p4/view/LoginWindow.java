package edu.colostate.cs.cs414.cteam.p4.view; 

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

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
	public JFrame frame;
	private ClientController controller;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel loginMsg;
	private JLabel loginMsg2;
	private JLabel regErrorMsg;
	private CardLayout cardLayout;
	private JTextField email;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField confirm;
	private JTextField regEmail;
	private JTextField regUsername;
	private JPasswordField  regPassword;
	private JPasswordField  regConfirm;


	 // matches valid email addresses
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    // Usernames must start with a letter, followed by any number of alpha-numeric characters
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Z]+[A-Z0-9]*$", Pattern.CASE_INSENSITIVE);
    private JTextField ipField;
    
	public LoginWindow(ClientController controller) {
		this.controller = controller;
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
		
	//Hands login info to controller to authenticate
	public int handleLogin() {
		loginMsg.setVisible(false);
		loginMsg2.setVisible(false);
		
		String ipaddress = ipField.getText();
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();
		
		if (username.toLowerCase().equals("local") || username.toLowerCase().equals("guest")) {
			return 1; //1 is local user
		} else if(ipaddress.isEmpty()) {
			System.out.println("No IP address");
			return -1;
		}else if(username.toLowerCase().equals("test")) {
			controller.connect(ipaddress);
			return 3; //3 is reserved for test user
		} else if(username.toLowerCase().equals("admin")) {
			controller.connect(ipaddress);
			return 999; //999 is reserved for admin
		} else if(username.isEmpty() || password.length == 0) {
			return -1;
		} else {
			controller.connect(ipaddress);
			int result = controller.login(username, new String(password));
			// Returns -2 for server error, -1 for failed authentication, or otherwise the ID of the logged in user
			return result; 
		}
	}
	
	private void changeCard(int card) {
		if (card == 0) {   // login screen
			this.cardLayout.show(frame.getContentPane(), "login screen"); 
		} else if (card == 1) {  // register screen
			this.cardLayout.show(frame.getContentPane(), "register screen");
		}
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
		regEmail = new JTextField();
		regEmail.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 0, 430, 50);
		panel_1.setLayout(null);
		emailLabel.setBounds(0, 20, 163, 30);
		emailLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		regEmail.setBounds(182, 22, 212, 30);
		panel_1.add(emailLabel);
		panel_1.add(regEmail);
		centerPanel.add(panel_1);
		
		// Email panel
		JPanel panel_2 = new JPanel();    
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		usernameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		regUsername = new JTextField();
		regUsername.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 49, 430, 50);
		panel_2.setLayout(null);
		usernameLabel.setBounds(0, 20, 163, 30);
		usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		regUsername.setBounds(182, 22, 212, 30);
		panel_2.add(usernameLabel);
		panel_2.add(regUsername);
		centerPanel.add(panel_2);
		
		// Password panel
		JPanel panel_3 = new JPanel();    
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		passwordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		regPassword = new JPasswordField();
		regPassword.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_3.setOpaque(false);
		panel_3.setBounds(0, 101, 430, 50);
		panel_3.setLayout(null);
		passwordLabel.setBounds(0, 20, 163, 30);
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		regPassword.setBounds(182, 22, 212, 30);
		panel_3.add(passwordLabel);
		panel_3.add(regPassword);
		centerPanel.add(panel_3);
		
		// Confirm password panel
		JPanel panel_4 = new JPanel();    
		JLabel confirmLabel = new JLabel("Confirm Password:");
		confirmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		confirmLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		confirmLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		regConfirm = new JPasswordField();
		regConfirm.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_4.setOpaque(false);
		panel_4.setBounds(0, 150, 430, 50);
		panel_4.setLayout(null);
		confirmLabel.setBounds(0, 20, 163, 30);
		confirmLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		regConfirm.setBounds(182, 22, 212, 30);
		panel_4.add(confirmLabel);
		panel_4.add(regConfirm);
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
		regErrorMsg.setBounds(12, 314, 430, 15);
		regErrorMsg.setVisible(false);
		registerPanel.add(regErrorMsg);
		
		regConfirm.addActionListener(new RegistrationListener());
		regButton.addActionListener(new RegistrationListener());
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCard(0);  // display login screen
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
		centerPanel.setBounds(10, 105, 430, 196);
		centerPanel.setOpaque(false);
		centerPanel.setLayout(null);
		loginPanel.add(centerPanel);
		
		// Username panel
		JPanel panel_1 = new JPanel();    
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.usernameField = new JTextField();
		usernameField.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 63, 430, 62);
		panel_1.setLayout(null);
		usernameLabel.setBounds(11, 16, 121, 30);
		usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		usernameField.setBounds(159, 15, 240, 36);
		panel_1.add(usernameLabel);
		panel_1.add(usernameField);
		centerPanel.add(panel_1);
		
		// Password panel
		JPanel panel_2 = new JPanel();    
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 122, 430, 62);
		panel_2.setLayout(null);
		passwordLabel.setBounds(11, 16, 121, 30);
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField.setBounds(157, 15, 241, 36);
		panel_2.add(passwordLabel);
		panel_2.add(passwordField);
		centerPanel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		JLabel ipLabel = new JLabel("IP Address:");
		ipLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.setBackground(new Color(220, 220, 220));
		this.ipField = new JTextField();
		ipField.setFont(new Font("Dialog", Font.PLAIN, 16));
		ipField.setBounds(159, 15, 240, 36);
		panel_3.setBounds(0, 0, 430, 62);
		panel_3.setLayout(null);
		ipLabel.setBounds(11, 16, 121, 30);
		ipLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		panel_3.add(ipLabel);
		panel_3.add(ipField);
		centerPanel.add(panel_3);
		
		
		// Panel for login/register buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 302, 430, 110);
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
		loginMsg.setBounds(10, 70, 430, 23);
		loginMsg.setVisible(false);
		loginMsg.setFont(new Font("Dialog", Font.PLAIN, 15));
		loginMsg.setForeground(Color.RED);
		loginMsg.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel.add(loginMsg);
		
		loginMsg2 = new JLabel("Login failed due to server connection issue");
		loginMsg2.setBounds(20, 70, 430, 23);
		loginMsg2.setVisible(false);
		loginMsg2.setFont(new Font("Dialog", Font.PLAIN, 15));
		loginMsg2.setForeground(Color.RED);
		loginMsg2.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel.add(loginMsg2);

		
		// Listeners
		button_1.addActionListener(new LoginListener());
		passwordField.addActionListener(new LoginListener());
		// Register button handler
		button_2.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		    	changeCard(1);  // switches to registration panel
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
	
	// Handler for pressing the "Login" button or an enter press in the password field
	public class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int result = handleLogin();
			
			System.out.println("Result " + result);
			
	    	if (result >= 0) {
	    		loginMsg.setVisible(false);
	    		controller.launchGame(result); //Result is the ID of the authenticated user
		        frame.dispose(); // closes login window
	    	} else if(result == -1){
	    		loginMsg.setVisible(true);
	    		usernameField.setText("");
	    		passwordField.setText("");
	    	} else {
	    		loginMsg2.setVisible(true);
	    		usernameField.setText("");
	    		passwordField.setText("");
	    	}
	    }
	}
	
	// Handler for pressing the "Register" button or an enter press
	public class RegistrationListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				regErrorMsg.setForeground(Color.RED);
				regErrorMsg.setVisible(true);
				String newEmail = regEmail.getText();
				String newUsername = regUsername.getText();
//				int newPassword = new String(regPassword.getPassword()).hashCode();   // password hashes
//				int newConfirm = new String(regConfirm.getPassword()).hashCode();
				String newPassword = new String(regPassword.getPassword());
				String newConfirm = new String(regConfirm.getPassword());
				String ipaddress = ipField.getText();
				
				if (!newPassword.equals(newConfirm)) {
					regPassword.setText("");
					regConfirm.setText("");
					System.out.println(newPassword);
					System.out.print(newConfirm);
					regErrorMsg.setText("Passwords do not match");
			
				} else if(!EMAIL_PATTERN.matcher(newEmail).matches()) {
					regEmail.setText("");
					regErrorMsg.setText("This is incorrect format for an email ID ");
			
				} else if (!USERNAME_PATTERN.matcher(newUsername).matches()) {
					regUsername.setText("");

			
				} else {
					// problem: you need to enter the IP address for registering as well
					controller.connect(ipaddress);
					int result = controller.register(newEmail, newUsername, newPassword);
					if (result < 0) {
						regErrorMsg.setText("Server error: request failed");
						
					} else {     // registration is successful
						regUsername.setText("");
						regEmail.setText("");
						regPassword.setText("");
						regConfirm.setText("");
						regErrorMsg.setText("Registration Successful!");
						regErrorMsg.setForeground(new Color(3, 196, 3));
						Timer timer = new Timer(1000, new ActionListener() {
						    @Override
						    public void actionPerformed( ActionEvent e ){
						    	changeCard(0);
								regErrorMsg.setVisible(false);
						    }
						});
						timer.setRepeats(false);
						timer.start();
					}
				}
			}
		}
}
