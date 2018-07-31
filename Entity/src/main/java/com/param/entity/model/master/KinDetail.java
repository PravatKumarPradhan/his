package com.param.entity.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "KinDetail")
@Table(name = "m_kin_details", schema = "public")
public class KinDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kin_id", unique = true, nullable = false)
	private Integer kinId;

	@Column(length = 2147483647)
	private String address;

	@Column(name = "admission_id")
	private Integer admissionId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "d_patient_id")
	private Integer dPatientId;

	@Column(name = "expiry_date", length = 2147483647)
	private String expiryDate;

	@Column(name = "identification_id")
	private Integer identificationId;

	@Column(name = "identification_no", length = 2147483647)
	private String identificationNo;

	@Column(name = "is_guarantor", length = 1)
	private String isGuarantor;

	@Column(name = "kin_name", length = 2147483647)
	private String kinName;

	@Column(name = "mobile_no", length = 2147483647)
	private String mobileNo;

	@Column(name = "patient_id")
	private Integer patientId;

	@Column(name = "phone_no", length = 2147483647)
	private String phoneNo;

	@Column(name = "post_code", length = 2147483647)
	private String postCode;

	@Column(length = 1)
	private String status;

	@Column(name = "t_patient_id")
	private Integer tPatientId;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	private District district;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prefix_id")
	private Prefix prefix;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_id")
	private Relation relation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id")
	private VisitType visitType;

	public KinDetail() {
	}

	public Integer getKinId() {
		return this.kinId;
	}

	public void setKinId(Integer kinId) {
		this.kinId = kinId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAdmissionId() {
		return this.admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDPatientId() {
		return this.dPatientId;
	}

	public void setDPatientId(Integer dPatientId) {
		this.dPatientId = dPatientId;
	}

	public String getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getIdentificationId() {
		return this.identificationId;
	}

	public void setIdentificationId(Integer identificationId) {
		this.identificationId = identificationId;
	}

	public String getIdentificationNo() {
		return this.identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getIsGuarantor() {
		return this.isGuarantor;
	}

	public void setIsGuarantor(String isGuarantor) {
		this.isGuarantor = isGuarantor;
	}

	public String getKinName() {
		return this.kinName;
	}

	public void setKinName(String kinName) {
		this.kinName = kinName;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTPatientId() {
		return this.tPatientId;
	}

	public void setTPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Prefix getPrefix() {
		return this.prefix;
	}

	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}

	public Relation getRelation() {
		return this.relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public VisitType getVisitType() {
		return this.visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

}