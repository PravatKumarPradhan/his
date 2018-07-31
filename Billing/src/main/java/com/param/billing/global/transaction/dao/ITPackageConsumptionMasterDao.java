package com.param.billing.global.transaction.dao;

import com.param.billing.dto.TPackageConsumptionMasterDto;
import com.param.billing.exception.BillingException;
import com.param.billing.exception.ServiceException;
import com.param.billing.packages.model.TPackageConsumptionMaster;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface ITPackageConsumptionMasterDao extends IGenericDao<TPackageConsumptionMaster, Integer>{
	public Response saveTPackageConsumptionMaster(TPackageConsumptionMasterDto dto)throws BillingException;
	public Response getExistingActivePackageByPatientEncounter(Integer patientId, Integer encounterId,Integer visitTypeId, Integer organisationId, Integer unitId) throws ServiceException;
	public Response getPackageConsumptionServiceDetailsByPackageIdPatientId(Integer packageId, Integer patientId) throws ServiceException;
}
