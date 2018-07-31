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

@Entity(name="ReturnOfEmptyAmpoulesDetail")
@Table(name="t_return_of_empty_ampoules_detail",schema="pharmacy")
public class ReturnOfEmptyAmpoulesDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="return_of_empty_ampoules_id")
	private ReturnOfEmptyAmpoules returnOfEmptyAmpoules;
	
	@Column(name="return_qty")
	private Integer returnQuantity;
	
	@Column(name="accept_quantity")
	private Integer acceptQuantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="issue_batch_mapper_id")
	private IssueDetailBatchMapper issueBatchMapper;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReturnOfEmptyAmpoules getReturnOfEmptyAmpoules() {
		return returnOfEmptyAmpoules;
	}

	public void setReturnOfEmptyAmpoules(ReturnOfEmptyAmpoules returnOfEmptyAmpoules) {
		this.returnOfEmptyAmpoules = returnOfEmptyAmpoules;
	}

	public Integer getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(Integer returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public IssueDetailBatchMapper getIssueBatchMapper() {
		return issueBatchMapper;
	}

	public void setIssueBatchMapper(IssueDetailBatchMapper issueBatchMapper) {
		this.issueBatchMapper = issueBatchMapper;
	}

	public Integer getAcceptQuantity() {
		return acceptQuantity;
	}

	public void setAcceptQuantity(Integer acceptQuantity) {
		this.acceptQuantity = acceptQuantity;
	}

	public ReturnOfEmptyAmpoulesDetail(Integer returnQuantity, IssueDetailBatchMapper issueBatchMapper) {
		super();
		this.returnQuantity = returnQuantity;
		this.issueBatchMapper = issueBatchMapper;
	}

	public ReturnOfEmptyAmpoulesDetail() {
		super();
	}
	
}