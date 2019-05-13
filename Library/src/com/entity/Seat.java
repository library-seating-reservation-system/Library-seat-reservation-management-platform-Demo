package com.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Seat {
	@JSONField(name = "ProductName")
	private String floor;
	@JSONField(name = "SeatNumber")
	private String SeatNumber;
	@JSONField(name = "Date")
	private Date date;
	@JSONField(name = "Reporter")
	private String whoName;
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getSeatNumber() {
		return SeatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		SeatNumber = seatNumber;
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
	public Seat(String floor, String seatNumber, Date date, String whoName) {
		super();
		this.floor = floor;
		SeatNumber = seatNumber;
		this.date = date;
		this.whoName = whoName;
	}
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}


}
