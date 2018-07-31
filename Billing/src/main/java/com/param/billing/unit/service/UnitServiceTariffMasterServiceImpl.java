package com.param.billing.unit.service;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.billing.global.transaction.config.dto.ServiceChargesReqDto;
import com.param.billing.global.transaction.config.service.IServiceTarrifMasterService;
import com.param.billing.unit.dao.IUnitServiceTariffMasterDao;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.MultiSelectDto;
import com.param.global.dto.ServicePriceDetailsDto;
import com.param.global.dto.UnitServiceMapperDto;
import com.param.global.dto.UnitServiceTariffMasterDto;
import com.param.global.service.IUnitServiceMapperService;

import in.param.exception.ApplicationException;


@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class UnitServiceTariffMasterServiceImpl implements IUnitServiceTariffMasterService,ICommonConstants{

	@Autowired
	private IUnitServiceMapperService iUnitServiceMapperService;
	
	@Autowired
	IServiceTarrifMasterService iServiceTarrifMasterService; 
	
	@Autowired
	IUnitServiceTariffMasterDao iUnitServiceTariffMasterDao;
	
	@Override
	public Response getMasterServiceListByMultipleParameters(UnitServiceTariffMasterDto unitServiceTariffMasterDto)
			throws ApplicationException {
		try {
			Integer tariffId =null;
			String tariffDesc = null;
			Integer paymentEntitlementId=null;
			String paymentEntitlementDesc = null;
			Integer patientTypeId = null;
			String patientTypeDesc = null;
			Integer visitTypeId = null;
			String visitTypeDesc = null;
			Integer billingBedCatId=null;
			String billingBedCatDesc = null;
			Integer specialityId = null;
			String specialityDesc = null;
			Integer subSpecialityId = null;
			String subSpecialityDesc = null;
			Integer serviceId = null;
			String serviceDesc = null;
			String effectiveDate = null;
			Double rate = null;
			String statPer = null;
			List serviceList=null;
			/*List<Integer> specialityIdsList = new ArrayList<>();
			List<Integer> subSpecialityIdsList = new ArrayList<>();
			List<Integer> serviceIdsList = new ArrayList<>();*/
			
			List<UnitServiceTariffMasterDto> list =new ArrayList<>();
			
			ListIterator<MultiSelectDto> tariffItr = unitServiceTariffMasterDto.getTariffMasterList().listIterator();
			while(tariffItr.hasNext())
			{
				MultiSelectDto tariffItrDto = tariffItr.next();
				tariffId=tariffItrDto.getId();
				tariffDesc=tariffItrDto.getLable();
				
				
				
				ListIterator<MultiSelectDto> visitTypeItr = unitServiceTariffMasterDto.getVisitTypeList().listIterator();
				while(visitTypeItr.hasNext())
				{
					MultiSelectDto visitTypeItrDto = visitTypeItr.next();
					visitTypeId=visitTypeItrDto.getId();
					visitTypeDesc=visitTypeItrDto.getLable();
					
					
					
					ListIterator<MultiSelectDto> paymentEntitlementItr=unitServiceTariffMasterDto.getPaymentEntitlementList().listIterator();
					while(paymentEntitlementItr.hasNext())
					{
						MultiSelectDto paymentEntitlementItrDto = paymentEntitlementItr.next();
						paymentEntitlementId=paymentEntitlementItrDto.getId();
						paymentEntitlementDesc=paymentEntitlementItrDto.getLable();
						
						
						
						ListIterator<MultiSelectDto> patientTypeItr=unitServiceTariffMasterDto.getPatientTypeList().listIterator();
						while(patientTypeItr.hasNext())
						{
							MultiSelectDto patientTypeItrDto = patientTypeItr.next();
							patientTypeId=patientTypeItrDto.getId();
							patientTypeDesc=patientTypeItrDto.getLable();
							

							ListIterator<MultiSelectDto> billingBedCatItr=unitServiceTariffMasterDto.getBedCategoryList().listIterator();
							while(billingBedCatItr.hasNext())
							{
								MultiSelectDto billingBedCatItrDto = billingBedCatItr.next();
								billingBedCatId=billingBedCatItrDto.getId();
								billingBedCatDesc=billingBedCatItrDto.getLable();
								

								//<-----Logic for retrieving service list 
								if(unitServiceTariffMasterDto.getServiceList().size()>0)
									serviceList = getServiceListWithBasePrices(unitServiceTariffMasterDto,unitServiceTariffMasterDto.getServiceList(),visitTypeId,billingBedCatId,patientTypeId,paymentEntitlementId);
								else if(unitServiceTariffMasterDto.getSubSpecialityList().size()>0)
									serviceList = getServiceListWithBasePrices(unitServiceTariffMasterDto,unitServiceTariffMasterDto.getSubSpecialityList(),visitTypeId,billingBedCatId,patientTypeId,paymentEntitlementId);
								else if(unitServiceTariffMasterDto.getSpecialityList().size()>0)
									serviceList = getServiceListWithBasePrices(unitServiceTariffMasterDto,unitServiceTariffMasterDto.getSpecialityList(),visitTypeId,billingBedCatId,patientTypeId,paymentEntitlementId);
								else
									return new Response(ERROR, null, "Select atleast Speciality OR SubSpeciality OR Services", null, null);
								
								
								
								ListIterator<ServicePriceDetailsDto> serviceItr=serviceList.listIterator();
										while(serviceItr.hasNext())
										{
											ServicePriceDetailsDto servicePriceDetailsDto = serviceItr.next();
												serviceId=servicePriceDetailsDto.getServiceMasterId();
												serviceDesc=servicePriceDetailsDto.getServiceStandardName();
												specialityId=servicePriceDetailsDto.getSpecialityId();
												specialityDesc=servicePriceDetailsDto.getSpecialityName();
												subSpecialityId=servicePriceDetailsDto.getSubSpecialityId();
												subSpecialityDesc=servicePriceDetailsDto.getSubSpecialityName();
												rate=servicePriceDetailsDto.getBasePrice();
												
											UnitServiceTariffMasterDto serviceTariffMasterDto =new UnitServiceTariffMasterDto();
												serviceTariffMasterDto.setTariffId(tariffId);
												serviceTariffMasterDto.setTariffDesc(tariffDesc);
												serviceTariffMasterDto.setPaymentEntitlementId(paymentEntitlementId);
												serviceTariffMasterDto.setPaymentEntitlementDesc(paymentEntitlementDesc);
												serviceTariffMasterDto.setPatientTypeId(patientTypeId);
												serviceTariffMasterDto.setPatientTypeDesc(patientTypeDesc);
												serviceTariffMasterDto.setVisitTypeId(visitTypeId);
												serviceTariffMasterDto.setVisitTypeDesc(visitTypeDesc);
												serviceTariffMasterDto.setBillingBedCatId(billingBedCatId);
												serviceTariffMasterDto.setBillingBedCatDesc(billingBedCatDesc);
												serviceTariffMasterDto.setSpecialityId(specialityId);
												serviceTariffMasterDto.setSpecialityDesc(specialityDesc);
												serviceTariffMasterDto.setSubSpecialityId(subSpecialityId);
												serviceTariffMasterDto.setSubSpecialityDesc(subSpecialityDesc);
												serviceTariffMasterDto.setServiceId(serviceId);
												serviceTariffMasterDto.setServiceDesc(serviceDesc);
												serviceTariffMasterDto.setRate(rate);
											list.add(serviceTariffMasterDto);
											
										}
									
								}
								}
							}
							
						}
				
			}
			
			return new Response(SUCCESS, null, null, list, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	
	private List getServiceListWithBasePrices(UnitServiceTariffMasterDto unitServiceTariffMasterDto,List<Integer> listToCarryForGettingServices,Integer visitTypeId,Integer billingBedCatId, Integer patientTypeId, Integer paymentEntitlementId)
	{
		try{
			
		Response unitServiceMapperDtoRes =	null;
		List<Integer> listServiceId=new LinkedList<>();
		
		UnitServiceMapperDto unitServiceMapperDto =new UnitServiceMapperDto();
		unitServiceMapperDto.setOrgnisationId(unitServiceTariffMasterDto.getOrganizationId());
		unitServiceMapperDto.setUnitId(unitServiceTariffMasterDto.getUnitId());
		
		if(unitServiceTariffMasterDto.getServiceList().size()>0)
		{
			unitServiceMapperDto.setServiceIdList(listToCarryForGettingServices);
			
		}else if(unitServiceTariffMasterDto.getSubSpecialityList().size()>0)
		{
			unitServiceMapperDto.setSubSpecialityIdList(listToCarryForGettingServices);
			
		}else if(unitServiceTariffMasterDto.getSpecialityList().size()>0)
		{
			unitServiceMapperDto.setSpecialityIdList(listToCarryForGettingServices);
			
		}
		unitServiceMapperDtoRes = iUnitServiceMapperService.searchUnitServiceByMultipleSpecialityAndSubSpeciality(unitServiceMapperDto);
		
		ListIterator<UnitServiceMapperDto> listItr = unitServiceMapperDtoRes.getListObject().listIterator();
		while(listItr.hasNext())
		{
			UnitServiceMapperDto serviceMapperDto = listItr.next();
			listServiceId.add(serviceMapperDto.getServiceId());
		}
		
		if(unitServiceMapperDtoRes.getListObject().size()>0){
		ServiceChargesReqDto serviceChargesReqDto = new ServiceChargesReqDto();
			serviceChargesReqDto.setOrgId(unitServiceTariffMasterDto.getOrganizationId());
			serviceChargesReqDto.setUnitId(unitServiceTariffMasterDto.getUnitId());
			serviceChargesReqDto.setVisitTypeId(visitTypeId);
			serviceChargesReqDto.setBedTypeId(billingBedCatId);
			serviceChargesReqDto.setPatientTypeId(patientTypeId);
			serviceChargesReqDto.setPaymentTypeId(paymentEntitlementId);
			serviceChargesReqDto.setListServiceId(listServiceId);
		
		com.param.billing.common.Response serviceCharges = iServiceTarrifMasterService.getServiceCharges(serviceChargesReqDto);	
		
		return (List) serviceCharges.getObject();
		}
		else
			return null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Response saveUnitServiceTariffMaster(UnitServiceTariffMasterDto unitServiceTariffMasterDto) {
		try {
			
			int alreadyExistCount=0;
			int addedCount=0;
			UnitServiceTariffMasterDto checkDto = new UnitServiceTariffMasterDto();
			List<UnitServiceTariffMasterDto> dtosList = new LinkedList<>();
			
			ListIterator<UnitServiceTariffMasterDto> listFromFrontEnd =  unitServiceTariffMasterDto.getUnitServiceTariffMasterDtosList().listIterator();
			while(listFromFrontEnd.hasNext())
			{
				UnitServiceTariffMasterDto listFromFrontEndObj = listFromFrontEnd.next();
				listFromFrontEndObj.setOrganizationId(unitServiceTariffMasterDto.getOrganizationId());
				listFromFrontEndObj.setUnitId(unitServiceTariffMasterDto.getUnitId());
				Response res = iUnitServiceTariffMasterDao.getUnitServiceTariffsUniqueness(listFromFrontEndObj);
				if(res.getStatus().equals(SUCCESS))
				{
					if (res.getListObject() != null && res.getListObject().size() > 0) {
						checkDto=listFromFrontEndObj;
						checkDto.setAlreadyExist(1);
						dtosList.add(checkDto);
						alreadyExistCount++;
					}else
					{
						checkDto=listFromFrontEndObj;
						checkDto.setAlreadyExist(0);
						dtosList.add(checkDto);
					}
				}
				else{
					return new Response(ERROR, null, "Something went wrong with checking data !! ", null, null);
				}
			}
			
			if(alreadyExistCount>0)
			{
				return new Response(ERROR, null, ALREADY_EXIST, dtosList, null);
			}else{
				 
				ListIterator<UnitServiceTariffMasterDto> listForSavingDetails = unitServiceTariffMasterDto
						.getUnitServiceTariffMasterDtosList().listIterator();
				while (listForSavingDetails.hasNext()) {
					UnitServiceTariffMasterDto listFromFrontEndObjNew = listForSavingDetails.next();
					listFromFrontEndObjNew.setOrganizationId(unitServiceTariffMasterDto.getOrganizationId());
					listFromFrontEndObjNew.setUnitId(unitServiceTariffMasterDto.getUnitId());

					Response res2 = iUnitServiceTariffMasterDao.saveUnitServiceTariffMaster(listFromFrontEndObjNew);
					if (res2.getStatus().equals(SUCCESS)) {
						addedCount++;
					}
				}
			}
			if(addedCount==unitServiceTariffMasterDto.getUnitServiceTariffMasterDtosList().size())
				return new Response(SUCCESS, null, COMMON_SUCCESS , null, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
		
	}


	@Override
	public UnitServiceTariffMasterDto getBasePriceByServiceTariffMaster(UnitServiceTariffMasterDto unitServiceTariffMasterDto) {
		try {
			return iUnitServiceTariffMasterDao.getBasePriceByServiceTariffMaster(unitServiceTariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Response getBasePriceOfServicesFromTariffMasterByServiceList(
			UnitServiceTariffMasterDto unitServiceTariffMasterDto) throws ApplicationException {
		try {
			return iUnitServiceTariffMasterDao.getBasePriceOfServicesFromTariffMasterByServiceList(unitServiceTariffMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
