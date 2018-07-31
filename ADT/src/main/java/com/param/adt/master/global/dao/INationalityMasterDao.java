package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.NationalityMasterDto;
import com.param.adt.master.global.model.NationalityMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface INationalityMasterDao extends IGenericDao<NationalityMaster, Integer>
{
	public Response addNationalityTypeMaster(NationalityMasterDto nationalityMasterDto) throws ApplicationException;

	public Response getNationalityByName(NationalityMasterDto nationalityMasterDto)throws ApplicationException;

	public Response getNationalityMasterList(NationalityMasterDto nationalityMasterDto)throws ApplicationException;

	public Response getNationalityByID(NationalityMasterDto nationalityMasterDto)throws ApplicationException;

	public Response getActiveNationalityMasterList()throws ApplicationException;

	public Response updateNationalityStatus(NationalityMasterDto nationalityMasterDto)throws ApplicationException;

	public Response getNationalityByNameNotId(NationalityMasterDto nationalityMasterDto)throws ApplicationException;

	public Response updateNationalityMaster(NationalityMasterDto nationalityMasterDto)throws ApplicationException;

	public Response getCount(NationalityMasterDto nationalityMasterDto)throws ApplicationException;

}
