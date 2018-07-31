package com.param.lis.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.HourMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.HourMasterDto;

@Repository
@SuppressWarnings("rawtypes")
public class HourMasterDaoImpl extends  GenericDao<HourMaster, Integer> implements IHourMasterDao, ICommonConstants, IError{

	public HourMasterDaoImpl() {
		super(HourMaster.class);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getListHourMaster() throws ApplicationException {
		try
		{
			List<HourMasterDto> listHourMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_HOURE_MASTER").setResultTransformer(Transformers.aliasToBean(HourMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listHourMasterDto, null);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	

}
