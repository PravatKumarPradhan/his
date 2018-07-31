package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.ReportTypeMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ReagentMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IReportTypeMasterDao extends IGenericDao<ReportTypeMaster, Integer>{
	
	public Response addReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto)throws ApplicationException;
	public Response getaddReportTypeMasterById(Integer orgId, Integer reagentId) throws ApplicationException;
	public Response updateReportTypeMaster(ReportTypeMasterDto reportTypeMasterDto) throws ApplicationException;
	public Response updateCheckReportTypeCodeAvaiable(ReportTypeMasterDto reportTypeMasterDto) throws ApplicationException;
	public Response ActivateInactivateReportTypeMaster(Integer orgId, Integer reagentId, Character reagentStatus) throws ApplicationException;
	public Response listReportTypeMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkReportTypeMaster(ReportTypeMasterDto reagentMasterDto) throws ApplicationException;
	public Response getToTalReportTypeMasterRecord(Integer orgId) throws ApplicationException;
}
