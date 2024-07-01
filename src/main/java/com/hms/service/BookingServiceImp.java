package com.hms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hms.dao.BookingDao;
import com.hms.entity.Booking;

public class BookingServiceImp implements BookingService {
	
	@Autowired
	BookingDao bookingDao;
	
	
	

	@Override
	public String storeBooking(Booking booking) throws Exception {
		return bookingDao.storeBooking(booking);
	}

	@Override
	public List<Integer> findAvailableRooms(LocalDate date) throws Exception {
		return bookingDao.findAvailableRooms(date);
	}

	@Override
	public List<Booking> findBookingsByGuest(String guestName) throws Exception {
		return bookingDao.findBookingsByGuest(guestName);
	}

}
