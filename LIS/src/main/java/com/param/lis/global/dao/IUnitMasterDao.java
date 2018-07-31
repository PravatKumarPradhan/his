package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.LabUnitMaster;
import com.param.entity.lis.global.MediaMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.MediaMasterDto;
import com.param.lis.global.dto.PrerequisitesMasterDto;
import com.param.lis.global.dto.UnitMasterDto;

@SuppressWarnings("rawtypes")
public interface IUnitMasterDao extends IGenericDao<LabUnitMaster, Integer>{
	
    public Response addUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getUnitMasterById(Integer orgId, Integer unitId) throws ApplicationException;
	public Response updateUnitMaster(UnitMasterDto unitMasterDto) throws ApplicationException;
	public Response updateCheckUnitCodeAvaiable(UnitMasterDto UnitMasterDto) throws ApplicationException;
	public Response checkUnitCodeAvaiable(UnitMasterDto unitMasterDto) throws ApplicationException;
	public Response ActivateInactivateUnitMaster(Integer orgId, Integer unitId, Character unitStatus) throws ApplicationException;
	public Response listUnitMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalUnitMasterRecord(Integer orgId) throws ApplicationException;
}
