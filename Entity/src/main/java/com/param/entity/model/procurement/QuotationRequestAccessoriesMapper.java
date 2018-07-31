package com.param.entity.model.procurement;

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

@Entity()
@Table(name="t_qr_accessories_mapper",schema="procurement")
public class QuotationRequestAccessoriesMapper extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="accessories_detail")
	private String accessoriesDetail;
	
	@Column(name="name")
	private String name;	
	
	//bi-directional many-to-one association to QuotationRequest
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qr_detail_id")
    private QuotationRequestDetail quotationRequestDetail;
	

	public QuotationRequestAccessoriesMapper() {
		super();
	}
	
	public QuotationRequestAccessoriesMapper(Integer id) {
		super();
		this.id = id ;
	}

	public QuotationRequestAccessoriesMapper(String accessoriesDetail, String name) {
		super();
		this.accessoriesDetail = accessoriesDetail;
		this.name = name;
	}
	
	public void updateQuotationRequestAccessoriesMapper(String accessoriesDetail, String name) {
		this.accessoriesDetail = accessoriesDetail;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessoriesDetail() {
		return accessoriesDetail;
	}

	public void setAccessoriesDetail(String accessoriesDetail) {
		this.accessoriesDetail = accessoriesDetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QuotationRequestDetail getQuotationRequestDetail() {
		return quotationRequestDetail;
	}

	public void setQuotationRequestDetail(QuotationRequestDetail quotationRequestDetail) {
		this.quotationRequestDetail = quotationRequestDetail;
	}
	
}
