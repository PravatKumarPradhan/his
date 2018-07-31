package com.param.adt.master.unit.dao;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;
import com.param.adt.master.unit.dto.UnitSubSpecialityMapperDto;
import com.param.adt.master.unit.model.UnitSubSpecialityMapper;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitSubSpecialityDao extends IGenericDao<UnitSubSpecialityMapper, Integer>
{

	
	Response saveUnitSubSpecialitiy(UnitSubSpecialityMapperDto subSpecialityMapperDto);

	Response getUnitSubSpecialityList(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

	Response getUnitSubSpecialityById(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

	Response updateStatusForUnitSpecialityList(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

	Response truncateUnitSubSpecialtiy(UnitSubSpecialityMapperDto obj)throws ApplicationException;

	Response getUnitSubSpecialityCount(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

	Response getSubSpecialityBySpecialityId(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

	Response getSubSpecialityBySpecialityArray(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;
	
	Response getActiveUnitSubSpecialityList(UnitSubSpecialityMapperDto unitSpecialtyMapperDto) throws ApplicationException;

	Response getSubSpecialityBySpecialityArrayForTariff(UnitSubSpecialityMapperDto unitSubSpecialityMapperDto)throws ApplicationException;

}
