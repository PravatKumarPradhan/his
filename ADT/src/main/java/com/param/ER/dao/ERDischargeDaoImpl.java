package com.param.ER.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.adt.discharge.model.Discharge;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class ERDischargeDaoImpl extends GenericDao<Discharge, Integer> implements IERDischargeDao,ICommonConstants{

	public ERDischargeDaoImpl() {
		super(Discharge.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Response getERDischargePatientList(DischargeDto dischargeDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ER_DISCHARGE_LIST")
					.setInteger("organizationId", dischargeDto.getOrganizationId())
					.setInteger("unitId", dischargeDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
