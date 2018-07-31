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
import com.param.adt.master.global.dto.ICUTypeMasterDto;
import com.param.adt.master.global.model.ICUTypeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ICUTypeMasterDaoImpl extends GenericDao<ICUTypeMaster, Integer>
		implements IICUTypeMasterDao, ICommonConstants {
	public ICUTypeMasterDaoImpl() {
		super(ICUTypeMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response addICUTypeMaster(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			ICUTypeMaster icuTypeMaster = mapper.map(icuTypeMasterDto, ICUTypeMaster.class,
					"ICUTypeMasterDto_to_ICUTypeMasterMaster");
			icuTypeMaster = save(icuTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_SUCCESS, null, null);

	}

	@Override
	public Response getICUByName(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			List<ICUTypeMasterDto> ICUTypeList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ICU_LIST_BY_NAME")
					.setString("iCUName", icuTypeMasterDto.getICUType().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(ICUTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, ICUTypeList, null);

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
	public Response getICUMasterList(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			List<ICUTypeMasterDto> icuTypeList = sessionFactory.getCurrentSession().getNamedQuery("GET_ICU_LIST")
					.setInteger("orgId", icuTypeMasterDto.getOrganizationId())
					.setFirstResult(icuTypeMasterDto.getOffset() != null ? icuTypeMasterDto.getOffset() : 0)
					.setMaxResults(icuTypeMasterDto.getNoOfRecordsPerPage() != null ? icuTypeMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(ICUTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, icuTypeList, null);

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
	public Response getICUByID(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			List<ICUTypeMasterDto> ICUTypeList = sessionFactory.getCurrentSession().getNamedQuery("GET_ICU_LIST_BY_ID")
					.setInteger("iCUID", icuTypeMasterDto.getICUTypeMasterId())
					.setResultTransformer(Transformers.aliasToBean(ICUTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, ICUTypeList, null);

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
	public Response getICUByNameNotId(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			List<ICUTypeMasterDto> ICUTypeList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ICU_LIST_BY_NAME_NOT_ID")
					.setString("iCUName", icuTypeMasterDto.getICUType().toLowerCase())
					.setInteger("iCUID", icuTypeMasterDto.getICUTypeMasterId())
					.setResultTransformer(Transformers.aliasToBean(ICUTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, ICUTypeList, null);

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
	public Response updateICUTypeMaster(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			// System.out.println(icuTypeMasterDto.+"
			// "+SpecialityMasterDto.getSpecialityName());
			ICUTypeMaster icuTypeMaster = findById(icuTypeMasterDto.getICUTypeMasterId());
			icuTypeMaster.setICUType(icuTypeMasterDto.getICUType());
			icuTypeMaster.setICUtypeCode(icuTypeMasterDto.getICUtypeCode());
			icuTypeMaster.setIsCloseICU(icuTypeMasterDto.getIsCloseICU());
			icuTypeMaster.setIsOpenICU(icuTypeMasterDto.getIsOpenICU());
			icuTypeMaster.setUpdatedBy(icuTypeMasterDto.getUpdatedBy());
			icuTypeMaster
					.setUpdatedDate(ADTCommonDateUtils.getDate(icuTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));

			update(icuTypeMaster);
			return new Response<ICUTypeMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ICUTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ICUTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateICUStatus(ICUTypeMasterDto icuTypeMasterDto) {
		try {
			// System.out.println(icuTypeMasterDto.+"
			// "+SpecialityMasterDto.getSpecialityName());
			ICUTypeMaster icuTypeMaster = findById(icuTypeMasterDto.getICUTypeMasterId());
			icuTypeMaster.setUpdatedBy(icuTypeMasterDto.getUpdatedBy());
			icuTypeMaster
					.setUpdatedDate(ADTCommonDateUtils.getDate(icuTypeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			icuTypeMaster.setStatus(icuTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			update(icuTypeMaster);
			return new Response<ICUTypeMasterDto, Integer>(SUCCESS, null, STATUS_CHANGED, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ICUTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ICUTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getCount(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(ICUTypeMaster.class);
			c.add(Restrictions.eq("organizationId", icuTypeMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

}
