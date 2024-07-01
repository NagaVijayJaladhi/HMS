package com.hms.entity;

import java.time.LocalDate;

public class Booking {

	private String guestName;
	private int roomNo;
	private LocalDate dateTime;

	public Booking(String guestName) {
		super();
		this.guestName = guestName;
	}
	
	public Booking(LocalDate dateTime) {
		super();
		this.dateTime = dateTime;
	}
	
	public Booking(String guestName, LocalDate dateTime) {
		super();
		this.guestName = guestName;
		this.dateTime = dateTime;
	}
	
	public Booking(String guestName, int roomNo, LocalDate dateTime) {
		super();
		this.guestName = guestName;
		this.roomNo = roomNo;
		this.dateTime = dateTime;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

}
