package com.vsvdev.booking.vsvdevangularspring.repository;



import com.vsvdev.booking.vsvdevangularspring.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {
Optional<Room> findById(Long id);
}
