package com.param.rabbitmq.util;

import java.util.ResourceBundle;

public class RMQConnection {

	private ResourceBundle rb = ResourceBundle.getBundle("rabbitmq");

	private static RMQConnection connection;

	private RMQConnection() {

	}

	public static RMQConnection getInstance() {
		if (connection == null) {
			connection = new RMQConnection();
		}
		return connection;
	}

	public String getHost() {
		return rb.getString("host");
	}

	public String getUser() {
		return rb.getString("user");
	}

	public String getPassword() {
		return rb.getString("password");
	}

	public String getVhost() {
		return rb.getString("vhost");
	}

	public String getUrl() {
		return rb.getString("url");
	}
}