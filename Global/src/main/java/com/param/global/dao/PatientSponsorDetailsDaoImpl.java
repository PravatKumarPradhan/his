package com.param.global.dao;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.PatientSponsorDetailsDto;
import com.param.global.model.PatientSponsorDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientSponsorDetailsDaoImpl extends GenericDao<PatientSponsorDetails, Integer>implements IPatientSponsorDetailsDao,ICommonConstants{

	public PatientSponsorDetailsDaoImpl() {
		super(PatientSponsorDetails.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	

	@Override
	public Response savePatientSponsorDetails(PatientSponsorDetailsDto patientSponsorDetailsDto)
			throws ApplicationException {
		try {
			PatientSponsorDetails patientSponsorDetails = mapper.map(patientSponsorDetailsDto,
					PatientSponsorDetails.class, "PatientSponsorDetailsDto_to_PatientSponsorDetails");
			save(patientSponsorDetails);
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {

			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
