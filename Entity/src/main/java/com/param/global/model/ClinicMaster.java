package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.param.adt.master.unit.model.FloorMaster;
import com.param.adt.master.unit.model.WingMaster;


@NamedQueries({
	
	@NamedQuery(name="GET_CLINIC_MASTER_LIST_BY_ORG_AND_UNIT",
				query="SELECT 		clinic.clinicMasterId as clinicMasterId , clinic.clinicCode as clinicCode , "
						+ "			clinic.clinicDescription as clinicDescription , clinic.created_by as created_by , "
						+ "			to_char(clinic.cerated_date , 'DD-MM-YYYY') as cerated_date , clinic.updated_by as updated_by , "
						+ "			to_char(clinic.updated_date , 'DD-MM-YYYY') as updated_date , clinic.organizationId as organizationId , "
						+ "			clinic.unitId as unitId , clinic.status as status ,"
						+ "			clinic.floorId as floorId, clinic.wingId as wingId,floor.floorName as floorName , wing.wingDesc as wingDesc  "
						+ "FROM		ClinicMaster clinic "
						+ "INNER JOIN clinic.floorMaster floor "
						+ "INNER JOIN clinic.wingMaster wing "
						+ "WHERE	clinic.organizationId =:organizationId "
						+ "AND 		clinic.unitId =:unitId "
						+ "ORDER BY clinic.clinicMasterId "),
						
	@NamedQuery(name="GET_CLINIC_BY_NAME_ORG_AND_UNIT",
				query="SELECT 		clinic.clinicMasterId as clinicMasterId ,clinic.clinicDescription as clinicDescription ,"
						+ "			clinic.organizationId as organizationId ,clinic.unitId as unitId "
						+ "FROM		ClinicMaster clinic "
						+ "WHERE	LOWER(clinic.clinicDescription) = :clinicDescription "
						+ "AND		clinic.organizationId =:organizationId "
						+ "AND 		clinic.unitId =:unitId "),
						
	@NamedQuery(name="GET_CLINIC_BY_NAME_AND_NOT_BY_ID",
				query="SELECT 		clinic.clinicMasterId as clinicMasterId ,clinic.clinicDescription as clinicDescription ,"
						+ "			clinic.organizationId as organizationId ,clinic.unitId as unitId "
						+ "FROM		ClinicMaster clinic "
						+ "WHERE	LOWER(clinic.clinicDescription) = :clinicDescription "
						+ "AND		clinic.clinicMasterId <> :clinicMasterId "
						+ "AND		clinic.organizationId =:organizationId "
						+ "AND 		clinic.unitId =:unitId "),
						
	@NamedQuery(name="GET_CLINIC_MASTER_BY_ID",
				query="SELECT 		clinic.clinicMasterId as clinicMasterId , clinic.clinicCode as clinicCode , "
						+ "			clinic.clinicDescription as clinicDescription , clinic.created_by as created_by , "
						+ "			to_char(clinic.cerated_date , 'DD-MM-YYYY') as cerated_date , clinic.updated_by as updated_by , "
						+ "			to_char(clinic.updated_date , 'DD-MM-YYYY') as updated_date , clinic.organizationId as organizationId , "
						+ "			clinic.unitId as unitId , clinic.status as status , clinic.floorId as floorId, clinic.wingId as wingId "
						+ "FROM		ClinicMaster clinic "
						+ "WHERE	clinic.clinicMasterId = :clinicMasterId "
						+ "AND		clinic.organizationId =:organizationId "
						+ "AND 		clinic.unitId =:unitId "),
						
	@NamedQuery(name="UPDATE_CLINIC_STATUS_BY_ID",
				query="UPDATE ClinicMaster SET status = :status "
						+ "WHERE clinicMasterId = :clinicMasterId ")
})

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_CLINIC_COUNT",
						query="select 		count(*) from public.m_clinic_master clinic "
								+ "where 	clinic.organization_id =:organizationId "
								+ "and		clinic.unit_id =:unitId ")
})

@Entity
@Table(name="m_clinic_master", schema="public")
@SequenceGenerator(name = "m_clinic_master_seq", sequenceName = "public.m_clinic_master_seq",  allocationSize = 1)
public class ClinicMaster {

	@Id
	@Column(name="clinic_master_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_clinic_master_seq")
	private int clinicMasterId;
	
	@Column(name="clinic_code")
	private String clinicCode;
	
	@Column(name="clinic_description")
	private String clinicDescription;
	
	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "created_date")
	private Date cerated_date;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "organization_id")
	private Integer organizationId;
	
	@Column(name = "status")
	private char status;
	
	@Column(name="floor_id")
	private Integer floorId;
	
	@Column(name = "wing_id")
	private Integer wingId;
	
	
	@ManyToOne
	@JoinColumn(name="floor_id",insertable = false , updatable = false , nullable = false)
	private FloorMaster floorMaster;

	@ManyToOne
	@JoinColumn(name="wing_id",insertable = false , updatable = false , nullable = false)
	private WingMaster wingMaster;
	
	public int getClinicMasterId() {
		return clinicMasterId;
	}

	public void setClinicMasterId(int clinicMasterId) {
		this.clinicMasterId = clinicMasterId;
	}

	public String getClinicCode() {
		return clinicCode;
	}

	public void setClinicCode(String clinicCode) {
		this.clinicCode = clinicCode;
	}

	public String getClinicDescription() {
		return clinicDescription;
	}

	public void setClinicDescription(String clinicDescription) {
		this.clinicDescription = clinicDescription;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Integer getWingId() {
		return wingId;
	}

	public void setWingId(Integer wingId) {
		this.wingId = wingId;
	}

	public FloorMaster getFloorMaster() {
		return floorMaster;
	}

	public void setFloorMaster(FloorMaster floorMaster) {
		this.floorMaster = floorMaster;
	}

	public WingMaster getWingMaster() {
		return wingMaster;
	}

	public void setWingMaster(WingMaster wingMaster) {
		this.wingMaster = wingMaster;
	}
	
	
	
	
}
