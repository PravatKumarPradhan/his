package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="GET_MODALITY_MASTER_LIST",query="SELECT modality.modalityId as modalityId, "
			+ "modality.equipmentId as equipmentId, "
			+ "eqp.equipmentDesc as equipmentDesc, "
			+ "modality.modalityCode as modalityCode, "
			+ "modality.modalityDesc as modalityDesc, "
			+ "modality.status as status, "
			+ "modality.specialityId as specialityId, "
			+ "speciality.specialityName as specialityName, "
			+ "modality.subSpecialityId as subSpecialityId, "
			+ "subSpeciality.subSpecialityMasterName as subSpecialityName "
			+ "FROM ModalityMaster modality "
			+ "INNER JOIN modality.equipmentMaster eqp "
			+ "INNER JOIN modality.specialityMaster speciality "
			+ "INNER JOIN modality.subSpecialityMaster subSpeciality "
			+ "WHERE modality.organizationId=:orgId "
			+ "AND modality.unitId=:unitId "),
	
	@NamedQuery(name="GET_MODALITY_BY_NAME",query="SELECT modality.modalityId as modalityId, "
			+ "modality.modalityDesc as modalityDesc "
			+ "FROM ModalityMaster modality "
			+ "WHERE modality.organizationId=:orgId "
			+ "AND modality.unitId=:unitId "
			+ "AND modality.modalityDesc=:modalityDesc "),
	
	@NamedQuery(name="GET_MODALITY_BY_NAME_NOT_ID",query="SELECT modality.modalityId as modalityId, "
			+ "modality.modalityDesc as modalityDesc "
			+ "FROM ModalityMaster modality "
			+ "WHERE modality.organizationId=:orgId "
			+ "AND modality.unitId=:unitId "
			+ "AND modality.modalityDesc=:modalityDesc "
			+ "AND modality.modalityId!=:modalityId "),
	
	@NamedQuery(name="GET_MODALITY_BY_ID",query="SELECT modality.modalityId as modalityId, "
			+ "modality.equipmentId as equipmentId, "
			//+ "eqp.equipmentDesc as equipmentDesc, "
			+ "modality.modalityCode as modalityCode, "
			+ "modality.modalityDesc as modalityDesc, "
			+ "modality.status as status, "
			+ "modality.specialityId as specialityId, "
			+ "speciality.specialityName as specialityName, "
			+ "modality.subSpecialityId as subSpecialityId, "
			+ "subSpeciality.subSpecialityMasterName as subSpecialityName "
			+ "FROM ModalityMaster modality "
			//+ "INNER JOIN modality.equipmentMaster eqp "
			+ "INNER JOIN modality.specialityMaster speciality "
			+ "INNER JOIN modality.subSpecialityMaster subSpeciality "
			+ "WHERE modality.organizationId=:orgId "
			+ "AND modality.unitId=:unitId "
			+ "AND modality.modalityId=:modalityId "),
	
	@NamedQuery(name="GET_ACTIVE_MODALITY_LIST",query="SELECT modality.modalityId as modalityId, "
			+ "modality.equipmentId as equipmentId, "
			//+ "eqp.equipmentDesc as equipmentDesc, "
			+ "modality.modalityCode as modalityCode, "
			+ "modality.modalityDesc as modalityDesc, "
			+ "modality.status as status, "
			+ "modality.specialityId as specialityId, "
			+ "speciality.specialityName as specialityName, "
			+ "modality.subSpecialityId as subSpecialityId, "
			+ "subSpeciality.subSpecialityMasterName as subSpecialityName "
			+ "FROM ModalityMaster modality "
			//+ "INNER JOIN modality.equipmentMaster eqp "
			+ "INNER JOIN modality.specialityMaster speciality "
			+ "INNER JOIN modality.subSpecialityMaster subSpeciality "
			+ "WHERE modality.organizationId=:orgId "
			+ "AND modality.unitId=:unitId "
			+ "AND modality.status='A' "),
	
	@NamedQuery(name="GET_ACTIVE_MODALITY_LIST_BY_SUB_SPECILAITY_ARRAY",query="SELECT modality.modalityId as modalityId, "
			+ "modality.equipmentId as equipmentId, "
			+ "modality.modalityDesc as modalityDesc, "
			+ "modality.status as status, "
			+ "modality.specialityId as specialityId, "
			+ "speciality.specialityName as specialityName, "
			+ "modality.subSpecialityId as subSpecialityId, "
			+ "subSpeciality.subSpecialityMasterName as subSpecialityName "
			+ "FROM ModalityMaster modality "
			+ "INNER JOIN modality.specialityMaster speciality "
			+ "INNER JOIN modality.subSpecialityMaster subSpeciality "
			+ "LEFT JOIN modality.equipmentMaster equipMast "
			+ "WHERE modality.organizationId=:orgId "
			+ "AND modality.unitId=:unitId "
			+ "AND modality.subSpecialityId IN (:subSpecialityArray) "
			+ "AND modality.status='A' "),
	
	@NamedQuery(name="GET_MODALITY_LIST_BY_SUB_SPECILAITY_ID",query="SELECT modality.modalityId as modalityId, "
			+ "modality.modalityCode as modalityCode, "
			+ "modality.modalityDesc as modalityDesc,"
			+ "modality.status as status, "
			+ "modality.equipmentId as equipmentId, "
			+ "equipMast.equipmentNumber as equipmentNumber, "
			+ "equipMast.path as path, "
			+ "modality.specialityId as specialityId, "
			+ "speciality.specialityName as specialityName, "
			+ "modality.subSpecialityId as subSpecialityId, "
			+ "subSpeciality.subSpecialityMasterName as subSpecialityName "
			+ "FROM ModalityMaster modality "
			+ "INNER JOIN modality.specialityMaster speciality "
			+ "INNER JOIN modality.subSpecialityMaster subSpeciality "
			+ "LEFT JOIN modality.equipmentMaster equipMast "
			+ "WHERE modality.organizationId=:orgId "
			+ "AND modality.unitId=:unitId "
			+ "AND modality.subSpecialityId =:subSpecialityId "
			+ "AND modality.status='A' "),
		@NamedQuery(name="GET_MODALITYMASTER_FOR_SYNC_BY_ID",
	    query="SELECT modality.modalityDesc AS modalityDesc,"
	    		+ "modality.status AS status,"
	    		+ "modality.modalityCode AS modalityCode,"
	    		+ "modality.equipmentId AS equipmentId,"
	    		+ "modality.subSpecialityId AS subGroupId,"
	    		+ "to_char(modality.createdDate,'DD-MM-YYYY HH24:MI:SS.SSS') AS createdDate,"
	    		+ "modality.createdBy AS createdBy,"
	    		+ "to_char(modality.updatedDate,'DD-MM-YYYY HH24:MI:SS.SSS' ) AS updatedDate,"
	    		+ "modality.updatedBy AS updatedBy,"
	    		+ "modality.unitId AS unitId,"
	    		+ "modality.organizationId AS hospitalId,"
	    		+ "modality.modalityId AS modalityLocalId,"
	    		+ "2 as applicationId "	    	
	    	    + "FROM ModalityMaster modality "	   
	    	    + "INNER JOIN modality.specialityMaster specialityMaster "
	    	    + "INNER JOIN modality.subSpecialityMaster subSpeciality "
	    	    + "WHERE modality.modalityId=:modalityId  "
	    	    + "AND modality.status='A' ")
		
	
})

@Entity
@Table(name="m_modality_master",schema="public")
@SequenceGenerator(name="modality_master_seq",sequenceName="public.modality_master_seq",allocationSize=1)
public class ModalityMaster {

	@Id
	@Column(name="modality_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="modality_master_seq")
	private int modalityId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="equipment_id")
	private Integer equipmentId;
	
	@Column(name="speciality_id")
	private Integer specialityId;
	
	@Column(name="sub_speciality_id")
	private Integer subSpecialityId;
	
	@Column(name="modality_code")
	private String modalityCode;
	
	@Column(name="modality_desc")
	private String modalityDesc;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id", insertable = false, nullable = false, updatable = false)
	private EquipmentMaster equipmentMaster;
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modality_type_id", insertable = false, nullable = false, updatable = false)
	private ModalityTypeMaster modalityTypeMaster;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, nullable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_speciality_id", insertable = false, nullable = false, updatable = false)
	private SubSpecialityMaster subSpecialityMaster;
	

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public int getModalityId() {
		return modalityId;
	}

	public void setModalityId(int modalityId) {
		this.modalityId = modalityId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getModalityCode() {
		return modalityCode;
	}

	public void setModalityCode(String modalityCode) {
		this.modalityCode = modalityCode;
	}

	public String getModalityDesc() {
		return modalityDesc;
	}

	public void setModalityDesc(String modalityDesc) {
		this.modalityDesc = modalityDesc;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
}