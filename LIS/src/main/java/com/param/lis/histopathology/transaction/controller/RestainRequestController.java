package com.param.lis.histopathology.transaction.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TGrossMasterDto;
import com.param.lis.histopathology.transaction.dto.TRestainingRequestDetailsMasterDto;
import com.param.lis.histopathology.transaction.dto.TRestainingRequestMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import com.param.lis.histopathology.transaction.service.IRestainRequestService;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Restain Request Controller", tags = "TRestainingRequestMaster")
@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/LIS/histopathology")
public class RestainRequestController implements ICommonConstants, IError{
  
  final static Logger logger = Logger.getLogger(RestainRequestController.class);

  @Autowired
  private IRestainRequestService iRestainRequestService;

  @RequestMapping(value = "/restainRequest/details", method = RequestMethod.POST)
  public @ResponseBody Response getSlidesDetails(@RequestBody TSubSpecimanMasterDto tSubSpecimanMasterDto)
  {
    Response<TGrossMasterDto, Integer> response = null;
    try {
      response = iRestainRequestService.getSlidesDetails(tSubSpecimanMasterDto);
      return response;
    } catch (Exception e) 
    {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @RequestMapping(value = "/restainRequest", method = RequestMethod.POST)
  public @ResponseBody Response saveRestainRequest(
      @RequestBody TRestainingRequestMasterDto tRestainingRequestMasterDto)
 {
    Response<TSubSpecimanMasterDto, Integer> response = null;
    try {
      response = iRestainRequestService.saveRestainRequest(tRestainingRequestMasterDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @RequestMapping(value = "/restainRequest/worklist", method = RequestMethod.POST)
  public @ResponseBody Response getRestainRequestWorkList(@RequestBody HistoParamDto histoParamDto)
 {
    Response<TSubSpecimanMasterDto, Integer> response = null;
    try {
      response = iRestainRequestService.getRestainRequestWorkList(histoParamDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  @RequestMapping(value = "/restainRequest/worklist/count", method = RequestMethod.POST)
  public @ResponseBody Response getRestainRequestWorkListCount(
      @RequestBody HistoParamDto histoParamDto)
 {
    Response<TSubSpecimanMasterDto, Integer> response = null;
    try {
      response = iRestainRequestService.getRestainRequestWorkListCount(histoParamDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  
  
  @RequestMapping(value = "/restainRequest/worklist/details/{tRestainingReqId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getRestainRequestWorkListDetails(@PathVariable(value = "tRestainingReqId") Integer tRestainingReqId,
      @PathVariable(value = "orgId") Integer orgId,
      @PathVariable(value = "orgUnitId") Integer orgUnitId)
  {
    Response<TRestainingRequestDetailsMasterDto, Integer> response = null;
    try {
      response = iRestainRequestService.getRestainRequestWorkListDetails(tRestainingReqId,orgId,orgUnitId);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  @RequestMapping(value = "/restainRequest/worklist/slides/{tRestainingDetailId}/{tRestainingSubDetailId}/{isNew}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getRestainRequestWorkListSlides(@PathVariable(value = "tRestainingDetailId") Integer tRestainingDetailId,
      @PathVariable(value = "tRestainingSubDetailId") Integer tRestainingSubDetailId,
      @PathVariable(value = "isNew") Character isNew,
      @PathVariable(value = "orgId") Integer orgId,
      @PathVariable(value = "orgUnitId") Integer orgUnitId)
  {
    Response<TGrossMasterDto, Integer> response = null;
    try {
      response = iRestainRequestService.getRestainRequestWorkListSlides(tRestainingDetailId,tRestainingSubDetailId,isNew,orgId,orgUnitId);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  
  @RequestMapping(value = "/maxslide/{orgId}/{orgUnitId}/{tBlockId}", method = RequestMethod.GET)
  public @ResponseBody Response getMaxSlideNumber(@PathVariable(value = "orgId") Integer orgId,
      @PathVariable(value = "orgUnitId") Integer orgUnitId,
      @PathVariable(value = "tBlockId") Integer tBlockId)
  {
    Response<TGrossMasterDto, Integer> response = null;
    try {
      response = iRestainRequestService.getMaxSlideNumber(orgId,orgUnitId,tBlockId);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  

}
