package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;

@Entity
@Table(name = "m_frequency_detail")
@NamedQuery(name = "FrequencyDetail.findAll", query = "SELECT f FROM FrequencyDetail f")
public class FrequencyDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="administration_time")
	private Timestamp administrationTime;

	@Column(name="post_buffer_administration_time")
	private Timestamp postBufferAdministrationTime;

	@Column(name="pre_buffer_administration_time")
	private Timestamp preBufferAdministrationTime;

	private String slot;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="frequency_id")
	private Frequency frequency;

	public FrequencyDetail() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getAdministrationTime() {
		return administrationTime;
	}

	public void setAdministrationTime(Timestamp administrationTime) {
		this.administrationTime = administrationTime;
	}

	public Timestamp getPostBufferAdministrationTime() {
		return postBufferAdministrationTime;
	}

	public void setPostBufferAdministrationTime(Timestamp postBufferAdministrationTime) {
		this.postBufferAdministrationTime = postBufferAdministrationTime;
	}

	public Timestamp getPreBufferAdministrationTime() {
		return preBufferAdministrationTime;
	}

	public void setPreBufferAdministrationTime(Timestamp preBufferAdministrationTime) {
		this.preBufferAdministrationTime = preBufferAdministrationTime;
	}

	public String getSlot() {
		return this.slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public Frequency getMFrequency() {
		return this.frequency;
	}

	public void setMFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public FrequencyDetail(Timestamp administrationTime, Timestamp postBufferAdministrationTime, Timestamp preBufferAdministrationTime,
			String slot) {
		super();
		this.administrationTime = administrationTime;
		this.postBufferAdministrationTime = postBufferAdministrationTime;
		this.preBufferAdministrationTime = preBufferAdministrationTime;
		this.slot = slot;
	}

	public void updateFrequencyDetail(Timestamp administrationTime, Timestamp postBufferAdministrationTime, Timestamp preBufferAdministrationTime,
			String slot) {
		this.administrationTime = administrationTime;
		this.postBufferAdministrationTime = postBufferAdministrationTime;
		this.preBufferAdministrationTime = preBufferAdministrationTime;
		this.slot = slot;
	}
	
}