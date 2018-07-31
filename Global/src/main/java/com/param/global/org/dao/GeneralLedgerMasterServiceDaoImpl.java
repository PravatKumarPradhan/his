package com.param.global.org.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.entity.org.model.GeneralLedgerMaster;
import com.param.entity.org.model.LicenceTypeMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.dto.GeneralLedgerMasterDto;
import com.param.global.org.dto.LiceneceTypeMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GeneralLedgerMasterServiceDaoImpl extends GenericDao<GeneralLedgerMaster, Integer> implements IGeneralLedgerMasterServiceDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	public GeneralLedgerMasterServiceDaoImpl() {
		super(GeneralLedgerMaster.class);
	}

	@Override
	public Response getGeneralLedgerMasterByName(GeneralLedgerMasterDto generalLedgerMasterDto)
			throws ApplicationException {
		try {
			List<GeneralLedgerMasterDto> generalLedgerMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GENERAL_LEDGER_MASTER_BY_NAME")
					.setString("ledgerName", generalLedgerMasterDto.getGeneralLedgerName().toLowerCase())
					.setInteger("orgId", generalLedgerMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(GeneralLedgerMasterDto.class)).list();
			return new Response(SUCCESS, null, null, generalLedgerMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response saveGeneralLedgerMaster(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException {
		try {
			GeneralLedgerMaster generalLedgerMaster = new GeneralLedgerMaster();
			generalLedgerMaster.setGeneralLedgerCode(generalLedgerMasterDto.getGeneralLedgerCode());
			generalLedgerMaster.setGeneralLedgerName(generalLedgerMasterDto.getGeneralLedgerName());
			generalLedgerMaster.setCreatedBy(generalLedgerMasterDto.getCreatedBy());
			generalLedgerMaster.setCreatedDate(
					GlobalCommonDateUtils.getDate(generalLedgerMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			generalLedgerMaster.setStatus(generalLedgerMasterDto.getStatus());
			generalLedgerMaster.setUpdatedBy(generalLedgerMasterDto.getUpdatedBy());
			generalLedgerMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(generalLedgerMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			generalLedgerMaster.setOrganizationId(generalLedgerMasterDto.getOrganizationId());
			generalLedgerMaster.setUnitId(generalLedgerMasterDto.getUnitId());
			save(generalLedgerMaster);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getGeneralLedgerMasterById(int lederId, int orgId) throws ApplicationException {
		try {
			List<GeneralLedgerMasterDto> generalLedgerMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GENERAL_LEDGER_MASTER_BY_ID").setInteger("lederId", lederId)
					.setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(GeneralLedgerMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, generalLedgerMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getGeneralLedgerMasterList(int orgId) throws ApplicationException {
		try {
			List<GeneralLedgerMasterDto> generalLedgerMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GENERAL_LEDGER_MASTER_LIST").setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(GeneralLedgerMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, generalLedgerMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getLedgerByNameNotId(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException {
		try {
			List<GeneralLedgerMasterDto> generalLedgerMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LEDGER_BY_NAME_NOT_ID")
					.setInteger("ledgerId", generalLedgerMasterDto.getGeneralLedgerId())
					.setString("ledgerName", generalLedgerMasterDto.getGeneralLedgerName())
					.setResultTransformer(Transformers.aliasToBean(GeneralLedgerMasterDto.class)).list();
			return new Response(SUCCESS, null, null, generalLedgerMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateGeneralLedgerMaster(GeneralLedgerMasterDto generalLedgerMasterDto)
			throws ApplicationException {
		try {
			GeneralLedgerMaster generalLedgerMaster = findById(generalLedgerMasterDto.getGeneralLedgerId());
			generalLedgerMaster.setGeneralLedgerCode(generalLedgerMasterDto.getGeneralLedgerCode());
			generalLedgerMaster.setGeneralLedgerName(generalLedgerMasterDto.getGeneralLedgerName());
			generalLedgerMaster.setUpdatedBy(generalLedgerMasterDto.getUpdatedBy());
			generalLedgerMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(generalLedgerMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(generalLedgerMaster);
			return new Response(SUCCESS, COMMON_UPDATE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateGeneralLedgerMasterStatus(GeneralLedgerMasterDto generalLedgerMasterDto)
			throws ApplicationException {
		try {
			GeneralLedgerMaster generalLedgerMaster = findById(generalLedgerMasterDto.getGeneralLedgerId());
			
			generalLedgerMaster.setStatus(generalLedgerMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			generalLedgerMaster.setUpdatedBy(generalLedgerMasterDto.getUpdatedBy());
			generalLedgerMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(generalLedgerMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));

			update(generalLedgerMaster);
			return new Response<LiceneceTypeMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getGeneralLedgerMasterActiveList(int orgId) throws ApplicationException {
		try {
			List<GeneralLedgerMasterDto> generalLedgerMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_GENERAL_LEDGER_MASTER_ACTIVE_LIST").setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(GeneralLedgerMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, generalLedgerMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
