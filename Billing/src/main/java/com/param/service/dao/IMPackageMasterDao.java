package com.param.service.dao;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.service.dto.MPackageMasterDto;
import com.param.service.dto.ServiceForPackageReqDto;
import com.param.service.global.model.MPackageMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IMPackageMasterDao extends IGenericDao<MPackageMaster, Integer>{
	public Response savePackageMaster(MPackageMasterDto masterDto)throws ServiceException;
	public Response searchServiceForPackage(ServiceForPackageReqDto reqDto)throws ServiceException;
	public Response getEHCPackagesList(ServiceForPackageReqDto reqDto)throws ApplicationException;
	public Response searchServicesForBilling(ServiceSearchReqDto reqDto)throws ServiceException;
	public Response getPackageById(int packageId)throws ServiceException;
}
