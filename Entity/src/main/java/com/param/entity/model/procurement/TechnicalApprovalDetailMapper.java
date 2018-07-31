package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Vendor;

import java.sql.Timestamp;


/**
 * The persistent class for the t_technical_approval_detail_mapper database table.
 * 
 */
@Entity
@Table(name="t_technical_approval_detail_mapper", schema = "procurement")
public class TechnicalApprovalDetailMapper extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private Boolean accept;
	
	private Boolean reject;
	
	@Column(name="reject_note")
	private String rejectNote;
	
	@Column(name="on_hold")
	private Boolean onHold;
	
	private String remark;	

	@Column(name="expected_payment")
	private Boolean expectedPayment;	
	
	@Column(name="support_term")
	private Boolean supportTerm;
	
	@Column(name="technical_specification")
	private Boolean technicalSpecification;
	
	@Column(name="warrenty_period")
	private Boolean warrentyPeriod;	
	
	@Column(name="expected_payment_reject_note")
	private String expectedPaymentRejectNote;

	@Column(name="support_term_reject_note")
	private String supportTermRejectNote;
	
	@Column(name="technical_specification_reject_note")
	private String technicalSpecificationRejectNote;
	
	@Column(name="warrenty_period_reject_note")
	private String warrentyPeriodRejectNote;

	
	//bi-directional many-to-one association to TTechnicalApproval
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="technical_approval_detail_id")
	private TechnicalApprovalDetail technicalApprovalDetail;
	
	//bi-directional many-to-one association to TTechnicalApproval
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="quotation_fill_id")
	private QuotationFill quotationFill;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;
	
	
	public TechnicalApprovalDetailMapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TechnicalApprovalDetailMapper(String remark, Boolean expectedPayment, Boolean supportTerm,
			Boolean technicalSpecification, Boolean warrentyPeriod, QuotationFill quotationFill, Status status,
			Vendor vendor) {
		super();
		this.remark = remark;
		this.expectedPayment = expectedPayment;
		this.supportTerm = supportTerm;
		this.technicalSpecification = technicalSpecification;
		this.warrentyPeriod = warrentyPeriod;
		this.quotationFill = quotationFill;
		this.status = status;
		this.vendor = vendor;
	}
	
	public void updateTechnicalApprovalDetailMapper(Boolean accept, Boolean reject, String rejectNote, Boolean onHold,
			String remark, Boolean expectedPayment, Boolean supportTerm, Boolean technicalSpecification,
			Boolean warrentyPeriod, String expectedPaymentRejectNote, String supportTermRejectNote,
			String technicalSpecificationRejectNote, String warrentyPeriodRejectNote) {
		this.accept = accept;
		this.reject = reject;
		this.rejectNote = rejectNote;
		this.onHold = onHold;
		this.remark = remark;
		this.expectedPayment = expectedPayment;
		this.supportTerm = supportTerm;
		this.technicalSpecification = technicalSpecification;
		this.warrentyPeriod = warrentyPeriod;
		this.expectedPaymentRejectNote = expectedPaymentRejectNote;
		this.supportTermRejectNote = supportTermRejectNote;
		this.technicalSpecificationRejectNote = technicalSpecificationRejectNote;
		this.warrentyPeriodRejectNote = warrentyPeriodRejectNote;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

	public Boolean getReject() {
		return reject;
	}

	public void setReject(Boolean reject) {
		this.reject = reject;
	}

	public String getRejectNote() {
		return rejectNote;
	}

	public void setRejectNote(String rejectNote) {
		this.rejectNote = rejectNote;
	}

	public Boolean getOnHold() {
		return onHold;
	}

	public void setOnHold(Boolean onHold) {
		this.onHold = onHold;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getExpectedPayment() {
		return expectedPayment;
	}

	public void setExpectedPayment(Boolean expectedPayment) {
		this.expectedPayment = expectedPayment;
	}

	public Boolean getSupportTerm() {
		return supportTerm;
	}

	public void setSupportTerm(Boolean supportTerm) {
		this.supportTerm = supportTerm;
	}

	public Boolean getTechnicalSpecification() {
		return technicalSpecification;
	}

	public void setTechnicalSpecification(Boolean technicalSpecification) {
		this.technicalSpecification = technicalSpecification;
	}

	public Boolean getWarrentyPeriod() {
		return warrentyPeriod;
	}

	public void setWarrentyPeriod(Boolean warrentyPeriod) {
		this.warrentyPeriod = warrentyPeriod;
	}

	public String getExpectedPaymentRejectNote() {
		return expectedPaymentRejectNote;
	}

	public void setExpectedPaymentRejectNote(String expectedPaymentRejectNote) {
		this.expectedPaymentRejectNote = expectedPaymentRejectNote;
	}

	public String getSupportTermRejectNote() {
		return supportTermRejectNote;
	}

	public void setSupportTermRejectNote(String supportTermRejectNote) {
		this.supportTermRejectNote = supportTermRejectNote;
	}

	public String getTechnicalSpecificationRejectNote() {
		return technicalSpecificationRejectNote;
	}

	public void setTechnicalSpecificationRejectNote(String technicalSpecificationRejectNote) {
		this.technicalSpecificationRejectNote = technicalSpecificationRejectNote;
	}

	public String getWarrentyPeriodRejectNote() {
		return warrentyPeriodRejectNote;
	}

	public void setWarrentyPeriodRejectNote(String warrentyPeriodRejectNote) {
		this.warrentyPeriodRejectNote = warrentyPeriodRejectNote;
	}

	public TechnicalApprovalDetail getTechnicalApprovalDetail() {
		return technicalApprovalDetail;
	}

	public void setTechnicalApprovalDetail(TechnicalApprovalDetail technicalApprovalDetail) {
		this.technicalApprovalDetail = technicalApprovalDetail;
	}

	public QuotationFill getQuotationFill() {
		return quotationFill;
	}

	public void setQuotationFill(QuotationFill quotationFill) {
		this.quotationFill = quotationFill;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	
}