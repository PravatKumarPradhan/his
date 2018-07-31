package com.param.lis.transaction.dao;

import java.math.BigInteger;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.entity.lis.transaction.LabSampleDetailsMaster;


import com.param.lis.common.dto.CommonAutoCompleteDto;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.global.common.StringCommonMethods;
import com.param.lis.transaction.dto.LabCommonDto;
import com.param.lis.transaction.dto.LabDashBoardDetailsDto;
import com.param.lis.transaction.dto.LabDashBoardDto;

import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class LabDashBoardDaoImpl extends GenericDao<LabSampleDetailsMaster, Integer>
    implements ILabDashBoardDao, ICommonConstants, IError, ITransactionConstants {

  final static Logger logger = Logger.getLogger(LabDashBoardDaoImpl.class);

  public LabDashBoardDaoImpl() {
    super(LabSampleDetailsMaster.class);
  }


  @Override
  public Response getlabDashBoardList(LabCommonDto labCommonDto) throws ApplicationException {
    try {
      List<LabDashBoardDto> listLabDashBoardDto =
          sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_DASHBOARD_LIST")
              .setInteger("orgId", labCommonDto.getOrgId())
              .setInteger("orgUnitId", labCommonDto.getOrgUnitId())
              .setInteger("serviceRenderingDeptId", labCommonDto.getDeptId())
              .setFirstResult(labCommonDto.getOffset() != null ? labCommonDto.getOffset() : 0)
              .setMaxResults(labCommonDto.getNoOfRecordsPerPage() != null
                  ? labCommonDto.getNoOfRecordsPerPage()
                  : 10)
              .setResultTransformer(Transformers.aliasToBean(LabDashBoardDto.class)).list();

      if (listLabDashBoardDto.isEmpty()) {
        return new Response(ERROR, ERR_500, "No Speciman Records Found.", null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listLabDashBoardDto, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  public Response getlabDashBoardListCount(LabCommonDto labCommonDto) throws ApplicationException {
    try {
      BigInteger labDashBoardCount =
          (BigInteger) sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_DASHBOARD_LIST_COUNT")
              .setInteger("orgId", labCommonDto.getOrgId())
              .setInteger("orgUnitId", labCommonDto.getOrgUnitId())
              .setInteger("serviceRenderingDeptId", labCommonDto.getDeptId())
              .uniqueResult();
      if (labDashBoardCount.compareTo(BigInteger.ZERO) == 1) {
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, labDashBoardCount);
      } else {
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
      }
      
    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  public Response getSampleLog(Integer labSampleDtlsId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      List<LabDashBoardDetailsDto> listLabDashBoardDetailsDto =
          sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_DASHBOARD_DETAILS")
              .setInteger("orgId", orgId)
              .setInteger("orgUnitId", orgUnitId)
              .setInteger("labSampleDtlsId", labSampleDtlsId)
              .setResultTransformer(Transformers.aliasToBean(LabDashBoardDetailsDto.class)).list();

      if (listLabDashBoardDetailsDto.isEmpty()) {
        return new Response(ERROR, ERR_500, "No Speciman Records Found.", null, null);
      } else {
        return new Response(SUCCESS, SUCCESS_200, null, listLabDashBoardDetailsDto, null);
      }

    } catch (Exception e) {
      logger.error("Exection", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  public Response getFilteredDashBoardList(SearchDto searchDto) throws ApplicationException {
    try
    {
        
        StringBuilder getDashBoardSearchQuery =new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_DASHBOARD_LIST").getQueryString().toString());
        
        if(searchDto.getFromDate()!=null && !searchDto.getFromDate().isEmpty())
        {
       /*   getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.getFromDate()+"' ");*/
          getDashBoardSearchQuery.append(" AND order_details.created_date > '"+searchDto.getFromDate()+"' ");
        }
        
        if(searchDto.getToDate()!=null && !searchDto.getToDate().isEmpty())
        {
         /* getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.getToDate()+"' ");*/
          getDashBoardSearchQuery.append(" AND order_details.created_date < '"+searchDto.getToDate()+"' ");
        }
        
        if(searchDto.getPatientId()!=null &&searchDto.getPatientId() >0)
          getDashBoardSearchQuery.append(" AND pati_mst.patient_id = "+searchDto.getPatientId());
        
        if(searchDto.getSpecimanTypes()!=null && searchDto.getSpecimanTypes().size()>0)
          getDashBoardSearchQuery.append(" AND speciman_mst.speciman_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getSpecimanTypes()) +")");
        
        if(searchDto.getVisitTypes()!=null && searchDto.getVisitTypes().size()>0)
          getDashBoardSearchQuery.append(" AND visit_type_master.visit_type_id IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
        
        if(searchDto.getTestTypes()!=null && searchDto.getTestTypes().size()>0)
          getDashBoardSearchQuery.append(" AND labSampleDetailsMst.test_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getTestTypes())+")");
        
        if(searchDto.getSubDepts()!=null && searchDto.getSubDepts().size()>0)
          getDashBoardSearchQuery.append(" AND test_mst.dept_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getSubDepts())+")");
        
        if(searchDto.getOutSource()!=null && searchDto.getOutSource()=='Y')
          getDashBoardSearchQuery.append(" AND test_mst.is_outsourced ='Y' ");
        
        if(searchDto.getSampleStatusId()!=null && searchDto.getSampleStatusId()>0)
          getDashBoardSearchQuery.append(" AND lbsampledtls.sample_status_id ="+searchDto.getSampleStatusId());
        
        if(searchDto.getPending()!=null && searchDto.getPending()=='Y')
          getDashBoardSearchQuery.append(" AND order_details.service_rendered = 0");
        
        if(searchDto.getLabSampleDtlsId()!=null && searchDto.getLabSampleDtlsId()>0)
          getDashBoardSearchQuery.append(" AND lbsampledtls.lab_sample_dtls_id ="+searchDto.getLabSampleDtlsId());
        
        if(searchDto.getCompleted()!=null && searchDto.getCompleted()=='Y')
          getDashBoardSearchQuery.append(" lbsampledtls.sample_status_id ="+FINAL_REPORT_RELEASED);
        
        Query createdQuery=sessionFactory.getCurrentSession().createSQLQuery(getDashBoardSearchQuery.toString());
        
        
        List<LabDashBoardDto> listLabDashBoardDto = createdQuery
            .setInteger("orgId", searchDto.getOrgId())
            .setInteger("orgUnitId", searchDto.getOrgUnitId())
            .setInteger("serviceRenderingDeptId", searchDto.getDeptId())
            .setResultTransformer(Transformers.aliasToBean(LabDashBoardDto.class)).list();
        
        
        if(listLabDashBoardDto.isEmpty())
        {
          return new Response(SUCCESS, SUCCESS_200_MESSAGE,"No Records Found.",listLabDashBoardDto , null);
        }
        else {
          return new Response(SUCCESS, SUCCESS_200_MESSAGE,null, listLabDashBoardDto, null);
        }
       
        
    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
   
    
  }


@Override
public Response searchAcceptedSpecimanByPatient(SearchCommonDto searchCommonDto) throws ApplicationException {
	List<CommonAutoCompleteDto>  patientList  = null; 
	try 
	{
		patientList = (List<CommonAutoCompleteDto>) sessionFactory.getCurrentSession()
				.getNamedQuery("SEARCH_DASHBORD_PATIENT")
				.setInteger("orgId", searchCommonDto.getOrgId())
				.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
				.setInteger("serviceRenderingDeptId", searchCommonDto.getDeptId())
				.setString("searchKeyword", "%"+searchCommonDto.getSearchKeyword().trim().toLowerCase().trim()+"%")
                .setResultTransformer(Transformers.aliasToBean(CommonAutoCompleteDto.class)).list();					
		return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, patientList, null);

	} catch (Exception e)
	{
		logger.error("Exection", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}


@Override
public Response searchAcceptedSampleNo(SearchCommonDto searchCommonDto) throws ApplicationException {
	List<LabDashBoardDto>  patientList  = null; 
	try
	{
		patientList = (List<LabDashBoardDto>) sessionFactory.getCurrentSession()
				.getNamedQuery("SEARCH_SAMPLE_NO_LAB_DASHBOARD_LIST")
				.setInteger("orgId", searchCommonDto.getOrgId())
				.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
				.setString("searchKeyword", "%"+searchCommonDto.getSearchKeyword().trim().toLowerCase().trim()+"%")
				//.setInteger("sampleStatusId", SPECIMAN_ACCEPTED)
				//.setInteger("detpId", searchCommonDto.getDeptId())
				//.setInteger("subDeptId", searchCommonDto.getSubDeptId())
                .setResultTransformer(Transformers.aliasToBean(LabDashBoardDto.class)).list();					
		return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, patientList, null);

	} catch (Exception e)
	{
		logger.error("Exection", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}

}
