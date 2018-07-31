package com.param.adt.master.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.admission.model.Admission;
import com.param.adt.admission.model.AdmissionDetails;
import com.param.adt.admission.model.AdmissionPatientDocuments;
import com.param.adt.admission.model.Sponsor;
import com.param.adt.admission.model.SponsorDetails;
import com.param.adt.admission.model.UnreservedPatient;
import com.param.adt.admission.model.VisitorAgainstPatient;
import com.param.adt.admission.model.VisitorPassTypeMaster;
import com.param.adt.admission.model.VisitorPatientMapper;
import com.param.adt.discharge.model.Discharge;
import com.param.adt.master.unit.model.AmenityMaster;
import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.adt.master.unit.model.FloorMapMaster;
import com.param.adt.master.unit.model.FloorMaster;
import com.param.adt.master.unit.model.MealPreferenceMaster;
import com.param.adt.master.unit.model.MortuaryBedMaster;
import com.param.adt.master.unit.model.NursingStationMaster;
import com.param.adt.master.unit.model.NursingStationWardMapper;
import com.param.adt.master.unit.model.RoomMaster;
import com.param.adt.master.unit.model.UnitBedCategoryMapper;
import com.param.adt.master.unit.model.UnitICUTypeMapper;
import com.param.adt.master.unit.model.UnitSpecialityMapper;
import com.param.adt.master.unit.model.UnitSubSpecialityMapper;
import com.param.adt.master.unit.model.WardMaster;
import com.param.adt.master.unit.model.WingMaster;
import com.param.adt.transfer.model.Transfer;
import com.param.adt.transfer.model.TransferRequest;
import com.param.entity.org.model.LicenceTypeMaster;
import com.param.entity.org.model.OrganizationUnitLicenceDetails;
import com.param.global.model.AreaMaster;
import com.param.global.model.CityMaster;
import com.param.global.model.CountryMaster;
import com.param.global.model.DistrictMaster;
import com.param.global.model.RelationMaster;
import com.param.global.model.StateMaster;

@NamedQueries({
		@NamedQuery(name = "GET_ORGANIZATION_MASTER_LIST",
				query = "select organizationId as organizationId,organizationName as organizationName "
						+ " from OrganizationMaster"),
				
		@NamedQuery(name = "GET_ORGANISATION_MASTER_RECORD_FORSYNC_BY_ID", 
		query = " SELECT orgMaster.organizationName AS organisationName, "
				+ " to_char(orgMaster.createdDate,'DD-MM-YYYY HH24:MI:SS.SSS') AS createdDate, "
				+ " orgMaster.status AS status, " 
				+ " orgMaster.organizationId AS localId, " 
				+ " 2 AS applicationId ,"
				+ " 6 AS serviceProviderTypeId ,"
				+ " orgMaster.createdBy AS createdBy ,"
				+ " orgMaster.organizationId AS localOrganisationId ,"
				+ " countryMaster.countryName AS country ,"
				+ " stateMaster.stateName AS state ,"
				+ " cityMaster.cityName AS city ,"
				+ " areaMaster.areaName AS area ,"
				+ " to_char(orgMaster.postCode,'999999') AS pincode "
				+ " FROM OrganizationMaster orgMaster "
				+ " INNER JOIN orgMaster.countryMaster countryMaster " 
				+ " INNER JOIN orgMaster.stateMaster stateMaster " 
				+ " INNER JOIN orgMaster.cityMaster cityMaster " 
				+ " INNER JOIN orgMaster.areaMaster areaMaster " 
				+ " WHERE orgMaster.organizationId=:orgId"),
			
		@NamedQuery(name="GET_ORGANIZATION_BY_NAME",
					query="SELECT 	org.organizationId as organizationId ,org.organizationName as organizationName,"
							+ "		org.organizationDesc as organizationDesc "
							+ "FROM OrganizationMaster org "
							+ "WHERE LOWER(org.organizationName)= :organizationName "),
							
		@NamedQuery(name="GET_ORGANIZATION_BY_ID",
					query="SELECT 	org.organizationId as organizationId , org.organizationName as organizationName ,"
							+ "		org.organizationDesc as organizationDesc , org.organizationCode as organizationCode ,"
							+ "		org.countryId as countryId , org.stateId as stateId , org.cityId as cityId ,"
							+ "		org.areaId as areaId , org.districtId as districtId , "
							+ "		org.postCode as postCode , org.organizationAddress as organizationAddress , "
							+ "		org.organizationContact as organizationContact ,org.status as status,"
							+ "		org.organizationEmailId as organizationEmailId  "
						//	+ "licenceDtls.licenceTypeId as licenceTypeId ,"
						//	+ "		licenceDtls.licenceNumber as licenceNumber , to_char(licenceDtls.expiryDate,'DD-MM-YYYY') as expiryDate "
							+ "FROM OrganizationMaster org "
						//	+ "LEFT JOIN org.listOrganizationUnitLicenceDetails licenceDtls "
							+ "WHERE org.organizationId = :organizationId "),
							
							
		@NamedQuery(name="GET_ORGANIZATION_BY_NAME_AND_NOT_BY_ID",
					query="SELECT 	org.organizationId as organizationId ,org.organizationName as organizationName,"
							+ "		org.organizationDesc as organizationDesc "
							+ "FROM OrganizationMaster org "
							+ "WHERE LOWER(org.organizationName)= :organizationName "
							+ "AND org.organizationId <> :organizationId "),
							
		@NamedQuery(name="GET_ALL_ORGNIZATION_LIST",
				query="SELECT 	org.organizationId as organizationId , org.organizationName as organizationName ,"
						+ "		org.organizationDesc as organizationDesc , org.organizationCode as organizationCode ,"
						+ "		org.countryId as countryId , org.stateId as stateId , org.cityId as cityId ,"
						+ "		org.areaId as areaId , org.districtId as districtId , "
						+ "		org.postCode as postCode , org.organizationAddress as organizationAddress , "
						+ "		org.organizationContact as organizationContact ,org.status as status,"
						+ "		org.organizationEmailId as organizationEmailId  ,org.createdBy as createdBy ,"
						+ "		org.updatedBy as updatedBy , to_char(org.createdDate,'DD-MM-YYYY') as createdDate ,"
						+ "		to_char(org.updatedDate,'DD-MM-YYYY') as updatedDate, country.countryName as countryName ,"
						+ "		state.stateName as stateName , city.cityName as cityName "
						+ "FROM OrganizationMaster org "
						+ "INNER JOIN org.countryMaster country "
						+ "INNER JOIN org.stateMaster state "
						+ "INNER JOIN org.cityMaster city "
						+ "ORDER BY org.organizationId ASC "),
						
		@NamedQuery(name="UPDATE_ORGANIZATION_STATUS",
					query="UPDATE OrganizationMaster org SET org.status= :status "
							+ "WHERE org.organizationId = :organizationId "),
							
		@NamedQuery(name = "GET_ACTIVE_ORGANIZATION_MASTER_LIST",
					query = "select org.organizationId as organizationId,org.organizationName as organizationName "
							+ " from OrganizationMaster org "
							+ "WHERE org.status='A' "),
				
		})

@Entity
@Table(name = "m_organization_master", schema = "public")
@SequenceGenerator(name = "organization_master_seq", sequenceName = "public.organization_master_seq", allocationSize = 1)
public class OrganizationMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_master_seq")
	@Column(name = "organization_id")
	private int organizationId;

	@Column(name = "organization_code")
	private String organizationCode;

	@Column(name = "organization_name")
	private String organizationName;

	@Column(name = "organization_desc")
	private String organizationDesc;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "state_id")
	private Integer stateId;

	@Column(name = "district_id")
	private Integer districtId;

	@Column(name = "city_id")
	private Integer cityId;

	@Column(name = "area_id")
	private Integer areaId;

	@Column(name = "post_code")
	private Integer postCode;

	@Column(name = "organization_address")
	private String organizationAddress;

	@Column(name = "organization_contact")
	private String organizationContact;

	@Column(name = "organization_email_id")
	private String organizationEmailId;

	@Column(name = "organization_logo")
	private String organizationLogo;

	@Column(name = "status")
	private char status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", insertable = false, updatable = false, nullable = false)
	private CountryMaster countryMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false, nullable = false)
	private StateMaster stateMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", insertable = false, updatable = false, nullable = false)
	private CityMaster cityMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false, nullable = false)
	private DistrictMaster districtMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id", insertable = false, updatable = false, nullable = false)
	private AreaMaster areaMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransferRequest> transferRequestslist;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionTypeMaster> admissionTypeMasterslist;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedMaster> bedMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedStatusMaster> bedstatusMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ConsentMaster> consentMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ConsentTypeMaster> consentTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ConsultantTypeMaster> consultantTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DeliveryTypeMaster> deliveryTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DischargeTypeMaster> dischargeTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocumentTypeMaster> documentTypeMasterList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EncounterTypeMaster> encounterTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FloorMaster> floorMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HierarchyMaster> hierarchyMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ICUTypeMaster> icuTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LicenceTypeMaster> licenceTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MaritalStatusMaster> maritalStatusMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NationalityMaster> MastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OccupancyUnitMaster> occupancyUnitMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OccupationMaster> occupationMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientSourceMaster> patientSourceMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaymentModeMaster> paymentModeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RaceMaster> raceMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReferralMaster> referralMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReligionMaster> religionMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RenderingDeptMaster> renderingDeptMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoomMaster> roomMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceCodeTypeMaster> serviceCodeTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ICUTypeMaster> listIcuTypeMasters;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ShortLeaveReasonMaster> shortLeaveReasonMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitMaster> listUnitMasters;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HierarchyMaster> listHierarchyMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> listWardMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrganizationLicenceMaster> listOrganizationLicenceMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrganizationUnitMapper> listOrganizationUnitMapper;

	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReasonMaster> reasonMastersList;

	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NursingStationMaster> nursingStationMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NursingStationWardMapper> nursingStationWardMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MealPreferenceMaster> mealPreferenceMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MortuaryBedMaster> mortuaryBedMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sponsor> sponsersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Admission> admissionsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionDetails> admissionDetailsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SponsorDetails> sponserDetailsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitSpecialityMapper> unitSpecialityMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitSubSpecialityMapper> unitSubSpecialityMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitICUTypeMapper> unitICUMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AmenityMaster> amenityMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FloorMapMaster> floorMapMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitICUTypeMapper> unitICUTypeMappersList;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WingMaster> wingMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitBedCategoryMapper> unitBedCategoryMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BillingBedCategoryMaster> billingBedCategoryMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> wardMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionPatientDocuments> admissionPatientDocumentsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedLogStatus> bedLogStatusList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnreservedPatient> unreservedPatientsList;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorPassTypeMaster> visitorPassTypeMastersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorAgainstPatient> visitorAgainstPatientsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorPatientMapper> visitorPatientMappersList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Discharge> dischargesList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrganizationUnitLicenceDetails> listOrganizationUnitLicenceDetails ;

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationDesc() {
		return organizationDesc;
	}

	public void setOrganizationDesc(String organizationDesc) {
		this.organizationDesc = organizationDesc;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getPostCode() {
		return postCode;
	}

	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}

	public String getOrganizationAddress() {
		return organizationAddress;
	}

	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}

	public String getOrganizationContact() {
		return organizationContact;
	}

	public void setOrganizationContact(String organizationContact) {
		this.organizationContact = organizationContact;
	}

	public String getOrganizationEmailId() {
		return organizationEmailId;
	}

	public void setOrganizationEmailId(String organizationEmailId) {
		this.organizationEmailId = organizationEmailId;
	}

	public String getOrganizationLogo() {
		return organizationLogo;
	}

	public void setOrganizationLogo(String organizationLogo) {
		this.organizationLogo = organizationLogo;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<OrganizationUnitLicenceDetails> getListOrganizationUnitLicenceDetails() {
		return listOrganizationUnitLicenceDetails;
	}

	public void setListOrganizationUnitLicenceDetails(
			List<OrganizationUnitLicenceDetails> listOrganizationUnitLicenceDetails) {
		this.listOrganizationUnitLicenceDetails = listOrganizationUnitLicenceDetails;
	}



}
