package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IAppointmentVitalMapperDao;
import com.param.opd.coversheet.dto.AppointmentVitalMapperDto;
import com.param.opd.coversheet.model.AppointmentVitalMapper;

@Service
public class AppointmentVitalMapperServiceImpl implements IAppointmentVitalMapperService {

	@Autowired
	private IAppointmentVitalMapperDao iAppointmentVitalMapperDao;
	
	@Override
	@Transactional
	public Response saveAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException {
		// TODO Auto-generated method stub
		Response appointmentVitalMapperResponse = iAppointmentVitalMapperDao.updateAppointmentVitalMapper(appointmentVitalMapperDto);
		return iAppointmentVitalMapperDao.saveAppointmentVitalMapper(appointmentVitalMapperDto);
	}

	@Override
	
	public Response getListOfAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAppointmentVitalMapperDao.getListOfAppointmentVitalMapper(appointmentVitalMapperDto);
	}

	@Override
	public Response getListOfAppointmentVitalMapperByPatientId(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAppointmentVitalMapperDao.getListOfAppointmentVitalMapperByPatientId(appointmentVitalMapperDto);
	}

	@Override
	@Transactional
	public Response getOldAppointmentVitalMapper(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAppointmentVitalMapperDao.getOldAppointmentVitalMapper(appointmentVitalMapperDto);
	}

	@Override
	@Transactional
	public Response getCurrentAppointmentVitalMapper(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAppointmentVitalMapperDao.getCurrentAppointmentVitalMapper(appointmentVitalMapperDto);
	}

	

	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			AppointmentVitalMapper appointmentVitalMapper)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAppointmentVitalMapperDao.updateSaveNurseReviewedFlag(appointmentVitalMapper);
	}

	@Override
	@Transactional
	public Response updateAppointmentVitalMapper(
			AppointmentVitalMapperDto appointmentVitalMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAppointmentVitalMapperDao.updateAppointmentVitalMapperDto(appointmentVitalMapperDto);
	}

}
