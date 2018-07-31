package com.param.billing.unit.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.IError;
import com.param.billing.exception.ServiceException;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.TContractBedCategoryDetailDto;
import com.param.service.global.model.TContractBedCategoryDetail;
import com.param.service.global.model.TContractCapDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TContractBeadBillingDetailsDaoImpl extends GenericDao<TContractBedCategoryDetail, Integer> implements ITContractBedBillingCategoryDetailsDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TContractBeadBillingDetailsDaoImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TContractBeadBillingDetailsDaoImpl() {
		super(TContractBedCategoryDetail.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveContractBedBillingCategoryDetails(TContractBedCategoryDetailDto masterDto)
			throws ApplicationException {
		try{
			TContractBedCategoryDetail mContarctCapMst = new TContractBedCategoryDetail();
			mContarctCapMst.setCreatedBy(masterDto.getCreatedBy());
			mContarctCapMst.setCreatedDate(new Date());
			mContarctCapMst.setOrgUnitId(masterDto.getOrgUnitId());
			mContarctCapMst.setOrgId(masterDto.getOrgId());
			mContarctCapMst.setUpdatedBy(masterDto.getUpdatedBy());
			mContarctCapMst.setContractId(masterDto.getContractId());
			mContarctCapMst.setStatus(ACTIVE);
			mContarctCapMst.setTotalAmount(masterDto.getTotalAmount());
			mContarctCapMst.setBillingBedCategoryId(masterDto.getBillingBedCategoryId());
			mContarctCapMst.setApplicableDays(masterDto.getApplicableDays());
			mContarctCapMst = save(mContarctCapMst);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mContarctCapMst);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}





	
}
