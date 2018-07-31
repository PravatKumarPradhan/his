package com.param.adt.admission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.admission.dao.IAdmissionRequestDao;
import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DoctorSpecialityMapperDto;
import com.param.adt.master.global.dto.VisitMasterDto;
import com.param.global.dto.PatientRegistrationDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdmissionRequestServiceImpl implements IAdmissionRequestService, ICommonConstants {
	@Autowired
	IAdmissionRequestDao iAdmissionRequestDao;

	@Override
	public Response saveAdmissionRequest(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {

			/*admissionNoteDto.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
			admissionNoteDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/

			return iAdmissionRequestDao.saveAdmissionRequest(admissionNoteDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPatientListLike(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			return iAdmissionRequestDao.getPatientListLike(patientRegistrationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPatientVisitDetailsById(VisitMasterDto visitMasterDto) throws ApplicationException {
		try {
			return iAdmissionRequestDao.getPatientVisitDetailsById(visitMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDoctorListAganistSpeciality(DoctorSpecialityMapperDto doctorSpecialityMapperdto)
			throws ApplicationException {
		try {
			return iAdmissionRequestDao.getDoctorListAganistSpeciality(doctorSpecialityMapperdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getReasonMasterList() throws ApplicationException {
		try {
			return iAdmissionRequestDao.getReasonMasterList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveAdmissionPatientMapper(AdmissionNoteDto admissionNoteDto) {

		try {
	
			return iAdmissionRequestDao.saveAdmissionPatientMapper(admissionNoteDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveWaitingListNumber(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
			
			Response res= iAdmissionRequestDao.getMaxWaitingListNumber(admissionNoteDto);
			if(res.getStatus().equals(SUCCESS))
			{
				if(!res.getObject().equals(null))
				{
					Integer i=(Integer)res.getObject();
					i++;
					admissionNoteDto.setWaitListNumber(i);
					admissionNoteDto.setStatus('A');
					admissionNoteDto.setCreatedBy(admissionNoteDto.getUpdatedBy());
					return iAdmissionRequestDao.saveWaitingListNumber(admissionNoteDto);
				}
				else
					return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
			}
			else
				return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDoctorsListAganistSpecialitys(DoctorSpecialityMapperDto doctorSpecialityMapper)
			throws ApplicationException {
		try {
			return iAdmissionRequestDao.getDoctorsListAganistSpecialitys(doctorSpecialityMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
