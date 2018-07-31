package com.param.lis.unit.controller;

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
import com.param.lis.unit.dto.ParameterMasterDto;
import com.param.lis.unit.service.IParameterMasterService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/unit")
public class ParameterMasterController implements ICommonConstants, IError
{
	@Autowired
	private IParameterMasterService iParameterMasterService;
	
	final static Logger logger = Logger.getLogger(ParameterMasterController.class);
	
	@RequestMapping(value = "/addParameter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addParameter(@RequestBody ParameterMasterDto parameterMasterDto)
	{
		Response<ParameterMasterDto, Integer> response = null;
		try
		{
			response = iParameterMasterService.addParameter(parameterMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@RequestMapping(value = "/getParameterById/{orgId}/{orgUnitId}/{parameterId}", method = RequestMethod.GET)
	public @ResponseBody Response getParameterById(@PathVariable(value = "orgId") Integer orgId,
	    @PathVariable(value = "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "parameterId") Integer parameterId)
	{
		Response<ParameterMasterDto, Integer> response = null;
		try
		{
			response = iParameterMasterService.getParameterById(orgId,orgUnitId, parameterId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@RequestMapping(value = "/updateParameter", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateParameter(@RequestBody ParameterMasterDto parameterMasterDto)
	{
		Response<ParameterMasterDto, Integer> response = null;
		try
		{
			response = iParameterMasterService.updateParameter(parameterMasterDto);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveParameter/{orgId}/{parameterId}/{paramStatus}", method = RequestMethod.GET)
	public @ResponseBody Response activeInactiveParameter(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "parameterId") Integer parameterId,
			@PathVariable(value = "paramStatus") Character paramStatus)
	{
		Response<ParameterMasterDto, Integer> response = null;
		try
		{
			response = iParameterMasterService.activeInactiveParameter(orgId, parameterId, paramStatus);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listParameterMaster/{orgId}/{orgUnitId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listSampleMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<ParameterMasterDto, Integer> response = null;
		try
		{
			response = iParameterMasterService.listParameterMaster(orgId,orgUnitId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getTotalParameterMasterRecord/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalParameterMasterRecord(@PathVariable(value= "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId)
		{
			Response<ParameterMasterDto, Integer> response = null;
			try
				{
					response = iParameterMasterService.getTotalParameterMasterRecord(orgId, orgUnitId);
					return response;
				}
			catch (Exception e)
				{
				    logger.error("Exection", e);
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	

     
      @RequestMapping(value = "/refRanges/{parameterId}/{refrangeTypeId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
      public @ResponseBody Response getRefReangeByParameter(
          @PathVariable(value = "parameterId") Integer parameterId,
          @PathVariable(value = "refrangeTypeId") Integer refrangeTypeId,@PathVariable(value= "orgId") Integer orgId,
              @PathVariable(value = "orgUnitId") Integer orgUnitId)
          {
              Response<ParameterMasterDto, Integer> response = null;
              try
                  {
                      response = iParameterMasterService.getRefReangeByParameter(parameterId,refrangeTypeId,orgId, orgUnitId);
                      return response;
                  }
              catch (Exception e)
                  {
                      logger.error("Exection", e);
                      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
                  }
          }


      @RequestMapping(value = "/helpValues/{parameterId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
      public @ResponseBody Response getHelpValueByParameter(
          @PathVariable(value = "parameterId") Integer parameterId,
          @PathVariable(value= "orgId") Integer orgId,
              @PathVariable(value = "orgUnitId") Integer orgUnitId)
          {
              Response<ParameterMasterDto, Integer> response = null;
              try
                  {
                      response = iParameterMasterService.getHelpValueByParameter(orgId, orgUnitId, parameterId);
                      return response;
                  }
              catch (Exception e)
                  {
                      logger.error("Exection", e);
                      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
                  }
          }
      
}
