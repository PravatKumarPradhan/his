package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IReportTypeMasterService {
	public Response addReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto)throws ApplicationException;
	public Response getaddReportTypeMasterById(Integer orgId, Integer reagentId) throws ApplicationException;
	public Response updateReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto) throws ApplicationException;
	public Response ActivateInactivateReportTypeMaster(Integer orgId, Integer reagentId, Character reagentStatus) throws ApplicationException;
	public Response listReportTypeMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalReportTypeMasterRecord(Integer orgId) throws ApplicationException;
}
