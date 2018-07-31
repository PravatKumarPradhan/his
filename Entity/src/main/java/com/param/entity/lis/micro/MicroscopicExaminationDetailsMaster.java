package com.param.entity.lis.micro;

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
import com.param.entity.lis.global.OrganismGroupMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({

@NamedQuery(name = "GET_MICROORGAMISM_BY_MICROORGANISM_GROUP", query = " SELECT "
		 +"	organismGroupOrganismMapperMaster.organismId AS organismId "
		 +"FROM "
		 +"	OrganismGroupOrganismMapperMaster organismGroupOrganismMapperMaster "
		 +"WHERE "
		 +"	organismGroupOrganismMapperMaster.organismGroupId =:organismGroupId "
		 +"	AND organismGroupOrganismMapperMaster.isDeleted = 'N' "
		 +"	AND organismGroupOrganismMapperMaster.orgId =:orgId " )
})




@Entity
@Table(name = "t_microscopic_examination_details", schema = "lab")
@SequenceGenerator(name = "t_seq_microscopic_examination_details", sequenceName = "lab.t_seq_microscopic_examination_details", allocationSize = 1)
public class MicroscopicExaminationDetailsMaster {
	
	@Id
	@Column(name = "examination_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_microscopic_examination_details")
	private int examinationDetailsId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;

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
	
	@Column(name = "microscopic_exa_id")
	private Integer microscopicExaId;
	
	@Column(name = "organism_group_id")
	private Integer organismGroupId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "microscopic_exa_id", insertable = false, nullable = false, updatable = false)
	private MicroscopicExaminationMaster microscopicExaminationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organism_group_id", insertable = false, nullable = false, updatable = false)
	private OrganismGroupMaster organismGroupMaster;
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "microscopicExaminationDetailsMaster")
	private List<MicroscopicExaminationSubDetailsMaster> listMicroscopicExaminationSubDetailsMaster;

	public int getExaminationDetailsId() {
		return examinationDetailsId;
	}

	public void setExaminationDetailsId(int examinationDetailsId) {
		this.examinationDetailsId = examinationDetailsId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
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

	public Character getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Character isDeleted) {
	  this.isDeleted = (isDeleted == '\u0000') ? 'N' : isDeleted;
	}

	public Integer getMicroscopicExaId() {
		return microscopicExaId;
	}

	public void setMicroscopicExaId(Integer microscopicExaId) {
		this.microscopicExaId = microscopicExaId;
	}


	public MicroscopicExaminationMaster getMicroscopicExaminationMaster() {
		return microscopicExaminationMaster;
	}

	public void setMicroscopicExaminationMaster(MicroscopicExaminationMaster microscopicExaminationMaster) {
		this.microscopicExaminationMaster = microscopicExaminationMaster;
	}

	public Integer getOrganismGroupId() {
		return organismGroupId;
	}

	public void setOrganismGroupId(Integer organismGroupId) {
		this.organismGroupId = organismGroupId;
	}

	public OrganismGroupMaster getOrganismGroupMaster() {
		return organismGroupMaster;
	}

	public void setOrganismGroupMaster(OrganismGroupMaster organismGroupMaster) {
		this.organismGroupMaster = organismGroupMaster;
	}

	public List<MicroscopicExaminationSubDetailsMaster> getListMicroscopicExaminationSubDetailsMaster() {
		return listMicroscopicExaminationSubDetailsMaster;
	}

	public void setListMicroscopicExaminationSubDetailsMaster(
			List<MicroscopicExaminationSubDetailsMaster> listMicroscopicExaminationSubDetailsMaster) {
		this.listMicroscopicExaminationSubDetailsMaster = listMicroscopicExaminationSubDetailsMaster;
	}
	
	
	

}
