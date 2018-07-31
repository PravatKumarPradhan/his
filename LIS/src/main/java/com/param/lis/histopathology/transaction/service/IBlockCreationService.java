package com.param.lis.histopathology.transaction.service;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TGrossMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBlockCreationService {
  public Response getBlockCreationList(HistoParamDto histoParamDto) throws ApplicationException;

  public Response getBlockCreationListCount(HistoParamDto histoParamDto)
      throws ApplicationException;

  public Response getCreatedGross(TSubSpecimanMasterDto tSubSpecimanMasterDto)
      throws ApplicationException;

  public Response creteBlocks(List<TGrossMasterDto> listTGrossMasterDto)
      throws ApplicationException;



}
