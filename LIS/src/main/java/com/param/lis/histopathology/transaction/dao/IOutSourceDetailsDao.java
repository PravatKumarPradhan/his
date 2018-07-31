package com.param.lis.histopathology.transaction.dao;

import com.param.entity.lis.histo.OutSourceDetailMaster;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.OutSourceDetailMasterDto;
import com.param.lis.transaction.dto.SearchCommonDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({"rawtypes"})
public interface IOutSourceDetailsDao  extends IGenericDao<OutSourceDetailMaster, Integer>{
  public Response getOutSourceDetailsByOutSourceId(OutSourceDetailMasterDto outSourceDetailMasterDto) throws ApplicationException;
}
