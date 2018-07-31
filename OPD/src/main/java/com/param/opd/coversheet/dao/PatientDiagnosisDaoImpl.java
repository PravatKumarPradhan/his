package com.param.opd.coversheet.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.common.CommonDateUtils;
import com.param.opd.coversheet.dto.DiagnosisPatientAppointmentMapperDto;
import com.param.opd.coversheet.model.DiagnosisPatientAppointmentMapper;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PatientDiagnosisDaoImpl extends GenericDao<DiagnosisPatientAppointmentMapper, Integer>
		implements IPatientDiagnosisDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	public PatientDiagnosisDaoImpl() {
		super(DiagnosisPatientAppointmentMapper.class);
	}

	@Override
	public Response savePatientDiagnosis(DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto)
			throws ApplicationException {
		try {
			for (int i = 0; i < diagnosisPatientAppointmentMapperDto.getListDiagnosisPatientAppointmentDetailsDto()
					.size(); i++) {
				DiagnosisPatientAppointmentMapper diagnosisPatientAppointmentMapper = new DiagnosisPatientAppointmentMapper();
				System.out.println(
						diagnosisPatientAppointmentMapperDto.getListDiagnosisPatientAppointmentDetailsDto().size());
				diagnosisPatientAppointmentMapper.setCreated_date(
						CommonDateUtils.getDate(diagnosisPatientAppointmentMapperDto.getCreated_date(), "dd-MM-yyyy"));
				diagnosisPatientAppointmentMapper
						.setEncounterId(diagnosisPatientAppointmentMapperDto.getEncounterId());
				diagnosisPatientAppointmentMapper.setCreated_by(diagnosisPatientAppointmentMapperDto.getCreated_by());
				diagnosisPatientAppointmentMapper.setUpdated_by(diagnosisPatientAppointmentMapperDto.getUpdated_by());
				diagnosisPatientAppointmentMapper.setUpdated_date(
						CommonDateUtils.getDate(diagnosisPatientAppointmentMapperDto.getUpdated_date(), "dd-MM-yyyy"));
				diagnosisPatientAppointmentMapper.setPatientId(diagnosisPatientAppointmentMapperDto.getPatientId());
				diagnosisPatientAppointmentMapper.setStatus(diagnosisPatientAppointmentMapperDto.getStatus());
				diagnosisPatientAppointmentMapper
						.setOrganizationId(diagnosisPatientAppointmentMapperDto.getOrganizationId());
				diagnosisPatientAppointmentMapper.setUnitId(diagnosisPatientAppointmentMapperDto.getUnitId());
				diagnosisPatientAppointmentMapper.setDiagnosisId(diagnosisPatientAppointmentMapperDto
						.getListDiagnosisPatientAppointmentDetailsDto().get(i).getDiagnosisId());
				diagnosisPatientAppointmentMapper.setRemark(diagnosisPatientAppointmentMapperDto
						.getListDiagnosisPatientAppointmentDetailsDto().get(i).getRemark());
				diagnosisPatientAppointmentMapper.setType(diagnosisPatientAppointmentMapperDto
						.getListDiagnosisPatientAppointmentDetailsDto().get(i).getType());
			      save(diagnosisPatientAppointmentMapper);
			}
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientDiagnosisByPatientId(
			DiagnosisPatientAppointmentMapperDto diagnosisPatientAppointmentMapperDto) throws ApplicationException {
		try {

			List<DiagnosisPatientAppointmentMapperDto> diagnosisPatientAppointmentMapperList = sessionFactory
					.getCurrentSession().getNamedQuery("GET_PATIENT_DIAGNOSIS_BY_PATIENT_ID")
					.setInteger("unitId", diagnosisPatientAppointmentMapperDto.getUnitId())
					.setInteger("orgId", diagnosisPatientAppointmentMapperDto.getOrganizationId())
					.setInteger("patientId", diagnosisPatientAppointmentMapperDto.getPatientId())
					.setResultTransformer(Transformers.aliasToBean(DiagnosisPatientAppointmentMapperDto.class)).list();
			return new Response(SUCCESS, null, null, diagnosisPatientAppointmentMapperList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
