package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.SubSpecialityMasterDto;
import com.param.global.model.SubSpecialityMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ISubSpecialityMasterDao extends IGenericDao<SubSpecialityMaster, Integer> 
{

	public Response getSubSpecialityMasterList(SubSpecialityMasterDto subSpecialityMasterDto)throws ApplicationException;
	
	public Response addSubSpecialityMaster(SubSpecialityMasterDto subSpecialityMasterDto)throws ApplicationException;
	
	public Response editSubSpecialityMaster(SubSpecialityMasterDto subSpecialityMasterDto)throws ApplicationException;
	
	public Response updateSubSpecialityMaster(SubSpecialityMasterDto subSpecialityMasterDto)throws ApplicationException;
	
	public Response getSubSpecialityByName(SubSpecialityMasterDto subSpecialityMasterDto)throws ApplicationException;	
	
	public Response getSubActiveSpecialityList()throws ApplicationException;

	public Response getSubSpecialityById(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException;

	public Response updateSubSpecialityStatus(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException;

	public Response getSubSpecialityByNameNotId(SubSpecialityMasterDto subspecialityMasterDto) throws ApplicationException;

	Response getCount(SubSpecialityMasterDto subspecialityMasterDto)throws ApplicationException;
	
	public Response getSubSpecialityBySpecialityId(SubSpecialityMasterDto subspecialityMasterDto)throws ApplicationException;

	public Response getSubspecialityNotInUnit(int id) throws ApplicationException;

}
