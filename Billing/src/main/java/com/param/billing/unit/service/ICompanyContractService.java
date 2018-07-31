package com.param.billing.unit.service;

import javax.transaction.Transactional;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.service.dto.CompanyContractReqDto;
import com.param.service.dto.ServiceForCompnayContarctReqDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")

public interface ICompanyContractService {
	
	@Transactional
	public  Response getCompanyMasterList(CompanyMasterDto companyMasterDto)throws ApplicationException;
	
	@Transactional
	public  Response getAssociateCompanyMaster(CompanyMasterDto companyMasterDto)throws ApplicationException;
	
	@Transactional
	public  Response getGradeListByAssociateCompanyId(CompanyMasterDto companyMasterDto)throws ApplicationException;
	
	@Transactional
	public  Response getVisitTypeList(CompanyMasterDto companyMasterDto)throws ApplicationException;
	
	@Transactional
	public  Response createCompanyContarct(CompanyContractReqDto companyContractReqDto)throws ApplicationException;
	
	@Transactional
	public Response searchCompanyContract(ServiceForCompnayContarctReqDto reqDto) throws ServiceException;
}
