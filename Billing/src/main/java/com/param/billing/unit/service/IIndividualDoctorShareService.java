package com.param.billing.unit.service;

import javax.transaction.Transactional;

import com.param.billing.unit.dto.IndividualDoctorShareReqDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")

public interface IIndividualDoctorShareService {
	
	
	@Transactional
	public  Response saveIndividualDoctorShare(IndividualDoctorShareReqDto individualDoctorShareReqDto)throws ApplicationException;
	
	/*@Transactional
	public Response searchCompanyContract(ServiceForCompnayContarctReqDto reqDto) throws ServiceException;*/
}
