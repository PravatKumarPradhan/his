package com.param.rabbitmq.service.EmrService;

import org.springframework.stereotype.Service;

import com.param.rabbitmq.dto.base.Request;
import com.param.rabbitmq.util.RabbitMQ;

@Service("EmrService")
public class EmrService implements IEmrService {

	@Override
	public Object publish(Request request) {

		try {
			boolean publish = RabbitMQ.getInstance().publish("HIS.EXCHANGE", "EMR", "EMR", request);
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
