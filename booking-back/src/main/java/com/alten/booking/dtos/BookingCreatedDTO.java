package com.alten.booking.dtos;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingCreatedDTO {

	@NotNull(message = "Check In Date should not be null.")
	@ApiModelProperty(required = true, example = "2021-12-12")
	private LocalDate checkInDate;

	@ApiModelProperty(required = true, example = "2021-12-15")
	@NotNull(message = "Check Out Date should not be blank.")
	private LocalDate checkOutDate;

	@ApiModelProperty(notes = "Reservation date as YYYY-MM-DD", required = true)
	private LocalDateTime createdDate;

	@ApiModelProperty(notes = "Reservation date as YYYY-MM-DD", required = true)
	private LocalDateTime updatedDate;

	public BookingCreatedDTO() {

	}

	public BookingCreatedDTO(@NotNull(message = "Check In Date should not be null.") LocalDate checkInDate,
			@NotNull(message = "Check Out Date should not be blank.") LocalDate checkOutDate, LocalDateTime createdDate,
			LocalDateTime updatedDate) {
		super();
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
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
