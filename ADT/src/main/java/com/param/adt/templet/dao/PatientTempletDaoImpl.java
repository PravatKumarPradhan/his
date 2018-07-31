package com.param.adt.templet.dao;

import java.util.List;


import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.templet.dto.PatientTempletDto;
import com.param.adt.templet.model.PatientTemplet;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientTempletDaoImpl extends GenericDao<PatientTemplet, Integer>
		implements IPatientTempletDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;
	

	@Autowired
	private DozerBeanMapper mapper;

	public PatientTempletDaoImpl() {
		super(PatientTemplet.class);
	}

	@Override
	public Response savePatientTemplet(PatientTempletDto patientTempletDto) throws ApplicationException {
		try {
			List<PatientTempletDto> patientTempletDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_TEMPLET_LIST_BY_ID")
					.setInteger("admissionId", patientTempletDto.getAdmissionId())
					.setInteger("typeId", patientTempletDto.getTypeId())
					.setInteger("templeId", patientTempletDto.getTempletId())
					.setInteger("unitId", patientTempletDto.getUnitId())
					.setInteger("orgId", patientTempletDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(PatientTempletDto.class)).list();

			if (patientTempletDtosList.size() > 0) {
				String updateQuery = 
						 "UPDATE " 
				        + " PatientTemplet "
						+ " SET status='I' "				       
						+ " WHERE templetId=:tempId "
						+ " AND admissionId=:admisionId "
						+ " AND typeId=:typeId";
				sessionFactory.getCurrentSession().createQuery(updateQuery)
						.setParameter("tempId", patientTempletDto.getTempletId())
						.setParameter("admisionId", patientTempletDto.getAdmissionId())
						.setParameter("typeId",patientTempletDto.getTypeId())
						.executeUpdate();				
			}
			PatientTemplet patientTemplet = mapper.map(patientTempletDto, PatientTemplet.class, "PatientTempletDto_to_PatientTemplet");
			patientTemplet = save(patientTemplet);

			return new Response(SUCCESS, null, null, null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
