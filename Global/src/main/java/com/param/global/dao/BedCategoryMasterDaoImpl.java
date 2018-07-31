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
import com.param.global.dto.BedCategoryMasterDto;
import com.param.global.model.BedCategoryMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BedCategoryMasterDaoImpl extends GenericDao<BedCategoryMaster, Integer>
		implements IBedCategoryMasterDao, ICommonConstants {
	public BedCategoryMasterDaoImpl() {
		super(BedCategoryMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getBedCategoryByName(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			List<BedCategoryMasterDto> bedCategoryMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_CATEGORY_LIST_BY_NAME")
					.setString("bedCategoryDesc", bedCategoryMasterDto.getBedCategoryDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(BedCategoryMasterDto.class)).list();
			if (bedCategoryMasterDtosList != null) {
				return new Response(SUCCESS, null, null, bedCategoryMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addBedCategoryTypeMaster(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {

			/*
			 * XBedCategoryMaster xBedCategoryMaster = new XBedCategoryMaster();
			 * 
			 * xBedCategoryMaster.setBedCategoryCode(bedCategoryMasterDto.
			 * getBedCategoryCode());
			 * xBedCategoryMaster.setBedCategoryDesc(bedCategoryMasterDto.
			 * getBedCategoryDesc());
			 * xBedCategoryMaster.setHierarchyId(bedCategoryMasterDto.
			 * getHierarchyId());
			 * xBedCategoryMaster.setIsBedRetention(bedCategoryMasterDto.
			 * getIsBedRetention());
			 * xBedCategoryMaster.setOccupancyUnitId(bedCategoryMasterDto.
			 * getOccupancyUnitId());
			 * xBedCategoryMaster.setOrganizationId(bedCategoryMasterDto.
			 * getOrganizationId());
			 * xBedCategoryMaster.setCreatedBy(bedCategoryMasterDto.getCreatedBy
			 * ());
			 * xBedCategoryMaster.setCreatedDate(ADTCommonDateUtils.getDate(
			 * bedCategoryMasterDto.getCreatedDate(),"dd-M-yyy HH:mm:ss"));
			 * xBedCategoryMaster.setUpdatedBy(bedCategoryMasterDto.getUpdatedBy
			 * ());
			 * xBedCategoryMaster.setStatus(bedCategoryMasterDto.getStatus());
			 * xBedCategoryMaster.setUpdatedDate(ADTCommonDateUtils.getDate(
			 * bedCategoryMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			 * 
			 * 
			 * session.save(xBedCategoryMaster); tr.commit(); session.close();
			 */
			//Session session = sessionFactory.openSession();
			//Transaction tr = session.beginTransaction();
			BedCategoryMaster xBedCategoryMaster = mapper.map(bedCategoryMasterDto, BedCategoryMaster.class,
					"BedCategoryMasterDto_to_BedCategoryMaster");
			xBedCategoryMaster.setHierarchyId(bedCategoryMasterDto.getHierarchyId());
			
			save(xBedCategoryMaster);

			
			/*Query q = (Query) session.createSQLQuery("update x_masters.x_bed_category_master xbc set xbc.occupancy_unit_id="
					+ bedCategoryMasterDto.getOccupancyUnitDesc() + ", xbc.hierarchy_id="
					+ bedCategoryMasterDto.getHierarchyId() + " WHERE xbc.bed_category_master_id="
					+ xBedCategoryMaster.getBedCategoryId());
			q.executeUpdate();
			//tr.commit();
*/			
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedCategoryMasterList(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			List<BedCategoryMasterDto> bedCategoryMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_CATEGORY_LIST")
					.setFirstResult(bedCategoryMasterDto.getOffset() != null ? bedCategoryMasterDto.getOffset() : 0)
					.setMaxResults(bedCategoryMasterDto.getNoOfRecordsPerPage() != null
							? bedCategoryMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", bedCategoryMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(BedCategoryMasterDto.class)).list();
			if (bedCategoryMasterDtosList != null) {
				return new Response(SUCCESS, null, null, bedCategoryMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedCategoryById(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			List<BedCategoryMasterDto> bedCategoryMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_CATEGORY_LIST_BY_ID")
					.setInteger("bedCategoryId", bedCategoryMasterDto.getBedCategoryId())
					.setResultTransformer(Transformers.aliasToBean(BedCategoryMasterDto.class)).list();
			if (bedCategoryMasterDtosList != null) {
				return new Response(SUCCESS, null, null, bedCategoryMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateBedCategoryStatus(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			BedCategoryMaster bedCategoryMaster = findById(bedCategoryMasterDto.getBedCategoryId());

			bedCategoryMaster.setStatus(bedCategoryMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			bedCategoryMaster.setUpdatedBy(bedCategoryMasterDto.getUpdatedBy());
			bedCategoryMaster.setUpdatedDate(
			GlobalCommonDateUtils.getDate(bedCategoryMasterDto.getUpdatedDate(), "dd-M-yyy HH:mm:ss"));
			update(bedCategoryMaster);
			return new Response<BedCategoryMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<BedCategoryMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<BedCategoryMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getBedCategoryByNameNotId(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			List<BedCategoryMasterDto> bedCategoryMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_CATEGORY_LIST_BY_NAME_NOT_ID")
					.setInteger("bedCategoryId", bedCategoryMasterDto.getBedCategoryId())
					.setString("bedCategoryDesc", bedCategoryMasterDto.getBedCategoryDesc())
					.setResultTransformer(Transformers.aliasToBean(BedCategoryMasterDto.class)).list();
			if (bedCategoryMasterDtosList != null) {
				return new Response(SUCCESS, null, null, bedCategoryMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateBedCategoryMaster(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			BedCategoryMaster bedCategoryMaster = findById(bedCategoryMasterDto.getBedCategoryId());
			bedCategoryMaster.setBedCategoryCode(bedCategoryMasterDto.getBedCategoryCode());
			bedCategoryMaster.setBedCategoryDesc(bedCategoryMasterDto.getBedCategoryDesc());
			bedCategoryMaster.setHierarchyId(bedCategoryMasterDto.getHierarchyId());
			bedCategoryMaster.setIsBedRetention(bedCategoryMasterDto.getIsBedRetention());
			bedCategoryMaster.setOccupancyUnitId(bedCategoryMasterDto.getOccupancyUnitId());
			// bedCategoryMaster.setOrganizationId(bedCategoryMasterDto.getOrganizationId());

			bedCategoryMaster.setUpdatedBy(bedCategoryMasterDto.getUpdatedBy());
			bedCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(bedCategoryMasterDto.getUpdatedDate(), "dd-M-yyy HH:mm:ss"));
			update(bedCategoryMaster);
			return new Response<BedCategoryMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<BedCategoryMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<BedCategoryMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveBedCategoryList() throws ApplicationException {
		try {
			List<BedCategoryMasterDto> bedCategoryMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_BED_CATEGORY_LIST")
					.setResultTransformer(Transformers.aliasToBean(BedCategoryMasterDto.class)).list();
			if (bedCategoryMasterDtosList != null) {
				return new Response(SUCCESS, null, null, bedCategoryMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	public Response getCount(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(BedCategoryMaster.class);
			c.add(Restrictions.eq("organizationId", bedCategoryMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

}
