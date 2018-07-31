package com.param.adt.admission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.service.IAdmissionRequestService;
import com.param.adt.common.IADTConstants;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DoctorSpecialityMapperDto;
import com.param.adt.master.global.dto.VisitMasterDto;
import com.param.global.dto.PatientRegistrationDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdmissionRequestController implements ICommonConstants, IADTConstants {

	@Autowired
	IAdmissionRequestService iAdmissionRequestService;

	@RequestMapping(value = "/saveAdmissionRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Response saveAdmissionRequest(@RequestBody AdmissionNoteDto admissionNoteDto) {
		try {
			Response res = iAdmissionRequestService.saveAdmissionRequest(admissionNoteDto);
			AdmissionNoteDto admissionNoteDto2 = (AdmissionNoteDto) res.getObject();
			if (res.getStatus().equals(SUCCESS)) {
				Response res2 = iAdmissionRequestService.saveAdmissionPatientMapper(admissionNoteDto2);
				AdmissionNoteDto admissionNoteDto3 = (AdmissionNoteDto) res.getObject();
				if(!(admissionNoteDto.getDoa().substring(0,10).equals(admissionNoteDto.getUpdatedDate().substring(0,10))))
				{
					if(res2.getStatus().equals(SUCCESS))
						return iAdmissionRequestService.saveWaitingListNumber(admissionNoteDto3);
				}else
				{
					if(res2.getStatus().equals(SUCCESS))
						return new Response(SUCCESS, null, NOTE_GENERATED, null, null);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getPatientListLike", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientListLike(@RequestBody PatientRegistrationDto patientRegistrationDto) {
		try {
			return iAdmissionRequestService.getPatientListLike(patientRegistrationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getPatientVisitDetailsById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientVisitDetailsById(@RequestBody VisitMasterDto visitMasterDto) {
		try {

			return iAdmissionRequestService.getPatientVisitDetailsById(visitMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getDoctorListAganistSpeciality", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorListAganistSpeciality(
			@RequestBody DoctorSpecialityMapperDto doctorSpecialityMapper) {
		try {

			return iAdmissionRequestService.getDoctorListAganistSpeciality(doctorSpecialityMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value = "/getDoctorsListAganistSpecialities", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorsListAganistSpecialities(
			@RequestBody DoctorSpecialityMapperDto doctorSpecialityMapper) {
		try {

			return iAdmissionRequestService.getDoctorsListAganistSpecialitys(doctorSpecialityMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@RequestMapping(value = "/getReasonMasterList", method = RequestMethod.GET)
	public @ResponseBody Response getReasonMasterList() {
		try {

			return iAdmissionRequestService.getReasonMasterList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
