package com.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class MSeat {
	@JSONField(name = "Floor")
	private String floor;
	@JSONField(name = "Seat")
	private String seatNumber;
	@JSONField(name = "data")
	private Date date;
	@JSONField(name = "Operator")
	private String whoName;
	public MSeat(String floor, String seatNumber, Date date, String whoName) {
		super();
		this.floor = floor;
		this.seatNumber = seatNumber;
		this.date = date;
		this.whoName = whoName;
	}
	public MSeat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getWhoName() {
		return whoName;
	}
	public void setWhoName(String whoName) {
		this.whoName = whoName;
	}
	

}
