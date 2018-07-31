package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.ReagentMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ReagentMasterDto;
import com.param.lis.unit.dto.PhlebotomyMasterDto;

@SuppressWarnings("rawtypes")
public interface IReagentMasterDao extends IGenericDao<ReagentMaster, Integer>{
	public Response addReagentMaster(ReagentMasterDto reagentMasterDto)throws ApplicationException;
	public Response getReagentMasterById(Integer orgId, Integer reagentId) throws ApplicationException;
	public Response updateReagentMaster(ReagentMasterDto reagentMasterDto) throws ApplicationException;
	public Response updateCheckReagentCodeAvaiable(ReagentMasterDto reagentMasterDto) throws ApplicationException;
	public Response ActivateInactivateReagentMaster(Integer orgId, Integer reagentId, Character reagentStatus) throws ApplicationException;
	public Response listReagentMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkReagentCodeAvaiable(ReagentMasterDto reagentMasterDto) throws ApplicationException;
	public Response getToTalReagentMasterRecord(Integer orgId) throws ApplicationException;
}
