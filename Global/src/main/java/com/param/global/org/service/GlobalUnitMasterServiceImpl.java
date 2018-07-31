package com.param.global.org.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.adt.master.global.model.UnitMaster;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IGlobalUnitMasterDao;
import com.param.global.org.dao.IOrganizationUnitLicenceDetailsDao;
import com.param.global.org.dto.OrganizationUnitLicenceDetailsDto;
import com.param.global.org.dto.UnitMasterDto;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class GlobalUnitMasterServiceImpl implements IGlobalUnitMasterService , ICommonConstants{

	@Autowired
	IGlobalUnitMasterDao iGlobalUnitMasterDao;
	
	@Autowired
	IOrganizationUnitLicenceDetailsDao iOrganizationUnitLicenceDetailsDao;
	
	@Override
	@Transactional
	public Response saveUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			Response unitRes = iGlobalUnitMasterDao.getUnitByName(unitMasterDto);
			if(unitRes.getListObject() != null && unitRes.getListObject().size() > 0){
				return new Response(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			}else{
				Response unit = iGlobalUnitMasterDao.saveUnitMaster(unitMasterDto);
				if(unit.getObject() != null){
					//save licence Details
					UnitMaster unitMaster = (UnitMaster) unit.getObject();
					if(unitMasterDto.getListOrganizationUnitLicenceDetailsDto() != null && unitMasterDto.getListOrganizationUnitLicenceDetailsDto().size() >0){
						for(OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto : unitMasterDto.getListOrganizationUnitLicenceDetailsDto()){
							
							organizationUnitLicenceDetailsDto.setOrganizationId(null);
							organizationUnitLicenceDetailsDto.setIsUnit(ACTIVE);
							organizationUnitLicenceDetailsDto.setUnitId(unitMaster.getUnitId());
							iOrganizationUnitLicenceDetailsDao.saveOrganizationUnitLicenceDetails(organizationUnitLicenceDetailsDto);
						}
					}
					
					//save schedule logs
				}
				
				return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response updateUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			Response unitRes = iGlobalUnitMasterDao.getUnitByNameAndNotById(unitMasterDto);
			if(unitRes.getListObject() != null && unitRes.getListObject().size() > 0){
				return new Response(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			}else{
				Response unit = iGlobalUnitMasterDao.updateUnitMaster(unitMasterDto);
				/*if(unit.getObject() != null){
					//save licence Details
					UnitMaster unitMaster = (UnitMaster) unit.getObject();
					OrganizationUnitLicenceDetailsDto organizationUnitLicenceDetailsDto = new OrganizationUnitLicenceDetailsDto();
					organizationUnitLicenceDetailsDto.setOrganizationId(null);
					organizationUnitLicenceDetailsDto.setIsUnit(ACTIVE);
					organizationUnitLicenceDetailsDto.setUnitId(unitMaster.getUnitId());
					iOrganizationUnitLicenceDetailsDao.saveOrganizationUnitLicenceDetails(organizationUnitLicenceDetailsDto);
					
					//save schedule logs
				}*/
				
				return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getUnitById(Integer unitId) throws ApplicationException {
		try{
			return iGlobalUnitMasterDao.getUnitById(unitId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response updateUnitStatusById(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			return iGlobalUnitMasterDao.updateUnitStatusById(unitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getAllUnitList(UnitMasterDto unitMasterDto){
	try{
		return iGlobalUnitMasterDao.getAllUnitList(unitMasterDto);
	}catch(Exception e){
		e.printStackTrace();
	}
	return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getUnitTotalCount() throws ApplicationException {
		try{
			return iGlobalUnitMasterDao.getUnitTotalCount();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	
}
