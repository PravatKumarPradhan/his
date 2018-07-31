package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.BedCategoryMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBedCategoryMasterService 
{

	@Transactional
	Response addBedCategoryMaster(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	@Transactional
	Response getBedCategoryMasterList(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	@Transactional
	Response updateBedCategoryMaster(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	@Transactional
	Response getBedCategoryById(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForBedCategory(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveBedCategoryList() throws ApplicationException;

	@Transactional
	Response getBedCategoryCount(BedCategoryMasterDto bedCategoryMasterDto)throws ApplicationException;
	
}
