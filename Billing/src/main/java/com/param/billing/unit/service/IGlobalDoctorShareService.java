package com.param.billing.unit.service;

import javax.transaction.Transactional;

import com.param.billing.unit.dto.DoctorShareReqDto;
import com.param.global.common.Response;
import com.param.service.dto.CommonDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")

public interface IGlobalDoctorShareService {
	
	
	@Transactional
	public  Response saveGlobalDoctorShare(DoctorShareReqDto doctorShareReqDto)throws ApplicationException;
	
	@Transactional
	public  Response getDoctorGradeMaster(Integer orgId, Integer orgUnitId)throws ApplicationException;
	
	
	/*@Transactional
	public Response searchCompanyContract(ServiceForCompnayContarctReqDto reqDto) throws ServiceException;*/
}
