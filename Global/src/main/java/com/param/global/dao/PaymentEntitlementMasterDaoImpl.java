package com.param.global.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.global.dto.CommonDto;
import com.param.global.dto.PatientCategoryMasterDto;
import com.param.global.dto.PaymentEntitlementMasterDto;
import com.param.global.model.PaymentEntitlementMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class PaymentEntitlementMasterDaoImpl extends GenericDao<PaymentEntitlementMaster, Integer> implements IPaymentEntitlementMasterDao, ICommonConstants, IError {

	public PaymentEntitlementMasterDaoImpl() {
		super(PaymentEntitlementMaster.class);
	}

	@Override
	public Response getPaymentEntitlementMaster(
			PaymentEntitlementMasterDto paymentEntitlementMasterDto)
			throws ApplicationException {
		try {
			List<PaymentEntitlementMasterDto> listPaymentEntitlementMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_ACTIVE_PAYMENT_ENTITLEMENT_LIST").setResultTransformer(Transformers.aliasToBean(PaymentEntitlementMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, "", listPaymentEntitlementMasterDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getPaymentEntitlementListByOrgId(int orgId) throws ApplicationException {
		try{
			List<CommonDto> listCommonDto = sessionFactory.getCurrentSession().getNamedQuery("GET_PAYMENT_ENTITLEMENT_LIST_BY_ORG").setInteger("orgId", orgId).setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listCommonDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	

}
