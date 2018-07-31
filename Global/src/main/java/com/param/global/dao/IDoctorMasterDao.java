package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.model.DoctorMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDoctorMasterDao extends IGenericDao<DoctorMaster, Integer>{

	public Response getDoctorMasterList(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	public Response checkUniqueDoctor(DoctorMasterDto doctorMasterDto) throws ApplicationException;

	public Response saveDoctorRegistration(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	public Response saveDoctorDetails(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	public Response getDoctorDetails(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	public Response updateDoctorDetails(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	public Response updateDoctorStatus(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	public Response saveDoctorSpecialityMapper(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	public Response searchDoctorDetailsByCriteria(DoctorMasterDto doctorMasterDto)throws ApplicationException;
	
	public Response getDoctorForSyncById(Integer docId) throws ApplicationException;
}
