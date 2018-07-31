package com.param.adt.admission.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IAdmittedPatientListService 
{
	
	@Transactional
	Response getAdmittedPatientList(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response getActiveWardListByFloorId(WardMasterDto wardMasterDto)throws ApplicationException;

	@Transactional
	Response getKinDetailsByAdmissionId(AdmissionDto admissionDto)throws ApplicationException;
	
	@Transactional
	Response getAdmittedPatientByFloorWard(AdmissionDto admissionDto) throws ApplicationException;

	@Transactional
	Response serachPatientForMapOfBed(AdmissionDto admissionDto)throws ApplicationException;

}
