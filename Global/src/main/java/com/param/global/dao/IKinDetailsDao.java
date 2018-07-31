package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.KinDetailsDto;
import com.param.global.dto.MortuaryDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IKinDetailsDao 
{
	Response saveKinDetails(KinDetailsDto kinDetailsDto)throws ApplicationException;
	
	Response getKinDetailsByPatientId(Integer patientId, Integer unitId, Integer orgId)throws ApplicationException;
	
	Response getKinDetailsById(Integer kinDetailsId) throws ApplicationException;
	
	Response changeKinDetailsStatus(Integer kinDetailsId, Character status)throws ApplicationException;
	
	Response getkinDeatilsForMortuaryAllocation(MortuaryDto mortuaryDto)throws ApplicationException;
	
	Response updateKinDetails(KinDetailsDto kinDetailsDto)throws ApplicationException;
}
