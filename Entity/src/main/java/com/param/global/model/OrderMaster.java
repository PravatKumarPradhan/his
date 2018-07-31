package com.param.global.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.DateConverter;
import com.param.opd.coversheet.model.EncounterMaster;


@NamedQueries({

	@NamedQuery(name = "LAB_UPDATE_ORDER_MASTER_STATUS", query = "UPDATE"
			+ " OrderMaster orderMaster "
			+ " SET "
			+ " orderMaster.orderStatusId = :orderStatusId  "
			+ " WHERE "
			+ " orderMaster.orderId = :orderId "),
	
	@NamedQuery(name = "CHECK_ORDER_STATUS", query = " SELECT "
			  +"	COUNT(*) "
			  +"FROM "
			  +"	OrderMaster orderMaster "
			  +"INNER JOIN orderMaster.listOrderDetailsMaster orderDtailsMaster "
			  +"WHERE "
			  +"	orderMaster.orderStatusId NOT IN(:orderStatus) "
			  +"	AND orderMaster.orgId = :orgId"
			  +"	AND orderMaster.orgUnitId  = :orgUnitId "
			  +"	AND orderDtailsMaster.serviceRendered = :serviceRendered "
			  +"	AND orderDtailsMaster.serviceIsBilled = :serviceIsBilled "
			  +"	AND orderDtailsMaster.serviceRenderingDeptId = :serviceRenderingDeptId "
			  +"	AND orderDtailsMaster.orderId = :orderId "),
	
})


@Entity
@Table(name = "t_order_master", schema = "public")
@SequenceGenerator(name = "m_seq_order_master", sequenceName = "public.m_seq_order_master", allocationSize = 1)
public class OrderMaster
{
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_order_master")
	private int orderId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "order_no")
	private String orderNo;

	@Column(name = "order_date")
	@Convert(converter=DateConverter.class)
	private Long orderDate;
	
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	
	@Column(name = "encounter_id")
	private Integer encounterId;
	
	@Column(name = "admission_id")
	private Integer admissionId;
	
	@Column(name = "ord_doctor_id")
	private Integer doctorId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	@Column(name = "priority_id")
	private Integer priorityId;
	
	@Column(name = "dr_cr_id")
	private Integer drCrId ;
	
	@Column(name = "ord_remarks")
	private String ordRemarks;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_date")
	@Convert(converter=DateConverter.class)
	private Long createdDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_date")
	@Convert(converter=DateConverter.class)
	private Long updatedDate;
	
	@Column(name = "order_status")
	private Character orderStatus;
	
	@Column(name = "order_status_id")
	private Integer orderStatusId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderMaster")
	private List<OrderDetailsMaster> listOrderDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priority_id", insertable = false, nullable = false, updatable = false)
	private PriorityMaster priorityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ord_doctor_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
    private VisitTypeMaster visitTypeMaster;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_id", insertable = false, nullable = false, updatable = false)
    private EncounterMaster encounterMaster;
	
	public int getOrderId()
	{
		return orderId;
	}

	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	public Integer getOrgId()
	{
		return orgId;
	}

	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}

	public Integer getOrgUnitId()
	{
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId)
	{
		this.orgUnitId = orgUnitId;
	}

	public String getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}

	public Long getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(Long orderDate)
	{
		this.orderDate = orderDate;
	}

	public Integer getVisitTypeId()
	{
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId)
	{
		this.visitTypeId = visitTypeId;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPriorityId()
	{
		return priorityId;
	}

	public void setPriorityId(Integer priorityId)
	{
		this.priorityId = priorityId;
	}

	

	public Integer getDrCrId()
	{
		return drCrId;
	}

	public void setDrCrId(Integer drCrId)
	{
		this.drCrId = drCrId;
	}

	public String getOrdRemarks()
	{
		return ordRemarks;
	}

	public void setOrdRemarks(String ordRemarks)
	{
		this.ordRemarks = ordRemarks;
	}

	public Integer getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	public Long getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Long createdDate)
	{
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public Character getOrderStatus()
	{
		return orderStatus;
	}

	public void setOrderStatus(Character orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	public List<OrderDetailsMaster> getListOrderDetailsMaster() {
		return listOrderDetailsMaster;
	}

	public void setListOrderDetailsMaster(List<OrderDetailsMaster> listOrderDetailsMaster) {
		this.listOrderDetailsMaster = listOrderDetailsMaster;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	public PriorityMaster getPriorityMaster() {
		return priorityMaster;
	}

	public void setPriorityMaster(PriorityMaster priorityMaster) {
		this.priorityMaster = priorityMaster;
	}

  public DoctorMaster getDoctorMaster() {
    return doctorMaster;
  }

  public void setDoctorMaster(DoctorMaster doctorMaster) {
    this.doctorMaster = doctorMaster;
  }

  public VisitTypeMaster getVisitTypeMaster() {
    return visitTypeMaster;
  }

  public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
    this.visitTypeMaster = visitTypeMaster;
  }
	
	
}
