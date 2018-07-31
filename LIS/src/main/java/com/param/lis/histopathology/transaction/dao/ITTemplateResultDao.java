package com.param.lis.histopathology.transaction.dao;

import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.TTemplateResultDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ITTemplateResultDao 
{

  public Response saveTemplateResult(TTemplateResultDto tTemplateResultDto)
      throws ApplicationException;
  
  public Response getHistopathReport(Integer templateResId, Integer orgId, Integer orgUnitId)
      throws ApplicationException;

}
