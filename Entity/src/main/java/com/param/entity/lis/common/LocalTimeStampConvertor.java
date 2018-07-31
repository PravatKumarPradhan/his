package com.param.entity.lis.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;

public class LocalTimeStampConvertor implements AttributeConverter<LocalTime, Date>
{
	@Override
	public Date convertToDatabaseColumn(LocalTime localTime) {
		Date date = null;
		try {			
			Instant instant = localTime.atDate(LocalDate.of(2016, 9, 12)).atZone(ZoneId.systemDefault()).toInstant();
			date = Date.from(instant);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}

	@Override
	public LocalTime convertToEntityAttribute(Date timeSlot) {
		LocalTime localTime = null;
		try {			
			Instant instant = Instant.ofEpochMilli(timeSlot.getTime());
			localTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localTime;
	}

}
