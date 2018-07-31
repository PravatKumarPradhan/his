package com.param.adt.master.unit.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitSpecialityService {

	@Transactional
	Response saveUnitSpecialtiy(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException;

	@Transactional
	Response getUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	@Transactional
	Response updateStatusForUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	@Transactional
	Response getUnitSpecialityCount(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	@Transactional
	Response getSpecialityListByDoctorId(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;
	
	@Transactional

	Response getUnitSpecialityById(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	@Transactional
	Response getActiveUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException;
	


}
