package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.DiagnosisMasterDto;
import com.param.opd.coversheet.model.DiagnosisMaster;

public interface IDiagnosisMasterDao extends IGenericDao<DiagnosisMaster, Integer>{

	public Response getListOfDiagnosisMaster(DiagnosisMasterDto diagnosisMasterDto)throws ApplicationException;
}
