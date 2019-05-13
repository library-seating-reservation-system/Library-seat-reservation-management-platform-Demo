package com.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class UserAll {
	@JSONField(name = "name")
private String name;
	@JSONField(name = "eduNumber")
private String userName;
	@JSONField(name = "Department")
private String department;
	@JSONField(name = "password")
private String passWord;
	@JSONField(name = "grade")
private String grade;
	@JSONField(name = "profession")
private String profession;
	@JSONField(name = "RegistrationTime")
private Date time;
	public UserAll(String name, String userName, String department, String passWord, String grade, String profession,
			Date time) {
		super();
		this.name = name;
		this.userName = userName;
		this.department = department;
		this.passWord = passWord;
		this.grade = grade;
		this.profession = profession;
		this.time = time;
	}
	public UserAll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
