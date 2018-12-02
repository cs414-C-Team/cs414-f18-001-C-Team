package edu.colostate.cs.cs414.cteam.p3.model;

public class RegisterUser {

	private String email;
	private String nickname;
	private String password;
	private String firstName;
	private String lastName;
	
	public RegisterUser(String email, String nickname , String password , String firstName, String lastName) {
		this.setEmail(email);
		this.setPassword(password);
		this.setNickname(nickname);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
