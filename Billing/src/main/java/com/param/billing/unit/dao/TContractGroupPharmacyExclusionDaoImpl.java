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
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.global.model.CompanyMaster;
import com.param.service.dto.CommonDto;
import com.param.service.dto.MCompanyContractMasterDto;
import com.param.service.dto.TContractGroupPharmacyExclusionDetailsDto;
import com.param.service.global.model.MCompanyContractMaster;
import com.param.service.global.model.TContractGroupPharmacyExclusionDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;


@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TContractGroupPharmacyExclusionDaoImpl extends GenericDao<TContractGroupPharmacyExclusionDetails, Integer> implements ITContractGroupPharmacyExclusionDetailsDto,ICommonConstants, IError{

	final static Logger logger = Logger.getLogger(TContractGroupPharmacyExclusionDaoImpl.class);
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public TContractGroupPharmacyExclusionDaoImpl() {
		super(TContractGroupPharmacyExclusionDetails.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Response saveContractGroupPharmacyExclusionDetails(
			TContractGroupPharmacyExclusionDetailsDto masterDto)
			throws ApplicationException {
		try{
			TContractGroupPharmacyExclusionDetails mContractGroupPharmacyMaster = new TContractGroupPharmacyExclusionDetails();
			mContractGroupPharmacyMaster.setCreatedBy(masterDto.getCreatedBy());
			mContractGroupPharmacyMaster.setCreatedDate(new Date());
			mContractGroupPharmacyMaster.setCategoryId(masterDto.getCategoryId());
			mContractGroupPharmacyMaster.setUnitId(masterDto.getUnitId());
			mContractGroupPharmacyMaster.setOrgId(masterDto.getOrgId());
			mContractGroupPharmacyMaster.setUpdatedBy(masterDto.getUpdatedBy());
			mContractGroupPharmacyMaster.setContractId(masterDto.getContractId());
			mContractGroupPharmacyMaster.setDepartmentId(masterDto.getDepartmentId());
			mContractGroupPharmacyMaster.setSubDepartmentId(masterDto.getSubDepartmentId());
			mContractGroupPharmacyMaster.setServiceId(masterDto.getServiceId());
			mContractGroupPharmacyMaster.setItemId(masterDto.getItemId());
			mContractGroupPharmacyMaster.setIsServiceItem(masterDto.getIsServiceItem());//1 or 2
			mContractGroupPharmacyMaster = save(mContractGroupPharmacyMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, mContractGroupPharmacyMaster);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}





	
}
