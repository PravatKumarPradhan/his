package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.SpecialityMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ISpecialityService 
{

	//public Response getSpecialityByUnitIdAndDoctorId(DoctorUnitSpecialityMapperDto doctorUnitSpecialityMapperDto)throws ApplicationException;
	//public Response getApiSpecialityListByDoctor(DoctorUnitSpecialityMapperDto doctorUnitSpecialityMapperDto)throws ApplicationException;
	
	public Response getSpecialityMasterList(SpecialityMasterDto specialityMasterDto)throws ApplicationException;
	
	public Response addSpecialityMaster(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;
	
	public Response updateSpecialityMaster(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;
	
	public Response getSpecialityByName(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;
	
	public Response getSpecialityById(SpecialityMasterDto specialityMasterDto)throws ApplicationException;

	public Response updateStatus(SpecialityMasterDto specialityMasterDto)throws ApplicationException;

	public Response getActiveSpecialityMasterList(SpecialityMasterDto specialityMasterDto)throws ApplicationException;

	public Response getSpecialityListForSubSpeciality() throws ApplicationException;

	public Response getSpecialityCount(SpecialityMasterDto specialityMasterDto)throws ApplicationException;

	public Response getOrgActiveSpecialityList(SpecialityMasterDto specialityMasterDto) throws ApplicationException;
}
