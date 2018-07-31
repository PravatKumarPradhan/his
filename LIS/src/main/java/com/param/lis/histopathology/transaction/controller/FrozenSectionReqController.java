package com.param.lis.histopathology.transaction.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TFrozenSectionReqMasterDto;
import com.param.lis.histopathology.transaction.service.IFrozenSectionReqService;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Frozen section Request", tags = "TFrozenSectionReqMaster")
@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/LIS/histopathology")
public class FrozenSectionReqController implements ICommonConstants, IError{
  
  final static Logger logger = Logger.getLogger(FrozenSectionReqController.class);

  @Autowired
  private IFrozenSectionReqService iFrozenSectionReqService;

  @RequestMapping(value = "/frozenSectionRequest", method = RequestMethod.POST)
  public @ResponseBody Response saveFrozerSectionRequest(@RequestBody TFrozenSectionReqMasterDto tFrozenSectionReqMasterDto) {
    Response<TFrozenSectionReqMasterDto, Integer> response = null;
    try {
      response = iFrozenSectionReqService.saveFrozerSectionRequest(tFrozenSectionReqMasterDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @RequestMapping(value = "/frozenSectionRequest/list", method = RequestMethod.POST)
  public @ResponseBody Response getFrozenSectionReqList(
      @RequestBody HistoParamDto histoParamDto) {
    Response<TFrozenSectionReqMasterDto, Integer> response = null;
    try {
      response = iFrozenSectionReqService.getFrozenSectionReqList(histoParamDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @RequestMapping(value = "/frozenSectionRequest/count", method = RequestMethod.POST)
  public @ResponseBody Response getFrozenSectionReqListCount(
      @RequestBody HistoParamDto histoParamDto) {
    Response<TFrozenSectionReqMasterDto, Integer> response = null;
    try {
      response = iFrozenSectionReqService.getFrozenSectionReqListCount(histoParamDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @RequestMapping(value = "/frozenSection/Accept", method = RequestMethod.POST)
  public @ResponseBody Response acceptFrozensectionReq(
      @RequestBody List<TFrozenSectionReqMasterDto> listTFrozenSectionReqMasterDto) {
    Response<TFrozenSectionReqMasterDto, Integer> response = null;
    try {
      response = iFrozenSectionReqService.acceptFrozensectionReq(listTFrozenSectionReqMasterDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}
