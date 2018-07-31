package com.param.entity.model.pharmacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Gender;
import com.param.entity.model.master.Prefix;

@Entity(name = "TempPatientRegistration")
@Table(name = "t_temp_patient_registration", schema = "pharmacy")
public class TempPatientRegistration extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String address;

	private Integer age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id")
	private Gender gender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prefix_id")
	private Prefix prefix;

	@Column(name = "patient_name")
	private String patientName;

	@Column(name = "temp_uhid")
	private String tempUhid;

	public TempPatientRegistration() {
	}

	public TempPatientRegistration(String address, Integer age, Gender gender, String patientName, Prefix prefix,
			String tempUhid) {
		super();
		this.address = address;
		this.age = age;
		this.gender = gender;
		this.patientName = patientName;
		this.prefix = prefix;
		this.tempUhid = tempUhid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getTempUhid() {
		return tempUhid;
	}

	public void setTempUhid(String tempUhid) {
		this.tempUhid = tempUhid;
	}

	public Prefix getPrefix() {
		return prefix;
	}

	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
}