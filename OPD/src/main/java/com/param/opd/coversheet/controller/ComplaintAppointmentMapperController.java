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
import com.param.opd.coversheet.service.IComplaintAppointmentMapperService;

/*FUNCTIONALITY : COMPLAINT APPOINTMENT
DATE:03/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/opd")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ComplaintAppointmentMapperController implements ICommonConstants{

	@Autowired
	private IComplaintAppointmentMapperService iComplaintAppointmentMapperService;
	
	@RequestMapping(value="/getComplaintAppointment", method=RequestMethod.GET)
	public @ResponseBody Response listOfComplaintAppointment(@RequestHeader(value="unit_id") int unitId, @RequestHeader(value="organization_id") int organizationId,
			@RequestParam(value="encounterId") int encounterId,@RequestParam(value="roleId") int roleId) {
		try
		{
			ComplaintAppointmentMapperDto complaintAppointmentMapperDto= new ComplaintAppointmentMapperDto();
			complaintAppointmentMapperDto.setUnitId(unitId);
			complaintAppointmentMapperDto.setOrganizationId(organizationId);
			complaintAppointmentMapperDto.setEncounterId(encounterId);
			complaintAppointmentMapperDto.setRoleId(roleId);
			return iComplaintAppointmentMapperService.getListOfComplaintAppointmentMapper(complaintAppointmentMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/saveComplaintAppointment", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveComplaintAppointment(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody ComplaintAppointmentMapperDto complaintAppointmentMapperDto) {
		try
		{
			complaintAppointmentMapperDto.setUnitId(unitId);
			complaintAppointmentMapperDto.setOrganizationId(organizationId);
			return iComplaintAppointmentMapperService.saveComplaintAppointmentMapper(complaintAppointmentMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/editComplaintAppointment", method=RequestMethod.GET)
	public @ResponseBody Response editComplaintAppointment(@RequestHeader(value="unit_id") int unitId, @RequestHeader(value="organization_id") int organizationId,
			@RequestParam(value="complaintAppoId") int complaintAppoId) {
		try
		{
			ComplaintAppointmentMapperDto complaintAppointmentMapperDto= new ComplaintAppointmentMapperDto();
			complaintAppointmentMapperDto.setUnitId(unitId);
			complaintAppointmentMapperDto.setOrganizationId(organizationId);
			complaintAppointmentMapperDto.setComplaintAppoId(complaintAppoId);
			return iComplaintAppointmentMapperService.editListOfComplaintAppointmentMapper(complaintAppointmentMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	@RequestMapping(value="/updateComplaintAppointment", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateComplaintAppointment(@RequestHeader(value="unit_id") int unitId,
			@RequestHeader(value="organization_id") int organizationId,@RequestBody ComplaintAppointmentMapperDto complaintAppointmentMapperDto) {
		try
		{
			complaintAppointmentMapperDto.setUnitId(unitId);
			complaintAppointmentMapperDto.setOrganizationId(organizationId);
			return iComplaintAppointmentMapperService.updateComplaintAppointmentMapper(complaintAppointmentMapperDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
