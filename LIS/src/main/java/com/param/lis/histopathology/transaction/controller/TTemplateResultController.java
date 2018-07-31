package com.param.lis.histopathology.transaction.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.entity.lis.histo.TTemplateResult;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.TTemplateResultDto;
import com.param.lis.histopathology.transaction.service.ITTemplateResultService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Slide Creation Controller", tags = "TSlideMaster")
@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/LIS/histopathology")
public class TTemplateResultController implements ICommonConstants, IError 
{
   final Logger logger = Logger.getLogger(TTemplateResultController.class);

  @Autowired
  private ITTemplateResultService iTTemplateResultService;


  @RequestMapping(value = "/saveTemplateResult", method = RequestMethod.POST)
  public @ResponseBody Response createSlides(
      @RequestBody TTemplateResultDto tTemplateResultDto)
  {
    Response<TTemplateResultDto, Integer> response = null;
    try 
    {
      response = iTTemplateResultService.saveTemplateResult(tTemplateResultDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  
  @RequestMapping(value = "/getReport/{templateResId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getHistopathReport(@PathVariable(value = "templateResId") Integer templateResId,
      @PathVariable(value = "orgId") Integer orgId,
      @PathVariable(value = "orgUnitId") Integer orgUnitId)
  {
    Response<TTemplateResultDto, Integer> response = null;
    try 
    {
      response = iTTemplateResultService.getHistopathReport(templateResId,orgId,orgUnitId);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
}
