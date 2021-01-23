package convertor;

import com.vsvdev.booking.vsvdevangularspring.entity.Reservation;
import com.vsvdev.booking.vsvdevangularspring.model.request.ReservationRequest;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToReservationConverter  implements Converter<ReservationRequest, Reservation> {

    @Override
    public Reservation convert(ReservationRequest source) {

        Reservation reservationEntity = new Reservation();
        reservationEntity.setCheckin(source.getCheckin());
        reservationEntity.setCheckout(source.getCheckout());
        if (null != source.getId())
            reservationEntity.setId(source.getId());

        return reservationEntity;
    }
}
