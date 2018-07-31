package com.param.billing.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="GET_ACTIVE_CARD_TYPE_NAME", 
			query="SELECT cardTypeMaster.cardTypeId as cardTypeId, cardTypeMaster.cardTypeName as cardTypeName, "
					+ " cardTypeMaster.unitId as unitId, cardTypeMaster.orgnisationId as orgnisationId "
					+ " FROM CardTypeMaster cardTypeMaster"
					+ " WHERE cardTypeMaster.status='A' AND cardTypeMaster.unitId =:unitId AND cardTypeMaster.orgnisationId =:orgId")
})
@Entity
@Table(name="m_card_type_master",schema="billing")
public class CardTypeMaster {
	@Id
	@Column(name = "card_type_id")
	private int cardTypeId;
	
	@Column(name = "card_type_name")
	private String cardTypeName;

	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name = "orgnisation_id")
	private Integer orgnisationId;

	@Column(name = "status")
	private Character status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "cardTypeMaster")
	private List<PaymentDepositDetails> listPaymentDepositDetails;
	
	public List<PaymentDepositDetails> getListPaymentDepositDetails() {
		return listPaymentDepositDetails;
	}

	public void setListPaymentDepositDetails(List<PaymentDepositDetails> listPaymentDepositDetails) {
		this.listPaymentDepositDetails = listPaymentDepositDetails;
	}

	public int getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrgnisationId() {
		return orgnisationId;
	}

	public void setOrgnisationId(Integer orgnisationId) {
		this.orgnisationId = orgnisationId;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
