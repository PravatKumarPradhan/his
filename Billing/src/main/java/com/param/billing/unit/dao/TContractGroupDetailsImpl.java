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
import com.param.service.dto.TContractGroupDetailsDto;
import com.param.service.global.model.TContractCapDetails;
import com.param.service.global.model.TContractGroupDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TContractGroupDetailsImpl extends GenericDao<TContractGroupDetails, Integer> implements ITContractGroupDetailsDao,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TContractGroupDetailsImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TContractGroupDetailsImpl() {
		super(TContractGroupDetails.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveContractGroupDetails(TContractGroupDetailsDto masterDto)
			throws ApplicationException {
		try{
			TContractGroupDetails mContarctGroupDtlsMst = new TContractGroupDetails();
			mContarctGroupDtlsMst.setCreatedBy(masterDto.getCreatedBy());
			mContarctGroupDtlsMst.setCreatedDate(new Date());
			mContarctGroupDtlsMst.setUnitId(masterDto.getUnitId());
			mContarctGroupDtlsMst.setOrgId(masterDto.getOrgId());
			mContarctGroupDtlsMst.setUpdatedBy(masterDto.getUpdatedBy());
			mContarctGroupDtlsMst.setDepartmentId(masterDto.getDepartmentId());
			mContarctGroupDtlsMst.setSubDepartmentId(masterDto.getSubDepartmentId());
			mContarctGroupDtlsMst.setContractId(masterDto.getContractId());
			mContarctGroupDtlsMst.setVariancePercentage(masterDto.getVariancePercentage());
			mContarctGroupDtlsMst.setVarianceUpDown(masterDto.getVarianceUpDown());
			mContarctGroupDtlsMst.setRoundOffAmount(masterDto.getRoundOffAmount());
			mContarctGroupDtlsMst = save(mContarctGroupDtlsMst);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mContarctGroupDtlsMst);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}





	
}
