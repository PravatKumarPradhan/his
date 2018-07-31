package com.param.adt.mortuary.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.MortuaryDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IMortuaryService {

	@Transactional
	Response saveDeathRegistation(MortuaryDto mortuaryDto)throws ApplicationException;

	@Transactional
	Response saveMortuaryRequest(MortuaryDto mortuaryDto)throws ApplicationException;

	@Transactional
	Response getPendingMortuaryRequestList(MortuaryDto mortuaryDto)throws ApplicationException;

	@Transactional
	Response acceptRejectMortuaryRequestFromPendingList(MortuaryDto mortuaryDto)throws ApplicationException;

	@Transactional
	Response getReservedMortuaryRequestList(MortuaryDto mortuaryDto)throws ApplicationException;

	@Transactional
	Response getMorturyBedList(MortuaryDto mortuaryDto)throws ApplicationException;
	
	@Transactional
	Response saveBedAllocationMortuary(MortuaryDto mortuaryDto)throws ApplicationException;
	
	@Transactional
	Response getAdmittedMortuaryList(MortuaryDto mortuaryDto)throws ApplicationException;

	@Transactional
	Response releaseMorturyBed(MortuaryDto mortuaryDto)throws ApplicationException;

	@Transactional
	Response getDataForMorturyMapOfBed(MortuaryDto mortuaryDto)throws ApplicationException;

	@Transactional
	Response cancelMorgueRequest(MortuaryDto mortuaryDto)throws ApplicationException;

}
