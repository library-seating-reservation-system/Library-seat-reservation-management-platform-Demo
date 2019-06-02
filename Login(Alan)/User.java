package com.entity;

public class User {

	
	private Integer id;
	private String userName;
	private String passWord;
	private String level;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String userName, String passWord, String level) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.level = level;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
	
}
