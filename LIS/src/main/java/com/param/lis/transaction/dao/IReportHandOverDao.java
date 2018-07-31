package com.param.lis.transaction.dao;

import com.param.entity.lis.transaction.LabResultMaster;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IReportHandOverDao extends IGenericDao<LabResultMaster, Integer>{

	public Response getReportHandList(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getReportHandListCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	
}
