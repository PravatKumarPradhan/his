package com.param.rabbitmq.service.HisService;

import org.springframework.stereotype.Service;

import com.param.rabbitmq.dto.base.Request;
import com.param.rabbitmq.util.RabbitMQ;

@Service("HisService")
public class HisService implements IHisService {
	@Override
	public Object publish(Request request) {

		try {
			boolean publish = RabbitMQ.getInstance().publish("HIS.EXCHANGE", "HIS", "HIS", request);
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