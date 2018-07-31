package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SurgeryGradeMasterDto;
import com.param.global.model.SurgeryGradeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class SurgeryGradeMasterDaoImpl extends GenericDao<SurgeryGradeMaster, Integer> implements ISurgeryGradeMasterDao,ICommonConstants {

	public SurgeryGradeMasterDaoImpl() {
		super(SurgeryGradeMaster.class);
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Response getSurgeryGradeByName(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException {
		try {
			List<SurgeryGradeMasterDto> surgeryGradeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SURGERY_GRADE_LIST_BY_NAME")
					.setString("surgeryGrade", surgeryGradeMasterDto.getSurgeryGrade().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(SurgeryGradeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, surgeryGradeMasterDtoList, null);

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
	public Response addSurgeryGradeMaster(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException {

		try {
			SurgeryGradeMaster surgeryMasterMaster = mapper.map(surgeryGradeMasterDto, SurgeryGradeMaster.class,
					"SurgeryGradeMasterDto_to_SurgeryGradeMaster");
			surgeryMasterMaster = save(surgeryMasterMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
		
	}

	@Override
	public Response getSurgeryGradeMasterList(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException {
		try {
			List<SurgeryGradeMasterDto> surgeryGradeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SURGERY_GRADE_LIST")
					.setInteger("orgId", surgeryGradeMasterDto.getOrgnisationId())
					.setResultTransformer(Transformers.aliasToBean(SurgeryGradeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, surgeryGradeMasterDtoList, null);

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
	public Response getSurgeryGradeByNameNotId(SurgeryGradeMasterDto surgeryGradeMasterDto)
			throws ApplicationException {
		try {
			List<SurgeryGradeMasterDto> surgeryGradeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SURGERY_GRADE_LIST_BY_NAME_NOT_ID")
					.setInteger("surgeryGradeId", surgeryGradeMasterDto.getSurgeryGradeId())
					.setString("surgeryGrade", surgeryGradeMasterDto.getSurgeryGrade().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(SurgeryGradeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, surgeryGradeMasterDtoList, null);

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
	public Response updateSurgeryGradeMaster(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException {
		try {
			SurgeryGradeMaster surgeryGradeMaster = findById(surgeryGradeMasterDto.getSurgeryGradeId());
			surgeryGradeMaster.setSurgeryGrade(surgeryGradeMasterDto.getSurgeryGrade());
			surgeryGradeMaster.setSurgeryGradeCode(surgeryGradeMasterDto.getSurgeryGradeCode());
			surgeryGradeMaster.setUpdatedBy(surgeryGradeMasterDto.getUpdatedBy());
			surgeryGradeMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(surgeryGradeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(surgeryGradeMaster);
			return new Response<SurgeryGradeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SurgeryGradeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SurgeryGradeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getSurgeryGradeByID(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException {
			
		try {
			List<SurgeryGradeMasterDto> surgeryGradeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SURGERY_GRADE_LIST_BY_ID")
					.setInteger("surgeryGradeId", surgeryGradeMasterDto.getSurgeryGradeId())
					.setResultTransformer(Transformers.aliasToBean(SurgeryGradeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, surgeryGradeMasterDtoList, null);

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
	public Response updateSurgeryGradeStatus(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException {
		try {
			SurgeryGradeMaster surgeryGradeMaster = findById(surgeryGradeMasterDto.getSurgeryGradeId());

			surgeryGradeMaster.setStatus(surgeryGradeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			surgeryGradeMaster.setUpdatedBy(surgeryGradeMasterDto.getUpdatedBy());
			surgeryGradeMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(surgeryGradeMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(surgeryGradeMaster);
			return new Response<SurgeryGradeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SurgeryGradeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SurgeryGradeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}

	}

	@Override
	public Response getActiveSurgeryGradeMasterList() throws ApplicationException {
			
		try {
			List<SurgeryGradeMasterDto> surgeryGradeMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_SURGERY_GRADE_LIST")
					.setResultTransformer(Transformers.aliasToBean(SurgeryGradeMasterDto.class)).list();
			return new Response(SUCCESS, null, null, surgeryGradeMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

}
