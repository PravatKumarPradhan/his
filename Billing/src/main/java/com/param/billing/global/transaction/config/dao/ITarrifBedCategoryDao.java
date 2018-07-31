package com.param.billing.global.transaction.config.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.TarrifBedCategoryMpprDto;
import com.param.billing.global.transaction.model.ServiceTarrifBedtypeManager;

public interface ITarrifBedCategoryDao extends IGenericDao<ServiceTarrifBedtypeManager, Integer>{

	Response saveServiceTarrifMaster(TarrifBedCategoryMpprDto tarrifBedCategoryMpprDto) throws ApplicationException;
	
	Response saveListServiceTarrifMaster(List<TarrifBedCategoryMpprDto> listTarrifBedCategoryMpprDto) throws ApplicationException;
	
	Response getBedCategoryFactorByTarrifId(Integer serviceTarrifId) throws ApplicationException;
}
