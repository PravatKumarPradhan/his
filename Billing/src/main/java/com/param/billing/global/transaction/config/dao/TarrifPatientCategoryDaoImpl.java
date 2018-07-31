package com.param.billing.global.transaction.config.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.TarrifBedCategoryMpprDto;
import com.param.billing.global.transaction.config.dto.TarrifPatientCategoryMpprDto;
import com.param.billing.global.transaction.model.ServiceTarrifBedtypeManager;
import com.param.billing.global.transaction.model.ServiceTarrifPatientCategoryManager;
import com.param.billing.global.transaction.model.ServiceTarrifPaymentEntitlementManager;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
public class TarrifPatientCategoryDaoImpl extends GenericDao<ServiceTarrifPatientCategoryManager, Integer> implements ITarrifPatientCategoryDao, ICommonConstants, IError{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	public TarrifPatientCategoryDaoImpl() {
		super(ServiceTarrifPatientCategoryManager.class);
	}

	@Override
	public Response savePatientCategoryMultiplicationFactor(TarrifPatientCategoryMpprDto patientCategoryMpprDto)
			throws ApplicationException {
		try {
			patientCategoryMpprDto.setServiceTarrifPatientCategoryManagerId(null);
			ServiceTarrifPatientCategoryManager serviceTarrifPatientCategoryManager = mapper.map(patientCategoryMpprDto, ServiceTarrifPatientCategoryManager.class,
					"TarrifPatientCategoryMpprDto_to_ServiceTarrifPatientCategoryManager");
			serviceTarrifPatientCategoryManager = save(serviceTarrifPatientCategoryManager);

			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, serviceTarrifPatientCategoryManager);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getPatientCategoryMultiplicationFactorByTarrifId(Integer serviceTarrifId) throws ApplicationException {
		try{
			List<TarrifPatientCategoryMpprDto> listPatientCategoryMpprDto = sessionFactory.getCurrentSession()
				.getNamedQuery("GET_ACTIVE_PATIENT_TYPE_FACTOR_BY_TARRIF_ID")
				.setInteger("serviceTarrifMasterId", serviceTarrifId)
				.setResultTransformer(Transformers.aliasToBean(TarrifPatientCategoryMpprDto.class)).list();
		
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listPatientCategoryMpprDto, null);
		
		}catch(HibernateException he){
			throw he;
		}catch (Exception e) {
			throw e;
		}
	}

}
