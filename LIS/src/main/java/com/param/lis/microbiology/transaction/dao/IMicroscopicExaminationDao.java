package com.param.lis.microbiology.transaction.dao;

import com.param.entity.lis.micro.MicroscopicExaminationMaster;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicroscopicExaminationMasterDto;
import com.param.lis.microbiology.transaction.dto.TMacroscopicExaminationMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface IMicroscopicExaminationDao  extends IGenericDao<MicroscopicExaminationMaster, Integer>{

  public Response getMicroScopicExaminationList(MicrobioParamDto microbioParamDto) throws ApplicationException;

  public Response getMicroScopicExaminationListCount(MicrobioParamDto microbioParamDto) throws ApplicationException;
  
  public Response saveMicroScopicExamination(MicroscopicExaminationMasterDto microscopicExaminationMasterDto) throws ApplicationException;
  
  public Response updateMicroScopicExamination(MicroscopicExaminationMasterDto microscopicExaminationMasterDto) throws ApplicationException;
  
  public Response getMicroScopicExamination(Integer labSampleDtlsId,Integer orgId,Integer orgUnitId) throws ApplicationException;
  
}
