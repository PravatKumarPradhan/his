package com.param.lis.transaction.dao;

import com.param.entity.lis.transaction.LabResultMaster;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBiochemReportReleaseDao extends IGenericDao<LabResultMaster, Integer>{

	public Response getReportReleaseWorkList(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getReportReleaseWorkListCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getReportReleaseWorkListDetails(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response saveReportReleaseWorkList(LabResultMasterDto labResultMasterDto) throws ApplicationException;
}
