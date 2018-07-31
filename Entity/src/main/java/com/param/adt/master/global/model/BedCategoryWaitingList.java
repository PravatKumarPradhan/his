package com.param.adt.master.global.model;

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

import com.param.adt.admission.model.AdmissionNote;
import com.param.global.model.BedCategoryMaster;

@NamedQueries({ 
	
	@NamedQuery(name="GET_WAITING_LIST_DETAILS",query="SELECT MAX(pcwl.waitListNumber) as waitListNumber " 
			+ " FROM BedCategoryWaitingList pcwl " 
			+ " WHERE pcwl.organizationId=:organizationId "
			+ " AND pcwl.unitId=:unitId " 
			+ " AND pcwl.doa=:doa " 
			+ " AND pcwl.bedCategoryId=:bedCategoryId "
			+ " AND pcwl.status='A' "),
	
	@NamedQuery(name="GET_WAITING_LIST_DATA_WHICH_ARE_GREATER_THAN_WAITING_LIST_NUMBER_OF_CURRENT_ADMISSION",query=""
			+ "SELECT pcwl.bedCategoryWaitingListId as bedCategoryWaitingListId, "
			+ " pcwl.admissionNoteId as admissionNoteId, "
			+ " pcwl.organizationId as organizationId, "
			+ " pcwl.unitId as unitId, "
			+ " pcwl.bedCategoryId as bedCategoryId, "
			+ " to_char(pcwl.doa,'dd-MM-yyyy') as doa, "
			+ " pcwl.waitListNumber as waitListNumber "
			+ " FROM BedCategoryWaitingList pcwl "
			+ " WHERE pcwl.organizationId=:organizationId "
			+ " AND pcwl.unitId=:unitId " 
			+ " AND pcwl.doa=:doa " 
			+ " AND pcwl.bedCategoryId=:bedCategoryId "
			+ " AND pcwl.status='A' "
			+ " AND pcwl.waitListNumber > (SELECT pcwl.waitListNumber FROM BedCategoryWaitingList pcwl "
			+ " WHERE pcwl.organizationId=:organizationId "
			+ " AND pcwl.unitId=:unitId " 
			+ " AND pcwl.bedCategoryWaitingListId=:bedCategoryWaitingListId "
			+ " AND pcwl.status='A') "),
	
	@NamedQuery(name="GET_WATING_LIST_DETAILS",query="SELECT distinct(bcwl.bedCategoryId) as bedCategoryId, "
			+ "to_char(bcwl.doa,'yyyy-MM-dd') as doa, "
			+ "(SELECT max(bc.waitListNumber) FROM BedCategoryWaitingList bc "
			+ "WHERE bc.status='A' "
			+ "AND bc.doa=bcwl.doa "
			+ "AND bc.bedCategoryId=bcwl.bedCategoryId) as waitListNumber "
			+ "FROM BedCategoryWaitingList bcwl "
			+ "WHERE bcwl.status='A' "
			+ "AND bcwl.doa BETWEEN :startDate AND :endDate "
			+ "AND bcwl.organizationId=:organizationId "
			+ "AND bcwl.unitId=:unitId ")
	
	
	/*"select distinct(bcwl.bed_category_id) as \"bedCategoryId\", "
	+ "to_char(bcwl.doa,'yyyy-MM-dd') as \"doa\", "
	+ "(select max(wait_list_number) FROM adt.t_bed_category_waiting_list "
	+ "where status='A' "
	+ "AND doa = bcwl.doa "
	+ "AND bed_category_id = bcwl.bed_category_id) as \"waitListNumber\" "
	+ "from adt.t_bed_category_waiting_list bcwl "
	+ "where status='A' "
	+ "AND doa between ':startDate' AND ':endDate' "
	+ "AND organization_id=:organizationId "
	+ "AND unit_id=:unitId "*/
})



@Entity
@Table(name="t_bed_category_waiting_list",schema="adt")
@SequenceGenerator(name="bed_category_waiting_list_seq",sequenceName="adt.bed_category_waiting_list_seq",allocationSize=1)
public class BedCategoryWaitingList {

	@Id
	@Column(name="bed_category_waiting_list_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="bed_category_waiting_list_seq")
	private int bedCategoryWaitingListId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="admission_note_id")
	private Integer admissionNoteId;

	@Column(name="bed_category_id")
	private Integer bedCategoryId;
	
	@Column(name="doa")
	private Date doa;
	
	@Column(name="wait_list_number")
	private Integer waitListNumber;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_note_id", insertable = false, updatable = false)
	private AdmissionNote admissionNote;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_category_id", insertable = false, updatable = false)
	private BedCategoryMaster bedCategoryMaster;

	public int getBedCategoryWaitingListId() {
		return bedCategoryWaitingListId;
	}

	public void setBedCategoryWaitingListId(int bedCategoryWaitingListId) {
		this.bedCategoryWaitingListId = bedCategoryWaitingListId;
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

	public Integer getAdmissionNoteId() {
		return admissionNoteId;
	}

	public void setAdmissionNoteId(Integer admissionNoteId) {
		this.admissionNoteId = admissionNoteId;
	}

	public Integer getBedCategoryId() {
		return bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public Date getDoa() {
		return doa;
	}

	public void setDoa(Date doa) {
		this.doa = doa;
	}

	public Integer getWaitListNumber() {
		return waitListNumber;
	}

	public void setWaitListNumber(Integer waitListNumber) {
		this.waitListNumber = waitListNumber;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
