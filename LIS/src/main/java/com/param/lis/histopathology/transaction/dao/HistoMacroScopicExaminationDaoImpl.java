package com.param.lis.histopathology.transaction.dao;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.histo.TSpecimanMaster;
import com.param.entity.lis.histo.TSubSpecimanMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.lis.common.dto.CommonAutoCompleteDto;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.global.common.StringCommonMethods;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TSpecimanMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class HistoMacroScopicExaminationDaoImpl extends GenericDao<TSpecimanMaster, Integer>
implements IHistoMacroScopicExaminationDao, ICommonConstants, IError,ITransactionConstants
{
	
	final static Logger logger = Logger.getLogger(HistoMacroScopicExaminationDaoImpl.class);
	
	public HistoMacroScopicExaminationDaoImpl() 
	{
		super(TSpecimanMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private ITSubSpecimemDao iTSubSpecimemDao;

	@Override
	public Response listSpecimanReceipt(HistoParamDto histoParamDto) throws ApplicationException {
		try
		{
			List<TSpecimanMasterDto> listTSpecimanMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPCIMEN_LIST")
					.setInteger("orgId", histoParamDto.getOrgId())
					.setInteger("orgUnitId", histoParamDto.getOrgUnitId())
					.setInteger("sampleStatusId", SPECIMAN_ACCEPTED)
					.setFirstResult(histoParamDto.getOffset() != null ? histoParamDto.getOffset() : 0)
					.setMaxResults(histoParamDto.getRecordPerPage() != null ? histoParamDto.getRecordPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(TSpecimanMasterDto.class)).list();
			
			if(listTSpecimanMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, "No Speciman Records Found.", null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listTSpecimanMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getCollectedSpecimensCount(HistoParamDto histoParamDto) throws ApplicationException {
		try
		{
			Long specimenListCount = (Long) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPCIMEN_LIST_COUNT")
					.setInteger("orgId", histoParamDto.getOrgId())
					.setInteger("orgUnitId", histoParamDto.getOrgUnitId())
					.setInteger("sampleStatusId", SPECIMAN_ACCEPTED)
					.uniqueResult();
			if (specimenListCount!=null)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, specimenListCount);
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
	public Response saveMicroscopicExaminationDetails(TSpecimanMasterDto tSpecimanMasterDto)
			throws ApplicationException 
	{
		try
		{
		 TSpecimanMaster tSpecimenMaster = mapper.map(tSpecimanMasterDto, TSpecimanMaster.class,"TSpecimanMasterDtoToTSpecimanMaster");
		 tSpecimenMaster.setIsDeleted('N');
		 TSpecimanMaster tSpecimenMasterObj = update(tSpecimenMaster);
		  if(!tSpecimenMaster.getListTSubSpecimanMaster().isEmpty())
		  {
			  for (Iterator iterator = tSpecimenMaster.getListTSubSpecimanMaster().iterator(); iterator.hasNext();) 
			  {
				TSubSpecimanMaster tSubSpecimanMaster = (TSubSpecimanMaster) iterator.next();
				tSubSpecimanMaster.setLabSampleDtlsId(tSpecimenMasterObj.getLabSampleDtlsId());
				tSubSpecimanMaster.settSpecimanId(tSpecimenMasterObj.gettSpecimanId());
				tSubSpecimanMaster.setCreatedBy(tSpecimenMasterObj.getUpdatedBy());
				tSubSpecimanMaster.setCreatedDate(tSpecimenMasterObj.getUpdatedDate());
				iTSubSpecimemDao.saveSubSpecimanDetails(tSubSpecimanMaster);
			  }
			  return new Response(SUCCESS, SUCCESS_200, GROSS_CREATE_SUCCESS, null, null);
		  }
		  else 
		  {
			  return new Response(ERROR, ERR_500, GROSS_CREATE_FAIL, null, null);
		  }
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response saveSpecimanDetails(TSpecimanMasterDto tSpecimanMasterDto) throws ApplicationException 
	{
		try
		{
			TSpecimanMaster tSpecimanMaster = mapper.map(tSpecimanMasterDto, TSpecimanMaster.class,
					"TSpecimanMasterDtoToTSpecimanMaster");
			TSpecimanMaster tSpecimanObj = save(tSpecimanMaster);
			if (tSpecimanObj != null)
			{
				return new Response(SUCCESS, SUCCESS_200, "Specimen Accepted Successfully.", null, null);
			} else
			{
				return new Response(ERROR, ERR_500, "Failed to Accept Specimen.", null, null);
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
	public Response searchAcceptedSpecimanByPatient(SearchCommonDto searchCommonDto) throws ApplicationException 
	{
		List<CommonAutoCompleteDto>  patientList  = null; 
		try
		{
			patientList = (List<CommonAutoCompleteDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_ACCPTED_SPECIMEN_BY_PATIENT")
					.setInteger("orgId", searchCommonDto.getOrgId())
					.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
					.setString("searchKeyword", "%"+searchCommonDto.getSearchKeyword().trim().toLowerCase().trim()+"%")
					.setInteger("sampleStatusId", SPECIMAN_ACCEPTED)
					//.setInteger("detpId", searchCommonDto.getDeptId())
					//.setInteger("subDeptId", searchCommonDto.getSubDeptId())
                    .setResultTransformer(Transformers.aliasToBean(CommonAutoCompleteDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, patientList, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response searchPatientbyVisitType(SearchCommonDto searchCommonDto) throws ApplicationException {
		 List<CommonAutoCompleteDto>  visityTypeList  = null; 
		try
		{
			visityTypeList = 	(List<CommonAutoCompleteDto>) sessionFactory.getCurrentSession()
			.createSQLQuery(" SELECT "
					 +"	visit_mst.visit_type_id AS id, "
					 +"	visit_mst.visit_type_name AS label "
					 +"FROM "
					 +"	public.m_visit_type_master visit_mst "
					 +"ORDER BY "
					 +"	visit_type_id LIMIT 4 ")
                       .setResultTransformer(Transformers.aliasToBean(CommonAutoCompleteDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, visityTypeList, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response searchBySpecimenType(SearchCommonDto searchCommonDto) throws ApplicationException {
		List<CommonAutoCompleteDto>  organismList  = null; 
		try
		{
			organismList = (List<CommonAutoCompleteDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_ACCEPTED_SPECIMEN_TYPE")
					.setInteger("orgId", searchCommonDto.getOrgId())
					/*.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
					.setInteger("sampleStatusId", SPECIMAN_ACCEPTED)*/
					//.setInteger("detpId", searchCommonDto.getDeptId())
					//.setInteger("subDeptId", searchCommonDto.getSubDeptId())
                    .setResultTransformer(Transformers.aliasToBean(CommonAutoCompleteDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, organismList, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response searchByTest(SearchCommonDto searchCommonDto) throws ApplicationException {
		List<CommonDto>  testList  = null; 
		try
		{
			testList = (List<CommonDto>) sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_SPECIMEN_ACCEPTED_TEST")
					.setInteger("orgId", searchCommonDto.getOrgId())
					.setInteger("orgUnitId", searchCommonDto.getOrgUnitId())
					/*.setInteger("sampleStatusId", SPECIMAN_ACCEPTED)*/
					//.setInteger("detpId", searchCommonDto.getDeptId())
					//.setInteger("subDeptId", searchCommonDto.getSubDeptId())
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, testList, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response accetedSpecimenSearch(SearchDto searchDto) throws ApplicationException
	{
		try
		{
			
			StringBuilder getSecimanSearchQuery =new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_SPCIMEN_LIST").getQueryString().toString());
			
			
			if(searchDto.getFromDate()!=null)
				getSecimanSearchQuery.append(" AND tSpecimanMaster.createdDate > '"+searchDto.getFromDate()+"'");
			
			if(searchDto.getToDate()!=null)
				getSecimanSearchQuery.append(" AND tSpecimanMaster.createdDate < '"+searchDto.getFromDate()+"' ");
			
			if(searchDto.getPatientId()!=null &&searchDto.getPatientId() >0)
				getSecimanSearchQuery.append(" AND patientReg.patientId = "+searchDto.getPatientId());
			
			if(searchDto.getSpecimanTypes()!=null && searchDto.getSpecimanTypes().size()>0)
				getSecimanSearchQuery.append(" AND tSpecimanMaster.specimanTypeId IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getSpecimanTypes()) +")");
			
			if(searchDto.getVisitTypes()!=null && searchDto.getVisitTypes().size()>0)
				getSecimanSearchQuery.append(" AND labSampleMst.visitTypeId IN ("+StringCommonMethods.convertToStringJoiner(searchDto.getVisitTypes())+")");
			
			if(searchDto.getTestTypes()!=null && searchDto.getTestTypes().size()>0)
				getSecimanSearchQuery.append(" AND labSampleDetailsMst.testId IN ( "+StringCommonMethods.convertToStringJoiner(searchDto.getTestTypes())+")");
			
			Query createdQuery=sessionFactory.getCurrentSession().createQuery(getSecimanSearchQuery.toString());
			
			
			List<TSpecimanMasterDto> listTSpecimanMasterDto = createdQuery
				.setInteger("orgId", searchDto.getOrgId())
				.setInteger("orgUnitId", searchDto.getOrgUnitId())
				.setInteger("sampleStatusId", SPECIMAN_ACCEPTED)
				.setResultTransformer(Transformers.aliasToBean(TSpecimanMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listTSpecimanMasterDto, null);
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
		
	}

	@Override
	public Response getMicroscopicExamDataBySubSpecimenId(Integer subSpecimanId, Integer orgId, Integer orgUnitId)
			throws ApplicationException {
		try {
			TSubSpecimanMaster tSubSpecimanMst = (TSubSpecimanMaster) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MICROSCOPIC_EXAM_DATA_BY_SUB_SPCIMEN_ID")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId",orgUnitId)
					.setInteger("subSpecimanId", subSpecimanId)
                   .uniqueResult();			
			
			TSubSpecimanMasterDto tSubSpecimanMasterDto = mapper.map(tSubSpecimanMst, TSubSpecimanMasterDto.class,
			"TSubSpecimanMasterDtoToTSubSpecimanMaster");
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, tSubSpecimanMasterDto);
			
		}catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
