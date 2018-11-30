package edu.colostate.cs.cs414.cteam.p3.view;

import edu.colostate.cs.cs414.cteam.p3.model.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import edu.colostate.cs.cs414.cteam.p3.model.LoginRequest;

import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
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
	public LoginPage() {
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
		
		JLabel Textfield = new JLabel("Username :");
		Textfield.setVerticalAlignment(SwingConstants.TOP);
		Textfield.setAlignmentX(Component.RIGHT_ALIGNMENT);
		Textfield.setAlignmentY(Component.TOP_ALIGNMENT);
		Textfield.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		Textfield.setMinimumSize(new Dimension(25, 16));
		Textfield.setMaximumSize(new Dimension(25, 16));
		Textfield.setHorizontalAlignment(SwingConstants.LEFT);
		Textfield.setFont(new Font("Calibri", Font.PLAIN, 20));
		frame.getContentPane().add(Textfield);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel_1);
		
		usernameField = new JTextField();
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(25);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();
		frame.getContentPane().add(passwordField);
		
		Button button = new Button("Log in ");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			LoginRequest details	= new LoginRequest(usernameField.getText(), passwordField.getPassword());	
			}
		});
		button.setForeground(UIManager.getColor("Button.focus"));
		button.setFont(new Font("Calibri", Font.PLAIN, 13));
		button.setBackground(UIManager.getColor("Button.highlight"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("Register");
		button_1.setBackground(UIManager.getColor("Button.highlight"));
		button_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		button_1.setForeground(UIManager.getColor("Button.focus"));
		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("Cancel");
		button_2.setForeground(UIManager.getColor("Button.focus"));
		button_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		button_2.setBackground(UIManager.getColor("Button.highlight"));
		frame.getContentPane().add(button_2);
		
		springLayout.putConstraint(SpringLayout.NORTH, Textfield, 100, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, Textfield, 41, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, Textfield, -277, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, Textfield, -6, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 41, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 202, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, usernameField, 154, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, usernameField, -273, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, usernameField, -32, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, usernameField, 46, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 25, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 90, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 286, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 154, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -6, SpringLayout.WEST, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 69, SpringLayout.SOUTH, usernameField);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -32, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, button, 61, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, button, 55, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, button, -88, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, -57, SpringLayout.NORTH, button_1);
		springLayout.putConstraint(SpringLayout.WEST, button_1, 193, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button, -45, SpringLayout.WEST, button_1);
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.SOUTH, button_1, -90, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button_1, 0, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, button_2, 0, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.WEST, button_2, -109, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button_2, -23, SpringLayout.EAST, frame.getContentPane());

		
	}
}
