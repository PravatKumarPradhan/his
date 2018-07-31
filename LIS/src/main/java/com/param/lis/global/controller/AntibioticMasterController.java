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
import com.param.lis.global.dto.AntibioticMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.HourMasterDto;
import com.param.lis.global.service.IAntibioticMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class AntibioticMasterController implements ICommonConstants, IError{

	@Autowired
	private IAntibioticMasterService iAntibioticMasterService;
	
	@RequestMapping(value = "/saveAntibiotic" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addAntibioticMaster(@RequestBody AntibioticMasterDto antibioticMasterDto){
		try{
			return iAntibioticMasterService.addAntibioticMaster(antibioticMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTIC_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAntibioticById/{orgId}/{antibioticsId}", method = RequestMethod.GET)
	public @ResponseBody Response getAntibioticMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antibioticsId") Integer antibioticsId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iAntibioticMasterService.getAntibioticMasterById(orgId, antibioticsId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateAntibiotic", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateAntibioticMaster(@RequestBody AntibioticMasterDto AntibioticMasterDto)
	{
		Response<AntibioticMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticMasterService.updateAntibioticMaster(AntibioticMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activateInactivateAntibiotic/{orgId}/{antibioticsId}/{antibioticsStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateAntibioticMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "antibioticsId") Integer antibioticsId,
			@PathVariable(value = "antibioticsStatus") Character antibioticsStatus)
	{
		Response<AntibioticMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticMasterService.ActivateInactivateAntibioticMaster(orgId, antibioticsId, antibioticsStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listAntibiotic/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listAntibioticMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iAntibioticMasterService.listAntibioticMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTalAntibiotic/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalAntibioticMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<AntibioticMasterDto, Integer> response = null;
			try
				{
					response = iAntibioticMasterService.getToTalAntibioticMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listOfAntibiotic", method = RequestMethod.GET)
	public @ResponseBody Response getListHourMaster()
	{
		Response<AntibioticMasterDto, Integer> response = null;
		try
		{
			response = iAntibioticMasterService.getListAntibioticMaster();
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
