package ch.makery.address.model;

public class User {
	private String username;
	private String password;
	
	public User() {
		this.username="default";
		this.password="default";
	}
	public User(String a, String b) {
		this.username=a;
		this.password=b;
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
