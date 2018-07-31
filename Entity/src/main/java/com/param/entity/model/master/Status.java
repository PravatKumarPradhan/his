package com.param.entity.model.master;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.base.CommonEntity;

@Entity(name = "Status")
@Table(name = "m_status", schema = "public")
public class Status extends CommonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 10)
	private String code;

	@Column(length = 100)
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	private List<ScreenStatus> screenStatusMapperList;

	public Status() {
	}
	
	public Status(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ScreenStatus> getScreenStatusMapperList() {
		return this.screenStatusMapperList;
	}

	public void setScreenStatusMapperList(List<ScreenStatus> screenStatusMapperList) {
		this.screenStatusMapperList = screenStatusMapperList;
	}

	public ScreenStatus addScreenStatusMapperList(ScreenStatus screenStatusMapperList) {
		getScreenStatusMapperList().add(screenStatusMapperList);
		screenStatusMapperList.setStatus(this);

		return screenStatusMapperList;
	}

	public ScreenStatus removeScreenStatusMapperList(ScreenStatus screenStatusMapperList) {
		getScreenStatusMapperList().remove(screenStatusMapperList);
		screenStatusMapperList.setStatus(null);

		return screenStatusMapperList;
	}

}
