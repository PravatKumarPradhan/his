package com.param.global.org.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IScheduleLogsDao;
import com.param.global.dto.ScheduleLogsDto;
import com.param.global.org.dao.IGlobalOrganisationMasterDao;
import com.param.global.org.dao.IOrganizationUnitLicenceDetailsDao;
import com.param.global.org.dto.OrganizationMasterDto;
import com.param.global.org.dto.OrganizationUnitLicenceDetailsDto;

@Service
@SuppressWarnings("rawtypes")
public class GlobalOrganizationMasterServiceImpl implements IGlobalOrganizationMasterService , ICommonConstants{

	@Autowired
	IGlobalOrganisationMasterDao iGlobalOrganisationMasterDao;
	
	@Autowired
	IScheduleLogsDao iScheduleLogsDao;
	
	@Autowired
	IOrganizationUnitLicenceDetailsDao iOrganizationUnitLicenceDetailsDao;
	
	
	@Override
	@Transactional
	public Response saveOrganizationMaster(OrganizationMasterDto organizationMasterDto)throws ApplicationException {
		try{
			Response organizationRes = iGlobalOrganisationMasterDao.getOrganizationByName(organizationMasterDto.getOrganizationName());
			if(organizationRes.getListObject() != null && organizationRes.getListObject().size() > 0 ){
				return new Response<>(ERROR, null, ALREADY_EXIST, null, null);
			}else{
				  Response orgResponse = iGlobalOrganisationMasterDao.saveOrganizationMaster(organizationMasterDto);
				  
				if(orgResponse.getStatus() == SUCCESS && orgResponse.getObject() != null){
					OrganizationMaster organizationMaster = (OrganizationMaster) orgResponse.getObject();
					
					if(organizationMasterDto.getListOrganizationUnitLicenceDetailsDto() != null && organizationMasterDto.getListOrganizationUnitLicenceDetailsDto().size() >0){
						for(OrganizationUnitLicenceDetailsDto licenceDetailsDto : organizationMasterDto.getListOrganizationUnitLicenceDetailsDto()){
							licenceDetailsDto.setOrganizationId(organizationMaster.getOrganizationId());
							licenceDetailsDto.setIsUnit(INACTIVE);
							iOrganizationUnitLicenceDetailsDao.saveOrganizationUnitLicenceDetails(licenceDetailsDto);
						}
					}
					//ScheduleLogsDto scheduleLogsDto = new ScheduleLogsDto();
				}
				  return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response updateOrganizationMaster(OrganizationMasterDto organizationMasterDto)throws ApplicationException {
		try{
			Response orgRes = iGlobalOrganisationMasterDao.getOrganizationByNameAndNotById(organizationMasterDto);
			if(orgRes.getStatus() == SUCCESS && orgRes.getListObject() != null && orgRes.getListObject().size() >0){
				return new Response<>(ERROR, null, ALREADY_EXIST, null, null);
			}
			else{
				iGlobalOrganisationMasterDao.updateOrganizationMaster(organizationMasterDto);
				return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getOrganizationById(int organizationId)throws ApplicationException {
		try{
			return iGlobalOrganisationMasterDao.getOrganizationById(organizationId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getAllOrganizationMasterList(OrganizationMasterDto organizationMasterDto) throws ApplicationException {
		try{
			return iGlobalOrganisationMasterDao.getAllOrganizationMasterList(organizationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getOrganizationCount() throws ApplicationException {
		try{
			return iGlobalOrganisationMasterDao.getOrganizationCount();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response updateOrganizationStatus(OrganizationMasterDto organizationMasterDto)throws ApplicationException {
		try{
			return iGlobalOrganisationMasterDao.updateOrganizationStatus(organizationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getLicenceDetailsByOrgUnitId(OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto)throws ApplicationException {
		try{
			return iOrganizationUnitLicenceDetailsDao.getLicenceDetailsByOrgUnitId(organizationUnitLicenceDetailsDto); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getActiveOrganizationList() throws ApplicationException {
		try{
			return iGlobalOrganisationMasterDao.getActiveOrganizationList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

	
}
