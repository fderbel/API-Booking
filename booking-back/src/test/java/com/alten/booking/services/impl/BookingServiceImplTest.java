package com.alten.booking.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import static com.alten.booking.utils.Constants.*;
import com.alten.booking.entities.Booking;
import com.alten.booking.repositories.BookingRepository;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(PowerMockRunner.class)
public class BookingServiceImplTest {

	@InjectMocks
	private BookingServiceImpl bookingService;

	@Mock
	private BookingRepository bookingRepository;

	private List<Booking> listBookings = new ArrayList<>();
	private Booking exempleBooking;
	private static final LocalDate DATE = OffsetDateTime.now(ZoneOffset.UTC).toLocalDate();
	private static final LocalDateTime Current_DATE = LocalDateTime.now();

	@Before
	public void setup() throws Exception {
		exempleBooking = new Booking(DATE, DATE, Current_DATE, Current_DATE);
		listBookings.add(exempleBooking);
	}

	@Test
	public void testFindAllBookings() {

		when(bookingRepository.findAll()).thenReturn(listBookings);

		List<Booking> actualListBookings = bookingService.findAllBookings();
		assertEquals(exempleBooking.getCheckInDate(), actualListBookings.get(0).getCheckInDate());
		assertEquals(exempleBooking.getCheckOutDate(), actualListBookings.get(0).getCheckOutDate());
	}

	@Test
	public void testFindBookingById() {

		when(bookingRepository.findById("id")).thenReturn(Optional.of(exempleBooking));
		final Booking actualBooking = bookingService.findBookingById("id");
		assertEquals(exempleBooking.getCheckInDate(), actualBooking.getCheckInDate());
		assertEquals(exempleBooking.getCheckOutDate(), actualBooking.getCheckOutDate());
	}

	@Test()
	public void testNotFoundBookingById() {
		when(bookingRepository.findById("id1")).thenReturn(Optional.empty());
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.findBookingById("id1"))
				.withMessage(BOOKING_NOT_FOUND);
	}

	@Test
	public void testdeleteBookingById() {
		when(bookingRepository.findById("id")).thenReturn(Optional.of(exempleBooking));
		bookingService.deleteBookingById("id");
		verify(bookingRepository, times(1)).delete(any(Booking.class));
	}

	@Test()
	public void testNotFoundDeleteBookingById() {
		when(bookingRepository.findById("id1")).thenReturn(Optional.ofNullable(null));
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.deleteBookingById("id1"))
				.withMessage(BOOKING_NOT_FOUND);
	}

	@Test
	public void testInsertValidBooking() {
		final Booking bookingInsert = new Booking(DATE.plusDays(2), DATE.plusDays(3), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		when(bookingRepository.insert(any(Booking.class))).thenReturn(bookingInsert);

		final Booking actualBooking = bookingService.insertBooking(bookingInsert);
		assertEquals(bookingInsert.getCheckInDate(), actualBooking.getCheckInDate());
		assertEquals(bookingInsert.getCheckOutDate(), actualBooking.getCheckOutDate());
	}

	@Test
	public void testCheckInBeforeCheckout() {
		final Booking bookingInsert = new Booking(DATE.plusDays(3), DATE.plusDays(2), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.insertBooking(bookingInsert))
				.withMessage(CHECKIN_BEFORE_CHECKOUT);
	}

	@Test
	public void testCheckInStartNow() {
		final Booking bookingInsert = new Booking(DATE, DATE.plusDays(2), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.insertBooking(bookingInsert))
				.withMessage(CHECKIN_START);
	}

	@Test
	public void testLenghtStayThenThree() {
		final Booking bookingInsert = new Booking(DATE.plusDays(3), DATE.plusDays(7), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.insertBooking(bookingInsert))
				.withMessage(LENGHT_STAY);
	}

	@Test
	public void testReserveStayAdvance() {
		final Booking bookingInsert = new Booking(DATE.plusDays(31), DATE.plusDays(32), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.insertBooking(bookingInsert))
				.withMessage(RESERVED_STAY_ADVANCE);
	}

	@Test
	public void testRoomAvailibleTest1() {
		Booking reservedBooking = new Booking(DATE.plusDays(4), DATE.plusDays(8), Current_DATE, Current_DATE);
		listBookings.add(reservedBooking);
		final Booking bookingInsert = new Booking(DATE.plusDays(4), DATE.plusDays(6), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.insertBooking(bookingInsert))
				.withMessage(ROOM_AVAILIBLE);
	}

	@Test
	public void testRoomAvailibleTest2() {
		Booking reservedBooking = new Booking(DATE.plusDays(4), DATE.plusDays(8), Current_DATE, Current_DATE);
		listBookings.add(reservedBooking);
		final Booking bookingInsert = new Booking(DATE.plusDays(5), DATE.plusDays(8), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.insertBooking(bookingInsert))
				.withMessage(ROOM_AVAILIBLE);
	}

	@Test
	public void testRoomAvailibleTest3() {
		Booking reservedBooking = new Booking(DATE.plusDays(4), DATE.plusDays(8), Current_DATE, Current_DATE);
		listBookings.add(reservedBooking);
		final Booking bookingInsert = new Booking(DATE.plusDays(5), DATE.plusDays(6), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.insertBooking(bookingInsert))
				.withMessage(ROOM_AVAILIBLE);
	}

	@Test
	public void testRoomAvailibleTest4() {
		Booking reservedBooking = new Booking(DATE.plusDays(4), DATE.plusDays(8), Current_DATE, Current_DATE);
		listBookings.add(reservedBooking);
		final Booking bookingInsert = new Booking(DATE.plusDays(7), DATE.plusDays(9), Current_DATE, Current_DATE);
		when(bookingRepository.findAll()).thenReturn(listBookings);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> bookingService.insertBooking(bookingInsert))
				.withMessage(ROOM_AVAILIBLE);
	}

	@Test
	public void testupdateFoundBooking() {
		final Booking existantBooking = new Booking(DATE.plusDays(2), DATE.plusDays(4), Current_DATE, Current_DATE);
		listBookings.add(existantBooking);
		final Booking newBooking = new Booking(DATE.plusDays(2), DATE.plusDays(5), Current_DATE, Current_DATE);

		when(bookingRepository.findById("id")).thenReturn(Optional.of(existantBooking));

		bookingService.updateBooking("id", newBooking);

		verify(bookingRepository, times(1)).save(any(Booking.class));
	}

	@Test
	public void testUpdateNotFoundBooking() {

		final Booking newBooking = new Booking(DATE.plusDays(2), DATE.plusDays(5), Current_DATE, Current_DATE);
		when(bookingRepository.findById("id1")).thenReturn(Optional.ofNullable(null));
		assertThatExceptionOfType(RuntimeException.class)
				.isThrownBy(() -> bookingService.updateBooking("id1", newBooking)).withMessage(BOOKING_NOT_FOUND);
	}

}
