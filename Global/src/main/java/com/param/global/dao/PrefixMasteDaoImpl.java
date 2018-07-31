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
import com.param.global.dto.PrefixMasterDto;
import com.param.global.model.PrefixMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PrefixMasteDaoImpl extends GenericDao<PrefixMaster, Integer>
		implements IPrefixMasterDao, ICommonConstants {

	public PrefixMasteDaoImpl() {
		super(PrefixMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getPrefixByName(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			List<PrefixMasterDto> prefixMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PREFIX_LIST_BY_NAME")
					.setString("prefixName", prefixMasterDto.getPrefixDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(PrefixMasterDto.class)).list();
			return new Response(SUCCESS, null, null, prefixMasterDtoList, null);

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
	public Response addPrefixTypeMaster(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			PrefixMaster prefixMaster = mapper.map(prefixMasterDto, PrefixMaster.class, "PrefixMasterDto_to_PrefixMaster");
			prefixMaster = save(prefixMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPrefixByNameNotId(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			List<PrefixMasterDto> prefixMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PREFIX_LIST_BY_NAME_NOT_ID")
					.setInteger("prefixId", prefixMasterDto.getPrefixId())
					.setString("prefixName", prefixMasterDto.getPrefixDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(PrefixMasterDto.class)).list();
			return new Response(SUCCESS, null, null, prefixMasterDtoList, null);

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
	public Response updatePrefixMaster(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			PrefixMaster prefixMaster = findById(prefixMasterDto.getPrefixId());
			prefixMaster.setPrefixCode(prefixMasterDto.getPrefixCode());
			prefixMaster.setPrefixDesc(prefixMasterDto.getPrefixDesc());
			prefixMaster.setUpdatedBy(prefixMasterDto.getUpdatedBy());
			prefixMaster.setId(prefixMasterDto.getId());
			prefixMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(prefixMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			update(prefixMaster);
			return new Response<PrefixMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<PrefixMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<PrefixMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getPrefixByID(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			List<PrefixMasterDto> prefixMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PREFIX_LIST_BY_ID").setInteger("prefixId", prefixMasterDto.getPrefixId())
					.setResultTransformer(Transformers.aliasToBean(PrefixMasterDto.class)).list();
			return new Response(SUCCESS, null, null, prefixMasterDtoList, null);

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
	public Response updatePrefixStatus(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			PrefixMaster prefixMaster = findById(prefixMasterDto.getPrefixId());
			
			prefixMaster.setUpdatedBy(prefixMasterDto.getUpdatedBy());
			prefixMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(prefixMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			prefixMaster.setStatus(prefixMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			update(prefixMaster);
			return new Response<PrefixMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<PrefixMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<PrefixMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActivePrefixList() throws ApplicationException {
		try {
			List<PrefixMasterDto> prefixMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_PREFIX_LIST")
					.setResultTransformer(Transformers.aliasToBean(PrefixMasterDto.class)).list();
			return new Response(SUCCESS, null, null, prefixMasterDtoList, null);

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
	public Response getPrefixMasterList(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			List<PrefixMasterDto> prefixMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PREFIX_LIST")
					.setFirstResult(prefixMasterDto.getOffset() != null ? prefixMasterDto.getOffset() : 0)
					.setMaxResults(prefixMasterDto.getNoOfRecordsPerPage() != null ? prefixMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", prefixMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(PrefixMasterDto.class)).list();
			return new Response(SUCCESS, null, null, prefixMasterDtoList, null);

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
	public Response getCount(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(PrefixMaster.class); 
			c.add(Restrictions.eq("organizationId", prefixMasterDto.getOrganizationId()));
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
