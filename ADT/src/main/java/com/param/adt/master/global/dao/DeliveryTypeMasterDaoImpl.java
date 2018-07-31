package com.param.adt.master.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DeliveryTypeMasterDto;
import com.param.adt.master.global.model.DeliveryTypeMaster;
import com.param.global.dto.StateMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class DeliveryTypeMasterDaoImpl extends GenericDao<DeliveryTypeMaster, Integer>
		implements IDeliveryTypeMasterDao, ICommonConstants {

	public DeliveryTypeMasterDaoImpl() {
		super(DeliveryTypeMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getDeliveryTypeByName(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			List<DeliveryTypeMasterDto> deliveryTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DELIVERY_TYPE_LIST_BY_NAME")
					.setString("deliveryName", deliveryTypeMasterDto.getDeliveryTypeName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DeliveryTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, deliveryTypeMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addDeliveryTypeMaster(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			DeliveryTypeMaster deliveryTypeMaster = mapper.map(deliveryTypeMasterDto, DeliveryTypeMaster.class,
					"DeliveryTypeMasterDto_to_DeliveryTypeMaster");
			deliveryTypeMaster = save(deliveryTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDeliveryTypeByNameNotId(DeliveryTypeMasterDto deliveryTypeMasterDto)
			throws ApplicationException {
		try {
			List<DeliveryTypeMasterDto> deliveryTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DELIVERY_TYPE_LIST_BY_NAME_NOT_ID")
					.setInteger("deliveryId", deliveryTypeMasterDto.getDeliveryTypeId())
					.setString("deliveryName", deliveryTypeMasterDto.getDeliveryTypeName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DeliveryTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, deliveryTypeMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDeliveryTypeMaster(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {

			DeliveryTypeMaster deliveryTypeMaster = findById(deliveryTypeMasterDto.getDeliveryTypeId());
			deliveryTypeMaster.setDeliveryTypeCode(deliveryTypeMasterDto.getDeliveryTypeCode());
			deliveryTypeMaster.setDeliveryTypeName(deliveryTypeMasterDto.getDeliveryTypeName());
			deliveryTypeMaster.setUpdatedBy(deliveryTypeMasterDto.getUpdatedBy());
			deliveryTypeMaster.setUpdatedDate(
					ADTCommonDateUtils.getDate(deliveryTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(deliveryTypeMaster);
			return new Response<StateMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getDeliveryTypeById(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			List<DeliveryTypeMasterDto> deliveryTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DELIVERY_TYPE_LIST_BY_ID")
					.setInteger("deliveryId", deliveryTypeMasterDto.getDeliveryTypeId())
					.setResultTransformer(Transformers.aliasToBean(DeliveryTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, deliveryTypeMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDeliveryTypeStatus(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {

			DeliveryTypeMaster deliveryTypeMaster = findById(deliveryTypeMasterDto.getDeliveryTypeId());
			deliveryTypeMaster.setStatus(deliveryTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			deliveryTypeMaster.setUpdatedBy(deliveryTypeMasterDto.getUpdatedBy());
			deliveryTypeMaster.setUpdatedDate(
					ADTCommonDateUtils.getDate(deliveryTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(deliveryTypeMaster);
			return new Response<StateMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<StateMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveDeliveryTypeList() throws ApplicationException {
		try {
			List<DeliveryTypeMasterDto> deliveryTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_DELIVERY_TYPE_LIST")
					.setResultTransformer(Transformers.aliasToBean(DeliveryTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, deliveryTypeMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDeliveryTypeMasterList(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			List<DeliveryTypeMasterDto> deliveryTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DELIVERY_MASTER_TYPE_LIST")
					.setInteger("orgId", deliveryTypeMasterDto.getOrganizationId())
					.setFirstResult(deliveryTypeMasterDto.getOffset() != null ? deliveryTypeMasterDto.getOffset() : 0)
					.setMaxResults(deliveryTypeMasterDto.getNoOfRecordsPerPage() != null ? deliveryTypeMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(DeliveryTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, deliveryTypeMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@Override
	public Response getCount(DeliveryTypeMasterDto deliveryTypeMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(DeliveryTypeMaster.class); 
			c.add(Restrictions.eq("organizationId", deliveryTypeMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

}
