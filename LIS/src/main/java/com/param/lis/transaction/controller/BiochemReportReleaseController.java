package com.param.lis.transaction.controller;

import java.util.List;
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
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;
import com.param.lis.transaction.service.IBiochemReportReleaseService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/BioChemistry")
public class BiochemReportReleaseController implements ICommonConstants, IError 
{
	@Autowired
	private IBiochemReportReleaseService iBiochemReportReleaseService;

	final static Logger logger = Logger.getLogger(BiochemValidationWorklistController.class);
	
	@RequestMapping(value = "/ReportReleaseWorkList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReportReleaseWorkList(
			@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<SampleAcceptancePendingDto, Integer> response = null;
		try
		{
			response = iBiochemReportReleaseService.getReportReleaseWorkList(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	@RequestMapping(value = "/ReportReleaseWorkList/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReportReleaseWorkListCount(@RequestBody BioChemParamDto bioChemParamDto)
	{
		Response<SampleAcceptancePendingDto, Integer> response = null;
		try
		{
			response = iBiochemReportReleaseService.getReportReleaseWorkListCount(bioChemParamDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/ReportReleaseWorkList/Details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReportReleaseWorkListDetails(@RequestBody List<BioChemParamDto> bioChemParamDtoList)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iBiochemReportReleaseService.getReportReleaseWorkListDetails(bioChemParamDtoList);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/ReportReleaseWorkList/EntryDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveReportReleaseWorkList(@RequestBody List<LabResultMasterDto> listLabResultMasterDto)
	{
		Response<LabResultMasterDto, Integer> response = null;
		try
		{
			response = iBiochemReportReleaseService.saveReportReleaseWorkList(listLabResultMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}	

}
