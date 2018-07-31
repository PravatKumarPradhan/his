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
import com.param.global.dto.OPConsultationConfigurationMasterDto;
import com.param.global.model.OPConsultationConfigurationMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"unchecked","rawtypes"})
public class OPConsultationConfigurationMasterDaoImpl extends GenericDao<OPConsultationConfigurationMaster, Integer> implements IOPConsultationConfigurationMasterDao,ICommonConstants{

	public OPConsultationConfigurationMasterDaoImpl() {
		super(OPConsultationConfigurationMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Override
	public Response checkOPConsultationExistOrNot(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto) throws ApplicationException {
		try {
			List<OPConsultationConfigurationMasterDto> OPConsultationConfigurationMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("CHECK_OP_CONSULTATION_EXIST_OR_NOT")
					.setInteger("orgId", opConsultationConfigurationMasterDto.getOrganizationId())
					.setInteger("unitId", opConsultationConfigurationMasterDto.getUnitId())
					.setInteger("specialityId", opConsultationConfigurationMasterDto.getSpecialityId())
					/*.setInteger("followupVisitDays", opConsultationConfigurationMasterDto.getFollowupVisitDays())
					.setInteger("followupVisitCount", opConsultationConfigurationMasterDto.getFollowupVisitCount())
					.setInteger("secondaryVisitDays", opConsultationConfigurationMasterDto.getSecondaryVisitDays())
					.setInteger("secondaryVisitCount", opConsultationConfigurationMasterDto.getSecondaryVisitCount())*/
					.setResultTransformer(Transformers.aliasToBean(OPConsultationConfigurationMasterDto.class)).list();
			
			return new Response(SUCCESS, null, null, OPConsultationConfigurationMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveOPConsultationConfiguration(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto) {
		try {
			OPConsultationConfigurationMaster opConsultationConfigurationMaster = mapper.map(
					opConsultationConfigurationMasterDto, OPConsultationConfigurationMaster.class,
					"OPConsultationConfigurationMasterDto_to_OPConsultationConfigurationMaster");
			save(opConsultationConfigurationMaster);

			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOPConsultationConfigurationList(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto) throws ApplicationException {
		try {
			List<OPConsultationConfigurationMasterDto> OPConsultationConfigurationMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OP_CONSULTATION_LIST")
					.setInteger("orgId", opConsultationConfigurationMasterDto.getOrganizationId())
					.setInteger("unitId", opConsultationConfigurationMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(OPConsultationConfigurationMasterDto.class)).list();
			
			return new Response(SUCCESS, null, null, OPConsultationConfigurationMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOPConsultationConfigurationListById(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto) throws ApplicationException {
		try {
			List<OPConsultationConfigurationMasterDto> OPConsultationConfigurationMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OP_CONSULTATION_CONFIGURATION_LIST_BY_ID")
					.setInteger("opVisitRuleId", opConsultationConfigurationMasterDto.getOpVisitRuleId())
					.setResultTransformer(Transformers.aliasToBean(OPConsultationConfigurationMasterDto.class)).list();
			return new Response(SUCCESS, null, null, OPConsultationConfigurationMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateOPConsultationConfigurationMaster(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto) throws ApplicationException {
		try {
			OPConsultationConfigurationMaster opConsultationConfigurationMaster = findById(opConsultationConfigurationMasterDto.getOpVisitRuleId());
				opConsultationConfigurationMaster.setFollowupVisitCount(opConsultationConfigurationMasterDto.getFollowupVisitCount());
				opConsultationConfigurationMaster.setFollowupVisitDays(opConsultationConfigurationMasterDto.getFollowupVisitDays());
				opConsultationConfigurationMaster.setSecondaryVisitDays(opConsultationConfigurationMasterDto.getSecondaryVisitDays());
				opConsultationConfigurationMaster.setSecondaryVisitCount(opConsultationConfigurationMasterDto.getSecondaryVisitCount());
				opConsultationConfigurationMaster.setUpdatedBy(opConsultationConfigurationMasterDto.getUpdatedBy());
				opConsultationConfigurationMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(opConsultationConfigurationMasterDto.getUpdatedDate(),"dd-mm-yyyy HH:mm:ss"));
			update(opConsultationConfigurationMaster);
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOPConsultationConfigurationBySpecialityId(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto) throws ApplicationException {
		try {
			OPConsultationConfigurationMasterDto OPConsultationConfigurationMasterDtosList = (OPConsultationConfigurationMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_OP_CONSULTATION_LIST_BY_SPECILAITY_ID")
					.setInteger("orgId", opConsultationConfigurationMasterDto.getOrganizationId())
					.setInteger("unitId", opConsultationConfigurationMasterDto.getUnitId())
					.setInteger("specialityId",opConsultationConfigurationMasterDto.getSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(OPConsultationConfigurationMasterDto.class)).uniqueResult();
			return new Response(SUCCESS, null, null, null, OPConsultationConfigurationMasterDtosList);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}
	
	
	

}
