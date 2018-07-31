package com.param.lis.microbiology.transaction.dao;

import com.param.entity.lis.micro.TMacroscopicExaminationMaster;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.TMacroscopicExaminationMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface IMacroscopicExaminationDao  extends IGenericDao<TMacroscopicExaminationMaster, Integer>{
  
  public Response getMacroScopicExaminationList(MicrobioParamDto microbioParamDto) throws ApplicationException;

  public Response getMacroScopicExaminationListCount(MicrobioParamDto microbioParamDto) throws ApplicationException;
  
  public Response saveMacroScopicExamination(TMacroscopicExaminationMasterDto tMacroscopicExaminationMasterDto) throws ApplicationException;
  
  public Response updateMacroScopicExamination(TMacroscopicExaminationMasterDto tMacroscopicExaminationMasterDto) throws ApplicationException;
  
  public Response getMacroScopicExamination(Integer tMacroExaId,Integer orgId,Integer orgUnitId) throws ApplicationException;
  

}
