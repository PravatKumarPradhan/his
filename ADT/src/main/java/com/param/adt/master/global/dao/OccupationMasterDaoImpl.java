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
import com.param.adt.master.global.dto.OccupationMasterDto;
import com.param.adt.master.global.model.OccupationMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class OccupationMasterDaoImpl  extends GenericDao<OccupationMaster, Integer> implements IOccupationMasterDao,ICommonConstants{

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public OccupationMasterDaoImpl() {
		super(OccupationMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getOccupationMasterList(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {
			List<OccupationMasterDto> occupationStatusMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OCCUPATION_LIST")
					.setInteger("orgId", occupationMasterDto.getOrganizationId())
					.setFirstResult(occupationMasterDto.getOffset() != null ? occupationMasterDto.getOffset() : 0)
					.setMaxResults(occupationMasterDto.getNoOfRecordsPerPage() != null ? occupationMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(OccupationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, occupationStatusMasterDtoList, null);

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
	public Response getOccupationByName(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try
		{
			List<OccupationMasterDto> occupationMasterDtoList = sessionFactory.getCurrentSession().getNamedQuery("GET_OCCUPATION_BY_NAME").setString("desc", occupationMasterDto.getDesc().toLowerCase()).setResultTransformer(Transformers.aliasToBean(OccupationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, occupationMasterDtoList,null);
 			
		}catch(HibernateException he){
			he.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			 ;
		}
		return new Response(ERROR, null, null, null,null);
	}

	@Override
	public Response addOccupationMaster(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try{
			OccupationMaster occupationMaster = mapper.map(occupationMasterDto, OccupationMaster.class,"occupationDto_to_occupationMaster");
			occupationMaster = save(occupationMaster);
				return new Response(SUCCESS, null, null, null,null);
			}catch(HibernateException he){
				he.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
	}

	@Override
	public Response getOccupationByID(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {
			List<OccupationMasterDto> occupationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OCCUPATION_BY_ID")
					.setInteger("id", occupationMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(OccupationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, occupationMasterDtoList, null);

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
	public Response getOccupationByNameNotId(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try
		{
			List<OccupationMasterDto> occupationMasterDtoList = sessionFactory.getCurrentSession().getNamedQuery("GET_OCCUPATION_BY_NAME_NOT_ID").setInteger("id", occupationMasterDto.getId()).setString("desc", occupationMasterDto.getDesc().toLowerCase()).setResultTransformer(Transformers.aliasToBean(OccupationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, occupationMasterDtoList,null);
 			
		}catch(HibernateException he){
			he.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			 ;
		}
		return new Response(ERROR, null, null, null,null);
	}

	@Override
	public Response updateOccupationMaster(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {
			OccupationMaster occupationMaster = findById(occupationMasterDto.getId());
			occupationMaster.setDesc(occupationMasterDto.getDesc());
			occupationMaster.setCode(occupationMasterDto.getCode());
			occupationMaster.setUpdatedBy(occupationMasterDto.getUpdatedBy());
			occupationMaster.setUpdatedDate(ADTCommonDateUtils.getDate(occupationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(occupationMaster);
			return new Response<OccupationMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<OccupationMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<OccupationMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateOccupationStatus(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		
		
		try {

			OccupationMaster occupationMaster = findById(occupationMasterDto.getId());
			occupationMaster.setUpdatedBy(occupationMasterDto.getUpdatedBy());
			occupationMaster.setStatus(occupationMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			
			occupationMaster.setUpdatedDate(ADTCommonDateUtils.getDate(occupationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(occupationMaster);
			return new Response<OccupationMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<OccupationMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<OccupationMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveOccupationList() throws ApplicationException {
		try {
			List<OccupationMasterDto> occupationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_OCCUPATION_LIST")
					.setResultTransformer(Transformers.aliasToBean(OccupationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, occupationMasterDtoList, null);

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
	public Response getCount(OccupationMasterDto occupationMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(OccupationMaster.class); 
			c.add(Restrictions.eq("organizationId", occupationMasterDto.getOrganizationId()));
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
