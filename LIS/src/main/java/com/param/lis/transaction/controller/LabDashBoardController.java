package com.param.lis.transaction.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dto.LabCommonDto;
import com.param.lis.transaction.dto.LabDashBoardDetailsDto;
import com.param.lis.transaction.dto.LabDashBoardDto;
import com.param.lis.transaction.dto.SearchCommonDto;
import com.param.lis.transaction.service.ILabDashBoardService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS")
public class LabDashBoardController  implements ICommonConstants, IError{
  
  @Autowired
  private ILabDashBoardService iBioChemistryService;
  
  final static Logger logger = Logger.getLogger(LabDashBoardController.class);
  
  
  @RequestMapping(value = "/Dashboard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getlabDashBoardList(
          @RequestBody LabCommonDto labCommonDto)
  {
      Response<LabDashBoardDto, Integer> response = null;
      try
      {
          response = iBioChemistryService.getlabDashBoardList(labCommonDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  @RequestMapping(value = "/Dashboard/Count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getlabDashBoardListCount(@RequestBody LabCommonDto labCommonDto)
  {
      Response<LabDashBoardDto, Integer> response = null;
      try
      {
          response = iBioChemistryService.getlabDashBoardListCount(labCommonDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
  @RequestMapping(value = "/sampleDetails/{labSampleDtlsId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getSampleLog(@PathVariable(value = "labSampleDtlsId") Integer labSampleDtlsId,
      @PathVariable(value = "orgId") Integer orgId,
      @PathVariable(value = "orgUnitId") Integer orgUnitId)
  {
      Response<LabDashBoardDetailsDto, Integer> response = null;
      try
      {
          response = iBioChemistryService.getSampleLog(labSampleDtlsId,orgId,orgUnitId);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
  
  @RequestMapping(value = "/Dashboard/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getFilteredDashBoardList(@RequestBody SearchDto searchDto)
  {
      Response<LabDashBoardDto, Integer> response = null;
      try
      {
          response = iBioChemistryService.getFilteredDashBoardList(searchDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

  
  @RequestMapping(value = "/Dashboard/doctor/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response searchAcceptedSpecimanByPatient(@RequestBody SearchCommonDto searchCommonDto)
  {
      Response<LabDashBoardDto, Integer> response = null;
      try
      {
          response = iBioChemistryService.searchAcceptedSpecimanByPatient(searchCommonDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
  @RequestMapping(value = "/Dashboard/sampleNo/Search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response searchAcceptedSampleNo(@RequestBody SearchCommonDto searchCommonDto)
  {
      Response<LabDashBoardDto, Integer> response = null;
      try
      {
          response = iBioChemistryService.searchAcceptedSampleNo(searchCommonDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
}
