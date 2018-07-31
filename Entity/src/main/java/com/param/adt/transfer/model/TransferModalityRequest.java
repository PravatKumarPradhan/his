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
	
	@NamedNativeQuery(name="GET_MODALITY_TRANSFER_REQUEST_LIST",
			query="SELECT tmr.transfer_modality_id as \"transferModalityId\", "
				+ "pso.patient_sevices_order_id as \"patientSevicesOrderId\", "
				+ "pso.service_id as \"serviceId\", "
				+ "pso.admission_id as \"admissionId\", "
				+ "ser.service_standard_name as \"serviceStandardName\", "
				+ "ss.schedule_id as \"scheduleId\", "
				+ "to_char(tmr.modality_time,'HH:MI') as \"modalityTimeString\", "
				+ "to_char(tmr.transfer_time,'HH:MI') as \"transferTimeString\", "
				+ "tmr.note as \"note\", "
				+ "tmr.transfer_status_id as \"transferStatusId\", "
				+ "ts.transfer_status_desc as \"transferStatusDesc\", "
				+ "tmr.modality_id as \"modalityId\", "
				+ "mm.modality_desc as \"modalityDesc\" "
				+ "FROM adt.t_transfer_modality_request tmr "
				+ "left join public.t_patient_sevices_order pso on tmr.patient_service_order_id = pso.patient_sevices_order_id "
				+ "left join service.m_service_master ser on ser.service_master_id=pso.service_id "
				+ "left join service.t_service_schedule ss on pso.patient_sevices_order_id = ss.patient_service_order_id AND ss.transfer_type_id=:transferTypeId "
				+ "left join adt.m_transfer_status_master ts on ts.transfer_status_id=tmr.transfer_status_id "
				+ "left JOIN service.m_modality_master mm on tmr.modality_id = mm.modality_id "
				+ "where tmr.admission_id =:admissionId "
				+ "AND tmr.organization_id=:orgId "
				+ "AND tmr.unit_id=:unitId "
				+ "AND tmr.transfer_status_id = 1 "
				)
})


@Entity
@Table(name="t_transfer_modality_request",schema="adt")
@SequenceGenerator(name="transfer_modality_request_seq",sequenceName="adt.transfer_modality_request_seq",allocationSize=1)
public class TransferModalityRequest 
{
	@Id
	@Column(name="transfer_modality_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="transfer_modality_request_seq")
	private int transferModalityId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="patient_service_order_id")
	private Integer patientServiceOrderId;
	
	@Column(name="admission_id")
	private Integer admissionId;
	
	@Column(name="schedular_id")
	private Integer schedularId;
	
	@Column(name="modality_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime modalityTime;
	
	@Column(name="transfer_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime transferTime;
	
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
	
	@Column(name="note")
	private String note;
	
	@Column(name="transfer_status_id")
	private Integer transferStatusId;
	
	@Column(name="modality_id")
	private Integer modalityId;
	
	
	
	public Integer getModalityId() {
		return modalityId;
	}

	public void setModalityId(Integer modalityId) {
		this.modalityId = modalityId;
	}

	public Integer getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getTransferModalityId() {
		return transferModalityId;
	}

	public void setTransferModalityId(int transferModalityId) {
		this.transferModalityId = transferModalityId;
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

	public Integer getPatientServiceOrderId() {
		return patientServiceOrderId;
	}

	public void setPatientServiceOrderId(Integer patientServiceOrderId) {
		this.patientServiceOrderId = patientServiceOrderId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getSchedularId() {
		return schedularId;
	}

	public void setSchedularId(Integer schedularId) {
		this.schedularId = schedularId;
	}

	public LocalTime getModalityTime() {
		return modalityTime;
	}

	public LocalTime getTransferTime() {
		return transferTime;
	}

	public char getIsRetained() {
		return isRetained;
	}

	public void setIsRetained(char isRetained) {
		this.isRetained = (isRetained == '\u0000') ? 'A' : isRetained;
	}

	public Integer getDestinationId() {
		return destinationId;
	}

	public void setModalityTime(LocalTime modalityTime) {
		this.modalityTime = modalityTime;
	}

	public void setTransferTime(LocalTime transferTime) {
		this.transferTime = transferTime;
	}

	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}

	

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
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


