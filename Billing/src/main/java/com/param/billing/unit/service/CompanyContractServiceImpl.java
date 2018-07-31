package com.param.billing.unit.service;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.billing.exception.ServiceException;
import com.param.billing.unit.dao.ICompanyContractDao;
import com.param.billing.unit.dao.ITContractBedBillingCategoryDetailsDao;
import com.param.billing.unit.dao.ITContractCapDetailsDao;
import com.param.billing.unit.dao.ITContractGroupDetailsDao;
import com.param.billing.unit.dao.ITContractGroupPharmacyExclusionDetailsDto;
import com.param.billing.unit.dao.ITContractServiceDetailsDao;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.service.dto.CompanyContractReqDto;
import com.param.service.dto.MCompanyContractMasterDto;
import com.param.service.dto.ServiceForCompnayContarctReqDto;
import com.param.service.dto.TContractBedCategoryDetailDto;
import com.param.service.dto.TContractCapDetailsDto;
import com.param.service.dto.TContractGroupDetailsDto;
import com.param.service.dto.TContractGroupPharmacyExclusionDetailsDto;
import com.param.service.dto.TContractServiceDetailsDto;
import com.param.service.dto.TPackageBedCategoryDetailDto;
import com.param.service.global.model.MCompanyContractMaster;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CompanyContractServiceImpl implements ICompanyContractService, ICommonConstants {

	@Autowired
	ICompanyContractDao iCompanyContractDao;
	@Autowired
	ITContractBedBillingCategoryDetailsDao iTContractBedBillingCategoryDetailsDao;
	@Autowired
	ITContractGroupPharmacyExclusionDetailsDto iTContractGroupPharmacyExclusionDetailsDto;
	@Autowired 
	ITContractCapDetailsDao iTContractCapDetailsDao;
	@Autowired
	ITContractGroupDetailsDao iTContractGroupDetailsDao;
	@Autowired
	ITContractServiceDetailsDao iTContractServiceDetailsDao;

	@Override
	public Response getCompanyMasterList(CompanyMasterDto companyMasterDto) throws ApplicationException {
		try {
			return iCompanyContractDao.getCompanyMasterList(companyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAssociateCompanyMaster(CompanyMasterDto companyMasterDto) throws ApplicationException {
		try {
			return iCompanyContractDao.getAssociateCompanyMaster(companyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getGradeListByAssociateCompanyId(CompanyMasterDto companyMasterDto) throws ApplicationException {
		try {
			return iCompanyContractDao.getGradeListByAssociateCompanyId(companyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getVisitTypeList(CompanyMasterDto companyMasterDto) throws ApplicationException {
		try {
			return iCompanyContractDao.getVisitTypeList(companyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response createCompanyContarct(CompanyContractReqDto reqDto)
			throws ApplicationException {
		try{
			MCompanyContractMasterDto contractMasterDto = reqDto.getCompanyContractMasterDto();
			if(contractMasterDto != null){
				//save Company Contract master
				contractMasterDto.setOrgId(reqDto.getOrgId());
				contractMasterDto.setUnitId(reqDto.getUnitId());
				Response contractRes = iCompanyContractDao.createCompanyContarct(contractMasterDto);
				if(contractRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && contractRes.getObject() != null){
					MCompanyContractMaster contractMaster = (MCompanyContractMaster)contractRes.getObject();
					Integer contractId = contractMaster.getContractId();
					
					//save contract group pharmacy exclusion details
					if(reqDto.getListTContractGroupPharmacyExclusionDetailsDto() != null && reqDto.getListTContractGroupPharmacyExclusionDetailsDto().size() > 0){
						ListIterator<TContractGroupPharmacyExclusionDetailsDto> servicDtlsItr = reqDto.getListTContractGroupPharmacyExclusionDetailsDto().listIterator();
						TContractGroupPharmacyExclusionDetailsDto contractGroupPharmacyExclusionDto = null;
						while(servicDtlsItr.hasNext()){
							contractGroupPharmacyExclusionDto = servicDtlsItr.next();
							contractGroupPharmacyExclusionDto.setContractId(contractId);
							contractGroupPharmacyExclusionDto.setOrgId(reqDto.getOrgId());
							contractGroupPharmacyExclusionDto.setUnitId(reqDto.getUnitId());
							iTContractGroupPharmacyExclusionDetailsDto.saveContractGroupPharmacyExclusionDetails(contractGroupPharmacyExclusionDto);
						}
					}
					
					
					//save contarct bed category details
					if(reqDto.getListTContractBedCategoryDetailDto() != null && reqDto.getListTContractBedCategoryDetailDto().size() > 0){
						ListIterator<TContractBedCategoryDetailDto> contractBedCatItr = reqDto.getListTContractBedCategoryDetailDto().listIterator();
						TContractBedCategoryDetailDto bedCategoryDetailsDto = null;
						while(contractBedCatItr.hasNext()){
							bedCategoryDetailsDto = contractBedCatItr.next();
							bedCategoryDetailsDto.setContractId(contractId);
							bedCategoryDetailsDto.setOrgId(reqDto.getOrgId());
							bedCategoryDetailsDto.setOrgUnitId(reqDto.getUnitId());
							iTContractBedBillingCategoryDetailsDao.saveContractBedBillingCategoryDetails(bedCategoryDetailsDto);
						}
					}
					
					
					//save contract cap amount
					if(reqDto.getListTContractCapDetailsDto() != null && reqDto.getListTContractCapDetailsDto().size() > 0){
						ListIterator<TContractCapDetailsDto> contractCapAmtItr = reqDto.getListTContractCapDetailsDto().listIterator();
						TContractCapDetailsDto contractCapDetailsDto = null;
						while(contractCapAmtItr.hasNext()){
							contractCapDetailsDto = contractCapAmtItr.next();
							contractCapDetailsDto.setContractId(contractId);
							contractCapDetailsDto.setOrgId(reqDto.getOrgId());
							contractCapDetailsDto.setUnitId(reqDto.getUnitId());
							iTContractCapDetailsDao.saveContractCapDetails(contractCapDetailsDto);
						}
					}
					
					
					//save contract group details
					if(reqDto.getListTContractGroupDetailsDto() != null && reqDto.getListTContractGroupDetailsDto().size() > 0){
						ListIterator<TContractGroupDetailsDto> contractGroupDtlsItr = reqDto.getListTContractGroupDetailsDto().listIterator();
						TContractGroupDetailsDto contractGroupDetailsDto = null;
						while(contractGroupDtlsItr.hasNext()){
							contractGroupDetailsDto = contractGroupDtlsItr.next();
							contractGroupDetailsDto.setContractId(contractId);
							contractGroupDetailsDto.setOrgId(reqDto.getOrgId());
							contractGroupDetailsDto.setUnitId(reqDto.getUnitId());
							iTContractGroupDetailsDao.saveContractGroupDetails(contractGroupDetailsDto);
						}
					}
					
					//save Contract Service details
					if(reqDto.getListTContractServiceDetailsDto() != null && reqDto.getListTContractServiceDetailsDto().size() > 0){
						ListIterator<TContractServiceDetailsDto> consumDtlsItr = reqDto.getListTContractServiceDetailsDto().listIterator();
						TContractServiceDetailsDto servicesDetailsDto = null;
						while(consumDtlsItr.hasNext()){
							servicesDetailsDto = consumDtlsItr.next();
							servicesDetailsDto.setContractId(contractId);
							servicesDetailsDto.setOrgId(reqDto.getOrgId());
							servicesDetailsDto.setUnitId(reqDto.getUnitId());
							iTContractServiceDetailsDao.saveContractServiceDetails(servicesDetailsDto);
						}
					}
					
					return new Response<>(SUCCESS, SUCCESS_CODE, COMPANY_CONTRACT_CREATED, null, null);
				}
			}
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Response searchCompanyContract(ServiceForCompnayContarctReqDto reqDto) throws ServiceException {
		try {
			return iCompanyContractDao.searchCompanyContract(reqDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	
}
