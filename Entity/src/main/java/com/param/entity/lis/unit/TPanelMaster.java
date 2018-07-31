package com.param.entity.lis.unit;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.global.SampleTypeMediaMappingMaster;
import com.param.global.model.ServiceMaster;

@NamedNativeQueries({

		@NamedNativeQuery(name = "GET_PAGINATED_TPANEL_TEST_MASTER_LIST", query = " SELECT  "
				+ "	panel_master.panel_id AS \"panelId\",  "
				+ "	panel_master.org_id AS \"orgId\",  "
				+ "	panel_master.org_unit_id AS \"orgUnitId\",  "
				+ "	panel_master.panel_code AS \"panelCode\",  "
				+ "	panel_master.panel_alies AS \"panelAlies\",  "
				+ "	panel_master.status AS \"status\",  "
				+ "	panel_master.is_deleted AS \"isDeleted\",  "
				+ "	panel_master.created_by AS \"createdBy\",  "
				/* +"	panel_master.created_date AS \"createdDate\",  " */
				+ "	panel_master.updated_by AS \"updatedBy\"  "
				/* +"	panel_master.updated_date AS \"updatedDate\"  " */
				+ "FROM  "
				+ "	lab.t_panel_master panel_master  " 
				+ "WHERE  "
				+ "	panel_master.org_id =:orgId  " 
				+ "	AND panel_master.org_unit_id =:orgUnitId  "
				+ "	AND panel_master.is_deleted = 'N'  "),

		@NamedNativeQuery(name = "GET_PAGINATED_TPANEL_TEST_MASTER_LIST_COUNT", query = "SELECT  " 
		+ "	COUNT(*)  "
				+ "FROM  " 
				+ "	lab.t_panel_master panel_master  "
				+ "WHERE  "
				+ "	panel_master.org_id =:orgId  " 
				+ "	AND panel_master.org_unit_id =:orgUnitId  "
				+ "	AND panel_master.is_deleted = 'N'  "),

		@NamedNativeQuery(name = "UPDATE_TPANEL_TEST_MASTER_ACTIVE_INACTIVE", query = "UPDATE  "
				+ "	lab.t_panel_details  "
				+ "SET  "
				+ "	status =:status  " 
				+ "WHERE  "
				+ "	org_id =:orgId  "
				+ "	AND org_unit_id =:orgUnitid  "
				+ "	AND panel_id =:panelId  "),

		@NamedNativeQuery(name = "SET_MAPPR_DETAILS_STATUS_BEFORE_UPDATE", query = "UPDATE  "
				+ "	lab.t_panel_details  " 
				+ "SET  " 
				+ "	is_deleted = 'Y'  "
				+ "WHERE  "
				+ "	org_id =:orgId  " 
				+ "	AND org_unit_id =:orgUnitId "
				+ "	AND panel_id =:panelId "),

		@NamedNativeQuery(name = "GET_PANEL_MAPPER_BY_PANEL_ID", query = "SELECT  "
				+ "	panel_master.panel_id AS \"serviceId\",  " + "	panel_master.org_id AS \"orgId\",  "
				+ "	panel_master.org_unit_id AS \"orgUnitId\",  "
				+ "	panel_master.panel_code AS \"panelCode\",  "
				+ "	panel_master.panel_alies AS \"panelAlies\",  " 
				+ "	panel_master.status AS \"status\",  "
				+ "	panel_master.is_deleted AS \"isDeleted\",  "
				+ "	panel_master.created_by AS \"createdBy\",  "
				+ "	panel_master.created_date AS \"createdDate\",  "
				+ "	panel_master.updated_by AS \"updatedBy\",  "
				+ "	panel_master.updated_date AS \"updatedDate\"  " 
				+ "FROM  "
				+ "	lab.t_panel_master panel_master  "
				+ "WHERE  " 
				+ "	panel_master.org_id =:orgId "
				+ "	AND panel_master.org_unit_id =:orgUnitId "
				+ "	AND panel_master.is_deleted = 'N' "
				+ "	AND panel_master.panel_id = :panelId "),

		/*
		 * @NamedNativeQuery(name = "GET_PANEL_MAPPER_DETAILS_BY_SERVICE_ID", query =
		 * "SELECT  "
		 * +"	panel_details.panel_master_dtls_id AS \"panelTestMpprDtlsId\",  "
		 * +"	panel_details.panel_id AS \"serviceId\",  "
		 * +"	panel_details.test_id AS \"testId\",  "
		 * +"	panel_details.header_id AS \"headerId\",  "
		 * +"	panel_details.org_id AS \"orgId\",  "
		 * +"	panel_details.org_unit_id AS \"orgUnitId\",  "
		 * +"	panel_details.status AS \"status\",  "
		 * +"	panel_details.is_deleted AS \"isDeleted\",  "
		 * +"	panel_details.created_by AS \"createdBy\",  "
		 * +"	panel_details.created_date AS \"createdDate\",  "
		 * +"	panel_details.updated_by AS \"updatedBy\",  "
		 * +"	panel_details.updated_date AS \"updatedDate\"  " +"FROM  "
		 * +"	lab.t_panel_details panel_details  " +"WHERE  "
		 * +"	panel_details.org_id =:orgId  "
		 * +"	AND panel_details.org_unit_id =:orgUnitId  "
		 * +"	AND panel_details.is_deleted = 'N'  "
		 * +"	AND panel_details.panel_id =:serviceId  "),
		 */

		@NamedNativeQuery(name = "CHECK_PANEL_CODE_BY_PANEL_ID", query = "SELECT  "
		+ "	COUNT(*)  "
		+ "FROM  "
				+ "	lab.t_panel_master panel_master  " 
				+ "WHERE  "
				+ "	panel_master.org_id =:orgId  "
				+ "	AND panel_master.org_unit_id =:orgUnitId  "
				+ "	AND panel_master.is_deleted = 'N'  "
				+ "	AND lower(panel_master.panel_code) =  lower(:panelCode) "),

		@NamedNativeQuery(name = "CHECK_PANEL_TEST_EXISTS", query = "SELECT " 
		+ "	COUNT(*)  "
				+ "FROM  "
				+ "	lab.t_panel_master panel_master  "
				+ "WHERE  " 
				+ "	panel_master.org_id =:orgId "
				+ "	AND panel_master.org_unit_id =:orgUnitId" 
				+ "	AND panel_master.is_deleted = 'N'  "
				+ "	AND panel_master.service_id = :serviceId "),
		
		
		@NamedNativeQuery(name = "GET_PANEL_DETAILS", query =" SELECT  "
            +" panel_master.panel_code AS \"panelCode\",  "
            +" panel_master.panel_alies AS \"panelAlies\"  "
            +"FROM  "
            +" lab.t_panel_master panel_master  "
            +"WHERE  "
            +" panel_master.panel_id =:panelId  "
            +" AND panel_master.org_id =:orgId  "
            +" AND panel_master.org_unit_id =:orgUnitId  "
            +" AND panel_master.is_deleted = 'N'  "),

		})

@Entity
@Table(name = "t_panel_master", schema = "lab")
@SequenceGenerator(name = "t_seq_panel_master", sequenceName = "lab.t_seq_panel_master", allocationSize = 1)
public class TPanelMaster {
	@Id
	@Column(name = "panel_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_panel_master")
	private int panelId;

	@Column(name = "service_id")
	private Integer serviceId;
	@Column(name = "org_id")
	private Integer orgId;
	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "panel_alies")
	private String panelAlies;

	@Column(name = "panel_code")
	private String panelCode;

	@Column(name = "status")
	private char status;

	@Column(name = "is_deleted")
	private char isDeleted;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id", insertable = false, nullable = false, updatable = false)
	private ServiceMaster serviceMaster;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tPanelMaster")
	private List<TPanelDetailsMaster> listTPanelDetailsMaster;

	public String getPanelAlies() {
		return panelAlies;
	}

	public void setPanelAlies(String panelAlies) {
		this.panelAlies = panelAlies;
	}

	public String getPanelCode() {
		return panelCode;
	}

	public void setPanelCode(String panelCode) {
		this.panelCode = panelCode;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public char getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(char isDeleted) {
		this.isDeleted = (isDeleted == '\u0000') ? 'N' : isDeleted;

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

	public int getPanelId() {
		return panelId;
	}

	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
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

	public List<TPanelDetailsMaster> getListTPanelDetailsMaster() {
		return listTPanelDetailsMaster;
	}

	public void setListTPanelDetailsMaster(List<TPanelDetailsMaster> listTPanelDetailsMaster) {
		this.listTPanelDetailsMaster = listTPanelDetailsMaster;
	}

	public ServiceMaster getServiceMaster() {
		return serviceMaster;
	}

	public void setServiceMaster(ServiceMaster serviceMaster) {
		this.serviceMaster = serviceMaster;
	}

	
}