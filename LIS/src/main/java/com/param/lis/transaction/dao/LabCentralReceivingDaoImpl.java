package com.param.lis.transaction.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.global.common.StringCommonMethods;
import com.param.lis.transaction.dto.CollectedSampleDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.RejectedSampleDto;
import com.param.lis.transaction.dto.SampleForInOutPatient;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class LabCentralReceivingDaoImpl extends GenericDao<LabSampleDetailsMaster, Integer>
		implements ILabCentralReceivingDao, ICommonConstants, ITransactionConstants, IError
{
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private IPatientArrivalDao iPatientArrivalDao;

	public LabCentralReceivingDaoImpl() {
		super(LabSampleDetailsMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(LabCentralReceivingDaoImpl.class);

	@Override
	public Response getSampleListForInOutPatient(Integer orgId, Integer orgUnitId,Integer visitTypeId, Integer deptId, Integer offset,
			Integer recordPerPage) throws ApplicationException
	{
		List<SampleForInOutPatient> listSampleForInOutPatient = null;
		try
		{
			if (visitTypeId == OP)
			{
				listSampleForInOutPatient = sessionFactory.getCurrentSession()
						.getNamedQuery("COLLECT_SAMPLE_OUT_PATIENT")
						.setInteger("orgId", orgId)
						.setInteger("orgUnitId", orgUnitId)
						.setInteger("deptId", deptId)
						.setInteger("sampleStatusId", SAMPLE_SEND_TO_CR)
						.setInteger("visitTypeId", visitTypeId).setFirstResult(offset != null ? offset : 0)
						.setMaxResults(recordPerPage != null ? recordPerPage : 10)
						.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
						.setResultTransformer(Transformers.aliasToBean(SampleForInOutPatient.class)).list();
				return new Response(SUCCESS, SUCCESS_200, null, listSampleForInOutPatient, null);
			} else if (visitTypeId == IP)
			{
				listSampleForInOutPatient = sessionFactory.getCurrentSession()
						.getNamedQuery("COLLECT_SAMPLE_IN_PATIENT").setInteger("orgId", orgId)
						.setInteger("orgUnitId", orgUnitId).setInteger("sampleStatusId", SAMPLE_SEND_TO_CR)
						.setInteger("visitTypeId", visitTypeId).setFirstResult(offset != null ? offset : 0)
						.setMaxResults(recordPerPage != null ? recordPerPage : 10)
						.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
						.setResultTransformer(Transformers.aliasToBean(SampleForInOutPatient.class)).list();
				return new Response(SUCCESS, SUCCESS_200, null, listSampleForInOutPatient, null);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response acceptOrRejectSample(List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto)
			throws ApplicationException
	{
		try
		{
			if (!listLabSampleDetailsMasterDto.isEmpty())
			{
				LabSampleDetailsMaster lSampleDetailsMaster;
				for (Iterator iterator = listLabSampleDetailsMasterDto.iterator(); iterator.hasNext();)
				{
					LabSampleDetailsMasterDto labSampleDetailsMasterDto = (LabSampleDetailsMasterDto) iterator.next();
					LabSampleDetailsMaster labSampleDetailsMst = findById(
							labSampleDetailsMasterDto.getLabSampleDtlsId());
					if (labSampleDetailsMasterDto.getCurrStatus().equals(ACCEPT))
					{
						labSampleDetailsMst.setSampleAcceptBy(labSampleDetailsMasterDto.getSampleAcceptBy());
						labSampleDetailsMst.setSamplePendingCount(labSampleDetailsMasterDto.getSamplePendingCount() > 0
								? (labSampleDetailsMasterDto.getSamplePendingCount() - 1) : 0);
						labSampleDetailsMst.setSampleAcceptDatetime(new Date().getTime());
						labSampleDetailsMst.setUpdatedBy(labSampleDetailsMasterDto.getUpdatedBy());
						labSampleDetailsMst.setUpdatedDate(new Date().getTime());
						if(labSampleDetailsMasterDto.getIsCentrifugationReq()=='Y')
						{
						
							labSampleDetailsMst.setSampleStatusId(OP_IP_ACCEPTED);
							lSampleDetailsMaster = update(labSampleDetailsMst);
							
						}else{
							
							labSampleDetailsMst.setSampleStatusId(SAMPLE_SEND_TO_DEPT);
							lSampleDetailsMaster = update(labSampleDetailsMst);
						}
					
						if (lSampleDetailsMaster != null)
						{
							iPatientArrivalDao.patientNotArrivalData(lSampleDetailsMaster);
							continue;
						} else
						{
							return new Response(ERROR, ERR_500, SAMPLE_ACCEPT_FAIL, null, null);
						}
					} else if (labSampleDetailsMasterDto.getCurrStatus().equals(REJECT))
					{

						labSampleDetailsMst
								.setSampleRejectReasonId(labSampleDetailsMasterDto.getSampleRejectReasonId());
						labSampleDetailsMst.setSampleRejectReason(labSampleDetailsMasterDto.getSampleRejectReason());
						labSampleDetailsMst.setSampleRejectBy(labSampleDetailsMasterDto.getSampleRejectBy());
					    labSampleDetailsMst.setSampleRejectDatetime(new Date().getTime());
						labSampleDetailsMst.setSampleStatusId(OP_IP_REJECTED);
						lSampleDetailsMaster = update(labSampleDetailsMst);
						if (lSampleDetailsMaster != null)
						{
							continue;
						} else
						{
							return new Response(ERROR, ERR_500, SAMPLE_ACCEPT_FAIL, null, null);
						}
					}
				}
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_ACCEPT_SUCC, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return null;

	}

	@Override
	public Response getCentrifugationWorklist(Integer orgId, Integer orgUnitId,Integer deptId ,Integer offset, Integer recordPerPage)
			throws ApplicationException
	{
		List<SampleForInOutPatient> centrifugationWorkList = null;
		try
		{
			centrifugationWorkList = sessionFactory.getCurrentSession().getNamedQuery("CENTRIFUGATION_WORK_LIST")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
					.setInteger("deptId", deptId)
					.setInteger("sampleStatusId", OP_IP_ACCEPTED)
					.setCharacter("isCentrifugationReq", 'Y')
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
					.setResultTransformer(Transformers.aliasToBean(SampleForInOutPatient.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, centrifugationWorkList, null);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getTotalRecordOutPatient(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException
	{
		try
		{
			BigInteger getTotalRecordForOutPatient = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNT_FOR_OUT_PATIENT")
					.setInteger("orgId", orgId)
					.setInteger("deptId", deptId)
					.setInteger("visitTypeId", OP)
					.setInteger("sampleStatusId", SAMPLE_SEND_TO_CR)
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
					.setInteger("orgUnitId", orgUnitId).uniqueResult();
			if (getTotalRecordForOutPatient.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getTotalRecordForOutPatient);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getTotalcentrifugationWorkList(
			Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException
	{
		try
		{
			BigInteger getTotalCentrifugationWorkCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNT_CEONTRIFUGATION_WORKLIST")
					.setInteger("orgId", orgId)
					.setInteger("deptId", deptId)
					.setInteger("sampleStatusId", OP_IP_ACCEPTED)
					.setCharacter("isCentrifugationReq", 'Y')
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
					.setInteger("orgUnitId", orgUnitId).uniqueResult();

			if (getTotalCentrifugationWorkCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getTotalCentrifugationWorkCount);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getTotalRecordINPatient(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException
	{
		try
		{
			BigInteger getTotalRecordForOutPatient = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNT_FOR_IN_PATIENT")
					.setInteger("orgId", orgId)
					.setInteger("deptId", deptId)
					.setInteger("visitTypeId", IP)
					.setInteger("sampleStatusId", SAMPLE_SEND_TO_CR)
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
					.setInteger("orgUnitId", orgUnitId).uniqueResult();
			if (getTotalRecordForOutPatient.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getTotalRecordForOutPatient);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getRejectedSampleList(Integer orgId, Integer orgUnitId, Integer deptId,Integer offset, Integer recordPerPage)
			throws ApplicationException
	{
		List<RejectedSampleDto> listRejectedSampleDto = null;
		try
		{
			listRejectedSampleDto = sessionFactory.getCurrentSession().getNamedQuery("GET_REJECTED_SAMPLE_LIST")
					.setInteger("orgId", orgId).setInteger("orgUnitId", orgUnitId)
					.setInteger("deptId", deptId)
					/*.setInteger("sampleStatusId", SAMPLE_REJECTED)*/
					.setParameterList("sampleStatusIds", REJECTED_SAMPLE_IDS)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(RejectedSampleDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, listRejectedSampleDto, null);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getTotalRecordRejectedSample(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException
	{
		try
		{
			BigInteger getTotalRecordForRejectedSample = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_COUNT_FOR_REJECTED_SAMPLE")
					.setInteger("orgId", orgId)
					.setInteger("deptId", deptId)
				/*	.setInteger("sampleStatusId", SAMPLE_REJECTED)* */
					.setParameterList("sampleStatusIds", REJECTED_SAMPLE_IDS)
					.setInteger("orgUnitId", orgUnitId).uniqueResult();
			if (getTotalRecordForRejectedSample.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, getTotalRecordForRejectedSample);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response sampleRecollect(List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto)
			throws ApplicationException
	{
		try
		{
			if (listlabLabSampleDetailsMasterDto != null)
			{
				for (Iterator iterator = listlabLabSampleDetailsMasterDto.iterator(); iterator.hasNext();)
				{

					LabSampleDetailsMasterDto labSampleDetailsMasterDto = (LabSampleDetailsMasterDto) iterator.next();
					BigInteger sampleNo = (BigInteger) sessionFactory.getCurrentSession()
							.getNamedQuery("GENERATE_SAMPLE_NO").uniqueResult();
					LabSampleDetailsMaster labSampleDetailsMaster = mapper.map(labSampleDetailsMasterDto,
							LabSampleDetailsMaster.class, "LabSampleDetailsMasterDtoTOLabSampleDetailsMaster");
					labSampleDetailsMaster.setSampleNo(sampleNo);
					labSampleDetailsMaster.setLabSampleDtlsId(0);
					labSampleDetailsMaster.setSampleStatusId(SAMPLE_COLLECTED);
					labSampleDetailsMaster.setSampleRecollectFlag('Y');
					labSampleDetailsMaster.setCreatedDate(new Date().getTime());
					labSampleDetailsMaster.setSampleRecollectAgainstId(labSampleDetailsMasterDto.getLabSampleDtlsId());
					labSampleDetailsMaster.setSampleCollectionDatetime(new Date().getTime());
					labSampleDetailsMaster.setSampleGenDatetime(new Date().getTime());
					labSampleDetailsMaster.setGenderId(labSampleDetailsMasterDto.getGenderId());
					labSampleDetailsMaster.setSampleCollectionBy(labSampleDetailsMasterDto.getSampleCollectionBy());
					labSampleDetailsMaster.setIsAlliquoteReq(labSampleDetailsMasterDto.getIsAlliquoteReq());
					labSampleDetailsMaster.setSamplePendingCount(labSampleDetailsMaster.getSampleReqCount());
					labSampleDetailsMaster.setPatientVisitAge(new Date().getTime());
					LabSampleDetailsMaster LabSmpleDetailsMstr = save(labSampleDetailsMaster);
					if (LabSmpleDetailsMstr != null)
					{
						Integer sampleResult = sessionFactory.getCurrentSession()
								.getNamedQuery("UPDATE_SAMPLE_AFTER_RECOLLECTION")
								.setCharacter("sampleRecollectFlag", 'N')
								.setInteger("sampleStatusId", SAMPLE_RECOLLECTED)
								.setInteger("labSampleDtlsId", labSampleDetailsMasterDto.getLabSampleDtlsId())
								.executeUpdate();
						if (sampleResult > 0)
						{
							continue;
						} else
						{
							return new Response(SUCCESS, SUCCESS_200, SAMPLE_RECOLLECT_FAIL, null, null);
						}
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, SAMPLE_RECOLLECT_FAIL, null, null);
					}
				}
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_RECOLLECT_SUCC, null, null);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			}
		} catch (

		Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response sendToDepartMent(List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto)
			throws ApplicationException
	{
		try
		{
			if (listlabLabSampleDetailsMasterDto != null)
			{
				for (Iterator iterator = listlabLabSampleDetailsMasterDto.iterator(); iterator.hasNext();)
				{

					LabSampleDetailsMasterDto labSampleDetailsMasterDto = (LabSampleDetailsMasterDto) iterator.next();
					LabSampleDetailsMaster labSampleDetailsMaster = findById(
							labSampleDetailsMasterDto.getLabSampleDtlsId());
					labSampleDetailsMaster.setCreatedBy(labSampleDetailsMasterDto.getCreatedBy());
					labSampleDetailsMaster.setCreatedDate(new Date().getTime());
					labSampleDetailsMaster.setSampleStatusId(SAMPLE_SEND_TO_DEPT);
					labSampleDetailsMaster.setSampleCentrifugationDatetime(labSampleDetailsMasterDto.getSampleCentrifugationDatetime());
					labSampleDetailsMaster.setSampleCentrifugationBy(labSampleDetailsMasterDto.getSampleCentrifugationBy());
					labSampleDetailsMaster.setSampWrkStatusId(labSampleDetailsMasterDto.getSampWrkStatusId());
					update(labSampleDetailsMaster);
				}
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_SEND_TO_DEPT_SUCC, null, null);
			} else
			{
				return new Response(ERROR, ERR_500, SAMPLE_SEND_TO_DEPT_FAIL, null, null);
			}
		} catch (

		Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response samplCollection(LabSampleDetailsMaster labSampleDetailsMaster) throws ApplicationException
	{
		try
		{
			
			LabSampleDetailsMaster labSmpDetailsMst = save(labSampleDetailsMaster);
			if (labSmpDetailsMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, labSmpDetailsMst);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			}
		} catch (

		Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getFilteredOutPatientList(SearchDto searchDto) throws ApplicationException {
		 try
		    {
		        
			 String getOutPatientQuery =sessionFactory.getCurrentSession().getNamedQuery("COLLECT_SAMPLE_OUT_PATIENT").getQueryString().toString();
		        
		        String [] orderSplit = getOutPatientQuery.split("ORDER", 2);
		        
		        String orderPart1 = orderSplit[0];
		        String orderPart2 = "ORDER "+orderSplit[1];
		        
		        
		        StringBuilder getOutPatientSearchQueryPart1 =new StringBuilder(orderPart1);
			       
		        
		        if(searchDto.getFromDate()!=null && !searchDto.getFromDate().isEmpty())
		        {
		       /*   getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.getFromDate()+"' ");*/
		        	getOutPatientSearchQueryPart1.append(" AND lbsampledtls.sample_sendtocr_datetime > '"+searchDto.getFromDate()+"' ");
		        }
		        
		        if(searchDto.getToDate()!=null && !searchDto.getToDate().isEmpty())
		        {
		         /* getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.getToDate()+"' ");*/
		        	getOutPatientSearchQueryPart1.append(" AND lbsampledtls.sample_sendtocr_datetime < '"+searchDto.getToDate()+"' ");
		        }
		        
		        if(searchDto.getPatientId()!=null &&searchDto.getPatientId() >0)
		        	getOutPatientSearchQueryPart1.append(" AND pati_mst.patient_id = "+searchDto.getPatientId());
		        
		        if(searchDto.getVisitTypes()!=null && searchDto.getVisitTypes().size()>0)
		        	getOutPatientSearchQueryPart1.append(" AND visit_type_master.visit_type_id IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
		        
		        if(searchDto.getTestTypes()!=null && searchDto.getTestTypes().size()>0)
		        	getOutPatientSearchQueryPart1.append(" AND lbsampledtls.test_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getTestTypes())+")");
		      
		        
		        if(searchDto.getSubDepts()!=null && searchDto.getSubDepts().size()>0)
		        	getOutPatientSearchQueryPart1.append(" AND test_mst.dept_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getSubDepts())+")");
		      
		        if(searchDto.getLabSampleDtlsId()!=null && searchDto.getLabSampleDtlsId()>0)
		        	getOutPatientSearchQueryPart1.append(" AND lbsampledtls.lab_sample_dtls_id ="+searchDto.getLabSampleDtlsId());
		        
		        StringBuilder getOutPatientSearchQueryPart2 =new StringBuilder(orderPart2);
		        getOutPatientSearchQueryPart1.append(getOutPatientSearchQueryPart2);
		          
		        Query createdQuery=sessionFactory.getCurrentSession().createSQLQuery(getOutPatientSearchQueryPart1.toString());
		        
		        
		        List<SampleForInOutPatient> listSampleForInOutPatient = createdQuery
		        	.setInteger("orgId", searchDto.getOrgId())
					.setInteger("orgUnitId", searchDto.getOrgUnitId())
					.setInteger("sampleStatusId", SAMPLE_SEND_TO_CR)
					.setInteger("deptId", searchDto.getDeptId())
					.setInteger("visitTypeId", searchDto.getVisitTypeId())
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
		            .setResultTransformer(Transformers.aliasToBean(SampleForInOutPatient.class)).list();
		        
		        
		        if(listSampleForInOutPatient.isEmpty())
		        {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,"No Records Found.",listSampleForInOutPatient , null);
		        }
		        else {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,null, listSampleForInOutPatient, null);
		        }
		       
		        
		    } catch (Exception e)
		    {
		        logger.error("Exection", e);
		        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		    }
	}
	
	
	
	@Override
	public Response getFilteredCentrifugationWorkList(SearchDto searchDto) throws ApplicationException {
		 try
		    {
		        
			 String getCentrifugationWorkListQuery =sessionFactory.getCurrentSession().getNamedQuery("CENTRIFUGATION_WORK_LIST").getQueryString().toString();
		        
		        String [] orderSplit = getCentrifugationWorkListQuery.split("ORDER", 2);
		        
		        String orderPart1 = orderSplit[0];
		        String orderPart2 = "ORDER "+orderSplit[1];
		        
		        
		        StringBuilder getCentrifugationWorkListSearchQueryPart1 =new StringBuilder(orderPart1);
			       
		        
		        if(searchDto.getFromDate()!=null && !searchDto.getFromDate().isEmpty())
		        {
		       /*   getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate > '"+searchDto.getFromDate()+"' ");*/
		        	getCentrifugationWorkListSearchQueryPart1.append(" AND lbsampledtls.sample_accept_datetime > '"+searchDto.getFromDate()+"' ");
		        }
		        
		        if(searchDto.getToDate()!=null && !searchDto.getToDate().isEmpty())
		        {
		         /* getDashBoardSearchQuery.append(" AND labSampleDetailsMst.createdDate < '"+searchDto.getToDate()+"' ");*/
		        	getCentrifugationWorkListSearchQueryPart1.append(" AND lbsampledtls.sample_accept_datetime < '"+searchDto.getToDate()+"' ");
		        }
		        
		        if(searchDto.getPatientId()!=null &&searchDto.getPatientId() >0)
		        	getCentrifugationWorkListSearchQueryPart1.append(" AND pati_mst.patient_id = "+searchDto.getPatientId());
		        
		        if(searchDto.getVisitTypes()!=null && searchDto.getVisitTypes().size()>0)
		        	getCentrifugationWorkListSearchQueryPart1.append(" AND visit_type_master.visit_type_id IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
		        
		        if(searchDto.getTestTypes()!=null && searchDto.getTestTypes().size()>0)
		        	getCentrifugationWorkListSearchQueryPart1.append(" AND lbsampledtls.test_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getTestTypes())+")");
		      
		        
		        if(searchDto.getSubDepts()!=null && searchDto.getSubDepts().size()>0)
		        	getCentrifugationWorkListSearchQueryPart1.append(" AND test_mst.dept_id IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getSubDepts())+")");
		      
		        if(searchDto.getLabSampleDtlsId()!=null && searchDto.getLabSampleDtlsId()>0)
		        	getCentrifugationWorkListSearchQueryPart1.append(" AND lbsampledtls.lab_sample_dtls_id ="+searchDto.getLabSampleDtlsId());
		        
		        StringBuilder getCentrifugationWorkListQueryPart2 =new StringBuilder(orderPart2);
		        getCentrifugationWorkListSearchQueryPart1.append(getCentrifugationWorkListQueryPart2);
		          
		        Query createdQuery=sessionFactory.getCurrentSession().createSQLQuery(getCentrifugationWorkListSearchQueryPart1.toString());
		        
		        List<SampleForInOutPatient> listSampleForInOutPatient = createdQuery
					.setCharacter("isCentrifugationReq", 'Y')
		        	.setInteger("orgId", searchDto.getOrgId())
					.setInteger("orgUnitId", searchDto.getOrgUnitId())
					.setInteger("sampleStatusId", OP_IP_ACCEPTED)
					.setInteger("deptId", searchDto.getDeptId())
					.setParameterList("biochemHematoDeptIds", BIOCHEMISTRY_HEMATOLOGY_DEPT_IDS)
		            .setResultTransformer(Transformers.aliasToBean(SampleForInOutPatient.class)).list();
		        
		        
		        if(listSampleForInOutPatient.isEmpty())
		        {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,"No Records Found.",listSampleForInOutPatient , null);
		        }
		        else {
		          return new Response(SUCCESS, SUCCESS_200_MESSAGE,null, listSampleForInOutPatient, null);
		        }
		       
		        
		    } catch (Exception e)
		    {
		        logger.error("Exection", e);
		        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		    }
	}

}
