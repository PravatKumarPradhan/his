package com.param.global.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.service.IServiceMasterService;


@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping(value="billing")
public class ServiceMasterController implements IError, ICommonConstants{
	
	@Autowired
	private IServiceMasterService iServiceMasterService; 
	
	
	@RequestMapping(value="ok", method=RequestMethod.GET)
	public Response saveServiceMaster() {
		/*System.out.println("Ganpati Bappa Moraya!!");*/
		return new Response("sucess", "200", "", null, null);
	}
	
	@RequestMapping(value="saveOrganizationServiceMaster",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response saveOrganizationServiceMaster(@RequestBody ServiceMasterDto xServiceMasterDto) {
		try {
			return iServiceMasterService.saveOrganizationServiceMaster(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value="/changeOrganizationServiceMasterStatus", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response changeOrganizationServiceMasterStatus(@RequestBody ServiceMasterDto xServiceMasterDto) {
		try {
			return iServiceMasterService.changeOrganizationServiceMasterStatus(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/listOrganizationServiceMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public Response listOrganizationServiceMasterMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage){
		try{
			return iServiceMasterService.listOrganizationServiceMasterMaster(orgId, offset, recordPerPage);
		
		} catch (Exception e){
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getOrganizationServiceMasterCount/{orgId}", method = RequestMethod.GET)
	public Response getOrganizationServiceMasterTotalCount(@PathVariable(value= "orgId") Integer orgId){
			try{
				return iServiceMasterService.getOrganizationServiceMasterTotalCount(orgId);
			}
			catch (Exception e){
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			}
		}
	
	@RequestMapping(value="/getOrganizationServiceMasterById", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getOrganizationServiceMasterById(@RequestBody ServiceMasterDto xServiceMasterDto) {
		try {
			return iServiceMasterService.getOrganizationServiceMasterById(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value="updateGlobalServiceMaster", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response updateOrganizationServiceMaster(@RequestBody ServiceMasterDto xServiceMasterDto) {
		try {
			return iServiceMasterService.updateOrganizationServiceMaster(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value="/organizationMaster/speciality/{specialityId}/subSpeciality/{subSpecialityId}/org/{orgId}",method=RequestMethod.GET)
	public Response getOrganizationMasterByDepartmentAndSUbDepartment(@PathVariable(value= "specialityId") Integer specialityId,
			@PathVariable(value= "subSpecialityId") Integer subSpecialityId, @PathVariable(value= "orgId") Integer orgId) {
		try {
			ServiceMasterDto xServiceMasterDto = new ServiceMasterDto();
			xServiceMasterDto.setOrganizationId(orgId);
			xServiceMasterDto.setSpecialityId(specialityId);
			xServiceMasterDto.setSubSpecialityId(subSpecialityId);
			return iServiceMasterService.getOrganizationServiceMasterBySpecialityAndSubSpeciality(xServiceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/serviceMaster/details", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getserviceMasterDetailsById(@RequestBody ServiceMasterDto serviceMasterDto){
			try{
				return iServiceMasterService.getServiceDetailsByServiceId(serviceMasterDto);
			}
			catch (Exception e){
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			}
	}
	
	
	
}
