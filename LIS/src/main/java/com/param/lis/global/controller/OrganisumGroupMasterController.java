package com.param.lis.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.OrganisumGroupMasterDto;
import com.param.lis.global.service.IOrganisumGroupMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class OrganisumGroupMasterController implements ICommonConstants, IError{

	@Autowired
	private IOrganisumGroupMasterService iOrganisumGroupMasterService;
	
	@RequestMapping(value = "/addOrganisumGroup" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addOrganisumGroupMaster(@RequestBody OrganisumGroupMasterDto organisumGroupMasterDto){
		try{
			return iOrganisumGroupMasterService.addOrganisumGroupMaster(organisumGroupMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ORGANISUM_GROUP_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddOrganisumGroupById/{orgId}/{organisumGroupId}", method = RequestMethod.GET)
	public @ResponseBody Response getOrganisumGroupMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "organisumGroupId") Integer organisumGroupId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iOrganisumGroupMasterService.getOrganisumGroupMasterById(orgId, organisumGroupId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateOrganisumGroupMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateOrganisumGroupMaster(@RequestBody OrganisumGroupMasterDto organisumGroupMasterDto)
	{
		Response<OrganisumGroupMasterDto, Integer> response = null;
		try
		{
			response = iOrganisumGroupMasterService.updateOrganisumGroupMaster(organisumGroupMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveOrganisumGroup/{orgId}/{organisumGroupId}/{organisumGroupStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateOrganisumGroupMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "organisumGroupId") Integer organisumGroupId,
			@PathVariable(value = "organisumGroupStatus") Character organisumGroupStatus)
	{
		Response<OrganisumGroupMasterDto, Integer> response = null;
		try
		{
			response = iOrganisumGroupMasterService.ActivateInactivateOrganisumGroupMaster(orgId, organisumGroupId, organisumGroupStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listOrganisumGroup/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listOrganisumGroupMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iOrganisumGroupMasterService.listOrganisumGroupMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistlistOrganisumGroup/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalOrganisumGroupMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<OrganisumGroupMasterDto, Integer> response = null;
			try
				{
					response = iOrganisumGroupMasterService.getToTalOrganisumGroupMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
