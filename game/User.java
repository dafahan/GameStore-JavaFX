package game;

public class User {
	private String UserId;
	private String Username;
	private String PhoneNumber;
	private String Password;
	private String Email;
	private String Role;
	public User(String userId, String username, String phoneNumber, String password, String email, String role) {
		super();
		UserId = userId;
		Username = username;
		PhoneNumber = phoneNumber;
		Password = password;
		Email = email;
		Role = role;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	
}
