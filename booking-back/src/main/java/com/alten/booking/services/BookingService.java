package com.alten.booking.services;

import com.alten.booking.entities.Booking;

import java.util.List;

/**
 * @author fatma DERBEL BOUATTOUR
 */
public interface BookingService {

	List<Booking> findAllBookings();

	Booking findBookingById(final String id);

	void deleteBookingById(final String id);

	Booking updateBooking(final String id, final Booking booking);

	Booking insertBooking(final Booking booking);

}
