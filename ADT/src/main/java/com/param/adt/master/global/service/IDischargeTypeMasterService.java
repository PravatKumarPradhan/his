package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DischargeTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDischargeTypeMasterService 
{

	@Transactional
	Response addDischargeTypeMaster(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getDischargeTypeMasterList(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateDischargeTypeMaster(DischargeTypeMasterDto dischargeTypeMasterDto)throws ApplicationException;

	@Transactional
	Response getDischargeTypeById(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForDischargeType(DischargeTypeMasterDto dischargeTypeMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveDischargeTypeList(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getDischargeTypeCount(DischargeTypeMasterDto dischargeTypeMasterDto)throws ApplicationException ;

}
