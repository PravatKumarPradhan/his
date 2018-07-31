package com.param.entity.model.adt;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name="WardMasters")
@Table(name="m_ward_master", schema="adt")
public class WardMasters implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ward_id")
	private Integer wardId;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="floor_id")
	private Integer floorId;

	@Column(name="gender_id")
	private Integer genderId;

	@Column(name="icu_type_id")
	private Integer icuTypeId;

	@Column(name="is_day_care")
	private String isDayCare;

	@Column(name="is_er")
	private String isEr;

	@Column(name="is_icu")
	private String isIcu;

	@Column(name="no_of_rooms")
	private Integer noOfRooms;

	@Column(name="organization_id")
	private Integer organizationId;

	private String status;

	@Column(name="store_id")
	private Integer storeId;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@Column(name="ward_code")
	private String wardCode;

	@Column(name="ward_name")
	private String wardName;

	@OneToMany(mappedBy="wardMaster")
	private List<BedMaster> bedMasters;

	@OneToMany(mappedBy="wardMaster")
	private List<AdmissionDetail> admissionDetails;

	public WardMasters() {
		super();
	}

	public Integer getWardId() {
		return this.wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
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

	public Integer getFloorId() {
		return this.floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Integer getGenderId() {
		return this.genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Integer getIcuTypeId() {
		return this.icuTypeId;
	}

	public void setIcuTypeId(Integer icuTypeId) {
		this.icuTypeId = icuTypeId;
	}

	public String getIsDayCare() {
		return this.isDayCare;
	}

	public void setIsDayCare(String isDayCare) {
		this.isDayCare = isDayCare;
	}

	public String getIsEr() {
		return this.isEr;
	}

	public void setIsEr(String isEr) {
		this.isEr = isEr;
	}

	public String getIsIcu() {
		return this.isIcu;
	}

	public void setIsIcu(String isIcu) {
		this.isIcu = isIcu;
	}

	public Integer getNoOfRooms() {
		return this.noOfRooms;
	}

	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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

	public String getWardCode() {
		return this.wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getWardName() {
		return this.wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public List<BedMaster> getBedMasters() {
		return this.bedMasters;
	}

	public void setBedMasters(List<BedMaster> bedMasters) {
		this.bedMasters = bedMasters;
	}

	public BedMaster addBedMaster(BedMaster bedMaster) {
		getBedMasters().add(bedMaster);
		bedMaster.setWardMaster(this);

		return bedMaster;
	}

	public BedMaster removeBedMaster(BedMaster bedMaster) {
		getBedMasters().remove(bedMaster);
		bedMaster.setWardMaster(null);

		return bedMaster;
	}

	public List<AdmissionDetail> getAdmissionDetails() {
		return this.admissionDetails;
	}

	public void setAdmissionDetails(List<AdmissionDetail> admissionDetails) {
		this.admissionDetails = admissionDetails;
	}

	public AdmissionDetail addAdmissionDetail(AdmissionDetail admissionDetail) {
		getAdmissionDetails().add(admissionDetail);
		admissionDetail.setWardMaster(this);

		return admissionDetail;
	}

	public AdmissionDetail removeAdmissionDetail(AdmissionDetail admissionDetail) {
		getAdmissionDetails().remove(admissionDetail);
		admissionDetail.setWardMaster(null);

		return admissionDetail;
	}

	public WardMasters(Integer wardId) {
		super();
		this.wardId = wardId;
	}

}