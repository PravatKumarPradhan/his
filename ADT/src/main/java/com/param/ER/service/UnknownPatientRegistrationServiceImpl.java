package com.param.ER.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.ER.dao.IUnknownPatientRegistrationDao;
import com.param.adt.admission.service.BedAllocationServiceImpl;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class UnknownPatientRegistrationServiceImpl implements IUnknownPatientRegistrationService,ICommonConstants
{

	@Autowired
	IUnknownPatientRegistrationDao iUnknownPatientRegistrationDao;
	
	
	@Override
	public Response getPriorityMasterList() throws ApplicationException {
		try {
			return iUnknownPatientRegistrationDao.getPriorityMasterList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveUnknownPatient(AdmissionDto unAdmissionDto)
			throws ApplicationException {
		try {
				unAdmissionDto.settUhid(getUHID());
				Response res =iUnknownPatientRegistrationDao.saveUnknownPatient(unAdmissionDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, res.getObject());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	protected String getUHID() 
	{
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		StringBuilder salt = new StringBuilder("TUHID00");
		Random rnd = new Random();
		while (salt.length() < 10) 
		{ 
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	@Override
	public Response saveERPatientAdmisson(AdmissionDto unAdmissionDto) throws ApplicationException {
		try {
			unAdmissionDto.setAdmissionNumber(BedAllocationServiceImpl.getAdmissionNumber());
			Response res = iUnknownPatientRegistrationDao.saveERPatientAdmission(unAdmissionDto);
			return new Response(SUCCESS, null, res.getMessage(), null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveERsavePatientDetails(AdmissionDto unAdmissionDto) throws ApplicationException {
		try {
			return iUnknownPatientRegistrationDao.saveERPatientDetails(unAdmissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response patientSearchByMultipleCriteria(PatientSearchDto patientSearchDto) throws ApplicationException {
		try {
			return iUnknownPatientRegistrationDao.patientSearchByMultipleCriteria(patientSearchDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveMedicoLegalDetails(AdmissionDto unAdmissionDto) throws ApplicationException {
		try {
			return iUnknownPatientRegistrationDao.saveMedicoLegalDetails(unAdmissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}



}
