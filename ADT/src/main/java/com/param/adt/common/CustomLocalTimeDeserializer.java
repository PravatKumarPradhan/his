package com.param.adt.common;

import java.io.IOException;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomLocalTimeDeserializer extends StdDeserializer<LocalTime>{
	
	private static final long serialVersionUID = 1L;

	public CustomLocalTimeDeserializer(){
		super(LocalTime.class);
	}

	@Override
	public LocalTime deserialize(JsonParser p, DeserializationContext ctxs)throws IOException, JsonProcessingException {
		System.out.println(p.getValueAsString());
		return LocalTime.parse(p.getValueAsString());
	}
}
