package com.param.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;



import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.model.SystemObervationPropertyMaster;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SystemPropertyMasterDaoImpl extends GenericDao<SystemObervationPropertyMaster, Integer> implements ISystemPropertyMasterDao, ICommonConstants {


	public SystemPropertyMasterDaoImpl() {
		super(SystemObervationPropertyMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response saveSystemObservationProperty(SystemObervationPropertyMaster systemObervationPropertyMaster)throws ApplicationException {
		try {
		
			SystemObervationPropertyMaster systemObervationProperty = save(systemObervationPropertyMaster);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	
}
