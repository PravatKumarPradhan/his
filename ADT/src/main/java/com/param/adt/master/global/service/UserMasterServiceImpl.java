package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.IUserLoginConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IUserMasterDao;
import com.param.adt.master.global.dto.OrganizationMasterDto;
import com.param.adt.master.global.dto.OrganizationUnitUserMapperDto;
import com.param.adt.master.global.dto.UserMasterDto;

import in.param.exception.ApplicationException;


@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class UserMasterServiceImpl implements IUserMasterService,ICommonConstants,IUserLoginConstants{

	@Autowired
	private IUserMasterDao iUserMasterDao;
	
	@Transactional
	public Response getOrganizationList() throws ApplicationException {
		try{
			return iUserMasterDao.getOrganizationList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@Transactional
	@Override
	public Response getUnitListByOrganization(OrganizationMasterDto organizationMasterDto) throws ApplicationException {
		try{
			return iUserMasterDao.getUnitFromOrganizationId(organizationMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@Override
	@Transactional
	public Response validateUniqueUserName(UserMasterDto userMasterDto)throws ApplicationException {
		try{
			return iUserMasterDao.validateUniqueUserName(userMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@Override
	@Transactional
	public Response userLogin(UserMasterDto userMasterDto)throws ApplicationException {
		try{
			String userName = userMasterDto.getUserName();
			String password = userMasterDto.getPassword();
			System.out.println("1");
			Response loginResponse = iUserMasterDao.userLogin(userMasterDto);
			System.out.println("2");
			if(loginResponse.getStatus().equalsIgnoreCase("success")){
				UserMasterDto masterDto = (UserMasterDto)loginResponse.getListObject().get(0);
				// Check If user name is valid
				if(masterDto.getUserName().equalsIgnoreCase(userName)){
					// Check If password is valid
					if(masterDto.getPassword().equals(password)){
						OrganizationUnitUserMapperDto organizationUnitUserMapperDto = new OrganizationUnitUserMapperDto();
						organizationUnitUserMapperDto.setOrganizationlId(userMasterDto.getOrganizationId());
						organizationUnitUserMapperDto.setUnitId(userMasterDto.getUnitId());
						organizationUnitUserMapperDto.setUserId(masterDto.getUserId());
						System.out.println("3");
						Response hospUnitResponse = iUserMasterDao.getHospitalUnitUserForLogin(organizationUnitUserMapperDto);
						System.out.println("4");
						if(hospUnitResponse.getListObject() != null && hospUnitResponse.getListObject().size() > 0){
							return new Response(SUCCESS, VALID_PWD_CODE, VALID_PWD_MSG, loginResponse.getListObject(),null);
						}else{
							return new Response(ERROR, null, null, null,null);
						}
					}else{
						return new Response(ERROR,INVALID_PWD_CODE,INVALID_PWD_MSG,null,null);
					}
				}else{
					return new Response(ERROR, INVALID_USER_CODE, INVALID_USER_MSG, null,null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, INVALID_USER_CODE, INVALID_USER_MSG, null,null);
	}

}
