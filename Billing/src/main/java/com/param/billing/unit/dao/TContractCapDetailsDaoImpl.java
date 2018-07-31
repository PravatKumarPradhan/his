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
import com.param.service.dto.TContractCapDetailsDto;
import com.param.service.global.model.TContractCapDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TContractCapDetailsDaoImpl extends GenericDao<TContractCapDetails, Integer> implements ITContractCapDetailsDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TContractCapDetailsDaoImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TContractCapDetailsDaoImpl() {
		super(TContractCapDetails.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveContractCapDetails(TContractCapDetailsDto masterDto)
			throws ApplicationException {
		try{
			TContractCapDetails mContarctCapMst = new TContractCapDetails();
			mContarctCapMst.setCreatedBy(masterDto.getCreatedBy());
			mContarctCapMst.setCreatedDate(new Date());
			mContarctCapMst.setUnitId(masterDto.getUnitId());
			mContarctCapMst.setOrgId(masterDto.getOrgId());
			mContarctCapMst.setUpdatedBy(masterDto.getUpdatedBy());
			mContarctCapMst.setDepartmentId(masterDto.getDepartmentId());
			mContarctCapMst.setSubDepartmentId(masterDto.getSubDepartmentId());
			mContarctCapMst.setIsServiceItem(masterDto.getIsServiceItem());//1 or 2
			mContarctCapMst.setContractId(masterDto.getContractId());
			mContarctCapMst.setStatus(ACTIVE);
			mContarctCapMst.setProductCategoryId(masterDto.getProductCategoryId());
			mContarctCapMst.setProductCategoryCapAmount(masterDto.getProductCategoryCapAmount());
			mContarctCapMst = save(mContarctCapMst);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mContarctCapMst);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}





	
}
