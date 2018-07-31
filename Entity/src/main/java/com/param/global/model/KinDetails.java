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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_KIN_DETAILS_BY_PATIENT_ID", query="SELECT kin.kin_id as \"kinDetailsId\", "
			+ "kin.visit_type_id as \"visitTypeId\", "
			+ "kin.prefix_id as \"prefixId\", "
			+ "prefix.prefix_code as \"prefixCode\", "
			+ "kin.kin_name as \"kinName\", "
		//	+ "kin.relation_id as \"relationId\", "
			//+ "relation.relation_name as \"relationDesc\", "
			+ "kin.mobile_no as \"mobileNo\", "
			+ "kin.phone_no as \"phoneNo\", "
			+ "kin.identification_id as \"identificationId\", "
		//  + "id.identification_name as \"identificationName\", "
			+ "kin.identification_no as \"identificationNo\", "
			+ "kin.address as \"address\" "
			+ "FROM public.m_kin_details kin "
			+ "INNER JOIN  public.m_prefix_master prefix ON  prefix.prefix_id = kin.prefix_id "
		//	+ "INNER JOIN  public.m_relation_master relation ON  relation.relation_id = kin.relation_id "
		//	+ "INNER JOIN  adt.m_identification_master id ON id.identification_id = kin.identification_id "
			+ "WHERE kin.organization_id=:organizationId "
			+ "AND kin.unit_id=:unitId "
			+ "AND kin.status='A' "
			+ "AND kin.patient_id=:patientId "
			),
	
	@NamedNativeQuery(name="GET_KIN_DETAILS_BY_ID", query="SELECT kin.kin_id as \"kinDetailsId\", "
			+ "kin.visit_type_id as \"visitTypeId\", "
			+ "kin.prefix_id as \"prefixId\", "
		//	+ "prefix.prefix_code as \"prefixCode\", "
			+ "kin.kin_name as \"kinName\", "
			+ "kin.relation_id as \"relationId\", "
		//	+ "relation.relation_name as \"relationDesc\", "
			+ "kin.mobile_no as \"mobileNo\", "
			+ "kin.phone_no as \"phoneNo\", "
			+ "kin.identification_id as \"identificationId\", "
		//	+ "id.identification_name as \"identificationName\", "
			+ "kin.identification_no as \"identificationNo\", "
			+ "kin.address as \"address\", kin.expiry_date as \"expiry\", "
			+ "kin.patient_id as \"patientId\", kin.is_guarantor as \"isGuarantor\", "
			+ "kin.country_id as \"countryId\", kin.state_id as \"stateId\", kin.district_id as \"districtId\", kin.city_id as \"cityId\", "
			+ "kin.area_id as \"areaId\", kin.post_code as \"postCode\", kin.t_patient_id as \"tPatientId\", "
			+ "kin.d_patient_id as \"dPatientId\", kin.status as \"status\", "
			+ "to_char(kin.created_date, 'DD-MM-YYYY HH24:MI:SS') as \"createdDate\", "
			+ "to_char(kin.updated_date, 'DD-MM-YYYY HH24:MI:SS') as \"updatedDate\", "
			+ "kin.created_by as \"createdBy\", kin.updated_by as \"updatedBy\", "
			+ "kin.organization_id as \"organizationId\", kin.unit_id as \"unitId\", kin.admission_id as \"admissionId\" "
			+ "FROM public.m_kin_details kin "
		//	+ "INNER JOIN  public.m_prefix_master prefix ON  prefix.prefix_id = kin.prefix_id "
		//	+ "INNER JOIN  public.m_relation_master relation ON  relation.relation_id = kin.relation_id "
		//	+ "INNER JOIN  adt.m_identification_master id ON id.identification_id = kin.identification_id "
			+ "WHERE kin.kin_id=:kinDetailsId "),
	
	@NamedNativeQuery(name="CHANGE_KIN_DETAILS_STATUS", query="UPDATE public.m_kin_details SET status=:status WHERE kin_id =:kinDetailsId")
})



@Entity
@Table(name = "m_kin_details", schema = "public")
@SequenceGenerator(name = "kin_details_seq", sequenceName = "public.kin_details_seq", allocationSize = 1)
public class KinDetails 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="kin_details_seq")
	@Column(name="kin_id")
	private int kinDetailsId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="visit_type_id")
	private Integer visitTypeId;
	
	@Column(name="prefix_id")
	private Integer prefixId;
	
	@Column(name="kin_name")
	private String kinName;
	
	@Column(name="relation_id")
	private Integer relationId;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	@Column(name="phone_no")
	private String phoneNo;
	
	@Column(name="identification_id")
	private Integer identificationId;
	
	@Column(name="identification_no")
	private String identificationNo;
	
	@Column(name="expiry_date")
	private Date expiry;
	
	@Column(name="is_guarantor")
	private char isGuarantor;
	
	@Column(name="country_id")
	private Integer countryId;
	
	@Column(name="state_id")
	private Integer stateId;
	
	@Column(name="district_id")
	private Integer districtId;
	
	@Column(name="city_id")
	private Integer cityId;
	
	@Column(name="area_id")
	private Integer areaId;
	
	@Column(name="address")
	private String address;
	
	@Column(name="post_code")
	private String postCode;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="t_patient_id")
	private Integer tPatientId;
	
	@Column(name="d_patient_id")
	private Integer dPatientId;
	
	@Column(name="status")
	private char status;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
	private OrganizationMaster organizationMaster;*/
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
	private UnitMaster unitMaster;*/
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_id", insertable = false, nullable = false, updatable = false)
	private Admission admission;*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
	private VisitTypeMaster visitTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prefix_id", insertable = false, nullable = false, updatable = false)
	private PrefixMaster prefixMaster;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_id", insertable = false, nullable = false, updatable = false)
	private RelationMaster relationMaster;*/
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identification_id", insertable = false, nullable = false, updatable = false)
	private IdentificationMaster identificationMaster;*/

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", insertable = false, updatable = false)
	private CountryMaster countryMaster;*/
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	private StateMaster stateMaster;*/
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private DistrictMaster districtMaster ;*/
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", insertable = false, updatable = false)
	private CityMaster cityMaster;*/
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id", insertable = false, updatable = false)
	private AreaMaster areaMaster;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_patient_id", insertable = false, updatable = false)
	private UnknownPatientRegistration unknownPatientRegistration;

	
	public Integer getdPatientId() {
		return dPatientId;
	}

	public void setdPatientId(Integer dPatientId) {
		this.dPatientId = dPatientId;
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

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public char getIsGuarantor() {
		return isGuarantor;
	}

	public void setIsGuarantor(char isGuarantor) {
		this.isGuarantor =  (isGuarantor == '\u0000') ? 'N' : isGuarantor;;
	}

	public int getKinDetailsId() {
		return kinDetailsId;
	}

	public void setKinDetailsId(int kinDetailsId) {
		this.kinDetailsId = kinDetailsId;
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

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(Integer prefixId) {
		this.prefixId = prefixId;
	}

	public String getKinName() {
		return kinName;
	}

	public void setKinName(String kinName) {
		this.kinName = kinName;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(Integer identificationId) {
		this.identificationId = identificationId;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
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

	public PrefixMaster getPrefixMaster() {
		return prefixMaster;
	}

	public void setPrefixMaster(PrefixMaster prefixMaster) {
		this.prefixMaster = prefixMaster;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer gettPatientId() {
		return tPatientId;
	}

	public void settPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}
	
	
}
