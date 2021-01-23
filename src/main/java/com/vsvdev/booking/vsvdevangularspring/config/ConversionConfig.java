package com.vsvdev.booking.vsvdevangularspring.config;

import java.util.HashSet;
import java.util.Set;


import convertor.ReservationRequestToReservationConverter;
import convertor.ReservationToReservationResponseConverter;
import convertor.RoomToReservableRoomResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;



@Configuration
public class ConversionConfig {
	@SuppressWarnings("rawtypes")
	private Set<Converter> getConverters() {
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(new RoomToReservableRoomResponseConverter());
		converters.add(new ReservationRequestToReservationConverter());
		converters.add(new ReservationToReservationResponseConverter());

		return converters;
	}

	@Bean public ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();

		return bean.getObject();
	}
}
