package com.param.adt.admission.service;

import javax.transaction.Transactional;

import com.param.adt.admission.dto.VisitorDto;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPassIssuedService {

	@Transactional
	Response getVisitorTypeList(AdmissionDto admissionDto) throws ApplicationException;

	@Transactional
	Response saveVisitorAgainsPatient(VisitorDto visitorDto)throws ApplicationException;

	@Transactional
	Response saveVisitorPatientMapper(VisitorDto visitorDto)throws ApplicationException;

	@Transactional
	Response getVisitorsList(VisitorDto visitorDto)throws ApplicationException;

	@Transactional
	Response updateVisitorsDetails(VisitorDto visitorDto)throws ApplicationException;

	
	@Transactional
	Response getBedListByWardId(BedMasterDto bedMasterDto)throws ApplicationException;

	@Transactional
	Response getAdmittedPatientListByMultipleCriteria(AdmissionDto admissionDto);
	
	
	

}
