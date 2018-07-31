package com.param.lis.global.dao;
/*package com.param.labinfosystem.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.labinfosystem.global.common.ICommonConstants;
import com.param.labinfosystem.global.common.IError;
import com.param.labinfosystem.global.common.Response;
import com.param.labinfosystem.global.dto.DayMasterDto;
import com.param.labinfosystem.global.dto.HourMasterDto;
import com.param.labinfosystem.global.model.DayMaster;
import com.param.labinfosystem.global.model.HourMaster;

@Repository
@SuppressWarnings("rawtypes")
public class DayMasterDaoImpl extends  GenericDao<DayMaster, Integer> implements IDayMasterDao, ICommonConstants, IError{

	public DayMasterDaoImpl() {
		super(DayMaster.class);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Response getListDayMaster() throws ApplicationException {
		try
		{
			List<DayMasterDto> listDayMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_DAY_MASTER").setResultTransformer(Transformers.aliasToBean(DayMasterDto.class)).list();
			return new Response(SUCCESS, null, null, listDayMasterDto, null);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	

}
*/