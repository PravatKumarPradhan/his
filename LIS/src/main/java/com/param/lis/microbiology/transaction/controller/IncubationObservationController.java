package com.param.lis.microbiology.transaction.controller;

import org.apache.log4j.Logger;
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
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicrobioResultDetailsMasterDto;
import com.param.lis.microbiology.transaction.dto.MicrobioResultEntryMasterDto;
import com.param.lis.microbiology.transaction.dto.TMicroIncubationMasterDto;
import com.param.lis.microbiology.transaction.service.IincubationObservationService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/Microbiology")
public class IncubationObservationController  implements ICommonConstants, IError {
	
	@Autowired
	private IincubationObservationService iIncubationObservationService;

	final static Logger logger = Logger.getLogger(IncubationObservationController.class);
	
	@RequestMapping(value = "/incubation/observation/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getIncubationObservationList(
			@RequestBody MicrobioParamDto microbioParamDto)
	{
		Response<MicrobioResultEntryMasterDto, Integer> response = null;
		try
		{
			response = iIncubationObservationService.getIncubationObservationList(microbioParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@RequestMapping(value = "/incubation/observation/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getIncubationObservationListCount(@RequestBody MicrobioParamDto microbioParamDto)
	{
		Response<MicrobioResultEntryMasterDto, Integer> response = null;
		try
		{
			response = iIncubationObservationService.getIncubationObservationListCount(microbioParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	
	  @RequestMapping(value = "/start/incubation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody Response saveIncubationTrasaction(
	            @RequestBody TMicroIncubationMasterDto tMicroIncubationMasterDto)
	    {
	        Response<MicrobioResultDetailsMasterDto, Integer> response = null;
	        try
	        {
	            response = iIncubationObservationService.saveIncubationTrasaction(tMicroIncubationMasterDto);
	            return response;
	        } catch (Exception e)
	        {
	            logger.error("Exception", e);
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }
	  
	  @RequestMapping(value = "/restart/incubation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
      public @ResponseBody Response updateIncubationTransaction(
              @RequestBody TMicroIncubationMasterDto tMicroIncubationMasterDto)
      {
          Response<MicrobioResultDetailsMasterDto, Integer> response = null;
          try
          {
              response = iIncubationObservationService.updateIncubationTransaction(tMicroIncubationMasterDto);
              return response;
          } catch (Exception e)
          {
              logger.error("Exception", e);
              return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
          }
      }
	  
	  
	
	@RequestMapping(value = "/incubation/details/{tIncubationId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getIncubationDetails(@PathVariable(value="tIncubationId") Integer tIncubationId,
	    @PathVariable(value="orgId") Integer orgId,
	    @PathVariable(value="orgUnitId") Integer orgUnitId)
	{
		Response<MicrobioResultDetailsMasterDto, Integer> response = null;
		try
		{
			response = iIncubationObservationService.getIncubationDetails(tIncubationId,orgId,orgUnitId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
