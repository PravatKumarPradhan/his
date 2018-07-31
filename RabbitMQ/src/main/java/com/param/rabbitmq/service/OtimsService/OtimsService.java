package com.param.rabbitmq.service.OtimsService;

import org.springframework.stereotype.Service;

import com.param.rabbitmq.dto.base.Request;
import com.param.rabbitmq.util.RabbitMQ;

@Service("OtimsService")
public class OtimsService implements IOtimsService {

	@Override
	public Object publish(Request request) {
		try {
			boolean publish = RabbitMQ.getInstance().publish("HIS.EXCHANGE", "OTIMS", "OTIMS", request);
			if (publish) {
				
				return "Message successfully published to RabbitMQ";
			} else {
				return "Message publish failed";
			}
		} catch (Exception ex) {
			return null;
		}
	}
}
