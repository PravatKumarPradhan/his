package com.param.rabbitmq.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Request {

	protected String key;
	
	protected Payload payload;

	public Request() {
		super();
	}

	public Request(String key, Payload payload) {
		super();
		this.key = key;
		this.payload = payload;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}
}
