package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.Status;


@Entity(name="PhysicalInspectionDetail")
@Table(name="t_physical_inspection_detail",schema= "procurement")
public class PhysicalInspectionDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name="approved_quantity")
	private Integer approvedQuantity;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "grn_detail_id")
	private GoodReceiptNoteDetail goodReceiptNoteDetail;

	@Column(name="reject_note")
	private String rejectNote;

	@Column(name="reject_quantity")
	private Integer rejectQuantity;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "reject_reason_id")
	private RejectReason rejectReason;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	//bi-directional many-to-one association to TPhysicalInspection
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="physical_inspection_id")
	private PhysicalInspection PhysicalInspection;

	public PhysicalInspectionDetail() {
	}
	
	public PhysicalInspectionDetail(GoodReceiptNoteDetail goodReceiptNoteDetail,Integer approvedQuantity, 
			String rejectNote, Integer rejectQuantity, RejectReason rejectReason, Status status) {
		super();
		this.goodReceiptNoteDetail = goodReceiptNoteDetail;
		this.approvedQuantity = approvedQuantity;
		this.rejectNote = rejectNote;
		this.rejectQuantity = rejectQuantity;
		this.rejectReason = rejectReason;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApprovedQuantity() {
		return this.approvedQuantity;
	}

	public void setApprovedQuantity(Integer approvedQuantity) {
		this.approvedQuantity = approvedQuantity;
	}

	public GoodReceiptNoteDetail getGoodReceiptNoteDetail() {
		return goodReceiptNoteDetail;
	}

	public void setGoodReceiptNoteDetail(GoodReceiptNoteDetail goodReceiptNoteDetail) {
		this.goodReceiptNoteDetail = goodReceiptNoteDetail;
	}

	public String getRejectNote() {
		return this.rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public Integer getRejectQuantity() {
		return this.rejectQuantity;
	}

	public void setRejectQuantity(Integer rejectQuantity) {
		this.rejectQuantity = rejectQuantity;
	}

	public RejectReason getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(RejectReason rejectReason) {
		this.rejectReason = rejectReason;
	}

	public PhysicalInspection getPhysicalInspection() {
		return this.PhysicalInspection;
	}

	public void setPhysicalInspection(PhysicalInspection PhysicalInspection) {
		this.PhysicalInspection = PhysicalInspection;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}