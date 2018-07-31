package com.param.lis.histopathology.transaction.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.histopathology.transaction.service.IOutSourceMasterService;
import com.param.lis.transaction.dto.LabDashBoardDto;
import com.param.lis.transaction.dto.LabSampleMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Out Sourced Pending list Assignment", tags = "OutSourceMaster")
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/OutSource")
public class OutSourceMasterController implements ICommonConstants, IError
{
	final static Logger logger = Logger.getLogger(OutSourceMasterController.class);
	
	@Autowired 
	private IOutSourceMasterService iOutSourceMasterService;
	
	
	@RequestMapping(value = "/OutSourceList", method = RequestMethod.POST)
	public @ResponseBody Response getlistOutSourceMaster(@RequestBody OutSourceMasterDto outSourceMasterDto) 
	{
		Response<OutSourceMasterDto, Integer> response = null;
		try {
			response = iOutSourceMasterService.listOutSourceMaster(outSourceMasterDto);
			return response;
		} catch (Exception e) 
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/OutSource/count", method = RequestMethod.POST)
	public @ResponseBody Response getOutSourceMasterCount(@RequestBody  OutSourceMasterDto outSourceMasterDto) {
		Response<OutSourceMasterDto, Integer> response = null;
		try {
			response = iOutSourceMasterService.getOutSourceMasterCount(outSourceMasterDto);
			return response;
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value = "/addOutSourceMasterDetails", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveOutSourceMasterDetails(@RequestBody List<OutSourceMasterDto> outSourceMasterDto) {
		Response<OutSourceMasterDto, Integer> response = null;
		try {
			response = iOutSourceMasterService.saveOutSourceMasterDetails(outSourceMasterDto);
			return response;
		} catch (Exception e) 
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value = "/uploadDocument/OutSourceData", headers=("content-type=multipart/*"), method = RequestMethod.POST)
	@ApiOperation(value="Read Lab Template Data", response = OutSourceMasterDto.class)
	@ApiResponses(value = {
	                @ApiResponse(code = 200, message = "Suceess|OK"),
	                @ApiResponse(code = 401, message = "not authorized!"),
	                @ApiResponse(code = 403, message = "forbidden!!!"),
	                @ApiResponse(code = 404, message = "not found!!!") })
	    public ResponseEntity<String> uploadDocumentData(@RequestParam("file") MultipartFile inputFile)
	    {
	        String templateData  = iOutSourceMasterService.uploadDocumentData(inputFile);
	        if(templateData==null)
	        {
	          return new ResponseEntity<>(templateData, HttpStatus.NOT_FOUND);
	        }
	        else {
	          return new ResponseEntity<>(templateData, HttpStatus.OK);
	        }
	    }

	  
	  @RequestMapping(value = "/OutSourcelist/patient/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response searchOutSourcelistPatient(@RequestBody SearchCommonDto searchCommonDto)
	  {
	      Response<LabDashBoardDto, Integer> response = null;
	      try
	      {
	          response = iOutSourceMasterService.searchOutSourcelistPatient(searchCommonDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	  
	  @RequestMapping(value = "/OutSourceList/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response getFilteredOutSourceList(@RequestBody SearchDto searchDto)
	  {
	      Response<LabSampleMasterDto, Integer> response = null;
	      try
	      {
	          response = iOutSourceMasterService.getFilteredOutSourceList(searchDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
	  
	  @RequestMapping(value = "/OutSourceCommonList/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  public @ResponseBody Response getFilteredOutSourceCommonList(@RequestBody SearchDto searchDto)
	  {
	      Response<LabSampleMasterDto, Integer> response = null;
	      try
	      {
	          response = iOutSourceMasterService.getFilteredOutSourceCommonList(searchDto);
	          return response;
	      } catch (Exception e)
	      {
	          logger.error("Exception", e);
	          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	      }
	  }
}
