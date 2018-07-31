package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IDoctorConsultationServiceMapperDao;
import com.param.global.dto.DoctorConsultationServiceMapperDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DoctorConsultationServiceMapperServiceImpl
		implements IDoctorConsultationServiceMapperService, ICommonConstants {

	@Autowired
	IDoctorConsultationServiceMapperDao iDoctorConsultationServiceMapperDao;

	@Override
	public Response saveDoctorConsultationServiceMapperService(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException {
		try {
			Integer id = iDoctorConsultationServiceMapperDao
					.checkDoctorConsultationExistOrNot(doctorConsultationServiceMapperDto);
			if (id != null) {
				// if consultation for doctor is already exist then
				iDoctorConsultationServiceMapperDao.inactivePreviousEntry(id);
				// adding new entry after in-activating previous
				iDoctorConsultationServiceMapperDao
						.saveDoctorConsultationServiceMapperService(doctorConsultationServiceMapperDto);
				return new Response(SUCCESS, null, "Inactivated Previous And Added New...!!", null, null);
			} else {

				return iDoctorConsultationServiceMapperDao
						.saveDoctorConsultationServiceMapperService(doctorConsultationServiceMapperDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDoctorConsultationServiceMapperList(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException {
		try {
			return iDoctorConsultationServiceMapperDao
					.getDoctorConsultationServiceMapperList(doctorConsultationServiceMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDoctorConsultationServiceMapper(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) throws ApplicationException {
		try {
			return iDoctorConsultationServiceMapperDao
					.updateDoctorConsultationServiceMapper(doctorConsultationServiceMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Override
	public Response getDoctorConsultationServiceMapperById(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) {
		try {
			return iDoctorConsultationServiceMapperDao
					.getDoctorConsultationServiceMapperById(doctorConsultationServiceMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDoctorConsultationServiceBySpecialityAndDoctorId(
			DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto) {
		try {
			return iDoctorConsultationServiceMapperDao
					.getDoctorConsultationServiceBySpecialityAndDoctorId(doctorConsultationServiceMapperDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
