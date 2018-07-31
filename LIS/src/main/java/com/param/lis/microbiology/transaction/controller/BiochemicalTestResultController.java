package com.param.lis.microbiology.transaction.controller;

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
import com.param.lis.microbiology.transaction.dto.MicrobioResultEntryMasterDto;
import com.param.lis.microbiology.transaction.dto.TBiochemicalTestMasterDto;
import com.param.lis.microbiology.transaction.service.IBiochemicalTestResultService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/Microbiology")
public class BiochemicalTestResultController  implements ICommonConstants, IError{
  @Autowired
  private IBiochemicalTestResultService iBiochemicalTestResultService;

  final static Logger logger = Logger.getLogger(BiochemicalTestResultController.class);
  
  @RequestMapping(value = "/biochemical/result/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getBiochemicalWorkList(
          @RequestBody MicrobioParamDto microbioParamDto)
  {
      Response<MicrobioResultEntryMasterDto, Integer> response = null;
      try
      {
          response = iBiochemicalTestResultService.getBiochemicalWorkList(microbioParamDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  @RequestMapping(value = "/biochemical/result/list/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response getBiochemicalWorkListCount(@RequestBody MicrobioParamDto microbioParamDto)
  {
      Response<MicrobioResultEntryMasterDto, Integer> response = null;
      try
      {
          response = iBiochemicalTestResultService.getBiochemicalWorkListCount(microbioParamDto);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
  
    @RequestMapping(value = "/biochemical/resultEntry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
      public @ResponseBody Response saveBiochemicalResult(
              @RequestBody TBiochemicalTestMasterDto tBiochemicalTestMasterDto)
      {
          Response<MicrobioResultDetailsMasterDto, Integer> response = null;
          try
          {
              response = iBiochemicalTestResultService.saveBiochemicalResult(tBiochemicalTestMasterDto);
              return response;
          } catch (Exception e)
          {
              logger.error("Exception", e);
              return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
          }
      }
    
    @RequestMapping(value = "/biochemical/resultEntry", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Response updateBiochemicalResult(
            @RequestBody TBiochemicalTestMasterDto tBiochemicalTestMasterDto)
    {
        Response<MicrobioResultDetailsMasterDto, Integer> response = null;
        try
        {
            response = iBiochemicalTestResultService.updateBiochemicalResult(tBiochemicalTestMasterDto);
            return response;
        } catch (Exception e)
        {
            logger.error("Exception", e);
            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
        }
    }
    
    
  
  @RequestMapping(value = "/biochemical/resultEntry/details/{tBiochemicalTestId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getBiochemicalResultDetails(@PathVariable(value="tBiochemicalTestId") Integer tBiochemicalTestId,
      @PathVariable(value="orgId") Integer orgId,
      @PathVariable(value="orgUnitId") Integer orgUnitId)
  {
      Response<MicrobioResultDetailsMasterDto, Integer> response = null;
      try
      {
          response = iBiochemicalTestResultService.getBiochemicalResultDetails(tBiochemicalTestId,orgId,orgUnitId);
          return response;
      } catch (Exception e)
      {
          logger.error("Exception", e);
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
}
