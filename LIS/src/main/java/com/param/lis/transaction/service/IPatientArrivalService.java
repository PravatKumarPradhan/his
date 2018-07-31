package com.param.lis.transaction.service;

import java.util.List;

import in.param.exception.ApplicationException;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.LabCommonDto;
import com.param.lis.transaction.dto.PatientArrivalMapperMasterDto;

@SuppressWarnings("rawtypes")
public interface IPatientArrivalService {
	public Response patientArrival(LabCommonDto labCommonDto) throws ApplicationException;
	public Response patientArrivalList(LabCommonDto labCommonDto) throws ApplicationException;
	public Response patientArrivalMapperMaster(List<PatientArrivalMapperMasterDto> patientArrivalMapperMasterDto) throws ApplicationException;
	public Response patientNotArrivalData(LabSampleDetailsMaster labSampleDetailsMaster)throws ApplicationException;
}
