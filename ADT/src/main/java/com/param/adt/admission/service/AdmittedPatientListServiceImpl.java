package com.param.adt.admission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.admission.dao.IAdmittedPatientListDao;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class AdmittedPatientListServiceImpl implements IAdmittedPatientListService,ICommonConstants
{
	@Autowired
	IAdmittedPatientListDao iAdmittedPatientListDao; 


	public Response getAdmittedPatientList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iAdmittedPatientListDao.getAdmittedPatientList(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getActiveWardListByFloorId(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			return iAdmittedPatientListDao.getActiveWardListByFloorId(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getKinDetailsByAdmissionId(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iAdmittedPatientListDao.getKinDetailsByAdmissionId(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getAdmittedPatientByFloorWard(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iAdmittedPatientListDao.getAdmittedPatientByFloorWard(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response serachPatientForMapOfBed(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iAdmittedPatientListDao.serachPatientForMapOfBed(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	

	}
