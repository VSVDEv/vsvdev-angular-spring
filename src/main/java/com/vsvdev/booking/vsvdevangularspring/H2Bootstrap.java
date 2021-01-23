package com.vsvdev.booking.vsvdevangularspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vsvdev.booking.vsvdevangularspring.entity.Room;
import com.vsvdev.booking.vsvdevangularspring.repository.RoomRepository;
@Component
public class H2Bootstrap implements CommandLineRunner {
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public void run(String... args) throws Exception {
		roomRepository.save(new Room(405, "200"));
		roomRepository.save(new Room(407, "210"));
		roomRepository.save(new Room(408, "220"));
		roomRepository.save(new Room(409, "250"));

		Iterable<Room> itr = roomRepository.findAll();
		for (Room room : itr) {
			System.out.println(room.getRoomNumber());
		}
	}

}
