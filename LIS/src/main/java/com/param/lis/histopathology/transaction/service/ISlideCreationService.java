package com.param.lis.histopathology.transaction.service;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TFrozenSectionReqMasterDto;
import com.param.lis.histopathology.transaction.dto.TGrossMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISlideCreationService {

  public Response getSlideCreationWorkList(HistoParamDto histoParamDto) throws ApplicationException;

  public Response getSlideCreationWorkListCount(HistoParamDto histoParamDto)
      throws ApplicationException;

  public Response getCreatedBlockList(TSubSpecimanMasterDto tSubSpecimanMasterDto)
      throws ApplicationException;

  public Response createSlides(List<TGrossMasterDto> listTGrossMasterDto)
      throws ApplicationException;
}
