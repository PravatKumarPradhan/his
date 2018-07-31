

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
import com.param.adt.master.unit.model.UnitBedCategoryMapper;
import com.param.adt.master.unit.model.UnitICUTypeMapper;
import com.param.adt.master.unit.model.UnitSpecialityMapper;
import com.param.adt.master.unit.model.UnitSubSpecialityMapper;
import com.param.adt.master.unit.model.WardMaster;
import com.param.adt.master.unit.model.WingMaster;
import com.param.adt.transfer.model.Transfer;
import com.param.adt.transfer.model.TransferRequest;
import com.param.global.model.AreaMaster;
import com.param.global.model.CityMaster;
import com.param.global.model.CountryMaster;
import com.param.global.model.DistrictMaster;
import com.param.global.model.StateMaster;


@NamedQueries({
	
	@NamedQuery(name = "GET_UNIT_BY_ORGANIZATION_ID",
			   query = " SELECT uniT.unitId as unitId, uniT.unitName as unitName"
			   	     + " FROM 		UnitMaster uniT"
			   	     + " INNER 		JOIN uniT.listOrganizationUnitMapper as mapper"
			   	     + " INNER 		JOIN mapper.organizationMaster org"
			   	     + " WHERE 		mapper.organizationUnitMapperId.organizationId=:Organization_ID AND org.status ='A' and uniT.status = 'A' "
			   	     + " ORDER BY	uniT.unitId"),
			   	     
	@NamedQuery(name = "GET_UNITMASTERRECORD_FORSYNC_BY_ID",
				query=" SELECT unit.unitName AS unitName, "
					+ " to_char(unit.createdDate,'DD-MM-YYYY HH24:MI:SS.SSS') AS createdDate, "
					+ " unit.status AS status, "
					+ " unit.unitId AS localId, "
					+ " 2 AS applicationId, "
					+ " unit.createdBy AS createdBy, "
					+ " countryMaster.countryName AS country, "
					+ " unit.unitId AS localUnitId, "
					+ " stateMaster.stateName AS state, "
					+ " cityMaster.cityName AS city, "
//					+ " to_char(unit.postCode,'999') AS pincode "
					+" unit.postCode as pincode "
			   	    + " FROM UnitMaster unit "
			   	    + " INNER JOIN unit.countryMaster countryMaster "
			   	    + " INNER JOIN unit.stateMaster stateMaster "
			   	    + " INNER JOIN unit.cityMaster cityMaster "
			   	    + " INNER JOIN unit.areaMaster areaMaster "
			   	    + " WHERE unit.unitId=:unitId"),
	
		@NamedQuery(name="GET_UNIT_BY_NAME",
					query="SELECT unit.unitId as unitId , unit.unitName as unitName "
							+ "FROM UnitMaster unit "
							+ " WHERE LOWER(unit.unitName) = :unitName "),
		
		@NamedQuery(name="GET_UNIT_BY_NAME_AND_NOT_BY_ID",
		query="SELECT unit.unitId as unitId , unit.unitName as unitName "
				+ "FROM UnitMaster unit "
				+ " WHERE LOWER(unit.unitName) = :unitName AND unit.unitId <> :unitId "),
				
		@NamedQuery(name="UPDATE_UNIT_STATUS_BY_ID",
					query="UPDATE UnitMaster SET status = :status "
							+ "WHERE unitId = :unitId "),
							
		@NamedQuery(name = "GET_ALL_UNIT_LIST",
					query= "SELECT 	unit.unitId as unitId , unit.unitCode as unitCode ,"
							+ "		unit.unitName as unitName , unit.organizationId as organizationId , "
							+ "		unit.unitCountryId as unitCountryId , unit.unitStateId as unitStateId , "
							+ "		unit.unitCityId as unitCityId , unit.postCode as postCode , "
							+ "		unit.unitEmailId as unitEmailId , unit.status as status ,"
							+ "		unit.unitAddress as unitAddress ,  unit.unitDescription as unitDescription , "
							+ "		unit.unitAreaId as unitAreaId , unit.createdBy as createdBy , to_char(unit.createdDate , 'DDD-MM-YYYY') as createdDate,"
							+ "		country.countryName as countryName  , state.stateName as stateName , city.cityName as cityName,"
							+ "		org.organizationName as organizationName,unit.unitContact as unitContact "
							+ "FROM UnitMaster unit "
							+ "INNER JOIN unit.countryMaster country "
							+ "INNER JOIN unit.stateMaster state "
							+ "INNER JOIN unit.cityMaster city "
							+ "INNER JOIN unit.organizationMaster org "
							+ "ORDER BY unit.unitId "),
							
							
		@NamedQuery(name = "GET_UNIT_MASTER_BY_ID",
					query= "SELECT 	unit.unitId as unitId , unit.unitCode as unitCode ,"
							+ "		unit.unitName as unitName , unit.organizationId as organizationId , "
							+ "		unit.unitCountryId as unitCountryId , unit.unitStateId as unitStateId , "
							+ "		unit.unitCityId as unitCityId , unit.postCode as postCode , "
							+ "		unit.unitEmailId as unitEmailId , unit.status as status ,"
							+ "		unit.unitAddress as unitAddress ,  unit.unitDescription as unitDescription , "
							+ "		unit.unitAreaId as unitAreaId , unit.createdBy as createdBy , to_char(unit.createdDate , 'DDD-MM-YYYY') as createdDate,"
							+ "		country.countryName as countryName  , state.stateName as stateName , city.cityName as cityName,unit.unitContact as unitContact  "
							+ "FROM UnitMaster unit "
							+ "INNER JOIN unit.countryMaster country "
							+ "INNER JOIN unit.stateMaster state "
							+ "INNER JOIN unit.cityMaster city "
							+ "WHERE unit.unitId = :unitId ")
	})

@Entity
@Table(name="m_unit_master",schema="public")
@SequenceGenerator(name="unit_master_seq", sequenceName="public.unit_master_seq", allocationSize=1)
public class UnitMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unit_master_seq")
	@Column(name="unit_id")
	private int unitId;

	@Column(name="unit_code")
	private String unitCode;

	@Column(name="unit_name")
	private String unitName;

	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="unit_country_id")
	private Integer unitCountryId;

	@Column(name="unit_state_id")
	private Integer unitStateId;

	@Column(name="unit_district_id")
	private Integer unitDistrictId;

	@Column(name="post_code")
	private String postCode;

	@Column(name="unit_email_id")
	private String unitEmailId;

	@Column(name="unit_logo")
	private String unitLogo;

	@Column(name="status")
	private char status; 

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="unit_city_id")
	private Integer unitCityId;
	
	@Column(name="unit_address")
	private String unitAddress;
	
	@Column(name="unit_description")
	private String unitDescription;
	
	@Column(name="unit_area_id")
	private Integer unitAreaId;
	
	@Column(name="unit_contact")
	private String unitContact;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_country_id" , insertable = false , updatable = false , nullable = false)
	private CountryMaster countryMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_state_id" , insertable = false , updatable = false , nullable = false)
	private StateMaster stateMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_city_id" , insertable = false , updatable = false , nullable = false)
	private CityMaster cityMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_district_id" , insertable = false , updatable = false , nullable = false)
	private DistrictMaster districtMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_area_id" , insertable = false , updatable = false , nullable = false)
	private AreaMaster areaMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id" , insertable = false , updatable = false , nullable = false)
	private OrganizationMaster organizationMaster;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transfer> transferslist;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransferRequest> transferRequestslist;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<UnitSpecialityMapper> listUnitSpecialityMappers;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<UnitSubSpecialityMapper> unitSubSpecialityMappersList;
	
/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<DoctorSpecialityMapper> DocotrSpecialityMapperslist;*/
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<UnitLicenceMaster> listUnitLicenceMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> listWardMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FloorMaster> listFloorMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedMaster> listBedMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrganizationUnitMapper> listOrganizationUnitMapper;

/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitMaster> visitMastersList ;*/
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NursingStationMaster> nursingStationMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<NursingStationWardMapper> nursingStationWardMappersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MealPreferenceMaster> mealPreferenceMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MortuaryBedMaster> mortuaryBedMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sponsor> sponsersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Admission> admissionsList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionDetails> admissionDetailsList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SponsorDetails> sponserDetailsList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitICUTypeMapper> unitICUMappersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AmenityMaster> amenityMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FloorMapMaster> floorMapMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitICUTypeMapper> unitICUTypeMappersList;
	
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitHolidayCalenderMapper> unitHolidayCalenderMappersList;*/

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WingMaster> wingMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnitBedCategoryMapper> unitBedCategoryMappersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BillingBedCategoryMaster> billingBedCategoryMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WardMaster> wardMastersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AdmissionPatientDocuments> admissionPatientDocumentsList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BedLogStatus> bedLogStatusList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnreservedPatient> unreservedPatientsList;
	
	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UnknownPatientRegistration> unknownPatientRegistrationsList;*/
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorPassTypeMaster> visitorPassTypeMastersList;
	
	public List<NursingStationMaster> getNursingStationMastersList() {
		return nursingStationMastersList;
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorAgainstPatient> visitorAgainstPatientsList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitorPatientMapper> visitorPatientMappersList;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "unitMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Discharge> dischargesList;
	
	public void setNursingStationMastersList(List<NursingStationMaster> nursingStationMastersList) {
		this.nursingStationMastersList = nursingStationMastersList;
	}

	public List<NursingStationWardMapper> getNursingStationWardMappersList() {
		return nursingStationWardMappersList;
	}

	public void setNursingStationWardMappersList(List<NursingStationWardMapper> nursingStationWardMappersList) {
		this.nursingStationWardMappersList = nursingStationWardMappersList;
	}

	public List<MealPreferenceMaster> getMealPreferenceMastersList() {
		return mealPreferenceMastersList;
	}

	public void setMealPreferenceMastersList(List<MealPreferenceMaster> mealPreferenceMastersList) {
		this.mealPreferenceMastersList = mealPreferenceMastersList;
	}

	public List<MortuaryBedMaster> getMortuaryBedMastersList() {
		return mortuaryBedMastersList;
	}

	public void setMortuaryBedMastersList(List<MortuaryBedMaster> mortuaryBedMastersList) {
		this.mortuaryBedMastersList = mortuaryBedMastersList;
	}

	public List<Sponsor> getSponsersList() {
		return sponsersList;
	}

	public void setSponsersList(List<Sponsor> sponsersList) {
		this.sponsersList = sponsersList;
	}

	/*public List<DoctorSpecialityMapper> getDocotrSpecialityMapperslist() {
		return DocotrSpecialityMapperslist;
	}

	public void setDocotrSpecialityMapperslist(List<DoctorSpecialityMapper> docotrSpecialityMapperslist) {
		DocotrSpecialityMapperslist = docotrSpecialityMapperslist;
	}*/


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

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getUnitCountryId() {
		return unitCountryId;
	}

	public void setUnitCountryId(Integer unitCountryId) {
		this.unitCountryId = unitCountryId;
	}

	public Integer getUnitStateId() {
		return unitStateId;
	}

	public void setUnitStateId(Integer unitStateId) {
		this.unitStateId = unitStateId;
	}

	public Integer getUnitDistrictId() {
		return unitDistrictId;
	}

	public void setUnitDistrictId(Integer unitDistrictId) {
		this.unitDistrictId = unitDistrictId;
	}

	
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getUnitEmailId() {
		return unitEmailId;
	}

	public void setUnitEmailId(String unitEmailId) {
		this.unitEmailId = unitEmailId;
	}

	public String getUnitLogo() {
		return unitLogo;
	}

	public void setUnitLogo(String unitLogo) {
		this.unitLogo = unitLogo;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public CityMaster getCityMaster() {
		return cityMaster;
	}

	public void setCityMaster(CityMaster cityMaster) {
		this.cityMaster = cityMaster;
	}

	public DistrictMaster getDistrictMaster() {
		return districtMaster;
	}

	public void setDistrictMaster(DistrictMaster districtMaster) {
		this.districtMaster = districtMaster;
	}

	public AreaMaster getAreaMaster() {
		return areaMaster;
	}

	public void setAreaMaster(AreaMaster areaMaster) {
		this.areaMaster = areaMaster;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public List<UnitSpecialityMapper> getListUnitSpecialityMappers() {
		return listUnitSpecialityMappers;
	}

	public void setListUnitSpecialityMappers(List<UnitSpecialityMapper> listUnitSpecialityMappers) {
		this.listUnitSpecialityMappers = listUnitSpecialityMappers;
	}

	public List<UnitLicenceMaster> getListUnitLicenceMaster() {
		return listUnitLicenceMaster;
	}

	public void setListUnitLicenceMaster(List<UnitLicenceMaster> listUnitLicenceMaster) {
		this.listUnitLicenceMaster = listUnitLicenceMaster;
	}

	public List<WardMaster> getListWardMaster() {
		return listWardMaster;
	}

	public void setListWardMaster(List<WardMaster> listWardMaster) {
		this.listWardMaster = listWardMaster;
	}

	public List<FloorMaster> getListFloorMaster() {
		return listFloorMaster;
	}

	public void setListFloorMaster(List<FloorMaster> listFloorMaster) {
		this.listFloorMaster = listFloorMaster;
	}

	public List<BedMaster> getListBedMaster() {
		return listBedMaster;
	}

	public void setListBedMaster(List<BedMaster> listBedMaster) {
		this.listBedMaster = listBedMaster;
	}

	public List<OrganizationUnitMapper> getListOrganizationUnitMapper() {
		return listOrganizationUnitMapper;
	}

	public void setListOrganizationUnitMapper(List<OrganizationUnitMapper> listOrganizationUnitMapper) {
		this.listOrganizationUnitMapper = listOrganizationUnitMapper;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitCityId() {
		return unitCityId;
	}

	public void setUnitCityId(Integer unitCityId) {
		this.unitCityId = unitCityId;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getUnitDescription() {
		return unitDescription;
	}

	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}

	public Integer getUnitAreaId() {
		return unitAreaId;
	}

	public void setUnitAreaId(Integer unitAreaId) {
		this.unitAreaId = unitAreaId;
	}

	public String getUnitContact() {
		return unitContact;
	}

	public void setUnitContact(String unitContact) {
		this.unitContact = unitContact;
	}
	
	

	
}
