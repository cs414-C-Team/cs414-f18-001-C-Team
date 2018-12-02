package edu.colostate.cs.cs414.cteam.p3.model;

public class RegisterUser {

	private String email;
	private String username;
	private String password;
	
	
	public RegisterUser(String newEmail, String newUsername, String newPassword) {
		this.setEmail(newEmail);
		this.setUsername(newUsername);
		this.setPassword(newPassword);
		
	}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}


	
	//private String firstName;
	//private String lastName;
	
	
	
	