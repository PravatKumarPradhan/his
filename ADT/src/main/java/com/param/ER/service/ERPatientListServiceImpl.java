package com.param.ER.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.ER.dao.IERPatientListDao;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class ERPatientListServiceImpl implements IERPatientListService,ICommonConstants{

	@Autowired
	IERPatientListDao iERPatientListDao;
	
	
	@Override
	public Response getERAdmissionList(AdmissionDto erAdmissionDto)throws ApplicationException {
		try {

			return iERPatientListDao.getERAdmissionList(erAdmissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getERAdmissionCount(AdmissionDto erAdmissionDto) throws ApplicationException {
		try {

			return iERPatientListDao.getERAdmissionCount(erAdmissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getDataForMapOfBed(AdmissionDto erAdmissionDto) throws ApplicationException {
		try {

			return iERPatientListDao.getDataForMapOfBed(erAdmissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getERPatient(UnknownPatientRegistrationDto unknownPatientRegistrationDto)throws ApplicationException {
		try {
			return iERPatientListDao.getERPatient(unknownPatientRegistrationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response searchERPatient(AdmissionDto admissionDto)
			throws ApplicationException {
		try {
			return iERPatientListDao.searchERPatient(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response searchPatientForMapOfBed(AdmissionDto erAdmissionDto) throws ApplicationException {
		try {
			return iERPatientListDao.searchPatientForMapOfBed(erAdmissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}





	
}
