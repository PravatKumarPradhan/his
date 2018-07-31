package com.param.lis.histopathology.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.OutSourceMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.common.dto.CommonAutoCompleteDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.global.common.StringCommonMethods;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.microbiology.transaction.dao.IincubationObservationDao;
import com.param.lis.microbiology.transaction.dto.MicrobioResultEntryMasterDto;
import com.param.lis.transaction.dto.SampleForInOutPatient;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class OutSourceMasterDaoImpl extends GenericDao<OutSourceMaster, Integer>
implements IOutSourceMasterDao, ICommonConstants, IError,ITransactionConstants
{
	
	final static Logger logger = Logger.getLogger(OutSourceMasterDaoImpl.class);
	
	public OutSourceMasterDaoImpl() 
	{
		super(OutSourceMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;


	@Autowired
	IincubationObservationDao iincubationObservationDao;

	
	@Override
	public Response listOutSourceMaster(OutSourceMasterDto outSourceMasterDto) throws ApplicationException {
		try
		{
			List<OutSourceMasterDto> listOutSourceMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_OUT_SOURCE_PENDING_LIST")
					.setInteger("orgId", outSourceMasterDto.getOrgId())
					.setInteger("orgUnitId", outSourceMasterDto.getOrgUnitId())
					.setParameterList("sampleStatusIds", OUT_SOURCED_STATUS)
					.setFirstResult(outSourceMasterDto.getOffset() != null ? outSourceMasterDto.getOffset() : 0)
					.setMaxResults(outSourceMasterDto.getRecordPerPage() != null ? outSourceMasterDto.getRecordPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(OutSourceMasterDto.class)).list();
			
			if(listOutSourceMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, "No Out Source Records Found.", null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listOutSourceMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getOutSourceMasterCount(OutSourceMasterDto outSourceMasterDto) throws ApplicationException {
		try
		{
			BigInteger outSourceMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OUT_SOURCE_PENDING_COUNT")
					.setInteger("orgId", outSourceMasterDto.getOrgId())
					.setInteger("orgUnitId", outSourceMasterDto.getOrgUnitId())
					.setParameterList("sampleStatusIds", OUT_SOURCED_STATUS)
					.uniqueResult();
			if (outSourceMasterCount!=null)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, outSourceMasterCount);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	

	@Override
	public Response saveOutSourceMasterDetails(OutSourceMasterDto outSourceMasterDto) throws ApplicationException 
	{
		try
		{
			OutSourceMaster outSourceMst = mapper.map(outSourceMasterDto, OutSourceMaster.class,
					"OutSourceMasterDtoToOutSourceMaster");
			outSourceMst.setAccPayableMstId(2);
			OutSourceMaster outSourceMasterDtoObj = save(outSourceMst);
			if (outSourceMasterDtoObj != null)
			{
				return new Response(SUCCESS, SUCCESS_200, "Out Source Details Added Successfully.", null, null);
				
			} else
			{
				return new Response(ERROR, ERR_500, "Failed to Added Out Source Details.", null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		
	
				
	}

	@Override
	public Response searchOutSourcelistPatient(SearchCommonDto searchCommonDto) throws ApplicationException {
		List<CommonAutoCompleteDto>  patientList  = null; 
		try 
		{
			patientList = (List<CommonAutoCompleteDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("OUT_SOURCE_PAITENT_SEARCH_LIST")
					.setInteger("orgId", searchCommonDto.getOrgId())
					.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
					.setParameterList("sampleStatus", searchCommonDto.getSampleStatusIds())
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
	public Response getFilteredOutSourceList(SearchDto searchDto) throws ApplicationException {
		 try
		    {
		        
			 String getOutSourceListQuery =sessionFactory.getCurrentSession().getNamedQuery("GET_PAGINATED_OUT_SOURCE_PENDING_LIST").getQueryString().toString();
		        
		     	        
		        StringBuilder getPendingOutSourceListSearchQueryPart =new StringBuilder(getOutSourceListQuery);
			       
		        
		        if(searchDto.getFromDate()!=null && !searchDto.getFromDate().isEmpty())
		        {
		       /*   getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.getFromDate()+"' ");*/
		        	getPendingOutSourceListSearchQueryPart.append(" AND lbsampledtls.sample_accept_datetime > '"+searchDto.getFromDate()+"' ");
		        }
		        
		        if(searchDto.getToDate()!=null && !searchDto.getToDate().isEmpty())
		        {
		         /* getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.getToDate()+"' ");*/
		        	getPendingOutSourceListSearchQueryPart.append(" AND lbsampledtls.sample_accept_datetime < '"+searchDto.getToDate()+"' ");
		        }
		        
		        if(searchDto.getPatientId()!=null &&searchDto.getPatientId() >0)
		        	getPendingOutSourceListSearchQueryPart.append(" AND pati_mst.patient_id = "+searchDto.getPatientId());
		        
		        if(searchDto.getVisitTypes()!=null && searchDto.getVisitTypes().size()>0)
		        	getPendingOutSourceListSearchQueryPart.append(" AND visit_type_master.visit_type_id IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
		        
		        if(searchDto.getTestTypes()!=null && searchDto.getTestTypes().size()>0)
		        	getPendingOutSourceListSearchQueryPart.append(" AND lbsampledtls.test_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getTestTypes())+")");
		      
		          
		        Query createdQuery=sessionFactory.getCurrentSession().createSQLQuery(getPendingOutSourceListSearchQueryPart.toString());
		        
		        List<OutSourceMasterDto> listOutSourceMasterDto = createdQuery
		        		.setInteger("orgId", searchDto.getOrgId())
						.setInteger("orgUnitId", searchDto.getOrgUnitId())
						.setInteger("sampleStatusId", SAMPLE_COLLECTED)
		            .setResultTransformer(Transformers.aliasToBean(OutSourceMasterDto.class)).list();
		        
		        
		        if(listOutSourceMasterDto.isEmpty())
		        {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,"No Records Found.",listOutSourceMasterDto , null);
		        }
		        else {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,null, listOutSourceMasterDto, null);
		        }
		       
		        
		    } catch (Exception e)
		    {
		        logger.error("Exection", e);
		        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		    }
	}
	
	
	@Override
	public Response getFilteredOutSourceCommonList(SearchDto searchDto) throws ApplicationException {
		 try
		    {
		        
			 String getPendingOutSourceListQuery =sessionFactory.getCurrentSession().getNamedQuery("GET_PENDING_OUT_SOURCE_SAMPLE_DISPATCH_LIST").getQueryString().toString();
		        
		     	        
		        StringBuilder getPendingOutSourceListSearchQueryPart =new StringBuilder(getPendingOutSourceListQuery);
			       
		        /*
		        if(searchDto.getFromDate()!=null && !searchDto.getFromDate().isEmpty())
		        {
		          //getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.getFromDate()+"' ");
		        }
		        getPendingOutSourceListSearchQueryPart.append(" AND lab_sample_dtls.sample_accept_datetime > '"+searchDto.getFromDate()+"' ");
		        
		        if(searchDto.getToDate()!=null && !searchDto.getToDate().isEmpty())
		        {
		          //getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.getToDate()+"' ");
		        	getPendingOutSourceListSearchQueryPart.append(" AND lab_sample_dtls.sample_accept_datetime < '"+searchDto.getToDate()+"' ");
		        }*/
		        
		        if(searchDto.getPatientId()!=null &&searchDto.getPatientId() >0)
		        	getPendingOutSourceListSearchQueryPart.append(" AND pati_mst.patient_id = "+searchDto.getPatientId());
		        
		        if(searchDto.getVisitTypes()!=null && searchDto.getVisitTypes().size()>0)
		        	getPendingOutSourceListSearchQueryPart.append(" AND visit_type_master.visit_type_id IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
		        
		        if(searchDto.getTestTypes()!=null && searchDto.getTestTypes().size()>0)
		        	getPendingOutSourceListSearchQueryPart.append(" AND lab_sample_dtls.test_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getTestTypes())+")");
		      
		          
		        Query createdQuery=sessionFactory.getCurrentSession().createSQLQuery(getPendingOutSourceListSearchQueryPart.toString());
		        
		        List<OutSourceMasterDto> listOutSourceMasterDto = createdQuery
		        		.setInteger("orgId", searchDto.getOrgId())
						.setInteger("orgUnitId", searchDto.getOrgUnitId())
						.setInteger("sampleStatusId", searchDto.getSampleStatusId())
		            .setResultTransformer(Transformers.aliasToBean(OutSourceMasterDto.class)).list();
		        
		        
		        if(listOutSourceMasterDto.isEmpty())
		        {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,"No Records Found.",listOutSourceMasterDto , null);
		        }
		        else {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,null, listOutSourceMasterDto, null);
		        }
		       
		        
		    } catch (Exception e)
		    {
		        logger.error("Exection", e);
		        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		    }
	}

	

}
