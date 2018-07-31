package com.param.billing.global.transaction.dao;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.billing.global.transaction.dto.DoctorConsultationChargesMasterDto;
import com.param.billing.global.transaction.model.DoctorConsultationChargesMaster;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DoctorConsultationChargesMasterDaoImpl extends GenericDao<DoctorConsultationChargesMaster, Integer> implements IDoctorConsultationChargesMasterDao,ICommonConstants{

	public DoctorConsultationChargesMasterDaoImpl() {
		super(DoctorConsultationChargesMaster.class);
	}

	@Override
	public Response getDoctorConsultationChargesByDoctorIdAndVisitTypeAndClass(
			DoctorConsultationChargesMasterDto doctorConsultationChargesMasterDto) throws ApplicationException {
		try {
			doctorConsultationChargesMasterDto=(DoctorConsultationChargesMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_CHARGES_BY_DOCTOR_VISIT_TYPE_CLASS")
					.setInteger("doctorId", doctorConsultationChargesMasterDto.getDoctorId())
					.setInteger("visitTypeId", doctorConsultationChargesMasterDto.getVisitTypeId())
					.setInteger("classId", doctorConsultationChargesMasterDto.getClassId())
					.setResultTransformer(Transformers.aliasToBean(DoctorConsultationChargesMasterDto.class))
					.uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, doctorConsultationChargesMasterDto);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	

}
