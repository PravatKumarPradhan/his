package com.param.entity.model.adt;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name="BedMasters")
@Table(name="m_bed_master", schema="adt")
public class BedMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bed_id")
	private Integer bedId;

	@Column(name="bed_category_id")
	private Integer bedCategoryId;

	@Column(name="bed_code")
	private String bedCode;

	@Column(name="bed_decs")
	private String bedDecs;

	@Column(name="bed_number")
	private String bedNumber;

	@Column(name="bed_status_id")
	private Integer bedStatusId;

	@Column(name="bed_type_id")
	private Integer bedTypeId;

	@Column(name="billing_bed_category_id")
	private Integer billingBedCategoryId;

	@Column(name="created_by")
	private Integer createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="floor_id")
	private FloorMasters floor;

	@Column(name="is_virtual")
	private String isVirtual;

	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="room_id")
	private Integer roomId;

	private String status;

	@Column(name="unit_id")
	private Integer unitId;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ward_id")
	private WardMasters wardMaster;

	@OneToMany(mappedBy="bedMaster")
	private List<AdmissionDetail> admissionDetails;

	public BedMaster() {
	}

	public BedMaster(Integer bedId) {
		super();
		this.bedId = bedId;
	}

	public Integer getBedId() {
		return this.bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public Integer getBedCategoryId() {
		return this.bedCategoryId;
	}

	public void setBedCategoryId(Integer bedCategoryId) {
		this.bedCategoryId = bedCategoryId;
	}

	public String getBedCode() {
		return this.bedCode;
	}

	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}

	public String getBedDecs() {
		return this.bedDecs;
	}

	public void setBedDecs(String bedDecs) {
		this.bedDecs = bedDecs;
	}

	public String getBedNumber() {
		return this.bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public Integer getBedStatusId() {
		return this.bedStatusId;
	}

	public void setBedStatusId(Integer bedStatusId) {
		this.bedStatusId = bedStatusId;
	}

	public Integer getBedTypeId() {
		return this.bedTypeId;
	}

	public void setBedTypeId(Integer bedTypeId) {
		this.bedTypeId = bedTypeId;
	}

	public Integer getBillingBedCategoryId() {
		return this.billingBedCategoryId;
	}

	public void setBillingBedCategoryId(Integer billingBedCategoryId) {
		this.billingBedCategoryId = billingBedCategoryId;
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

	public FloorMasters getFloor() {
		return floor;
	}

	public void setFloorId(FloorMasters floor) {
		this.floor = floor;
	}

	public String getIsVirtual() {
		return this.isVirtual;
	}

	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public WardMasters getWardMaster() {
		return this.wardMaster;
	}

	public void setWardMaster(WardMasters wardMaster) {
		this.wardMaster = wardMaster;
	}

	public List<AdmissionDetail> getAdmissionDetails() {
		return this.admissionDetails;
	}

	public void setAdmissionDetails(List<AdmissionDetail> admissionDetails) {
		this.admissionDetails = admissionDetails;
	}

	public AdmissionDetail addAdmissionDetail(AdmissionDetail admissionDetail) {
		getAdmissionDetails().add(admissionDetail);
		admissionDetail.setBedMaster(this);

		return admissionDetail;
	}

	public AdmissionDetail removeAdmissionDetail(AdmissionDetail admissionDetail) {
		getAdmissionDetails().remove(admissionDetail);
		admissionDetail.setBedMaster(null);

		return admissionDetail;
	}

}