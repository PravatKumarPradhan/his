package com.param.lis.microbiology.transaction.service;

import java.util.List;
import com.param.entity.lis.micro.SensitivityTestResultMaster;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicrobioResultEntryMasterDto;
import com.param.lis.microbiology.transaction.dto.SensitivityTestResultMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISensitivityTestingService {

	public Response getSensitivityTestingList(MicrobioParamDto microbioParamDto) throws ApplicationException;

	public Response getSensitivityTestingListCount(MicrobioParamDto microbioParamDto) throws ApplicationException;

	public Response getSensitivityTestingDetails(MicrobioParamDto microbioParamDto) throws ApplicationException;

	public Response saveSensitivityTestingObservation(SensitivityTestResultMasterDto listSensitivityTestResultMasterDto,Integer labSampleStausId)
			throws ApplicationException;
	
	public Response getSensitivityDetails(Integer labSampleDtlsId, Integer orgId, Integer orgUnitId) throws ApplicationException;
	
	

}
