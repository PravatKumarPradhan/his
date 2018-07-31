package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Status;

import java.sql.Timestamp;

/**
 * The persistent class for the t_quotation_fill_accessories database table.
 * 
 */
@Entity
@Table(name="t_quotation_fill_accessories",schema="procurement")
public class QuotationFillAccessory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	private String name;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qr_accessories_id")
	private QuotationRequestAccessoriesMapper qrAccessories;
	
	@Column(name="vendor_detail")
	private String vendorDetail;

	//bi-directional many-to-one association to TQuotationFillDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="quotation_fill_detail_id")
	private QuotationFillDetail quotationFillDetail;
	
	public QuotationFillAccessory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QuotationFillAccessory(String name, QuotationRequestAccessoriesMapper qrAccessories, String vendorDetail) {
		super();
		this.name = name;
		this.qrAccessories = qrAccessories;
		this.vendorDetail = vendorDetail;
	}
	
	public void updateQuotationFillAccessory(String name, String vendorDetail) {
		this.name = name;
		this.vendorDetail = vendorDetail;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QuotationRequestAccessoriesMapper getQrAccessories() {
		return qrAccessories;
	}

	public void setQrAccessories(QuotationRequestAccessoriesMapper qrAccessories) {
		this.qrAccessories = qrAccessories;
	}

	public String getVendorDetail() {
		return vendorDetail;
	}

	public void setVendorDetail(String vendorDetail) {
		this.vendorDetail = vendorDetail;
	}

	public QuotationFillDetail getQuotationFillDetail() {
		return quotationFillDetail;
	}

	public void setQuotationFillDetail(QuotationFillDetail quotationFillDetail) {
		this.quotationFillDetail = quotationFillDetail;
	}

	
	
}