package edu.colostate.cs.cs414.cteam.p3.model;

public class LoginRequest {

	private String username;
	//private String nickName;
	private char[] password;
	

	public LoginRequest(String username , char[] password) {
		this.setEmail(username);
		this.setPassword(password);
	}


	public String getUsername() {
		return username;
	}

	public void setEmail(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}


}
