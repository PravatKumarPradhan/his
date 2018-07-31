package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.AntibioticGroupMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticGroupMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticsGroupMasterDao extends IGenericDao<AntibioticGroupMaster, Integer>{
	
	public Response addAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto)throws ApplicationException;
	public Response checkAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto) throws ApplicationException;
	public Response updateAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto) throws ApplicationException;
	public Response updateCheckAntibioticsGroupCodeAvaiable(AntibioticGroupMasterDto antibioticGroupMasterDto) throws ApplicationException;
	public Response ActivateInactivateAntibioticsGroupMaster(Integer orgId, Integer antibioticGroupId, Character antibioticGroupStatus) throws ApplicationException;
	public Response listAntibioticsGroupMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticsGroupMaster(Integer orgId) throws ApplicationException;
	public Response getAntibioticsGroupMasterById(Integer orgId, Integer antibioticGroupId)
			throws ApplicationException;
}
