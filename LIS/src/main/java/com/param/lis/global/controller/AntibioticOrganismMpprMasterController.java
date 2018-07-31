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
import com.param.lis.global.dto.AntibioticOrganismMpprMasterDto;
import com.param.lis.global.service.IAntibioticOrganismMpprMasterService;
@RestController
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping("/LIS/global")
public class AntibioticOrganismMpprMasterController implements ICommonConstants, IError{

	@Autowired
	private IAntibioticOrganismMpprMasterService iAntibioticOrganismMpprMasterService;
	
	@RequestMapping(value = "/addAntibioticOrganismMpprMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addAntibioticOrganismMpprMaster(@RequestBody AntibioticOrganismMpprMasterDto antibioticAdditionMasterDto){
		try{
			return iAntibioticOrganismMpprMasterService.addAntibioticOrganismMpprMaster(antibioticAdditionMasterDto);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTIC_ADD_FAIL, null, null);
	}
	
	@RequestMapping(value = "/listAntibioticOrganismMpprMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listAntibioticOrganismMpprMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<AntibioticOrganismMpprMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticOrganismMpprMasterService.listAntibioticOrganismMpprMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getToTalAntibioticOrganismMpprMaster/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalAntibioticOrganismMpprMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<AntibioticOrganismMpprMasterDto, Integer> response = null;
			try
				{
					response = iAntibioticOrganismMpprMasterService.getToTalAntibioticOrganismMpprMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	@RequestMapping(value = "/activateInactivateAntibioticOrganismMpprMaster/{orgId}/{organismId}/{status}", method = RequestMethod.GET)
	public @ResponseBody Response activateInactivateAntibioticOrganismMpprMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "organismId") Integer organismId,
			@PathVariable(value = "status") Character status)
	{
		Response<AntibioticOrganismMpprMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticOrganismMpprMasterService.activateInactivateAntibioticOrganismMpprMaster(orgId, organismId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	
	@RequestMapping(value = "/updateAntibioticOrganismMpprMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAntibioticOrganismMpprMaster(@RequestBody AntibioticOrganismMpprMasterDto antibioticAdditionMasterDto)
	{
		Response<AntibioticOrganismMpprMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticOrganismMpprMasterService.updateAntibioticOrganismMpprMaster(antibioticAdditionMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	@RequestMapping(value = "/getAntibioticOrganismMpprMasterById/{orgId}/{antiboiticClassId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticOrganismMpprMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antiboiticClassId") Integer antiboiticClassId)
	{
		Response<AntibioticOrganismMpprMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticOrganismMpprMasterService.getAntibioticOrganismMpprMasterById(orgId, antiboiticClassId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	@RequestMapping(value = "/getAntibioticByOrganismId/{antiboiticClassId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticByOrganismId(
			@PathVariable(value = "antiboiticClassId") Integer antiboiticClassId)
	{
		Response<AntibioticOrganismMpprMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticOrganismMpprMasterService.getAntibioticByOrganismId(antiboiticClassId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

}