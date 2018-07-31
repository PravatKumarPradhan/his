package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dao.IGeneralExaminationAppoMapperDao;
import com.param.opd.coversheet.dto.GeneralExaminationAppoMapperDto;
import com.param.opd.coversheet.model.GeneralExaminationAppoMapper;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GeneralExaminationAppoMapperServiceImpl implements IGeneralExaminationAppoMapperService, ICommonConstants{

	@Autowired
	private IGeneralExaminationAppoMapperDao iGeneralExaminationAppoMapperDao;
	
	@Override
	public Response saveGeneralExaminationMapper(GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto)throws ApplicationException {
		try
		{
			for(int i=0;i<generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().size();i++)
			{	
				if(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation() != null 
						&& generalExaminationAppoMapperDto.getIsNADValue() != YES)
				{
					for(int j=0;j<generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().size();j++)
					{
						if(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getListOfGeneralSystemProperty() != null 
								&& generalExaminationAppoMapperDto.getIsNADValue() != YES
								&& (generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getRemark().equals(null) 
								|| generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getRemark().equals("")))
						{
							for(int k=0;k<generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getListOfGeneralSystemProperty().size();k++)
							{
								
								GeneralExaminationAppoMapper generalExaminationAppoMapper = new GeneralExaminationAppoMapper();
								
								generalExaminationAppoMapper.setSystemId(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getSystemId());
								generalExaminationAppoMapper.setSystemObervationId(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getSystemObervationId());
								generalExaminationAppoMapper.setSystemObervationPropertyId(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getListOfGeneralSystemProperty().get(k).getSystemObervationPropertyId());
								generalExaminationAppoMapper.setIsNADValue(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getIsNADValue());
								generalExaminationAppoMapper.setRemark(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getListOfGeneralSystemProperty().get(k).getRemark());
								
								generalExaminationAppoMapper.setEncounterId(generalExaminationAppoMapperDto.getEncounterId());
								generalExaminationAppoMapper.setPatientId(generalExaminationAppoMapperDto.getPatientId());
								generalExaminationAppoMapper.setCreatedBy(generalExaminationAppoMapperDto.getCreatedBy());
								generalExaminationAppoMapper.setCreatedDate(CommonDateUtils.getDate(generalExaminationAppoMapperDto.getCreatedDate(), "dd-mm-yyyy"));
								generalExaminationAppoMapper.setOrganizationId(generalExaminationAppoMapperDto.getOrganizationId());
								generalExaminationAppoMapper.setStatus(generalExaminationAppoMapperDto.getStatus());
								generalExaminationAppoMapper.setUnitId(generalExaminationAppoMapperDto.getUnitId());
								generalExaminationAppoMapper.setUpdatedBy(generalExaminationAppoMapperDto.getUpdatedBy());
								generalExaminationAppoMapper.setUpdatedDate(CommonDateUtils.getDate(generalExaminationAppoMapperDto.getUpdatedDate(), "dd-mm-yyyy"));
								generalExaminationAppoMapper.setTypeId(generalExaminationAppoMapperDto.getTypeId());
								
								iGeneralExaminationAppoMapperDao.saveGeneralExamSystemProperty(generalExaminationAppoMapper);
								
							}//END SYSTEM PROPERTY FOR.
						
						}//END SYSTEM PROPERTY IF.
						else
						{
							GeneralExaminationAppoMapper generalExaminationAppoMapper = new GeneralExaminationAppoMapper();
							
							generalExaminationAppoMapper.setSystemId(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getSystemId());
							generalExaminationAppoMapper.setSystemObervationId(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getSystemObervationId());
							generalExaminationAppoMapper.setIsNADValue(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getIsNADValue());
							generalExaminationAppoMapper.setRemark(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getListOfGeneralSystemObservation().get(j).getRemark());
							
							generalExaminationAppoMapper.setEncounterId(generalExaminationAppoMapperDto.getEncounterId());
							generalExaminationAppoMapper.setPatientId(generalExaminationAppoMapperDto.getPatientId());
							generalExaminationAppoMapper.setCreatedBy(generalExaminationAppoMapperDto.getCreatedBy());
							generalExaminationAppoMapper.setCreatedDate(CommonDateUtils.getDate(generalExaminationAppoMapperDto.getCreatedDate(), "dd-mm-yyyy"));
							generalExaminationAppoMapper.setOrganizationId(generalExaminationAppoMapperDto.getOrganizationId());
							generalExaminationAppoMapper.setStatus(generalExaminationAppoMapperDto.getStatus());
							generalExaminationAppoMapper.setUnitId(generalExaminationAppoMapperDto.getUnitId());
							generalExaminationAppoMapper.setUpdatedBy(generalExaminationAppoMapperDto.getUpdatedBy());
							generalExaminationAppoMapper.setUpdatedDate(CommonDateUtils.getDate(generalExaminationAppoMapperDto.getUpdatedDate(), "dd-mm-yyyy"));
							generalExaminationAppoMapper.setTypeId(generalExaminationAppoMapperDto.getTypeId());
							
							iGeneralExaminationAppoMapperDao.saveGeneralExaminationObservationMapper(generalExaminationAppoMapper);
						}
						
					}//END SYSTEM OBSERVATION FOR LOOP.
				}//END SYSTEM OBSERVATION IF LOOP.
				
				
				//CODE FOR SAVE SYSTEM 
				if(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getIsNADValue() != NO)
				{
					GeneralExaminationAppoMapper generalExaminationAppoMapper = new GeneralExaminationAppoMapper();
					
					generalExaminationAppoMapper.setSystemId(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getSystemId());
					generalExaminationAppoMapper.setIsNADValue(generalExaminationAppoMapperDto.getListOfListOfGeneralExamSystemMaster().get(i).getIsNADValue());
					
					generalExaminationAppoMapper.setEncounterId(generalExaminationAppoMapperDto.getEncounterId());
					generalExaminationAppoMapper.setPatientId(generalExaminationAppoMapperDto.getPatientId());
					generalExaminationAppoMapper.setCreatedBy(generalExaminationAppoMapperDto.getCreatedBy());
					generalExaminationAppoMapper.setCreatedDate(CommonDateUtils.getDate(generalExaminationAppoMapperDto.getCreatedDate(), "dd-mm-yyyy"));
					generalExaminationAppoMapper.setOrganizationId(generalExaminationAppoMapperDto.getOrganizationId());
					generalExaminationAppoMapper.setStatus(generalExaminationAppoMapperDto.getStatus());
					generalExaminationAppoMapper.setUnitId(generalExaminationAppoMapperDto.getUnitId());
					generalExaminationAppoMapper.setUpdatedBy(generalExaminationAppoMapperDto.getUpdatedBy());
					generalExaminationAppoMapper.setUpdatedDate(CommonDateUtils.getDate(generalExaminationAppoMapperDto.getUpdatedDate(), "dd-mm-yyyy"));
					generalExaminationAppoMapper.setTypeId(generalExaminationAppoMapperDto.getTypeId());
					iGeneralExaminationAppoMapperDao.saveGeneralExaminationSystem(generalExaminationAppoMapper);
					
				}
				
			}//END SYSTEM MASER FOR LOOP.
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
		
	}

	@Override
	@Transactional
	public Response getListOfGeneralExaminationMapper(GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iGeneralExaminationAppoMapperDao.getListOfGeneralExaminationMapper(generalExaminationAppoMapperDto);
	}

	@Override
	@Transactional
	public Response getListOfSavedGeneralExaminationMapper(GeneralExaminationAppoMapperDto generalExaminationAppoMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iGeneralExaminationAppoMapperDao.getListOfSavedGeneralExaminationMapper(generalExaminationAppoMapperDto);
	}

}
