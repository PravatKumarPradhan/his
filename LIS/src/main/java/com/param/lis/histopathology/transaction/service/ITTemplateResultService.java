package com.param.lis.histopathology.transaction.service;

import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.TTemplateResultDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ITTemplateResultService {

  public Response saveTemplateResult(TTemplateResultDto tTemplateResultDto) throws ApplicationException;

  public Response getHistopathReport(Integer templateResId, Integer orgId, Integer orgUnitId)
      throws ApplicationException;

}
