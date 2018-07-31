package com.param.rabbitmq.dto.base;

public class Payload {
	protected String actionType;

	protected Object data;

	public Payload() {
		super();
	}

	public Payload(String actionType, Object data) {
		super();
		this.actionType = actionType;
		this.data = data;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
