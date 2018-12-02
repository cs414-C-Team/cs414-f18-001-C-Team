package edu.colostate.cs.cs414.cteam.p3.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserProfile extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfile frame = new UserProfile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Launches the new game 
	public static void launchGame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameLauncher launcher = new GameLauncher();
                launcher.display();
            }
        });
    }

	/**
	 * Create the frame.
	 */
	public UserProfile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 432, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnCreateNewMatch = new JButton("Create New Match");
		btnCreateNewMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				launchGame();		
			}
		});
		btnCreateNewMatch.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnCreateNewMatch.setBounds(143, 66, 149, 25);
		contentPane.add(btnCreateNewMatch);
		
		JButton btnSendInvitation = new JButton("Send Invitation");
		btnSendInvitation.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnSendInvitation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSendInvitation.setBounds(143, 104, 149, 25);
		contentPane.add(btnSendInvitation);
		
		JButton btnNewButton = new JButton("View Invites");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(143, 145, 149, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Match History");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnNewButton_1.setBounds(143, 183, 149, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Present Matches");
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(143, 221, 149, 25);
		contentPane.add(btnNewButton_2);
	}
}
