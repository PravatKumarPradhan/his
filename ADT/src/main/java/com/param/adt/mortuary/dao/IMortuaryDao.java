package com.param.adt.mortuary.dao;
import com.param.global.common.Response;
import com.param.global.dto.MortuaryDto;
import com.param.global.model.DeathRegistration;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IMortuaryDao extends IGenericDao<DeathRegistration, Integer>{

	Response saveDeathRegistation(MortuaryDto mortuaryDto)throws ApplicationException;

	Response saveMortuaryRequest(MortuaryDto mortuaryDto)throws ApplicationException;

	Response getPendingMortuaryRequestList(MortuaryDto mortuaryDto) throws ApplicationException;

	Response acceptRejectMortuaryRequestFromPendingList(MortuaryDto mortuaryDto)throws ApplicationException;

	Response getReservedMortuaryRequestList(MortuaryDto mortuaryDto)throws ApplicationException;

	Response getMorturyBedList(MortuaryDto mortuaryDto)throws ApplicationException;
	
	Response saveBedAllocationMortuary(MortuaryDto mortuaryDto)throws ApplicationException;
	
	Response getAdmittedMortuaryList(MortuaryDto mortuaryDto)throws ApplicationException;

	Response releaseMorturyBed(MortuaryDto mortuaryDto)throws ApplicationException;

	Response getDataForMorturyMapOfBed(MortuaryDto mortuaryDto)throws ApplicationException;

	Response cancelMorgueRequest(MortuaryDto mortuaryDto)throws ApplicationException;

}
