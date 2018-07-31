package com.param.lis.microbiology.transaction.dao;

import com.param.entity.lis.micro.SensitivityTestResultMaster;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.SensitivityTestResultMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISensitivityTestingDao extends IGenericDao<SensitivityTestResultMaster, Integer> {

	public Response getSensitivityTestingList(MicrobioParamDto microbioParamDto) throws ApplicationException;

	public Response getSensitivityTestingListCount(MicrobioParamDto microbioParamDto) throws ApplicationException;

	public Response getSensitivityTestingDetails(MicrobioParamDto microbioParamDto) throws ApplicationException;

	public Response saveSensitivityTestingObservation(SensitivityTestResultMasterDto listSensitivityTestResultMasterDto)
			throws ApplicationException;
	
	public Response getSensitivityDetails(Integer labSampleDtlsId, Integer orgId, Integer orgUnitId) throws ApplicationException;
	
	public Response updateLabSampleDetailsMaster(SensitivityTestResultMasterDto sensitivityTestResultMasterDto) throws ApplicationException;
}
