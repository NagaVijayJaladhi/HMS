package com.hms;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hms.entity.Booking;
import com.hms.service.BookingService;

@RestController("/booking")
public class BookingController {

	@Autowired
	BookingService service;
	
	@PostMapping("/storeBooking")
	public String storeBooking(@RequestBody Booking booking) throws Exception {
		return service.storeBooking(booking);
	}

	@GetMapping("/findAvailableRooms/{date}")
	public List<Integer> findAvailableRooms(@PathVariable LocalDate date) throws Exception {
		return service.findAvailableRooms(date);
	}

	@GetMapping("/findAvailableRooms/{guestName}")
	public List<Booking> findBookingsByGuest(@PathVariable String guestName) throws Exception {
		return service.findBookingsByGuest(guestName);
	}
}
