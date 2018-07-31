package com.param.lis.transaction.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.transaction.service.IReportHandOverService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/ReportHandOver")
public class ReportHandOverController implements ICommonConstants, IError 
{
	@Autowired
	private IReportHandOverService iReportHandOverService;

	final static Logger logger = Logger.getLogger(BiochemValidationWorklistController.class);
	
	@RequestMapping(value = "/ReportHandOverList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReportHandList(
			@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<LabResultMasterDto, Integer> response = null;
		try
		{
			response = iReportHandOverService.getReportHandList(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@RequestMapping(value = "/ReportHandOverList/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReportHandListCount(@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<LabResultMasterDto, Integer> response = null;
		try
		{
			response = iReportHandOverService.getReportHandListCount( bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
