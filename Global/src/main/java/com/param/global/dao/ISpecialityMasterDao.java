package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.SpecialityMasterDto;
import com.param.global.model.SpecialityMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ISpecialityMasterDao extends IGenericDao<SpecialityMaster, Integer>
{
	
	public Response getSpecialityMasterList(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;
	
	public Response addSpecialityMaster(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;
	
	public Response editSpecialityMaster(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;
	
	public Response updateSpecialityMaster(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;
	
	public Response getSpecialityByNameNotId(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;	
	
	public Response getActiveSpecialityList(SpecialityMasterDto SpecialityMasterDto)throws ApplicationException;

	public Response getSpecialityById(SpecialityMasterDto specialityMasterDto) throws ApplicationException;

	public Response updateSpecialityStatus(SpecialityMasterDto specialityMasterDto);

	public Response getSpecialityByName(SpecialityMasterDto SpecialityMasterDto) throws ApplicationException;

	public Response getActiveSpecialityMasterList()throws ApplicationException;

	public Response getSpecialityListForSubSpeciality() throws ApplicationException;

	public Response getCount(SpecialityMasterDto SpecialityMasterDto) throws ApplicationException;

	public Response getOrgActiveSpecialityList(SpecialityMasterDto specialityMasterDto)throws ApplicationException;
}
