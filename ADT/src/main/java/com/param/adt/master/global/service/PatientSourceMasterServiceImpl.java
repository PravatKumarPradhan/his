package com.param.adt.master.global.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IPatientServiceMasterDao;
import com.param.adt.master.global.dto.PatientSourceMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientSourceMasterServiceImpl implements IPatientSourceMasterService, ICommonConstants {
	@Autowired
	private IPatientServiceMasterDao iPatientSourceMasterDao;

	@Override
	public Response addPatientSourceMaster(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iPatientSourceMasterDao.getPatientSourceByName(patientSourceMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*patientSourceMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				patientSourceMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iPatientSourceMasterDao.addPatientSourceTypeMaster(patientSourceMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientSourceMasterList(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException {
		try {
			return iPatientSourceMasterDao.getPatientSourceMasterList(patientSourceMasterDto);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updatePatientSourceMaster(PatientSourceMasterDto patientSourceMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iPatientSourceMasterDao.getPatientSourceByNameNotId(patientSourceMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*patientSourceMasterDto
				.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iPatientSourceMasterDao.updatePatientSource(patientSourceMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Override
	public Response getPatientSourceById(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException {
		try {
			return iPatientSourceMasterDao.getPatientSourceById(patientSourceMasterDto);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForPatientSource(PatientSourceMasterDto patientSourceMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iPatientSourceMasterDao.getPatientSourceById(patientSourceMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*patientSourceMasterDto
				.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iPatientSourceMasterDao.updatePatientSourceStatus(patientSourceMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
		
			} else {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActivePatientSourceList() throws ApplicationException {
		try {
			return iPatientSourceMasterDao.getActivePatientSourceList();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientSourceCount(PatientSourceMasterDto patientSourceMasterDto) {
		try {
			return iPatientSourceMasterDao.getCount(patientSourceMasterDto);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
