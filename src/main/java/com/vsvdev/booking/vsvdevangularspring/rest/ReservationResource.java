package com.vsvdev.booking.vsvdevangularspring.rest;

import com.vsvdev.booking.vsvdevangularspring.entity.Reservation;
import com.vsvdev.booking.vsvdevangularspring.entity.Room;
import com.vsvdev.booking.vsvdevangularspring.model.request.ReservationRequest;
import com.vsvdev.booking.vsvdevangularspring.model.response.ReservableRoomResponse;
import com.vsvdev.booking.vsvdevangularspring.model.response.ReservationResponse;
import com.vsvdev.booking.vsvdevangularspring.repository.PageableRoomRepository;
import com.vsvdev.booking.vsvdevangularspring.repository.ReservationRepository;
import com.vsvdev.booking.vsvdevangularspring.repository.RoomRepository;
import convertor.RoomToReservableRoomResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ResourseConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {
	@Autowired
	PageableRoomRepository pageableRoomRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<ReservableRoomResponse> getAvailableRooms(
			@RequestParam(value = "checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
			@RequestParam(value = "checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
			Pageable pageable) {

		Page<Room> roomEntityList = pageableRoomRepository.findAll(pageable);
		List<ReservableRoomResponse> list = new ArrayList<>();
		for (Room room : roomEntityList) {
			ReservableRoomResponse r = new RoomToReservableRoomResponseConverter().convert(room);
			if (checkFree(room, checkin, checkout)) {
				list.add(r);
			}
		}
		Page<ReservableRoomResponse> page = new PageImpl<>(list);
		return page;
	}

	@RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {

		Room roomEntity = roomRepository.findById(roomId).orElseThrow();

		return new ResponseEntity<>(roomEntity, HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest) {

		Reservation reservationEntity = conversionService.convert(reservationRequest, Reservation.class);
		reservationRepository.save(reservationEntity);

		Room roomEntity = roomRepository.findById(reservationRequest.getRoomId()).orElseThrow();
		roomEntity.addReservation(reservationEntity);
		roomRepository.save(roomEntity);
		reservationEntity.setRoomEntity(roomEntity);

		ReservationResponse reservationResponse = conversionService.convert(reservationEntity,
				ReservationResponse.class);

		return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservableRoomResponse> updateReservation(
			@RequestBody ReservationRequest reservationRequest) {

		return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	public boolean checkFree(Room source, LocalDate checkin, LocalDate checkout) {
		List<Reservation> reservationEntityList = source.getReservationEntityList();
		List<Boolean> check = new ArrayList<>();
		for (Reservation res : reservationEntityList) {
			LocalDate resCheckin = res.getCheckin();
			LocalDate resCheckout = res.getCheckout();
			if (((checkin.isBefore(resCheckin)) && (checkout.isBefore(resCheckin.plusDays(1))))
					|| ((checkin.isAfter(resCheckout.minusDays(1))) && (checkout.isAfter(resCheckout)))) {
				check.add(true);
			} else {
				check.add(false);
			}
		}
		return !check.contains(false);
	}
}
