package com.param.entity.model.procurement;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.User;
import com.param.entity.model.master.Vendor;

/**
 * The persistent class for the t_quotation_vendor database table.
 * 
 */
@Entity
@Table(name = "t_quotation_vendor",schema="procurement")
public class QuotationVendor extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approved_date")
	private Date approvedDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "approval_status_id")
	private Status approvalStatus;

	@Column(name = "cancel_note")
	private String cancelNote;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancel_reason_id")
	private CancelReason cancelReason;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qr_id")
	private QuotationRequest quotationRequest;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@Column(name = "vq_number")
	private String vqNumber;

	// bi-directional many-to-one association to TQuotationVendorDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quotationVendor", cascade = CascadeType.ALL)
	private List<QuotationVendorDetail> quotationVendorDetailsList;

	
	public QuotationVendor() {
		super();
	}
	
	public QuotationVendor(Integer id) {
		this.id = id;
	}

	public QuotationVendor(Date dueDate, QuotationRequest quotationRequest, Status status, Store store, Vendor vendor,
			String vqNumber, Status approvalStatus) {
		super();
		this.dueDate = dueDate;
		this.quotationRequest = quotationRequest;
		this.status = status;
		this.store = store;
		this.vendor = vendor;
		this.vqNumber = vqNumber;
		this.approvalStatus = approvalStatus;
	}
	
	public void  updateQuotationVendor(Date dueDate) {
		
		this.dueDate = dueDate;		
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getCancelNote() {
		return cancelNote;
	}

	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public QuotationRequest getQuotationRequest() {
		return quotationRequest;
	}

	public void setQuotationRequest(QuotationRequest quotationRequest) {
		this.quotationRequest = quotationRequest;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getVqNumber() {
		return vqNumber;
	}

	public void setVqNumber(String vqNumber) {
		this.vqNumber = vqNumber;
	}
	
	public Status getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Status approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public List<QuotationVendorDetail> getQuotationVendorDetailsList() {
		return quotationVendorDetailsList;
	}

	public void setQuotationVendorDetailsList(List<QuotationVendorDetail> quotationVendorDetailsList) {
		this.quotationVendorDetailsList = quotationVendorDetailsList;
	}

	public QuotationVendorDetail addQuotationVendorDetail(QuotationVendorDetail quotationVendorDetail) {
		if(getQuotationVendorDetailsList() == null)
			this.quotationVendorDetailsList = new ArrayList<QuotationVendorDetail>();
		
		getQuotationVendorDetailsList().add(quotationVendorDetail);
		quotationVendorDetail.setQuotationVendor(this);
		return quotationVendorDetail;
	}
	
	
	public QuotationVendorDetail removeQuotationVendorDetail(QuotationVendorDetail quotationVendorDetail) {
		getQuotationVendorDetailsList().remove(quotationVendorDetail);
		quotationVendorDetail.setQuotationVendor(null);

		return quotationVendorDetail;
	}

}