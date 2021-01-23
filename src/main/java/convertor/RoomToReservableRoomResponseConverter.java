package convertor;

import com.vsvdev.booking.vsvdevangularspring.entity.Room;
import com.vsvdev.booking.vsvdevangularspring.model.Links;
import com.vsvdev.booking.vsvdevangularspring.model.Self;
import com.vsvdev.booking.vsvdevangularspring.model.response.ReservableRoomResponse;
import com.vsvdev.booking.vsvdevangularspring.rest.ResourseConstants;

import org.springframework.core.convert.converter.Converter;

public class RoomToReservableRoomResponseConverter implements Converter<Room, ReservableRoomResponse> {

	@Override
	public ReservableRoomResponse convert(Room source) {

		ReservableRoomResponse reservationResponse = new ReservableRoomResponse();
		if (null != source.getId()) {
			reservationResponse.setId(source.getId());
		}
		reservationResponse.setRoomNumber(source.getRoomNumber());
		reservationResponse.setPrice(Integer.valueOf(source.getPrice()));
		Links links = new Links();
		Self self = new Self();
		self.setRef(ResourseConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
		links.setSelf(self);

		reservationResponse.setLinks(links);

		return reservationResponse;
	}

}
