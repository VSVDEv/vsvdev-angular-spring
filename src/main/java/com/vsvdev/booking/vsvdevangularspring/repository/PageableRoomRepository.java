package com.vsvdev.booking.vsvdevangularspring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vsvdev.booking.vsvdevangularspring.entity.Room;

public interface PageableRoomRepository extends PagingAndSortingRepository<Room, Long> {
Page<Room> findById(Long id, Pageable page);


}
