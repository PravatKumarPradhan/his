package com.param.ER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.ER.service.IUnknownPatientRegistrationService;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;
import com.param.global.model.UnknownPatientRegistration;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnknownPatientRegistrationController implements ICommonConstants {

	@Autowired
	IUnknownPatientRegistrationService iUnknownPatientRegistrationService;

	@RequestMapping(value = "/saveUnknownPatient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveUnknownPatient(@RequestBody AdmissionDto unAdmissionDto) {
		try {
			int count=0;
			
			if(!unAdmissionDto.gettUhid().equals("")||!unAdmissionDto.gettUhid().isEmpty())
				count=1;
			else
				count=0;
			
			if(count==0)
			{
				Response res=iUnknownPatientRegistrationService.saveUnknownPatient(unAdmissionDto);
				UnknownPatientRegistration obj = (UnknownPatientRegistration) res.getObject();
				unAdmissionDto.settPatientId(obj.getUnknownPatientId());
				unAdmissionDto.setVisitTypeId(4); // For ER patients
				unAdmissionDto.setAdmissionTypeId(4); // For ER patients
				count=1;
				}
				if(count==1) 
				{
					Response res2 = iUnknownPatientRegistrationService.saveERPatientAdmisson(unAdmissionDto);
					String id = res2.getMessage();
					unAdmissionDto.setAdmissionId(Integer.parseInt(id));
	
					if (res2.getStatus().equals(SUCCESS)) 
					{
						unAdmissionDto.setAdmissionTypeId(4);
						Response res3= iUnknownPatientRegistrationService.saveERsavePatientDetails(unAdmissionDto);
						if(unAdmissionDto.getIsMedicoLegal()=='Y' && res3.getStatus().equals(SUCCESS))
						{
							return iUnknownPatientRegistrationService.saveMedicoLegalDetails(unAdmissionDto);
						}
						else
						{
							return res3;
						}
					} else {
						return new Response(ERROR, null, "Error while adding in admission Details", null, null);
					}
				} else {
					return new Response(ERROR, null, "ERROR while adding admission", null, null);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getActivePriorityList", method = RequestMethod.GET)
	public @ResponseBody Response getPriorityMasterList() {
		try {
			return iUnknownPatientRegistrationService.getPriorityMasterList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/patientSearchByMultipleCriteria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response patientSearchByMultipleCriteria(@RequestBody PatientSearchDto patientSearchDto) {
		try {
			return iUnknownPatientRegistrationService.patientSearchByMultipleCriteria(patientSearchDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}