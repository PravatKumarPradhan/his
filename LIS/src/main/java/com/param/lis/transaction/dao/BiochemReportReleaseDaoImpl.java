package com.param.lis.transaction.dao;

import java.math.BigInteger;
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
public class BiochemReportReleaseDaoImpl extends GenericDao<LabResultMaster, Integer>
implements IBiochemReportReleaseDao, ICommonConstants, ITransactionConstants, IError {

public BiochemReportReleaseDaoImpl() 
{
  super(LabResultMaster.class);
}

@Autowired
private DozerBeanMapper mapper;

final static Logger logger = Logger.getLogger(BiochemReportReleaseDaoImpl.class);

@Override
public Response getReportReleaseWorkList(BioChemParamDto bioChemParamDto) throws ApplicationException {
	List<LabResultMasterDto> listLabResultMasterDto = null;
	try {
		listLabResultMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_BIOCHEM_VALIDATION_ENTRY")
				.setInteger("orgId", bioChemParamDto.getOrgId())
				.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
				.setInteger("sampleStatusId", RESULT_VALIDATION_DONE)
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
public Response getReportReleaseWorkListCount(BioChemParamDto bioChemParamDto) throws ApplicationException {
	try {
		BigInteger sampleResultEntryCount = (BigInteger) sessionFactory.getCurrentSession()
				.getNamedQuery("GET_BIOCHEM_VALIDATION_ENTRY_LIST_COUNT")
				.setInteger("orgId", bioChemParamDto.getOrgId())
				.setInteger("sampleStatusId", RESULT_VALIDATION_DONE)
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
public Response getReportReleaseWorkListDetails(BioChemParamDto bioChemParamDto) throws ApplicationException 
{
	
	try {
		String GET_REPORT_DETTAILS = sessionFactory.getCurrentSession().getNamedQuery("GET_LAB_RESULT_DETAILS").getQueryString();
		List<LabResultDetailsViewDto> listLabResultDetailsMasterDto = null;
		if(bioChemParamDto.getTestType()==SINGLE_PARAM_TEST)
		{
			 listLabResultDetailsMasterDto = sessionFactory.getCurrentSession().createQuery(GET_REPORT_DETTAILS)
					.setInteger("orgId", bioChemParamDto.getOrgId())
					.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
					.setInteger("labResultId", bioChemParamDto.getLabResultId())
					.setResultTransformer(Transformers.aliasToBean(LabResultDetailsViewDto.class)).list();
			return new Response(SUCCESS, SUCCESS_200, null, listLabResultDetailsMasterDto, null);	
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
public Response saveReportReleaseWorkList(LabResultMasterDto labResultMasterDto) throws ApplicationException {
	try {
		
		LabResultMaster labResultMaster = mapper.map(labResultMasterDto, LabResultMaster.class,
				"LabResultMasterDtoToLabResultMaster");
		LabResultMaster labResultMst = update(labResultMaster);
		LabSampleDetailsMaster labSampleDetailsMaster = (LabSampleDetailsMaster) sessionFactory.getCurrentSession()
				.get(LabSampleDetailsMaster.class, labResultMst.getLabSampleDtlsId());
		
		labSampleDetailsMaster.setCreatedBy(labResultMst.getCreatedBy());
		labSampleDetailsMaster.setCreatedDate(new Date().getTime());
		labResultMaster.setUpdatedDate(new Date().getTime());
		labResultMaster.setUpdatedBy(labResultMasterDto.getCreatedBy());
		labResultMaster.setCreatedDate(new Date().getTime());
		labResultMaster.setCreatedBy(labResultMasterDto.getCreatedBy());
		labResultMaster.setSampleNo(labResultMasterDto.getSampleNo());
		labSampleDetailsMaster.setUpdatedBy(labResultMst.getUpdatedBy());
		labSampleDetailsMaster.setUpdatedDate(new Date().getTime());
		labResultMaster.setSampleNo(labResultMasterDto.getSampleNo());
		labSampleDetailsMaster.setSampleStatusId(FINAL_REPORT_RELEASED);
		sessionFactory.getCurrentSession().update(labSampleDetailsMaster);
			for (Iterator iterator = labResultMst.getListLabSampleResultDetailsMaster().iterator(); iterator
					.hasNext();) 
			{
				LabResultDetailsMaster labResultDetailsMaster = (LabResultDetailsMaster) iterator.next();
				labResultDetailsMaster.setLabResultId(labResultMst.getLabTestResId());
				labResultDetailsMaster.setThirdLevelResult(labResultDetailsMaster.getFinalResult());
				sessionFactory.getCurrentSession().update(labResultDetailsMaster);
		   }
			return new Response(SUCCESS, SUCCESS_200, SAMPLE_RESULT_ENTRY_SUCC, null, null);
	} catch (Exception e) {
		logger.error("Exception", e);
		return new Response(ERROR, ERR_500, SAMPLE_RESULT_ENTRY_FAIL, null, null);
	}
}
}
