package com.param.lis.unit.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class LabTemplateMasterDto {

  private Integer labTemplateId;

  private MultipartFile inputFile;

  private Integer orgId;

  private Integer orgUnitId;

  private String templateCode;

  private String templateDesc;

  private Integer pathologistId;

  private Integer genderId;

  private String templatePath;

  private Character status;

  private Integer createdBy;

  private Long createdDate;

  private Integer updatedBy;

  private Long updatedDate;

  private Character isDeleted;

  private String templateData;

  private String htmlData;
  private String genderName;
  
  private List<SpecialtyTemplateMapperDto> listSpecialtyTemplateMapperDto;


  public Integer getLabTemplateId() {
    return labTemplateId;
  }

  public void setLabTemplateId(Integer labTemplateId) {
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

  public MultipartFile getInputFile() {
    return inputFile;
  }

  public void setInputFile(MultipartFile inputFile) {
    this.inputFile = inputFile;
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

public List<SpecialtyTemplateMapperDto> getListSpecialtyTemplateMapperDto() {
	return listSpecialtyTemplateMapperDto;
}

public void setListSpecialtyTemplateMapperDto(List<SpecialtyTemplateMapperDto> listSpecialtyTemplateMapperDto) {
	this.listSpecialtyTemplateMapperDto = listSpecialtyTemplateMapperDto;
}

public String getGenderName() {
	return genderName;
}

public void setGenderName(String genderName) {
	this.genderName = genderName;
}




}
