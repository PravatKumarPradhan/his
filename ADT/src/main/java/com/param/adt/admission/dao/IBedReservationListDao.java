package com.param.adt.admission.dao;

import com.param.adt.admission.dto.UnreservedPatientDto;
import com.param.adt.admission.model.UnreservedPatient;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBedReservationListDao extends IGenericDao<UnreservedPatient, Integer> {

	Response getBedReservationList(AdmissionDto admissionDto) throws ApplicationException;

	Response cancelReservation(UnreservedPatientDto unreservedPatientDto) throws ApplicationException;

	Response rescheduleReservation(UnreservedPatientDto unreservedPatientDto) throws ApplicationException;

	Response getCount(AdmissionDto admissionDto)throws ApplicationException;

}
