package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.global.dto.UnitServiceMapperDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IUnitServiceMapperService {
	public Response saveUnitServiceMapper(UnitServiceMapperDto unitServiceMapperDto)throws ApplicationException;
	public Response searchServiceMasterBySpecialitySubSplecialityUnitAndOrg(String searchKeyword, Integer specialityId, Integer subSpecialityId, Integer unitId, Integer orgId)throws ApplicationException;
	public Response getMappedServicesListByUnit(UnitServiceMapperDto unitServiceMapperDto)throws ApplicationException;
	public Response getCountOfMappedServicesListByUnit(UnitServiceMapperDto unitServiceMapperDto)throws ApplicationException;
	public Response updateUnitServiceMapperStatus(UnitServiceMapperDto unitServiceMapperDto)throws ApplicationException;
	public Response searchServicesByNameAndCode(ServiceMasterDto serviceMasterDto)throws ApplicationException;
	public Response searchUnitServiceByMultipleSpecialityAndSubSpeciality(UnitServiceMapperDto unitServiceMapperDto)throws ApplicationException;
	public Response searchActiveServices(ServiceSearchReqDto serviceSearchReqDto)throws ApplicationException;
}

