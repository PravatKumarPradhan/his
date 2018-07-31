package com.param.entity.lis.transaction;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
//PatientArrivalMaster 
@Embeddable
@SuppressWarnings("serial")
public class PatientArrivalMasterId implements Serializable 
{

	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "status_id")
	private Integer statusId;
	
	@Column(name = "lab_smp_dtls_id")
	private Long labSmpDtlsId;
	
	@Column(name = "lab_smp_mst_id")
	private Integer labSmpMstId;
	
	@Column(name = "order_id")
	private Integer orderId ;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Long getLabSmpDtlsId() {
		return labSmpDtlsId;
	}

	public void setLabSmpDtlsId(Long labSmpDtlsId) {
		this.labSmpDtlsId = labSmpDtlsId;
	}

	public Integer getLabSmpMstId() {
		return labSmpMstId;
	}

	public void setLabSmpMstId(Integer labSmpMstId) {
		this.labSmpMstId = labSmpMstId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
