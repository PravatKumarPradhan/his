package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.DoctorMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDoctorMasterService {

	@Transactional
	public Response getDoctorMasterList(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	@Transactional
	public Response saveDoctorRegistration(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	@Transactional
	public Response getDoctorDetails(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	@Transactional
	public Response updateDoctorDetails(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	@Transactional
	public Response updateDoctorStatus(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	@Transactional
	public Response searchDoctorDetailsByCriteria(DoctorMasterDto doctorMasterDto)throws ApplicationException;
}
