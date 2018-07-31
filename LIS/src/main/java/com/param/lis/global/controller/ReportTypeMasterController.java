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
import com.param.lis.global.dto.ReportTypeMasterDto;
import com.param.lis.global.service.IReportTypeMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class ReportTypeMasterController implements ICommonConstants, IError{

	@Autowired
	private IReportTypeMasterService iReportTypeMasterService;
	
	@RequestMapping(value = "/addReportTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addReportTypeMaster(@RequestBody ReportTypeMasterDto reportTypeMasterDto){
		try{
			return iReportTypeMasterService.addReportTypeMaster(reportTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,REPORT_TYPR_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddReportTypeById/{orgId}/{reportTypeId}", method = RequestMethod.GET)
	public @ResponseBody Response getaddReportTypeMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "reportTypeId") Integer reportTypeId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iReportTypeMasterService.getaddReportTypeMasterById(orgId, reportTypeId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateReportType", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReportTypeMaster(@RequestBody ReportTypeMasterDto reportTypeMasterDto)
	{
		Response<ReportTypeMasterDto, Integer> response = null;
		try
		{
			response = iReportTypeMasterService.updateReportTypeMaster(reportTypeMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveReportType/{orgId}/{reportTypeId}/{reportTypeStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateReportTypeMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "reportTypeId") Integer reportTypeId,
			@PathVariable(value = "reportTypeStatus") Character reportTypeStatus)
	{
		Response<ReportTypeMasterDto, Integer> response = null;
		try
		{
			response = iReportTypeMasterService.ActivateInactivateReportTypeMaster(orgId, reportTypeId, reportTypeStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listReportType/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listReportTypeMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iReportTypeMasterService.listReportTypeMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTalReportTypeMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalReportTypeMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<ReportTypeMasterDto, Integer> response = null;
			try
				{
					response = iReportTypeMasterService.getToTalReportTypeMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
