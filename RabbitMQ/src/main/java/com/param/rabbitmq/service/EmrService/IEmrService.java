package com.param.rabbitmq.service.EmrService;

import com.param.rabbitmq.dto.base.Request;

public interface IEmrService {

	Object publish(Request request);
	
}
