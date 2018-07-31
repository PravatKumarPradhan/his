package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SurgeryMasterDto;
import com.param.global.model.SurgeryMaster;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SurgeryMasterDaoImpl extends GenericDao<SurgeryMaster, Integer>
		implements ISurgeryMasterDao, ICommonConstants {

	public SurgeryMasterDaoImpl() {
		super(SurgeryMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getListOfSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {

			List<SurgeryMasterDto> DiagnosisMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("SURGERY_MASTER_AUTO_FILL_SEARCH")
					.setParameter("surgeryName", "%" + surgeryMasterDto.getSurgeryName().toLowerCase() + "%")
					.setInteger("organizationId", surgeryMasterDto.getOrganizationId())
					.setInteger("unitId", surgeryMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(SurgeryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, DiagnosisMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getSurgeryMasterList(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			List<SurgeryMasterDto> surgeryMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_LIST_OF_SURGERY_MASTER")
					.setInteger("organizationId", surgeryMasterDto.getOrganizationId())
					.setInteger("unitId", surgeryMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(SurgeryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, surgeryMasterList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getSurgeryMasterByName(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {

			List<SurgeryMasterDto> surgeryMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SURGERY_MASTER_BY_NAME")
					.setString("surgeryName", surgeryMasterDto.getSurgeryName().toLowerCase())
					.setInteger("orgId", surgeryMasterDto.getOrganizationId())
					.setInteger("unitId", surgeryMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(SurgeryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, surgeryMasterList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			SurgeryMaster surgeryMaster = mapper.map(surgeryMasterDto, SurgeryMaster.class,
					"SurgeryMasterDto_to_SurgeryMaster");
			save(surgeryMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getSurgeryMasterById(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			List<SurgeryMasterDto> surgeryMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SURGERY_MASTER_LIST_BY_ID")
					.setInteger("orgId", surgeryMasterDto.getOrganizationId())
					.setInteger("unitId", surgeryMasterDto.getUnitId())
					.setInteger("surgeryId", surgeryMasterDto.getSurgery_id())
					.setResultTransformer(Transformers.aliasToBean(SurgeryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, surgeryMasterDtosList, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			SurgeryMaster surgeryMaster = findById(surgeryMasterDto.getSurgery_id());
			surgeryMaster.setSurgeryCode(surgeryMasterDto.getSurgeryCode());
			surgeryMaster.setSurgeryCodeCpt(surgeryMasterDto.getSurgeryCodeCpt());
			surgeryMaster.setSpecialityId(surgeryMasterDto.getSpecialityId());
			surgeryMaster.setSurgeryGradeId(surgeryMasterDto.getSurgeryGradeId());
			surgeryMaster.setSurgeryName(surgeryMasterDto.getSurgeryName());
			surgeryMaster.setUpdatedBy(surgeryMasterDto.getUpdatedBy());
			surgeryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(surgeryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(surgeryMaster);
			return new Response<SurgeryMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SurgeryMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SurgeryMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateStatusForSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			SurgeryMaster surgeryMaster = findById(surgeryMasterDto.getSurgery_id());

			surgeryMaster.setStatus(surgeryMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			surgeryMaster.setUpdatedBy(surgeryMasterDto.getUpdatedBy());
			surgeryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(surgeryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(surgeryMaster);
			return new Response<SurgeryMasterDto, Integer>(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SurgeryMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SurgeryMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

}
