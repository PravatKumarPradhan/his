package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the t_quotation_fill_detail database table.
 * 
 */
@Entity
@Table(name="t_quotation_fill_detail",schema="procurement")
public class QuotationFillDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	@Column(name="vendor_cop")
	private Double vendorCop;

	@Column(name="expected_payment")
	private String expectedPayment;

	@Column(name="expected_support_term")
	private String expectedSupportTerm;

	@Column(name="expected_technical_specification")
	private String expectedTechnicalSpecification;

	@Column(name="vendor_warranty_period")
	private String vendorWarrantyPeriod;
	
	private String remark;
	
	//bi-directional many-to-one association to TQuotationFill
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="quotation_fill_id")
	private QuotationFill quotationFill;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qr_detail_id")
	private QuotationRequestDetail quotationRequestDetail;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vq_detail_id")
	private QuotationVendorDetail quotationVendorDetail;

	//bi-directional many-to-one association to TQuotationFillAccessory
	@OneToMany(fetch = FetchType.LAZY, mappedBy="quotationFillDetail", cascade = CascadeType.ALL)
	private List<QuotationFillAccessory> quotationFillAccessoryList;

	
	public QuotationFillDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuotationFillDetail(Integer id) {
		this.id = id;
	}	

	public QuotationFillDetail(Status status, Double vendorCop, String expectedPayment, String expectedSupportTerm,
			String expectedTechnicalSpecification, String vendorWarrantyPeriod, String remark,
			QuotationRequestDetail quotationRequestDetail, QuotationVendorDetail quotationVendorDetail) {
		super();
		this.status = status;
		this.vendorCop = vendorCop;
		this.expectedPayment = expectedPayment;
		this.expectedSupportTerm = expectedSupportTerm;
		this.expectedTechnicalSpecification = expectedTechnicalSpecification;
		this.vendorWarrantyPeriod = vendorWarrantyPeriod;
		this.remark = remark;
		this.quotationRequestDetail = quotationRequestDetail;
		this.quotationVendorDetail = quotationVendorDetail;
	}



	public void updateQuotationFillDetail(Double vendorCop, String expectedPayment, String expectedSupportTerm,
			String expectedTechnicalSpecification, String vendorWarrantyPeriod, String remark) {
		this.vendorCop = vendorCop;
		this.expectedPayment = expectedPayment;
		this.expectedSupportTerm = expectedSupportTerm;
		this.expectedTechnicalSpecification = expectedTechnicalSpecification;
		this.vendorWarrantyPeriod = vendorWarrantyPeriod;
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

	public Double getVendorCop() {
		return vendorCop;
	}

	public void setVendorCop(Double vendorCop) {
		this.vendorCop = vendorCop;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExpectedPayment() {
		return expectedPayment;
	}

	public void setExpectedPayment(String expectedPayment) {
		this.expectedPayment = expectedPayment;
	}

	public String getExpectedSupportTerm() {
		return expectedSupportTerm;
	}

	public void setExpectedSupportTerm(String expectedSupportTerm) {
		this.expectedSupportTerm = expectedSupportTerm;
	}

	public String getExpectedTechnicalSpecification() {
		return expectedTechnicalSpecification;
	}

	public void setExpectedTechnicalSpecification(String expectedTechnicalSpecification) {
		this.expectedTechnicalSpecification = expectedTechnicalSpecification;
	}

	public String getVendorWarrantyPeriod() {
		return this.vendorWarrantyPeriod;
	}

	public void setVendorWarrantyPeriod(String vendorWarrantyPeriod) {
		this.vendorWarrantyPeriod = vendorWarrantyPeriod;
	}

	public QuotationRequestDetail getQuotationRequestDetail() {
		return quotationRequestDetail;
	}

	public void setQuotationRequestDetail(QuotationRequestDetail quotationRequestDetail) {
		this.quotationRequestDetail = quotationRequestDetail;
	}

	public QuotationVendorDetail getQuotationVendorDetail() {
		return quotationVendorDetail;
	}

	public void setQuotationVendorDetail(QuotationVendorDetail quotationVendorDetail) {
		this.quotationVendorDetail = quotationVendorDetail;
	}

	public List<QuotationFillAccessory> getQuotationFillAccessoryList() {
		return quotationFillAccessoryList;
	}

	public void setQuotationFillAccessoryList(List<QuotationFillAccessory> quotationFillAccessoryList) {
		this.quotationFillAccessoryList = quotationFillAccessoryList;
	}

	public QuotationFill getQuotationFill() {
		return quotationFill;
	}

	public void setQuotationFill(QuotationFill quotationFill) {
		this.quotationFill = quotationFill;
	}
	
	public QuotationFillAccessory addQuotationFillAccessoryList(QuotationFillAccessory quotationFillAccessory) {
		if (getQuotationFillAccessoryList() == null)
			this.quotationFillAccessoryList = new ArrayList<QuotationFillAccessory>();
		
		getQuotationFillAccessoryList().add(quotationFillAccessory);
		quotationFillAccessory.setQuotationFillDetail(this);
		
		return quotationFillAccessory;
	}

}