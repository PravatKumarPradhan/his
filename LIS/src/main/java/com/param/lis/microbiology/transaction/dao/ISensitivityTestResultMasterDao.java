package com.param.lis.microbiology.transaction.dao;

import com.param.entity.lis.micro.SensitivityTestResultMaster;
import com.param.lis.global.common.Response;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISensitivityTestResultMasterDao extends IGenericDao<SensitivityTestResultMaster, Integer>
{
	public Response saveSensitivityTestResultMasterDao(SensitivityTestResultMaster sensitivityTestResultMaster)throws ApplicationException;
}
