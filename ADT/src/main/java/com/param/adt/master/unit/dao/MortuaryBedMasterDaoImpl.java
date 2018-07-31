package com.param.adt.master.unit.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.MortuaryBedMasterDto;
import com.param.adt.master.unit.model.MortuaryBedLogStatus;
import com.param.adt.master.unit.model.MortuaryBedMaster;
import com.param.global.unit.dto.FloorMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MortuaryBedMasterDaoImpl extends GenericDao<MortuaryBedMaster, Integer>implements IMortuaryBedMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;
	
	public MortuaryBedMasterDaoImpl() {
		super(MortuaryBedMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response saveMortuaryBeds(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			MortuaryBedMaster mortuaryBedMaster = mapper.map(mortuaryBedMasterDto, MortuaryBedMaster.class,
					"MortuaryBedMasterDto_to_MortuaryBedMaster");
			mortuaryBedMaster=save(mortuaryBedMaster);
			mortuaryBedMasterDto.setMortuaryBedId(mortuaryBedMaster.getMortuaryBedId());
			return new Response(SUCCESS, null, null, null, mortuaryBedMasterDto);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMortuaryBedByName(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			List<MortuaryBedMasterDto> mortuaryBedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MORTURY_BED_LIST_BY_NAME")
					.setInteger("orgId", mortuaryBedMasterDto.getOrganizationId())
					.setInteger("unitId", mortuaryBedMasterDto.getUnitId())
					.setString("mortuaryBedDesc", mortuaryBedMasterDto.getMortuaryBedDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(MortuaryBedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, mortuaryBedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMortuaryBedList(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			List<MortuaryBedMasterDto> mortuaryBedMasterDtosList = sessionFactory.getCurrentSession().getNamedQuery("GET_MORTURY_BED_LIST")
					.setInteger("orgId", mortuaryBedMasterDto.getOrganizationId())
					.setInteger("unitId", mortuaryBedMasterDto.getUnitId())
					.setFirstResult(mortuaryBedMasterDto.getOffset() != null ? mortuaryBedMasterDto.getOffset() : 0)
					.setMaxResults(mortuaryBedMasterDto.getNoOfRecordsPerPage() != null
							? mortuaryBedMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(MortuaryBedMasterDto.class)).list();

			return new Response(SUCCESS, null, null, mortuaryBedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveMortuaryBeds(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			List<MortuaryBedMasterDto> mortuaryBedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_MORTURY_BED_LIST")
					.setInteger("orgId", mortuaryBedMasterDto.getOrganizationId())
					.setInteger("unitId", mortuaryBedMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(MortuaryBedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, mortuaryBedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMortuaryBedCount(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(MortuaryBedMaster.class);
				c.add(Restrictions.eq("organizationId", mortuaryBedMasterDto.getOrganizationId()));
				c.add(Restrictions.eq("unitId", mortuaryBedMasterDto.getUnitId()));
				c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getMortuaryListBedById(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			List<MortuaryBedMasterDto> mortuaryBedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MORTURY_BED_LIST_BY_ID")
					.setInteger("mortuaryBedId", mortuaryBedMasterDto.getMortuaryBedId())
					.setResultTransformer(Transformers.aliasToBean(MortuaryBedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, mortuaryBedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateMortuaryBedStatus(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			MortuaryBedMaster mortuaryBedMaster = findById(mortuaryBedMasterDto.getMortuaryBedId());
				mortuaryBedMaster.setUpdatedBy(mortuaryBedMasterDto.getUpdatedBy());
				mortuaryBedMaster.setStatus(mortuaryBedMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
				mortuaryBedMaster.setUpdatedDate(ADTCommonDateUtils.getDate(mortuaryBedMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(mortuaryBedMaster);
			return new Response<FloorMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<FloorMasterDto, Integer>(ERROR, null, COMMON_UPDATE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<FloorMasterDto, Integer>(ERROR, null, null, null, null);
		}
	}
	
	@Override
	public Response getMortuaryBedByNameNotId(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			List<MortuaryBedMasterDto> mortuaryBedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MORTURY_BED_BY_NAME_NOT_ID")
					.setInteger("mortuaryBedId", mortuaryBedMasterDto.getMortuaryBedId())
					.setString("mortuaryBedDesc", mortuaryBedMasterDto.getMortuaryBedDesc().toLowerCase())
					.setInteger("orgId", mortuaryBedMasterDto.getOrganizationId())
					.setInteger("unitId", mortuaryBedMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(MortuaryBedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, mortuaryBedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateMortuaryBedMaster(MortuaryBedMasterDto mortuaryBedMasterDto) {
		try {
			MortuaryBedMaster mortuaryBedMaster = findById(mortuaryBedMasterDto.getMortuaryBedId());
				mortuaryBedMaster.setMortuaryBedCode(mortuaryBedMasterDto.getMortuaryBedCode());
				mortuaryBedMaster.setMortuaryBedDesc(mortuaryBedMasterDto.getMortuaryBedDesc());
				mortuaryBedMaster.setUpdatedBy(mortuaryBedMasterDto.getUpdatedBy());
				mortuaryBedMaster.setUpdatedDate(ADTCommonDateUtils.getDate(mortuaryBedMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(mortuaryBedMaster);
			return new Response<FloorMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<FloorMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<FloorMasterDto, Integer>(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getMorturyBedListByStatusId(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			List<MortuaryBedMasterDto> mortuaryBedMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_MORTURY_BED_LIST_BY_STATUS_ID")
					.setInteger("orgId", mortuaryBedMasterDto.getOrganizationId())
					.setInteger("unitId", mortuaryBedMasterDto.getUnitId())
					//.setInteger("bedStatusId", mortuaryBedMasterDto.getBedStatusId())
					.setResultTransformer(Transformers.aliasToBean(MortuaryBedMasterDto.class)).list();
			return new Response(SUCCESS, null, null, mortuaryBedMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveMortuaryBedLogStatus(MortuaryBedMasterDto mortuaryBedMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Transaction tr =session.beginTransaction();
			MortuaryBedLogStatus mortuaryBedLogStatus = new MortuaryBedLogStatus();
				mortuaryBedLogStatus.setOrganizationId(mortuaryBedMasterDto.getOrganizationId());
				mortuaryBedLogStatus.setUnitId(mortuaryBedMasterDto.getUnitId());
				mortuaryBedLogStatus.setMortuaryBedId(mortuaryBedMasterDto.getMortuaryBedId());
				mortuaryBedLogStatus.setBedStatusId(mortuaryBedMasterDto.getBedStatusId());
				mortuaryBedLogStatus.setCreatedBy(mortuaryBedMasterDto.getCreatedBy());
				mortuaryBedLogStatus.setCreatedDate(ADTCommonDateUtils.getDate(mortuaryBedMasterDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
				mortuaryBedLogStatus.setUpdatedBy(mortuaryBedMasterDto.getCreatedBy());
				mortuaryBedLogStatus.setUpdatedDate(ADTCommonDateUtils.getDate(mortuaryBedMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				mortuaryBedLogStatus.setStatus('A');
			session.save(mortuaryBedLogStatus);
			tr.commit();
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
