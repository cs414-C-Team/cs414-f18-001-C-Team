package edu.colostate.cs.cs414.cteam.p3.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.UIManager;

public class Register {

	private JFrame frame;
	private JTextField emailID;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Register Page");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 32, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 98, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 62, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -115, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email :");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 60, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 34, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -265, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 134, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username :");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 26, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel_1);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 20));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 23, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel_1);
		frame.getContentPane().add(lblNewLabel_3);
		
		emailID = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, emailID, 54, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, emailID, 26, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, emailID, 218, SpringLayout.EAST, lblNewLabel_1);
		frame.getContentPane().add(emailID);
		emailID.setColumns(10);
		
		usernameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, usernameField, 26, SpringLayout.SOUTH, emailID);
		springLayout.putConstraint(SpringLayout.WEST, usernameField, 0, SpringLayout.WEST, emailID);
		springLayout.putConstraint(SpringLayout.EAST, usernameField, 0, SpringLayout.EAST, emailID);
		usernameField.setColumns(10);
		frame.getContentPane().add(usernameField);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 2, SpringLayout.NORTH, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, emailID);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, emailID);
		frame.getContentPane().add(passwordField);
		
		Box horizontalBox = Box.createHorizontalBox();
		frame.getContentPane().add(horizontalBox);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		frame.getContentPane().add(horizontalBox_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		frame.getContentPane().add(horizontalBox_2);
		
		Box verticalBox = Box.createVerticalBox();
		frame.getContentPane().add(verticalBox);
		
		JButton register = new JButton("Register");
		springLayout.putConstraint(SpringLayout.NORTH, register, 62, SpringLayout.SOUTH, lblNewLabel_3);
		register.setForeground(UIManager.getColor("Button.focus"));
		register.setFont(new Font("Calibri", Font.BOLD, 13));
		frame.getContentPane().add(register);
		
		JButton btnNewButton = new JButton("Login Page");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -157, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, register, -111, SpringLayout.WEST, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, register, -22, SpringLayout.WEST, btnNewButton);
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 64, SpringLayout.SOUTH, passwordField);
		btnNewButton.setForeground(UIManager.getColor("Button.focus"));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 13));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 64, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 26, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 107, SpringLayout.EAST, btnNewButton);
		btnNewButton_1.setForeground(UIManager.getColor("Button.focus"));
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 13));
		frame.getContentPane().add(btnNewButton_1);
	}
}
