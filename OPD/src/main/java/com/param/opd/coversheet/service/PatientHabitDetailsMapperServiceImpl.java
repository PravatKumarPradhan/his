package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IPatientHabitDetailsMapperDao;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.model.PatientHabitDetails;

@Service
@SuppressWarnings({ "unused", "unchecked" })
public class PatientHabitDetailsMapperServiceImpl implements IPatientHabitDetailsMapperService, ICommonConstants {

	
	@Autowired
	private IPatientHabitDetailsMapperDao iPatientHabitDetailsMapperDao;
	
	@Override
	
	public Response savePatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException {
		// TODO Auto-generated method stub
		try
		{
			Response patientHabitDetailsMapperResponse = iPatientHabitDetailsMapperDao.updatePatientHabitDetailsMapper(patientHabitDetailsDto);
			return iPatientHabitDetailsMapperDao.savePatientHabitDetailsMapper(patientHabitDetailsDto);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getListOfPatientHabitDetailsMapper(PatientHabitDetailsDto patientHabitDetailsDto)throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientHabitDetailsMapperDao.getListOfPatientHabitDetailsMapper(patientHabitDetailsDto);
	}

	@Override
	@Transactional
	public Response getOldPatientHabitDetails(
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientHabitDetailsMapperDao.getOldPatientHabitDetails(patientHabitDetailsDto);
	}

	@Override
	@Transactional
	public Response getCurrentPatientHabitDetails(
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientHabitDetailsMapperDao.getCurrentPatientHabitDetails(patientHabitDetailsDto);
	}

	@Override
	@Transactional
	public Response updatePatientHabitDetails(
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientHabitDetailsMapperDao.updatePatientHabitDetails(patientHabitDetailsDto);
	}

	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			PatientHabitDetails patientHabitDetails)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientHabitDetailsMapperDao.updateSaveNurseReviewedFlag(patientHabitDetails);
	}

	@Override
	@Transactional
	public Response updateStatusEnterInError(
			PatientHabitDetailsDto patientHabitDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientHabitDetailsMapperDao.updateStatusEnterInError(patientHabitDetailsDto);
	}

}
