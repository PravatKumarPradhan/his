package com.param.ER.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.ER.dao.IERBedTypeAllocationDao;
import com.param.ER.dto.ERBedTypeAllocationDto;
import com.param.adt.admission.model.Admission;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ERBedTypeAllocationServiceImpl implements IERBedTypeAllocationService, ICommonConstants {

	@Autowired
	IERBedTypeAllocationDao iBedTypeAllocationDao;

	@Override
	public Response getUnknownPatientsList(UnknownPatientRegistrationDto unknownPatientRegistrationDto)
			throws ApplicationException {
		try {

			return iBedTypeAllocationDao.getUnknownPatientsList(unknownPatientRegistrationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getERBedTypeAllocationList(ERBedTypeAllocationDto erBedTypeAllocationDto) {
		try {

			return iBedTypeAllocationDao.getERBedTypeAllocationList(erBedTypeAllocationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getERBedTypeAllocationCount(ERBedTypeAllocationDto erBedTypeAllocationDto)
			throws ApplicationException {
		try {

			return iBedTypeAllocationDao.getCount(erBedTypeAllocationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getERBedList(ERBedTypeAllocationDto erBedTypeAllocationDto) throws ApplicationException {
		try {

			return iBedTypeAllocationDao.getERBedList(erBedTypeAllocationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateERPatientAdmisson(AdmissionDto admissionDto) {
		try {

			Response res = iBedTypeAllocationDao.updateERPatientAdmission(admissionDto);
			Admission admission = (Admission) res.getObject();
			return new Response(SUCCESS, null, null, null, admission);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateERsavePatientDetails(AdmissionDto admissionDto) {
		try {

			return iBedTypeAllocationDao.updateERPatientDetails(admissionDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveDoctorList(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {

			return iBedTypeAllocationDao.getActiveDoctorList(doctorMasterDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveTransfer(AdmissionDto unAdmissionDto) throws ApplicationException {
		try {

			TransferDto transferDto = new TransferDto();
			transferDto.setOrganizationId(unAdmissionDto.getOrganizationId());
			transferDto.setUnitId(unAdmissionDto.getUnitId());
			transferDto.setAdmissionId(unAdmissionDto.getAdmissionId());
			transferDto.setFromBedCategoryId(unAdmissionDto.getBedCategoryId());
			transferDto.setFromBillingBedCategoryId(unAdmissionDto.getBillingBedCategoryId());
			transferDto.setFromWardId(unAdmissionDto.getWardId());
			transferDto.setFromRoomId(unAdmissionDto.getRoomId());
			transferDto.setFromBedId(unAdmissionDto.getBedId());
			transferDto.setFromDate(unAdmissionDto.getDoa());
			transferDto.setFromTime(unAdmissionDto.getDoa());
			transferDto.setCreatedBy(unAdmissionDto.getCreatedBy());
			transferDto.setCreatedDate(unAdmissionDto.getDoa());
			transferDto.setUpdatedBy(unAdmissionDto.getUpdatedBy());
			transferDto.setUpdatedDate(unAdmissionDto.getDoa());
			transferDto.setStatus('A');
			transferDto.setTransferStatusId(1);
			return iBedTypeAllocationDao.saveTransfer(transferDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
