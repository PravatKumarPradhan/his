package com.param.adt.master.unit.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitICUTypeMapperDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitICUService {

	@Transactional
	Response saveUnitSubSpecialtiy(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

	@Transactional
	Response getUnitICUTypeList(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

	@Transactional
	Response updateStatusForUnitICUType(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

	@Transactional
	Response getUnitICUTypeCount(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

	@Transactional
	Response getActiveUnitICUTypeList(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

}
