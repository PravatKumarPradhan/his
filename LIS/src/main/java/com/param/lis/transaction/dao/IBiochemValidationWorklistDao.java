package com.param.lis.transaction.dao;

import java.util.List;
import com.param.entity.lis.transaction.LabResultMaster;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBiochemValidationWorklistDao extends IGenericDao<LabResultMaster, Integer>{

	public Response getValidationWorkList(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getValidationWorkListCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getValidationWorkListDetails(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response saveValidationWorkList(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException;
	
	public Response getPrivousResultBySample(BioChemParamDto bioChemParamDto) throws ApplicationException;
}
