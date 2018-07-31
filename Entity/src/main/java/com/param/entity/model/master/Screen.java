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

@Entity(name = "Screen")
@Table(name = "m_screen", schema = "public")
public class Screen extends CommonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 10)
	private String code;

	@Column(length = 100)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "screen")
	private List<ScreenPriority> screenPriorityMapperList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "screen")
	private List<ScreenStatus> screenStatusMapperList;

	public Screen() {
	}

	public Screen(Integer id) {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ScreenPriority> getScreenPriorityMapperList() {
		return this.screenPriorityMapperList;
	}

	public void setScreenPriorityMapperList(List<ScreenPriority> screenPriorityMapperList) {
		this.screenPriorityMapperList = screenPriorityMapperList;
	}

	public ScreenPriority addScreenPriorityMapperList(ScreenPriority screenPriorityMapperList) {
		getScreenPriorityMapperList().add(screenPriorityMapperList);
		screenPriorityMapperList.setScreen(this);

		return screenPriorityMapperList;
	}

	public ScreenPriority removeScreenPriorityMapperList(ScreenPriority screenPriorityMapperList) {
		getScreenPriorityMapperList().remove(screenPriorityMapperList);
		screenPriorityMapperList.setScreen(null);

		return screenPriorityMapperList;
	}

	public List<ScreenStatus> getScreenStatusMapperList() {
		return this.screenStatusMapperList;
	}

	public void setScreenStatusMapperList(List<ScreenStatus> screenStatusMapperList) {
		this.screenStatusMapperList = screenStatusMapperList;
	}

	public ScreenStatus addScreenStatusMapperList(ScreenStatus screenStatusMapperList) {
		getScreenStatusMapperList().add(screenStatusMapperList);
		screenStatusMapperList.setScreen(this);

		return screenStatusMapperList;
	}

	public ScreenStatus removeScreenStatusMapperList(ScreenStatus screenStatusMapperList) {
		getScreenStatusMapperList().remove(screenStatusMapperList);
		screenStatusMapperList.setScreen(null);

		return screenStatusMapperList;
	}

}
