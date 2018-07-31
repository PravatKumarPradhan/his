package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Status;

import java.sql.Timestamp;


/**
 * The persistent class for the t_quotation_vendor_detail database table.
 * 
 */
@Entity
@Table(name="t_quotation_vendor_detail",schema="procurement")
public class QuotationVendorDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qr_detail_id")
	private QuotationRequestDetail qrDetail;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	//bi-directional many-to-one association to TQuotationVendor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vq_id")
	private QuotationVendor quotationVendor;

	

	public QuotationVendorDetail() {
		super();
		
	}
	
	public QuotationVendorDetail(Integer id) {
		super();
		this.id = id;
		
	}
	
	public QuotationVendorDetail(QuotationRequestDetail qrDetail, Status status) {
		super();
		this.qrDetail = qrDetail;
		this.status = status;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuotationRequestDetail getQrDetail() {
		return qrDetail;
	}

	public void setQrDetail(QuotationRequestDetail qrDetail) {
		this.qrDetail = qrDetail;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public QuotationVendor getQuotationVendor() {
		return quotationVendor;
	}

	public void setQuotationVendor(QuotationVendor quotationVendor) {
		this.quotationVendor = quotationVendor;
	}

	
}