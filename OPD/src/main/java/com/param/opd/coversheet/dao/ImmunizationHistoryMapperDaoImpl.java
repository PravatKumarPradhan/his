package com.param.opd.coversheet.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.ImmunizationHistoryMapperDto;
import com.param.opd.coversheet.model.ImmunizationHistoryMapper;
import com.param.opd.coversheet.service.IImmunizationHistoryMapperService;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ImmunizationHistoryMapperDaoImpl extends GenericDao<ImmunizationHistoryMapper, Integer> implements
		IImmunizationHistoryMapperDao, ICommonConstants {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;
	
	public ImmunizationHistoryMapperDaoImpl() {
		super(ImmunizationHistoryMapper.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IImmunizationHistoryMapperService iImmunizationHistoryMapperService;
	
	@Override
	public Response saveImmunizationHistoryMapper(ImmunizationHistoryMapperDto immunizationHistoryMapperDto)throws ApplicationException {
		try
		{
			for(int i=0;i<immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().size();i++)
			{
				if(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getImmunizationHistoryMapperId() != null && 
						immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getImmunizationHistoryMapperId()>0
						)
				{
					ImmunizationHistoryMapper immunizationHistoryMapper = findById(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getImmunizationHistoryMapperId());
					immunizationHistoryMapper.setIsReviewedFlag(YES);
					iImmunizationHistoryMapperService.updateSaveNurseReviewedFlag(immunizationHistoryMapper);
				}
				
				ImmunizationHistoryMapper immunizationHistoryMapper = new ImmunizationHistoryMapper();
				immunizationHistoryMapper.setDrugId(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getDrugId());
				immunizationHistoryMapper.setIsAdministeredStatus(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getIsAdministeredStatus());
				immunizationHistoryMapper.setImmunizationDate(CommonDateUtils.getDate(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getImmunizationDate(),"dd-MM-yyyy"));
				
				immunizationHistoryMapper.setRoleId(immunizationHistoryMapperDto.getRoleId());
				immunizationHistoryMapper.setIsReviewedFlag(
						immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getIsReviewedFlag() == '\0' ? NO :
							immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getIsReviewedFlag()
						);
				
				immunizationHistoryMapper.setCreatedDate(CommonDateUtils.getDate(immunizationHistoryMapperDto.getCreatedDate(), "dd-MM-yyyy"));
				immunizationHistoryMapper.setEncounterId(immunizationHistoryMapperDto.getEncounterId());
				immunizationHistoryMapper.setCreatedBy(immunizationHistoryMapperDto.getCreatedBy());
				immunizationHistoryMapper.setUpdatedBy(immunizationHistoryMapperDto.getUpdatedBy());
				immunizationHistoryMapper.setUpdatedDate(CommonDateUtils.getDate(immunizationHistoryMapperDto.getUpdatedDate(), "dd-MM-yyyy"));
				immunizationHistoryMapper.setStatus(immunizationHistoryMapperDto.getStatus());
				immunizationHistoryMapper.setOrganizationId(immunizationHistoryMapperDto.getOrganizationId());
				immunizationHistoryMapper.setUnitId(immunizationHistoryMapperDto.getUnitId());
				immunizationHistoryMapper.setPatientId(immunizationHistoryMapperDto.getPatientId());
				
				ImmunizationHistoryMapper historyId=save(immunizationHistoryMapper);
			}
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getListOfImmunizationHistoryMapper(
			ImmunizationHistoryMapperDto immunizationHistoryMapperDto)
			throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<ImmunizationHistoryMapperDto> listImmunizationHistoryMapperDto = statlessSession.getNamedQuery("GET_LIST_IMMUNIZATION_MASTER_HISTORY")
					.setInteger("patientId", immunizationHistoryMapperDto.getPatientId())
					.setInteger("unitId", immunizationHistoryMapperDto.getUnitId())
					.setInteger("organizationId", immunizationHistoryMapperDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ImmunizationHistoryMapperDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listImmunizationHistoryMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getOldImmunizationHistoryMapper(
			ImmunizationHistoryMapperDto immunizationHistoryMapperDto)
			throws ApplicationException {
		try
		{
			List<ImmunizationHistoryMapperDto> listImmunizationHistoryMapperDto;
			/*if(immunizationHistoryMapperDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				 listImmunizationHistoryMapperDto = statlessSession.getNamedQuery("GET_LIST_IMMUNIZATION_MASTER_HISTORY_BY_ROLE_ID")
						.setInteger("patientId", immunizationHistoryMapperDto.getPatientId())
						.setInteger("unitId", immunizationHistoryMapperDto.getUnitId())
						.setInteger("organizationId", immunizationHistoryMapperDto.getOrganizationId())
						.setInteger("encounterId", immunizationHistoryMapperDto.getEncounterId())
						.setInteger("roleId", immunizationHistoryMapperDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(ImmunizationHistoryMapperDto.class)).list();
				statlessSession.close();
			}
			else
			{*/
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				 listImmunizationHistoryMapperDto = statlessSession.getNamedQuery("GET_LIST_IMMUNIZATION_MASTER_HISTORY")
						.setInteger("patientId", immunizationHistoryMapperDto.getPatientId())
						.setInteger("unitId", immunizationHistoryMapperDto.getUnitId())
						.setInteger("organizationId", immunizationHistoryMapperDto.getOrganizationId())
						.setInteger("encounterId", immunizationHistoryMapperDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(ImmunizationHistoryMapperDto.class)).list();
				statlessSession.close();
			/*}
			*/
			return new Response(SUCCESS, SUCCESS_CODE, null, listImmunizationHistoryMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getCurrentImmunizationHistoryMapper(
			ImmunizationHistoryMapperDto immunizationHistoryMapperDto)
			throws ApplicationException {
		try
		{
			List<ImmunizationHistoryMapperDto> listImmunizationHistoryMapperDto;
			if(immunizationHistoryMapperDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				 listImmunizationHistoryMapperDto = statlessSession.getNamedQuery("GET_CURRENT_LIST_IMMUNIZATION_MASTER_HISTORY_BY_ROLE_ID")
						.setInteger("patientId", immunizationHistoryMapperDto.getPatientId())
						.setInteger("unitId", immunizationHistoryMapperDto.getUnitId())
						.setInteger("organizationId", immunizationHistoryMapperDto.getOrganizationId())
						.setInteger("encounterId", immunizationHistoryMapperDto.getEncounterId())
						.setInteger("roleId", immunizationHistoryMapperDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(ImmunizationHistoryMapperDto.class)).list();
				statlessSession.close();
			}
			else
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				 listImmunizationHistoryMapperDto = statlessSession.getNamedQuery("GET_CURRENT_LIST_IMMUNIZATION_MASTER_HISTORY")
						.setInteger("patientId", immunizationHistoryMapperDto.getPatientId())
						.setInteger("unitId", immunizationHistoryMapperDto.getUnitId())
						.setInteger("organizationId", immunizationHistoryMapperDto.getOrganizationId())
						.setInteger("encounterId", immunizationHistoryMapperDto.getEncounterId())
						.setResultTransformer(Transformers.aliasToBean(ImmunizationHistoryMapperDto.class)).list();
				statlessSession.close();
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listImmunizationHistoryMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updateImmunizationHistoryMapper(
			ImmunizationHistoryMapperDto immunizationHistoryMapperDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().size();i++)
			{
				
				ImmunizationHistoryMapper immunizationHistoryMapper = findById(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getImmunizationHistoryMapperId());
					
				if(immunizationHistoryMapperDto.getRoleId() != null 
						&& immunizationHistoryMapperDto.getRoleId() == 2 
						&& immunizationHistoryMapper.getRoleId() != 2 
						&& immunizationHistoryMapper.getIsReviewedFlag() == 'N'
						)
				{
					iImmunizationHistoryMapperService.saveImmunizationHistoryMapper(immunizationHistoryMapperDto);
				}
				
				immunizationHistoryMapper.setDrugId(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getDrugId());
				immunizationHistoryMapper.setIsAdministeredStatus(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getIsAdministeredStatus());
				immunizationHistoryMapper.setImmunizationDate(CommonDateUtils.getDate(immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getImmunizationDate(),"dd-MM-yyyy"));
				
				immunizationHistoryMapper.setRoleId(immunizationHistoryMapperDto.getRoleId());
				immunizationHistoryMapper.setIsReviewedFlag(immunizationHistoryMapperDto.getRoleId() == 2 
						&& immunizationHistoryMapperDto.getRoleId() == 3
						? YES : immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getIsReviewedFlag());
				immunizationHistoryMapper.setCreatedDate(CommonDateUtils.getDate(immunizationHistoryMapperDto.getCreatedDate(), "dd-MM-yyyy"));
				immunizationHistoryMapper.setEncounterId(immunizationHistoryMapperDto.getEncounterId());
				immunizationHistoryMapper.setCreatedBy(immunizationHistoryMapperDto.getCreatedBy());
				immunizationHistoryMapper.setUpdatedBy(immunizationHistoryMapperDto.getUpdatedBy());
				immunizationHistoryMapper.setUpdatedDate(CommonDateUtils.getDate(immunizationHistoryMapperDto.getUpdatedDate(), "dd-MM-yyyy"));
				immunizationHistoryMapper.setStatus(immunizationHistoryMapperDto.getStatus());
				immunizationHistoryMapper.setOrganizationId(immunizationHistoryMapperDto.getOrganizationId());
				immunizationHistoryMapper.setUnitId(immunizationHistoryMapperDto.getUnitId());
				immunizationHistoryMapper.setPatientId(immunizationHistoryMapperDto.getPatientId());
				
				ImmunizationHistoryMapper historyId=save(immunizationHistoryMapper);
			}
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updateSaveNurseReviewedFlag(
			ImmunizationHistoryMapper immunizationHistoryMapper)
			throws ApplicationException {
		try
		{
			save(immunizationHistoryMapper);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response updateImmunizationHistoryForOldData(
			ImmunizationHistoryMapperDto immunizationHistoryMapperDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().size();i++)
			{
				
					Session statlessSession = sessionFactory.getCurrentSession();
					int updateCount= statlessSession.getNamedQuery("UPDATE_OLD_IMMUNIZATION")
							.setInteger("patientId", immunizationHistoryMapperDto.getPatientId())
							.setInteger("drugId", immunizationHistoryMapperDto.getListListImmunizationHistoryMapperDto().get(i).getDrugId())
							.setInteger("roleId", immunizationHistoryMapperDto.getRoleId())
							.setInteger("encounterId", immunizationHistoryMapperDto.getEncounterId())
							.executeUpdate();
				
			}
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
