package com.param.adt.master.unit.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;
import com.param.adt.master.unit.dto.UnitSubSpecialityMapperDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitSubSpecialityService {
	@Transactional
	Response saveUnitSubSpecialtiy(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

	@Transactional
	Response getUnitSubSpecialityList(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

	@Transactional
	Response updateStatusForUnitSubSpeciality(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto) throws ApplicationException;

	@Transactional
	Response getUnitSubSpecialityCount(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

	@Transactional
	Response getSubSpecialityBySpecialityId(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;
	
	@Transactional
	Response getSubSpecialityBySpecialityArray(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;
	
	@Transactional
	Response getActiveUnitSubSpecialityList(UnitSubSpecialityMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	@Transactional
	Response getSubSpecialityBySpecialityArrayForTariff(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;


}
