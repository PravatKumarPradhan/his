package com.param.opd.coversheet.dao;

import java.util.List;






import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.EncounterAssignmenMapperDto;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.model.EncounterDetails;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EncounterAssignmenMapperDaoImpl extends GenericDao<EncounterDetails, Integer> implements IEncounterAssignmenMapperDao,ICommonConstants {

	
	public EncounterAssignmenMapperDaoImpl() {
		super(EncounterDetails.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response saveEncounterAssignment(EncounterAssignmenMapperDto encounterAssignmenMapperDto)throws ApplicationException {
		try
		{
			Session s= sessionFactory.getCurrentSession();
			EncounterDetails encounterDetailsMapp = (EncounterDetails) s.getNamedQuery("GET_ENCOUNTER_LIST_BY_ENCOUNTER_ID")
					.setInteger("encounterId", encounterAssignmenMapperDto.getEncounterId())
					.setResultTransformer(Transformers.aliasToBean(EncounterDetails.class)).uniqueResult();
			
			
			if(encounterDetailsMapp != null && encounterAssignmenMapperDto.getEncounterId().equals(encounterDetailsMapp.getEncounterId()) )
			{
				EncounterDetails encounter= findById(encounterDetailsMapp.getEncounterDetailsMapperId());
				encounter.setIsAssignmentCompStatus(encounterAssignmenMapperDto.getIsAssignmentCompStatus());
				encounter.setAssignmentStartBy(
						encounterAssignmenMapperDto.getAssignmentStartBy() == null ? encounter.getAssignmentStartBy():
						encounterAssignmenMapperDto.getAssignmentStartBy());
				encounter.setAssignmentStartRoleId(
						encounterAssignmenMapperDto.getAssignmentStartRoleId() == null ? encounter.getAssignmentStartRoleId() :
						encounterAssignmenMapperDto.getAssignmentStartRoleId());
				encounter.setAssignmentStartTime(
						encounterAssignmenMapperDto.getAssignmentStartTime().isEmpty() ||
						encounterAssignmenMapperDto.getAssignmentStartTime() == null
						? encounter.getAssignmentStartTime() :CommonDateUtils.getDate(encounterAssignmenMapperDto.getAssignmentStartTime(), "dd-MM-yyyy hh:mm:ss"));
				encounter.setAssignmentStopBy(
						encounterAssignmenMapperDto.getAssignmentStopBy() == null ? encounter.getAssignmentStopBy() :
						encounterAssignmenMapperDto.getAssignmentStopBy());
				encounter.setAssignmentStopRoleId(encounterAssignmenMapperDto.getAssignmentStopRoleId() == null ? encounter.getAssignmentStopRoleId() : encounterAssignmenMapperDto.getAssignmentStopRoleId() );
				encounter.setAssignmentStopTime(
						encounterAssignmenMapperDto.getAssignmentStopTime().isEmpty() ||
						encounterAssignmenMapperDto.getAssignmentStopTime() == null ? encounter.getAssignmentStopTime() :
						CommonDateUtils.getDate(encounterAssignmenMapperDto.getAssignmentStopTime(), "dd-MM-yyyy hh:mm:ss"));
				encounter.setConsultationStartBy(encounterAssignmenMapperDto.getConsultationStartBy() == null ? encounter.getConsultationStartBy() : encounterAssignmenMapperDto.getConsultationStartBy() );
				encounter.setConsultationStartRoleId(encounterAssignmenMapperDto.getConsultationStartRoleId() == null  ? 
						encounter.getConsultationStartRoleId() : encounterAssignmenMapperDto.getConsultationStartRoleId());
				encounter.setConsultationStartTime(
						encounterAssignmenMapperDto.getConsultationStartTime().isEmpty() ||
						encounterAssignmenMapperDto.getConsultationStartTime() == null ? encounter.getConsultationStartTime() :
						CommonDateUtils.getDate(encounterAssignmenMapperDto.getConsultationStartTime(), "dd-MM-yyyy hh:mm:ss"));
				encounter.setConsultationStopBy(encounterAssignmenMapperDto.getConsultationStopBy() == null 
						? encounter.getConsultationStopBy() : encounterAssignmenMapperDto.getConsultationStopBy());
				encounter.setConsultationStopRoleId(encounterAssignmenMapperDto.getConsultationStopRoleId() == null 
						? encounter.getConsultationStopRoleId() : encounterAssignmenMapperDto.getConsultationStopRoleId());
				encounter.setConsultationStopTime(
						encounterAssignmenMapperDto.getConsultationStopTime().isEmpty() ||
						encounterAssignmenMapperDto.getConsultationStopTime() == null ? encounter.getConsultationStopTime() :
						CommonDateUtils.getDate(encounterAssignmenMapperDto.getConsultationStopTime(), "dd-MM-yyyy hh:mm:ss"));
				encounter.setEncounterId(encounterAssignmenMapperDto.getEncounterId());
				encounter.setPatientId(encounterAssignmenMapperDto.getPatientId());
				encounter.setUnitId(encounterAssignmenMapperDto.getUnitId());
				encounter.setOrganizationId(encounterAssignmenMapperDto.getOrganizationId());
				encounter.setIsConsultationStatus(encounterAssignmenMapperDto.getIsConsultationStatus());
				EncounterDetails enc = save(encounter);
				
			}else
			{
				EncounterDetails encounter = new EncounterDetails();
				encounter.setIsAssignmentCompStatus(encounterAssignmenMapperDto.getIsAssignmentCompStatus());
				encounter.setAssignmentStartBy(
						encounterAssignmenMapperDto.getAssignmentStartBy() == null ? null:
						encounterAssignmenMapperDto.getAssignmentStartBy());
				encounter.setAssignmentStartRoleId(
						encounterAssignmenMapperDto.getAssignmentStartRoleId() == null ? null :
						encounterAssignmenMapperDto.getAssignmentStartRoleId());
				encounter.setAssignmentStartTime(
						encounterAssignmenMapperDto.getAssignmentStartTime().isEmpty() ||
						encounterAssignmenMapperDto.getAssignmentStartTime() == null
						? null :CommonDateUtils.getDate(encounterAssignmenMapperDto.getAssignmentStartTime(), "dd-MM-yyyy hh:mm:ss"));
				encounter.setAssignmentStopBy(
						encounterAssignmenMapperDto.getAssignmentStopBy() == null ? null :
						encounterAssignmenMapperDto.getAssignmentStopBy());
				encounter.setAssignmentStopRoleId(encounterAssignmenMapperDto.getAssignmentStopRoleId() == null ? null : encounterAssignmenMapperDto.getAssignmentStopRoleId() );
				encounter.setAssignmentStopTime(
						encounterAssignmenMapperDto.getAssignmentStopTime().isEmpty() ||
						encounterAssignmenMapperDto.getAssignmentStopTime() == null ? null :
						CommonDateUtils.getDate(encounterAssignmenMapperDto.getAssignmentStopTime(), "dd-MM-yyyy hh:mm:ss"));
				encounter.setConsultationStartBy(encounterAssignmenMapperDto.getConsultationStartBy() == null ? null : encounterAssignmenMapperDto.getConsultationStartBy() );
				encounter.setConsultationStartRoleId(encounterAssignmenMapperDto.getConsultationStartRoleId() == null  ? null : encounterAssignmenMapperDto.getConsultationStartRoleId());
				encounter.setConsultationStartTime(
						encounterAssignmenMapperDto.getConsultationStartTime().isEmpty() ||
						encounterAssignmenMapperDto.getConsultationStartTime() == null ? null :
						CommonDateUtils.getDate(encounterAssignmenMapperDto.getConsultationStartTime(), "dd-MM-yyyy hh:mm:ss"));
				encounter.setConsultationStopBy(encounterAssignmenMapperDto.getConsultationStopBy() == null ? null : encounterAssignmenMapperDto.getConsultationStopBy());
				encounter.setConsultationStopRoleId(encounterAssignmenMapperDto.getConsultationStopRoleId() == null ? null : encounterAssignmenMapperDto.getConsultationStopRoleId());
				encounter.setConsultationStopTime(
						encounterAssignmenMapperDto.getConsultationStopTime().isEmpty() ||
						encounterAssignmenMapperDto.getConsultationStopTime() == null ? null :
						CommonDateUtils.getDate(encounterAssignmenMapperDto.getConsultationStopTime(), "dd-MM-yyyy hh:mm:ss"));
				encounter.setEncounterId(encounterAssignmenMapperDto.getEncounterId());
				encounter.setPatientId(encounterAssignmenMapperDto.getPatientId());
				encounter.setUnitId(encounterAssignmenMapperDto.getUnitId());
				encounter.setOrganizationId(encounterAssignmenMapperDto.getOrganizationId());
				encounter.setIsConsultationStatus(encounterAssignmenMapperDto.getIsConsultationStatus());
				EncounterDetails enc = save(encounter);
			}
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Response getOldListEncounterAssignment(EncounterAssignmenMapperDto encounterAssignmenMapperDto)
			throws ApplicationException {
			try
			{
					Session statlessSession = sessionFactory.getCurrentSession();
					List<EncounterAssignmenMapperDto> listEncounterAssignmenMapperDto = statlessSession.getNamedQuery("GET_OLD_ENCOUNTER_LIST")
							.setInteger("unitId", encounterAssignmenMapperDto.getUnitId())
							.setInteger("organizationId", encounterAssignmenMapperDto.getOrganizationId())
							.setInteger("patientId", encounterAssignmenMapperDto.getPatientId())
							.setResultTransformer(Transformers.aliasToBean(EncounterAssignmenMapperDto.class)).list();
				
				return new Response(SUCCESS, SUCCESS_CODE, null, listEncounterAssignmenMapperDto, null);
			}catch(HibernateException he) { 
				he.printStackTrace();
				throw new HibernateException(he.getMessage());
			}catch(Exception e) {
				e.printStackTrace();
			}
			return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
