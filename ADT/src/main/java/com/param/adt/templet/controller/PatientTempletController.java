package com.param.adt.templet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.adt.templet.dto.PatientTempletDto;
import com.param.adt.templet.service.IPatientTempletService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@RestController
@RequestMapping("api/adt")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PatientTempletController implements ICommonConstants {

	@Autowired
	IPatientTempletService iPatientTempletService;

	@RequestMapping(value = "/savePatientTemplet", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientTemplet(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId,
			@RequestBody PatientTempletDto patientTempletDto) {
		try {
			patientTempletDto.setUnitId(unitId);
			patientTempletDto.setOrganizationId(organizationId);
			return iPatientTempletService.savePatientTemplet(patientTempletDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
