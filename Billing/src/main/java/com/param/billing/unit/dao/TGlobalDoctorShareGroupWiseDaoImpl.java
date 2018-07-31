package com.param.billing.unit.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.IError;
import com.param.billing.exception.ServiceException;
import com.param.billing.global.model.TGlobalDocShareGroupWise;
import com.param.billing.unit.dto.TGlobalDocShareGroupWiseDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.CommonDto;
import com.param.service.global.model.TContractServiceDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TGlobalDoctorShareGroupWiseDaoImpl extends GenericDao<TGlobalDocShareGroupWise, Integer> implements IGlobalDoctorShareGroupWiseDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TGlobalDoctorShareGroupWiseDaoImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TGlobalDoctorShareGroupWiseDaoImpl() {
		super(TGlobalDocShareGroupWise.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveGlobalDoctorShareGroupWiseDao(TGlobalDocShareGroupWiseDto masterDto)
			throws ApplicationException {
		try{
			TGlobalDocShareGroupWise mContarctServiceDtlsMst = new TGlobalDocShareGroupWise();
			mContarctServiceDtlsMst.setCreatedBy(masterDto.getCreatedBy());
			mContarctServiceDtlsMst.setCreatedDate(new Date());
			mContarctServiceDtlsMst.setUnitId(masterDto.getUnitId());
			mContarctServiceDtlsMst.setOrgId(masterDto.getOrgId());
			mContarctServiceDtlsMst.setUpdatedBy(masterDto.getUpdatedBy());
			mContarctServiceDtlsMst.setBillingBedCategoryId(masterDto.getBillingBedCategoryId());
			mContarctServiceDtlsMst.setDepartmentId(masterDto.getDepartmentId());
			mContarctServiceDtlsMst.setSubDepartmentId(masterDto.getSubDepartmentId());
			mContarctServiceDtlsMst.setDocShare(masterDto.getDocShare());
			mContarctServiceDtlsMst.setEffectiveDate(masterDto.getEffectiveDate());
			mContarctServiceDtlsMst.setPatientTypeId(masterDto.getPatientTypeId());
			mContarctServiceDtlsMst.setVisitTypeId(masterDto.getVisitTypeId());
			mContarctServiceDtlsMst.setPaymentEntitlementId(masterDto.getPatientTypeId());
			mContarctServiceDtlsMst.setGradeId(masterDto.getGradeId());
			mContarctServiceDtlsMst.setSpecialityId(masterDto.getSpecialityId());
			mContarctServiceDtlsMst.setStatus(ACTIVE);
			mContarctServiceDtlsMst = save(mContarctServiceDtlsMst);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mContarctServiceDtlsMst);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}



	@Override
	public Response getDoctorGradeList(Integer orgId,Integer orgUnitId) throws ApplicationException
	{
		List<CommonDto>  listDoctorGrade  = null; 
		try
		{
			listDoctorGrade = (List<CommonDto>) sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT "
							+"	docgrade.doctor_grade_id AS \"id\", "
							+"	docgrade.doc_grade_name AS \"name\" "
							+"FROM "
							+"	doctor.m_doctor_grade docgrade "
							+"WHERE "
							+"	docgrade.status = 'A' "
							+"	AND docgrade.org_id = :orgId "
							+"	AND docgrade.org_unit_id = :orgUnitId ")
					.setInteger("orgId", orgId)
					.setInteger("orgUnitId", orgUnitId)
                    .setResultTransformer(Transformers.aliasToBean(CommonDto.class)).list();					
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_CODE, listDoctorGrade, null);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500, null, null);
		}
	}

	
}
