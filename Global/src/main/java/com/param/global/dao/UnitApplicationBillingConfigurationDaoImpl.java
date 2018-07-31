package com.param.global.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.UnitApplicationConfigurationDto;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitApplicationBillingConfigurationDaoImpl implements IUnitApplicationBillingConfigurationDao,ICommonConstants{

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Response getUnitApplicationBillingConfigByOrgUnit(
			UnitApplicationConfigurationDto unitApplicationConfigurationDto) {
		try {
			UnitApplicationConfigurationDto unitApplicationConfigurationDtos = (UnitApplicationConfigurationDto)sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DEFAULT_SELF_TARIFF_FROM_APP_BILLING_CONF")
					.setInteger("unitId", unitApplicationConfigurationDto.getUnitId())
					.setInteger("organizationId", unitApplicationConfigurationDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(UnitApplicationConfigurationDto.class))
					.uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, unitApplicationConfigurationDtos);
	}catch (HibernateException he) {
		he.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

}
