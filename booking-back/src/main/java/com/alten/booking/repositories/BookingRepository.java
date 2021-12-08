package com.alten.booking.repositories;

import com.alten.booking.entities.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
