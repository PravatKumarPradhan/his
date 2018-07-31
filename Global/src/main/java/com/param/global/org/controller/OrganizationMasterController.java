package com.param.global.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.dto.OrganizationMasterDto;
import com.param.global.org.dto.OrganizationUnitLicenceDetailsDto;
import com.param.global.org.service.IGlobalOrganizationMasterService;

@Controller
@SuppressWarnings("rawtypes")
public class OrganizationMasterController implements ICommonConstants{

	@Autowired
	IGlobalOrganizationMasterService iGlobalOrganizationMasterService;
	
	
	@RequestMapping(value="/organization/save",method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveOrganizationMaster(@RequestBody OrganizationMasterDto organizationMasterDto){
		try{
			return iGlobalOrganizationMasterService.saveOrganizationMaster(organizationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value="/organization/update",method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateOrganizationMaster(@RequestBody OrganizationMasterDto organizationMasterDto){
		try{
			return iGlobalOrganizationMasterService.updateOrganizationMaster(organizationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value="/organization/{organizationId}" , method=RequestMethod.GET)
	public @ResponseBody Response getOrganizationById(@PathVariable("organizationId") int organizationId){
		try{
			return iGlobalOrganizationMasterService.getOrganizationById(organizationId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value="/allArganizationList/{offSet}/{noOfRecords}" , method=RequestMethod.GET)
	public @ResponseBody Response allArganizationList(@PathVariable("offSet") int offSet , @PathVariable("noOfRecords") int noOfRecords){
		try{
			OrganizationMasterDto organizationMasterDto = new OrganizationMasterDto();
			organizationMasterDto.setOffset(offSet);
			organizationMasterDto.setNoOfRecordsPerPage(noOfRecords);
			return iGlobalOrganizationMasterService.getAllOrganizationMasterList(organizationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value="/organization/count" , method=RequestMethod.GET)
	public @ResponseBody Response getOrganizationCount(){
		try{
			return iGlobalOrganizationMasterService.getOrganizationCount();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value="/organization/status/{organizationId}/{status}" , method=RequestMethod.GET)
	public @ResponseBody Response updateOrganizationStatus(@PathVariable("organizationId") int organizationId , @PathVariable("status") char status){
		try{
			OrganizationMasterDto organizationMasterDto = new OrganizationMasterDto();
			organizationMasterDto.setOrganizationId(organizationId);
			organizationMasterDto.setStatus(status);
			return iGlobalOrganizationMasterService.updateOrganizationStatus(organizationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value="/licenceDetailsByOrganization" , method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOrganizationById(@RequestBody OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto){
		try{
			return iGlobalOrganizationMasterService.getLicenceDetailsByOrgUnitId(organizationUnitLicenceDetailsDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value="/organization/active" , method=RequestMethod.GET)
	public @ResponseBody Response getActiveOrganizations(){
		try{
			return iGlobalOrganizationMasterService.getActiveOrganizationList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
}
