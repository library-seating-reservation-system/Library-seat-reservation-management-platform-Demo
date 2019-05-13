package com.entity;

public class information {
private Integer id;
private String floor;
private String seatNumber;
private String time;
private String have;
private String whoId;
private String whoName;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
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
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getHave() {
	return have;
}
public void setHave(String have) {
	this.have = have;
}
public String getWhoId() {
	return whoId;
}
public void setWhoId(String whoId) {
	this.whoId = whoId;
}
public String getWhoName() {
	return whoName;
}
public void setWhoName(String whoName) {
	this.whoName = whoName;
}
public information(Integer id, String floor, String seatNumber, String time, String have, String whoId,
		String whoName) {
	super();
	this.id = id;
	this.floor = floor;
	this.seatNumber = seatNumber;
	this.time = time;
	this.have = have;
	this.whoId = whoId;
	this.whoName = whoName;
}
public information() {
	super();
	// TODO Auto-generated constructor stub
}

}
