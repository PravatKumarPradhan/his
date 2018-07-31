package com.param.entity.model.pharmacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Item;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name="PatientIndentDetail")
@Table(name="t_patient_indent_detail",schema="pharmacy")
public class PatientIndentDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_indent_id")
	private PatientIndent patientIndent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uom_type_id")
	private UomType uomType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uom_unit_id")
	private UomUnit uomUnit;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;
	
	@Column(name="indent_quantity")
	private Integer indentQuantity;
	
	@Column(name="pending_quantity")
	private Integer pendingQuantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PatientIndent getPatientIndent() {
		return patientIndent;
	}

	public void setPatientIndent(PatientIndent patientIndent) {
		this.patientIndent = patientIndent;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	public Integer getIndentQuantity() {
		return indentQuantity;
	}

	public void setIndentQuantity(Integer indentQuantity) {
		this.indentQuantity = indentQuantity;
	}
	
	public Integer getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(Integer pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PatientIndentDetail()
	{
		super();
	}

	public PatientIndentDetail(Integer id, PatientIndent patientIndent, Item item, UomType uomType, UomUnit uomUnit,
			Integer indentQuantity, Status status) {
		super();
		this.id = id;
		this.patientIndent = patientIndent;
		this.item = item;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.indentQuantity = indentQuantity;
		this.status = status;
	}

	public PatientIndentDetail(Item item, UomType uomType, UomUnit uomUnit, Integer indentQuantity, Status status,
			Integer pendingQuantity) {
		super();
		this.item = item;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.indentQuantity = indentQuantity;
		this.status = status;
		this.pendingQuantity = pendingQuantity;
	}

	public PatientIndentDetail(Integer indentQuantity, Item item, Integer pendingQuantity, Status status,
			UomType uomType, UomUnit uomUnit) {
		super();
		this.indentQuantity = indentQuantity;
		this.item = item;
		this.pendingQuantity = pendingQuantity;
		this.status = status;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
	}

	public PatientIndentDetail(Integer id) {
		super();
		this.id = id;
	}

	public void updatePatientIndentDetail(Item item, Integer indentQuantity) {
		this.item = item;
		this.indentQuantity = indentQuantity;
	}
	
}