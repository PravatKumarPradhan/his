package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IClinicMasterDao;
import com.param.global.dto.ClinicMasterDto;

@Service
@SuppressWarnings("rawtypes")
public class ClinicMasterServiceImpl implements IClinicMasterService , ICommonConstants {

	@Autowired
	private IClinicMasterDao iClinicMasterDao;
	
	@Override
	@Transactional
	public Response getListOfClinicMasterByDoctorId(ClinicMasterDto clinicMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return iClinicMasterDao.getListOfClinicMasterByDoctorId(clinicMasterDto);
	}

	
	@Override
	@Transactional
	public Response saveClinicMaster(ClinicMasterDto clinicMasterDto)throws ApplicationException {
		try{
			Response clinicRes = iClinicMasterDao.getClinicMasterByName(clinicMasterDto);
			if(clinicRes.getListObject() != null && clinicRes.getListObject().size() > 0 ){
				return new Response<>(ERROR, ERROR_CODE, ALREADY_EXIST, null, null);
			}else{
				iClinicMasterDao.saveClinicMaster(clinicMasterDto);
				return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getClinicMasterById(ClinicMasterDto clinicMasterDto)throws ApplicationException {
		try{
			return iClinicMasterDao.getClinicMasterById(clinicMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response updateClinicMaster(ClinicMasterDto clinicMasterDto)throws ApplicationException {
		try{
			Response clinicRes = iClinicMasterDao.getClinicMasterByNameAndNotById(clinicMasterDto);
			if(clinicRes.getListObject() != null && clinicRes.getListObject().size() > 0 ){
				return new Response<>(ERROR, ERROR_CODE, ALREADY_EXIST, null, null);
			}else{
				iClinicMasterDao.updateClinicMaster(clinicMasterDto);
				return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getClinicMasterListByOrgANdUnitId(ClinicMasterDto clinicMasterDto) throws ApplicationException {
		try{
			return iClinicMasterDao.getClinicMasterListByOrgANdUnitId(clinicMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getClinicMasterCountByOrgAndUnit(int organizationId,int unitId) throws ApplicationException {
		try{
			return iClinicMasterDao.getClinicMasterCountByOrgAndUnit(organizationId, unitId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}


	@Override
	@Transactional
	public Response updateClinicMasterStatus(Integer clinicMasterId,Character status) throws ApplicationException {
		try{
			return iClinicMasterDao.updateClinicMasterStatus(clinicMasterId, status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

}
