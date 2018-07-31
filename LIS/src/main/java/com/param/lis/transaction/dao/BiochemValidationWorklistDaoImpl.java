package com.param.lis.transaction.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.transaction.LabResultDetailsMaster;
import com.param.entity.lis.transaction.LabResultMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultDetailsViewDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BiochemValidationWorklistDaoImpl extends GenericDao<LabResultMaster, Integer>
implements IBiochemValidationWorklistDao, ICommonConstants, ITransactionConstants, IError {

public BiochemValidationWorklistDaoImpl() {
super(LabResultMaster.class);
}

@Autowired
private DozerBeanMapper mapper;

final static Logger logger = Logger.getLogger(BiochemValidationWorklistDaoImpl.class);

@Override
public Response getValidationWorkList(BioChemParamDto bioChemParamDto) throws ApplicationException {
	List<LabResultMasterDto> listLabResultMasterDto = null;
	try {
		listLabResultMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_BIOCHEM_VALIDATION_ENTRY")
				.setInteger("orgId", bioChemParamDto.getOrgId())
				.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
				.setInteger("sampleStatusId", RESULT_ENTRY_DONE)
				.setInteger("deptId", bioChemParamDto.getDeptId())
				.setInteger("subDeptId", bioChemParamDto.getSubDeptId())
				.setFirstResult(bioChemParamDto.getOffset() != null ? bioChemParamDto.getOffset() : 0)
				.setMaxResults(bioChemParamDto.getRecordPerPage() != null ? bioChemParamDto.getRecordPerPage() : 10)
				.setResultTransformer(Transformers.aliasToBean(LabResultMasterDto.class)).list();
		return new Response(SUCCESS, SUCCESS_200, null, listLabResultMasterDto, null);
	} catch (Exception e) {
		logger.error("Exception", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}

@Override
public Response getValidationWorkListCount(BioChemParamDto bioChemParamDto) throws ApplicationException {
	try {
		BigInteger sampleResultEntryCount = (BigInteger) sessionFactory.getCurrentSession()
				.getNamedQuery("GET_BIOCHEM_RESULT_ENTRY_LIST_COUNT")
				.setInteger("orgId", bioChemParamDto.getOrgId())
				.setInteger("sampleStatusId", RESULT_ENTRY_DONE)
				.setInteger("deptId", bioChemParamDto.getDeptId())
				.setInteger("subDeptId", bioChemParamDto.getSubDeptId())
				.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId()).uniqueResult();
		if (sampleResultEntryCount.compareTo(BigInteger.ZERO) == 1) {
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, sampleResultEntryCount);
		} else {
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, BigInteger.ZERO);
		}

	} catch (Exception e) {
		logger.error("Exception", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}

@Override
public Response getValidationWorkListDetails(BioChemParamDto bioChemParamDto) throws ApplicationException 
{
	try {
		String GET_REPORT_DETTAILS = sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_RESULT_DETAILS").getQueryString();
		List<LabResultDetailsViewDto> listLabResultDetailsMasterDto = null;
			if(bioChemParamDto.getTestType()==SINGLE_PARAM_TEST)
			{
				List<LabResultDetailsViewDto> listLabResultDetailsMstDto = new ArrayList<>();
				LabResultDetailsViewDto labResultDetailsMasterDto = (LabResultDetailsViewDto) sessionFactory.getCurrentSession().createQuery(GET_REPORT_DETTAILS)
						.setInteger("orgId", bioChemParamDto.getOrgId())
						.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
						.setInteger("labResultId", bioChemParamDto.getLabResultId())
						.setResultTransformer(Transformers.aliasToBean(LabResultDetailsViewDto.class)).uniqueResult();
				listLabResultDetailsMstDto.add(labResultDetailsMasterDto);
				return new Response(SUCCESS, SUCCESS_200, null, listLabResultDetailsMstDto, null);	
			}
			else if(bioChemParamDto.getTestType()==MULTY_PARAM_TEST)
			{
				StringBuilder GET_REPORT_DETTAILS_MULTYPARAM  = new StringBuilder(GET_REPORT_DETTAILS).append(" AND labResultDetailsMaster.headerId= :headerId");
				listLabResultDetailsMasterDto = sessionFactory.getCurrentSession().createQuery(GET_REPORT_DETTAILS_MULTYPARAM.toString())
						.setInteger("orgId", bioChemParamDto.getOrgId())
						.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
						.setInteger("headerId", bioChemParamDto.getHeaderId())
						.setInteger("labResultId", bioChemParamDto.getLabResultId())
						.setResultTransformer(Transformers.aliasToBean(LabResultDetailsViewDto.class)).list();
				return new Response(SUCCESS, SUCCESS_200, null, listLabResultDetailsMasterDto, null);
			}
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	} catch (Exception e) {
		logger.error("Exception", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}

@Override
public Response saveValidationWorkList(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException {
	try {
	  for (Iterator iterator = listLabResultMasterDto.iterator(); iterator.hasNext();) {
	    LabResultMasterDto labResultMasterDto = (LabResultMasterDto) iterator.next();
		LabResultMaster labResultMaster = mapper.map(labResultMasterDto, LabResultMaster.class,
				"LabResultMasterDtoToLabResultMaster");
		labResultMaster.setUpdatedDate(new Date().getTime());
		labResultMaster.setUpdatedBy(labResultMasterDto.getCreatedBy());
		labResultMaster.setResultValidatedBy(labResultMasterDto.getCreatedBy());
		labResultMaster.setSampleNo(labResultMasterDto.getSampleNo());
		labResultMaster.setResultValidatedDatetime(new Date().getTime());
		labResultMaster.setCreatedDate(labResultMasterDto.getCreatedDate());
		labResultMaster.setCreatedBy(labResultMasterDto.getCreatedBy());
		LabResultMaster labResultMst = update(labResultMaster);
		LabSampleDetailsMaster labSampleDetailsMaster = (LabSampleDetailsMaster) sessionFactory.getCurrentSession()
				.get(LabSampleDetailsMaster.class, labResultMst.getLabSampleDtlsId());
		
		labSampleDetailsMaster.setUpdatedBy(labResultMst.getUpdatedBy());
		labSampleDetailsMaster.setUpdatedDate(new Date().getTime());
		labSampleDetailsMaster.setSampleStatusId(RESULT_VALIDATION_DONE);
		sessionFactory.getCurrentSession().update(labSampleDetailsMaster);
			for (Iterator iterator1 = labResultMst.getListLabSampleResultDetailsMaster().iterator(); iterator1
					.hasNext();) {
				LabResultDetailsMaster labResultDetailsMaster = (LabResultDetailsMaster) iterator1.next();
				labResultDetailsMaster.setLabResultId(labResultMst.getLabTestResId());
				labResultDetailsMaster.setSecondLevelResult(labResultDetailsMaster.getFinalResult());
				labResultDetailsMaster.setUpdatedBy(labResultMasterDto.getCreatedBy());
				labResultDetailsMaster.setUpdatedDate(new Date().getTime());
				sessionFactory.getCurrentSession().update(labResultDetailsMaster);
		}
	  }
			return new Response(SUCCESS, SUCCESS_200, SAMPLE_RESULT_ENTRY_VALIDATION_SUCC, null, null);
	} catch (Exception e) {
		logger.error("Exception", e);
		return new Response(ERROR, ERR_500, SAMPLE_RESULT_ENTRY_VALIDATION_FAIL, null, null);
	}
}

@Override
public Response getPrivousResultBySample(BioChemParamDto bioChemParamDto) throws ApplicationException {
	try {
		String GET_REPORT_DETTAILS = sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_RESULT_DETAILS").getQueryString();
		List<LabResultDetailsViewDto> listLabResultDetailsMasterDto = null;
			if(bioChemParamDto.getTestType()==SINGLE_PARAM_TEST)
			{
				List<LabResultDetailsViewDto> listLabResultDetailsMstDto = new ArrayList<>();
				LabResultDetailsViewDto labResultDetailsMasterDto = (LabResultDetailsViewDto) sessionFactory.getCurrentSession().createQuery(GET_REPORT_DETTAILS)
						.setInteger("orgId", bioChemParamDto.getOrgId())
						.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
						.setInteger("labResultId", bioChemParamDto.getLabResultId())
						.setResultTransformer(Transformers.aliasToBean(LabResultDetailsViewDto.class)).uniqueResult();
				listLabResultDetailsMstDto.add(labResultDetailsMasterDto);
				return new Response(SUCCESS, SUCCESS_200, null, listLabResultDetailsMstDto, null);	
			}
			else if(bioChemParamDto.getTestType()==MULTY_PARAM_TEST)
			{
				StringBuilder GET_REPORT_DETTAILS_MULTYPARAM  = new StringBuilder(GET_REPORT_DETTAILS).append(" AND labResultDetailsMaster.headerId= :headerId");
				listLabResultDetailsMasterDto = sessionFactory.getCurrentSession().createQuery(GET_REPORT_DETTAILS_MULTYPARAM.toString())
						.setInteger("orgId", bioChemParamDto.getOrgId())
						.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
						.setInteger("headerId", bioChemParamDto.getHeaderId())
						.setInteger("labResultId", bioChemParamDto.getLabResultId())
						.setResultTransformer(Transformers.aliasToBean(LabResultDetailsViewDto.class)).list();
				return new Response(SUCCESS, SUCCESS_200, null, listLabResultDetailsMasterDto, null);
			}
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	} catch (Exception e) {
		logger.error("Exception", e);
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}
}
