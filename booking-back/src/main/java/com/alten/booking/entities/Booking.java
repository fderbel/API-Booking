package com.alten.booking.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Generated;

@Document(collection = "bookings")

public class Booking {

	@Id
	@Generated(value = { "" })
	private String id;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	public Booking() {

	}

	public Booking(final LocalDate checkInDate, final LocalDate checkOutDate, final LocalDateTime createdDate,
			final LocalDateTime updatedDate) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
