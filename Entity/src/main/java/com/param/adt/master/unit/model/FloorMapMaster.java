package com.param.adt.master.unit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
@Entity
@Table(name="m_floor_map_master",schema="adt")
@SequenceGenerator(name="floor_map_master_seq",sequenceName="adt.floor_map_master_seq",allocationSize=1)
public class FloorMapMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="floor_map_master_seq")
	@Column(name="floor_map_id")
	private int floorMapId;
	
	@Column(name="floor_id")
	private Integer floorId;
	
	@Column(name="wing_id")
	private Integer wingId;
	
	@Column(name="speciality_id")
	private Integer specialityId;
	
	@Column(name="sub_speciality_id")
	private Integer subSpecialityId;

	@Column(name = "status")
	private char status;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
}
