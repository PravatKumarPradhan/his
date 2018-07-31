package com.param.global.billing.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.global.model.CurrencyDenominationMaster;
import com.param.global.billing.dto.CurrencyDenominationMasterDto;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class CurrencyDenominationMasterDaoImpl extends GenericDao<CurrencyDenominationMaster, Integer>
		implements ICurrencyDenominationMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	public CurrencyDenominationMasterDaoImpl() {
		super(CurrencyDenominationMaster.class);
	}

	@Override
	public Response saveCurrencyDenominationMaster(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {
			CurrencyDenominationMaster currencyDenominationMaster = new CurrencyDenominationMaster();
			currencyDenominationMaster.setCreatedBy(currencyDenominationMasterDto.getCreatedBy());
			currencyDenominationMaster.setCreatedDate(GlobalCommonDateUtils
					.getDate(currencyDenominationMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			currencyDenominationMaster.setCurrencyId(currencyDenominationMasterDto.getCurrencyId());
			currencyDenominationMaster.setDenominationCode(currencyDenominationMasterDto.getDenominationCode());
			currencyDenominationMaster.setDenominationDesc(currencyDenominationMasterDto.getDenominationDesc());
			currencyDenominationMaster.setDenominatonUnit(currencyDenominationMasterDto.getDenominatonUnit());
			currencyDenominationMaster.setLowestDenomination(currencyDenominationMasterDto.getLowestDenomination());
			currencyDenominationMaster.setOrgnisationId(currencyDenominationMasterDto.getOrgnisationId());
			currencyDenominationMaster.setUpdatedBy(currencyDenominationMasterDto.getUpdatedBy());
			currencyDenominationMaster.setUnitId(currencyDenominationMasterDto.getUnitId());
			currencyDenominationMaster.setUpdatedDate(GlobalCommonDateUtils
					.getDate(currencyDenominationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			currencyDenominationMaster.setStatus(currencyDenominationMasterDto.getStatus());
			save(currencyDenominationMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getCurrencyDenominationMasterById(int currencyId, int orgId) throws ApplicationException {
		try {

			List<CurrencyDenominationMasterDto> currencyDenominationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CURRENCY_DENOMINATIONMASTER_BY_ID")
					.setInteger("orgId", orgId)
					.setInteger("currencyId", currencyId)
					.setResultTransformer(Transformers.aliasToBean(CurrencyDenominationMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, currencyDenominationMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getCurrencyDenominationMasterList(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {
			List<CurrencyDenominationMasterDto> currencyDenominationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CURRENCY_DENOMINATIONMASTER_LIST")
					.setInteger("orgId", currencyDenominationMasterDto.getOrgnisationId())
					.setFirstResult(currencyDenominationMasterDto.getOffset() != null ? currencyDenominationMasterDto.getOffset() : 0)
					.setMaxResults(currencyDenominationMasterDto.getNoOfRecordsPerPage() != null ? currencyDenominationMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(CurrencyDenominationMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, currencyDenominationMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateCurrencyDenominationMaster(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {

			CurrencyDenominationMaster currencyDenominationMaster = findById(
					currencyDenominationMasterDto.getCurrencyDenominationId());
			currencyDenominationMaster.setCurrencyId(currencyDenominationMasterDto.getCurrencyId());
			currencyDenominationMaster.setDenominationCode(currencyDenominationMasterDto.getDenominationCode());
			currencyDenominationMaster.setDenominationDesc(currencyDenominationMasterDto.getDenominationDesc());
			currencyDenominationMaster.setDenominatonUnit(currencyDenominationMasterDto.getDenominatonUnit());
			currencyDenominationMaster.setLowestDenomination(currencyDenominationMasterDto.getLowestDenomination());
			currencyDenominationMaster.setUpdatedBy(currencyDenominationMasterDto.getUpdatedBy());
			currencyDenominationMaster.setUpdatedDate(GlobalCommonDateUtils
					.getDate(currencyDenominationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(currencyDenominationMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateCurrencyDenominationMasterStatus(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {
			CurrencyDenominationMaster currencyDenominationMaster = findById(
					currencyDenominationMasterDto.getCurrencyDenominationId());

			currencyDenominationMaster
					.setStatus(currencyDenominationMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			currencyDenominationMaster.setUpdatedBy(currencyDenominationMasterDto.getUpdatedBy());
			currencyDenominationMaster.setUpdatedDate(GlobalCommonDateUtils
					.getDate(currencyDenominationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));

			update(currencyDenominationMaster);
			return new Response<>(SUCCESS, COMMON_UPDATE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getCurrencyDenominationCount(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(CurrencyDenominationMaster.class); 
			c.add(Restrictions.eq("orgnisationId", currencyDenominationMasterDto.getOrgnisationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getCurrencyDenominationMasterByName(CurrencyDenominationMasterDto currencyDenominationMasterDto)
			throws ApplicationException {
		try {
			List<CurrencyDenominationMasterDto> currencyDenominationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CURRENCY_DENOMINATIONMASTER_BY_NAME")
					.setInteger("orgId", currencyDenominationMasterDto.getOrgnisationId())
					.setString("denominationName", currencyDenominationMasterDto.getDenominationDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(CurrencyDenominationMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, currencyDenominationMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getCurrencyDenominationMasterByNameNotById(
			CurrencyDenominationMasterDto currencyDenominationMasterDto) throws ApplicationException {
		try {
			List<CurrencyDenominationMasterDto> currencyDenominationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CURRENCY_DENOMINATIONMASTER_BY_NAME_NOT_BY_ID")
					.setInteger("currencyId", currencyDenominationMasterDto.getCurrencyDenominationId())
					.setString("denominationName", currencyDenominationMasterDto.getDenominationDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(CurrencyDenominationMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, currencyDenominationMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
