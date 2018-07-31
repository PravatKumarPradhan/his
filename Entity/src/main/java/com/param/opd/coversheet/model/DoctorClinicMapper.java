package com.param.opd.coversheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.param.global.model.AllergyMaster;
import com.param.global.model.ClinicMaster;
import com.param.global.model.DoctorMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_LIST_OF_CLINIC_MASTER_BY_DOCTOR_ID",
			query="    SELECT "
					+ " clMst.clinicMasterId as clinicMasterId, clMst.clinicCode as clinicCode, clMst.clinicDescription as clinicDescription,"
					+ " clMst.created_by as created_by "
					+ " FROM DoctorClinicMapper docClinMapp "
					+ " INNER JOIN docClinMapp.clinicMaster clMst "
					+ " WHERE docClinMapp.doctorClinicMapperId.doctorId=:doctoId "
					+ " AND clMst.organizationId=:organizationId "
					+ " AND clMst.unitId=:unitId "
					+ " AND clMst.status='A' "
			)
	
	
})


@Entity
@Table(name="t_doctor_clinic_mapper", schema="doctor")
public class DoctorClinicMapper {

	@EmbeddedId
	private DoctorClinicMapperId doctorClinicMapperId;
	
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctorMaster;
	
	
	
	
	public ClinicMaster getClinicMaster() {
		return clinicMaster;
	}

	public void setClinicMaster(ClinicMaster clinicMaster) {
		this.clinicMaster = clinicMaster;
	}

	public DoctorMaster getDoctorMaster() {
		return doctorMaster;
	}

	public void setDoctorMaster(DoctorMaster doctorMaster) {
		this.doctorMaster = doctorMaster;
	}

	public DoctorClinicMapperId getDoctorClinicMapperId() {
		return doctorClinicMapperId;
	}

	public void setDoctorClinicMapperId(DoctorClinicMapperId doctorClinicMapperId) {
		this.doctorClinicMapperId = doctorClinicMapperId;
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
