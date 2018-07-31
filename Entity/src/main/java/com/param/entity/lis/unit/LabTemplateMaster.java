package com.param.entity.lis.unit;

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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.model.GenderMaster;
import com.param.entity.lis.common.LocalTimeConverter;



@NamedQueries({



    @NamedQuery(name = "GET_LAB_TEMPLATES",
        query = "SELECT labTemplateMaster.labTemplateId AS labTemplateId,"
            + " labTemplateMaster.templateCode as templateCode,"
            + " labTemplateMaster.templateDesc as templateDesc,"
            + " labTemplateMaster.htmlData as htmlData "
            + " FROM LabTemplateMaster labTemplateMaster "
            + " WHERE labTemplateMaster.orgId = :orgId "
            + " AND labTemplateMaster.orgUnitId= :orgUnitId "
        	+ " AND labTemplateMaster.status= 'A' "
            + " AND labTemplateMaster.isDeleted= 'N'"),

		@NamedQuery(name = "GET_LAB_TEMPLATE_LIST", query = "SELECT labTemplateMaster.labTemplateId AS labTemplateId,"
				+ " labTemplateMaster.orgId as orgId,"
				+ " labTemplateMaster.orgUnitId as orgUnitId,"
				+ " labTemplateMaster.templateCode as templateCode,"
				+ " labTemplateMaster.templateData as templateData,"
				+ " labTemplateMaster.htmlData as htmlData,"
				+ " labTemplateMaster.templateDesc as templateDesc,"
				+ " labTemplateMaster.pathologistId as pathologistId,"
				+ " labTemplateMaster.genderId as genderId,"
				+ " gen.desc as genderName,"
				+ " labTemplateMaster.templatePath as templatePath,"
	            + " labTemplateMaster.templateData as templateData,"
				+ " labTemplateMaster.status as status,"
				+ " labTemplateMaster.createdBy as createdBy,"
				+ " labTemplateMaster.createdDate as createdDate,"
				+ " labTemplateMaster.updatedBy as updatedBy,"
				+ " labTemplateMaster.updatedDate as updatedDate "
				+ " FROM LabTemplateMaster labTemplateMaster "
				+"  INNER JOIN labTemplateMaster.genderMaster gen "
				+ " WHERE labTemplateMaster.orgId = :orgId "
				+ " AND labTemplateMaster.orgUnitId= :orgUnitId "
		        + " AND labTemplateMaster.isDeleted= 'N'"),
		
		
		@NamedQuery(name = "GET_LAB_TEMPLATE_BY_CODE", query = "SELECT labTemplateMaster.labTemplateId AS labTemplateId,"
				+ " labTemplateMaster.orgId as orgId,"
				+ " labTemplateMaster.orgUnitId as orgUnitId,"
				+ " labTemplateMaster.templateCode as templateCode,"
				+ " labTemplateMaster.templateData as templateData,"
				+ " labTemplateMaster.htmlData as htmlData,"
				+ " labTemplateMaster.templateDesc as templateDesc,"
				+ " labTemplateMaster.pathologistId as pathologistId,"
				+ " labTemplateMaster.genderId as genderId,"
				+ " labTemplateMaster.templatePath as templatePath,"
	            + " labTemplateMaster.templateData as templateData,"
				+ " labTemplateMaster.status as status,"
				+ " labTemplateMaster.createdBy as createdBy,"
				+ " labTemplateMaster.createdDate as createdDate,"
				+ " labTemplateMaster.updatedBy as updatedBy,"
				+ " labTemplateMaster.updatedDate as updatedDate "
				+ " FROM LabTemplateMaster labTemplateMaster "
				+ " WHERE labTemplateMaster.orgId = :orgId "
				+ " AND lower(labTemplateMaster.templateCode) = lower(:templateCode)"),
		
		
		
		@NamedQuery(name = "GET_TEMPLATES_BY_TEMPLATE_TYPE", query = "SELECT DISTINCT(labTemplateMaster.labTemplateId) AS labTemplateId,"
            + " labTemplateMaster.templateCode as description,"
            + " labTemplateMaster.htmlData as html,"
            + " labTemplateMaster.templateDesc as title "
            + " FROM LabTemplateMaster labTemplateMaster "
            + " INNER JOIN labTemplateMaster.ListSpecialtyTemplateMapper lstSpecialtyTemplateMapper "
            + " WHERE labTemplateMaster.orgId = :orgId "
            + " AND labTemplateMaster.orgUnitId = :orgUnitId "
            + " AND lstSpecialtyTemplateMapper.status = 'A' "
            + " AND lstSpecialtyTemplateMapper.isDeleted = 'N' "
            + " AND lstSpecialtyTemplateMapper.templateTypeId = :templateTypeId")

		

})



@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_LAB_TEMPLATE_RECORD", query = "select count(*) FROM lab.m_lab_template_master where "
		+ "org_id = :orgId AND "+ "org_unit_id = 1"),
	
	@NamedNativeQuery(name = "UPDATE_BY_LAB_TEMPLATE_ID", query = "UPDATE lab.m_specialty_template_mapper specialty_template_mapper "
			+ "SET is_deleted = 'Y' "
			+ " WHERE specialty_template_mapper.lab_template_id= :labTemplateId"
			+"  And specialty_template_mapper.org_id= :orgId"
			+"  And specialty_template_mapper.speciality_id= :specialityId"),
	
	@NamedNativeQuery(name = "UPDATE_STATUS_SPECL_TEMPLATE", query = "UPDATE lab.m_specialty_template_mapper specialty_template_mapper "
			+ "SET status =:status"
			+ " WHERE specialty_template_mapper.lab_template_id=:labTemplateId")





})

@Entity
@Table(name = "m_lab_template_master", schema = "lab")
@SequenceGenerator(name = "m_seq_lab_template_master",
    sequenceName = "lab.m_seq_lab_template_master", allocationSize = 1)
public class LabTemplateMaster {

	
	


  @Id
  @Column(name = "lab_template_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_lab_template_master")
  private int labTemplateId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "template_code")
  private String templateCode;

  @Column(name = "template_desc")
  private String templateDesc;

  @Column(name = "pathologist_id")
  private Integer pathologistId;

  @Column(name = "gender_id")
  private Integer genderId;

  @Column(name = "template_path")
  private String templatePath;

  @Column(name = "status")
  private Character status;

  @Column(name = "created_by")
  private Integer createdBy;

  @Column(name = "created_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long createdDate;

  @Column(name = "updated_by")
  private Integer updatedBy;

  @Column(name = "updated_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long updatedDate;

  @Column(name = "is_deleted")
  private Character isDeleted;

  @Column(name = "template_data")
  private String templateData;

  @Column(name = "html_data")
  private String htmlData;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gender_id", insertable = false, nullable = false, updatable = false)
  private GenderMaster genderMaster;
  
  @OneToMany(fetch = FetchType.LAZY,mappedBy = "labTemplateMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SpecialtyTemplateMapper> ListSpecialtyTemplateMapper;
	
  
  public int getLabTemplateId() {
    return labTemplateId;
  }

  public void setLabTemplateId(int labTemplateId) {
    this.labTemplateId = labTemplateId;
  }

  public Integer getOrgId() {
    return orgId;
  }

  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }

  public String getTemplateCode() {
    return templateCode;
  }

  public void setTemplateCode(String templateCode) {
    this.templateCode = templateCode;
  }

  public String getTemplateDesc() {
    return templateDesc;
  }

  public void setTemplateDesc(String templateDesc) {
    this.templateDesc = templateDesc;
  }

  public Integer getPathologistId() {
    return pathologistId;
  }

  public void setPathologistId(Integer pathologistId) {
    this.pathologistId = pathologistId;
  }

  public Integer getGenderId() {
    return genderId;
  }

  public void setGenderId(Integer genderId) {
    this.genderId = genderId;
  }

  public String getTemplatePath() {
    return templatePath;
  }

  public void setTemplatePath(String templatePath) {
    this.templatePath = templatePath;
  }

  public Character getStatus() {
    return status;
  }

  public void setStatus(Character status) {
    this.status = status;
  }

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public Long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Long createdDate) {
    this.createdDate = createdDate;
  }

  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Long getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Long updatedDate) {
    this.updatedDate = updatedDate;
  }

  public GenderMaster getGenderMaster() {
    return genderMaster;
  }

  public void setGenderMaster(GenderMaster genderMaster) {
    this.genderMaster = genderMaster;
  }

  public Integer getOrgUnitId() {
    return orgUnitId;
  }

  public void setOrgUnitId(Integer orgUnitId) {
    this.orgUnitId = orgUnitId;
  }

  public Character getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Character isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getTemplateData() {
    return templateData;
  }

  public void setTemplateData(String templateData) {
    this.templateData = templateData;
  }


  public String getHtmlData() {
    return htmlData;
  }

  public void setHtmlData(String htmlData) {
    this.htmlData = htmlData;
  }

public List<SpecialtyTemplateMapper> getListSpecialtyTemplateMapper() {
	return ListSpecialtyTemplateMapper;
}

public void setListSpecialtyTemplateMapper(List<SpecialtyTemplateMapper> listSpecialtyTemplateMapper) {
	ListSpecialtyTemplateMapper = listSpecialtyTemplateMapper;
}



}
	



