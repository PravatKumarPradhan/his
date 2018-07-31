package com.param.global.dao;

/*import com.param.adt.master.unit.model.NursingStationMaster;*/

import com.param.adt.master.unit.model.NursingStationMaster;
import com.param.global.common.Response;
import com.param.global.dto.NursingStationMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface INursingStationDao extends IGenericDao<NursingStationMaster, Integer>{

	@SuppressWarnings("rawtypes")
	public Response getListOfNursingStationMaster(NursingStationMasterDto nursingStationMasterDto)throws ApplicationException;
	
	public Response getListOfNursingStationMasterByClinicId(NursingStationMasterDto nursingStationMasterDto)throws ApplicationException;
	
}
