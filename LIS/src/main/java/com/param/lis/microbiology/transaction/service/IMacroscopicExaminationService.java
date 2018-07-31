package com.param.lis.microbiology.transaction.service;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.TMacroscopicExaminationMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IMacroscopicExaminationService {

  public Response getMacroScopicExaminationList(MicrobioParamDto microbioParamDto) throws ApplicationException;

  public Response getMacroScopicExaminationListCount(MicrobioParamDto microbioParamDto) throws ApplicationException;
  
  public Response saveMacroScopicExamination(List<TMacroscopicExaminationMasterDto> tMacroscopicExaminationMasterDtoList) throws ApplicationException;
  
  public Response updateMacroScopicExamination(List<TMacroscopicExaminationMasterDto> tMacroscopicExaminationMasterDtoList) throws ApplicationException;
  
  public Response getMacroScopicExamination(Integer tMacroExaId,Integer orgId,Integer orgUnitId) throws ApplicationException;
  
}
