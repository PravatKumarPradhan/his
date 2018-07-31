package com.param.entity.lis.common;

import java.util.Date;

import javax.persistence.AttributeConverter;

public class LocalTimeConverter implements AttributeConverter<Long, Date> {

	@Override
	public Date convertToDatabaseColumn(Long localTime) {
		Date date = null;

		try {

			if (localTime != null) {
				date = new Date(localTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public Long convertToEntityAttribute(Date timeSlot) {
		Long localTime = null;
		try {
			if (timeSlot != null) {
				localTime = timeSlot.getTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localTime;
	}


}
