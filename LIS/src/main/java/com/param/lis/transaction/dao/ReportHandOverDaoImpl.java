package com.param.lis.transaction.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.transaction.LabResultMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportHandOverDaoImpl extends GenericDao<LabResultMaster, Integer>
implements IReportHandOverDao, ICommonConstants, ITransactionConstants, IError {

public ReportHandOverDaoImpl() 
{
  super(LabResultMaster.class);
}



final static Logger logger = Logger.getLogger(ReportHandOverDaoImpl.class);

@Override
public Response getReportHandList(BioChemParamDto bioChemParamDto) throws ApplicationException {
	List<LabResultMasterDto> listLabResultMasterDto = null;
	try {
		listLabResultMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_BIOCHEM_REPORT_HAND_OVER_ENTRY_LIST")
				.setInteger("orgId", bioChemParamDto.getOrgId())
				.setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
				.setInteger("sampleStatusId", REPORT_RELEASED)
				.setInteger("deptId", bioChemParamDto.getDeptId())
				.setParameterList("subDeptIds",LAB_SUB_DEPTS)
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
public Response getReportHandListCount(BioChemParamDto bioChemParamDto) throws ApplicationException {
	try {
		BigInteger sampleResultEntryCount = (BigInteger) sessionFactory.getCurrentSession()
				.getNamedQuery("GET_BIOCHEM_REPORT_HAND_OVER_COUNT")
				.setInteger("orgId", bioChemParamDto.getOrgId())
				.setInteger("sampleStatusId", REPORT_RELEASED)
				.setInteger("deptId", bioChemParamDto.getDeptId())
			    .setParameterList("subDeptIds",LAB_SUB_DEPTS)
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


}
