package com.param.service.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.service.dto.AllInclusivePackageReqDto;
import com.param.service.dto.EhcPackageReqDto;
import com.param.service.dto.EitherOrPackageReqDto;
import com.param.service.dto.MultiencounterPackageReqDto;
import com.param.service.dto.PackageWithCapReqDto;
import com.param.service.dto.ServiceForPackageReqDto;

@SuppressWarnings("rawtypes")
public interface IMPackageMasterService {
	public Response createEhcPackage(EhcPackageReqDto reqDto)throws ServiceException;
	public Response searchServiceForPackage(ServiceForPackageReqDto reqDto)throws ServiceException;
	public Response createEitherOrPackage(EitherOrPackageReqDto reqDto)throws ServiceException;
	public Response getEHCPackagesList(ServiceForPackageReqDto reqDto)throws ServiceException;
	public Response createPackageWithCap(PackageWithCapReqDto reqDto)throws ServiceException;
	public Response createMultiencouterPackage(MultiencounterPackageReqDto reqDto)throws ServiceException;
	public Response SaveAllInclusivePackage(AllInclusivePackageReqDto reqDto)throws ServiceException;
	public Response searchServicesForBilling(ServiceSearchReqDto reqDto)throws ServiceException;
	public Response getPackageDetailsByIdForBilling(List<Integer> packageIds)throws ServiceException;
	public Response getExistingActivePackageByPatientEncounter(Integer patientId, Integer encounterId,Integer visitTypeId,Integer organisationId, Integer unitId) throws ServiceException;
}
