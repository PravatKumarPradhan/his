package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.AntibioticClassMaster;
import com.param.entity.lis.global.SlideProcedureMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticClassMasterDto;
import com.param.lis.global.dto.SlideProcedureMasterDto;

@SuppressWarnings("rawtypes")
public interface ISliderProcedureMasterDao extends IGenericDao<SlideProcedureMaster, Integer>{

	
	public Response addSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto)throws ApplicationException;
	public Response checkSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto) throws ApplicationException;
	public Response updateCheckSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto) throws ApplicationException;
	public Response updateSlideProcedureMaster(SlideProcedureMasterDto slideProcedureMasterDto) throws ApplicationException;
	public Response ActivateInactivateSlideProcedureMaster(Integer orgId, Integer slideProcedureId, Character slideProcedureStatus) throws ApplicationException;
	public Response listSlideProcedureMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalSlideProcedureMaster(Integer orgId) throws ApplicationException;
	public Response getSlideProcedureMasterById(Integer orgId, Integer slideProcedureId)
			throws ApplicationException;
}
