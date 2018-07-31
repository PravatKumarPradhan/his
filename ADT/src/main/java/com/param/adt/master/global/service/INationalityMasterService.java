package com.param.adt.master.global.service;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.NationalityMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface INationalityMasterService {

	public Response addNationalityMaster(NationalityMasterDto nationalityMasterDto) throws ApplicationException;

	public Response getNationalityMasterList(NationalityMasterDto nationalityMasterDto) throws ApplicationException;

	public Response updateNationalityMaster(NationalityMasterDto nationalityMasterDto) throws ApplicationException;

	public Response getNationalityById(NationalityMasterDto nationalityMasterDto) throws ApplicationException;

	public Response updateStatusForNationality(NationalityMasterDto nationalityMasterDto) throws ApplicationException;

	public Response getActiveNationalityList() throws ApplicationException;

	public Response getNationalityCount(NationalityMasterDto nationalityMasterDto)throws ApplicationException;

}
