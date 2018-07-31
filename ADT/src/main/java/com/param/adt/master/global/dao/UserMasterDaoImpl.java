package com.param.adt.master.global.dao;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OrganizationMasterDto;
import com.param.adt.master.global.dto.OrganizationUnitUserMapperDto;
import com.param.adt.master.global.dto.UserMasterDto;
import com.param.adt.master.global.model.UnitMaster;

import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unused","unchecked"})
public class UserMasterDaoImpl implements IUserMasterDao,ICommonConstants 
{

	@Autowired
	private SessionFactory SessionFactory;
	
	
	@Autowired
	private DozerBeanMapper mapper;
	




	@Override
	public Response getOrganizationList() throws ApplicationException {
		try{
			List<OrganizationMasterDto> hospitalMasterDtoList = SessionFactory.getCurrentSession().getNamedQuery("GET_ORGANIZATION_MASTER_LIST").setResultTransformer(Transformers.aliasToBean(OrganizationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, hospitalMasterDtoList,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}



	@Override
	public Response getUnitFromOrganizationId(OrganizationMasterDto organizationMasterDto) throws ApplicationException {
		try{
			List<UnitMaster> listUnitDepartmentMapperDto  = SessionFactory.getCurrentSession().getNamedQuery("GET_UNIT_BY_ORGANIZATION_ID").setInteger("Organization_ID",organizationMasterDto.getOrganizationId()).setResultTransformer(Transformers.aliasToBean(UnitMaster.class)).list();
			return new Response<UnitMaster, Integer>(SUCCESS, null, null, listUnitDepartmentMapperDto,null);
		}catch(HibernateException he){
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
		
	}



	@Override
	public Response validateUniqueUserName(UserMasterDto userMasterDto) throws ApplicationException {
		try{
			List<UserMasterDto> listUserMasterDto = SessionFactory.getCurrentSession().getNamedQuery("USER_LOGIN").setString("userName", userMasterDto.getUserName().toLowerCase()).setResultTransformer(Transformers.aliasToBean(UserMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listUserMasterDto,null);
		}catch(HibernateException he){
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}		
		return new Response(ERROR, null, null, null,null);
	}



	@Override
	public Response userLogin(UserMasterDto userMasterDto) throws ApplicationException {
		try{			
			List<UserMasterDto> listUserMasterDto = SessionFactory.getCurrentSession().getNamedQuery("USER_LOGIN").setString("userName", userMasterDto.getUserName()).setResultTransformer(Transformers.aliasToBean(UserMasterDto.class)).list();
			if(listUserMasterDto!=null){
				if(listUserMasterDto.size()!=0){
					return new Response(SUCCESS, null, null, listUserMasterDto,null);
				}
			}
		}catch(HibernateException he){
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}



	@Override
	public Response getHospitalUnitUserForLogin(OrganizationUnitUserMapperDto organizationUnitUserMapperDto)throws ApplicationException {
		try{
			List<OrganizationUnitUserMapperDto> listOrganizationUnitUserMapperDto = SessionFactory.getCurrentSession().getNamedQuery("GET_HOSPITAL_UNIT_USER_MAPPER_FOR_LOGIN").setInteger("organizationId", organizationUnitUserMapperDto.getOrganizationlId()).setInteger("userId", organizationUnitUserMapperDto.getUserId()).setInteger("unitId", organizationUnitUserMapperDto.getUnitId()).setResultTransformer(Transformers.aliasToBean(OrganizationUnitUserMapperDto.class)).list();
			if(listOrganizationUnitUserMapperDto != null){
				return new Response(SUCCESS, null, null, listOrganizationUnitUserMapperDto,null);
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

}
