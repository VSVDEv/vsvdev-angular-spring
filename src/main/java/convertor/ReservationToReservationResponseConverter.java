package convertor;

import com.vsvdev.booking.vsvdevangularspring.entity.Reservation;
import com.vsvdev.booking.vsvdevangularspring.model.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;

public class ReservationToReservationResponseConverter  implements Converter<Reservation, ReservationResponse> {

    @Override
    public ReservationResponse convert(Reservation source) {

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(source.getCheckin());
        reservationResponse.setCheckout(source.getCheckout());

        if (null != source.getRoomEntity())
            reservationResponse.setId(source.getRoomEntity().getId());

        return reservationResponse;
    }
}
