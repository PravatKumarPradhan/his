package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DischargeTypeMasterDto;
import com.param.adt.master.global.model.DischargeTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDischargeTypeMasterDao extends IGenericDao<DischargeTypeMaster, Integer>
{

	Response getDischargeTypeByName(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	Response addDischargeTypeMaster(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	Response getDischargeTypeByNameNotId(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	Response updateDischargeTypeMaster(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	Response getDischargeTypeMasterList(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	Response getDischargeTypeById(DischargeTypeMasterDto dischargeTypeMasterDto)throws ApplicationException;

	Response getActiveDischargeTypeList(DischargeTypeMasterDto dischargeTypeMasterDto)throws ApplicationException;

	Response updateStatusForDischargeType(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;

	Response getCount(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException;
	

}
