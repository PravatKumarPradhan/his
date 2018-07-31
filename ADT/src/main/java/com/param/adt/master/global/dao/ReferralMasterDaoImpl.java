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
import com.param.adt.master.global.dto.ReferralMasterDto;
import com.param.adt.master.global.model.ReferralMaster;
import com.param.global.dto.AreaMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Repository
public class ReferralMasterDaoImpl extends GenericDao<ReferralMaster, Integer> implements IReferralMasterDao,ICommonConstants
{

	public ReferralMasterDaoImpl() {
		super(ReferralMaster.class);
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response getReferralByName(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			List<ReferralMasterDto> referralMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFERRAL_MASTER_LIST_BY_NAME")
					.setString("desc", referralMasterDto.getReferralName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(ReferralMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addReferralTypeTypeMaster(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			ReferralMaster referralMaster = mapper.map(referralMasterDto, ReferralMaster.class, "ReferralMasterDto_to_ReferralMaster");
			referralMaster = save(referralMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralMasterList(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			List<ReferralMasterDto> referralMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFERRAL_MASTER_LIST")
					.setFirstResult(referralMasterDto.getOffset() != null ? referralMasterDto.getOffset() : 0)
					.setMaxResults(referralMasterDto.getNoOfRecordsPerPage() != null ? referralMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", referralMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ReferralMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralById(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			List<ReferralMasterDto> referralMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFERRAL_MASTER_LIST_BY_ID")
					.setInteger("referralId", referralMasterDto.getReferralId())
					.setResultTransformer(Transformers.aliasToBean(ReferralMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveReferralList() throws ApplicationException {
		try {
			List<ReferralMasterDto> referralMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_REFERRAL_MASTER_LIST")
					.setResultTransformer(Transformers.aliasToBean(ReferralMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralByNameNotId(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			List<ReferralMasterDto> referralMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFERRAL_MASTER_LIST_BY_NAME_NOT_ID")
					.setInteger("referralId", referralMasterDto.getReferralId())
					.setString("desc",referralMasterDto.getReferralName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(ReferralMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateReferralMaster(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			ReferralMaster referralMaster = findById(referralMasterDto.getReferralId());

			referralMaster.setReferralName(referralMasterDto.getReferralName());
			referralMaster.setReferralCode(referralMasterDto.getReferralCode());
			referralMaster.setAreaId(referralMasterDto.getAreaId());
			referralMaster.setStateId(referralMasterDto.getStateId());
			referralMaster.setAreaId(referralMasterDto.getAreaId());
			referralMaster.setCityId(referralMasterDto.getCityId());
			referralMaster.setCountryId(referralMasterDto.getCountryId());
			referralMaster.setDistrictId(referralMasterDto.getDistrictId());
			referralMaster.setReferralAddress(referralMasterDto.getReferralAddress());
			referralMaster.setReferralContact(referralMasterDto.getReferralContact());
			referralMaster.setReferralContactPerson(referralMasterDto.getReferralContactPerson());
			referralMaster.setReferralEmail(referralMasterDto.getReferralEmail());
			referralMaster.setUpdatedBy(referralMasterDto.getUpdatedBy());
			update(referralMaster);
			return new Response<ReferralMaster, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ReferralMaster, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ReferralMaster, Integer>(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response updateReferralMasterStatus(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			ReferralMaster referralMaster = findById(referralMasterDto.getReferralId());
			referralMaster.setUpdatedBy(referralMasterDto.getUpdatedBy());
			referralMaster.setUpdatedDate(ADTCommonDateUtils.getDate(referralMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			referralMaster.setStatus(referralMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			update(referralMaster);
			return new Response<ReferralMaster, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ReferralMaster, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ReferralMaster, Integer>(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response getAreaByCityId(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			List<AreaMasterDto> areaMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_AREA_BY_CITY_ID")
					.setInteger("cityId", areaMasterDto.getCityId())
					.setResultTransformer(Transformers.aliasToBean(AreaMasterDto.class)).list();
			return new Response(SUCCESS, null, null, areaMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}
	
	public Response getCount(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(ReferralMaster.class); 
			c.add(Restrictions.eq("organizationId", referralMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getReferralListByReferralTypeId(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			List<ReferralMasterDto> referralMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REFERRAL_LIST_BY_REFERRAL_TYPE_ID")
					.setInteger("referralTypeId", referralMasterDto.getReferralTypeId())
					.setInteger("organizationId", referralMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ReferralMasterDto.class)).list();
			return new Response(SUCCESS, null, null, referralMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

}
