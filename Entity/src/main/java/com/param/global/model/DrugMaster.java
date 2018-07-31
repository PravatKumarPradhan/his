package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name="GET_DRUG_MASTER_LIST",
				query=" SELECT  drgMst.drugId as drugId, drgMst.genericName as genericName, drgMst.medicationName as medicationName, drgMst.status as status,"
					+ " 		to_char(drgMst.createdDate, 'DD-MM-YYYY') as createdDate, to_char(drgMst.updatedDate, 'DD-MM-YYYY') as updatedDate,"
					+ " 		drgMst.createdBy as createdBy, drgMst.updatedBy as updatedBy, drgMst.organizationId as organizationId, drgMst.unitId as unitId,"
					+ " 		drgMst.isVaccineStatus as isVaccineStatus "
					+ " FROM 	DrugMaster drgMst"
					+ " WHERE 	drgMst.unitId=:unitId"
					+ " AND 	drgMst.organizationId=:orgId"
					+ " AND 	drgMst.status = 'A' "
					+ " AND 	drgMst.isVaccineStatus = 'A' "
					
				)
		
})


@Entity
@Table(name="m_drug_master",schema="public")
@SequenceGenerator(name="m_drug_master_seq", sequenceName="m_drug_master_seq", allocationSize=1)
public class DrugMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="m_drug_master_seq")
	@Column(name="drug_id")
	private int drugId;
	
	@Column(name="generic_name")
	private String genericName;
	
	@Column(name="medication_name")
	private String medicationName;
	
	@Column(name="status")
	private char status;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="organization_id")
	private int organizationId;
	
	@Column(name="unit_id")
	private int unitId;
	
	@Column(name="is_vaccine_status")
	private char isVaccineStatus;
	

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public char getIsVaccineStatus() {
		return isVaccineStatus;
	}

	public void setIsVaccineStatus(char isVaccineStatus) {
		this.isVaccineStatus = isVaccineStatus;
	}
	
	
}
