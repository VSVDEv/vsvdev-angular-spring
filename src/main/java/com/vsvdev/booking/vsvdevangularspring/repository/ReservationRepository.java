package com.vsvdev.booking.vsvdevangularspring.repository;

import com.vsvdev.booking.vsvdevangularspring.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
