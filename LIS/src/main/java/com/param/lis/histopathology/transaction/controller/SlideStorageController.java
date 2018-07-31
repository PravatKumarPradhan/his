package com.param.lis.histopathology.transaction.controller;

import java.util.List;

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
import com.param.lis.histopathology.transaction.dto.OutSourceMasterDto;
import com.param.lis.histopathology.transaction.dto.TSlideStorageMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import com.param.lis.histopathology.transaction.service.ISlideStorageService;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Slide storage controller", tags = "TSlideStorageMaster")
@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/LIS/histopathology")
public class SlideStorageController implements ICommonConstants, IError {
  final Logger logger = Logger.getLogger(SlideStorageController.class);

  @Autowired
  private ISlideStorageService iSlideStorageService;

  @RequestMapping(value = "/slide/storage/list", method = RequestMethod.POST)
  public @ResponseBody Response getSlidesForStorage(@RequestBody HistoParamDto histoParamDto) {
    Response<TSlideStorageMasterDto, Integer> response = null;
    try {
      response = iSlideStorageService.getSlidesForStorage(histoParamDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @RequestMapping(value = "/slide/storage/list/count", method = RequestMethod.POST)
  public @ResponseBody Response getSlidesForStorageCount(@RequestBody HistoParamDto histoParamDto) {
    Response<TSlideStorageMasterDto, Integer> response = null;
    try {
      response = iSlideStorageService.getSlidesForStorageCount(histoParamDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @RequestMapping(value = "/slide/storage", method = RequestMethod.POST)
  public @ResponseBody Response saveSlideStorageDetails(
      @RequestBody List<TSlideStorageMasterDto> tSlideStorageMasterDto ) {
     Response<TSlideStorageMasterDto, Integer> response = null;
    try {
      response = iSlideStorageService.saveSlideStorageDetails(tSlideStorageMasterDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }



  @RequestMapping(value = "/slides/storage/details/{rackId}/{shelfId}/{orgId}/{orgUnitId}",
      method = RequestMethod.GET)
  public @ResponseBody Response getStoredSlideDetails(
      @PathVariable(value = "rackId") Integer rackId,
      @PathVariable(value = "shelfId") Integer shelfId,
      @PathVariable(value = "orgId") Integer orgId,
      @PathVariable(value = "orgUnitId") Integer orgUnitId) {
    Response<TSlideStorageMasterDto, Integer> response = null;
    try {
      response = iSlideStorageService.getStoredSlideDetails(rackId, shelfId, orgId, orgUnitId);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @RequestMapping(value = "/stored/slide", method = RequestMethod.POST)
  public @ResponseBody Response getStoredSlideDetails(@RequestBody HistoParamDto histoParamDto) {
    Response<TSubSpecimanMasterDto, Integer> response = null;
    try {
      response = iSlideStorageService.getStoredSlideDetails(histoParamDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @RequestMapping(value = "/stored/slide/count", method = RequestMethod.POST)
  public @ResponseBody Response getStoredSlideDetailsCount(
      @RequestBody HistoParamDto histoParamDto) {
    Response<TSubSpecimanMasterDto, Integer> response = null;
    try {
      response = iSlideStorageService.getStoredSlideDetailsCount(histoParamDto);
      return response;
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }



}
