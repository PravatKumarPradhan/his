package com.param.lis.microbiology.transaction.dao;

import com.param.entity.lis.micro.TBiochemicalTestMaster;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.TBiochemicalTestMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBiochemicalTestResultDao  extends IGenericDao<TBiochemicalTestMaster, Integer>{

  public Response getBiochemicalWorkList(MicrobioParamDto microbioParamDto) throws ApplicationException;

  public Response getBiochemicalWorkListCount(MicrobioParamDto microbioParamDto) throws ApplicationException;
  
  public Response saveBiochemicalResult(TBiochemicalTestMasterDto tBiochemicalTestMasterDto) throws ApplicationException;
  
  public Response updateBiochemicalResult(TBiochemicalTestMasterDto tBiochemicalTestMasterDto) throws ApplicationException;
  
  public Response getBiochemicalResultDetails(Integer tBiochemicalTestId,Integer orgId,Integer orgUnitId) throws ApplicationException;
}
