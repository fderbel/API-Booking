package com.alten.booking.controllers;

import com.alten.booking.dtos.BookingCreatedDTO;
import com.alten.booking.dtos.BookingRecivedDTO;
import com.alten.booking.entities.Booking;
import com.alten.booking.services.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fatma DERBEL BOUATTOUR
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "User Rest API")
public class BookingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	private final BookingService bookingService;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public BookingController(final BookingService bookingService) {
		this.bookingService = bookingService;
	}

	/**
	 * getAllBooking
	 * 
	 * @return
	 */
	@ApiOperation(value = "Get all bookings")
	@GetMapping(value = "/bookings")
	public ResponseEntity<List<BookingRecivedDTO>> getAllBooking() {
		LOGGER.info("Get all bookings.");
		List<BookingRecivedDTO> listBookingDTO = bookingService.findAllBookings().stream()
				.map(booking -> modelMapper.map(booking, BookingRecivedDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<BookingRecivedDTO>>(listBookingDTO, HttpStatus.OK);
	}

	/**
	 * getBookingById
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "get a booking by id")
	@GetMapping(value = "/bookings/{id}")
	public ResponseEntity<BookingRecivedDTO> getBookingById(
			@PathVariable("id") @ApiParam(value = "The booking id") final String id) {
		return ResponseEntity.ok().body(modelMapper.map(bookingService.findBookingById(id), BookingRecivedDTO.class));
	}

	/**
	 * deleteBookingById
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Delete a booking by id")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "bookings/delete/{id}")
	public ResponseEntity<Void> deleteBookingById(
			@PathVariable("id") @ApiParam(value = "The booking id") final String id) {

		LOGGER.info("Deleting booking by id: {}.", id);
		bookingService.deleteBookingById(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * 
	 * @param booking
	 * @return
	 */

	@ApiOperation(value = "Create a new booking")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/bookings/create")
	public ResponseEntity<BookingRecivedDTO> createBooking(
			@RequestBody @ApiParam(value = "The booking data") @Valid final BookingCreatedDTO booking) {
		LOGGER.info("Inserting booking: {}.", booking);
		Booking bookingCreated = modelMapper.map(booking, Booking.class);
		BookingRecivedDTO bookingRecivedDTO = modelMapper.map(bookingService.insertBooking(bookingCreated),
				BookingRecivedDTO.class);
		return ResponseEntity.created(URI.create("/api/bookings/" + bookingRecivedDTO.getId())).body(bookingRecivedDTO);

	}

	/**
	 * 
	 * @param booking
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Update a booking by its id")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(value = "/bookings/update/{id}")
	public ResponseEntity<BookingRecivedDTO> updateBooking(
			@RequestBody @ApiParam(value = "The booking data") @Valid final BookingCreatedDTO booking,
			@PathVariable("id") @ApiParam(value = "The booking id") final String id) {
		Booking bookingCreated = modelMapper.map(booking, Booking.class);
		BookingRecivedDTO bookingRecivedDTO = modelMapper.map(bookingService.updateBooking(id, bookingCreated),
				BookingRecivedDTO.class);
		return ResponseEntity.created(URI.create("/api/bookings/" + bookingRecivedDTO.getId())).body(bookingRecivedDTO);
	}

}
