package com.param.lis.microbiology.transaction.dao;

import com.param.entity.lis.micro.MicroscopicExaminationMaster;
import com.param.entity.lis.micro.SensitivityTestResultDetailsMaster;
import com.param.entity.lis.micro.SensitivityTestResultMaster;
import com.param.lis.global.common.Response;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISensitivityTestResultDetailsMasterDao extends IGenericDao<SensitivityTestResultDetailsMaster, Integer>
{
	public Response saveSensitivityTestResultDetailsMaster(SensitivityTestResultDetailsMaster sensitivityTestResultDetailsMaster)throws ApplicationException;
	public Response updateSensitivityTestResultDetailsMaster(SensitivityTestResultDetailsMaster sensitivityTestResultDetailsMaster)throws ApplicationException;
}
