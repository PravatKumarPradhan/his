package com.param.opd.coversheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.PatientRegistration;
import com.param.global.model.RelationMaster;
import com.param.opd.appointment.model.AppointmentMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_PATIENT_FAMILY_HISTORY",
			    query=" SELECT 		patientFamilyHist.familyHistoryId as familyHistoryId, patientFamilyHist.status as status, "
			    	+ "        		patientFamilyHist.remark as remark, patientFamilyHist.isNoFamilyHistoryStatus as isNoFamilyHistoryStatus,"
			    	+ "				patientFamilyHist.relationId as relationId, patientFamilyHist.diagnosisId as diagnosisId, "
			    	+ "				patientFamilyHist.encounterId as encounterId,"
			    	+ "				patientFamilyHist.roleId as roleId,"
			    	+ "				patientFamilyHist.isEnterInErrorStatus as isEnterInErrorStatus,"
			    	+ "  			patientFamilyHist.patientId as patientId, patientFamilyHist.organizationId as organizationId, patientFamilyHist.unitId as unitId,"
			    	+ "				patientFamilyHist.createdBy as createdBy, patientFamilyHist.updatedBy as updatedBy, patientFamilyHist.isReviewedFlag as isReviewedFlag, "
			    	+ "				to_char(patientFamilyHist.createdDate, 'DD-MM-YYYY') as createdDate, to_char(patientFamilyHist.updatedDate, 'DD-MM-YYYY') as updatedDate,"
			    	+ " 			relationMst.desc as desc, diagMst.code as diagnosisCode, diagMst.diagnosisName as diagnosisName "
			    	+ " FROM 		PatientFamilyHistory patientFamilyHist "
			    	+ " INNER JOIN 	patientFamilyHist.patientRegistration patientReg "
			    	+ " INNER JOIN 	patientFamilyHist.relationMaster relationMst "
			    	+ " INNER JOIN	patientFamilyHist.diagnosisMaster diagMst"
			    	+ " WHERE 		patientFamilyHist.patientId=:patientId "
			    	+ " AND 		patientFamilyHist.status = 'A' "
			    	+ " AND 		patientFamilyHist.unitId=:unitId "
			    	+ " AND 		patientFamilyHist.organizationId=:organizationId"
			    	+ " AND 		patientFamilyHist.encounterId!=:encounterId"
			    	+ " ORDER BY 	patientFamilyHist.familyHistoryId desc"
			),
			
			
			@NamedQuery(name="GET_PATIENT_FAMILY_HISTORY_BY_ROLE_ID",
		    query=" SELECT 		patientFamilyHist.familyHistoryId as familyHistoryId, patientFamilyHist.status as status, "
		    	+ "        		patientFamilyHist.remark as remark, patientFamilyHist.isNoFamilyHistoryStatus as isNoFamilyHistoryStatus,"
		    	+ "				patientFamilyHist.relationId as relationId, patientFamilyHist.diagnosisId as diagnosisId, "
		    	+ "				patientFamilyHist.encounterId as encounterId,"
		    	+ "				patientFamilyHist.roleId as roleId,"
		    	+ "				patientFamilyHist.isEnterInErrorStatus as isEnterInErrorStatus,"
		    	+ "  			patientFamilyHist.patientId as patientId, patientFamilyHist.organizationId as organizationId, patientFamilyHist.unitId as unitId,"
		    	+ "				patientFamilyHist.createdBy as createdBy, patientFamilyHist.updatedBy as updatedBy, patientFamilyHist.isReviewedFlag as isReviewedFlag, "
		    	+ "				to_char(patientFamilyHist.createdDate, 'DD-MM-YYYY') as createdDate, to_char(patientFamilyHist.updatedDate, 'DD-MM-YYYY') as updatedDate,"
		    	+ " 			relationMst.desc as desc, diagMst.code as diagnosisCode, diagMst.diagnosisName as diagnosisName "
		    	+ " FROM 		PatientFamilyHistory patientFamilyHist "
		    	+ " INNER JOIN 	patientFamilyHist.patientRegistration patientReg "
		    	+ " INNER JOIN 	patientFamilyHist.relationMaster relationMst "
		    	+ " INNER JOIN	patientFamilyHist.diagnosisMaster diagMst"
		    	+ " WHERE 		patientFamilyHist.patientId=:patientId "
		    	+ " AND 		patientFamilyHist.status = 'A' "
		    	+ " AND 		patientFamilyHist.unitId=:unitId "
		    	+ " AND 		patientFamilyHist.organizationId=:organizationId"
		    	+ " AND 		patientFamilyHist.roleId=:roleId"
		    	+ " AND 		patientFamilyHist.encounterId!=:encounterId"
		    	+ " ORDER BY 	patientFamilyHist.familyHistoryId desc"
		),
		
		
		@NamedQuery(name="GET_CURRENT_PATIENT_FAMILY_HISTORY",
	    query=" SELECT 		patientFamilyHist.familyHistoryId as familyHistoryId, patientFamilyHist.status as status, "
	    	+ "        		patientFamilyHist.remark as remark, patientFamilyHist.isNoFamilyHistoryStatus as isNoFamilyHistoryStatus,"
	    	+ "				patientFamilyHist.relationId as relationId, patientFamilyHist.diagnosisId as diagnosisId, "
	    	+ "				patientFamilyHist.encounterId as encounterId,"
	    	+ "				patientFamilyHist.roleId as roleId,"
	    	+ "				patientFamilyHist.isEnterInErrorStatus as isEnterInErrorStatus,"
	    	+ "  			patientFamilyHist.patientId as patientId, patientFamilyHist.organizationId as organizationId, patientFamilyHist.unitId as unitId,"
	    	+ "				patientFamilyHist.createdBy as createdBy, patientFamilyHist.updatedBy as updatedBy, patientFamilyHist.isReviewedFlag as isReviewedFlag, "
	    	+ "				to_char(patientFamilyHist.createdDate, 'DD-MM-YYYY') as createdDate, to_char(patientFamilyHist.updatedDate, 'DD-MM-YYYY') as updatedDate,"
	    	+ " 			relationMst.desc as desc, diagMst.code as diagnosisCode, diagMst.diagnosisName as diagnosisName "
	    	+ " FROM 		PatientFamilyHistory patientFamilyHist "
	    	+ " INNER JOIN 	patientFamilyHist.patientRegistration patientReg "
	    	+ " INNER JOIN 	patientFamilyHist.relationMaster relationMst "
	    	+ " INNER JOIN	patientFamilyHist.diagnosisMaster diagMst"
	    	+ " WHERE 		patientFamilyHist.patientId=:patientId "
	    	+ " AND 		patientFamilyHist.status = 'A' "
	    	+ " AND 		patientFamilyHist.unitId=:unitId "
	    	+ " AND 		patientFamilyHist.organizationId=:organizationId"
	    	+ " AND 		patientFamilyHist.encounterId=:encounterId"
	    	+ " ORDER BY 	patientFamilyHist.familyHistoryId desc"
	),
	
	
	@NamedQuery(name="GET_CURRENT_PATIENT_FAMILY_HISTORY_BY_ROLE_ID",
    query=" SELECT 		patientFamilyHist.familyHistoryId as familyHistoryId, patientFamilyHist.status as status, "
    	+ "        		patientFamilyHist.remark as remark, patientFamilyHist.isNoFamilyHistoryStatus as isNoFamilyHistoryStatus,"
    	+ "				patientFamilyHist.relationId as relationId, patientFamilyHist.diagnosisId as diagnosisId, "
    	+ "				patientFamilyHist.encounterId as encounterId,"
    	+ "				patientFamilyHist.roleId as roleId,"
    	+ "				patientFamilyHist.isEnterInErrorStatus as isEnterInErrorStatus,"
    	+ "  			patientFamilyHist.patientId as patientId, patientFamilyHist.organizationId as organizationId, patientFamilyHist.unitId as unitId,"
    	+ "				patientFamilyHist.createdBy as createdBy, patientFamilyHist.updatedBy as updatedBy, patientFamilyHist.isReviewedFlag as isReviewedFlag, "
    	+ "				to_char(patientFamilyHist.createdDate, 'DD-MM-YYYY') as createdDate, to_char(patientFamilyHist.updatedDate, 'DD-MM-YYYY') as updatedDate,"
    	+ " 			relationMst.desc as desc, diagMst.code as diagnosisCode, diagMst.diagnosisName as diagnosisName "
    	+ " FROM 		PatientFamilyHistory patientFamilyHist "
    	+ " INNER JOIN 	patientFamilyHist.patientRegistration patientReg "
    	+ " INNER JOIN 	patientFamilyHist.relationMaster relationMst "
    	+ " INNER JOIN	patientFamilyHist.diagnosisMaster diagMst"
    	+ " WHERE 		patientFamilyHist.patientId=:patientId "
    	+ " AND 		patientFamilyHist.status = 'A' "
    	+ " AND 		patientFamilyHist.unitId=:unitId "
    	+ " AND 		patientFamilyHist.organizationId=:organizationId"
    	+ " AND 		patientFamilyHist.roleId=:roleId"
    	+ " AND 		patientFamilyHist.encounterId =:encounterId"
    	+ " ORDER BY 	patientFamilyHist.familyHistoryId desc"
),

@NamedQuery(name="GET_PATIENT_FAMILY_HISTORY_BY_ID",
query=" SELECT 		patientFamilyHist.familyHistoryId as familyHistoryId, patientFamilyHist.status as status, "
	+ "        		patientFamilyHist.remark as remark, patientFamilyHist.isNoFamilyHistoryStatus as isNoFamilyHistoryStatus,"
	+ "				patientFamilyHist.relationId as relationId, patientFamilyHist.diagnosisId as diagnosisId, "
	+ "				patientFamilyHist.encounterId as encounterId,"
	+ "				patientFamilyHist.roleId as roleId,"
	+ "				patientFamilyHist.isEnterInErrorStatus as isEnterInErrorStatus,"
	+ "  			patientFamilyHist.patientId as patientId, patientFamilyHist.organizationId as organizationId, patientFamilyHist.unitId as unitId,"
	+ "				patientFamilyHist.createdBy as createdBy, patientFamilyHist.updatedBy as updatedBy, patientFamilyHist.isReviewedFlag as isReviewedFlag, "
	+ "				to_char(patientFamilyHist.createdDate, 'DD-MM-YYYY') as createdDate, to_char(patientFamilyHist.updatedDate, 'DD-MM-YYYY') as updatedDate,"
	+ " 			relationMst.desc as desc, diagMst.code as diagnosisCode, diagMst.diagnosisName as diagnosisName "
	+ " FROM 		PatientFamilyHistory patientFamilyHist "
	+ " INNER JOIN 	patientFamilyHist.patientRegistration patientReg "
	+ " INNER JOIN 	patientFamilyHist.relationMaster relationMst "
	+ " INNER JOIN	patientFamilyHist.diagnosisMaster diagMst"
	+ " WHERE 		patientFamilyHist.familyHistoryId =:familyHistoryId"

)

	
	
	
})

@Entity
@Table(name="t_family_history",schema="patient")
@SequenceGenerator(name="t_family_history_seq" , sequenceName="patient.t_family_history_seq", allocationSize=1)
public class PatientFamilyHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "t_family_history_seq")
	@Column(name="family_history_id")
	private Integer familyHistoryId;
	
	@Column(name="status")
	private char status;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="is_no_family_history_status")
	private char isNoFamilyHistoryStatus;
	
	@Column(name="relation_id")
	private Integer relationId;
	
	@Column(name="diagnosis_id")
	private Integer diagnosisId;
	
	@Column(name="encounter_id")
	private Integer encounterId;
	
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="is_reviewed_flag")
	private char isReviewedFlag;
	
	@Column(name="is_enter_in_error")
	private char isEnterInErrorStatus;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", insertable = false, updatable = false)
	private PatientRegistration patientRegistration;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_id", insertable = false, nullable = false, updatable = false)
	private AppointmentMaster appointmentMaster;*/
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_id", insertable = false, nullable = false, updatable = false)
	private RelationMaster relationMaster;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosis_id", insertable = false, nullable = false, updatable = false)
	private DiagnosisMaster diagnosisMaster;
	
	

	public char getIsEnterInErrorStatus() {
		return isEnterInErrorStatus;
	}

	public void setIsEnterInErrorStatus(char isEnterInErrorStatus) {
		this.isEnterInErrorStatus = isEnterInErrorStatus;
	}

	public DiagnosisMaster getDiagnosisMaster() {
		return diagnosisMaster;
	}

	public void setDiagnosisMaster(DiagnosisMaster diagnosisMaster) {
		this.diagnosisMaster = diagnosisMaster;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getFamilyHistoryId() {
		return familyHistoryId;
	}

	public void setFamilyHistoryId(Integer familyHistoryId) {
		this.familyHistoryId = familyHistoryId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public char getIsNoFamilyHistoryStatus() {
		return isNoFamilyHistoryStatus;
	}

	public void setIsNoFamilyHistoryStatus(char isNoFamilyHistoryStatus) {
		this.isNoFamilyHistoryStatus = isNoFamilyHistoryStatus;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Integer diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public char getIsReviewedFlag() {
		return isReviewedFlag;
	}

	public void setIsReviewedFlag(char isReviewedFlag) {
		this.isReviewedFlag = isReviewedFlag;
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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}



	public RelationMaster getRelationMaster() {
		return relationMaster;
	}

	public void setRelationMaster(RelationMaster relationMaster) {
		this.relationMaster = relationMaster;
	}
	
	
	
}
