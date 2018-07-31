package com.param.lis.transaction.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class PatientArrivalMasterId implements Serializable
{

		private Integer patientId;
		
		private Integer statusId;
		
		private Long labSmpDtlsId;
		
		private Integer labSmpMstId;
		
		private Integer orderId;

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
