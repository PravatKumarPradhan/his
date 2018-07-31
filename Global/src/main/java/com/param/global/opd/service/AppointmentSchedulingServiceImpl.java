package com.param.global.opd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IScheduleLogsDao;
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.dto.SlotMasterDto;
import com.param.global.opd.dao.IAppointmentSchedulingDao;
import com.param.global.service.IPatientRegistrationService;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class AppointmentSchedulingServiceImpl implements IAppointmentSchedulingService,ICommonConstants{

	@Autowired
	IAppointmentSchedulingDao iAppointmentSchedulingDao;
	
	@Autowired
	IScheduleLogsDao iScheduleLogsDao;
	
	@Autowired
	IPatientRegistrationService patientRegistrationServiceImpl;
	
	@Override
	public Response scheduleAppointment(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			
			
			Response res = iAppointmentSchedulingDao.isAppointmentValid(slotMasterDto);
			
			if(res.getListObject().size()>0)
			{
				return new Response(ERROR, COMMON_ERROR_CODE, "Slot is already booked !!", null, null);
			}else
			{
			
			//If patient is not registered then there will be a pre-registration of a patient who's patientTypeId == 5
			if(slotMasterDto.getPatientId()==0)
			{
				PatientRegistrationDto patientRegistrationDto = new PatientRegistrationDto();
				patientRegistrationDto.setOrganizationId(slotMasterDto.getOrganizationId());
				patientRegistrationDto.setUnitId(slotMasterDto.getUnitId());
				patientRegistrationDto.setPrefixId(slotMasterDto.getPrefixId());
				patientRegistrationDto.setFirstName(slotMasterDto.getFirstName());
				patientRegistrationDto.setMiddleName(slotMasterDto.getMiddleName());
				patientRegistrationDto.setLastName(slotMasterDto.getLastName());
				patientRegistrationDto.setDob(slotMasterDto.getDob());
				patientRegistrationDto.setGenderId(slotMasterDto.getGenderId());
				patientRegistrationDto.setMobileNumber(slotMasterDto.getMobileNumber());
				patientRegistrationDto.setEmail(slotMasterDto.getEmail());
				patientRegistrationDto.setRegistrationTypeId(slotMasterDto.getRegistrationTypeId());
				patientRegistrationDto.setCreatedBy(slotMasterDto.getCreatedBy());
				patientRegistrationDto.setCreatedDate(slotMasterDto.getCreatedDate());
				patientRegistrationDto.setUpdatedBy(slotMasterDto.getUpdatedBy());
				patientRegistrationDto.setUpdatedDate(slotMasterDto.getUpdatedDate());
				patientRegistrationDto.setStatus('A');
				Response patientRegistraion = patientRegistrationServiceImpl.savePatientRegistration(patientRegistrationDto);
				slotMasterDto.setPatientId((Integer)patientRegistraion.getObject());
				if(patientRegistraion.getStatus().equals(SUCCESS))
					return iAppointmentSchedulingDao.scheduleAppointment(slotMasterDto);
				else
					return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
			}
			return iAppointmentSchedulingDao.scheduleAppointment(slotMasterDto);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response scheduledAppointmentList(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iAppointmentSchedulingDao.scheduledAppointmentList(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response cancelScheduledAppointment(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iAppointmentSchedulingDao.cancelScheduledAppointment(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response rescheduleAppointment(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iAppointmentSchedulingDao.rescheduleAppointment(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response searchAppointmentsByCriteria(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iAppointmentSchedulingDao.searchAppointmentsByCriteria(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response patientAutofillSearch(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iAppointmentSchedulingDao.patientAutofillSearch(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateAppointmentStatus(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iAppointmentSchedulingDao.updateAppointmentStatus(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response blockedScheduleAppointmentList(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iAppointmentSchedulingDao.blockedScheduleAppointmentList(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	
}
