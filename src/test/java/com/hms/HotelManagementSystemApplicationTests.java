package com.hms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.hms.entity.Booking;
import com.hms.service.BookingService;

@SpringBootTest
class HotelManagementSystemApplicationTests {

	@Autowired
	private BookingService bookingService;
	
	@Test
    public void testStoreBooking() throws Exception {
		Booking booking = new Booking("Ravi", 1, LocalDate.now());
		bookingService.storeBooking(booking);
        assertEquals(1, bookingService.findBookingsByGuest("Ravi").size());
    }

    @Test
    public void testFindAvailableRooms() throws Exception {
    	Booking booking = new Booking("Ravi", 1, LocalDate.now());
    	bookingService.storeBooking(booking);
        List<Integer> availableRooms = bookingService.findAvailableRooms(LocalDate.now().plusDays(1));
        assertEquals(3, availableRooms.size());
        assertTrue(availableRooms.contains(1));
        assertTrue(availableRooms.contains(2));
        assertTrue(availableRooms.contains(3));

        Booking booking2 = new Booking("Priya", 1, LocalDate.now().plusDays(1));
        bookingService.storeBooking(booking2);
       
        Booking booking3 = new Booking("Jhone", 2, LocalDate.now().plusDays(1));
        bookingService.storeBooking(booking3);

        availableRooms = bookingService.findAvailableRooms(LocalDate.now().plusDays(1));
        assertEquals(1, availableRooms.size());
        assertTrue(availableRooms.contains(3));
    }

    @Test
    public void testFindBookingsByGuest() throws Exception {
    	
    	Booking booking = new Booking("Ravi", 1, LocalDate.now());
    	Booking booking2 = new Booking("Priya", 2, LocalDate.now());
    	Booking booking3 = new Booking("Jhone", 3, LocalDate.now());
        bookingService.storeBooking(booking);
        bookingService.storeBooking(booking2);
        bookingService.storeBooking(booking3);

        List<Booking> guest_1sBookings = bookingService.findBookingsByGuest("Ravi");
        assertEquals(2, guest_1sBookings.size());
        assertEquals("Ravi", guest_1sBookings.get(0).getGuestName());
        assertEquals(1, guest_1sBookings.get(0).getRoomNo());
        assertEquals("Ravi", guest_1sBookings.get(1).getGuestName());
        assertEquals(2, guest_1sBookings.get(1).getRoomNo());

        List<Booking> guest_2Bookings = bookingService.findBookingsByGuest("Priya");
        assertEquals(1, guest_2Bookings.size());
        assertEquals("Priya", guest_2Bookings.get(0).getGuestName());
        assertEquals(3, guest_2Bookings.get(0).getRoomNo());
    }
	
}
