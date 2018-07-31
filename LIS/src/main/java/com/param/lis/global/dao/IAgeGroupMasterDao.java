package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.AgeGroupMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AgeGroupMasterDto;
import com.param.lis.global.dto.BlockMasterDto;

@SuppressWarnings("rawtypes")
public interface IAgeGroupMasterDao extends IGenericDao<AgeGroupMaster, Integer>{
	
    public Response addAgeGroupMaster(AgeGroupMasterDto ageGroupMasterDto)throws ApplicationException;
	public Response getAgeGroupMasterById(Integer orgId, Integer ageGroupId) throws ApplicationException;
	public Response updateAgeGroupMaster(AgeGroupMasterDto ageGroupMasterDto) throws ApplicationException;
	public Response updateCheckAgeGroupCodeAvaiable(AgeGroupMasterDto ageGroupMasterDto) throws ApplicationException;
	public Response checkAgeGroupCodeAvaiable(AgeGroupMasterDto ageGroupMasterDto) throws ApplicationException;
	public Response ActivateInactivateAgeGroupMaster(Integer orgId, Integer ageGroupId, Character ageGroupStatus) throws ApplicationException;
	public Response listAgeGroupMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAgeGroupMasterRecord(Integer orgId) throws ApplicationException;
}
