package com.entity;

import java.util.Date;


public class Message {
	
	private Integer id;
	private String name;
	private String department;
	private String grade;
	private String profession;
	private Date time;
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Message(Integer id, String name, String department, String grade, String profession, Date time) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.grade = grade;
		this.profession = profession;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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
