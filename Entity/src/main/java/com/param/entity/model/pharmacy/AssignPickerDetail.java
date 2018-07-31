package com.param.entity.model.pharmacy;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.inventory.Batch;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

import java.sql.Timestamp;

@Entity(name="AssignPickerDetail")
@Table(name="t_assign_picker_detail",schema="pharmacy")
public class AssignPickerDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="batch_id")
	private Batch batch;

	@Column(name="issue_quantity")
	private Integer issueQuantity;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prescription_detail_id")
	private PrescriptionDetail prescriptionDetail;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uom_type_id")
	private UomType uomType;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uom_unit_id")
	private UomUnit uomUnit;

	//bi-directional many-to-one association to AssignPicker
	@ManyToOne
	@JoinColumn(name="assign_picker_id")
	private AssignPicker assignPicker;

	public AssignPickerDetail() {
	}

	public AssignPickerDetail(PrescriptionDetail prescriptionDetail, Batch batch, UomType uomType, UomUnit uomUnit, Integer issueQuantity) {
		super();
		this.prescriptionDetail = prescriptionDetail;
		this.batch = batch;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.issueQuantity = issueQuantity;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIssueQuantity() {
		return this.issueQuantity;
	}

	public void setIssueQuantity(Integer issueQuantity) {
		this.issueQuantity = issueQuantity;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public PrescriptionDetail getPrescriptionDetail() {
		return prescriptionDetail;
	}

	public void setPrescriptionDetail(PrescriptionDetail prescriptionDetail) {
		this.prescriptionDetail = prescriptionDetail;
	}

	public UomType getUomType() {
		return uomType;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}

	public AssignPicker getAssignPicker() {
		return assignPicker;
	}

	public void setAssignPicker(AssignPicker assignPicker) {
		this.assignPicker = assignPicker;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public AssignPicker getTAssignPicker() {
		return this.assignPicker;
	}

	public void setTAssignPicker(AssignPicker assignPicker) {
		this.assignPicker = assignPicker;
	}

}