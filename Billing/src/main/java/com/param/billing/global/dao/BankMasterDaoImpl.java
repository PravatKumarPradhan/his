package com.param.billing.global.dao;

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
import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.dto.BankMasterDto;
import com.param.billing.global.model.BankMaster;
import com.param.global.common.GlobalCommonDateUtils;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BankMasterDaoImpl extends GenericDao<BankMaster, Integer> implements IBankMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;
	public BankMasterDaoImpl() {
		super(BankMaster.class);
	}

	@Override
	public Response getActiveBankMaster(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			List bankMasterList = sessionFactory.getCurrentSession().getNamedQuery("GET_ACTIVE_BANK_MASTER")
					.setInteger("unitId", bankMasterDto.getUnitId())
					.setInteger("orgId", bankMasterDto.getOrgnisationId())
					.setResultTransformer(Transformers.aliasToBean(BankMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, bankMasterList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankMasterCount(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(BankMaster.class); 
			c.add(Restrictions.eq("orgnisationId", bankMasterDto.getOrgnisationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBankMasterStatus(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			BankMaster bankMaster=findById(bankMasterDto.getBankId());
			bankMaster.setStatus(bankMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			bankMaster.setUpdatedBy(bankMasterDto.getUpdatedBy());
			bankMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(bankMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(bankMaster);
			return new Response<>(SUCCESS, COMMON_UPDATE, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBankMaster(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			BankMaster bankMaster=findById(bankMasterDto.getBankId());
			bankMaster.setBankAddress(bankMasterDto.getBankAddress());
			bankMaster.setBankCode(bankMasterDto.getBankCode());
			bankMaster.setBankDsc(bankMasterDto.getBankDsc());
			bankMaster.setBankName(bankMasterDto.getBankName());
			bankMaster.setBranchId(bankMasterDto.getBranchId());
			bankMaster.setUpdatedBy(bankMasterDto.getUpdatedBy());
			bankMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(bankMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(bankMaster);
			return new Response<>(SUCCESS,SUCCESS_CODE , COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankMasterList(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			List bankMasterList = sessionFactory.getCurrentSession().getNamedQuery("GET_BANK_MASTER_LIST")
					.setInteger("orgId", bankMasterDto.getOrgnisationId())
					.setFirstResult(bankMasterDto.getOffset() != null ? bankMasterDto.getOffset() : 0)
					.setMaxResults(bankMasterDto.getNoOfRecordsPerPage() != null ? bankMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(BankMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, bankMasterList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankMasterById(int bankId, int orgId) throws ApplicationException {
		try {
			List bankMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BANK_MASTER_LIST_BY_ID")
					.setInteger("orgId", orgId)
					.setInteger("bankId", bankId)
					.setResultTransformer(Transformers.aliasToBean(BankMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, bankMasterList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveBankMaster(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			BankMaster bankMaster=new BankMaster();
			bankMaster.setBankAddress(bankMasterDto.getBankAddress());
			bankMaster.setBankCode(bankMasterDto.getBankCode());
			bankMaster.setBankDsc(bankMasterDto.getBankDsc());
			bankMaster.setBankName(bankMasterDto.getBankName());
			bankMaster.setBranchId(bankMasterDto.getBranchId());
			bankMaster.setCreatedBy(bankMasterDto.getCreatedBy());
			bankMaster.setCreatedDate(GlobalCommonDateUtils.getDate(bankMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			bankMaster.setOrgnisationId(bankMasterDto.getOrgnisationId());
			bankMaster.setStatus(bankMasterDto.getStatus());
			bankMaster.setUpdatedBy(bankMasterDto.getUpdatedBy());
			bankMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(bankMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			bankMaster.setUnitId(bankMasterDto.getUnitId());
			save(bankMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankMasterByNameNotId(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			
			List bankMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BANKMASTER_BY_NAME_NOT_ID")
					.setInteger("bankId", bankMasterDto.getBankId())
					.setString("bankName", bankMasterDto.getBankName())
					.setResultTransformer(Transformers.aliasToBean(BankMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, bankMasterList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankMasterByName(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			List bankMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BANK_MASTER_BY_NAME")
					.setInteger("orgId", bankMasterDto.getOrgnisationId())
					.setString("bankName", bankMasterDto.getBankName())
					.setResultTransformer(Transformers.aliasToBean(BankMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, bankMasterList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
