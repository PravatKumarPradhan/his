package com.param.entity.model.procurement;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Status;


@Entity(name="PhysicalInspection")
@Table(name="t_physical_inspection",schema= "procurement")
public class PhysicalInspection extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "grn_id")
	private GoodReceiptNote goodReceiptNote;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	@Column(name = "remark")
	private String remark;

	//bi-directional many-to-one association to TPhysicalInspectionDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "PhysicalInspection", cascade = CascadeType.ALL)
	private List<PhysicalInspectionDetail> PhysicalInspectionDetails;

	public PhysicalInspection() {
	}

	public PhysicalInspection(GoodReceiptNote goodReceiptNote) {
		super();
		this.goodReceiptNote = goodReceiptNote;
	}


	public PhysicalInspection(GoodReceiptNote goodReceiptNote, Status status, String remark) {
		super();
		this.goodReceiptNote = goodReceiptNote;
		this.status = status;
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public GoodReceiptNote getGoodReceiptNote() {
		return goodReceiptNote;
	}

	public void setGoodReceiptNote(GoodReceiptNote goodReceiptNote) {
		this.goodReceiptNote = goodReceiptNote;
	}

	public List<PhysicalInspectionDetail> getPhysicalInspectionDetails() {
		return this.PhysicalInspectionDetails;
	}

	public void setPhysicalInspectionDetails(List<PhysicalInspectionDetail> PhysicalInspectionDetails) {
		this.PhysicalInspectionDetails = PhysicalInspectionDetails;
	}

	public PhysicalInspectionDetail addPhysicalInspectionDetail(PhysicalInspectionDetail PhysicalInspectionDetail) {
		if(getPhysicalInspectionDetails() == null)
			this.PhysicalInspectionDetails = new ArrayList<PhysicalInspectionDetail>();
		
		getPhysicalInspectionDetails().add(PhysicalInspectionDetail);
		PhysicalInspectionDetail.setPhysicalInspection(this);

		return PhysicalInspectionDetail;
	}

	public PhysicalInspectionDetail removePhysicalInspectionDetail(PhysicalInspectionDetail PhysicalInspectionDetail) {
		getPhysicalInspectionDetails().remove(PhysicalInspectionDetail);
		PhysicalInspectionDetail.setPhysicalInspection(null);

		return PhysicalInspectionDetail;
	}

}