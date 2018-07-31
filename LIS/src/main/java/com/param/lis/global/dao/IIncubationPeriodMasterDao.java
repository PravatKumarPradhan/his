package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.IncubationPeriodMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.IncubationPeriodMasterDto;

@SuppressWarnings("rawtypes")
public interface IIncubationPeriodMasterDao extends IGenericDao<IncubationPeriodMaster, Integer>{
	
	public Response addIncubationPeriodMaster(IncubationPeriodMasterDto incubationPeriodMasterDto)throws ApplicationException;
	public Response getIncubationPeriodMasterById(Integer orgId, Integer incubationPeriodId) throws ApplicationException;
	public Response updateIncubationPeriodMaster(IncubationPeriodMasterDto incubationPeriodMasterDto) throws ApplicationException;
	public Response updateCheckIncubationPeriodCodeAvaiable(IncubationPeriodMasterDto incubationPeriodMasterDto) throws ApplicationException;
	public Response ActivateInactivateIncubationPeriodMaster(Integer orgId, Integer incubationPeriodId, Character incubationPeriodStatus) throws ApplicationException;
	public Response listIncubationPeriodMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkIncubationPeriodMaster(IncubationPeriodMasterDto incubationPeriodMasterDto) throws ApplicationException;
	public Response getToTalIncubationPeriodMaster(Integer orgId) throws ApplicationException;
}
