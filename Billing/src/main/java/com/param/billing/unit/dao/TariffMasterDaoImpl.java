package com.param.billing.unit.dao;

import java.util.List;
import java.util.ListIterator;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.global.transaction.model.TariffMaster;
import com.param.billing.global.transaction.model.TariffPaymentEntitlementMapper;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.TariffMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TariffMasterDaoImpl extends GenericDao<TariffMaster, Integer> implements ITariffMasterDao,ICommonConstants{

	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TariffMasterDaoImpl() {
		super(TariffMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response saveTariffMaster(TariffMasterDto tariffMasterDto) {
		try {

			TariffMaster tariffMaster = mapper.map(tariffMasterDto, TariffMaster.class,
					"TariffMasterDto_to_TariffMaster");
			tariffMaster = save(tariffMaster);
			
			tariffMasterDto.setTariffId(tariffMaster.getTariffId());
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, tariffMasterDto);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	public Response saveTariffPaymentEntitlementMapper(TariffMasterDto tariffMasterDto) {
		try {
			Session session=sessionFactory.getCurrentSession();
			int saveCount=0;
			
			if (tariffMasterDto.getApplicablePaymentEntitlementIdList() != null
					&& tariffMasterDto.getApplicablePaymentEntitlementIdList().size() > 0) {

				ListIterator<Integer> itr = tariffMasterDto.getApplicablePaymentEntitlementIdList().listIterator();
				while (itr.hasNext()) 
				{
					TariffPaymentEntitlementMapper tariffPaymentEntitlementMapper=new TariffPaymentEntitlementMapper();
						tariffPaymentEntitlementMapper.setTariffId(tariffMasterDto.getTariffId());
						tariffPaymentEntitlementMapper.setPaymentEntitlementId(itr.next());
						tariffPaymentEntitlementMapper.setCreatedBy(tariffMasterDto.getUpdatedBy());
						tariffPaymentEntitlementMapper.setCreatedDate(GlobalCommonDateUtils.getDate(tariffMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
						tariffPaymentEntitlementMapper.setUpdatedDate(GlobalCommonDateUtils.getDate(tariffMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
						tariffPaymentEntitlementMapper.setUpdatedBy(tariffMasterDto.getUpdatedBy());
						tariffPaymentEntitlementMapper.setOrganizationId(tariffMasterDto.getOrganizationId());
						tariffPaymentEntitlementMapper.setUnitId(tariffMasterDto.getUnitId());
						tariffPaymentEntitlementMapper.setStatus('A');
						int count=(int) session.save(tariffPaymentEntitlementMapper);
					
					if (count>0)
						saveCount++;
				}
			}
			if(tariffMasterDto.getApplicablePaymentEntitlementIdList().size()==saveCount)
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			else
				return new Response(ERROR, null, COMMON_ERROR, null, null);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
	

	@Override
	public Response getTariffListByName(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			List<TariffMasterDto> tariffMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TARIFF_MASTER_LIST_BY_NAME")
					.setInteger("orgId", tariffMasterDto.getOrganizationId())
					.setInteger("unitId", tariffMasterDto.getUnitId())
					.setString("tariffDesc",tariffMasterDto.getTariffDesc())
					.setResultTransformer(Transformers.aliasToBean(TariffMasterDto.class)).list();
			return new Response(SUCCESS, null, null, tariffMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getTariffMasterList(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			List<TariffMasterDto> tariffMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TARIFF_MASTER_LIST")
					.setInteger("orgId", tariffMasterDto.getOrganizationId())
					.setInteger("unitId", tariffMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(TariffMasterDto.class)).list();
			return new Response(SUCCESS, null, null, tariffMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPaymentEntitlementListByTariffid(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			List<TariffMasterDto> tariffMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAYMENT_ENTITLEMENT_LIST_BY_TARIFF_ID")
					.setInteger("tariffId", tariffMasterDto.getTariffId())
					.setResultTransformer(Transformers.aliasToBean(TariffMasterDto.class)).list();
			return new Response(SUCCESS, null, null, tariffMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getTariffListById(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			TariffMasterDto tariffMasterDtoo = (TariffMasterDto)sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TARIFF_MASTER_LIST_BY_ID")
					.setInteger("tariffId", tariffMasterDto.getTariffId())
					.setResultTransformer(Transformers.aliasToBean(TariffMasterDto.class)).uniqueResult();
			
			List<TariffMasterDto> tariffMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAYMENT_ENTITLEMENT_LIST_BY_TARIFF_ID")
					.setInteger("tariffId", tariffMasterDto.getTariffId())
					.setResultTransformer(Transformers.aliasToBean(TariffMasterDto.class)).list();
			
			tariffMasterDtoo.setApplicablePaymentEntitlementIdList2(tariffMasterDtosList);
			
			return new Response(SUCCESS, null, null, null, tariffMasterDtoo);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getTariffListByNameNotId(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			List<TariffMasterDto> tariffMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TARIFF_MASTER_LIST_BY_NAME_NOT_ID")
					.setInteger("orgId", tariffMasterDto.getOrganizationId())
					.setInteger("tariffId", tariffMasterDto.getTariffId())
					.setInteger("unitId", tariffMasterDto.getUnitId())
					.setString("tariffDesc",tariffMasterDto.getTariffDesc())
					.setResultTransformer(Transformers.aliasToBean(TariffMasterDto.class)).list();
			return new Response(SUCCESS, null, null, tariffMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateTariffMaster(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {

			sessionFactory.getCurrentSession().createQuery("UPDATE TariffMaster tariff SET tariff.tariffCode='"+ tariffMasterDto.getTariffCode() +"', "
					+ "tariff.tariffDesc='"+tariffMasterDto.getTariffDesc()+"', "
					+ "tariff.validFrom='"+GlobalCommonDateUtils.getDate(tariffMasterDto.getValidFrom(), "dd-M-yyyy HH:mm:ss")+"', "
					+ "tariff.validTo='"+GlobalCommonDateUtils.getDate(tariffMasterDto.getValidTo(), "dd-M-yyyy HH:mm:ss")+"', "
					+ "tariff.updatedBy="+tariffMasterDto.getUpdatedBy()+", "
					+ "tariff.updatedDate='"+GlobalCommonDateUtils.getDate(tariffMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' "
					+ "WHERE tariff.tariffId="+tariffMasterDto.getTariffId())
			.executeUpdate();
			
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response inactiveOldPaymentEntitlements(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			sessionFactory.getCurrentSession().createQuery("update TariffPaymentEntitlementMapper tpem "
					+ "SET tpem.status='I', "
					+ "tpem.updatedBy="+tariffMasterDto.getUpdatedBy()+", "
					+ "tpem.updatedDate='"+GlobalCommonDateUtils.getDate(tariffMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' "
					+ "WHERE tpem.tariffId="+tariffMasterDto.getTariffId())
			.executeUpdate();
			
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response updateStatusForTariff(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			sessionFactory.getCurrentSession().createQuery("update TariffMaster tariff "
					+ "SET tariff.status='"+tariffMasterDto.getStatus()+"', "
					+ "tariff.updatedBy="+tariffMasterDto.getUpdatedBy()+", "
					+ "tariff.updatedDate='"+GlobalCommonDateUtils.getDate(tariffMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' "
					+ "WHERE tariff.tariffId="+tariffMasterDto.getTariffId())
			.executeUpdate();
			
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} 
	}
	
	@Override
	public Response updateStatusForTariffPaymentMapper(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			sessionFactory.getCurrentSession().createQuery("update TariffPaymentEntitlementMapper tpem "
					+ "SET tpem.status='"+tariffMasterDto.getStatus()+"', "
					+ "tpem.updatedBy="+tariffMasterDto.getUpdatedBy()+", "
					+ "tpem.updatedDate='"+GlobalCommonDateUtils.getDate(tariffMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' "
					+ "WHERE tpem.tariffPaymentEntitlementMapperId="+tariffMasterDto.getTariffPaymentEntitlementMapperId())
			.executeUpdate();
			
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response getActiveTariffMasterList(TariffMasterDto tariffMasterDto) throws ApplicationException {
		try {
			List<TariffMasterDto> tariffMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_TARIFF_MASTER_LIST")
					.setInteger("orgId", tariffMasterDto.getOrganizationId())
					.setInteger("unitId", tariffMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(TariffMasterDto.class)).list();
			return new Response(SUCCESS, null, null, tariffMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPaymentEntitlementListByTariffIdList(TariffMasterDto tariffMasterDto)
			throws ApplicationException {
		try {
			List<TariffMasterDto> tariffMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAYMENT_ENTITLEMENT_LIST_BY_TARIFF_ID_LIST")
					.setParameterList("tariffIdList", tariffMasterDto.getTariffIdList())
					.setResultTransformer(Transformers.aliasToBean(TariffMasterDto.class)).list();
			return new Response(SUCCESS, null, null, tariffMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
