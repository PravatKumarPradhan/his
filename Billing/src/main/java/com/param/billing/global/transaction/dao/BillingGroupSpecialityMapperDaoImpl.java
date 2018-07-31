package com.param.billing.global.transaction.dao;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.IBillingCommonConstants;
import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMapperDto;
import com.param.billing.global.transaction.model.BillingGroupSpecialityMapper;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes", "unchecked"})
public class BillingGroupSpecialityMapperDaoImpl extends GenericDao<BillingGroupSpecialityMapper, Integer> implements IBillingGroupSpecialityMapperDao, ICommonConstants, IBillingCommonConstants, IError{

	public BillingGroupSpecialityMapperDaoImpl() {
		super(BillingGroupSpecialityMapper.class);
	}

	public static Logger logger=Logger.getLogger(BillingGroupSpecialityMapperDaoImpl.class);
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response saveBillingGroupSpecialityMapper(BillingGroupSpecialityMapperDto billingGroupSpecialityMapperDto)
			throws ApplicationException {
		try {
			BillingGroupSpecialityMapper billingGroupSpecialityMapper = mapper.map(billingGroupSpecialityMapperDto, BillingGroupSpecialityMapper.class, "BillingGroupSpecialityMapperDto_to_BillingGroupSpecialityMapper");
			billingGroupSpecialityMapper = save(billingGroupSpecialityMapper);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, billingGroupSpecialityMapper);
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response deleteBillingGroupSpecialityMapper(BillingGroupSpecialityMapperDto billingGroupSpecialityMapperDto)
			throws ApplicationException {
		try {
			sessionFactory.getCurrentSession().getNamedQuery("DELETE_BILLING_GROUP_SPECIALITY_MAPPER")
			.setInteger("billingGroupId", billingGroupSpecialityMapperDto.getBillingGroupMasterId())
			.executeUpdate();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

}
