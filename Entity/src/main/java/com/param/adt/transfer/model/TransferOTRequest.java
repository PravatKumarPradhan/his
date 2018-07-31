package com.param.adt.transfer.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.LocalTimeConverter;

@NamedNativeQueries({
	
	@NamedNativeQuery(name="GET_OT_TRANSFER_REQUEST_LIST",query="SELECT tmr.transfer_ot_id as \"transferOTId\", "
				+ "pso.patient_surgery_order_id as \"patientSurgeryOrderId\", "
				+ "pso.surgery_id as \"surgeryId\", "
				+ "pso.admission_id as \"admissionId\", "
				+ "ser.surgery_name as \"surgeryName\", "
				//+ "ss.schedule_id as \"scheduleId\", "
				+ "to_char(tmr.surgery_time,'HH:MI') as \"surgeryTimeString\", "
				+ "to_char(tmr.transfer_time,'HH:MI') as \"transferTimeString\", "
				+ "tmr.is_retained as \"isRetained\", "
				+ "tmr.transfer_status_id as \"transferStatusId\", "
				+ "ts.transfer_status_desc as \"transferStatusDesc\" "
				+ "FROM adt.t_transfer_ot_request tmr "
				+ "INNER join public.t_patient_surgery_order pso on tmr.patient_surgery_order_id = pso.patient_surgery_order_id "
				+ "INNER join public.m_surgery_master ser on ser.surgery_id=pso.surgery_id "
				//+ "LEFT join service.t_service_schedule ss on pso.patient_surgery_order_id = ss.patient_service_order_id AND ss.transfer_type_id=:transferTypeId "
				+ "INNER join adt.m_transfer_status_master ts on ts.transfer_status_id=tmr.transfer_status_id "
				+ "WHERE tmr.admission_id =:admissionId "
				+ "AND tmr.organization_id=:orgId "
				+ "AND tmr.unit_id=:unitId "
				//+ "AND tmr.transfer_status_id = 1 "
				)
})

@Entity
@Table(name="t_transfer_ot_request",schema="adt")
@SequenceGenerator(name="transfer_ot_request_seq",sequenceName="adt.transfer_ot_request_seq",allocationSize=1)
public class TransferOTRequest {

	@Id
	@Column(name="transfer_ot_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="transfer_ot_request_seq")
	private int transferOTId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="surgery_id")
	private Integer surgeryId;
	
	@Column(name="surgery_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime surgeryTime;
	
	@Column(name="transfer_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime TransferTime;
	
	@Column(name="is_retained")
	private char isRetained;
	
	@Column(name="destination_id")
	private Integer destinationId;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name="patient_surgery_order_id")
	private Integer patientSurgeryOrderId;
	
	@Column(name="transfer_status_id")
	private Integer transferStatusId;
	
	
	
	
	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}


	public Integer getPatientSurgeryOrderId() {
		return patientSurgeryOrderId;
	}

	public void setPatientSurgeryOrderId(Integer patientSurgeryOrderId) {
		this.patientSurgeryOrderId = patientSurgeryOrderId;
	}

	public int getTransferOTId() {
		return transferOTId;
	}

	public void setTransferOTId(int transferOTId) {
		this.transferOTId = transferOTId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(Integer surgeryId) {
		this.surgeryId = surgeryId;
	}

	public LocalTime getSurgeryTime() {
		return surgeryTime;
	}

	public void setSurgeryTime(LocalTime surgeryTime) {
		this.surgeryTime = surgeryTime;
	}

	public LocalTime getTransferTime() {
		return TransferTime;
	}

	public void setTransferTime(LocalTime transferTime) {
		TransferTime = transferTime;
	}

	public char getIsRetained() {
		return isRetained;
	}

	public void setIsRetained(char isRetained) {
		this.isRetained = isRetained;
	}

	public Integer getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
