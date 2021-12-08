package com.alten.booking.services.impl;

import com.alten.booking.entities.Booking;
import com.alten.booking.repositories.BookingRepository;
import com.alten.booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static com.alten.booking.utils.Constants.*;

/**
 * @author fatma DERBEL BOUATTOUR
 */

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;

	/**
	 * 
	 * @param bookingRepository
	 */
	@Autowired
	public BookingServiceImpl(final BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	@Override
	public List<Booking> findAllBookings() {
		return StreamSupport.stream(bookingRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public Booking findBookingById(String id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		if (!booking.isPresent()) {
			throw new RuntimeException(BOOKING_NOT_FOUND);
		}
		return booking.get();
	}

	@Override
	public void deleteBookingById(String id) {
		final Optional<Booking> booking = bookingRepository.findById(id);
		if (!booking.isPresent()) {
			throw new RuntimeException(BOOKING_NOT_FOUND);
		}
		bookingRepository.delete(booking.get());
	}

	@Override
	public Booking insertBooking(Booking booking) {
		checkValidReservation(booking);
		return bookingRepository.insert(booking);
	}

	@Override
	public Booking updateBooking(final String id, final Booking booking) {
		final Optional<Booking> bookingfound = bookingRepository.findById(id);
		if (!bookingfound.isPresent()) {
			throw new RuntimeException(BOOKING_NOT_FOUND);
		}
		checkValidReservation(booking);
		bookingfound.get().setCheckInDate(booking.getCheckInDate());
		bookingfound.get().setCheckOutDate(booking.getCheckOutDate());
		bookingfound.get().setUpdatedDate(booking.getUpdatedDate());
		return bookingRepository.save(bookingfound.get());
	}

	/**
	 * * check if the dates of the reservation are valid
	 * @param booking
	 */
	private void checkValidReservation(final Booking booking) {
		final LocalDate now = LocalDate.now(ZoneId.systemDefault());
		LocalDate bookingcheckInDate = booking.getCheckInDate();
		LocalDate bookingcheckOutDate = booking.getCheckOutDate();

		if (bookingcheckInDate == null || bookingcheckOutDate == null) {
			throw new RuntimeException(NULL_DATE);
		}
		if (bookingcheckInDate.isAfter(bookingcheckOutDate)) {
			throw new RuntimeException(CHECKIN_BEFORE_CHECKOUT);
		}

		if (bookingcheckInDate.isBefore(now) || bookingcheckInDate.equals(now)) {
			throw new RuntimeException(CHECKIN_START);
		}
		long lengthOfStay = ChronoUnit.DAYS.between(bookingcheckInDate, bookingcheckOutDate);
		if (lengthOfStay > MAXIMUM_LENGHT_STAY_DAYS) {
			throw new RuntimeException(LENGHT_STAY);
		}
		long diffFromDateNow = ChronoUnit.DAYS.between(now, bookingcheckInDate);
		if (diffFromDateNow > MAXIMUM_DIFF_DAYS_IN_ADVANCE) {
			throw new RuntimeException(RESERVED_STAY_ADVANCE);
		}

		if (!isAvailableRoom(booking)) {
			throw new RuntimeException(ROOM_AVAILIBLE);
		}
	}
	/**
	 * * check if the room is availaible
	 * @param booking
	 * @return
	 */
	private boolean isAvailableRoom(final Booking booking) {
		final List<Booking> listBooking = findAllBookings();

		boolean availableBooking = true;
		Iterator<Booking> itr = listBooking.listIterator();

		while (availableBooking && itr.hasNext()) {

			if (reservationOverlaps(itr.next(), booking.getCheckInDate(), booking.getCheckOutDate())) {
				availableBooking = false;
			}
		}
		return availableBooking;
	}

	/**
	 * check the overlap of dates against existing reservation
	 * 
	 * @param reservation
	 * @param checkInDate
	 * @param checkOutDate
	 * @return
	 */
	private boolean reservationOverlaps(final Booking reservation, final LocalDate checkInDate,
			final LocalDate checkOutDate) {

		if (checkOutDate.isBefore(reservation.getCheckInDate())) {
			return false;
		}
		if (checkOutDate.equals(reservation.getCheckInDate()) || checkOutDate.equals(reservation.getCheckOutDate())
				|| checkInDate.equals(reservation.getCheckInDate())
				|| checkInDate.equals(reservation.getCheckOutDate())) {
			return true;
		}

		if (checkInDate.isAfter(reservation.getCheckInDate()) && checkInDate.isBefore(reservation.getCheckOutDate())
				|| checkOutDate.isAfter(reservation.getCheckInDate())
						&& checkOutDate.isBefore(reservation.getCheckOutDate())) {
			return true;
		}
		return false;

	}

}
