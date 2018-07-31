package com.param.lis.transaction.service;

import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dto.LabCommonDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ILabDashBoardService {
  
  public Response getlabDashBoardList(LabCommonDto labCommonDto) throws ApplicationException;

  public Response getlabDashBoardListCount(LabCommonDto labCommonDto)
      throws ApplicationException;
  
  public Response getSampleLog(Integer labSampleDtlsId, Integer orgId, Integer orgUnitId)
      throws ApplicationException;
  
  public Response getFilteredDashBoardList(SearchDto searchDto)
      throws ApplicationException;
  
  public Response searchAcceptedSpecimanByPatient(SearchCommonDto searchCommonDto) throws ApplicationException;
  public Response searchAcceptedSampleNo(SearchCommonDto searchCommonDto) throws ApplicationException;
  


}
