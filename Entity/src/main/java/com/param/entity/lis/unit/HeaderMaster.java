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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({
	@NamedQuery(name = "GET_HEADER_BY_HEADR_ID", query = "SELECT headerMaster.headerId as id, "
			+ " headerMaster.code as code,"
			+ " headerMaster.desc as desc,"
			+ " headerMaster.status as status,"
			+ " headerMaster.orgId as orgId,"
			+ " headerMaster.createdBy as createdBy,"
			+ " headerMaster.createdDate as createdDate,"
			+ " headerMaster.updatedBy as updatedBy,"
			+ " headerMaster.unitId as unitId,"
			+ " headerMaster.updatedDate as updatedDate"
			+ " FROM HeaderMaster headerMaster"
			+ " WHERE headerMaster.headerId = :headerId "
			+ " AND headerMaster.orgId= :orgId"
			+ " AND headerMaster.unitId= :unitId"),
	
	@NamedQuery(name = "GET_HEADER_BY_CODE", query = "SELECT headerMaster.headerId as id,"
			+ " headerMaster.code as code,"
			+ " headerMaster.desc as desc,"
			+ " headerMaster.status as status,"
			+ " headerMaster.createdBy as createdBy,"
			+ " headerMaster.createdDate as createdDate,"
			+ " headerMaster.updatedBy as updatedBy,"
			+ " headerMaster.unitId as unitId,"
			+ " headerMaster.updatedDate as updatedDate"
			+ " FROM HeaderMaster headerMaster"
			+ " WHERE headerMaster.orgId=:orgId "
			+"  AND headerMaster.unitId=:unitId "
			+ " AND lower(headerMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_HEADER_BY_CODE", query = "SELECT headerMaster.headerId as id,"
					+ " headerMaster.code as code,"
					+ " headerMaster.desc as desc,"
					+ " headerMaster.status as status,"
					+ " headerMaster.createdBy as createdBy,"
					+ " headerMaster.createdDate as createdDate,"
					+ " headerMaster.updatedBy as updatedBy,"
					+ " headerMaster.unitId as unitId,"
					+ " headerMaster.updatedDate as updatedDate"
					+ " FROM HeaderMaster headerMaster"
					+ " WHERE headerMaster.orgId=:orgId "
					+"  AND headerMaster.unitId=:unitId "
					+ " AND lower(headerMaster.code) = lower(:code)"
					+ " AND headerMaster.headerId <> :headerId"),

	@NamedQuery(name = "GET_PAGINATED_HEADER_MASTER_LIST", query = "SELECT headerMaster.headerId as id,"
			+ " headerMaster.code as code,"
			+ " headerMaster.desc as desc,"
			+ " headerMaster.status as status,"
			+ " headerMaster.createdBy as createdBy,"
			+ " headerMaster.orgId as orgId,"
			+ " headerMaster.unitId as unitId,"
			+ " headerMaster.createdDate as createdDate,"
			+ " headerMaster.updatedBy as updatedBy,"
			+ " headerMaster.updatedDate as updatedDate"
			+ " FROM HeaderMaster headerMaster"
			+ " WHERE headerMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_HEADER_RECORD", query = "select count(*) from lab.m_header_master where "
	+ "org_id = :orgId")
})
	@Entity
	@Table(name = "m_header_master", schema = "lab")
	@SequenceGenerator(name = "m_seq_header_master", sequenceName = "lab.m_seq_header_master", allocationSize = 1)
	public class HeaderMaster
	{
		@Id
		@Column(name = "header_id")
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_header_master")
		private int headerId;
		@Column(name="header_code")
		private String code;

		@Column(name="header_desc")
		private String desc;

		@Column(name="header_status")
		private char status; 

		@Column(name="crated_by")
		private int createdBy;

		@Column(name="created_date")
		@Convert(converter = LocalTimeConverter.class)
		private Long createdDate;
		
		@Column(name="updated_by")
		private int updatedBy;
		
		@Column(name="updated_date")
		@Convert(converter = LocalTimeConverter.class)
		private Long updatedDate;
		
		@Column(name = "org_id")
		private Integer orgId;
		
		@Column(name = "org_unit_id")
		private Integer unitId;
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "headerMaster")
		private List<TestParamMppr> listTestParamMppr;

		public int getHeaderId() {
			return headerId;
		}

		public void setHeaderId(int headerId) {
			this.headerId = headerId;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
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

		public Long getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Long createdDate) {
			this.createdDate = createdDate;
		}

		public int getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(int updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Long getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Long updatedDate) {
			this.updatedDate = updatedDate;
		}

		public Integer getOrgId() {
			return orgId;
		}

		public void setOrgId(Integer orgId) {
			this.orgId = orgId;
		}

		public Integer getUnitId() {
			return unitId;
		}

		public void setUnitId(Integer unitId) {
			this.unitId = unitId;
		}

		public List<TestParamMppr> getListTestParamMppr()
		{
			return listTestParamMppr;
		}

		public void setListTestParamMppr(List<TestParamMppr> listTestParamMppr)
		{
			this.listTestParamMppr = listTestParamMppr;
		}
		
		
	}
