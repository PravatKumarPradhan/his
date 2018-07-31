package com.param.adt.master.unit.dao;

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

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.BillingBedCategoryMasterDto;
import com.param.adt.master.unit.model.BillingBedCategoryMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BillingBedCategoryMasterDaoImpl extends GenericDao<BillingBedCategoryMaster, Integer> implements IBillingBedCategoryMasterDao,ICommonConstants{

	
	public BillingBedCategoryMasterDaoImpl() {
		super(BillingBedCategoryMaster.class);
	}



	@Autowired
	SessionFactory sessionFactory;
	

	@Override
	public Response getActiveBillingBedCategoryList(BillingBedCategoryMasterDto bedCategoryMasterDto) {
		try {
			List<BillingBedCategoryMasterDto> billingBedCategoryMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_BILLING_BED_CATEGORY_LIST")
					.setInteger("orgId", bedCategoryMasterDto.getOrganizationId())
					.setInteger("unitId", bedCategoryMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BillingBedCategoryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, billingBedCategoryMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveBillingBedCategoryByBedCat(BillingBedCategoryMasterDto bedCategoryMasterDto)
			throws ApplicationException {
		try {
			List<BillingBedCategoryMasterDto> billingBedCategoryMasterDtos = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BILLING_BED_LIST_BY_BED_CAT")
					.setInteger("organizationId", bedCategoryMasterDto.getOrganizationId())
					.setInteger("bedCategoryId", bedCategoryMasterDto.getBedCategoryId())
					.setResultTransformer(Transformers.aliasToBean(BillingBedCategoryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, billingBedCategoryMasterDtos, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response saveBillingBedCategoryMaster(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			BillingBedCategoryMaster billingBedCategoryMaster=new BillingBedCategoryMaster();
			billingBedCategoryMaster.setBillingBedCode(billingBedCategoryMasterDto.getBillingBedCode());
			billingBedCategoryMaster.setBillingBedDesc(billingBedCategoryMasterDto.getBillingBedDesc());
			billingBedCategoryMaster.setHierarchyId(billingBedCategoryMasterDto.getHierarchyId());
			billingBedCategoryMaster.setCreatedBy(billingBedCategoryMasterDto.getCreatedBy());
			billingBedCategoryMaster.setUpdatedBy(billingBedCategoryMasterDto.getUpdatedBy());
			billingBedCategoryMaster.setCreatedDate(GlobalCommonDateUtils.getDate(billingBedCategoryMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			billingBedCategoryMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(billingBedCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			billingBedCategoryMaster.setStatus(billingBedCategoryMasterDto.getStatus());
			save(billingBedCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBillingBedCategoryById(int billingBedId, int orgId, int unitId) throws ApplicationException {
		try {
			List<BillingBedCategoryMasterDto> billingBedCategoryMasterDtosList=sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BILLING_BED_CATEGORY_BY_ID")
					.setInteger("orgId",orgId)
					.setInteger("unitId", unitId)
					.setInteger("billingBedId", billingBedId)
					.setResultTransformer(Transformers.aliasToBean(BillingBedCategoryMasterDto.class)).list();		
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, billingBedCategoryMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBillingBedCategoryList(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			List<BillingBedCategoryMasterDto> billingBedCategoryMasterDtosList=sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BILLING_BED_CATEGORY_LIST")
					.setInteger("orgId",billingBedCategoryMasterDto.getOrganizationId())
					.setInteger("unitId", billingBedCategoryMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(BillingBedCategoryMasterDto.class)).list();		
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, billingBedCategoryMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBillingBedCategoryMaster(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			BillingBedCategoryMaster billingBedCategoryMaster=findById(billingBedCategoryMasterDto.getBedCategoryId());
			billingBedCategoryMaster.setBillingBedCode(billingBedCategoryMasterDto.getBillingBedCode());
			billingBedCategoryMaster.setBillingBedDesc(billingBedCategoryMasterDto.getBillingBedDesc());
			billingBedCategoryMaster.setHierarchyId(billingBedCategoryMasterDto.getHierarchyId());
			billingBedCategoryMaster.setUpdatedBy(billingBedCategoryMasterDto.getUpdatedBy());
			billingBedCategoryMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(billingBedCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(billingBedCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBillingBedCategoryMasterStatus(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			BillingBedCategoryMaster billingBedCategoryMaster=findById(billingBedCategoryMasterDto.getBedCategoryId());
			billingBedCategoryMaster.setStatus(billingBedCategoryMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			billingBedCategoryMaster.setUpdatedBy(billingBedCategoryMasterDto.getUpdatedBy());
			billingBedCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(billingBedCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(billingBedCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBillingBedCategoryMasterCount(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(BillingBedCategoryMaster.class);
			c.add(Restrictions.eq("organizationId", billingBedCategoryMasterDto.getOrganizationId()));
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
	public Response getBillingBedCategoryByName(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			List<BillingBedCategoryMasterDto> billingBedCategoryMasterDtosList=sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BILLING_BED_CATEGORY_BY_NAME")
					.setInteger("orgId",billingBedCategoryMasterDto.getOrganizationId())
					.setString("desc", billingBedCategoryMasterDto.getBillingBedDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(BillingBedCategoryMasterDto.class)).list();		
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, billingBedCategoryMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBillingBedCategoryByNameNotById(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {

			List<BillingBedCategoryMasterDto> billingBedCategoryMasterDtosList=sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BILLING_BED_CATEGORY_BY_NAME_NOT_BY_ID")
					.setInteger("billingBedId",billingBedCategoryMasterDto.getBillingBedCategoryId())
					.setString("desc", billingBedCategoryMasterDto.getBillingBedDesc())
					.setResultTransformer(Transformers.aliasToBean(BillingBedCategoryMasterDto.class)).list();		
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, billingBedCategoryMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
