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

import com.param.billing.global.model.BankBranchMaster;
import com.param.global.billing.dto.BankBranchMasterDto;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BankBranchMasterDaoImpl extends GenericDao<BankBranchMaster, Integer>
		implements IBankBranchMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	public BankBranchMasterDaoImpl() {
		super(BankBranchMaster.class);
	}

	@Override
	public Response saveBankBranchMaster(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {
			BankBranchMaster bankBranchMaster = new BankBranchMaster();
			bankBranchMaster.setBranchDesc(bankMasterDto.getBranchDesc());
			bankBranchMaster.setBranchCode(bankMasterDto.getBranchCode());
			bankBranchMaster.setCreatedBy(bankMasterDto.getCreatedBy());
			bankBranchMaster.setUpdatedBy(bankMasterDto.getUpdatedBy());
			bankBranchMaster.setCreatedDate(GlobalCommonDateUtils.getDate(bankMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			bankBranchMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(bankMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			bankBranchMaster.setStatus(bankMasterDto.getStatus());
			bankBranchMaster.setOrgnisationId(bankMasterDto.getOrgnisationId());
			save(bankBranchMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankBranchMasterById(int branchId, int orgId) throws ApplicationException {
		try {

			List<BankBranchMasterDto> BankBranchMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BANK_BRANCH_MASTER_BY_ID").setInteger("orgId", orgId)
					.setInteger("branchId", branchId)
					.setResultTransformer(Transformers.aliasToBean(BankBranchMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, BankBranchMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankBranchMasterList(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {
			List<BankBranchMasterDto> BankBranchMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BANK_BRANCH_MASTER_LIST")
					.setInteger("orgId", bankMasterDto.getOrgnisationId())
					.setFirstResult(bankMasterDto.getOffset() != null ? bankMasterDto.getOffset() : 0)
					.setMaxResults(bankMasterDto.getNoOfRecordsPerPage() != null ? bankMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(BankBranchMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, BankBranchMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBankBranchMaster(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		BankBranchMaster bankBranchMaster = findById(bankMasterDto.getBranchId());
		bankBranchMaster.setBranchDesc(bankMasterDto.getBranchDesc());
		bankBranchMaster.setBranchCode(bankMasterDto.getBranchCode());
		bankBranchMaster.setUpdatedBy(bankMasterDto.getUpdatedBy());
		bankBranchMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(bankMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
		update(bankBranchMaster);
		return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
	}

	@Override
	public Response updateBankBranchMasterStatus(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {

			BankBranchMaster bankBranchMaster = findById(bankMasterDto.getBranchId());
			bankBranchMaster.setStatus(bankMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			bankBranchMaster.setUpdatedBy(bankMasterDto.getUpdatedBy());
			bankBranchMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(bankMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(bankBranchMaster);
			return new Response<>(SUCCESS, COMMON_UPDATE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankBranchMasterCount(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(BankBranchMaster.class);
			c.add(Restrictions.eq("orgnisationId", bankMasterDto.getOrgnisationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankBranchMasterByNameNotId(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {

			List<BankBranchMasterDto> BankBranchMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BANK_BRANCH_MASTER_BY_NAME_NOT_ID")
					.setInteger("branchId", bankMasterDto.getBranchId())
					.setString("branchDesc", bankMasterDto.getBranchDesc())
					.setResultTransformer(Transformers.aliasToBean(BankBranchMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, BankBranchMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankBranchMasterByName(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {
			List<BankBranchMasterDto> BankBranchMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BANK_BRANCH_MASTER_BY_NAME")
					.setInteger("orgId", bankMasterDto.getOrgnisationId())
					.setString("branchDesc", bankMasterDto.getBranchDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(BankBranchMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, BankBranchMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
