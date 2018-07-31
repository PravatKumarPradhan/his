package com.param.opd.coversheet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.ComplaintAppointmentDto;
import com.param.opd.coversheet.dto.ComplaintAppointmentMapperDto;
import com.param.opd.coversheet.model.ComplaintAppointmentMapper;
import com.param.opd.coversheet.service.IComplaintAppointmentMapperService;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unused", "unchecked" })
public class ComplaintAppointmentMapperDaoImpl extends GenericDao<ComplaintAppointmentMapper, Integer> implements IComplaintAppointmentMapperDao,ICommonConstants{

	public ComplaintAppointmentMapperDaoImpl() {
		super(ComplaintAppointmentMapper.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private IComplaintAppointmentMapperService iComplaintAppointmentMapperService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public Response saveComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException {
		// TODO Auto-generated method stub
		try
		{
			for(int i=0;i<complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().size();i++)
			{
				if(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getComplaintAppoId() != null && complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getComplaintAppoId()>0)
				{
					
					ComplaintAppointmentMapper complaintAppointmentMapper = findById(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getComplaintAppoId());
					complaintAppointmentMapper.setIsReviewedFlag(YES);
					iComplaintAppointmentMapperService.updateSaveNurseReviewedFlag(complaintAppointmentMapper);
				}
				
				ComplaintAppointmentMapper complaintAppointmentMapper = new ComplaintAppointmentMapper();
				System.out.println(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().size());
				complaintAppointmentMapper.setCerated_date(CommonDateUtils.getDate(complaintAppointmentMapperDto.getCeratedDate(), "dd-MM-yyyy"));
				complaintAppointmentMapper.setEncounterId(complaintAppointmentMapperDto.getEncounterId());
				
				if(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getIsReviewedFlag() == '\0')
				{
					complaintAppointmentMapper.setIsReviewedFlag(NO);
				}
				else
				{
					complaintAppointmentMapper.setIsReviewedFlag(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getIsReviewedFlag());
				}
				
				complaintAppointmentMapper.setRoleId(complaintAppointmentMapperDto.getRoleId());
				complaintAppointmentMapper.setCreated_by(complaintAppointmentMapperDto.getCreatedBy());
				complaintAppointmentMapper.setUpdated_by(complaintAppointmentMapperDto.getUpdatedBy());
				complaintAppointmentMapper.setUpdated_date(CommonDateUtils.getDate(complaintAppointmentMapperDto.getUpdatedDate(), "dd-MM-yyyy"));
				complaintAppointmentMapper.setStatus(complaintAppointmentMapperDto.getStatus());
				complaintAppointmentMapper.setOrganizationId(complaintAppointmentMapperDto.getOrganizationId());
				complaintAppointmentMapper.setUnitId(complaintAppointmentMapperDto.getUnitId());
				complaintAppointmentMapper.setDiagnosisId(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getDiagnosisId());
				complaintAppointmentMapper.setDuration(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getDuration());
				complaintAppointmentMapper.setRemark(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getRemark());
				complaintAppointmentMapper.setSince(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getSince());
			
				save(complaintAppointmentMapper);
			}
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}



	@SuppressWarnings("rawtypes")
	@Override
	public Response getListOfComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)
			throws ApplicationException {
		try
		{
			List<ComplaintAppointmentDto> listComplaintAppointmentMapper;
			if(complaintAppointmentMapperDto.getRoleId() == 3)
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listComplaintAppointmentMapper = statlessSession.getNamedQuery("GET_COMPLAINT_APPOINTMENT_MAPPER_BY_ROLE_ID")
						.setInteger("encounterId", complaintAppointmentMapperDto.getEncounterId())
						.setInteger("unitId", complaintAppointmentMapperDto.getUnitId())
						.setInteger("organizationId", complaintAppointmentMapperDto.getOrganizationId())
						.setInteger("roleId", complaintAppointmentMapperDto.getRoleId())
						.setResultTransformer(Transformers.aliasToBean(ComplaintAppointmentDto.class)).list();
				statlessSession.close();
			}
			else
			{
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				 listComplaintAppointmentMapper = statlessSession.getNamedQuery("GET_COMPLAINT_APPOINTMENT_MAPPER")
						.setInteger("encounterId", complaintAppointmentMapperDto.getEncounterId())
						.setInteger("unitId", complaintAppointmentMapperDto.getUnitId()).setInteger("organizationId", complaintAppointmentMapperDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(ComplaintAppointmentDto.class)).list();
				statlessSession.close();
			}
			return new Response(SUCCESS, SUCCESS_CODE, null, listComplaintAppointmentMapper, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}



	@Override
	public Response editListOfComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException {
		try
		{
			List<ComplaintAppointmentDto> listComplaintAppointmentMapper;
				StatelessSession statlessSession = sessionFactory.openStatelessSession();
				listComplaintAppointmentMapper = statlessSession.getNamedQuery("GET_COMPLAINT_APPOINTMENT_MAPPER_BY_COMPLAINT_ID")
						.setInteger("complaintAppoId", complaintAppointmentMapperDto.getComplaintAppoId())
						.setInteger("unitId", complaintAppointmentMapperDto.getUnitId())
						.setInteger("organizationId", complaintAppointmentMapperDto.getOrganizationId())
						.setResultTransformer(Transformers.aliasToBean(ComplaintAppointmentDto.class)).list();
				statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listComplaintAppointmentMapper, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}



	@Override
	public Response updateComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)
			throws ApplicationException {
		try
		{
			for(int i=0;i<complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().size();i++)
			{
				Session statlessSession = sessionFactory.getCurrentSession();
				ComplaintAppointmentMapper complaintAppointmentMapper = (ComplaintAppointmentMapper) statlessSession.getNamedQuery("GET_COMPLAINT_APPOINTMENT_MAPPER_BY_COMPLAINT_ID")
						.setInteger("complaintAppoId", complaintAppointmentMapperDto.getComplaintAppoId())
						.setResultTransformer(Transformers.aliasToBean(ComplaintAppointmentMapper.class)).uniqueResult();
				
				if(complaintAppointmentMapperDto.getRoleId() != null && 
						complaintAppointmentMapperDto.getRoleId() == 2 && 
						complaintAppointmentMapper.getRoleId() != 2  && 
						complaintAppointmentMapper.getIsReviewedFlag() == 'N'
						)
				{
					iComplaintAppointmentMapperService.saveComplaintAppointmentMapper(complaintAppointmentMapperDto);
				}
				
				
				complaintAppointmentMapper.setCerated_date(CommonDateUtils.getDate(complaintAppointmentMapperDto.getCeratedDate(), "dd-MM-yyyy"));
				complaintAppointmentMapper.setEncounterId(complaintAppointmentMapperDto.getEncounterId());
				complaintAppointmentMapper.setRoleId(complaintAppointmentMapper.getRoleId());
				
				if(complaintAppointmentMapper.getRoleId() == 3 && complaintAppointmentMapperDto.getRoleId() == 2 )
				{
					complaintAppointmentMapper.setIsReviewedFlag(YES);
				}
				else
				{
					complaintAppointmentMapper.setIsReviewedFlag(NO);
				}
				
				//complaintAppointmentMapper.setIsReviewedFlag(complaintAppointmentMapper.getRoleId() == 3 ? YES:NO);
				//complaintAppointmentMapper.setIsReviewedFlag(YES);
				complaintAppointmentMapper.setCreated_by(complaintAppointmentMapperDto.getCreatedBy());
				complaintAppointmentMapper.setUpdated_by(complaintAppointmentMapperDto.getUpdatedBy());
				complaintAppointmentMapper.setUpdated_date(CommonDateUtils.getDate(complaintAppointmentMapperDto.getUpdatedDate(), "dd-MM-yyyy"));
				complaintAppointmentMapper.setStatus(complaintAppointmentMapperDto.getStatus());
				complaintAppointmentMapper.setOrganizationId(complaintAppointmentMapperDto.getOrganizationId());
				complaintAppointmentMapper.setUnitId(complaintAppointmentMapperDto.getUnitId());
				complaintAppointmentMapper.setDiagnosisId(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getDiagnosisId());
				complaintAppointmentMapper.setDuration(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getDuration());
				complaintAppointmentMapper.setRemark(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getRemark());
				complaintAppointmentMapper.setSince(complaintAppointmentMapperDto.getListComplaintAppointmentDetailsDto().get(i).getSince());
			
				save(complaintAppointmentMapper);
				
					
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
			ComplaintAppointmentMapper complaintAppointmentMapper)
			throws ApplicationException {
		try
		{
			save(complaintAppointmentMapper);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
