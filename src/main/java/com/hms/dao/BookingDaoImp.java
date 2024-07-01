package com.hms.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hms.entity.Booking;

public class BookingDaoImp implements BookingDao {
	
	private int numberOfRooms;
    private Map<Integer, List<Booking>> bookingsByRoom;
    private Map<String, List<Booking>> bookingsByGuest;
	
	public BookingDaoImp(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        this.bookingsByRoom = new ConcurrentHashMap<>();
        this.bookingsByGuest = new ConcurrentHashMap<>();
        for (int i = 1; i <= numberOfRooms; i++) {
            bookingsByRoom.put(i, new ArrayList<>());
        }
    }
	
	@Override
    public String storeBooking(Booking booking) throws IllegalArgumentException {
		String result = "Not Booked";
        try {
            for (Booking checkingBooking : bookingsByRoom.get(booking.getRoomNo())) {
                if (checkingBooking.getDateTime().equals(booking.getDateTime())) {
                    throw new IllegalArgumentException("Booking Already Exists for Room " + booking.getRoomNo() + " on Date " + booking.getDateTime());
                }
            }

            Booking saveBooking = new Booking(booking.getGuestName(), booking.getRoomNo(), booking.getDateTime());
            bookingsByRoom.get(booking.getRoomNo()).add(saveBooking);
            bookingsByGuest.computeIfAbsent(booking.getGuestName(), k -> new ArrayList<>()).add(saveBooking);
            result = "Your Room Booked Successfully";
        } catch (Exception e) {
        	result = "Your Room Not Booked";
            throw new IllegalArgumentException("Failed to Store Booking: " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Integer> findAvailableRooms(LocalDate date) throws IllegalArgumentException{
        List<Integer> availableRooms = new ArrayList<>();
        try {
            for (int i = 1; i <= numberOfRooms; i++) {
                boolean isBooked = false;
                for (Booking booking : bookingsByRoom.get(i)) {
                    if (booking.getDateTime().equals(date)) {
                        isBooked = true;
                        break;
                    }
                }
                if (!isBooked) {
                    availableRooms.add(i);
                }
            }
            return availableRooms;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error Finding Available Rooms : " + e.getMessage());
        }
    }

    @Override
    public List<Booking> findBookingsByGuest(String guestName) throws IllegalArgumentException{
        try {
            return bookingsByGuest.getOrDefault(guestName, new ArrayList<>());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error Finding Bookings for Guest :  " + e.getMessage());
        }
    }

}
