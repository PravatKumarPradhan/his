package com.param.lis.microbiology.transaction.service;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicroscopicExaminationMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IMicroscopicExaminationService {
  
  public Response getMicroScopicExaminationList(MicrobioParamDto microbioParamDto) throws ApplicationException;

  public Response getMicroScopicExaminationListCount(MicrobioParamDto microbioParamDto) throws ApplicationException;
  
  public Response saveMicroScopicExamination(List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMaster) throws ApplicationException;
  
  public Response updateMicroScopicExamination(List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMaster) throws ApplicationException;
  
  public Response getMicroScopicExamination(Integer labSampleDtlsId,Integer orgId,Integer orgUnitId) throws ApplicationException;

}
