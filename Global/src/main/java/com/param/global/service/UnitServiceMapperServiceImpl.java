package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IUnitServiceMapperDao;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.global.dto.UnitServiceMapperDto;
import com.param.global.model.UnitServiceMapperId;
import com.param.global.model.UnitServiceMasterDetails;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitServiceMapperServiceImpl implements IUnitServiceMapperService, ICommonConstants{

	@Autowired
	private IUnitServiceMapperDao iUnitServiceMapperDao;
	
	@Override
	@Transactional
	public Response saveUnitServiceMapper(UnitServiceMapperDto unitServiceMapperDto) throws ApplicationException {
		try {
				UnitServiceMapperId unitServiceMapperId = null;
				for(UnitServiceMapperDto mapperDto : unitServiceMapperDto.getListUnitServiceMapperDto()) {
					unitServiceMapperId=new UnitServiceMapperId();
					unitServiceMapperId.setServiceId(mapperDto.getServiceId());
					unitServiceMapperId.setUnitId(unitServiceMapperDto.getUnitId());
					mapperDto.setUnitServiceMapperId(unitServiceMapperId);
					mapperDto.setStatus(unitServiceMapperDto.getStatus());
					mapperDto.setOrgnisationId(unitServiceMapperDto.getOrgnisationId());
					mapperDto.setUnitId(unitServiceMapperDto.getUnitId());
					mapperDto.setCreatedBy(unitServiceMapperDto.getCreatedBy());
					mapperDto.setUpdatedBy(unitServiceMapperDto.getUpdatedBy());
					mapperDto.setCreatedDate(unitServiceMapperDto.getCreatedDate());
					mapperDto.setUpdatedDate(unitServiceMapperDto.getUpdatedDate());
					iUnitServiceMapperDao.saveUnitServiceMapper(mapperDto);		
										
					
				}
				return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response searchServiceMasterBySpecialitySubSplecialityUnitAndOrg(String searchKeyword, Integer specialityId, Integer subSpecialityId, Integer unitId, Integer orgId)
			throws ApplicationException {
		try {
			return iUnitServiceMapperDao.searchServiceMasterBySpecialitySubSplecialityUnitAndOrg(searchKeyword, specialityId, subSpecialityId, unitId, orgId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getMappedServicesListByUnit(UnitServiceMapperDto unitServiceMapperDto) throws ApplicationException {
		try {
			return iUnitServiceMapperDao.getMappedServicesListByUnit(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response updateUnitServiceMapperStatus(UnitServiceMapperDto unitServiceMapperDto)
			throws ApplicationException {
		try {
			return iUnitServiceMapperDao.updateUnitServiceMapperStatus(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getCountOfMappedServicesListByUnit(UnitServiceMapperDto unitServiceMapperDto)
			throws ApplicationException {
		try {
			return iUnitServiceMapperDao.getCountOfMappedServicesListByUnit(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response searchServicesByNameAndCode(ServiceMasterDto serviceMasterDto) throws ApplicationException {
		try {
			return iUnitServiceMapperDao.searchServicesByNameAndCode(serviceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response searchUnitServiceByMultipleSpecialityAndSubSpeciality(UnitServiceMapperDto unitServiceMapperDto)
			throws ApplicationException {
		try {
			return iUnitServiceMapperDao.searchUnitServiceByMultipleSpecialityAndSubSpeciality(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response searchActiveServices(ServiceSearchReqDto serviceSearchReqDto)
			throws ApplicationException {
		try{
			return iUnitServiceMapperDao.searchActiveServices(serviceSearchReqDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
