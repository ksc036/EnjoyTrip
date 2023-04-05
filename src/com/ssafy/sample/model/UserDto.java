package com.ssafy.sample.model;

public class UserDto {
	private String username;
	private String nickname;
	private String email;
	private String password;
	private String address;
	private String address2;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	@Override
	public String toString() {
		return "UserDto [name=" + username + ", nickname=" + nickname + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", address2=" + address2 + "]";
	}
	
	
	
}
