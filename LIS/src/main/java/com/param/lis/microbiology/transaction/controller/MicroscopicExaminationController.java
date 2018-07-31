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
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicroscopicExaminationMasterDto;
import com.param.lis.microbiology.transaction.service.IMicroscopicExaminationService;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/Microbiology")
public class MicroscopicExaminationController implements ICommonConstants, IError{


  final static Logger logger = Logger.getLogger(MicroscopicExaminationController.class);
  
  
  @Autowired
  IMicroscopicExaminationService iMicroscopicExaminationService;
  
  @RequestMapping(value = "/microscopic/examination/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getMicroScopicExaminationList(
          @RequestBody MicrobioParamDto microbioParamDto)
  {
      Response<SampleAcceptancePendingDto, Integer> response = null;
      try
      {
          response = iMicroscopicExaminationService.getMicroScopicExaminationList(microbioParamDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  @RequestMapping(value = "/microscopic/examination/list/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getMacroScopicExaminationListCount(@RequestBody MicrobioParamDto microbioParamDto)
  {
      Response<SampleAcceptancePendingDto, Integer> response = null;
      try
      {
          response = iMicroscopicExaminationService.getMicroScopicExaminationListCount(microbioParamDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
  @RequestMapping(value = "/microscopic/examination", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response saveMicroScopicExamination( @RequestBody List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMaster)
  {
      Response<MicroscopicExaminationMasterDto, Integer> response = null;
      try
      {
          response = iMicroscopicExaminationService.saveMicroScopicExamination(listMicroscopicExaminationMaster);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

@RequestMapping(value = "/microscopic/examination", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody Response updateMicroScopicExamination(
        @RequestBody List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMaster)
{
    Response<MicroscopicExaminationMasterDto, Integer> response = null;
    try
    {
        response = iMicroscopicExaminationService.updateMicroScopicExamination(listMicroscopicExaminationMaster);
        return response;
    } catch (Exception e)
    {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
}


@RequestMapping(value = "/microscopic/examination/{labSampleDtlsId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
public @ResponseBody Response getMicroScopicExamination(@PathVariable(value="labSampleDtlsId") Integer labSampleDtlsId,
  @PathVariable(value="orgId") Integer orgId,
  @PathVariable(value="orgUnitId") Integer orgUnitId)
{
  Response<MicroscopicExaminationMasterDto, Integer> response = null;
  try
  {
      response = iMicroscopicExaminationService.getMicroScopicExamination(labSampleDtlsId,orgId,orgUnitId);
      return response;
  } catch (Exception e)
  {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
  }
}
  
}
