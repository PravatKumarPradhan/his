package com.param.opd.coversheet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.model.SystemMaster;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.GeneralExaminationAppoMapperDto;
import com.param.opd.coversheet.dto.PatientHabitDetailsDto;
import com.param.opd.coversheet.model.GeneralExaminationAppoMapper;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class GeneralExaminationAppoMapperDaoImpl extends GenericDao<GeneralExaminationAppoMapper, Integer> implements
		IGeneralExaminationAppoMapperDao, ICommonConstants{

	public GeneralExaminationAppoMapperDaoImpl() {
		super(GeneralExaminationAppoMapper.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response getListOfGeneralExaminationMapper(GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto)throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<GeneralExaminationAppoMapperDto> listGeneralExaminationAppoMapperDto = statlessSession.getNamedQuery("GET_GENERAL_EXAM_LIST")
					.setInteger("patientId", generalExaminationAppoMapperDto.getPatientId())
					.setInteger("unitId", generalExaminationAppoMapperDto.getUnitId())
					.setInteger("organizationId", generalExaminationAppoMapperDto.getOrganizationId())
					.setInteger("typeId", generalExaminationAppoMapperDto.getTypeId())
					.setResultTransformer(Transformers.aliasToBean(GeneralExaminationAppoMapperDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listGeneralExaminationAppoMapperDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	@Transactional
	public Response saveGeneralExamSystemProperty(GeneralExaminationAppoMapper generalExamination)
			throws ApplicationException {
		try
		{
			GeneralExaminationAppoMapper propertyId = save(generalExamination);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}
		catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	@Transactional
	public Response saveGeneralExaminationObservationMapper(GeneralExaminationAppoMapper generalExamination)
			throws ApplicationException {
		try
		{
			GeneralExaminationAppoMapper observationId = save(generalExamination);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}
		catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	@Transactional
	public Response saveGeneralExaminationSystem(GeneralExaminationAppoMapper generalExamination)throws ApplicationException {
		// TODO Auto-generated method stub
		try
		{
			GeneralExaminationAppoMapper observationId = save(generalExamination);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}
		catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response getListOfSavedGeneralExaminationMapper(GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto)
			throws ApplicationException {
		try {
				  Session statlessSession = sessionFactory.openSession();
		
				  List<GeneralExaminationAppoMapperDto> listGeneralExaminationAppoMapperDto =
				  statlessSession.getNamedQuery("GET_SAVED_GENERAL_EXAM_LIST")
				  .setInteger("unitId", generalExaminationAppoMapperDto.getUnitId())
				  .setInteger("organizationId", generalExaminationAppoMapperDto.getOrganizationId())
				  .setInteger("typeId", generalExaminationAppoMapperDto.getTypeId())
				  .setInteger("patientId", generalExaminationAppoMapperDto.getPatientId())
				  .setResultTransformer(Transformers.aliasToBean(GeneralExaminationAppoMapperDto.class))
				  .list();
			  
				  List<SystemMaster> listSystemMaster = statlessSession
				  .createQuery("from SystemMaster p where unit_id=:unitId AND organization_id=:organizationId AND status = 'A' and (typeId=:typeId OR typeId=3) AND (genderId=:genderId OR genderId=4)")
				  .setInteger("genderId", generalExaminationAppoMapperDto.getGenderId())
				  .setInteger("unitId", generalExaminationAppoMapperDto.getUnitId())
				  .setInteger("typeId", generalExaminationAppoMapperDto.getTypeId())
				  .setInteger("organizationId", generalExaminationAppoMapperDto.getOrganizationId()).list();

				  for (int i = 0; i < listSystemMaster.size(); i++) 
				  {
					for(GeneralExaminationAppoMapperDto dto : listGeneralExaminationAppoMapperDto){
						if(dto.getSystemId() == listSystemMaster.get(i).getSystemId())
							{
								listSystemMaster.get(i).setSystemFlag(true);
								listSystemMaster.get(i).setSystemNADFlag(dto.getIsNADValue());
								break;
							}
					}
				
					for (int j = 0; j < listSystemMaster.get(i).getSystemObervationMasterList().size(); j++) 
					{
						for(GeneralExaminationAppoMapperDto dto : listGeneralExaminationAppoMapperDto){
							if (dto.getSystemObervationId() == listSystemMaster.get(i).getSystemObervationMasterList().get(j).getObservationId()) 
								{
									listSystemMaster.get(i).getSystemObervationMasterList().get(j).setSystemObservationFlag(true);
									listSystemMaster.get(i).getSystemObervationMasterList().get(j).setSystemObervationRemark(dto.getRemark());
									break;
								}
						}
						//ONE WAY TRASECTION 
						listSystemMaster.get(i).getSystemObervationMasterList().get(j).setSystemMaster(null);
					
						for (int k = 0; k < listSystemMaster.get(i).getSystemObervationMasterList().get(j).getSystemObervationPropertyMasterList().size(); k++) 
						{
							for(GeneralExaminationAppoMapperDto dto : listGeneralExaminationAppoMapperDto){
								if (dto.getSystemObervationPropertyId() == listSystemMaster.get(i).getSystemObervationMasterList().get(j).getSystemObervationPropertyMasterList().get(k).getPropertyId()) 
									{
										listSystemMaster.get(i).getSystemObervationMasterList().get(j).getSystemObervationPropertyMasterList().get(k).setSystemObservationPropertyFlag(true);
										listSystemMaster.get(i).getSystemObervationMasterList().get(j).getSystemObervationPropertyMasterList().get(k).setSystemObservationPropertyRemark(dto.getRemark());
										break;
									}
							}
							
						}
					}
				  }
			return new Response(SUCCESS, SUCCESS_CODE, null, listSystemMaster, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}
	
}
