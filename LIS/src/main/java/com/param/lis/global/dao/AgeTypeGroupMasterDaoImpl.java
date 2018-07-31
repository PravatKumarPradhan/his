package com.param.lis.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.AgeTypeGroupMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AgeTypeGroupMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class AgeTypeGroupMasterDaoImpl extends  GenericDao<AgeTypeGroupMaster, Integer> implements IAgeTypeGroupMasterDao, ICommonConstants, IError{

	public AgeTypeGroupMasterDaoImpl() {
		super(AgeTypeGroupMaster.class);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getListAgeTypeGroupMaster() throws ApplicationException {
		try
		{
			List<AgeTypeGroupMasterDto> listAgeTypeGroupMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_AGE_TYPE_GROUP_MASTER").setResultTransformer(Transformers.aliasToBean(AgeTypeGroupMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listAgeTypeGroupMasterDto, null);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	

}
