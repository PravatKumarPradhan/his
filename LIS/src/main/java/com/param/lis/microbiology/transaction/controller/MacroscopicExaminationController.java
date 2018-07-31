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
import com.param.lis.microbiology.transaction.dto.MicrobioResultDetailsMasterDto;
import com.param.lis.microbiology.transaction.dto.TMacroscopicExaminationMasterDto;
import com.param.lis.microbiology.transaction.service.IMacroscopicExaminationService;
import com.param.lis.transaction.dto.SampleAcceptancePendingDto;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/Microbiology")
public class MacroscopicExaminationController implements ICommonConstants, IError{
  
  final static Logger logger = Logger.getLogger(MacroscopicExaminationController.class);
  
  @Autowired
  IMacroscopicExaminationService iMacroscopicExaminationService;
  
  @RequestMapping(value = "/macroscopic/examination/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getMacroScopicExaminationList(
          @RequestBody MicrobioParamDto microbioParamDto)
  {
      Response<SampleAcceptancePendingDto, Integer> response = null;
      try
      {
          response = iMacroscopicExaminationService.getMacroScopicExaminationList(microbioParamDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  @RequestMapping(value = "/macroscopic/examination/list/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getMacroScopicExaminationListCount(@RequestBody MicrobioParamDto microbioParamDto)
  {
      Response<SampleAcceptancePendingDto, Integer> response = null;
      try
      {
          response = iMacroscopicExaminationService.getMacroScopicExaminationListCount(microbioParamDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
  
    @RequestMapping(value = "/macroscopic/examination", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
      public @ResponseBody Response saveMacroScopicExamination(
              @RequestBody List<TMacroscopicExaminationMasterDto> tMacroscopicExaminationMasterDtoList)
      {
          Response<MicrobioResultDetailsMasterDto, Integer> response = null;
          try
          {
              response = iMacroscopicExaminationService.saveMacroScopicExamination(tMacroscopicExaminationMasterDtoList);
              return response;
          } catch (Exception e)
          {
              logger.error("Exception", e);
              return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
          }
      }
    
    @RequestMapping(value = "/macroscopic/examination", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Response updateMacroScopicExamination(
            @RequestBody List<TMacroscopicExaminationMasterDto> tMacroscopicExaminationMasterDtoList)
    {
        Response<MicrobioResultDetailsMasterDto, Integer> response = null;
        try
        {
            response = iMacroscopicExaminationService.updateMacroScopicExamination(tMacroscopicExaminationMasterDtoList);
            return response;
        } catch (Exception e)
        {
            logger.error("Exception", e);
            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
        }
    }
    
    
  
  @RequestMapping(value = "/macroscopic/examination/{tMacroExaId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getMacroScopicExamination(@PathVariable(value="tMacroExaId") Integer tMacroExaId,
      @PathVariable(value="orgId") Integer orgId,
      @PathVariable(value="orgUnitId") Integer orgUnitId)
  {
      Response<MicrobioResultDetailsMasterDto, Integer> response = null;
      try
      {
          response = iMacroscopicExaminationService.getMacroScopicExamination(tMacroExaId,orgId,orgUnitId);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

}
