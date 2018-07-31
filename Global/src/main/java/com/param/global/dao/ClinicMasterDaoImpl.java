package com.param.global.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.AllergyTypeMasterDto;
import com.param.global.dto.ClinicMasterDto;
import com.param.global.model.ClinicMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ClinicMasterDaoImpl extends GenericDao<ClinicMaster, Integer> implements IClinicMasterDao, ICommonConstants{

	public ClinicMasterDaoImpl() {
		super(ClinicMaster.class);
	}

	
	@Override
	public Response getListOfClinicMasterByDoctorId(ClinicMasterDto clinicMasterDto) throws ApplicationException {
		try
		{
			Session statlessSession = sessionFactory.openSession();
			List<ClinicMasterDto> listClinicMasterDto = statlessSession.getNamedQuery("GET_LIST_OF_CLINIC_MASTER_BY_DOCTOR_ID")
					.setInteger("organizationId", clinicMasterDto.getOrganizationId())
					.setInteger("unitId", clinicMasterDto.getUnitId())
					.setInteger("doctoId", clinicMasterDto.getDoctorId())
					.setResultTransformer(Transformers.aliasToBean(ClinicMasterDto.class)).list();
			statlessSession.close();
			return new Response(SUCCESS, SUCCESS_CODE, null, listClinicMasterDto, null);
		}catch(HibernateException he) { 
			he.printStackTrace();
			throw new HibernateException(he.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, SERVER_ERROR, null, null);
	}


	@Override
	public Response saveClinicMaster(ClinicMasterDto clinicMasterDto)throws ApplicationException {
		try{
			ClinicMaster clinicMaster = new ClinicMaster();
			clinicMaster.setClinicCode(clinicMasterDto.getClinicCode());
			clinicMaster.setClinicDescription(clinicMasterDto.getClinicDescription());
			clinicMaster.setCreated_by(1);
			clinicMaster.setFloorId(clinicMasterDto.getFloorId());
			clinicMaster.setWingId(clinicMasterDto.getWingId());
			clinicMaster.setOrganizationId(clinicMasterDto.getOrganizationId());
			clinicMaster.setUnitId(clinicMasterDto.getUnitId());
			clinicMaster.setCerated_date(clinicMasterDto.getCerated_date() != null ? GlobalCommonDateUtils.getDate(clinicMasterDto.getCerated_date(), "dd-M-yyyy hh:mm:ss") :GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			clinicMaster.setUpdated_by(clinicMasterDto.getUpdated_by());
			clinicMaster.setUpdated_date(clinicMasterDto.getUpdated_date() != null ? GlobalCommonDateUtils.getDate(clinicMasterDto.getUpdated_date(), "dd-M-yyyy hh:mm:ss") :GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			clinicMaster.setStatus(clinicMasterDto.getStatus());
			clinicMaster = save(clinicMaster);
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response updateClinicMaster(ClinicMasterDto clinicMasterDto)throws ApplicationException {
		try{
			
			ClinicMaster clinicMaster = new ClinicMaster();
			clinicMaster.setClinicMasterId(clinicMasterDto.getClinicMasterId());
			clinicMaster.setClinicCode(clinicMasterDto.getClinicCode());
			clinicMaster.setClinicDescription(clinicMasterDto.getClinicDescription());
			clinicMaster.setCreated_by(1);
			clinicMaster.setFloorId(clinicMasterDto.getFloorId());
			clinicMaster.setWingId(clinicMasterDto.getWingId());
			clinicMaster.setOrganizationId(clinicMasterDto.getOrganizationId());
			clinicMaster.setUnitId(clinicMasterDto.getUnitId());
			clinicMaster.setCerated_date(clinicMasterDto.getCerated_date() != null ? GlobalCommonDateUtils.getDate(clinicMasterDto.getCerated_date(), "dd-M-yyyy HH:mm:ss") :GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			clinicMaster.setUpdated_by(clinicMasterDto.getUpdated_by());
			clinicMaster.setUpdated_date(clinicMasterDto.getUpdated_date() != null ? GlobalCommonDateUtils.getDate(clinicMasterDto.getUpdated_date(), "dd-M-yyyy HH:mm:ss") :GlobalCommonDateUtils.getDate(new Date(), "dd-M-yyyy hh:mm:ss"));
			clinicMaster.setStatus(clinicMasterDto.getStatus());
			clinicMaster = update(clinicMaster);
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getClinicMasterByName(ClinicMasterDto clinicMasterDto)throws ApplicationException {
		try{
			List<ClinicMasterDto> listClinicMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_CLINIC_BY_NAME_ORG_AND_UNIT")
									.setString("clinicDescription", clinicMasterDto.getClinicDescription())
									.setInteger("organizationId", clinicMasterDto.getOrganizationId())
									.setInteger("unitId", clinicMasterDto.getUnitId())
									.setResultTransformer(Transformers.aliasToBean(ClinicMasterDto.class)).list();
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listClinicMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getClinicMasterByNameAndNotById(ClinicMasterDto clinicMasterDto) throws ApplicationException {
		try{
			List<ClinicMasterDto> listClinicMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_CLINIC_BY_NAME_AND_NOT_BY_ID")
					.setString("clinicDescription", clinicMasterDto.getClinicDescription())
					.setInteger("organizationId", clinicMasterDto.getOrganizationId())
					.setInteger("unitId", clinicMasterDto.getUnitId())
					.setInteger("clinicMasterId", clinicMasterDto.getClinicMasterId())
					.setResultTransformer(Transformers.aliasToBean(ClinicMasterDto.class)).list();

			return new Response<>(SUCCESS, SUCCESS_CODE, null, listClinicMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getClinicMasterListByOrgANdUnitId(ClinicMasterDto clinicMasterDto) throws ApplicationException {
		try{
			
			List<ClinicMasterDto> listClinicMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_CLINIC_MASTER_LIST_BY_ORG_AND_UNIT")
					.setInteger("organizationId", clinicMasterDto.getOrganizationId())
					.setInteger("unitId", clinicMasterDto.getUnitId())
					.setFirstResult(clinicMasterDto.getOffset() != null ? clinicMasterDto.getOffset() : 0)
					.setMaxResults(clinicMasterDto.getNoOfRecordsPerPage() != null ? clinicMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(ClinicMasterDto.class)).list();

			return new Response<>(SUCCESS, SUCCESS_CODE, null, listClinicMasterDto, null);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getClinicMasterById(ClinicMasterDto clinicMasterDto)throws ApplicationException {
		try{
			
			ClinicMasterDto masterDto = (ClinicMasterDto) sessionFactory.getCurrentSession().getNamedQuery("GET_CLINIC_MASTER_BY_ID")
					.setInteger("clinicMasterId", clinicMasterDto.getClinicMasterId())
					.setInteger("organizationId", clinicMasterDto.getOrganizationId())
					.setInteger("unitId", clinicMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(ClinicMasterDto.class)).uniqueResult();

			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, masterDto);
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getClinicMasterCountByOrgAndUnit(int organizationId,int unitId) throws ApplicationException {
		try{
			BigInteger count = (BigInteger) sessionFactory.getCurrentSession().getNamedQuery("GET_CLINIC_COUNT")
								.setInteger("organizationId", organizationId).setInteger("unitId", unitId).uniqueResult();
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, count);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response updateClinicMasterStatus(Integer clinicMasterId,Character status) throws ApplicationException {
		try{
			Integer result = sessionFactory.getCurrentSession().getNamedQuery("UPDATE_CLINIC_STATUS_BY_ID").setCharacter("status", status).setInteger("clinicMasterId", clinicMasterId).executeUpdate();
			
			if(result != null){
				return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, result);
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
