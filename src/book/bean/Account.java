package book.bean;

import java.sql.Date;

public class Account {
	private int id;
	private String username;
	private String password;
	private String gender;
	private String email;
	private Date created;
	
	public Account () {}
	
	public Account(String username, String password, String gender, String email) {
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
