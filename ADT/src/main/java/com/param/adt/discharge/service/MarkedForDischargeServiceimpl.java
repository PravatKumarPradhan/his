package com.param.adt.discharge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.discharge.dao.IMarkedForDischargeDao;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MarkedForDischargeServiceimpl implements IMarkedForDischargeService, ICommonConstants {

	@Autowired
	IMarkedForDischargeDao iMarkedForDischargeDao;

	@Override
	public Response getDischagePatientListForNursing(DischargeDto dischargeDto) throws ApplicationException {
		try {

			return iMarkedForDischargeDao.getDischagePatientListForNursing(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientServiceOrderList(AdmissionDto admissionDto) throws ApplicationException {
		try {

			return iMarkedForDischargeDao.getPatientServiceOrderList(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updatePatientServiceOrder(AdmissionDto admissionDto) throws ApplicationException {
		try {

			return iMarkedForDischargeDao.updatePatientServiceOrder(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientReadyForBillingList(DischargeDto dischargeDto) throws ApplicationException {
		try {

			return iMarkedForDischargeDao.getPatientReadyForBillingList(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updatePatientReadyForBillingStatus(DischargeDto dischargeDto) throws ApplicationException {
		try {

			return iMarkedForDischargeDao.updatePatientReadyForBillingStatus(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientFinalDischargeList(DischargeDto dischargeDto) throws ApplicationException {
		try {

			return iMarkedForDischargeDao.getPatientFinalDischargeList(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response vacateBed(DischargeDto dischargeDto) throws ApplicationException {
		try {

			dischargeDto.setBedStatusId(2);
			dischargeDto.setStatus('A');
			dischargeDto.setCreatedDate(dischargeDto.getUpdatedDate());
			dischargeDto.setCreatedBy(dischargeDto.getUpdatedBy());
			
			Response res= iMarkedForDischargeDao.vacateBed(dischargeDto);
			DischargeDto dischargeDto2=(DischargeDto) res.getObject();
			return iMarkedForDischargeDao.saveHousekeepingLog(dischargeDto2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}


