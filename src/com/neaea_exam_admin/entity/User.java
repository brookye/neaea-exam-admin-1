package com.neaea_exam_admin.entity;

public class User {
	private Role role;
	private String password;
	private String telephone;
	private String email;
	private String firstName;
	private String lastName;
	private String userName;

	public User(Role role, String password, String telephone, String email,
			String firstName, String lastName, String userName) {
		super();
		this.role = role;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
