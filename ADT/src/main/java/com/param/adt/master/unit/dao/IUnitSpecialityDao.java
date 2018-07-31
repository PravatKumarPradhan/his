package com.param.adt.master.unit.dao;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;
import com.param.adt.master.unit.model.UnitSpecialityMapper;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitSpecialityDao extends IGenericDao<UnitSpecialityMapper, Integer>{

	Response saveUnitSpecialtiy(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	Response getUnitSpecialtiyList(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	Response getUnitSpecialityById(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException;

	Response updateStatusForUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException;

	Response truncateUnitSpecialtiy(UnitSpecialtyMapperDto obj)throws ApplicationException;

	Response getCount(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	Response getSpecialityListByDoctorId(UnitSpecialtyMapperDto unitSpecialtyMapperDto)throws ApplicationException;

	Response getActiveUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException;

	

}
