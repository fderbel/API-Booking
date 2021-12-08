package com.alten.booking.utils;

public class Constants {

	public static final Integer MAXIMUM_LENGHT_STAY_DAYS = 3;
	public static final Integer MAXIMUM_DIFF_DAYS_IN_ADVANCE = 30;
	public static final String BOOKING_NOT_FOUND = "booking not found";
	public static final String CHECKIN_BEFORE_CHECKOUT = "The date Check In should be before the  date Check Out";
	public static final String CHECKIN_START = "the booking should start at least the next day of booking";
	public static final String LENGHT_STAY = "the stay can’t be longer than " + MAXIMUM_LENGHT_STAY_DAYS + "days ";
	public static final String RESERVED_STAY_ADVANCE = "the stay can’t be reserved more than"
			+ MAXIMUM_DIFF_DAYS_IN_ADVANCE + "days in advance";
	public static final String ROOM_AVAILIBLE = "room not availible in this date";
	public static final String NULL_DATE = "the date should not be null";

}
