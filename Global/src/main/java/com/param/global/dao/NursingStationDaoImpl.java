package com.param.global.dao;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.adt.master.unit.model.NursingStationMaster;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.AllergyMasterDto;
import com.param.global.dto.NursingStationMasterDto;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class NursingStationDaoImpl extends GenericDao<NursingStationMaster, Integer> implements INursingStationDao, ICommonConstants {

	
	public NursingStationDaoImpl() {
		super(NursingStationMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response getListOfNursingStationMaster(NursingStationMasterDto nursingStationMasterDto)
			throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<NursingStationMasterDto> listNursingStationMasterDto = statlessSession.getNamedQuery("GET_LIST_NURSING_STATION")
					.setInteger("organizationId", nursingStationMasterDto.getOrganizationId())
					.setInteger("unitId", nursingStationMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(NursingStationMasterDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listNursingStationMasterDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

	@Override
	public Response getListOfNursingStationMasterByClinicId(NursingStationMasterDto nursingStationMasterDto)throws ApplicationException {
		try
		{
			StatelessSession statlessSession = sessionFactory.openStatelessSession();
			List<NursingStationMasterDto> listNursingStationMasterDto = statlessSession.getNamedQuery("GET_NURSING_LIST_BY_CLINIC_ID")
					.setInteger("organizationId", nursingStationMasterDto.getOrganizationId())
					.setInteger("unitId", nursingStationMasterDto.getUnitId())
					.setInteger("clinicId", nursingStationMasterDto.getClinicId())
					.setResultTransformer(Transformers.aliasToBean(NursingStationMasterDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listNursingStationMasterDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}

}
