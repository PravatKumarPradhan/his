package com.param.billing.unit.dao;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.service.dto.MCompanyContractMasterDto;
import com.param.service.dto.ServiceForCompnayContarctReqDto;
import com.param.service.global.model.MCompanyContractMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ICompanyContractDao extends IGenericDao<MCompanyContractMaster,Integer>{

	
	public  Response getCompanyMasterList(CompanyMasterDto companyMasterDto)throws ApplicationException;
	
	public  Response getAssociateCompanyMaster(CompanyMasterDto companyMasterDto)throws ApplicationException;
	
	public  Response getGradeListByAssociateCompanyId(CompanyMasterDto companyMasterDto)throws ApplicationException;
	
	public  Response getVisitTypeList(CompanyMasterDto companyMasterDto)throws ApplicationException;
	
	public  Response createCompanyContarct(MCompanyContractMasterDto mCompanyContractMasterDto)throws ApplicationException;
	
	public Response searchCompanyContract(ServiceForCompnayContarctReqDto reqDto) throws ServiceException;

}
