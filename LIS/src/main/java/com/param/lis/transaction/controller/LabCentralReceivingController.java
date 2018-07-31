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

import com.param.global.dto.OrderDetailsMasterDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.LabSampleMasterDto;
import com.param.lis.transaction.dto.SampleForInOutPatient;
import com.param.lis.transaction.service.ILabCentralReceivingService;
//LabCentralReceivingController
@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/centralReceiving")
public class LabCentralReceivingController implements ICommonConstants, IError
{
	@Autowired
	private ILabCentralReceivingService iILabCentralReceivingService;

	final static Logger logger = Logger.getLogger(LabCentralReceivingController.class);

	@RequestMapping(value = "/getSampleListForInOutPatient/{orgId}/{orgUnitId}/{visitTypeId}/{deptId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response getSampleListForInOutPatient(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "visitTypeId") Integer visitTypeId,
			@PathVariable(value = "deptId") Integer deptId,@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<SampleForInOutPatient, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.getSampleListForInOutPatient(orgId, orgUnitId, visitTypeId,deptId, offset,
					recordPerPage);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/acceptOrRejectSample", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptOrRejectSample(
			@RequestBody List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.acceptOrRejectSample(listlabLabSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getCentrifugationWorklist/{orgId}/{orgUnitId}/{deptId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response getCentrifugationWorklist(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId, 
			@PathVariable(value = "deptId") Integer deptId,@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<SampleForInOutPatient, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.getCentrifugationWorklist(orgId, orgUnitId,deptId ,offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@RequestMapping(value = "/getTotalRecordOutPatient/{orgId}/{orgUnitId}/{deptId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalRecordOutPatient(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,@PathVariable(value = "deptId") Integer deptId)
	{
		Response<SampleForInOutPatient, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.getTotalRecordOutPatient(orgId, orgUnitId,deptId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getTotalRecordINPatient/{orgId}/{orgUnitId}/{deptId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalRecordINPatient(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,@PathVariable(value = "deptId") Integer deptId)
	{
		Response<SampleForInOutPatient, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.getTotalRecordINPatient(orgId, orgUnitId,deptId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	
	
	
	@RequestMapping(value = "/getTotalcentrifugationWorkListRecord/{orgId}/{orgUnitId}/{deptId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalcentrifugationWorkList(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,@PathVariable(value = "deptId") Integer deptId)
	{
		Response<SampleForInOutPatient, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.getTotalcentrifugationWorkList(orgId, orgUnitId,deptId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/getRejectedSampleList/{orgId}/{orgUnitId}/{deptId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response getRejectedSampleList(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId, @PathVariable(value = "deptId") Integer deptId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage
	)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.getRejectedSampleList(orgId, orgUnitId,deptId,offset,recordPerPage);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getTotalRecordRejectedSample/{orgId}/{orgUnitId}/{deptId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalRecordRejectedSample(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,@PathVariable(value = "deptId") Integer deptId)
	{
		Response<SampleForInOutPatient, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.getTotalRecordRejectedSample(orgId, orgUnitId,deptId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/sampleRecollect", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sampleRecollect(@RequestBody List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto)
	{
		Response<OrderDetailsMasterDto, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.sampleRecollect(listlabLabSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/Centrifugation/Department", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response sendToDepartMent(@RequestBody List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto)
	{
		Response<LabSampleDetailsMasterDto, Integer> response = null;
		try
		{
			response = iILabCentralReceivingService.sendToDepartMent(listlabLabSampleDetailsMasterDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	 /*==========================SEARCH GENRAL LAB FILTER===============================*/
	
	
	 @RequestMapping(value = "/outPatientList/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response getFilteredOutPatientList(@RequestBody SearchDto searchDto)
	  {
	      Response<LabSampleMasterDto, Integer> response = null;
	      try
	      {
	          response = iILabCentralReceivingService.getFilteredOutPatientList(searchDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	 
	 @RequestMapping(value = "/CentrifugationWorkList/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response getFilteredCentrifugationWorkList(@RequestBody SearchDto searchDto)
	  {
	      Response<LabSampleMasterDto, Integer> response = null;
	      try
	      {
	          response = iILabCentralReceivingService.getFilteredCentrifugationWorkList(searchDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	
}
