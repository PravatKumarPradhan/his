package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedTypeMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBedTypeMasterService {

	@Transactional
	Response addBedTypeMaster(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getBedTypeMasterList(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateBedTypeMaster(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getBedTypeById(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForBedType(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveBedTypeList() throws ApplicationException;

	@Transactional
	Response getBedTypeCount(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

}
