package com.hms.service;

import java.time.LocalDate;
import java.util.List;

import com.hms.entity.Booking;

public interface BookingService {
	
	public String storeBooking(Booking booking) throws Exception;

	public List<Integer> findAvailableRooms(LocalDate date) throws Exception;

	public List<Booking> findBookingsByGuest(String guestName) throws Exception;

}
