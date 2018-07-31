package com.param.global.dto.global;

public class Prefix {
	protected Integer id;

	protected String prefix;

	public Prefix(Integer id, String prefix) {
		super();
		this.id = id;
		this.prefix = prefix;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
