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
import com.param.adt.master.global.dto.DocumentTypeMasterDto;
import com.param.adt.master.global.model.DocumentTypeMaster;
import com.param.global.dto.HolidayCalenderMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class DocumentTypeMasterDaoImpl extends GenericDao<DocumentTypeMaster, Integer>
		implements IDocumentTypeMasterDao, ICommonConstants {

	public DocumentTypeMasterDaoImpl(Class<DocumentTypeMaster> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public DocumentTypeMasterDaoImpl() {
		super(DocumentTypeMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getDocumentTypeByName(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			List<DocumentTypeMasterDto> documentTypeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCUMENT_TYPE_LIST_BY_NAME")
					.setString("documentName", documentTypeMasterDto.getDocumentTypeName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DocumentTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, documentTypeMasterDtoList, null);

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
	public Response addDocumentTypeTypeMaster(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			DocumentTypeMaster documentTypeMaster = mapper.map(documentTypeMasterDto, DocumentTypeMaster.class,
					"DocumentTypeMasterDto_to_DocumentTypeMaster");
			documentTypeMaster = save(documentTypeMaster);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDocumentTypeMasterList(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			List<DocumentTypeMasterDto> documentTypeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCUMENT_TYPE_LIST")
					.setInteger("orgId", documentTypeMasterDto.getOrganizationId())
					.setFirstResult(documentTypeMasterDto.getOffset() != null ? documentTypeMasterDto.getOffset() : 0)
					.setMaxResults(documentTypeMasterDto.getNoOfRecordsPerPage() != null ? documentTypeMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(DocumentTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, documentTypeMasterDtoList, null);

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
	public Response getDocumentTypeById(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			List<DocumentTypeMasterDto> documentTypeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCUMENT_TYPE_LIST_BY_ID")
					.setInteger("documentId", documentTypeMasterDto.getDocumentTypeId())
					.setResultTransformer(Transformers.aliasToBean(DocumentTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, documentTypeMasterDtoList, null);

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
	public Response getActiveDocumentTypeList() throws ApplicationException {
		try {
			List<DocumentTypeMasterDto> documentTypeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_DOCUMENT_TYPE_LIST")
					.setResultTransformer(Transformers.aliasToBean(DocumentTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, documentTypeMasterDtoList, null);

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
	public Response getDocumentTypeByNameNotId(DocumentTypeMasterDto documentTypeMasterDto)
			throws ApplicationException {
		try {
			List<DocumentTypeMasterDto> documentTypeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCUMENT_TYPE_LIST_BY_NAME_NOT_ID")
					.setInteger("documentId", documentTypeMasterDto.getDocumentTypeId())
					.setString("documentName", documentTypeMasterDto.getDocumentTypeName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DocumentTypeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, documentTypeMasterDtoList, null);

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
	public Response updateHolidayStatus(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			DocumentTypeMaster documentTypeMaster = findById(documentTypeMasterDto.getDocumentTypeId());
			documentTypeMaster.setStatus(documentTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			documentTypeMaster.setUpdatedBy(documentTypeMasterDto.getUpdatedBy());
			documentTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(documentTypeMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			update(documentTypeMaster);
			return new Response<HolidayCalenderMasterDto, Integer>(SUCCESS, null, STATUS_CHANGED, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<HolidayCalenderMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<HolidayCalenderMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateDocumentTypeMaster(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			DocumentTypeMaster documentTypeMaster = findById(documentTypeMasterDto.getDocumentTypeId());
			
			documentTypeMaster.setDocumentTypeName(documentTypeMasterDto.getDocumentTypeName());
			documentTypeMaster.setDocumentTypeCode(documentTypeMasterDto.getDocumentTypeCode());
			documentTypeMaster.setUpdatedBy(documentTypeMasterDto.getUpdatedBy());
			documentTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(documentTypeMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			update(documentTypeMaster);
			return new Response<HolidayCalenderMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<HolidayCalenderMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<HolidayCalenderMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	@Override
	public Response getCount(DocumentTypeMasterDto documentTypeMasterDto) {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(DocumentTypeMaster.class);
			c.add(Restrictions.eq("organizationId", documentTypeMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

}
