package com.param.lis.global.dao;

import in.param.exception.ApplicationException;

import java.util.List;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticAdditionMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticAddtionClassMasterDao {
	
	public Response addAntibioticAddtionClassMaster(AntibioticAdditionMasterDto antibioticAdditionMasterDto)throws ApplicationException;
	public Response listAntibioticAddtionClassMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticAddtionClassMaster(Integer orgId) throws ApplicationException;
	public Response updateAntibioticAddtionClassMaster(List<AntibioticAdditionMasterDto> listAntibioticAdditionMasterDto) throws ApplicationException;
	public Response activateInactivateAntibioticAddtionClassMaster(Integer orgId,
			Integer antiboiticClassId, Character status) throws ApplicationException;
	public Response checkAntibioticAddtionClassAvaiable(AntibioticAdditionMasterDto AntibioticAdditionMasterDto) throws ApplicationException;
	
	public Response getAntibioticAddtionClassMasterById(Integer orgId, Integer antiboiticMpprId) throws ApplicationException;
	public Response getAntibioticMasterClassById(Integer antiboiticClassId) throws ApplicationException;
	
}
