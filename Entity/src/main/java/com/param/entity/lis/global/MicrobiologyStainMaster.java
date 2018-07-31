package com.param.entity.lis.global;



import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({

    @NamedQuery(name = "GET_MICRO_STAIN_LIST", query = "SELECT microbiologyStainMaster.microStainId as id,"
            + " microbiologyStainMaster.desc as name"
            + " FROM MicrobiologyStainMaster microbiologyStainMaster "
            + " WHERE microbiologyStainMaster.orgId = :orgId "
            + " AND microbiologyStainMaster.status= 'A'"),
            
       
})


@Entity
@Table(name = "m_microbiology_stain_master", schema = "lab")
@SequenceGenerator(name = "m_seq_microbiology_stain_master", sequenceName = "lab.m_seq_microbiology_stain_master", allocationSize = 1)
public class MicrobiologyStainMaster {
    
    
    @Id
    @Column(name = "micro_stain_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_microbiology_stain_master")
    private int microStainId;
    
    @Column(name = "stain_code")
    private String code;
    
    @Column(name = "stain_desc")
    private String desc;
    
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
    
    @Column(name = "org_id")
    private Integer orgId;

    public int getMicroStainId() {
      return microStainId;
    }

    public void setMicroStainId(int microStainId) {
      this.microStainId = microStainId;
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

    public Character getStatus() {
      return status;
    }

    public void setStatus(Character status) {
      this.status = (status == '\u0000') ? 'A' : status;
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

    public Integer getOrgId() {
      return orgId;
    }

    public void setOrgId(Integer orgId) {
      this.orgId = orgId;
    }

    
}
