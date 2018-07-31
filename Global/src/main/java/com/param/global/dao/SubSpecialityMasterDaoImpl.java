package com.param.global.dao;

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

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SubSpecialityMasterDto;
import com.param.global.model.SubSpecialityMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class SubSpecialityMasterDaoImpl extends GenericDao<SubSpecialityMaster, Integer>
		implements ISubSpecialityMasterDao, ICommonConstants {

	public SubSpecialityMasterDaoImpl() {
		super(SubSpecialityMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getSubSpecialityMasterList(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException {
		try {
			List<SubSpecialityMasterDto> subSpecialityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SUB_SPECIALITY_LIST")
					.setInteger("orgId", subSpecialityMasterDto.getOrganizationId())
					.setFirstResult(subSpecialityMasterDto.getOffset() != null ? subSpecialityMasterDto.getOffset() : 0)
					.setMaxResults(subSpecialityMasterDto.getNoOfRecordsPerPage() != null ? subSpecialityMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(SubSpecialityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, subSpecialityMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addSubSpecialityMaster(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException {
		try {
			SubSpecialityMaster subSpecialityMaster = mapper.map(subSpecialityMasterDto, SubSpecialityMaster.class,
					"SubSpecialityMasterDto_to_SubSpecialityMaster");
			subSpecialityMaster = save(subSpecialityMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response editSubSpecialityMaster(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateSubSpecialityMaster(SubSpecialityMasterDto subSpecialityMasterDto)
			throws ApplicationException {
		try {
			// System.out.println(SpecialityMasterDto.getspecialityId()+"
			// "+SpecialityMasterDto.getSpecialityName());
			SubSpecialityMaster Speciality = findById(subSpecialityMasterDto.getSubSpecialityMasterId());
			Speciality.setSubSpecialityMasterName(subSpecialityMasterDto.getSubSpecialityMasterName());
			Speciality.setSubSpecialityMasterCode(subSpecialityMasterDto.getSubSpecialityMasterCode());
			Speciality.setSubSpecialityMasterName(subSpecialityMasterDto.getSubSpecialityMasterName());
			Speciality.setSpecialityId(subSpecialityMasterDto.getSpecialityId());
			Speciality.setUpdatedBy(subSpecialityMasterDto.getUpdatedBy());
			Speciality.setUpdatedDate(GlobalCommonDateUtils.getDate(subSpecialityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			Speciality.setIsModality(subSpecialityMasterDto.getIsModality());
			update(Speciality);
			return new Response<SubSpecialityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SubSpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SubSpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response getSubSpecialityByName(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException {
		try {
			List<SubSpecialityMasterDto> listSubSpecialityMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SUB_SPECIALITY_LIST_BY_NAME")
					.setString("subSpecialityName", subSpecialityMasterDto.getSubSpecialityMasterName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(SubSpecialityMasterDto.class)).list();
			if (listSubSpecialityMasterDto != null) {
				return new Response(SUCCESS, null, null, listSubSpecialityMasterDto, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSubActiveSpecialityList() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSubSpecialityById(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException {
		try {
			List<SubSpecialityMasterDto> listSubSpecialityMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SUB_SPECIALITY_LIST_BY_ID")
					.setInteger("subSpecialityId", subSpecialityMasterDto.getSubSpecialityMasterId())
					.setResultTransformer(Transformers.aliasToBean(SubSpecialityMasterDto.class)).list();
			if (listSubSpecialityMasterDto != null) {
				return new Response(SUCCESS, null, null, listSubSpecialityMasterDto, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateSubSpecialityStatus(SubSpecialityMasterDto subSpecialityMasterDto) {
		try {
			SubSpecialityMaster subSpeciality = findById(subSpecialityMasterDto.getSubSpecialityMasterId());

			subSpeciality.setStatus(subSpecialityMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			subSpeciality.setUpdatedBy(subSpecialityMasterDto.getUpdatedBy());
			subSpeciality.setUpdatedDate(
					GlobalCommonDateUtils.getDate(subSpecialityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(subSpeciality);
			return new Response<SubSpecialityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SubSpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SubSpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response getSubSpecialityByNameNotId(SubSpecialityMasterDto subspecialityMasterDto)
			throws ApplicationException {
		try {
			List<SubSpecialityMasterDto> listSubSpecialityMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SUB_SPECIALITY_LIST_BY_NAME_NOT_BY_ID")
					.setString("subSpecialityName", subspecialityMasterDto.getSubSpecialityMasterName().toLowerCase())
					.setInteger("subSpecialityMasterId", subspecialityMasterDto.getSubSpecialityMasterId())
					.setResultTransformer(Transformers.aliasToBean(SubSpecialityMasterDto.class)).list();
			if (listSubSpecialityMasterDto != null) {
				return new Response(SUCCESS, null, null, listSubSpecialityMasterDto, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCount(SubSpecialityMasterDto subspecialityMasterDto) {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(SubSpecialityMaster.class);
			c.add(Restrictions.eq("organizationId", subspecialityMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getSubSpecialityBySpecialityId(SubSpecialityMasterDto subspecialityMasterDto)
			throws ApplicationException {
		try {
				List<SubSpecialityMasterDto> listSubSpecialityMasterDto = sessionFactory.getCurrentSession()
						.getNamedQuery("GET_SUBSPECIALITY_BY_SPECIALITY_ID")
						.setInteger("specialityId", subspecialityMasterDto.getSpecialityId())
						.setResultTransformer(Transformers.aliasToBean(SubSpecialityMasterDto.class)).list();
				return new Response<>(SUCCESS, null, null, listSubSpecialityMasterDto, null);
 		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSubspecialityNotInUnit(int id) throws ApplicationException {
		try {
			List<SubSpecialityMasterDto> listSubSpecialityMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SUBSPECIALITY_BY_SPECIALITY_ID_NOT_MAPPED_TO_UNIT")
					.setInteger("specialityId", id)
					.setResultTransformer(Transformers.aliasToBean(SubSpecialityMasterDto.class)).list();
			return new Response<>(SUCCESS, null, null, listSubSpecialityMasterDto, null);
		}catch (HibernateException he) {
		he.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new Response(ERROR, null, null, null, null);
	}

}
