package com.param.lis.transaction.controller;

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

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.JsonCheck;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dto.CollectedSampleDto;
import com.param.lis.transaction.dto.LabDashBoardDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.LabSampleMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;
import com.param.lis.transaction.service.ILabTransactionService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/transaction")
public class LabTransactionController implements ICommonConstants, IError
{ 
	@Autowired
	private ILabTransactionService iLabTransactionService;
	
	final static Logger logger = Logger.getLogger(LabTransactionController.class);
	
	@RequestMapping(value = "/getPhlebotomyWorlist/{orgId}/{orgUnitId}/{deptId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response getPhlebotomyWorklist(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "deptId") Integer deptId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<LabSampleMasterDto, Integer> response = null;
		try
		{
			response = iLabTransactionService.getPhlebotomyWorklist(orgId, orgUnitId,deptId,offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	

	@RequestMapping(value = "/getTotalPhlebotomyWorkRecord/{orgId}/{orgUnitId}/{deptId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalPhlebotomyWorkRecord(@PathVariable(value= "orgId") Integer orgId,
			@PathVariable(value= "orgUnitId") Integer orgUnitId,@PathVariable(value= "deptId") Integer deptId)
		{
			Response<LabSampleMasterDto, Integer> response = null;
			try
				{
					response = iLabTransactionService.getTotalPhlobotomyRecord(orgId, orgUnitId,deptId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}
	
	
	@RequestMapping(value = "/getPhlebotomyWorlistDetails/{orgId}/{orgUnitId}/{orderId}/{deptId}", method = RequestMethod.GET)
	public @ResponseBody Response getPhlebotomyWorklistDetails(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,@PathVariable(value = "orderId") Integer orderId
			,@PathVariable(value = "deptId") Integer deptId
			)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iLabTransactionService.getPhlebotomyWorklistDetails(orgId, orgUnitId, orderId,deptId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	
	@RequestMapping(value = "/sampleCollection", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sampleCollection(@RequestBody LabSampleMasterDto labSampleMasterDto)
	{
		Response<LabSampleMasterDto, Integer> response = null;
		try
		{
			/*JsonCheck.mapJsonToObject(labSampleMasterDto, LabSampleMasterDto.class);*/
			response = iLabTransactionService.sampleCollection(labSampleMasterDto);
			return response;
		} catch (Exception e)
		{	
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	
	@RequestMapping(value = "/getCollectedSample/{orgId}/{orgUnitId}/{deptId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response getCollectedSample(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage,@PathVariable(value = "deptId") Integer deptId)
	{
		Response<CollectedSampleDto, Integer> response = null;
		try
		{
			response = iLabTransactionService.getCollectedSample(orgId, orgUnitId,deptId,offset,recordPerPage);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	
	@RequestMapping(value = "/sendToCr", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sendToCr(@RequestBody List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iLabTransactionService.sendToCr(listLabSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	

	
	@RequestMapping(value = "/getTotalSendToCrRecord/{orgId}/{orgUnitId}/{deptId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalSendToCrRecord(@PathVariable(value= "orgId") Integer orgId,
			@PathVariable(value= "orgUnitId") Integer orgUnitId,@PathVariable(value= "deptId") Integer deptId)
		{
			Response<LabSampleMasterDto, Integer> response = null;
			try
				{
					response = iLabTransactionService.getTotalSendToCrRecord(orgId, orgUnitId,deptId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}

	

	
	@RequestMapping(value = "/V1/getPhlebotomyWorlistDetails/{orgId}/{orgUnitId}/{orderId}", method = RequestMethod.GET)
	public @ResponseBody Response v1GetPhlebotomyWorklistDetails(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,@PathVariable(value = "orderId") Integer orderId,@PathVariable(value = "deptId") Integer deptId)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iLabTransactionService.v1GetPhlebotomyWorklistDetails(orgId, orgUnitId, orderId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}
	
	/*
	 * Phlebotomy Work list Search
	 */
	  @RequestMapping(value = "/PhlebotomyWorklist/patient/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response searchPhlebotomyWorklistPatient(@RequestBody SearchCommonDto searchCommonDto)
	  {
	      Response<LabDashBoardDto, Integer> response = null;
	      try
	      {
	          response = iLabTransactionService.searchPhlebotomyWorklistPatient(searchCommonDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	  
	  @RequestMapping(value = "/PhlebotomyWorklist/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response getFilteredPhlebotomyWorklistPatient(@RequestBody SearchDto searchDto)
	  {
	      Response<LabSampleMasterDto, Integer> response = null;
	      try
	      {
	          response = iLabTransactionService.getFilteredPhlebotomyWorklistPatient(searchDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	  
	  /*
		 * Send to Cr Search
		 */
	
	  
	  @RequestMapping(value = "/SendToCR/patient/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response searchSendToCRPatientlist(@RequestBody SearchCommonDto searchCommonDto)
	  {
	      Response<LabDashBoardDto, Integer> response = null;
	      try
	      {
	          response = iLabTransactionService.searchSendToCRPatientlist(searchCommonDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	  
	  @RequestMapping(value = "/SendToCRlist/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response getFilteredSendToCRPatientlist(@RequestBody SearchDto searchDto)
	  {
	      Response<LabSampleMasterDto, Integer> response = null;
	      try
	      {
	    	  
	          response = iLabTransactionService.getFilteredSendToCRPatientlist(searchDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	  
	  
	  @RequestMapping(value = "/GenralLab/sampleNo/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response searchLabGenralLabSampleNo(@RequestBody SearchCommonDto searchCommonDto)
	  {
	      Response<LabDashBoardDto, Integer> response = null;
	      try
	      {
	          response = iLabTransactionService.searchLabGenralLabSampleNo(searchCommonDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }

}
