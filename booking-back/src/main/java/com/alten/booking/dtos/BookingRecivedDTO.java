package com.alten.booking.dtos;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class BookingRecivedDTO {

	@ApiModelProperty(example = "608707209f974627a3ca5d70")
	private String id;

	@NotNull(message = "Check In Date should not be null.")
	@ApiModelProperty(required = true, example = "2021-12-12")
	private LocalDate checkInDate;

	@ApiModelProperty(required = true, example = "2021-12-15")
	@NotNull(message = "Check Out Date should not be blank.")
	private LocalDate checkOutDate;

	public BookingRecivedDTO() {

	}

	public BookingRecivedDTO(LocalDate checkInDate, LocalDate checkOutDate) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

}
