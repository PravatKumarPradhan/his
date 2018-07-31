package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.global.dao.IServiceMasterDao;
import com.param.global.dto.ServiceMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class ServiceMasterServiceImpl implements IServiceMasterService,IError,ICommonConstants{

	@Autowired
	private IServiceMasterDao iserviceMasterDao;
	
	@Override
	@Transactional
	public Response saveOrganizationServiceMaster(ServiceMasterDto xServiceMasterDto) throws ApplicationException {
		try {
			xServiceMasterDto.setIsAvailableForPackage('I');
			xServiceMasterDto.setIsAllowMultipleQuantity('I');
			xServiceMasterDto.setIsAutorendered('I');
			xServiceMasterDto.setIsDiscountApplicable('I');
			xServiceMasterDto.setIsDoctorRequired('I');
			xServiceMasterDto.setIsOrderSet('I');
			xServiceMasterDto.setIsOtProcedure('I');
			xServiceMasterDto.setIsOutsourceService('I');
			xServiceMasterDto.setIsRateEditable('I');
			xServiceMasterDto.setIsTaxApplicable('I');
			
			return iserviceMasterDao.saveOrganizationServiceMaster(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response changeOrganizationServiceMasterStatus(ServiceMasterDto xServiceMasterDto) throws ApplicationException {
		try {
			return iserviceMasterDao.changeOrganizationServiceMasterStatus(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response listOrganizationServiceMasterMaster(Integer orgId, Integer offset, Integer recordPerPage)
			throws ApplicationException {
		try {
			return iserviceMasterDao.listOrganizationServiceMasterMaster(orgId, offset, recordPerPage);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getOrganizationServiceMasterTotalCount(Integer orgId) throws ApplicationException {
		try {
			return iserviceMasterDao.getOrganizationServiceMasterTotalCount(orgId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getOrganizationServiceMasterById(ServiceMasterDto xServiceMasterDto) {
		try {
			return iserviceMasterDao.getOrganizationServiceMasterById(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null); 
	}

	@Override
	@Transactional
	public Response updateOrganizationServiceMaster(ServiceMasterDto xServiceMasterDto) throws ApplicationException {
		try {
			xServiceMasterDto.setIsAvailableForPackage('I');
			xServiceMasterDto.setIsAllowMultipleQuantity('I');
			xServiceMasterDto.setIsAutorendered('I');
			xServiceMasterDto.setIsDiscountApplicable('I');
			xServiceMasterDto.setIsDoctorRequired('I');
			xServiceMasterDto.setIsOrderSet('I');
			xServiceMasterDto.setIsOtProcedure('I');
			xServiceMasterDto.setIsOutsourceService('I');
			xServiceMasterDto.setIsRateEditable('I');
			xServiceMasterDto.setIsTaxApplicable('I');
			return iserviceMasterDao.updateOrganizationServiceMaster(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null); 
	}

	@Override
	@Transactional
	public Response getOrganizationServiceMasterBySpecialityAndSubSpeciality(ServiceMasterDto xServiceMasterDto)
			throws ApplicationException {
		try {					
			return iserviceMasterDao.getOrganizationServiceMasterBySpecialityAndSubSpeciality(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null); 
	}

	@Override
	@Transactional
	public Response getServiceDetailsByServiceId(ServiceMasterDto serviceMasterDto) throws ApplicationException {
		try {
			return iserviceMasterDao.getServiceDetailsByServiceId(serviceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}


}
