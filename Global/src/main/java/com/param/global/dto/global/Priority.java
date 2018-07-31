package com.param.global.dto.global;

public class Priority {
	protected Integer id;

	protected String priority;

	public Priority(Integer id, String priority) {
		super();
		this.id = id;
		this.priority = priority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}
