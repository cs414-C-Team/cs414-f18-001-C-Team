package edu.colostate.cs.cs414.cteam.p3.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.regex.Pattern;

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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Register {

	private JFrame frame;
	private JTextField emailID;
	private JTextField usernameField;
	private JPasswordField passwordField;
    // matches valid email addresses
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    // Usernames must start with a letter, followed by any number of alpha-numeric characters
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Z]+[A-Z0-9]*$", Pattern.CASE_INSENSITIVE);
    private JPasswordField passwordField_1;
    // matches a string containing at least 6 alpha-numeric, or punctuation characters.
 
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
					UIManager.setLookAndFeel("Metal");
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
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register Page");
		lblNewLabel.setBounds(98, 32, 219, 30);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email :");
		lblNewLabel_1.setBounds(0, 116, 129, 22);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username :");
		lblNewLabel_2.setBounds(0, 164, 119, 26);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setBounds(0, 213, 123, 26);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel_3);
		
		emailID = new JTextField();
		emailID.setBounds(119, 116, 303, 22);
		frame.getContentPane().add(emailID);
		String valid = emailID.getText();
		
	    
		
		usernameField = new JTextField();
		usernameField.setBounds(119, 164, 303, 22);
		usernameField.setColumns(10);
		frame.getContentPane().add(usernameField);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(119, 215, 303, 22);
		frame.getContentPane().add(passwordField);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(horizontalBox);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(horizontalBox_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(horizontalBox_2);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(0, 0, 0, 0);
		frame.getContentPane().add(verticalBox);
		
		JButton register = new JButton("Register");
		register.setBounds(56, 333, 89, 25);
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("1");
				validRegistration();
				//System.out.println("2");
				
		
			}
		});
		register.setForeground(UIManager.getColor("Button.focus"));
		register.setFont(new Font("Calibri", Font.BOLD, 13));
		frame.getContentPane().add(register);
		
		JButton btnNewButton = new JButton("Login Page");
		btnNewButton.setBounds(182, 333, 95, 25);
		btnNewButton.setForeground(UIManager.getColor("Button.focus"));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 13));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(303, 333, 81, 25);
		btnNewButton_1.setForeground(UIManager.getColor("Button.focus"));
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 13));
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setBounds(10, 266, 150, 23);
		lblConfirmPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		frame.getContentPane().add(lblConfirmPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(160, 267, 262, 22);
		frame.getContentPane().add(passwordField_1);
	}
	
	 boolean validateEmailAddress() {
		 //System.out.println("va;id email");
		
	        String email = emailID.getText();
	        boolean isEmailValid = EMAIL_PATTERN.matcher(email).matches();
	        if (isEmailValid) {
	            emailID.setBackground(Color.white);
	        } else {
	            emailID.setBackground(Color.RED);
	        }
	        return isEmailValid;
	    }
	 
	 boolean validateUsername() {
		 System.out.println("valid username");
	        String nickName = usernameField.getText();
	        boolean isNickNameValid = USERNAME_PATTERN.matcher(nickName).matches();
	        if (isNickNameValid) {
	            usernameField.setBackground(Color.white);
	        } else {
	            usernameField.setBackground(Color.RED);
	        }
	        return isNickNameValid;
	    }
	 
	 
	 boolean validatePasswordConfirmation() {
		 System.out.println("password");
	        char[] password = passwordField.getPassword();
	        char[] passwordConfirmed = passwordField_1.getPassword();
	        boolean isPasswordConfirmed = !passwordConfirmed.equals("") && password.equals(passwordConfirmed);
	        if (isPasswordConfirmed) {
	            passwordField_1.setBackground(Color.white);
	        } else {
	            passwordField_1.setBackground(Color.RED);
	        }
	        return isPasswordConfirmed;
	    }
	 
	 boolean validRegistration() {
		 //System.out.println("hey");
		 return(validateEmailAddress()&& validateUsername() && validatePasswordConfirmation());
	 }
	 
	 
	 
	 


}
