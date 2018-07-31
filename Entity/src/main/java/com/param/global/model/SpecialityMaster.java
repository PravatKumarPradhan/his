package com.param.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.unit.model.UnitSpecialityMapper;
import com.param.entity.org.model.GeneralLedgerMaster;
import com.param.service.global.model.TContractCapDetails;
import com.param.service.global.model.TContractGroupDetails;
import com.param.service.global.model.TContractGroupPharmacyExclusionDetails;
import com.param.service.global.model.TPackageCapDetails;


@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_ACTIVE_SPECIALITY_LIST",query="SELECT specialityMaster.speciality_id as \"specialityId\", "
			+ "specialityMaster.speciality_name as \"specialityName\", "
			+ "specialityMaster.is_clinical_speciality as \"isClinicalSpeciality\" "
			+ "FROM doctor.m_speciality_master specialityMaster "
			+ "INNER JOIN public.t_unit_speciality_mapper usm on specialityMaster.speciality_id=usm.speciality_id "
			+ "WHERE specialityMaster.status='A' "
			+ "AND specialityMaster.organization_id=:organizationId "
			+ "AND usm.unit_id=:unitId "
			+ "AND usm.status='A' "),
	@NamedNativeQuery(name = "GET_SPECIALITY_LIST", 
	 query = "SELECT	specialityMaster.speciality_id as \"specialityId\", "
			+ "specialityMaster.speciality_name as \"specialityName\", "
			+ "specialityMaster.speciality_code as \"specialityCode\", "
			+ "ledgerMster.general_ledger_name as \"generalLedgerName\", "
			+ "specialityMaster.status as \"status\", "
			+ "specialityMaster.is_surgical_code as \"isSurgicalCode\", "
			+ "specialityMaster.is_clinical_speciality as \"isClinicalSpeciality\" "
			+ "FROM 	doctor.m_speciality_master specialityMaster  "
			+ "INNER JOIN public.m_general_ledger_master ledgerMster ON specialityMaster.general_ledger_id=ledgerMster.general_ledger_id  "
			+ "WHERE specialityMaster.organization_id=:orgId " + "ORDER BY specialityMaster.speciality_id DESC") ,

	@NamedNativeQuery(name = "GET_ORG_ACTIVE_SPECIALITY_LIST_NOT_MAPPED_TO_UNIT", 
	 query = "SELECT specialityMaster.speciality_id as \"specialityId\", "
			+ "specialityMaster.speciality_name as \"specialityName\", "
			+ "specialityMaster.speciality_code as \"specialityCode\", "			
			+ "specialityMaster.status as \"status\", "
			+ "specialityMaster.is_surgical_code as \"isSurgicalCode\", "
			+ "specialityMaster.is_clinical_speciality as \"isClinicalSpeciality\" "
			+ "FROM doctor.m_speciality_master specialityMaster  "			
			+ "WHERE specialityMaster.speciality_id NOT IN "
			+ "(SELECT usm.speciality_id as \"specialityId\" FROM public.t_unit_speciality_mapper usm where (usm.status='A' or usm.status='I') AND usm.unit_id=:unitId) "
			+ " AND specialityMaster.organization_id=:orgId "
		    + "ORDER BY specialityMaster.speciality_id DESC") ,
	
})
@NamedQueries({

		@NamedQuery(name = "GET_SPECIALITY_LIST_FOR_SUB_SPE", query = "SELECT specialityMaster.specialityId as specialityId, "
				+ "specialityMaster.specialityName as specialityName " + "FROM SpecialityMaster as specialityMaster "
				+ "WHERE specialityMaster.status='A'"),

		@NamedQuery(name = "GET_SPECIALITY_LIST_BY_ID", query = "SELECT	SpecialityMaster.specialityId as specialityId, SpecialityMaster.specialityName as specialityName, "
				+ " 			SpecialityMaster.createdBy as createdBy, SpecialityMaster.specialityCode as specialityCode, SpecialityMaster.specialityName as specialityName,"
				+ "           SpecialityMaster.isSurgicalCode as isSurgicalCode,SpecialityMaster.isClinicalSpeciality as isClinicalSpeciality,SpecialityMaster.generalLedgerId as generalLedgerId, SpecialityMaster.status as status "
				+ " FROM 		SpecialityMaster SpecialityMaster "
				+ "WHERE SpecialityMaster.specialityId = :specialityId "),

		@NamedQuery(name = "GET_SPECIALITY_LIST_BY_NAME_NOT_ID", query = "SELECT	SpecialityMaster.specialityId as specialityId, SpecialityMaster.specialityName as specialityName, "
				+ " 			SpecialityMaster.createdBy as createdBy, SpecialityMaster.specialityCode as specialityCode, SpecialityMaster.specialityName as specialityName,"
				+ "           SpecialityMaster.isSurgicalCode as isSurgicalCode, SpecialityMaster.status as status "
				+ " FROM 		SpecialityMaster SpecialityMaster "
				+ "WHERE (LOWER(SpecialityMaster.specialityName) =:specialityName OR SpecialityMaster.specialityName=:specialityName) AND SpecialityMaster.specialityId !=:specialityId "),

		

		@NamedQuery(name = "GET_SPECIALITY_LIST_BY_NAME", query = "SELECT	SpecialityMaster.specialityId as specialityId, SpecialityMaster.specialityName as specialityName, "
				+ " 			SpecialityMaster.createdBy as createdBy, SpecialityMaster.specialityCode as specialityCode, SpecialityMaster.specialityName as specialityName,"
				+ "           SpecialityMaster.isSurgicalCode as isSurgicalCode, SpecialityMaster.status as status "
				+ " FROM 		SpecialityMaster SpecialityMaster "
				+ "WHERE LOWER(SpecialityMaster.specialityName) =:specialityName OR SpecialityMaster.specialityName=:specialityName"),

//		@NamedQuery(name = "GET_SPECIALITY_LIST", query = "SELECT	SpecialityMaster.specialityId as specialityId, "
//				+ "SpecialityMaster.specialityName as specialityName, "
//				+ "SpecialityMaster.specialityCode as specialityCode, "
//				+ "SpecialityMaster.specialityCode as specialityCode, "
//				+ "SpecialityMaster.status as status, "
//				+ "SpecialityMaster.isSurgicalCode as isSurgicalCode, "
//				+ "SpecialityMaster.isClinicalSpeciality as isClinicalSpeciality "
//				+ "FROM 	SpecialityMaster SpecialityMaster "
//				+ "WHERE SpecialityMaster.organizationId=:orgId " + "ORDER BY SpecialityMaster.specialityId DESC") 
		})



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "m_speciality_master", schema = "doctor")
@SequenceGenerator(name = "speciality_master_seq", sequenceName = "doctor.speciality_master_seq", allocationSize = 1)
public class SpecialityMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "speciality_master_seq")
	@Column(name = "speciality_id")
	private int specialityId;

	@Column(name = "speciality_name")
	private String specialityName;

	@Column(name = "speciality_code")
	private String specialityCode;

	@Column(name = "is_surgical_code")
	private char isSurgicalCode;
	
	@Column(name="is_clinical_speciality")
	private char isClinicalSpeciality;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "general_ledger_id")
	private Integer generalLedgerId;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "organization_id")
	private Integer organizationId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceMaster> specialityList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ModalityMaster> listModalityMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitSpecialityMapper> unitSpecialityMappersList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DoctorSpecialityMapper> doctorSpecialityMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TPackageCapDetails> listTPackageCapDetails;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "specialityMaster")
	private List<TContractGroupPharmacyExclusionDetails> listTContractGroupPharmacyExclusionDetails;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "specialityMaster")
	private List<TContractCapDetails> listTContractCapDetails;

	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "specialityMaster")
	private List<TContractGroupDetails> listTContractGroupDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "general_ledger_id", insertable = false, updatable = false)
	GeneralLedgerMaster generalLedgerMaster;

	
	public List<TContractGroupDetails> getListTContractGroupDetails() {
		return listTContractGroupDetails;
	}

	public void setListTContractGroupDetails(List<TContractGroupDetails> listTContractGroupDetails) {
		this.listTContractGroupDetails = listTContractGroupDetails;
	}
	
	public List<TContractCapDetails> getListTContractCapDetails() {
		return listTContractCapDetails;
	}

	public void setListTContractCapDetails(List<TContractCapDetails> listTContractCapDetails) {
		this.listTContractCapDetails = listTContractCapDetails;
	}

	public List<TContractGroupPharmacyExclusionDetails> getListTContractGroupPharmacyExclusionDetails() {
		return listTContractGroupPharmacyExclusionDetails;
	}

	public void setListTContractGroupPharmacyExclusionDetails(List<TContractGroupPharmacyExclusionDetails> listTContractGroupPharmacyExclusionDetails) {
		this.listTContractGroupPharmacyExclusionDetails = listTContractGroupPharmacyExclusionDetails;
	}
	
	public List<TPackageCapDetails> getListTPackageCapDetails() {
		return listTPackageCapDetails;
	}

	public void setListTPackageCapDetails(List<TPackageCapDetails> listTPackageCapDetails) {
		this.listTPackageCapDetails = listTPackageCapDetails;
	}

	public char getIsClinicalSpeciality() {
		return isClinicalSpeciality;
	}

	public void setIsClinicalSpeciality(char isClinicalSpeciality) {
		this.isClinicalSpeciality = (isClinicalSpeciality == '\u0000') ? 'A' : isClinicalSpeciality;
	}

	public int getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(int specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public char getIsSurgicalCode() {
		return isSurgicalCode;
	}

	public void setIsSurgicalCode(char isSurgicalCode) {
		this.isSurgicalCode = (isSurgicalCode == '\u0000') ? 'A' : isSurgicalCode;
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

	public Integer getGeneralLedgerId() {
		return generalLedgerId;
	}

	public void setGeneralLedgerId(Integer generalLedgerId) {
		this.generalLedgerId = generalLedgerId;
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

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public List<ServiceMaster> getSpecialityList() {
		return specialityList;
	}

	public void setSpecialityList(List<ServiceMaster> specialityList) {
		this.specialityList = specialityList;
	}

	public List<ModalityMaster> getListModalityMaster() {
		return listModalityMaster;
	}

	public void setListModalityMaster(List<ModalityMaster> listModalityMaster) {
		this.listModalityMaster = listModalityMaster;
	}

	public GeneralLedgerMaster getGeneralLedgerMaster() {
		return generalLedgerMaster;
	}

	public void setGeneralLedgerMaster(GeneralLedgerMaster generalLedgerMaster) {
		this.generalLedgerMaster = generalLedgerMaster;
	}
	
	
}