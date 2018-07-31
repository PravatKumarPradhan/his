package com.param.service.dao;

import com.param.global.common.Response;
import com.param.service.dto.ServiceForPackageReqDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IEHCpackageDao {
	public Response getPackgeType(Integer orgId,Integer orgUnitId) throws ApplicationException;
	public Response getAutoCompleteServices(Integer orgId,Integer orgUnitId,String serviceName) throws ApplicationException;
	public Response getTariff(Integer orgId,Integer orgUnitId) throws ApplicationException;
	public Response getPaymentEntitlementByTariffId(Integer orgId,Integer orgUnitId,Integer tariffId) throws ApplicationException;
	public Response getPatientTypeByTariffId(Integer orgId,Integer orgUnitId,Integer tariffId) throws ApplicationException;
	public Response getAutoCompletePackageCode(ServiceForPackageReqDto reqDto) throws ApplicationException;
	public Response getAutoCompletePackageName(Integer orgId,Integer orgUnitId,String serviceName) throws ApplicationException;
}
