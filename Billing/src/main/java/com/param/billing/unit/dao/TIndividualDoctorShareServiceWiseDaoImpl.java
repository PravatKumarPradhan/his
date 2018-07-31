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
import com.param.billing.global.model.TGlobalDocShareServiceWise;
import com.param.billing.global.model.TIndividualDocShareServiceWise;
import com.param.billing.unit.dto.TGlobalDocShareServiceWiseDto;
import com.param.billing.unit.dto.TIndividualDocShareServiceWiseDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TIndividualDoctorShareServiceWiseDaoImpl extends GenericDao<TIndividualDocShareServiceWise, Integer> implements IIndividualDoctorShareServiceWiseDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TIndividualDoctorShareServiceWiseDaoImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TIndividualDoctorShareServiceWiseDaoImpl() {
		super(TIndividualDocShareServiceWise.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveIndividualDocShareServiceWise(TIndividualDocShareServiceWiseDto masterDto)
			throws ApplicationException {
		try{
			TIndividualDocShareServiceWise tIndividualDocShareServiceWise = new TIndividualDocShareServiceWise();
			tIndividualDocShareServiceWise.setCreatedBy(masterDto.getCreatedBy());
			tIndividualDocShareServiceWise.setCreatedDate(new Date());
			tIndividualDocShareServiceWise.setUnitId(masterDto.getUnitId());
			tIndividualDocShareServiceWise.setOrgId(masterDto.getOrgId());
			tIndividualDocShareServiceWise.setUpdatedBy(masterDto.getUpdatedBy());
			tIndividualDocShareServiceWise.setBillingBedCategoryId(masterDto.getBillingBedCategoryId());
			tIndividualDocShareServiceWise.setServiceId(masterDto.getServiceId());;
			tIndividualDocShareServiceWise.setDocShare(masterDto.getDocShare());
			tIndividualDocShareServiceWise.setEffectiveDate(masterDto.getEffectiveDate());
			tIndividualDocShareServiceWise.setPatientTypeId(masterDto.getPatientTypeId());
			tIndividualDocShareServiceWise.setVisitTypeId(masterDto.getVisitTypeId());
			tIndividualDocShareServiceWise.setPaymentEntitlementId(masterDto.getPatientTypeId());
			tIndividualDocShareServiceWise.setDoctorId(masterDto.getDoctorId());
			tIndividualDocShareServiceWise.setSpecialityId(masterDto.getSpecialityId());
			tIndividualDocShareServiceWise.setStatus(ACTIVE);
			tIndividualDocShareServiceWise = save(tIndividualDocShareServiceWise);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, tIndividualDocShareServiceWise);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}





	
}
