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
import com.param.lis.global.dto.BactClassificationMasterDto;
import com.param.lis.global.dto.BacteriaMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.service.IBacteriaClassficationMasterService;
import com.param.lis.global.service.IBacteriaMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class BacteriaClassficationMasterController implements ICommonConstants, IError{

	@Autowired
	private IBacteriaClassficationMasterService iBacteriaClassficationMasterService;
	
	@RequestMapping(value = "/addBacteriaClassfication" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addBacteriaClassficationMaster(@RequestBody BactClassificationMasterDto bactClassificationMasterDto){
		try{
			return iBacteriaClassficationMasterService.addBacteriaClassficationMaster(bactClassificationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BACTERIA_CLASSFICATION_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddBacteriaClassficationById/{orgId}/{bactClassificationId}", method = RequestMethod.GET)
	public @ResponseBody Response getaddBacteriaClassficationMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "bactClassificationId") Integer bactClassificationId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iBacteriaClassficationMasterService.getBacteriaClassficationMasterById(orgId, bactClassificationId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateBacteriaClassficationMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBacteriaClassficationMaster(@RequestBody BactClassificationMasterDto bactClassificationMasterDto)
	{
		Response<BactClassificationMasterDto, Integer> response = null;
		try
		{
			response = iBacteriaClassficationMasterService.updateBacteriaClassficationMaster(bactClassificationMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveBacteriaClassfication/{orgId}/{bacteriaId}/{bacteriaStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateBacteriaClassficationMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "bacteriaId") Integer bacteriaId,
			@PathVariable(value = "bacteriaStatus") Character bacteriaStatus)
	{
		Response<BactClassificationMasterDto, Integer> response = null;
		try
		{
			response = iBacteriaClassficationMasterService.ActivateInactivateBacteriaClassficationMaster(orgId, bacteriaId, bacteriaStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listBacteriaClassfication/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listBacteriaClassficationMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iBacteriaClassficationMasterService.listBacteriaClassficationMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistBacteriaClassfication/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTallistBacteriaClassficationMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<BactClassificationMasterDto, Integer> response = null;
			try
				{
					response = iBacteriaClassficationMasterService.getToTalBacteriaClassficationMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
