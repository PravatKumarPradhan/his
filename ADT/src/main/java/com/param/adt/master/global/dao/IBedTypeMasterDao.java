package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedTypeMasterDto;
import com.param.adt.master.global.model.BedTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBedTypeMasterDao extends IGenericDao<BedTypeMaster, Integer> {

	Response getBedTypeByName(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	Response addBedTypeMaster(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	Response getBedTypeMasterList(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	Response getBedTypeByNameNotId(BedTypeMasterDto bedTypeMasterDto)throws ApplicationException;

	Response updateBedTypeMaster(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	Response getBedTypeById(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	Response updateBedTypeStatus(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

	Response getActiveBedTypeMasterList() throws ApplicationException;

	Response getCount(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException;

}
