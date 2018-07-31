package com.param.rabbitmq.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQ {
	private static RabbitMQ rabbitMQ;

	private ConnectionFactory factory;

	private Connection conn;

	private Channel channel;

	private RabbitMQ() {

	}

	public static RabbitMQ getInstance() {
		if (rabbitMQ == null) {
			rabbitMQ = new RabbitMQ();
		}
		return rabbitMQ;
	}

	public boolean publish(String exchange, String queue, String routingKey, Object message) {
		boolean publish = false;

		try {
			if (this.conn == null || !this.conn.isOpen()) {
				getConnection();
			}

			if (this.conn != null) {
				if (this.channel == null || !this.channel.isOpen()) {
					this.channel = this.conn.createChannel();
				}

				if (channel != null) {
					channel.queueDeclare(queue, true, false, false, null);
					channel.basicPublish(exchange, routingKey, null, new ObjectMapper().writeValueAsString(message).getBytes());
					publish = true;
				}
			}
		} catch (Exception ex) {

		}

		return publish;
	}

	private void getConnection() {
		try {
			if (this.factory == null) {
				RMQConnection rmqConn = RMQConnection.getInstance();
				this.factory = new ConnectionFactory();
				this.factory.setUsername(rmqConn.getUser());
				this.factory.setPassword(rmqConn.getPassword());
				this.factory.setVirtualHost(rmqConn.getVhost());
				this.factory.setHost(rmqConn.getHost());
			}

			this.conn = this.factory.newConnection();
		} catch (Exception ex) {
		}
	}
}