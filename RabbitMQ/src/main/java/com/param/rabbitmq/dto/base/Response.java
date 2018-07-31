package com.param.rabbitmq.dto.base;

public class Response extends Request {

	public Response() {
		super();
	}

	public Response(String key, Payload payload) {
		super(key, payload);
	}

}
