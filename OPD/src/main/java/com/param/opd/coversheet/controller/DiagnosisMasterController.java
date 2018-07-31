package com.param.opd.coversheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.coversheet.dto.ComplaintAppointmentMapperDto;
import com.param.opd.coversheet.dto.DiagnosisMasterDto;
import com.param.opd.coversheet.service.IDiagnosisMasterService;

/*FUNCTIONALITY : DIAGNOSIS MASTER
DATE:03/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DiagnosisMasterController implements ICommonConstants{

	@Autowired
	private IDiagnosisMasterService iDiagnosisMasterService;
	
/*	@RequestMapping(value="/diagnosisAutoFillSearch", method=RequestMethod.GET )
	public @ResponseBody Response diagnosisAutoFillSearch(@RequestHeader(value="unit_id") int unitId,@RequestHeader(value="doctor_id") int doctorId,
			@RequestHeader(value="organization_id") int organizationId, @RequestParam(value="diagnosisName") String diagnosisName ) {
		try
		{
			DiagnosisMasterDto diagnosisMasterDto = new DiagnosisMasterDto();
			diagnosisMasterDto.setDiagnosisName(diagnosisName);
			diagnosisMasterDto.setUnitId(unitId);
			diagnosisMasterDto.setDoctorId(doctorId);
			diagnosisMasterDto.setOrganizationId(organizationId);
			return iDiagnosisMasterService.getListOfDiagnosisMaster(diagnosisMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}*/
	
	@RequestMapping(value="/diagnosisAutoFillSearch", method=RequestMethod.GET )
	public @ResponseBody Response diagnosisAutoFillSearch(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId, @RequestParam(value="diagnosisName") String diagnosisName ) {
		try
		{
			DiagnosisMasterDto diagnosisMasterDto = new DiagnosisMasterDto();
			diagnosisMasterDto.setDiagnosisName(diagnosisName);
			diagnosisMasterDto.setUnitId(unitId);
			diagnosisMasterDto.setOrganizationId(organizationId);
			return iDiagnosisMasterService.getListOfDiagnosisMaster(diagnosisMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
