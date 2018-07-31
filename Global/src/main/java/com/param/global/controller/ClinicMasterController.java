package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ClinicMasterDto;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.service.IClinicMasterService;

/*FUNCTIONALITY : CLINIC MASTER
DATE:08/05/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClinicMasterController implements ICommonConstants{

	@Autowired
	private IClinicMasterService iClinicMasterService;
	
	@RequestMapping(value="/getClinicMasterListByDoctorId", method=RequestMethod.GET)
	public @ResponseBody Response getClinicMasterListByDoctorId(@RequestHeader(value="unit_id") int unitId, @RequestHeader(value="organization_id") int organizationId, 
			@RequestParam(value="doctor_id") int doctorId) {
		try
		{
			ClinicMasterDto clinicMasterDto= new ClinicMasterDto();
			clinicMasterDto.setUnitId(unitId);
			clinicMasterDto.setOrganizationId(organizationId);
			clinicMasterDto.setDoctorId(doctorId);
			return iClinicMasterService.getListOfClinicMasterByDoctorId(clinicMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/saveClinicMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveClinicMaster(@RequestBody ClinicMasterDto clinicMasterDto){
		try{
			return iClinicMasterService.saveClinicMaster(clinicMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/updateClinicMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response updateClinicMaster(@RequestBody ClinicMasterDto clinicMasterDto){
		try{
			return iClinicMasterService.updateClinicMaster(clinicMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getClinicMasterList/{organizationId}/{unitId}/{offset}/{noOfRecordsPerPage}" , method = RequestMethod.GET)
	public Response getClinicMasterList(@PathVariable("organizationId") Integer organizationId , @PathVariable("unitId") Integer unitId,
										@PathVariable("offset") Integer offset , @PathVariable("noOfRecordsPerPage") Integer noOfRecordsPerPage){
		try{
			ClinicMasterDto clinicMasterDto = new ClinicMasterDto();
			clinicMasterDto.setOrganizationId(organizationId);
			clinicMasterDto.setUnitId(unitId);
			return iClinicMasterService.getClinicMasterListByOrgANdUnitId(clinicMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/getClinicMasterCount/{organizationId}/{unitId}" , method = RequestMethod.GET)
	public Response getClinicMasterCount(@PathVariable("organizationId") Integer organizationId , @PathVariable("unitId") Integer unitId){
		try{
			return iClinicMasterService.getClinicMasterCountByOrgAndUnit(organizationId, unitId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getClinicMasterById/{clinicMasterId}/{organizationId}/{unitId}" , method = RequestMethod.GET)
	public Response getClinicMasterById(@PathVariable("organizationId") Integer organizationId , @PathVariable("unitId") Integer unitId,@PathVariable("clinicMasterId") Integer clinicMasterId){
		try{
			ClinicMasterDto clinicMasterDto = new ClinicMasterDto();
			clinicMasterDto.setOrganizationId(organizationId);
			clinicMasterDto.setUnitId(unitId);
			clinicMasterDto.setClinicMasterId(clinicMasterId);
			return iClinicMasterService.getClinicMasterById(clinicMasterDto);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updatedClinicStatus/{clinicMasterId}/{status}" , method = RequestMethod.GET)
	public Response updateClinicStatus(@PathVariable("clinicMasterId")Integer clinicMasterId , @PathVariable("status") Character status){
		try{
			return iClinicMasterService.updateClinicMasterStatus(clinicMasterId, status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
