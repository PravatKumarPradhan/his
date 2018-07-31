package com.param.billing.global.transaction.dao;

import com.param.billing.global.transaction.dto.DoctorConsultationChargesMasterDto;
import com.param.billing.global.transaction.model.DoctorConsultationChargesMaster;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDoctorConsultationChargesMasterDao extends IGenericDao<DoctorConsultationChargesMaster, Integer>{
	public Response getDoctorConsultationChargesByDoctorIdAndVisitTypeAndClass(DoctorConsultationChargesMasterDto doctorConsultationChargesMasterDto)throws ApplicationException;
}
