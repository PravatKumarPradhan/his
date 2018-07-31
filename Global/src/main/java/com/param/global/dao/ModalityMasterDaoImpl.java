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
import com.param.global.dto.ModalityMasterDto;
import com.param.global.dto.PrefixMasterDto;
import com.param.global.dto.sync.ModalityMasterDtoSync;
import com.param.global.model.ModalityMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ModalityMasterDaoImpl extends GenericDao<ModalityMaster, Integer>
		implements IModalityMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	public ModalityMasterDaoImpl() {
		super(ModalityMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getModalityByName(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			List<ModalityMasterDto> modalityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MODALITY_BY_NAME")
					.setString("modalityDesc", modalityMasterDto.getModalityDesc().toLowerCase())
					.setInteger("orgId", modalityMasterDto.getOrganizationId())
					.setInteger("unitId", modalityMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(ModalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, modalityMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveModalityMaster(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			ModalityMaster modalityMaster = mapper.map(modalityMasterDto, ModalityMaster.class,
					"ModalityMasterDto_to_ModalityMaster");
			modalityMaster = save(modalityMaster);
			return new Response(SUCCESS, null, null, null, modalityMaster);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityMasterList(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			List<ModalityMasterDto> modalityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MODALITY_MASTER_LIST")
					.setInteger("orgId", modalityMasterDto.getOrganizationId())
					.setInteger("unitId", modalityMasterDto.getUnitId())
					.setFirstResult(modalityMasterDto.getOffset() != null ? modalityMasterDto.getOffset() : 0)
					.setMaxResults(modalityMasterDto.getNoOfRecordsPerPage() != null
							? modalityMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(ModalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, modalityMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityByNameNotId(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			List<ModalityMasterDto> modalityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MODALITY_BY_NAME_NOT_ID")
					.setString("modalityDesc", modalityMasterDto.getModalityDesc().toLowerCase())
					.setInteger("modalityId", modalityMasterDto.getModalityId())
					.setInteger("orgId", modalityMasterDto.getOrganizationId())
					.setInteger("unitId", modalityMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(ModalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, modalityMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateModalityMaster(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			ModalityMaster modalityMaster = findById(modalityMasterDto.getModalityId());
			modalityMaster.setSpecialityId(modalityMasterDto.getSpecialityId());
			modalityMaster.setSubSpecialityId(modalityMasterDto.getSubSpecialityId());
			modalityMaster.setModalityCode(modalityMasterDto.getModalityCode());
			modalityMaster.setModalityDesc(modalityMasterDto.getModalityDesc());
			modalityMaster.setUpdatedBy(modalityMasterDto.getUpdatedBy());
			modalityMaster.setEquipmentId(modalityMasterDto.getEquipmentId());
			modalityMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(modalityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			// modalityMaster.setModalityTypeId(modalityMasterDto.getModalityTypeId());
			update(modalityMaster);
			return new Response<ModalityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<ModalityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<ModalityMasterDto, Integer>(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getModalityById(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			List<ModalityMasterDto> modalityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MODALITY_BY_ID").setInteger("modalityId", modalityMasterDto.getModalityId())
					.setInteger("orgId", modalityMasterDto.getOrganizationId())
					.setInteger("unitId", modalityMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(ModalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, modalityMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateModalityStatus(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			ModalityMaster modalityMaster = findById(modalityMasterDto.getModalityId());
			modalityMaster.setUpdatedBy(modalityMasterDto.getUpdatedBy());
			modalityMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(modalityMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			modalityMaster.setStatus(modalityMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			update(modalityMaster);
			return new Response<PrefixMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<PrefixMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<PrefixMasterDto, Integer>(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getActiveModalityList(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			List<ModalityMasterDto> modalityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_MODALITY_LIST")
					.setInteger("orgId", modalityMasterDto.getOrganizationId())
					.setInteger("unitId", modalityMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(ModalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, modalityMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityCount(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			Criteria c = session.createCriteria(ModalityMaster.class);
			c.add(Restrictions.eq("organizationId", modalityMasterDto.getOrganizationId()));
			c.add(Restrictions.eq("unitId", modalityMasterDto.getUnitId()));
			c.setProjection(Projections.rowCount());
			Long count = (Long) c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getModalityBySubSpecialityId(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			List<ModalityMasterDto> modalityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MODALITY_LIST_BY_SUB_SPECILAITY_ID")
					.setInteger("orgId", modalityMasterDto.getOrganizationId())
					.setInteger("unitId", modalityMasterDto.getUnitId())
					.setInteger("subSpecialityId", modalityMasterDto.getSubSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(ModalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, modalityMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityBySubSpecialityArray(ModalityMasterDto modalityMasterDto) throws ApplicationException {
		try {
			List<ModalityMasterDto> modalityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_MODALITY_LIST_BY_SUB_SPECILAITY_ARRAY")
					.setInteger("orgId", modalityMasterDto.getOrganizationId())
					.setInteger("unitId", modalityMasterDto.getUnitId())
					.setParameterList("subSpecialityArray", modalityMasterDto.getSubSpecialityArray())
					.setResultTransformer(Transformers.aliasToBean(ModalityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, modalityMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getModalityByIdForSync(Integer recordId) throws ApplicationException {
		try {

			List<ModalityMasterDtoSync> madalityDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_MODALITYMASTER_FOR_SYNC_BY_ID").setInteger("modalityId", recordId)
					.setResultTransformer(Transformers.aliasToBean(ModalityMasterDtoSync.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, madalityDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
