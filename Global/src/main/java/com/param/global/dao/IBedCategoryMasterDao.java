package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.BedCategoryMasterDto;
import com.param.global.model.BedCategoryMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBedCategoryMasterDao extends IGenericDao<BedCategoryMaster, Integer>{

	Response getBedCategoryByName(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	Response addBedCategoryTypeMaster(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	Response getBedCategoryMasterList(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	Response getBedCategoryById(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	Response updateBedCategoryStatus(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	Response getBedCategoryByNameNotId(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	Response updateBedCategoryMaster(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	Response getActiveBedCategoryList() throws ApplicationException;

	Response getCount(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

}
