package com.param.service.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.billing.exception.ServiceException;
import com.param.billing.global.transaction.dao.ITPackageConsumptionMasterDao;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.service.dao.IMPackageMasterDao;
import com.param.service.dao.IPackageConsumptionDao;
import com.param.service.dao.ITPackageBedCategoryDetailDao;
import com.param.service.dao.ITPackageCapDetailsDao;
import com.param.service.dao.ITPackageCategoryWiseConsumableDetailsDao;
import com.param.service.dao.ITPackageEitherOrGroupDetailsDao;
import com.param.service.dao.ITPackageIncExcDetailsDao;
import com.param.service.dao.ITPackageServicesDetailsDao;
import com.param.service.dto.AllInclusivePackageReqDto;
import com.param.service.dto.EhcPackageReqDto;
import com.param.service.dto.EitherOrPackageReqDto;
import com.param.service.dto.MPackageMasterDto;
import com.param.service.dto.MultiencounterPackageReqDto;
import com.param.service.dto.PackageConsumptionMasterResDto;
import com.param.service.dto.PackageListForOpBillResDto;
import com.param.service.dto.PackageWithCapReqDto;
import com.param.service.dto.ServiceForPackageReqDto;
import com.param.service.dto.TPackageBedCategoryDetailDto;
import com.param.service.dto.TPackageCapDetailsDto;
import com.param.service.dto.TPackageCategoryWiseConsumableDetailsDto;
import com.param.service.dto.TPackageEitherorGroupDetailsDto;
import com.param.service.dto.TPackageIncExcDetailsDto;
import com.param.service.dto.TPackageServicesDetailsDto;
import com.param.service.global.model.MPackageMaster;
import com.param.service.global.model.TPackageEitherorGroupDetails;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class MPackageMasterServiceImpl implements IMPackageMasterService, ICommonConstants{

	@Autowired
	private IMPackageMasterDao iMPackageMasterDao;
	@Autowired
	private ITPackageServicesDetailsDao iTPackageServicesDetailsDao;
	@Autowired
	private ITPackageEitherOrGroupDetailsDao iTPackageEitherOrGroupDetailsDao;
	@Autowired
	private ITPackageCapDetailsDao iTPackageCapDetailsDao;
	@Autowired
	private ITPackageIncExcDetailsDao iTPackageIncExcDetailsDao;
	@Autowired
	private ITPackageBedCategoryDetailDao iTPackageBedCategoryDetailDao;
	@Autowired
	private ITPackageCategoryWiseConsumableDetailsDao iTPackageCategoryWiseConsumableDetailsDao;
	@Autowired
	private ITPackageConsumptionMasterDao iTPackageConsumptionMasterDao; 
	@Autowired
	private IPackageConsumptionDao iPackageConsumptionDao; 

	
	@Override
	@Transactional
	public Response createEhcPackage(EhcPackageReqDto reqDto) throws ServiceException {
		try{
			MPackageMasterDto packageDto = reqDto.getmPackageMasterDto();
			if(packageDto != null){
				packageDto.setOrgId(reqDto.getOrgId());
				packageDto.setUnitId(reqDto.getUnitId());
				packageDto.setIsManualRoundingIsAllow('Y');
				packageDto.setBillingBedCategoryId(1);//op by default
				packageDto.setVisitTypeId(1);//op by default
				Response packageRes = iMPackageMasterDao.savePackageMaster(packageDto);
				if(packageRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packageRes.getObject() != null){
					MPackageMaster packageMaster = (MPackageMaster)packageRes.getObject();
					Integer packageId = packageMaster.getPackageMasterId();
					ListIterator<TPackageServicesDetailsDto> servicDtlsItr = reqDto.getListTPackageServicesDetailsDto().listIterator();
					TPackageServicesDetailsDto servicesDetailsDto = null;
					while(servicDtlsItr.hasNext()){
						servicesDetailsDto = servicDtlsItr.next();
						servicesDetailsDto.setPackageId(packageId);
						servicesDetailsDto.setOrgnisationId(reqDto.getOrgId());
						servicesDetailsDto.setUnitId(reqDto.getUnitId());
						iTPackageServicesDetailsDao.saveTPackageServicesDetails(servicesDetailsDto);
					}
				}
				return new Response<>(SUCCESS, SUCCESS_CODE, EHC_PACKAGE_CREATED_MSG, null, null);
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
	@Transactional
	public Response searchServiceForPackage(ServiceForPackageReqDto reqDto) throws ServiceException {
		try{
			return iMPackageMasterDao.searchServiceForPackage(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Response createEitherOrPackage(EitherOrPackageReqDto reqDto) throws ServiceException {
		try{
			MPackageMasterDto packageDto = reqDto.getmPackageMasterDto();
			if(packageDto != null){
				//save package master
				packageDto.setOrgId(reqDto.getOrgId());
				packageDto.setUnitId(reqDto.getUnitId());
				packageDto.setIsManualRoundingIsAllow('Y');
				Response packageRes = iMPackageMasterDao.savePackageMaster(packageDto);
				if(packageRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packageRes.getObject() != null){
					MPackageMaster packageMaster = (MPackageMaster)packageRes.getObject();
					Integer packageId = packageMaster.getPackageMasterId();
					//save group details
					ListIterator<TPackageEitherorGroupDetailsDto> eitherOrGrpItr = reqDto.getListTPackageEitherorGroupDetailsDto().listIterator();
					ListIterator<TPackageServicesDetailsDto> groupPackServItr = null;
					while(eitherOrGrpItr.hasNext()){
						TPackageEitherorGroupDetailsDto groupDetailsDto = eitherOrGrpItr.next();
						groupDetailsDto.setOrgId(reqDto.getOrgId());
						groupDetailsDto.setOrgUnitId(reqDto.getUnitId());
						groupDetailsDto.setPackageId(packageId);
						Response eitherOrGrpRes = iTPackageEitherOrGroupDetailsDao.savePackageEitherOrGroupDetails(groupDetailsDto);
						if(eitherOrGrpRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && eitherOrGrpRes.getObject() != null){
							TPackageEitherorGroupDetails eihterOrGrpDtls = (TPackageEitherorGroupDetails)eitherOrGrpRes.getObject();
							 Integer packageGrpDtlsId = eihterOrGrpDtls.getPackageGroupDetailsId();
							 groupPackServItr = groupDetailsDto.getListTPackageServicesDetailsDto().listIterator();
							//save group wise services	
							 TPackageServicesDetailsDto groupServiceDetailsDto = null;
							 while(groupPackServItr.hasNext()){
								 	groupServiceDetailsDto = groupPackServItr.next();
									groupServiceDetailsDto.setPackageEitherOrGroupId(packageGrpDtlsId);
									groupServiceDetailsDto.setOrgnisationId(reqDto.getOrgId());
									groupServiceDetailsDto.setUnitId(reqDto.getUnitId());
									groupServiceDetailsDto.setPackageId(packageId);
									iTPackageServicesDetailsDao.saveTPackageServicesDetails(groupServiceDetailsDto);
								}
						}
						
					}
					
					//save package wise services
					ListIterator<TPackageServicesDetailsDto> servicDtlsItr = reqDto.getListTPackageServicesDetailsDto().listIterator();
					TPackageServicesDetailsDto servicesDetailsDto = null;
					while(servicDtlsItr.hasNext()){
						servicesDetailsDto = servicDtlsItr.next();
						servicesDetailsDto.setPackageId(packageId);
						servicesDetailsDto.setOrgnisationId(reqDto.getOrgId());
						servicesDetailsDto.setUnitId(reqDto.getUnitId());
						iTPackageServicesDetailsDao.saveTPackageServicesDetails(servicesDetailsDto);
					}
				}
				return new Response<>(SUCCESS, SUCCESS_CODE, EITHER_OR_PACKAGE_CREATED, null, null);
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
	@Transactional
	public Response getEHCPackagesList(ServiceForPackageReqDto reqDto) throws ServiceException {
		try{
			return iMPackageMasterDao.getEHCPackagesList(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Response createPackageWithCap(PackageWithCapReqDto reqDto) throws ServiceException {
		try{
			MPackageMasterDto packageDto = reqDto.getmPackageMasterDto();
			if(packageDto != null){
				//save package master
				packageDto.setOrgId(reqDto.getOrgId());
				packageDto.setUnitId(reqDto.getUnitId());
				packageDto.setIsManualRoundingIsAllow('Y');
				Response packageRes = iMPackageMasterDao.savePackageMaster(packageDto);
				if(packageRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packageRes.getObject() != null){
					MPackageMaster packageMaster = (MPackageMaster)packageRes.getObject();
					Integer packageId = packageMaster.getPackageMasterId();
					
					//save service details
					if(reqDto.getListTPackageServicesDetailsDto() != null && reqDto.getListTPackageServicesDetailsDto().size() > 0){
						ListIterator<TPackageServicesDetailsDto> servicDtlsItr = reqDto.getListTPackageServicesDetailsDto().listIterator();
						TPackageServicesDetailsDto servicesDetailsDto = null;
						while(servicDtlsItr.hasNext()){
							servicesDetailsDto = servicDtlsItr.next();
							servicesDetailsDto.setPackageId(packageId);
							servicesDetailsDto.setOrgnisationId(reqDto.getOrgId());
							servicesDetailsDto.setUnitId(reqDto.getUnitId());
							iTPackageServicesDetailsDao.saveTPackageServicesDetails(servicesDetailsDto);
						}
					}
					
					//save group wise cap
					if(reqDto.getListTPackageGroupWiseCapDetailsDto() != null && reqDto.getListTPackageGroupWiseCapDetailsDto().size() > 0){
						ListIterator<TPackageCapDetailsDto> packCapItr = reqDto.getListTPackageGroupWiseCapDetailsDto().listIterator();
						TPackageCapDetailsDto packageCapDetailsDto = null;
						while(packCapItr.hasNext()){
							packageCapDetailsDto = packCapItr.next();
							packageCapDetailsDto.setPackageId(packageId);
							packageCapDetailsDto.setOrganisationId(reqDto.getOrgId());
							packageCapDetailsDto.setUnitId(reqDto.getUnitId());
							iTPackageCapDetailsDao.saveTPackageCapDetails(packageCapDetailsDto);
						}
					}
					
					//save group wise inclusion and exclusion
					if(reqDto.getListGroupWiseIncExc() != null && reqDto.getListGroupWiseIncExc().size() > 0){
						ListIterator<TPackageIncExcDetailsDto> groupWiseIncExcItr = reqDto.getListGroupWiseIncExc().listIterator();
						TPackageIncExcDetailsDto groupWiseIncExc = null;
						while(groupWiseIncExcItr.hasNext()){
							groupWiseIncExc = groupWiseIncExcItr.next();
							groupWiseIncExc.setPackageId(packageId);
							groupWiseIncExc.setOrgId(reqDto.getOrgId());
							groupWiseIncExc.setOrgUnitId(reqDto.getUnitId());
							iTPackageIncExcDetailsDao.saveTPackageIncExcDetails(groupWiseIncExc);
						}
					}
					
					//save package bed category details
					if(reqDto.getListTPackageBedCategoryDetailDto() != null && reqDto.getListTPackageBedCategoryDetailDto().size() > 0){
						ListIterator<TPackageBedCategoryDetailDto> packageBedCatItr = reqDto.getListTPackageBedCategoryDetailDto().listIterator();
						TPackageBedCategoryDetailDto bedCategoryDetailsDto = null;
						while(packageBedCatItr.hasNext()){
							bedCategoryDetailsDto = packageBedCatItr.next();
							bedCategoryDetailsDto.setPackageId(packageId);
							bedCategoryDetailsDto.setOrgId(reqDto.getOrgId());
							bedCategoryDetailsDto.setOrgUnitId(reqDto.getUnitId());
							iTPackageBedCategoryDetailDao.saveTPackageBedCategoryDetail(bedCategoryDetailsDto);
						}
					}
					
					//save package consumable details
					if(reqDto.getListTPackageConsumableDetailsDto() != null && reqDto.getListTPackageConsumableDetailsDto().size() > 0){
						ListIterator<TPackageServicesDetailsDto> consumDtlsItr = reqDto.getListTPackageConsumableDetailsDto().listIterator();
						TPackageServicesDetailsDto servicesDetailsDto = null;
						while(consumDtlsItr.hasNext()){
							servicesDetailsDto = consumDtlsItr.next();
							servicesDetailsDto.setPackageId(packageId);
							servicesDetailsDto.setOrgnisationId(reqDto.getOrgId());
							servicesDetailsDto.setUnitId(reqDto.getUnitId());
							iTPackageServicesDetailsDao.saveTPackageServicesDetails(servicesDetailsDto);
						}
					}
					
					//save category wise consumables
					if(reqDto.getListTPackageCategoryWiseConsumableDetailsDto() != null && reqDto.getListTPackageCategoryWiseConsumableDetailsDto().size() > 0){
						ListIterator<TPackageCategoryWiseConsumableDetailsDto> catWiseConsumDtlsItr = reqDto.getListTPackageCategoryWiseConsumableDetailsDto().listIterator();
						TPackageCategoryWiseConsumableDetailsDto catWiseConsumDto = null;
						while(catWiseConsumDtlsItr.hasNext()){
							catWiseConsumDto = catWiseConsumDtlsItr.next();
							catWiseConsumDto.setPackageId(packageId);
							catWiseConsumDto.setOrgId(reqDto.getOrgId());
							catWiseConsumDto.setOrgUnitId(reqDto.getUnitId());
							iTPackageCategoryWiseConsumableDetailsDao.saveTPackageCategoryWiseConsumableDetails(catWiseConsumDto);
						}
					}
					
					//save category wise exclusion
					if(reqDto.getListCategoryWiseExc() != null && reqDto.getListCategoryWiseExc().size() > 0){
						ListIterator<TPackageIncExcDetailsDto> packCatWiseExItr = reqDto.getListCategoryWiseExc().listIterator();
						TPackageIncExcDetailsDto packCatWiseEx = null;
						while(packCatWiseExItr.hasNext()){
							packCatWiseEx = packCatWiseExItr.next();
							packCatWiseEx.setPackageId(packageId);
							packCatWiseEx.setOrgId(reqDto.getOrgId());
							packCatWiseEx.setOrgUnitId(reqDto.getUnitId());
							iTPackageIncExcDetailsDao.saveTPackageIncExcDetails(packCatWiseEx);
						}
					}
					
					//save consumable wise amount
					if(reqDto.getListTPackageConsumableWiseCapDetailsDto() != null && reqDto.getListTPackageConsumableWiseCapDetailsDto().size() > 0){
						ListIterator<TPackageCapDetailsDto> packConsumCapItr = reqDto.getListTPackageConsumableWiseCapDetailsDto().listIterator();
						TPackageCapDetailsDto packConsumCap = null;
						while(packConsumCapItr.hasNext()){
							packConsumCap = packConsumCapItr.next();
							packConsumCap.setPackageId(packageId);
							packConsumCap.setOrganisationId(reqDto.getOrgId());
							packConsumCap.setUnitId(reqDto.getUnitId());
							iTPackageCapDetailsDao.saveTPackageCapDetails(packConsumCap);
						}
					}
					
					//save consumable wise inc exc
					if(reqDto.getListConsumableWiseIncExc() != null && reqDto.getListConsumableWiseIncExc().size() > 0){
						ListIterator<TPackageIncExcDetailsDto> consumWiseIncExcItr = reqDto.getListConsumableWiseIncExc().listIterator();
						TPackageIncExcDetailsDto consumWiseIncExc = null;
						while(consumWiseIncExcItr.hasNext()){
							consumWiseIncExc = consumWiseIncExcItr.next();
							consumWiseIncExc.setPackageId(packageId);
							consumWiseIncExc.setOrgId(reqDto.getOrgId());
							consumWiseIncExc.setOrgUnitId(reqDto.getUnitId());
							iTPackageIncExcDetailsDao.saveTPackageIncExcDetails(consumWiseIncExc);
						}
					}
					return new Response<>(SUCCESS, SUCCESS_CODE, PACKAGE_WITH_CAP_CREATED, null, null);
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
	@Transactional
	public Response createMultiencouterPackage(MultiencounterPackageReqDto reqDto) throws ServiceException {
		try{
			MPackageMasterDto packageDto = reqDto.getmPackageMasterDto();
			if(packageDto != null){
				packageDto.setOrgId(reqDto.getOrgId());
				packageDto.setUnitId(reqDto.getUnitId());
				packageDto.setIsManualRoundingIsAllow('N');

				Response packageRes = iMPackageMasterDao.savePackageMaster(packageDto);
				if(packageRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packageRes.getObject() != null){
					MPackageMaster packageMaster = (MPackageMaster)packageRes.getObject();
					Integer packageId = packageMaster.getPackageMasterId();
					
					//save service wise details
					if(reqDto.getServiceWiseDetails() != null && reqDto.getServiceWiseDetails().size() > 0){
						ListIterator<TPackageServicesDetailsDto> serviceWiseItr = reqDto.getServiceWiseDetails().listIterator();
						TPackageServicesDetailsDto serviceDetails = null;
						while(serviceWiseItr.hasNext()){
							serviceDetails = serviceWiseItr.next();
							serviceDetails.setPackageId(packageId);
							serviceDetails.setOrgnisationId(reqDto.getOrgId());
							serviceDetails.setUnitId(reqDto.getUnitId());
							iTPackageServicesDetailsDao.saveTPackageServicesDetails(serviceDetails);
						}
					}
					
					//save item wise details
					if(reqDto.getItemWiseDetails() != null && reqDto.getItemWiseDetails().size() > 0){
						ListIterator<TPackageServicesDetailsDto> itemWiseItr = reqDto.getItemWiseDetails().listIterator();
						TPackageServicesDetailsDto itemDetails = null;
						while(itemWiseItr.hasNext()){
							itemDetails = itemWiseItr.next();
							itemDetails.setPackageId(packageId);
							itemDetails.setOrgnisationId(reqDto.getOrgId());
							itemDetails.setUnitId(reqDto.getUnitId());
							iTPackageServicesDetailsDao.saveTPackageServicesDetails(itemDetails);
						}
					}
					return new Response<>(SUCCESS, SUCCESS_CODE, MULTIENCOUNTER_PACKAGE_CREATED, null, null);
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
	@Transactional
public Response SaveAllInclusivePackage(AllInclusivePackageReqDto reqDto)
		throws ServiceException {
	try{
		MPackageMasterDto packageDto = new MPackageMasterDto();
			packageDto.setServiceId(reqDto.getServiceId());
			packageDto.setPackageTypeId(reqDto.getPackageTypeId());
			packageDto.setPackagePrice(reqDto.getPackagePrice());
			packageDto.setValidityPeriodIndays(reqDto.getValidityPeriodIndays());
			packageDto.setOrgId(reqDto.getOrgId());
			packageDto.setUnitId(reqDto.getUnitId());
			packageDto.setIsManualRoundingIsAllow('N');
			packageDto.setBillingBedCategoryId(reqDto.getBillingBedCategoryId());
			packageDto.setVisitTypeId(reqDto.getVisitTypeId());
			
			Response packageRes = iMPackageMasterDao.savePackageMaster(packageDto);
			if(packageRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packageRes.getObject() != null){
				MPackageMaster packageMaster = (MPackageMaster)packageRes.getObject();
				Integer packageId = packageMaster.getPackageMasterId();
				
				ListIterator<TPackageIncExcDetailsDto> servicDtlsItr = reqDto.getListTPackageIncExcServiceDetailsDto().listIterator();
				TPackageIncExcDetailsDto servicesDetailsDto = null;
				while(servicDtlsItr.hasNext()){
					servicesDetailsDto = servicDtlsItr.next();
					servicesDetailsDto.setPackageId(packageId);
					servicesDetailsDto.setOrgId(reqDto.getOrgId());
					servicesDetailsDto.setOrgUnitId(reqDto.getUnitId());
					//iTPackageServicesDetailsDao.saveTPackageServicesDetails(servicesDetailsDto);
					iTPackageIncExcDetailsDao.saveTPackageIncExcDetails(servicesDetailsDto);
				}
			return new Response<>(SUCCESS, SUCCESS_CODE, ALL_EXCLUSIVE_PACKAGE_CREATED, null, null);
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
	@Transactional
	public Response searchServicesForBilling(ServiceSearchReqDto reqDto) throws ServiceException {
		try{
			return iMPackageMasterDao.searchServicesForBilling(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Response getPackageDetailsByIdForBilling(List<Integer> packageIds) throws ServiceException {
		try{
			//get package details
			List<PackageListForOpBillResDto> listPackageListForOpBillResDto = new LinkedList<PackageListForOpBillResDto>();
			PackageListForOpBillResDto mPackage = null;
			Response packageServRes = null;
			Response packageCapRes = null;
			for(int packageId : packageIds){
			Response packRes = iMPackageMasterDao.getPackageById(packageId);
			if(packRes.getCode().equals(SUCCESS_CODE) && packRes.getObject() != null){
				MPackageMasterDto mPackageDto = (MPackageMasterDto)packRes.getObject();
				switch(mPackageDto.getPackageTypeId()){
				case 1 : //EHC package
					mPackage = new PackageListForOpBillResDto();
					mPackage.setmPackageMasterDto(mPackageDto);
					
					//get service details
					packageServRes = iTPackageServicesDetailsDao.getPackageServicesByPackageId(packageId);
					mPackage.setListTPackageServicesDetailsDto(packageServRes.getListObject());
					listPackageListForOpBillResDto.add(mPackage);
					break;
				case 2 : //	Either or Package
					mPackage = new PackageListForOpBillResDto();
					mPackage.setmPackageMasterDto(mPackageDto);
					
					//get service details
					packageServRes = iTPackageServicesDetailsDao.getPackageServicesByPackageId(packageId);
					mPackage.setListTPackageServicesDetailsDto(packageServRes.getListObject());
					listPackageListForOpBillResDto.add(mPackage);
					break;
				case 3 : // package with cap
					mPackage = new PackageListForOpBillResDto();
					mPackage.setmPackageMasterDto(mPackageDto);
					
					//get service details
					packageServRes = iTPackageServicesDetailsDao.getPackageServicesByPackageId(packageId);
					mPackage.setListTPackageServicesDetailsDto(packageServRes.getListObject());
					listPackageListForOpBillResDto.add(mPackage);
					
					//get cap details
					packageCapRes = iTPackageCapDetailsDao.getPackageCapDetailsByPackageIdForBilling(packageId);
					mPackage.setListTPackageGroupWiseCapDetailsDto(packageCapRes.getListObject());
					break;
				case 4: //All exclusive package
					mPackage = new PackageListForOpBillResDto();
					mPackage.setmPackageMasterDto(mPackageDto);
					
					//get service details
					packageServRes = iTPackageServicesDetailsDao.getPackageServicesByPackageId(packageId);
					mPackage.setListTPackageServicesDetailsDto(packageServRes.getListObject());
					listPackageListForOpBillResDto.add(mPackage);
					break;
				case 5 : //multi encounter package
					mPackage = new PackageListForOpBillResDto();
					mPackage.setmPackageMasterDto(mPackageDto);
					
					//get service details
					packageServRes = iTPackageServicesDetailsDao.getPackageServicesByPackageId(packageId);
					mPackage.setListTPackageServicesDetailsDto(packageServRes.getListObject());
					listPackageListForOpBillResDto.add(mPackage);
					break;
				}
				
			}
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listPackageListForOpBillResDto, null);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Response getExistingActivePackageByPatientEncounter(
			Integer patientId, Integer encounterId, Integer visitTypeId, Integer organisationId, Integer unitId)
			throws ServiceException {
		try{
			List<PackageConsumptionMasterResDto> finalPackageConMasterDto =  new ArrayList<>();
			Response packageResponse = iTPackageConsumptionMasterDao.getExistingActivePackageByPatientEncounter(patientId, encounterId, visitTypeId, organisationId, unitId);
				if(packageResponse.getCode().equals(SUCCESS_CODE) && packageResponse.getListObject() != null){
					List<PackageConsumptionMasterResDto> listResDto= packageResponse.getListObject();
					//finalPackageConMasterDto = listResDto;
					for(PackageConsumptionMasterResDto resDto : listResDto){
						Response resPackServDetails = null;
						switch(resDto.getPackageTypeId()){
						case 1 : //EHC package
							//get service details
							 resPackServDetails = iTPackageConsumptionMasterDao.getPackageConsumptionServiceDetailsByPackageIdPatientId(resDto.getPackageId(), patientId);
							resDto.setListPackageConsumptionServiceDetailDto(resPackServDetails.getListObject());
							
							break;
						case 2 : //	Either or Package
							//get service details
							 resPackServDetails = iTPackageConsumptionMasterDao.getPackageConsumptionServiceDetailsByPackageIdPatientId(resDto.getPackageId(), patientId);
							 resDto.setListPackageConsumptionServiceDetailDto(resPackServDetails.getListObject());
							break;
						case 3 : 
							// package with cap
							resPackServDetails = iTPackageConsumptionMasterDao.getPackageConsumptionServiceDetailsByPackageIdPatientId(resDto.getPackageId(), patientId);
							resDto.setListPackageConsumptionServiceDetailDto(resPackServDetails.getListObject());
							
							//get cap details
							Response packageCapRes = iPackageConsumptionDao.getPackageConsumptionCapDetails(resDto.getPackageConsumptionMasterId());
							resDto.setListTPackageConsumptionCapDetailsDto(packageCapRes.getListObject());
							break;
						case 4: //All exclusive package
							 resPackServDetails = iTPackageConsumptionMasterDao.getPackageConsumptionServiceDetailsByPackageIdPatientId(resDto.getPackageId(), patientId);
							 resDto.setListPackageConsumptionServiceDetailDto(resPackServDetails.getListObject());
							break;
						case 5 : //multi encounter package
							 resPackServDetails = iTPackageConsumptionMasterDao.getPackageConsumptionServiceDetailsByPackageIdPatientId(resDto.getPackageId(), patientId);
							 resDto.setListPackageConsumptionServiceDetailDto(resPackServDetails.getListObject());
							break;
					}
						finalPackageConMasterDto.add(resDto);
					
				}
					return new Response(SUCCESS, SUCCESS_CODE, null, finalPackageConMasterDto, null);
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return null;
	}
}
