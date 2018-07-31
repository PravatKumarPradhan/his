package com.param.opd.coversheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.ClinicMaster;


@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_NURSING_LIST_BY_CLINIC_ID",
				query="     SELECT "
						+ " 			nursingMst.nursing_station_id as \"nursingStationId\", nursingMst.nursing_station_code as \"nursingStationCode\","
						+ "				nursingMst.nursing_station_desc as \"nursingStationDesc\" "
						+ " FROM 		opd.t_nursing_station_clinic_mapper nurClincMapp "
						+ " INNER JOIN 	adt.m_nursing_station_master nursingMst on nurClincMapp.nursing_station_id=nursingMst.nursing_station_id "
						+ " WHERE 		nurClincMapp.clinic_id=:clinicId"
						+ " AND 		nursingMst.unit_id=:unitId "
						+ " AND 		nursingMst.organization_id=:organizationId"
						+ " AND 		nursingMst.status='A' "
			)
})


@Entity
@Table(name="t_nursing_station_clinic_mapper", schema="opd")
@SequenceGenerator(name = "t_nursing_station_clinic_mapper_seq", sequenceName = "opd.t_nursing_station_clinic_mapper_seq", allocationSize = 1)
public class NursingStationClinicMapper {

	@Id
	@Column(name="nursing_clinic_mapp_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_nursing_station_clinic_mapper_seq")
	private int nursingClinicMappId;
	
	@Column(name = "clinic_id")
	private Integer clinicId;
	
	@Column(name = "nursing_station_id")
	private Integer nursingStationId;
	
	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "cerated_date")
	private Date cerated_date;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organization_id")
	private Integer organizationId;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic_id", insertable = false, nullable = false, updatable = false)
	private ClinicMaster clinicMaster;
	
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nursing_station_id", insertable = false, nullable = false, updatable = false)
	private NursingStationMaster nursingStationMaster;*/
	
	public int getNursingClinicMappId() {
		return nursingClinicMappId;
	}

	public void setNursingClinicMappId(int nursingClinicMappId) {
		this.nursingClinicMappId = nursingClinicMappId;
	}

	public Integer getClinicId() {
		return clinicId;
	}

	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}

	public Integer getNursingStationId() {
		return nursingStationId;
	}

	public void setNursingStationId(Integer nursingStationId) {
		this.nursingStationId = nursingStationId;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getCerated_date() {
		return cerated_date;
	}

	public void setCerated_date(Date cerated_date) {
		this.cerated_date = cerated_date;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	
	
}
