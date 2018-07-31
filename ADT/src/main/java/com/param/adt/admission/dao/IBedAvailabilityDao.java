package com.param.adt.admission.dao;

import com.param.adt.admission.dto.BedAvailibailityDto;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedCategoryMasterDto;
import com.param.global.model.BedCategoryMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBedAvailabilityDao extends IGenericDao<BedCategoryMaster, Integer> 
{
	
	Response getBedCategoryList(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException;

	Response bedAvailiblityList(BedAvailibailityDto bedAvailibailityDto);

}
