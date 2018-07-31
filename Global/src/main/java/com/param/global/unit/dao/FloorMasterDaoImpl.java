package com.param.global.unit.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

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

import com.param.adt.master.unit.model.FloorMaster;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.unit.dto.FloorMasterDto;
@SuppressWarnings({"rawtypes","unchecked"})
@Repository
public class FloorMasterDaoImpl extends GenericDao<FloorMaster, Integer> implements IFloorMasterDao,ICommonConstants{

	public FloorMasterDaoImpl() {
		super(FloorMaster.class);
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getFloorByName(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			List<FloorMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_FLOOR_LIST_BY_NAME")
					.setString("floorName", floorMasterDto.getFloorName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(FloorMasterDto.class)).list();
			return new Response(SUCCESS, null, null, nationalityMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addFloorMaster(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			FloorMaster floorMaster = mapper.map(floorMasterDto, FloorMaster.class,
					"FloorMasterDto_to_FloorMaster");
			floorMaster = save(floorMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFloorMasterList(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			List<FloorMasterDto> floorMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_FLOOR_LIST")
					.setInteger("orgId", floorMasterDto.getOrganizationId())
					.setInteger("unitId", floorMasterDto.getUnitId())
					.setFirstResult(floorMasterDto.getOffset() != null ? floorMasterDto.getOffset() : 0)
					.setMaxResults(floorMasterDto.getNoOfRecordsPerPage() != null ? floorMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(FloorMasterDto.class)).list();
			
			return new Response(SUCCESS, null, null, floorMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getFloorByID(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			List<FloorMasterDto> floorMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_FLOOR_LIST_BY_ID").setInteger("floorId", floorMasterDto.getFloorId())
					.setResultTransformer(Transformers.aliasToBean(FloorMasterDto.class)).list();
			return new Response(SUCCESS, null, null, floorMasterDtoList, null);

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
	public Response updateFloorStatus(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {

			FloorMaster floorMaster = findById(floorMasterDto.getFloorId());
			floorMaster.setUpdatedBy(floorMasterDto.getUpdatedBy());
			floorMaster.setStatus(floorMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			floorMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(floorMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(floorMaster);
			return new Response<FloorMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<FloorMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<FloorMasterDto, Integer>(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response updateFloorMaster(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			FloorMaster floorMaster = findById(floorMasterDto.getFloorId());
			floorMaster.setFloorName(floorMasterDto.getFloorName());
			floorMaster.setFloorCode(floorMasterDto.getFloorCode());
			floorMaster.setUpdatedBy(floorMasterDto.getUpdatedBy());
			floorMaster
					.setUpdatedDate(GlobalCommonDateUtils.getDate(floorMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(floorMaster);
			return new Response<FloorMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<FloorMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<FloorMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getFloorByNameNotId(FloorMasterDto floorMasterDto) throws ApplicationException {
		try {
			List<FloorMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_FLOOR_LIST_BY_NAME_NOT_ID")
					.setInteger("floorId", floorMasterDto.getFloorId())
					.setString("floorName", floorMasterDto.getFloorName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(FloorMasterDto.class)).list();
			return new Response(SUCCESS, null, null, nationalityMasterDtoList, null);

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
	public Response getActiveFloorList() throws ApplicationException {
		try {
			List<FloorMasterDto> nationalityMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_FLOOR_LIST")
					.setResultTransformer(Transformers.aliasToBean(FloorMasterDto.class)).list();
			return new Response(SUCCESS, null, null, nationalityMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCount(FloorMasterDto floorMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(FloorMaster.class); 
			c.add(Restrictions.eq("organizationId", floorMasterDto.getOrganizationId()));
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
