package com.param.lis.transaction.dao;

import java.util.List;
import com.param.entity.lis.transaction.LabResultMaster;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface IBioChemResultEntryDao extends IGenericDao<LabResultMaster, Integer>
{
	public Response getResultEntryList(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getResultEntryListCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getResultEntryDetails(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response saveEntryDetails(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException;
	
	public Response getSingleParamTestResult(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getMultyParamTestResult(BioChemParamDto bioChemParamDto) throws ApplicationException;
}
