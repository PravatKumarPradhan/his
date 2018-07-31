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
import com.param.service.dto.TContractServiceDetailsDto;
import com.param.service.global.model.TContractGroupDetails;
import com.param.service.global.model.TContractServiceDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TContractServiceDetailsImpl extends GenericDao<TContractServiceDetails, Integer> implements ITContractServiceDetailsDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TContractServiceDetailsImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TContractServiceDetailsImpl() {
		super(TContractServiceDetails.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveContractServiceDetails(TContractServiceDetailsDto masterDto)
			throws ApplicationException {
		try{
			TContractServiceDetails mContarctServiceDtlsMst = new TContractServiceDetails();
			mContarctServiceDtlsMst.setCreatedBy(masterDto.getCreatedBy());
			mContarctServiceDtlsMst.setCreatedDate(new Date());
			mContarctServiceDtlsMst.setUnitId(masterDto.getUnitId());
			mContarctServiceDtlsMst.setOrgId(masterDto.getOrgId());
			mContarctServiceDtlsMst.setUpdatedBy(masterDto.getUpdatedBy());
			mContarctServiceDtlsMst.setContractId(masterDto.getContractId());
			mContarctServiceDtlsMst.setContractGroupDetailId(masterDto.getContractGroupDetailId());
			mContarctServiceDtlsMst.setServicePrice(masterDto.getServicePrice());
			mContarctServiceDtlsMst.setFinalPrice(masterDto.getFinalPrice());
			mContarctServiceDtlsMst.setServiceId(masterDto.getServiceId());
			mContarctServiceDtlsMst.setApportionedPrice(masterDto.getApportionedPrice());
			mContarctServiceDtlsMst = save(mContarctServiceDtlsMst);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mContarctServiceDtlsMst);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}





	
}
