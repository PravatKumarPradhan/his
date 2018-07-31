package com.param.billing.global.transaction.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.billing.common.ICommonConstants;
import com.param.billing.dto.TPackageConsumptionCapDetailsDto;
import com.param.billing.dto.TPackageConsumptionEoDetailsDto;
import com.param.billing.dto.TPackageConsumptionMasterDto;
import com.param.billing.dto.TPackageConsumptionServiceDetailDto;
import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillListReqDto;
import com.param.billing.global.dto.BillingStatusMapperDto;
import com.param.billing.global.transaction.config.service.IServiceTarrifMasterService;
import com.param.billing.global.transaction.dao.IBillStatusMapperDao;
import com.param.billing.global.transaction.dao.IBillingMasterDao;
import com.param.billing.global.transaction.dao.IDoctorConsultationChargesMasterDao;
import com.param.billing.global.transaction.dao.IPaymentRecieptDetailsDao;
import com.param.billing.global.transaction.dao.IPaymentRecieptMasterDao;
import com.param.billing.global.transaction.dao.ITPackageConsumptionCapDetailsDao;
import com.param.billing.global.transaction.dao.ITPackageConsumptionEoDetailsDao;
import com.param.billing.global.transaction.dao.ITPackageConsumptionMasterDao;
import com.param.billing.global.transaction.dao.ITPackageConsumptionServiceDetailDao;
import com.param.billing.global.transaction.dto.BillPayeeDetailsDto;
import com.param.billing.global.transaction.dto.BillingDetailsDto;
import com.param.billing.global.transaction.dto.BillingMasterDto;
import com.param.billing.global.transaction.dto.OrderDetailsByEncounterIdDto;
import com.param.billing.global.transaction.dto.PaymentReceiptDetailsDto;
import com.param.billing.global.transaction.dto.PaymentRecieptMasterDto;
import com.param.billing.global.transaction.model.BillingStatusMapperId;
import com.param.billing.global.transaction.model.PaymentRecieptMaster;
import com.param.billing.packages.model.TPackageConsumptionMaster;
import com.param.billing.unit.service.IUnitServiceTariffMasterService;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.IBillingStatusMasterConstant;
import com.param.global.common.IEncounterTypeMasterConstant;
import com.param.global.common.Response;
import com.param.global.dto.OrderDetailsMasterDto;
import com.param.global.dto.OrderMasterDto;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.dto.UnitServiceTariffMasterDto;
import com.param.global.service.IOrderDetailsDiscountService;
import com.param.global.service.IOrderDetailsMasterService;
import com.param.global.service.IOrderMasterService;
import com.param.service.dao.IMPackageMasterDao;
import com.param.service.dao.ITPackageCapDetailsDao;
import com.param.service.dao.ITPackageEitherOrGroupDetailsDao;
import com.param.service.dao.ITPackageServicesDetailsDao;
import com.param.service.dto.MPackageMasterDto;
import com.param.service.dto.TPackageCapDetailsDto;
import com.param.service.dto.TPackageEitherorGroupDetailsDto;
import com.param.service.dto.TPackageServicesDetailsDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BillingMasterServiceImpl implements IBillingMasterService,ICommonConstants,IBillingStatusMasterConstant,IEncounterTypeMasterConstant{

	@Autowired
	private IBillingMasterDao billingMasterDao; 
	
	@Autowired
	private IBillingDetailsService iBillingDetailsService;
	
	@Autowired
	private IOrderMasterService iOrderMasterService; 
	
	@Autowired
	private IBillPayeeDetailsService iBillPayeeDetailsService;
	
	@Autowired
	private IPaymentRecieptMasterDao iPaymentRecieptMasterDao;
	
	@Autowired
	private IPaymentRecieptDetailsDao iPaymentRecieptDetailsDao;

	@Autowired
	private IServiceTarrifMasterService iServiceTarrifMasterService;
	
	@Autowired
	private IDoctorConsultationChargesMasterDao iDoctorConsultationChargesMasterDao;
	
	@Autowired
	private IUnitServiceTariffMasterService iUnitServiceTariffMasterService;
	
	@Autowired
	private IBillStatusMapperDao iBillStatusMapperDao;
	
	@Autowired
	private IOrderDetailsDiscountService iOrderDetailsDiscountService;
	
	@Autowired
	private IOrderDetailsMasterService iOrderDetailsMasterService;
	@Autowired
	private IMPackageMasterDao iMPackageMasterDao;
	@Autowired
	private ITPackageConsumptionMasterDao iTPackageConsumptionMasterDao;
	@Autowired
	private ITPackageServicesDetailsDao iTPackageServicesDetailsDao;
	@Autowired
	private ITPackageConsumptionServiceDetailDao iTPackageConsumptionServiceDetailDao;
	@Autowired
	private ITPackageCapDetailsDao iTPackageCapDetailsDao;
	@Autowired
	private ITPackageConsumptionCapDetailsDao iTPackageConsumptionCapDetailsDao;
	@Autowired
	private ITPackageEitherOrGroupDetailsDao iTPackageEitherOrGroupDetailsDao;
	@Autowired
	private ITPackageConsumptionEoDetailsDao iTPackageConsumptionEoDetailsDao;
	
	@Override
	@Transactional
	public Response collectPaymentAgainstBill(PaymentRecieptMasterDto paymentRecieptMasterDto)
			throws ApplicationException {
		try {
			/*double amount=0;
			double paidAmount=0;
			double totalConcessionAmount=0;
			double billNetAmountAmount=0;
			double nextPayableAmount=0;*/
				//--------- get total bill By Bill id --------------//
			BillingMasterDto billingMasterDto = new BillingMasterDto();
			billingMasterDto.setBillingMasterId(paymentRecieptMasterDto.getBillingMasterId());
			//Response totalBillResponse = billingMasterDao.getBillDetailsForPayment(billingMasterDto);
		//	if(totalBillResponse.getStatus().equals(SUCCESS) && totalBillResponse.getObject() != null) {
			//	billingMasterDto = (BillingMasterDto) totalBillResponse.getObject();
			//	paidAmount = billingMasterDto.getPaidAmount();
			//	totalConcessionAmount=billingMasterDto.getConcessionAmount() != null ? billingMasterDto.getConcessionAmount() : 0;
			//	billNetAmountAmount=billingMasterDto.getNetAmount();
			//	nextPayableAmount=(billNetAmountAmount-totalConcessionAmount) - paidAmount;
				
				com.param.billing.common.Response paymetReceiptMasterResponse = iPaymentRecieptMasterDao.savePaymentRecieptMaster(paymentRecieptMasterDto);
				if(paymetReceiptMasterResponse.getStatus().equals(SUCCESS) && paymetReceiptMasterResponse.getObject() !=null) {
					PaymentRecieptMaster paymentRecieptMaster = (PaymentRecieptMaster) paymetReceiptMasterResponse.getObject();
					for(PaymentReceiptDetailsDto paymentReceiptDetailsDto : paymentRecieptMasterDto.getListPaymentReceiptDetailsDto()) {
						paymentReceiptDetailsDto.setPaymentReceiptMasterId(paymentRecieptMaster.getPaymentRecieptId());
						iPaymentRecieptDetailsDao.savePaymentRecieptDetails(paymentReceiptDetailsDto);
					}
					//Get Bill Master By Id
					Response billMasterResponse = billingMasterDao.getBillMasterById(paymentRecieptMasterDto.getBillingMasterId());
					if(billMasterResponse.getStatus().equals(SUCCESS) && billMasterResponse.getObject() != null) {
						billingMasterDto=(BillingMasterDto) billMasterResponse.getObject();
						
						if(paymentRecieptMasterDto.getAmount() == billingMasterDto.getNetAmount()) {
							//----settle bill-----//
							billingMasterDto.setOutstanding(billingMasterDto.getOutstanding() - paymentRecieptMasterDto.getAmount());
							billingMasterDto.setBillStatusId(billingMasterDto.getOutstanding() == 0 ? SETTLED : ONHOLD);
						}else if(paymentRecieptMasterDto.getAmount() < billingMasterDto.getNetAmount()) {
							billingMasterDto.setOutstanding(billingMasterDto.getOutstanding() - paymentRecieptMasterDto.getAmount());
							billingMasterDto.setBillStatusId(billingMasterDto.getOutstanding() == 0 ? SETTLED : ONHOLD);
							//-----update outstanding amount-------// 
						}else if(paymentRecieptMasterDto.getAmount() > billingMasterDto.getNetAmount()) {
							//-----refund-------//
						}
						
						Response billSummaryResponse=billingMasterDao.updateBillSummaryAmount(billingMasterDto);
						
						//save bill status logs
						BillingStatusMapperDto mapperDto = new BillingStatusMapperDto();
						BillingStatusMapperId mapperId = new BillingStatusMapperId();
							mapperId.setBillId(billingMasterDto.getBillingMasterId());
							mapperId.setBillStatusId(billingMasterDto.getOutstanding() == 0 ? SETTLED : ONHOLD);
							mapperDto.setBillingStatusMapperId(mapperId);
							mapperDto.setCreatedBy(billingMasterDto.getCreatedBy());
							mapperDto.setOrgnisationId(billingMasterDto.getOrganisationId());
							mapperDto.setUnitId(billingMasterDto.getUnitId());
							mapperDto.setStatus(ACTIVE);
						iBillStatusMapperDao.saveBillStatus(mapperDto);
					}
					return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, billingMasterDto);
				}
				
			//}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	public UnitServiceTariffMasterDto getServiceBasePrice(BillingMasterDto billingMasterDto, List serviceList) {
		try {
			UnitServiceTariffMasterDto unitServiceTariffMasterDto = new UnitServiceTariffMasterDto();
				unitServiceTariffMasterDto.setOrganizationId(billingMasterDto.getOrganisationId());
				unitServiceTariffMasterDto.setUnitId(billingMasterDto.getUnitId());
				unitServiceTariffMasterDto.setVisitTypeId(1);
				unitServiceTariffMasterDto.setBillingBedCategoryId(billingMasterDto.getBedTypeId());
				unitServiceTariffMasterDto.setPatientTypeId(billingMasterDto.getPatientTypeId());
				unitServiceTariffMasterDto.setPaymentEntitlementId(billingMasterDto.getPatientTypeId());
				unitServiceTariffMasterDto.setServiceList(serviceList);
				unitServiceTariffMasterDto.setOrderDate(billingMasterDto.getOrderDate());
				unitServiceTariffMasterDto.setTariffId(billingMasterDto.getTariffId());

			com.param.global.common.Response response = iUnitServiceTariffMasterService
					.getBasePriceOfServicesFromTariffMasterByServiceList(unitServiceTariffMasterDto);

			if (response.getStatus().equals(SUCCESS) && response.getObject() != null) {
				UnitServiceTariffMasterDto masterDto = (UnitServiceTariffMasterDto) response.getObject();
				if (masterDto != null) {
					return masterDto;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	@Transactional
	public Response updateBillSummaryAmount(BillingMasterDto billingMasterDto) throws ApplicationException {
		try {
			billingMasterDao.updateBillSummaryAmount(billingMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@Override
	@Transactional
	public Response getAutoRenderedServicesByPatient(ServiceMasterDto serviceMasterDto) throws ApplicationException {
		try {
			//--------get Auto-Rendered Services---------//
			
			List<ServiceMasterDto> listServiceMasterDto=billingMasterDao.getAutoRenderedServicesByPatient(serviceMasterDto).getListObject();
			ListIterator<ServiceMasterDto> iterator = listServiceMasterDto.listIterator();
			if(serviceMasterDto.getPaymentEntitlementTypeId()==1)
			{
				while(iterator.hasNext())
				{
					ServiceMasterDto masterDto = iterator.next();
					
					UnitServiceTariffMasterDto unitServiceTariffMasterDto =new UnitServiceTariffMasterDto();
						unitServiceTariffMasterDto.setOrganizationId(serviceMasterDto.getOrganizationId());
						unitServiceTariffMasterDto.setUnitId(serviceMasterDto.getUnitId());
						unitServiceTariffMasterDto.setVisitTypeId(1);
						unitServiceTariffMasterDto.setBillingBedCategoryId(serviceMasterDto.getBedCategoryId());
						unitServiceTariffMasterDto.setPatientTypeId(serviceMasterDto.getPatientTypeId());
						unitServiceTariffMasterDto.setPaymentEntitlementId(serviceMasterDto.getPaymentEntitlementTypeId());
						unitServiceTariffMasterDto.setServiceId(masterDto.getServiceMasterId());
						unitServiceTariffMasterDto.setOrderDate(masterDto.getOrderDateString());
						unitServiceTariffMasterDto.setTariffId(serviceMasterDto.getDefaultSelfTariffId());
						unitServiceTariffMasterDto = iUnitServiceTariffMasterService.getBasePriceByServiceTariffMaster(unitServiceTariffMasterDto);
						
						if(unitServiceTariffMasterDto!=null) {
							masterDto.setBasePrice(unitServiceTariffMasterDto.getRate());
						}
						else
						{
							return new Response<>(ERROR, COMMON_ERROR_CODE, "Cannot get service price as order date !> effective date !!", null, null);
						}
				 }
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	@Transactional
	public Response getBillsByFilterForBillList(BillListReqDto reqDto) throws BillingException {
		try{
			return billingMasterDao.getBillsByFilterForBillList(reqDto);
		}catch(BillingException be){
			be.printStackTrace(); throw be;
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}


	@Override
	@Transactional
	public Response getRenderedServicesByEncounterId(ServiceMasterDto serviceMasterDto) throws ApplicationException {
		try {
			Response res = billingMasterDao.getRenderedServicesByEncounterId(serviceMasterDto);
			List<OrderDetailsByEncounterIdDto> orderDetailsByEncounterIdDtosList = res.getListObject();

			ListIterator<OrderDetailsByEncounterIdDto> iterator = orderDetailsByEncounterIdDtosList.listIterator();
			while (iterator.hasNext()) {
				OrderDetailsByEncounterIdDto orderDetailsByEncounterIdDto = iterator.next();
				Response res2 = iOrderDetailsDiscountService.getOrderDetailsDiscountByOrderDetailsId(orderDetailsByEncounterIdDto.getOrderDetailsId());
				orderDetailsByEncounterIdDto.setDiscountDetailsList(res2.getListObject());
			}
			return new Response<>(SUCCESS, null, null, orderDetailsByEncounterIdDtosList, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@Override
	@Transactional
	public Response saveBill(BillingMasterDto billingMasterDto) throws ApplicationException {
		try{
			List<OrderDetailsMasterDto> listToBeAdded = new LinkedList<>();
			List<OrderDetailsMasterDto> listToBeUpdated = new LinkedList<>();
			List<Integer> orderDetailsIdList = new LinkedList<>(); 
			
			ListIterator<OrderDetailsMasterDto> iterator = billingMasterDto.getOrderDetailsMasterDtosList().listIterator();
			while(iterator.hasNext())
			{
				OrderDetailsMasterDto detailsByEncounterIdDto = iterator.next();
				if(detailsByEncounterIdDto.getOrderId()==null)
					//save order master and details
					listToBeAdded.add(detailsByEncounterIdDto);
				else
					//update same object add if discount details list  
					listToBeUpdated.add(detailsByEncounterIdDto);
			}
			
			if(billingMasterDto.getCancelledDiscountsList().size()>0)
				iOrderDetailsDiscountService.saveCancelledDiscounts(billingMasterDto.getCancelledDiscountsList());

			if(billingMasterDto.getCancelOrderDetailsList().size()>0)
			{
				ListIterator<OrderDetailsMasterDto> iterator2 = billingMasterDto.getCancelOrderDetailsList().listIterator();
				while(iterator2.hasNext())
				{
					OrderDetailsMasterDto orderDetailsMasterDto = iterator2.next();
					iOrderDetailsMasterService.cancelOrder(orderDetailsMasterDto);
				}
			}
			// Adding new services in order master and details 
			if(listToBeAdded.size()>0)
			{
				OrderMasterDto orderMasterDto = new OrderMasterDto();
					orderMasterDto.setPatientId(billingMasterDto.getPatientId());
					orderMasterDto.setEncounterId(billingMasterDto.getEncounterId());
					orderMasterDto.setVisitTypeId(1);// op==1
					orderMasterDto.setCreatedBy(billingMasterDto.getCreatedBy());
					orderMasterDto.setUpdatedBy(billingMasterDto.getUpdatedBy());
					orderMasterDto.setCreatedDate(billingMasterDto.getCreatedDate());
					orderMasterDto.setUpdatedDate(billingMasterDto.getUpdatedDate());
					orderMasterDto.setOrderDate(billingMasterDto.getCreatedDate());
					orderMasterDto.setOrderStatus('A');
					orderMasterDto.setPriorityId(2);
					orderMasterDto.setOrgId(billingMasterDto.getOrganisationId());
					orderMasterDto.setOrgUnitId(billingMasterDto.getUnitId());
					orderMasterDto.setOrderStatusId(1);// order status==1
					orderMasterDto.setDoctorId(billingMasterDto.getOrdDoctorId());
					orderMasterDto.setListOrderDetailsMasterDto(listToBeAdded);
				   Response orderRes = iOrderMasterService.saveOrderMasterWithDiscount(orderMasterDto);
				   Map<Integer,Integer> packageOrders = (Map)orderRes.getObject();
				   
				   ListIterator<OrderDetailsMasterDto> packageItr = listToBeAdded.listIterator();
				   if(packageOrders != null && packageOrders.size() > 0){
				   while(packageItr.hasNext()){
					   OrderDetailsMasterDto orderDtls = packageItr.next();
					   if(orderDtls.isPackage() && orderDtls.getPackageId() != null){
						   Integer createdBy = billingMasterDto.getCreatedBy();
						   Integer updatedBy = billingMasterDto.getUpdatedBy();
						   String createdDate = GlobalCommonDateUtils.getStringDate(new Date(billingMasterDto.getCreatedDate() != null ? billingMasterDto.getCreatedDate() : new Date().getTime()), "dd-M-yyyy HH:mm:ss");
						   String updatedDate = GlobalCommonDateUtils.getStringDate(new Date(billingMasterDto.getUpdatedDate() != null ? billingMasterDto.getUpdatedDate() : new Date().getTime()), "dd-M-yyyy HH:mm:ss");
						   
						 //save package into package consumption
					   Response packRes = iMPackageMasterDao.getPackageById(orderDtls.getPackageId());
						if(packRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packRes.getObject() != null){
							MPackageMasterDto mPackage = (MPackageMasterDto)packRes.getObject();
							TPackageConsumptionMasterDto consMstDto = new TPackageConsumptionMasterDto();
							consMstDto.setAdmissionId(billingMasterDto.getAdmissionId());
							consMstDto.setApplicableNoOfVisit(mPackage.getNoOfEncounters());
							consMstDto.setEncounterId(billingMasterDto.getEncounterId());
							consMstDto.setOrderDetailId(packageOrders.get(mPackage.getPackageMasterId()));
							consMstDto.setOrganisationId(billingMasterDto.getOrganisationId());
							consMstDto.setPackageId(mPackage.getPackageMasterId());
							consMstDto.setPackageTypeId(mPackage.getPackageTypeId());
							consMstDto.setPatientId(billingMasterDto.getPatientId());
							consMstDto.setPayeeId(orderDtls.getPayeeId());
							consMstDto.setRate(mPackage.getPackagePrice());
							consMstDto.setStatus(ACTIVE);
							consMstDto.setUnitId(billingMasterDto.getUnitId());
							consMstDto.setVisitType(billingMasterDto.getVisitTypeId());
							consMstDto.setCreatedBy(createdBy);
							consMstDto.setCreatedDate(createdDate);
							consMstDto.setUpdatedBy(updatedBy);
							consMstDto.setUpdatedDate(updatedDate);
							
							Response packConsRes = iTPackageConsumptionMasterDao.saveTPackageConsumptionMaster(consMstDto);
							if(packConsRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packConsRes.getObject() != null){
								TPackageConsumptionMaster consMaster = (TPackageConsumptionMaster)packConsRes.getObject();
								Integer packageConsumptionId = consMaster.getPackageConsumptionMasterId();
								
								
								Response packServRes = iTPackageServicesDetailsDao.getPackageServicesByPackageId(mPackage.getPackageMasterId());
								if(packServRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packServRes.getListObject() != null && packServRes.getListObject().size() > 0){
									
									//save package service wise details
									ListIterator<TPackageServicesDetailsDto> srvItr = packServRes.getListObject().listIterator();
									TPackageConsumptionServiceDetailDto consumpServDtls = null;
									while(srvItr.hasNext()){
										TPackageServicesDetailsDto servDts = srvItr.next();
										consumpServDtls = new TPackageConsumptionServiceDetailDto();
										consumpServDtls.setApportionedPrice(servDts.getApportionedPrice());
										consumpServDtls.setBalancePackageQuantity(servDts.getNumberToBeUse());
										consumpServDtls.setIsServiceItem(servDts.getIsServiceItem());
										consumpServDtls.setItemId(servDts.getItemId());
										consumpServDtls.setOrganisationId(billingMasterDto.getOrganisationId());
										consumpServDtls.setPackageConsumptionMasterId(packageConsumptionId);
										consumpServDtls.setPackageEoDetailId(servDts.getPackageEitherOrGroupId());
										consumpServDtls.setPackageQuantity(servDts.getNumberToBeUse());
										consumpServDtls.setServiceId(servDts.getServiceId());
										consumpServDtls.setStatus(ACTIVE);
										consumpServDtls.setUnitId(billingMasterDto.getUnitId());
										consumpServDtls.setCreatedBy(createdBy);
										consumpServDtls.setCreatedDate(createdDate);
										consumpServDtls.setUpdatedBy(updatedBy);
										consumpServDtls.setUpdatedDate(updatedDate);
										iTPackageConsumptionServiceDetailDao.saveTPackageConsumptionServiceDetail(consumpServDtls);
									}
									
									//save package cap wise details
									Response packCapRes = iTPackageCapDetailsDao.getPackageCapDetailsByPackageId(mPackage.getPackageMasterId());
									if(packCapRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packCapRes.getListObject() != null && packCapRes.getListObject().size() > 0){
										ListIterator<TPackageCapDetailsDto> capItr = packCapRes.getListObject().listIterator();
										TPackageConsumptionCapDetailsDto consCapDtls = null;
										while(capItr.hasNext()){
											TPackageCapDetailsDto capDtls = capItr.next();
											consCapDtls = new TPackageConsumptionCapDetailsDto();
											consCapDtls.setCreatedBy(createdBy);
											consCapDtls.setCreatedDate(createdDate);
											consCapDtls.setDepartmentBalanceCapAmount(capDtls.getDepartmentCapAmount());
											consCapDtls.setDepartmentCapAmount(capDtls.getDepartmentCapAmount());
											consCapDtls.setDepartmentId(capDtls.getDepartmentId());
											consCapDtls.setIsServiceItem(capDtls.getIsServiceItem());
											consCapDtls.setOrganisationId(billingMasterDto.getOrganisationId());
											consCapDtls.setPackageConsumptionMasterId(packageConsumptionId);
											consCapDtls.setProductCategoryId(capDtls.getProductCateroyId());
											consCapDtls.setStatus(ACTIVE);
											consCapDtls.setSubDepartmentBalanceCapAmount(capDtls.getSubDepartmentCapAmount());
											consCapDtls.setSubDepartmentCapAmount(capDtls.getSubDepartmentCapAmount());
											consCapDtls.setSubDepartmentId(capDtls.getSubDepartmentId());
											consCapDtls.setUnitId(billingMasterDto.getUnitId());
											consCapDtls.setUpdatedBy(updatedBy);
											consCapDtls.setUpdatedDate(updatedDate);
											iTPackageConsumptionCapDetailsDao.saveTPackageConsumptionCapDetails(consCapDtls);
										}
									}
									
									//save package eo wise details
									Response packEoRes = iTPackageEitherOrGroupDetailsDao.getPackageEitherOrGroupDetailsByPackageId(mPackage.getPackageMasterId());
									if(packEoRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && packEoRes.getListObject() != null && packEoRes.getListObject().size() > 0){
										ListIterator<TPackageEitherorGroupDetailsDto> eoItr = packEoRes.getListObject().listIterator();
										TPackageConsumptionEoDetailsDto eoDetailsDto = null;
										while(eoItr.hasNext()){
											TPackageEitherorGroupDetailsDto packEoDtls = eoItr.next();
											eoDetailsDto = new TPackageConsumptionEoDetailsDto();
											eoDetailsDto.setBalanceQuantity(packEoDtls.getNumberServiceUsable());
											eoDetailsDto.setCreatedBy(createdBy);
											eoDetailsDto.setCreatedDate(createdDate);
											eoDetailsDto.setGroupPrice(packEoDtls.getGroupPrice());
											eoDetailsDto.setOrganisationId(billingMasterDto.getOrganisationId());
											eoDetailsDto.setPacakgeEighterorGroupId(packEoDtls.getPackageGroupDetailsId());
											eoDetailsDto.setPackageConsumptionMasterId(packageConsumptionId);
											eoDetailsDto.setTotalQuantity(packEoDtls.getNumberServiceUsable());
											eoDetailsDto.setUnitId(billingMasterDto.getUnitId());
											eoDetailsDto.setUpdatedBy(updatedBy);
											eoDetailsDto.setUpdatedDate(updatedDate);
											iTPackageConsumptionEoDetailsDao.saveTPackageConsumptionEoDetails(eoDetailsDto);
										}
									}
								}
							}
						  }
					   }
				   }
			   }
				   
			}
			// Updating services in order details 
			if(listToBeUpdated.size()>0)
			{
				iOrderDetailsMasterService.updateOrderDetailsWithDiscount(listToBeUpdated);
			}
			
			if(billingMasterDto.getIsFinalBill()=='Y')
			{
				ServiceMasterDto serviceMasterDto = new ServiceMasterDto();
					serviceMasterDto.setEncounterId(billingMasterDto.getEncounterId());
				
				Response res = getRenderedServicesByEncounterId(serviceMasterDto);
				List<OrderDetailsByEncounterIdDto> listForSavingBill = res.getListObject();
				
					billingMasterDto.setOutstanding(billingMasterDto.getNetAmount());
					billingMasterDto.setBillNumber(getBillNumber());
				Response billRes =billingMasterDao.saveBillingMaster(billingMasterDto);
					Integer billMasterId = (Integer) billRes.getObject();
				
				ListIterator<OrderDetailsByEncounterIdDto> listIterator =  listForSavingBill.listIterator();
				while(listIterator.hasNext())
				{
					OrderDetailsByEncounterIdDto detailsByEncounterIdDto = listIterator.next();
					
						BillingDetailsDto billingDetailsDto = new BillingDetailsDto();
							billingDetailsDto.setBillingMasterId(billMasterId);
							billingDetailsDto.setServiceId(detailsByEncounterIdDto.getServiceMasterId());
							billingDetailsDto.setRate(new Double(detailsByEncounterIdDto.getBasePrice().doubleValue()));
							billingDetailsDto.setQuantity(new Double(detailsByEncounterIdDto.getQuantity().doubleValue()));
							billingDetailsDto.setConcession(new Double(detailsByEncounterIdDto.getConcession().doubleValue()));
							billingDetailsDto.setGrossAmount(new Double(detailsByEncounterIdDto.getTotalAmt().doubleValue()));
							billingDetailsDto.setCoPayPercentage(detailsByEncounterIdDto.getCoPayPer() !=null ? new Double(detailsByEncounterIdDto.getCoPayPer().doubleValue()) : new Double(0.00));
							billingDetailsDto.setUnitId(detailsByEncounterIdDto.getOrgUnitId());
							billingDetailsDto.setOrganizationId(detailsByEncounterIdDto.getOrgId());
							billingDetailsDto.setStatus('A');
							billingDetailsDto.setCreatedBy(billingMasterDto.getCreatedBy());
							billingDetailsDto.setUpdatedBy(billingMasterDto.getUpdatedBy());
							billingDetailsDto.setCreatedDate(billingMasterDto.getCreatedDate());
							billingDetailsDto.setUpdatedDate(billingMasterDto.getUpdatedDate());
							billingDetailsDto.setDiscount(new Double(detailsByEncounterIdDto.getDiscount().doubleValue()));
							billingDetailsDto.setTaxId(detailsByEncounterIdDto.getTaxId());
							billingDetailsDto.setTaxPer(new Double(detailsByEncounterIdDto.getTaxPercentage().doubleValue()));
							billingDetailsDto.setTaxAmount(new Double(detailsByEncounterIdDto.getTaxAmt().doubleValue()));
							billingDetailsDto.setNetAmt(new Double(detailsByEncounterIdDto.getNetPay().doubleValue()));
							billingDetailsDto.setOrdDocId(detailsByEncounterIdDto.getDoctorId());
							billingDetailsDto.setOrdDocSplId(detailsByEncounterIdDto.getSpecialityId());
							billingDetailsDto.setDrugId(detailsByEncounterIdDto.getDrugId());
							billingDetailsDto.setBatchId(detailsByEncounterIdDto.getBatchId());
							//billingDetailsDto.setOrderDate(GlobalCommonDateUtils.getLongDateFromStrigDate(detailsByEncounterIdDto.getOrderDateString()));
							//billingDetailsDto.setOrderSchDate(detailsByEncounterIdDto.getOrderSchDate());
							billingDetailsDto.setSelfPayable(new Double(detailsByEncounterIdDto.getSelfPayable().doubleValue()));
							billingDetailsDto.setCreditPayable(new Double(detailsByEncounterIdDto.getCreditPayable().doubleValue()));
							billingDetailsDto.setPayeeId(detailsByEncounterIdDto.getPayeeId());
							billingDetailsDto.setOrderDetailsId(detailsByEncounterIdDto.getOrderDetailsId());
							billingDetailsDto.setPackageId(detailsByEncounterIdDto.getPackageId());
							billingDetailsDto.setPackageConsumptionAmt(detailsByEncounterIdDto.getPackageConsumptionAmt()!=null ? new Double(detailsByEncounterIdDto.getPackageConsumptionAmt().doubleValue()): new Double(0.00));
							billingDetailsDto.setCoSharePer(detailsByEncounterIdDto.getCoPayPer()!=null ? new Double(detailsByEncounterIdDto.getCoPayPer().doubleValue()): new Double(0.00));
							orderDetailsIdList.add(detailsByEncounterIdDto.getOrderDetailsId());
						iBillingDetailsService.saveBillingDetails(billingDetailsDto);
					
				}
				iOrderDetailsMasterService.updateIsBilledStatus(orderDetailsIdList, billMasterId);
				
				BillPayeeDetailsDto billPayeeDetailsDto = new BillPayeeDetailsDto();
					billPayeeDetailsDto.setBillId(billMasterId);
					billPayeeDetailsDto.setStatus(ACTIVE);
					billPayeeDetailsDto.setPayeeId(1);//bill payee id == 1(self)
					billPayeeDetailsDto.setCreatedBy(billingMasterDto.getCreatedBy());
					billPayeeDetailsDto.setUpdatedBy(billingMasterDto.getCreatedBy());
					billPayeeDetailsDto.setCreatedDate(billingMasterDto.getCreatedDate());
					billPayeeDetailsDto.setUpdatedDate(billingMasterDto.getUpdatedDate());
					billPayeeDetailsDto.setBillAmount(billingMasterDto.getNetAmount());
					billPayeeDetailsDto.setDueAmount(billingMasterDto.getNetAmount());
					billPayeeDetailsDto.setOrgId(billingMasterDto.getOrganisationId());
					billPayeeDetailsDto.setUnitId(billingMasterDto.getUnitId());
				iBillPayeeDetailsService.saveBillPayeeDetails(billPayeeDetailsDto);
				
			    //save bill status logs
                BillingStatusMapperDto mapperDto = new BillingStatusMapperDto();
                BillingStatusMapperId mapperId = new BillingStatusMapperId();
                  mapperId.setBillId(billMasterId);
                  mapperId.setBillStatusId(PENDING);
                  mapperDto.setBillingStatusMapperId(mapperId);
                  mapperDto.setCreatedBy(billingMasterDto.getCreatedBy());
                  mapperDto.setOrgnisationId(billingMasterDto.getOrganisationId());
                  mapperDto.setUnitId(billingMasterDto.getUnitId());
                  mapperDto.setStatus(ACTIVE);
                iBillStatusMapperDao.saveBillStatus(mapperDto);
				
				return new Response<>(SUCCESS, null, "Bill Generated Successfully !!", null, null);
			}
			
			return new Response<>(SUCCESS, null, "Order Generated Successfully !!", null, null);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	@Transactional
	public Response getActiveDiscountCategories(Integer orgId,Integer unitId) throws ApplicationException {
		try{
			return billingMasterDao.getActiveDiscountCategories(orgId,unitId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	public static String getBillNumber() {
      String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
      StringBuilder salt = new StringBuilder("BILL00");
      Random rnd = new Random();
      while (salt.length() < 10) {
          int index = (int) (rnd.nextFloat() * SALTCHARS.length());
          salt.append(SALTCHARS.charAt(index));
      }
      String saltStr = salt.toString();
      return saltStr;

  }

}
