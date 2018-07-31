package com.param.lis.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.transaction.LabSampleMaster;
import com.param.lis.common.dto.CommonAutoCompleteDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.global.common.StringCommonMethods;
import com.param.lis.transaction.dto.CollectedSampleDto;
import com.param.lis.transaction.dto.LabDashBoardDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.LabSampleMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class LabTransactionDaoImpl extends GenericDao<LabSampleMaster, Integer>
		implements ILabTransactionDao, ICommonConstants,ITransactionConstants, IError
{

	@Autowired
	private SessionFactory sessionFactory;
	
	public LabTransactionDaoImpl()
	{
		super(LabSampleMaster.class);
	}
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private ILabCentralReceivingDao iLabCentralReceivingDao;
	
	
	final static Logger logger = Logger.getLogger(LabTransactionDaoImpl.class);

	@Override
	public Response getPhlebotomyWorklist(Integer orgId, Integer orgUnitId,Integer deptId,Integer offset, Integer recordPerPage) throws ApplicationException
	{

		List<LabSampleMasterDto> listLabSampleMasterDto = null;
		try
		{
			listLabSampleMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_ORDERS")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("visitTypeId", OP)
					.setParameterList("orderStatusId", RENDER_CANCEL)
					.setInteger("serviceRendered", 0)
					.setInteger("serviceIsBilled", 1)
					.setParameterList("serviceRenderingDeptIds", LAB_SUB_DEPTS_ORDER)
					.setInteger("patientStatusId", REPAT_PATIRNT)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(LabSampleMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, listLabSampleMasterDto, null);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@Override
	public Response getTotalPhlobotomyRecord(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException
	{
		try
		{
			BigInteger phlebotomyTotalRecordCountOne = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("REPEAT_SAMPLE_PATIENT_ARRIVAL_COUNT")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("visitTypeId", OP)
					.setInteger("patientStatusId", REPAT_PATIRNT)
					.setInteger("orderCancelStatus", CANCEL)
					.uniqueResult();
			
			BigInteger phlebotomyTotalRecordCountTwo = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("REGULAR_PATIENT_ORDER_COUNT")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("visitTypeId", OP)
					.setInteger("patientStatusId", REPAT_PATIRNT)
					.setInteger("orgUnitId",orgUnitId)
					.setParameterList("orderStatusId", RENDER_CANCEL)
					.setParameterList("serviceRenderingDeptIds", LAB_SUB_DEPTS_ORDER)
					.uniqueResult();
			if(phlebotomyTotalRecordCountOne==null){
				phlebotomyTotalRecordCountOne=BigInteger.ZERO;
			}
			if(phlebotomyTotalRecordCountTwo==null){
				phlebotomyTotalRecordCountTwo=BigInteger.ZERO;
			}
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, phlebotomyTotalRecordCountOne.add(phlebotomyTotalRecordCountTwo));
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@Override
	public Response getPhlebotomyWorklistDetails(Integer orgId, Integer orgUnitId, Integer orderId,Integer deptId)
			throws ApplicationException
	{

		List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto = null;
		try
		{
			listLabSampleDetailsMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_ORDER_DETAILS")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("orderId", orderId)
					.setCharacter("ordCancelled", 'N')
					.setInteger("serviceIsBilled", 1)
					.setCharacter("sampleStatus", 'A')
					.setCharacter("unitStatus", 'A')
					.setInteger("serviceRendered", 0)
					.setParameterList("serviceRenderingDeptIds", LAB_SUB_DEPTS_ORDER)
					.setInteger("sampleStatusId", SAMPLE_RECOLLECTED)
					.setInteger("patientStatusId", REPAT_PATIRNT)
					.setResultTransformer(Transformers.aliasToBean(LabSampleDetailsMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, listLabSampleDetailsMasterDto, null);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	
	@Override
	public Response sampleCollection(LabSampleMasterDto labSampleMasterDto) throws ApplicationException
	{
		try
		{
			if(labSampleMasterDto!=null)
			{
				
				LabSampleMaster labSampleMaster = mapper.map(labSampleMasterDto, LabSampleMaster.class,
						"LabSampleMasterDtoTOLabSampleMaster");
				if(labSampleMasterDto.getOrderStatusId() == PENDING)
				{
					
					
					LabSampleMaster labSampleMst = save(labSampleMaster);
					if (labSampleMst != null)
					{
						for (Iterator iterator = labSampleMst.getListLabSampleDetailsMaster().iterator(); iterator.hasNext();)
						{
						  BigInteger sampleNo = (BigInteger) sessionFactory.getCurrentSession().getNamedQuery("GENERATE_SAMPLE_NO").uniqueResult();
							LabSampleDetailsMaster labSampleDetailsMaster = (LabSampleDetailsMaster) iterator
									.next();
							labSampleDetailsMaster.setSampleNo(sampleNo);
							labSampleDetailsMaster.setSampleStatusId(SAMPLE_COLLECTED);
							labSampleDetailsMaster.setLabSampleId(labSampleMst.getLabSampleId());
							labSampleDetailsMaster.setSampleCollectionDatetime(new Date().getTime());
							labSampleDetailsMaster.setSampleRecollectFlag('N');
							labSampleDetailsMaster.setSampleGenDatetime(new Date().getTime());
							labSampleDetailsMaster.setSamplePendingCount(labSampleDetailsMaster.getSampleReqCount());
							labSampleDetailsMaster.setGenderId(labSampleMasterDto.getGenderId());
							labSampleDetailsMaster.setPatientVisitAge(new Date().getTime());
							labSampleDetailsMaster.setCreatedBy(labSampleMaster.getCreatedBy());
							labSampleDetailsMaster.setSampleCollectionBy(labSampleMaster.getCreatedBy());
							Response<LabSampleDetailsMaster, Integer> labSampleDetailsRes = iLabCentralReceivingDao.samplCollection(labSampleDetailsMaster);
							if(labSampleDetailsRes.getStatus().equals(SUCCESS))
							{
								this.updateServiceRenderStatus(labSampleDetailsMaster.getOrderDetailsId());
							}
							else{
								return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
							}
						}
						Long checkOrderStatus = (Long) sessionFactory.getCurrentSession().getNamedQuery("CHECK_ORDER_STATUS")
								.setInteger("orgId", labSampleMasterDto.getOrgId())
								.setInteger("orgUnitId", labSampleMasterDto.getOrgUnitId())
								.setInteger("orderId", labSampleMasterDto.getOrderId())
								.setInteger("serviceRendered", 0)
								.setInteger("serviceIsBilled", 1)
								.setParameterList("orderStatus", RENDER_CANCEL)
								.setInteger("serviceRenderingDeptId", 8)
								.uniqueResult();
						
						if(checkOrderStatus>0)
						{
							this.updateOrderStatus(labSampleMasterDto.getOrderId(), INPROCESS);
						}
						else
						{
							this.updateOrderStatus(labSampleMasterDto.getOrderId(), RENDER);
						}
						return new Response(SUCCESS, SUCCESS_200, SAMPLE_COLLECT_SUCC, null, null);
					} 
				}
				else if(labSampleMasterDto.getOrderStatusId() == INPROCESS)
				{
					
					Integer labSampleId = (Integer) sessionFactory.getCurrentSession()
							.getNamedQuery("GET_LAB_SAMPLE_ID")
							.setInteger("orgId", labSampleMasterDto.getOrgId())
							.setInteger("orgUnitId",labSampleMasterDto.getOrgUnitId())
							.setInteger("orderId",labSampleMasterDto.getOrderId()).uniqueResult();
					if (labSampleId != null)
					{
					  BigInteger sampleNo = (BigInteger) sessionFactory.getCurrentSession().getNamedQuery("GENERATE_SAMPLE_NO").uniqueResult();
						for (Iterator iterator = labSampleMaster.getListLabSampleDetailsMaster().iterator(); iterator.hasNext();)
						{
							LabSampleDetailsMaster labSampleDetailsMaster = (LabSampleDetailsMaster) iterator
									.next();
							labSampleDetailsMaster.setSampleNo(sampleNo);
							labSampleDetailsMaster.setSampleStatusId(SAMPLE_COLLECTED);
							labSampleDetailsMaster.setLabSampleId(labSampleId);
							labSampleDetailsMaster.setSampleRecollectFlag('N');
							labSampleDetailsMaster.setSampleCollectionDatetime(new Date().getTime());
							labSampleDetailsMaster.setSampleGenDatetime(new Date().getTime());
							labSampleDetailsMaster.setSamplePendingCount(labSampleDetailsMaster.getSampleReqCount());
							labSampleDetailsMaster.setGenderId(labSampleMasterDto.getGenderId());
							labSampleDetailsMaster.setPatientVisitAge(new Date().getTime());
							labSampleDetailsMaster.setCreatedBy(labSampleMaster.getCreatedBy());
                            labSampleDetailsMaster.setSampleCollectionBy(labSampleMaster.getCreatedBy());
							Response<LabSampleDetailsMaster, Integer> labSampleDetailsRes = iLabCentralReceivingDao.samplCollection(labSampleDetailsMaster);
							if(labSampleDetailsRes.getStatus().equals(SUCCESS))
							{
								this.updateServiceRenderStatus(labSampleDetailsMaster.getOrderDetailsId());
							}
							else{
								return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
							}
						}
						Long checkOrderStatus = (Long) sessionFactory.getCurrentSession().getNamedQuery("CHECK_ORDER_STATUS")
								.setInteger("orgId", labSampleMasterDto.getOrgId())
								.setInteger("orgUnitId", labSampleMasterDto.getOrgUnitId())
								.setInteger("orderId", labSampleMasterDto.getOrderId())
								.setInteger("serviceRendered", 0)
								.setInteger("serviceIsBilled", 1)
								.setParameterList("orderStatus", RENDER_CANCEL)
								.setInteger("serviceRenderingDeptId", 8)
								.uniqueResult();
						
						if(checkOrderStatus>0)
						{
							this.updateOrderStatus(labSampleMasterDto.getOrderId(),INPROCESS);
						}
						else
						{
							this.updateOrderStatus(labSampleMasterDto.getOrderId(),RENDER );
						}
						return new Response(SUCCESS, SUCCESS_200, SAMPLE_COLLECT_SUCC, null, null);
					} 
				}
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, null);
			}
			else{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getCollectedSample(Integer orgId, Integer orgUnitId,Integer deptId,Integer offset, Integer recordPerPage) throws ApplicationException
	{
		List<CollectedSampleDto> listCollectedSampleDto = null;
		try
		{
			listCollectedSampleDto = sessionFactory.getCurrentSession().getNamedQuery("GET_COLLECTED_SAMPLE_LIST")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("deptId", deptId)
					.setInteger("visitTypeId", OP)
					.setParameterList("sampleStatusIds", COLLECTED_SAMPLE_STATUS)
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(CollectedSampleDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, listCollectedSampleDto, null);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@Override
	public Response getTotalSendToCrRecord(Integer orgId, Integer unitId,Integer deptId)
			throws ApplicationException {
		try
		{
			BigInteger 	getTotalSendToCrRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNT_FROM_SEND_TO_CR_LIST")
					.setInteger("orgId", orgId)
					.setInteger("deptId", deptId)
					.setParameterList("sampleStatusIds", COLLECTED_SAMPLE_STATUS)
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
					.setInteger("visitTypeId", OP)
					.setInteger("orgUnitId", unitId).uniqueResult();
			if (getTotalSendToCrRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getTotalSendToCrRecordCount);
			} else
			{
				return new Response(ERROR, ERR_500, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response sendToCr(List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto) throws ApplicationException
	{
		try
		{
			Integer result = null;
			for (Iterator iterator = listLabSampleDetailsMasterDto.iterator(); iterator.hasNext();)
			{	
				LabSampleDetailsMasterDto labSampleDetailsMasterDto = (LabSampleDetailsMasterDto) iterator.next();
				LabSampleDetailsMaster labSampleDetailsMaster = mapper.map(labSampleDetailsMasterDto, LabSampleDetailsMaster.class,
						"LabSampleDetailsMasterDtoTOLabSampleDetailsMaster");
				labSampleDetailsMaster.setSampleSendtocrDatetime(new Date().getTime());
			    result = sessionFactory.getCurrentSession().getNamedQuery("SEND_SAMPLE_TO_CENTRAL_RECEIVING")
						.setInteger("sampleStatusId", SAMPLE_SEND_TO_CR)
						.setInteger("labSampleDtlsId", labSampleDetailsMaster.getLabSampleDtlsId())
						.setInteger("sampleSendToCrBy",labSampleDetailsMaster.getSampleSendtocrBy())
						.setDate("sampleSendtocrDatetime",new Date()).executeUpdate();
			}
			if(result>0){
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_SENDTOCR_SUCC, null, null);
			}
			else{
				return new Response(ERROR, ERR_500, SAMPLE_SENDTOCR_FAIL, null, null);
			}
			
		}catch(HibernateException exception){
			logger.error("Exception", exception);
		} catch (Exception e)
		{
			logger.error("Exception", e);
		}
		return new Response(ERROR, ERR_500, SAMPLE_SENDTOCR_FAIL, null, null);
	}

	

	
	@Override
	public Response updateOrderStatus(Integer orderId,Integer orderStatusId) throws ApplicationException {
		try
		{
			Integer orderUpdateSucess = (Integer) sessionFactory.getCurrentSession()
					.getNamedQuery("LAB_UPDATE_ORDER_MASTER_STATUS")
					.setInteger("orderStatusId", orderStatusId)
					.setInteger("orderId", orderId)
					.executeUpdate();
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, orderUpdateSucess);

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response updateServiceRenderStatus(Integer orderDetailsId) throws ApplicationException {
		try
		{
			Integer orderDetailsUpdateSucess = (Integer) sessionFactory.getCurrentSession()
					.getNamedQuery("LAB_UPDATE_ORDER_DETAILS_SERVICE")
					.setInteger("orderDetailsId", orderDetailsId)
					.setInteger("serviceRendered", 1)
					.executeUpdate();
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, orderDetailsUpdateSucess);

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	

	@Override
	public Response v1GetPhlebotomyWorklistDetails(Integer orgId, Integer orgUnitId, Integer orderId)
			throws ApplicationException 
	{
		List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto = null;
		try
		{
			listLabSampleDetailsMasterDto = sessionFactory.getCurrentSession().getNamedQuery("V1_GET_PHLEBOTOMY_WORKLIST_DETAILS")
					.setInteger("orgId", orgId).setInteger("orgUnitId", orgUnitId)
					.setInteger("orderId", orderId)
					.setResultTransformer(Transformers.aliasToBean(LabSampleDetailsMasterDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, listLabSampleDetailsMasterDto, null);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@Override
	public Response searchPhlebotomyWorklistPatient(SearchCommonDto searchCommonDto) throws ApplicationException {
		List<CommonAutoCompleteDto>  patientList  = null; 
		try 
		{
			patientList = (List<CommonAutoCompleteDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_PHLEBOTOMY_WORK_LIST_PATIENT")
					.setInteger("orgId", searchCommonDto.getOrgId())
					.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
					.setInteger("visitTypeId", searchCommonDto.getVisitTypeId())
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
	public Response getFilteredPhlebotomyWorklistPatient(SearchDto searchDto) throws ApplicationException {
		 try
		    {
		        
			 
			 String query =sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_ORDERS").getQueryString().toString();
		        
		        
		        String [] unionSplit = query.split("UNION", 2);
		        
		        String unionPart1 = unionSplit[0];
		        String unionPart2 = "UNION "+unionSplit[1];
		        
		        String [] gorupSplit = unionPart2.split("GROUP", 2);
		        String gorupPart1=gorupSplit[0];
		        String gorupPart2="GROUP "+gorupSplit[1];
		     
		        
		        StringBuilder getPhlebotomyWorkListSearchQueryPart1 =new StringBuilder(unionPart1);
		        
		        StringBuilder getPhlebotomyWorkListSearchQueryPart2 =new StringBuilder(gorupPart1);
		             
		        if(searchDto.getFromDate()!=null && !searchDto.getFromDate().isEmpty())
		        {
		       /*   getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.getFromDate()+"' ");*/
		        	getPhlebotomyWorkListSearchQueryPart1.append(" AND ord_mst.created_date > '"+searchDto.getFromDate()+"' ");
		        	getPhlebotomyWorkListSearchQueryPart2.append(" AND ord_mst.created_date > '"+searchDto.getFromDate()+"' ");
		        }
		        
		        if(searchDto.getToDate()!=null && !searchDto.getToDate().isEmpty())
		        {
		         /* getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.getToDate()+"' ");*/
		        	getPhlebotomyWorkListSearchQueryPart1.append(" AND ord_mst.created_date < '"+searchDto.getToDate()+"' ");
		        	getPhlebotomyWorkListSearchQueryPart2.append(" AND ord_mst.created_date < '"+searchDto.getToDate()+"' ");
		        }
		        
		        if(searchDto.getPatientId()!=null &&searchDto.getPatientId() >0)
		        	getPhlebotomyWorkListSearchQueryPart1.append(" AND pati_mst.patient_id = "+searchDto.getPatientId());
		            getPhlebotomyWorkListSearchQueryPart2.append(" AND pati_mst.patient_id = "+searchDto.getPatientId());
		        
		        if(searchDto.getVisitTypes()!=null && searchDto.getVisitTypes().size()>0)
		        	getPhlebotomyWorkListSearchQueryPart1.append(" AND visit_type_mst.visit_type_id IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
		        getPhlebotomyWorkListSearchQueryPart2.append(" AND visit_type_mst.visit_type_id IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
		          
		        getPhlebotomyWorkListSearchQueryPart1.append(getPhlebotomyWorkListSearchQueryPart2).append(gorupPart2);
		        
		        Query createdQuery=sessionFactory.getCurrentSession().createSQLQuery(getPhlebotomyWorkListSearchQueryPart1.toString());
		        
		        
		        List<LabSampleMasterDto> listLabSampleMasterDto = createdQuery
		        		.setInteger("orgId", searchDto.getOrgId())
						.setInteger("orgUnitId", searchDto.getOrgUnitId())
						.setInteger("visitTypeId", OP)
						.setParameterList("orderStatusId", RENDER_CANCEL)
						.setInteger("serviceRendered", 0)
						.setInteger("serviceIsBilled", 1)
						.setInteger("serviceRenderingDeptId", searchDto.getDeptId())
						.setInteger("patientStatusId", REPAT_PATIRNT)
		            .setResultTransformer(Transformers.aliasToBean(LabSampleMasterDto.class)).list();
		        
		        
		        if(listLabSampleMasterDto.isEmpty())
		        {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,"No Records Found.",listLabSampleMasterDto , null);
		        }
		        else {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,null, listLabSampleMasterDto, null);
		        }
		       
		        
		    } catch (Exception e)
		    {
		        logger.error("Exection", e);
		        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		    }
	}
	@Override
	public Response searchSendToCRPatientlist(SearchCommonDto searchCommonDto) throws ApplicationException {
		List<CommonAutoCompleteDto>  patientList  = null; 
		try 
		{
			patientList = (List<CommonAutoCompleteDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_SEND_TO_CR_LIST_PATIENT_GENRAL_LAB")
					.setInteger("orgId", searchCommonDto.getOrgId())
					.setInteger("sampleStatusId",searchCommonDto.getSampleStatusId())
					.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
					.setInteger("visitTypeId", searchCommonDto.getVisitTypeId())
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
	public Response getFilteredSendToCRPatientlist(SearchDto searchDto) throws ApplicationException {
		 try
		    {
		        
			 String getSendToCRQuery =sessionFactory.getCurrentSession().getNamedQuery("GET_COLLECTED_SAMPLE_LIST").getQueryString().toString();
		        
		        String [] orderSplit = getSendToCRQuery.split("ORDER", 2);
		        
		        String orderPart1 = orderSplit[0];
		        String orderPart2 = "ORDER "+orderSplit[1];
		        
		        
		        StringBuilder getSendToCRSearchQueryPart1 =new StringBuilder(orderPart1);
			       
		        
		        if(searchDto.getFromDate()!=null && !searchDto.getFromDate().isEmpty())
		        {
		       /*   getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.getFromDate()+"' ");*/
		        	getSendToCRSearchQueryPart1.append(" AND lbsampledtls.sample_collection_datetime > '"+searchDto.getFromDate()+"' ");
		        }
		        
		        if(searchDto.getToDate()!=null && !searchDto.getToDate().isEmpty())
		        {
		         /* getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.getToDate()+"' ");*/
		        	getSendToCRSearchQueryPart1.append(" AND lbsampledtls.sample_collection_datetime < '"+searchDto.getToDate()+"' ");
		        }
		        
		        if(searchDto.getPatientId()!=null &&searchDto.getPatientId() >0)
		        	getSendToCRSearchQueryPart1.append(" AND pati_mst.patient_id = "+searchDto.getPatientId());
		        
		        if(searchDto.getVisitTypes()!=null && searchDto.getVisitTypes().size()>0)
		        	getSendToCRSearchQueryPart1.append(" AND visit_type_master.visit_type_id IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
		        
		        if(searchDto.getSubDepts()!=null && searchDto.getSubDepts().size()>0)
		        	getSendToCRSearchQueryPart1.append(" AND test_master.dept_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getSubDepts())+")");
		      
		        if(searchDto.getLabSampleDtlsId()!=null && searchDto.getLabSampleDtlsId()>0)
		        	getSendToCRSearchQueryPart1.append(" AND lbsampledtls.lab_sample_dtls_id ="+searchDto.getLabSampleDtlsId());
		        
		        StringBuilder getSendToCRSearchQueryPart2 =new StringBuilder(orderPart2);
		          getSendToCRSearchQueryPart1.append(getSendToCRSearchQueryPart2);
		          
		        Query createdQuery=sessionFactory.getCurrentSession().createSQLQuery(getSendToCRSearchQueryPart1.toString());
		        
		        
		        List<CollectedSampleDto> listCollectedSampleDto = createdQuery
		        	.setInteger("orgId", searchDto.getOrgId())
					.setInteger("orgUnitId", searchDto.getOrgUnitId())
					.setInteger("deptId", searchDto.getDeptId())
					.setInteger("visitTypeId", OP)
					.setParameterList("sampleStatusIds", COLLECTED_SAMPLE_STATUS)
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
		            .setResultTransformer(Transformers.aliasToBean(CollectedSampleDto.class)).list();
		        
		        
		        if(listCollectedSampleDto.isEmpty())
		        {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,"No Records Found.",listCollectedSampleDto , null);
		        }
		        else {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,null, listCollectedSampleDto, null);
		        }
		       
		        
		    } catch (Exception e)
		    {
		        logger.error("Exection", e);
		        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		    }
		   
	}

	@Override
	public Response searchLabGenralLabSampleNo(SearchCommonDto searchCommonDto) throws ApplicationException {
		List<LabDashBoardDto>  patientList  = null; 
		try
		{
			patientList = (List<LabDashBoardDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_SAMPLE_NO_GENRAL_LAB_LIST")
					.setInteger("orgId", searchCommonDto.getOrgId())
					.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
					.setString("searchKeyword", "%"+searchCommonDto.getSearchKeyword().trim().toLowerCase().trim()+"%")
					.setInteger("sampleStatusId", searchCommonDto.getSampleStatusId())
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
