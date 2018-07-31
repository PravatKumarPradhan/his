package com.param.lis.microbiology.transaction.controller;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.entity.lis.micro.TMicroSampleMedia;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.TMicroSampleMediaDto;
import com.param.lis.microbiology.transaction.service.IMicrobiologyService;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/Microbiology")
public class MicrobiologyController implements ICommonConstants, IError
{
	@Autowired
	private IMicrobiologyService iMicrobiologyService;

	final static Logger logger = Logger.getLogger(MicrobiologyController.class);
	
	@RequestMapping(value = "/Aacceptance/Pending", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response microAcceptancePending(
			@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<SampleAcceptancePendingDto, Integer> response = null;
		try
		{
			response = iMicrobiologyService.microAcceptancePending(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@RequestMapping(value = "/Aacceptance/Pending/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response microAcceptancePendingCount(@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<SampleAcceptancePendingDto, Integer> response = null;
		try
		{
			response = iMicrobiologyService.microAcceptancePendingCount(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/Microbiology/Pending/Sample", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response microChemistryAcceptRejectSample(@RequestBody List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iMicrobiologyService.microChemistryAcceptRejectSample(listlabSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	
	@RequestMapping(value = "/MicroWorklist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMicroChemistryWorklist(@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iMicrobiologyService.getMicroChemistryWorklist(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/MicroWorklist/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMicroChemistryWorklistCount(@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iMicrobiologyService.getMicroChemistryWorklistCount(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	   @RequestMapping(value = "/incubation/media", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody Response addMediaForIncuabation(@RequestBody List<TMicroSampleMediaDto> listTMicroSampleMediaDto)
	    {
	        Response<LabSampleDetailsMasterDto, Integer> response = null;
	        try
	        {
	            response = iMicrobiologyService.addMediaForIncuabation(listTMicroSampleMediaDto);
	            return response;
	        } catch (Exception e)
	        {
	            logger.error("Exception", e);
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }
	   
	    @RequestMapping(value = "/incubation/media/{labSampleDtlsId}/{orgId}/{orgUniId}", method = RequestMethod.GET)
	    public @ResponseBody Response getAddedMediaForIncuabation(@PathVariable(value = "labSampleDtlsId") Integer labSampleDtlsId,
	        @PathVariable(value = "orgId") Integer orgId,
	        @PathVariable(value = "orgUniId") Integer orgUniId)
	    {
	        Response<LabSampleDetailsMasterDto, Integer> response = null;
	        try
	        {
	            response = iMicrobiologyService.getAddedMediaForIncuabation(labSampleDtlsId,orgId,orgUniId);
	            return response;
	        } catch (Exception e)
	        {
	            logger.error("Exception", e);
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }
	
	
	@RequestMapping(value = "/Worklist/ReportEntry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sendForIncubationObjservation(@RequestBody List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response =iMicrobiologyService.sendForIncubationObjservation(listlabSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
