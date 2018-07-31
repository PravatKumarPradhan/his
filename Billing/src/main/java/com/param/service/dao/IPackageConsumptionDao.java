package com.param.service.dao;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.service.dto.PackageSearchRequestDto;

public interface IPackageConsumptionDao {
	
	public Response getPackageServiceByPackageType(PackageSearchRequestDto packageSearchRequestDto) throws ApplicationException;
	public Response getPackageConsumptionCapDetails(Integer packageConsumptionMasterId) throws ApplicationException;
}
