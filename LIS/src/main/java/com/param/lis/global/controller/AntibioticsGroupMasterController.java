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
import com.param.lis.global.dto.AntibioticGroupMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.service.IAntibioticsGroupMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class AntibioticsGroupMasterController implements ICommonConstants, IError{

	@Autowired
	private IAntibioticsGroupMasterService iAntibioticsGroupMasterService;
	
	@RequestMapping(value = "/addAntibioticsGroup" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addAntibioticsGroupMaster(@RequestBody AntibioticGroupMasterDto antibioticGroupMasterDto){
		try{
			return iAntibioticsGroupMasterService.addAntibioticsGroupMaster(antibioticGroupMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTICS_GROUP_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAntibioticsGroupMasterById/{orgId}/{antibioticsId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticsGroupMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antibioticsId") Integer antibioticsId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iAntibioticsGroupMasterService.getAntibioticsGroupMasterById(orgId, antibioticsId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateAntibioticsGroupMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAntibioticsGroupMaster(@RequestBody AntibioticGroupMasterDto AntibioticGroupMasterDto)
	{
		Response<AntibioticGroupMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticsGroupMasterService.updateAntibioticsGroupMaster(AntibioticGroupMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activateInactivateAntibioticsGroupMaster/{orgId}/{antibioticsId}/{antibioticsStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateAntibioticsGroupMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antibioticsId") Integer antibioticsId,
			@PathVariable(value = "antibioticsStatus") Character antibioticsStatus)
	{
		Response<AntibioticGroupMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticsGroupMasterService.ActivateInactivateAntibioticsGroupMaster(orgId, antibioticsId, antibioticsStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listAntibioticsGroupMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listAntibioticsGroupMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iAntibioticsGroupMasterService.listAntibioticsGroupMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTalAntibioticsGroupMaster/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalAntibioticsGroupMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<AntibioticGroupMasterDto, Integer> response = null;
			try
				{
					response = iAntibioticsGroupMasterService.getToTalAntibioticsGroupMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
