package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IPatientSponsorDetailsDao;
import com.param.global.dto.PatientSponsorDetailsDto;
@Service
@SuppressWarnings("rawtypes")
public class PatientSponsorDetailsServiceImpl implements IPatientSponsorDetailsService,ICommonConstants{

	@Autowired
	IPatientSponsorDetailsDao iPatientSponsorDetailsDao; 
	
	@Override
	public Response savePatientSponsorDetails(@RequestBody PatientSponsorDetailsDto patientSponsorDetailsDto) {
		try {
			return iPatientSponsorDetailsDao.savePatientSponsorDetails(patientSponsorDetailsDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
