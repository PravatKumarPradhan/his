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
import com.param.lis.global.dto.AntibioticClassMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.HourMasterDto;
import com.param.lis.global.service.IAntibioticsMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class AntibioticsMasterController implements ICommonConstants, IError{

	@Autowired
	private IAntibioticsMasterService iAntibioticsMasterService;
	
	@RequestMapping(value = "/addAntibiotics" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addAntibioticsMaster(@RequestBody AntibioticClassMasterDto antibioticClassMasterDto){
		try{
			return iAntibioticsMasterService.addAntibioticsMaster(antibioticClassMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTICS_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddAntibioticsById/{orgId}/{antibioticsId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticsMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antibioticsId") Integer antibioticsId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iAntibioticsMasterService.getAntibioticsMasterById(orgId, antibioticsId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateAntibioticsMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAntibioticsMaster(@RequestBody AntibioticClassMasterDto antibioticClassMasterDto)
	{
		Response<AntibioticClassMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticsMasterService.updateAntibioticsMaster(antibioticClassMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveAntibiotics/{orgId}/{antibioticsId}/{antibioticsStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateAntibioticsMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antibioticsId") Integer antibioticsId,
			@PathVariable(value = "antibioticsStatus") Character antibioticsStatus)
	{
		Response<AntibioticClassMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticsMasterService.ActivateInactivateAntibioticsMaster(orgId, antibioticsId, antibioticsStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listAntibiotics/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listAntibioticsMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iAntibioticsMasterService.listAntibioticsMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistlistAntibiotics/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTallistlistAntibioticsMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<AntibioticClassMasterDto, Integer> response = null;
			try
				{
					response = iAntibioticsMasterService.getToTalAntibioticsMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listOfListAntibioticClass", method = RequestMethod.GET)
	public @ResponseBody Response getListHourMaster()
	{
		Response<AntibioticClassMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticsMasterService.getListAntibioticsMaster();
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
