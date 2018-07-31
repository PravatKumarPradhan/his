package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.AntibioticMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticMasterDao extends IGenericDao<AntibioticMaster, Integer>{
	
	public Response addAntibioticMaster(AntibioticMasterDto atibioticMasterDto)throws ApplicationException;
	public Response checkAntibioticMaster(AntibioticMasterDto atibioticMasterDto) throws ApplicationException;
	public Response updateAntibioticMaster(AntibioticMasterDto atibioticMasterDto) throws ApplicationException;
	public Response getListAntibioticMaster() throws ApplicationException;
	public Response updateCheckeAntibioticMasterCodeAvaiable(AntibioticMasterDto atibioticMasterDto) throws ApplicationException;
	public Response ActivateInactivateAntibioticMaster(Integer orgId, Integer antibioticGroupId, Character antibioticGroupStatus) throws ApplicationException;
	public Response listAntibioticMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticMaster(Integer orgId) throws ApplicationException;
	public Response getAntibioticMasterById(Integer orgId, Integer antibioticGroupId)
			throws ApplicationException;
}
