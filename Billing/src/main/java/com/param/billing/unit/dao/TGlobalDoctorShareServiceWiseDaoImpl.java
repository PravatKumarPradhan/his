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
import com.param.billing.unit.dto.TGlobalDocShareServiceWiseDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TGlobalDoctorShareServiceWiseDaoImpl extends GenericDao<TGlobalDocShareServiceWise, Integer> implements IGlobalDoctorShareServiceWiseDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TGlobalDoctorShareServiceWiseDaoImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TGlobalDoctorShareServiceWiseDaoImpl() {
		super(TGlobalDocShareServiceWise.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveGlobalDoctorShareServiceWiseDao(TGlobalDocShareServiceWiseDto masterDto)
			throws ApplicationException {
		try{
			TGlobalDocShareServiceWise tGlobalDocShareServiceWiseDto = new TGlobalDocShareServiceWise();
			tGlobalDocShareServiceWiseDto.setCreatedBy(masterDto.getCreatedBy());
			tGlobalDocShareServiceWiseDto.setCreatedDate(new Date());
			tGlobalDocShareServiceWiseDto.setUnitId(masterDto.getUnitId());
			tGlobalDocShareServiceWiseDto.setOrgId(masterDto.getOrgId());
			tGlobalDocShareServiceWiseDto.setUpdatedBy(masterDto.getUpdatedBy());
			tGlobalDocShareServiceWiseDto.setBillingBedCategoryId(masterDto.getBillingBedCategoryId());
			tGlobalDocShareServiceWiseDto.setServiceId(masterDto.getServiceId());;
			tGlobalDocShareServiceWiseDto.setDocShare(masterDto.getDocShare());
			tGlobalDocShareServiceWiseDto.setEffectiveDate(masterDto.getEffectiveDate());
			tGlobalDocShareServiceWiseDto.setPatientTypeId(masterDto.getPatientTypeId());
			tGlobalDocShareServiceWiseDto.setVisitTypeId(masterDto.getVisitTypeId());
			tGlobalDocShareServiceWiseDto.setPaymentEntitlementId(masterDto.getPatientTypeId());
			tGlobalDocShareServiceWiseDto.setGradeId(masterDto.getGradeId());
			tGlobalDocShareServiceWiseDto.setSpecialityId(masterDto.getSpecialityId());
			tGlobalDocShareServiceWiseDto.setStatus(ACTIVE);
			tGlobalDocShareServiceWiseDto = save(tGlobalDocShareServiceWiseDto);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, tGlobalDocShareServiceWiseDto);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}





	
}
