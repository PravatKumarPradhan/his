package com.param.adt.master.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OrganizationMasterDto;
import com.param.adt.master.global.model.OrganizationMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({"rawtypes","unchecked"})
@Repository
public class OrganizationMasterDaoImpl extends GenericDao<OrganizationMaster, Integer> implements IOrganizationMasterDao,ICommonConstants
{

	@Autowired
	DozerBeanMapper mapper;
	
	public OrganizationMasterDaoImpl() 
	{
		super(OrganizationMaster.class);
	}

	@Override
	public Response getOrganizationMasterList() throws ApplicationException {
		try{
			List<OrganizationMasterDto> hospitalMasterDtoList = sessionFactory.getCurrentSession().getNamedQuery("GET_HOSPITAL_MASTER_LIST").setResultTransformer(Transformers.aliasToBean(OrganizationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, hospitalMasterDtoList,null);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@Override
	public Response saveOrganizationMaster(OrganizationMasterDto OrganizationMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateOrganizationMaster(OrganizationMasterDto OrganizationMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getOrganizationByName(OrganizationMasterDto OrganizationMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getOrganizationByNameAndId(OrganizationMasterDto OrganizationMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getOrganizationSuggestionByName(OrganizationMasterDto OrganizationMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
