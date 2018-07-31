package com.param.rabbitmq.dto.base;

public class Data {
	protected long id;
	
	protected int organization_id;
	
	protected int unit_id;
	
	protected boolean is_active;
	
	protected boolean is_deleted;

	public Data() {
		super();
	}

	public Data(long id, int organization_id, int unit_id, boolean is_active, boolean is_deleted) {
		super();
		this.id = id;
		this.organization_id = organization_id;
		this.unit_id = unit_id;
		this.is_active = is_active;
		this.is_deleted = is_deleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
}
