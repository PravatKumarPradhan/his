package com.param.billing.unit.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.IError;
import com.param.billing.exception.ServiceException;
import com.param.billing.global.model.TGlobalDocShareGroupWise;
import com.param.billing.global.model.TIndividualDocShareGroupWise;
import com.param.billing.unit.dto.TGlobalDocShareGroupWiseDto;
import com.param.billing.unit.dto.TIndividualDocShareGroupWiseDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.global.model.TContractServiceDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TIndividualDoctorShareGroupWiseDaoImpl extends GenericDao<TIndividualDocShareGroupWise, Integer> implements IIndividualDoctorShareGroupWiseDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TIndividualDoctorShareGroupWiseDaoImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TIndividualDoctorShareGroupWiseDaoImpl() {
		super(TIndividualDocShareGroupWise.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveIndividualDoctorShareGroupWise(TIndividualDocShareGroupWiseDto masterDto)
			throws ApplicationException {
		try{
			TIndividualDocShareGroupWise individualDoctorShareGroupDtlsMst = new TIndividualDocShareGroupWise();
			individualDoctorShareGroupDtlsMst.setCreatedBy(masterDto.getCreatedBy());
			individualDoctorShareGroupDtlsMst.setCreatedDate(new Date());
			individualDoctorShareGroupDtlsMst.setUnitId(masterDto.getUnitId());
			individualDoctorShareGroupDtlsMst.setOrgId(masterDto.getOrgId());
			individualDoctorShareGroupDtlsMst.setUpdatedBy(masterDto.getUpdatedBy());
			individualDoctorShareGroupDtlsMst.setBillingBedCategoryId(masterDto.getBillingBedCategoryId());
			individualDoctorShareGroupDtlsMst.setDepartmentId(masterDto.getDepartmentId());
			individualDoctorShareGroupDtlsMst.setSubDepartmentId(masterDto.getSubDepartmentId());
			individualDoctorShareGroupDtlsMst.setDocShare(masterDto.getDocShare());
			individualDoctorShareGroupDtlsMst.setEffectiveDate(masterDto.getEffectiveDate());
			individualDoctorShareGroupDtlsMst.setPatientTypeId(masterDto.getPatientTypeId());
			individualDoctorShareGroupDtlsMst.setVisitTypeId(masterDto.getVisitTypeId());
			individualDoctorShareGroupDtlsMst.setPaymentEntitlementId(masterDto.getPatientTypeId());
			individualDoctorShareGroupDtlsMst.setDoctorId(masterDto.getDoctorId());
			individualDoctorShareGroupDtlsMst.setSpecialityId(masterDto.getSpecialityId());
			individualDoctorShareGroupDtlsMst.setStatus(ACTIVE);
			individualDoctorShareGroupDtlsMst = save(individualDoctorShareGroupDtlsMst);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, individualDoctorShareGroupDtlsMst);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}





	
}
