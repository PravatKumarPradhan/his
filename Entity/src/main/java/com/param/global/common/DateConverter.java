package com.param.global.common;

import java.util.Date;

import javax.persistence.AttributeConverter;

public class DateConverter implements AttributeConverter<Long,Date> {

	@Override
	public Date convertToDatabaseColumn(Long longDate) {
		Date date = null;
		try {
			if(longDate != null) 
				date=new Date(longDate);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public Long convertToEntityAttribute(Date date) {
		Long longDate = null;
		try {
			if(date != null)
				longDate = date.getTime();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return longDate;
	}

}
