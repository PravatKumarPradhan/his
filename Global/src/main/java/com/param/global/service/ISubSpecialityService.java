package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.SubSpecialityMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ISubSpecialityService {

	
	public Response addSubSpecialityMaster(SubSpecialityMasterDto subspecialityMasterDto) throws ApplicationException;

	public Response getSubSpecialityMasterList(SubSpecialityMasterDto subspecialityMasterDto)throws ApplicationException;

	public Response getSubSpecialityById(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException;

	public Response updateStatusForSubSpeciality(SubSpecialityMasterDto subSpecialityMasterDto)throws ApplicationException;

	public Response updateSubSpecialityMaster(SubSpecialityMasterDto subspecialityMasterDto)throws ApplicationException;

	Response getSubSpecialityCount(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException;
	
	public Response getSubSpecialityBySpecialityId(SubSpecialityMasterDto subspecialityMasterDto)throws ApplicationException;

	public Response getSubspecialityNotInUnit(int id) throws ApplicationException;
	

}
