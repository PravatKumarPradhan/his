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
import com.param.lis.global.dto.SpecimanMasterDto;
import com.param.lis.global.service.ISpecimanMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class SpecimanMasterController implements ICommonConstants, IError{

	@Autowired
	private ISpecimanMasterService iSpecimanMasterService;
	
	@RequestMapping(value = "/addSpeciman" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addSpecimanMaster(@RequestBody SpecimanMasterDto specimanMasterDto){
		try{
			return iSpecimanMasterService.addSpecimanMaster(specimanMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,SPECIMAN_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddspecimanById/{orgId}/{specimanId}", method = RequestMethod.GET)
	public @ResponseBody Response getSpecimanMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "specimanId") Integer specimanId)
	{
		Response<SpecimanMasterDto, Integer> response = null;
		try
		{
			response = iSpecimanMasterService.getSpecimanMasteById(orgId, specimanId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateSpecimanMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSpecimanMaster(@RequestBody SpecimanMasterDto SpecimanMasterDto)
	{
		Response<SpecimanMasterDto, Integer> response = null;
		try
		{
			response = iSpecimanMasterService.updateSpecimanMaster(SpecimanMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveSpeciman/{orgId}/{specimanId}/{specimanStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateSpecimanMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "specimanId") Integer specimanId,
			@PathVariable(value = "specimanStatus") Character specimanStatus)
	{
		Response<SpecimanMasterDto, Integer> response = null;
		try
		{
			response = iSpecimanMasterService.ActivateInactivateSpecimanMaster(orgId, specimanId, specimanStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listSpeciman/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listSpecimanMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<SpecimanMasterDto, Integer> response = null;
		try
		{
			response = iSpecimanMasterService.listSpecimanMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistlistSpeciman/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTallistlistSpecimanMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<SpecimanMasterDto, Integer> response = null;
			try
				{
					response = iSpecimanMasterService.getToTalSpecimanMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/specimanGrossRequriedInSpecimanMaster/{orgId}/{specimanId}/{specimanGross}", method = RequestMethod.GET)
	public @ResponseBody Response specimanGrossRequriedInSpecimanMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "specimanId") Integer specimanId,
			@PathVariable(value = "specimanGross") Character specimanGross)
	{
		Response<SpecimanMasterDto, Integer> response = null;
		try
		{
			response = iSpecimanMasterService.specimanGrossRequriedInSpecimanMaster(orgId, specimanId, specimanGross);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/specimanBlockRequriedInSpecimanMaster/{orgId}/{specimanId}/{specimanBlock}", method = RequestMethod.GET)
	public @ResponseBody Response specimanBlockRequriedInSpecimanMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "specimanId") Integer specimanId,
			@PathVariable(value = "specimanBlock") Character specimanBlock)
	{
		Response<SpecimanMasterDto, Integer> response = null;
		try
		{
			response = iSpecimanMasterService.specimanBlockRequriedInSpecimanMaster(orgId, specimanId, specimanBlock);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}


}
