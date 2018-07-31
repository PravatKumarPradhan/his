package com.param.lis.histopathology.transaction.dao;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TFrozenSectionReqMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IFrozenSectionReqDao {
  

  public Response saveFrozerSectionRequest(TFrozenSectionReqMasterDto tFrozenSectionReqMasterDto)
      throws ApplicationException;

  public Response getFrozenSectionReqList(HistoParamDto histoParamDto) throws ApplicationException;

  public Response getFrozenSectionReqListCount(HistoParamDto histoParamDto)
      throws ApplicationException;

  public Response acceptFrozensectionReq(List<TFrozenSectionReqMasterDto> listTFrozenSectionReqMasterDto)
      throws ApplicationException;
  
}
