package com.param.service.services;

import com.param.global.common.Response;
import com.param.service.dto.PackageSearchRequestDto;

import in.param.exception.ApplicationException;

/**
 * Package consumption service interface
 * @author User
 *
 */

public interface IPackageConsumptionService {

	public Response getPackageServiceByPackageType(PackageSearchRequestDto packageSearchRequestDto) throws ApplicationException;
	
}
