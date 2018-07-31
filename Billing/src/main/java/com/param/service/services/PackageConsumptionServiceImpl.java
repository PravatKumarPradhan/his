package com.param.service.services;

import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.service.dto.PackageSearchRequestDto;

@Service
public class PackageConsumptionServiceImpl implements IPackageConsumptionService{

	@Override
	public Response getPackageServiceByPackageType(
			PackageSearchRequestDto packageSearchRequestDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
