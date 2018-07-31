package com.param.opd.coversheet.dao;

import java.util.List;

import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.opd.coversheet.dto.DiagnosisMasterDto;
import com.param.opd.coversheet.model.DiagnosisMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class DiagnosisMasterDaoImpl extends GenericDao<DiagnosisMaster, Integer> implements IDiagnosisMasterDao,ICommonConstants{

	public DiagnosisMasterDaoImpl() {
		super(DiagnosisMaster.class);
		// TODO Auto-generated constructor stub
	}

	/*@Override
	public Response getListOfDiagnosisMaster(DiagnosisMasterDto diagnosisMasterDto) throws ApplicationException {
		try {
			
			List<DiagnosisMasterDto> DiagnosisMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("DIAGNOSIS_AUTO_FILL_SEARCH")
					.setParameter("diagnosisName", "%" + diagnosisMasterDto.getDiagnosisName().toLowerCase() + "%")
					.setInteger("organizationId", diagnosisMasterDto.getOrganizationId())
					.setInteger("unitId", diagnosisMasterDto.getUnitId())
					.setInteger("doctorId", diagnosisMasterDto.getDoctorId())
					.setResultTransformer(Transformers.aliasToBean(DiagnosisMasterDto.class)).list();
			return new Response(SUCCESS, null, null, DiagnosisMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}*/
	@Override
	public Response getListOfDiagnosisMaster(DiagnosisMasterDto diagnosisMasterDto) throws ApplicationException {
		try {
			
			List<DiagnosisMasterDto> DiagnosisMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("DIAGNOSIS_AUTO_FILL_SEARCH")
					.setParameter("diagnosisName", "%" + diagnosisMasterDto.getDiagnosisName().toLowerCase() + "%")
					.setInteger("organizationId", diagnosisMasterDto.getOrganizationId())
					.setInteger("unitId", diagnosisMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(DiagnosisMasterDto.class)).list();
			return new Response(SUCCESS, null, null, DiagnosisMasterDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
