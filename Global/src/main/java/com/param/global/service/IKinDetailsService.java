package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.MortuaryDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IKinDetailsService {
	Response saveKinDetails(AdmissionDto admissionDto)throws ApplicationException;
	
	Response getKinDetailsByPatientId(Integer patientId, Integer unitId, Integer orgId)throws ApplicationException;
	
	Response getKinDetailsById(Integer kinDetailsId) throws ApplicationException;
	
	Response changeKinDetailsStatus(Integer kinDetailsId, Character status)throws ApplicationException;
	
	Response getkinDeatilsForMortuaryAllocation(MortuaryDto mortuaryDto)throws ApplicationException;
}
