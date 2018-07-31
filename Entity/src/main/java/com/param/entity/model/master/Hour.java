package com.param.entity.model.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Hour")
@Table(name = "m_hour_master", schema = "public")
public class Hour implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hour_id", unique = true, nullable = false)
	private Integer hourId;

	private Integer hours;

	public Hour() {
	}

	public Integer getHourId() {
		return this.hourId;
	}

	public void setHourId(Integer hourId) {
		this.hourId = hourId;
	}

	public Integer getHours() {
		return this.hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

}