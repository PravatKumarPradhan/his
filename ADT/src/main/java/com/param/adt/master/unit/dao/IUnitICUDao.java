package com.param.adt.master.unit.dao;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitICUTypeMapperDto;
import com.param.adt.master.unit.model.UnitICUTypeMapper;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitICUDao extends IGenericDao<UnitICUTypeMapper, Integer>{

	
	Response saveUnitICUType(UnitICUTypeMapperDto unIcuTypeMapperDto) throws ApplicationException;

	Response getUnitICUTypeList(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

	Response getUnitICUTypeById(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

	Response updateStatusForUnitICUType(UnitICUTypeMapperDto unitICUTypeMapperDto) throws ApplicationException;

	Response truncateUnitICUType(UnitICUTypeMapperDto obj)throws ApplicationException;

	Response getCount(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

	Response getActiveUnitICUTypeList(UnitICUTypeMapperDto unitICUTypeMapperDto)throws ApplicationException;

}
