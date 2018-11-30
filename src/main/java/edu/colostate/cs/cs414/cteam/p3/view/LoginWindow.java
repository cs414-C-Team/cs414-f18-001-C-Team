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

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Color;

// This is the main launcher for the program which contains to the login and
//  register screens. Upon login, the main game window is launched.

public class LoginWindow {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel loginMsg;
	
	public LoginWindow() {
		initialize();
		loadLoginMenu();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 450);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}	
	}
		
	
	public void loadLoginMenu() {
		JLabel titleLabel = new JLabel("Login");
		titleLabel.setBounds(10, 0, 430, 70);
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(titleLabel);
		
		// Center panel for username/password textboxes and labels
		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(10, 105, 430, 165);
		centerPanel.setOpaque(false);
		centerPanel.setLayout(null);
		frame.getContentPane().add(centerPanel);
		
		// Username panel
		JPanel panel_1 = new JPanel();    
		JLabel usernameLabel = new JLabel("Username :");
		this.usernameField = new JTextField();
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
		JLabel passwordLabel = new JLabel("Password :");
		this.passwordField = new JPasswordField();
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
		frame.getContentPane().add(buttonPanel);
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
		loginMsg.setForeground(Color.RED);
		loginMsg.setHorizontalAlignment(SwingConstants.CENTER);
		loginMsg.setHorizontalTextPosition(SwingConstants.CENTER);
		loginMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(loginMsg);
		
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
	}
	

	public boolean handleLogin() {
		loginMsg.setVisible(false);
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();
		LoginRequest req = new LoginRequest(username, password);
		// verify login info
			
		return false;
	}
	
	public static void launchGame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow launcher = new GameWindow();
                launcher.display();
            }
        });
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


