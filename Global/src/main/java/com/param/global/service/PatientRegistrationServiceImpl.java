package com.param.global.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.common.ScheduleActions;
import com.param.global.dao.IPatientRegistrationDao;
import com.param.global.dao.IScheduleLogsDao;
import com.param.global.dto.OrderDetailsMasterDto;
import com.param.global.dto.OrderMasterDto;
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.dto.ScheduleLogsDto;
import com.param.global.model.PatientRegistration;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class PatientRegistrationServiceImpl implements IPatientRegistrationService, ICommonConstants{

	@Autowired
	private IPatientRegistrationDao iPatientRegistrationDao;
	
	@Autowired
	private IOrderMasterService iOrderMasterService; 
	
	@Autowired
	private IScheduleLogsDao iScheduleLogsDao;
	
	@Override
	@Transactional
	public Response getPatientDetailsById(Integer patientId) throws ApplicationException {
		try {
			return iPatientRegistrationDao.getPatientDetailsById(patientId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, null, null, null);
	}
	
	@Override
	@Transactional
	public Response savePatientRegistration(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
				
			Response res = iPatientRegistrationDao.checkUniquePatient(patientRegistrationDto);
			if (res.getListObject().size() > 0) {
				return new Response(ERROR, null, "Already exist", null, null);
			} else {
				
				//if(patientRegistrationDto.getUhidNumber()!=null && !patientRegistrationDto.getUhidNumber().isEmpty())
					patientRegistrationDto.setUhidNumber(generateUhid(patientRegistrationDto.getRegistrationTypeId()));
				
				
				Response res1 = iPatientRegistrationDao.savePatientRegistration(patientRegistrationDto);
				if (res1.getStatus().equals(SUCCESS)) {
					
					Response patientResponse = iPatientRegistrationDao.savePatientDetails(patientRegistrationDto);
					if(patientResponse.getStatus().equals(SUCCESS)) {
						
						//patient Entry in log table start
						PatientRegistration patientRegistration=new PatientRegistration();
						PatientRegistrationDto patientRegistrationdt= (PatientRegistrationDto)res1.getObject();
						ScheduleLogsDto scheduleLogsDto=new ScheduleLogsDto();
						 	scheduleLogsDto.setAction(ScheduleActions.INSERT);
						 	scheduleLogsDto.setAddedBy(1);
						 	scheduleLogsDto.setAddedDate(patientRegistrationdt.getCreatedDate());
						 	scheduleLogsDto.setApplication(ScheduleActions.HIS);
						 	scheduleLogsDto.setDateTime(patientRegistrationdt.getCreatedDate());
						 	scheduleLogsDto.setErrorCount(0);
						 	scheduleLogsDto.setPriority(ScheduleActions.PRIORITY_NORM);
						 	scheduleLogsDto.setRecordId(patientRegistrationdt.getPatientId());
						 	scheduleLogsDto.setStatus(ScheduleActions.STATUS_PENDING);
						 	String[] tableName = patientRegistration.getClass().getName().split("\\.");
						 	scheduleLogsDto.setTableName(tableName[tableName.length - 1]);						 	
						iScheduleLogsDao.saveScheduleLogs(scheduleLogsDto);
						//patient Entry in log table end
						
						if(patientRegistrationDto.getRegistrationTypeId()==2)//Avoiding OTC patient for auto rendered service
						{
							
							return patientResponse;
						}
						else {
						//-------order master-------//
						OrderMasterDto orderMasterDto = new OrderMasterDto();
							orderMasterDto.setPatientId(patientRegistrationDto.getPatientId());
							orderMasterDto.setCreatedBy(patientRegistrationDto.getCreatedBy());
							orderMasterDto.setUpdatedBy(patientRegistrationDto.getUpdatedBy());
							orderMasterDto.setCreatedDate(GlobalCommonDateUtils.getLongDateFromStrigDate(patientRegistrationDto.getCreatedDate()));
							orderMasterDto.setUpdatedDate(GlobalCommonDateUtils.getLongDateFromStrigDate(patientRegistrationDto.getUpdatedDate()));
							orderMasterDto.setOrderDate(GlobalCommonDateUtils.getLongDateFromStrigDate(patientRegistrationDto.getCreatedDate()));
							orderMasterDto.setOrderStatus('A');
							orderMasterDto.setOrgId(patientRegistrationDto.getOrganizationId());
							orderMasterDto.setOrgUnitId(patientRegistrationDto.getUnitId());
							orderMasterDto.setVisitTypeId(1);// op==1
							orderMasterDto.setOrderStatusId(1);// order status==1
							//-------order details-------//
							OrderDetailsMasterDto orderDetailsMasterDto = new OrderDetailsMasterDto();
								orderDetailsMasterDto.setServiceId(1);//Patient Registration service Id==1
								orderDetailsMasterDto.setServiceIsBilled(0);
								orderDetailsMasterDto.setCreatedBy(patientRegistrationDto.getCreatedBy());
								orderDetailsMasterDto.setUpdatedBy(patientRegistrationDto.getUpdatedBy());
								orderDetailsMasterDto.setOrderDate(GlobalCommonDateUtils.getLongDateFromStrigDate(patientRegistrationDto.getCreatedDate()));
								orderDetailsMasterDto.setOrderSchDate(GlobalCommonDateUtils.getLongDateFromStrigDate(patientRegistrationDto.getCreatedDate()));
								orderDetailsMasterDto.setOrdRenderDatetime(GlobalCommonDateUtils.getLongDateFromStrigDate(patientRegistrationDto.getCreatedDate()));
								orderDetailsMasterDto.setCreatedDate(GlobalCommonDateUtils.getLongDateFromStrigDate(patientRegistrationDto.getCreatedDate()));
								orderDetailsMasterDto.setUpdatedDate(GlobalCommonDateUtils.getLongDateFromStrigDate(patientRegistrationDto.getUpdatedDate()));
								orderDetailsMasterDto.setStatus('A');
								orderDetailsMasterDto.setOrgId(patientRegistrationDto.getOrganizationId());
								orderDetailsMasterDto.setOrgUnitId(patientRegistrationDto.getUnitId());
								orderDetailsMasterDto.setOrderQty(1);
						
							List orderDetailsList = new ArrayList();
							orderDetailsList.add(orderDetailsMasterDto);
							orderMasterDto.setListOrderDetailsMasterDto(orderDetailsList);
						iOrderMasterService.saveOrderMaster(orderMasterDto);
						return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, patientRegistrationDto.getPatientId());
						}
					}else {
						return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
					}
					
				}
				else
					return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
			}
			// return new Response(SUCCESS, SUCCESS_CODE,SUCCESS_MESSAGE, null,
			// null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@Override
	@Transactional
	public Response getPatientDetais(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			return iPatientRegistrationDao.getPatientDetais(patientRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, null, null, null);
	}
	
	@Transactional
	private String generateUhid(int registrationTypeId) throws ApplicationException {
		try {
			Long incrementedId = iPatientRegistrationDao.getNextValPatientId();
			System.out.println(incrementedId);
			String uhidPrefix=null;
			if(registrationTypeId!=2)
				uhidPrefix = "PC-18-";
			
			if(registrationTypeId==5)
				uhidPrefix = "PR-18-";
			
			if(registrationTypeId==2)
				uhidPrefix = "O-18-";
			
			StringBuilder uhid=new StringBuilder(uhidPrefix);
			
				int length = Long.valueOf(incrementedId).toString().length();
				uhid = length == 1 ? (uhid.append("000000").append(incrementedId))
						: length == 2 ? (uhid.append("00000").append(incrementedId))
								: length == 3 ? (uhid.append("0000").append(incrementedId))
										: length == 4 ? (uhid.append("000").append(incrementedId))
												: length == 5 ? (uhid.append("00").append(incrementedId))
														: length == 6 ? (uhid.append("0").append(incrementedId))
																: length == 7 ? (uhid.append(incrementedId)) : uhid.append("");
				return uhid.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return "error";
	}

	@Override
	@Transactional
	public Response patientSearch(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		try {
			if(patientRegistrationDto.getRegistrationTypeId()==2)
				return iPatientRegistrationDao.patientSearchOTC(patientRegistrationDto);
			
			if(patientRegistrationDto.getRegistrationTypeId()==4)
				return iPatientRegistrationDao.patientSearchER(patientRegistrationDto);
			
			if(patientRegistrationDto.getRegistrationTypeId()==1)
				return iPatientRegistrationDao.patientSearchNormal(patientRegistrationDto);
			
			if(patientRegistrationDto.getRegistrationTypeId()==5)
				return iPatientRegistrationDao.patientSearchPre(patientRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, null, null, null);
	}

	@Override
	@Transactional
	public Response updatepatientRegistration(PatientRegistrationDto patientRegistrationDto)
			throws ApplicationException {
		
		Response res = iPatientRegistrationDao.checkUniquePatient(patientRegistrationDto);
		if(res.getStatus().equals(SUCCESS))
		{
			
			Response res2 = iPatientRegistrationDao.patientSearchNormalByPatientId(patientRegistrationDto);
			Iterator<PatientRegistrationDto> itr = res2.getListObject().iterator();
			 
			  while (itr.hasNext()) {
				  PatientRegistrationDto newPatientRegistrationDto =new PatientRegistrationDto();
				  PatientRegistrationDto obj = itr.next();
				  
				  newPatientRegistrationDto.setPatientId(obj.getPatientId());
				  newPatientRegistrationDto.setOrganizationId(obj.getOrganizationId());
				  newPatientRegistrationDto.setUnitId(obj.getUnitId());
				  newPatientRegistrationDto.setUhidNumber(obj.getUhidNumber());
				  newPatientRegistrationDto.setPrefixId(obj.getPrefixId());
				  newPatientRegistrationDto.setFirstName(obj.getFirstName());
				  newPatientRegistrationDto.setMiddleName(obj.getMiddleName());
				  newPatientRegistrationDto.setLastName(obj.getLastName());
				  newPatientRegistrationDto.setGenderId(obj.getGenderId());
				  newPatientRegistrationDto.setDob(obj.getDob());
				  newPatientRegistrationDto.setMobileNumber(obj.getMobileNumber());
				  newPatientRegistrationDto.setCountryCallingCode(obj.getCountryCallingCode());
				  newPatientRegistrationDto.setBarCode(obj.getBarCode());
				  newPatientRegistrationDto.setIsVip(obj.getIsVip());
				  newPatientRegistrationDto.setVipRemark(obj.getVipRemark());
				  newPatientRegistrationDto.setIsBlacklist(obj.getIsBlacklist());
				  newPatientRegistrationDto.setIdentificationTypeId(obj.getIdentificationTypeId());
				  newPatientRegistrationDto.setIdentificationNumber(obj.getIdentificationNumber());
				  newPatientRegistrationDto.setEmail(obj.getEmail());
				  newPatientRegistrationDto.setPatientCategoryId(obj.getPatientCategoryId());
				  newPatientRegistrationDto.setIsUnknownReg(obj.getIsUnknownReg());
				  newPatientRegistrationDto.setNationalityId(obj.getNationalityId());
				  newPatientRegistrationDto.setRaceId(obj.getRaceId());
				  newPatientRegistrationDto.setMaritalStatusId(obj.getMaritalStatusId());
				  newPatientRegistrationDto.setAddress(obj.getAddress());
				  newPatientRegistrationDto.setCountryId(obj.getCountryId());
				  newPatientRegistrationDto.setStateId(obj.getStateId());
				  newPatientRegistrationDto.setDistrictId(obj.getDistrictId());
				  newPatientRegistrationDto.setCityId(obj.getCityId());
				  newPatientRegistrationDto.setAreaId(obj.getAreaId());
				  newPatientRegistrationDto.setZipCode(obj.getZipCode());
				  newPatientRegistrationDto.setPhoneCode(obj.getPhoneCode());
				  newPatientRegistrationDto.setPhoneNumber(obj.getPhoneNumber());
				  newPatientRegistrationDto.setCompanyName(obj.getCompanyName());
				  newPatientRegistrationDto.setCompanyAddress(obj.getCompanyAddress());
				  newPatientRegistrationDto.setCompanyCountryId(obj.getCompanyCountryId());
				  newPatientRegistrationDto.setCompanyStateId(obj.getCompanyStateId());
				  newPatientRegistrationDto.setCompanyDistrictId(obj.getCompanyDistrictId());
				  newPatientRegistrationDto.setCompanyCityId(obj.getCompanyCityId());
				  newPatientRegistrationDto.setCompanyAreaId(obj.getCompanyAreaId());
				  newPatientRegistrationDto.setCompanyZipCode(obj.getCompanyZipCode());
				  newPatientRegistrationDto.setCompanyMobileNumber(obj.getCompanyMobileNumber());
				  newPatientRegistrationDto.setPermanentAddress(obj.getPermanentAddress());
				  newPatientRegistrationDto.setPermanentCountryId(obj.getPermanentCountryId());
				  newPatientRegistrationDto.setPermanentStateId(obj.getPermanentStateId());
				  newPatientRegistrationDto.setPermanentDistrictId(obj.getPermanentDistrictId());
				  newPatientRegistrationDto.setPermanentCityId(obj.getPermanentCityId());
				  newPatientRegistrationDto.setPermanentAreaId(obj.getPermanentAreaId());
				  newPatientRegistrationDto.setPermanentZipCode(obj.getPermanentZipCode());
				  newPatientRegistrationDto.setPermanentPhoneCode(obj.getPermanentPhoneCode());
				  newPatientRegistrationDto.setPermanentPhoneNumber(obj.getPermanentPhoneNumber());
				  newPatientRegistrationDto.setAliseName(obj.getAliseName());
				  newPatientRegistrationDto.setIdentificationExpiryDate(obj.getIdentificationExpiryDate());
				  newPatientRegistrationDto.setOccupationId(obj.getOccupationId());
				  newPatientRegistrationDto.setRegistrationTypeId(obj.getRegistrationTypeId());
				  newPatientRegistrationDto.setPreviousId(obj.getPreviousId());
				  newPatientRegistrationDto.setIsOtcReg(obj.getIsOtcReg());
				  newPatientRegistrationDto.setIsPreReg(obj.getIsPreReg());
				  newPatientRegistrationDto.setIsExpiryRequired(obj.getIsExpiryRequired());
				  newPatientRegistrationDto.setCreatedBy(patientRegistrationDto.getUpdatedBy());
				  newPatientRegistrationDto.setUpdatedBy(patientRegistrationDto.getUpdatedBy());
				  newPatientRegistrationDto.setCreatedDate(patientRegistrationDto.getUpdatedDate());
				  newPatientRegistrationDto.setUpdatedDate(patientRegistrationDto.getUpdatedDate());
				  
				  iPatientRegistrationDao.savePatientRegistrationLog(newPatientRegistrationDto);
				 
				 return iPatientRegistrationDao.updatePatient(patientRegistrationDto);
				  
			  }
				  
			
		}
		return new Response(ERROR, COMMON_ERROR_CODE, null, null, null);
	}

	@Override
	@Transactional
	public Response patientDetailsByIdForBilling(int patientId) throws ApplicationException {
		try{
			return iPatientRegistrationDao.patientDetailsByIdForBilling(patientId);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	@Transactional
	public Response convertPatient(PatientRegistrationDto patientRegistrationDto) throws ApplicationException {
		Response res = iPatientRegistrationDao.checkUniquePatient(patientRegistrationDto);
		if(res.getStatus().equals(SUCCESS))
		{
			
			Response res2 = iPatientRegistrationDao.patientSearchNormalByPatientId(patientRegistrationDto);
			Iterator<PatientRegistrationDto> itr = res2.getListObject().iterator();
			 
			  while (itr.hasNext()) {
				  PatientRegistrationDto newPatientRegistrationDto =new PatientRegistrationDto();
				  PatientRegistrationDto obj = itr.next();
				  
				  String uhid = generateUhidForConversion(patientRegistrationDto.getRegistrationTypeId(),patientRegistrationDto.getPatientId());
				  
				  
				  newPatientRegistrationDto.setPatientId(obj.getPatientId());
				  newPatientRegistrationDto.setOrganizationId(obj.getOrganizationId());
				  newPatientRegistrationDto.setUnitId(obj.getUnitId());
				  newPatientRegistrationDto.setPrevUhidNumber(obj.getUhidNumber());
				  newPatientRegistrationDto.setUhidNumber(uhid);
				  newPatientRegistrationDto.setPrefixId(obj.getPrefixId());
				  newPatientRegistrationDto.setFirstName(obj.getFirstName());
				  newPatientRegistrationDto.setMiddleName(obj.getMiddleName());
				  newPatientRegistrationDto.setLastName(obj.getLastName());
				  newPatientRegistrationDto.setGenderId(obj.getGenderId());
				  newPatientRegistrationDto.setDob(obj.getDob());
				  newPatientRegistrationDto.setMobileNumber(obj.getMobileNumber());
				  newPatientRegistrationDto.setCountryCallingCode(obj.getCountryCallingCode());
				  newPatientRegistrationDto.setBarCode(obj.getBarCode());
				  newPatientRegistrationDto.setIsVip(obj.getIsVip());
				  newPatientRegistrationDto.setVipRemark(obj.getVipRemark());
				  newPatientRegistrationDto.setIsBlacklist(obj.getIsBlacklist());
				  newPatientRegistrationDto.setIdentificationTypeId(obj.getIdentificationTypeId());
				  newPatientRegistrationDto.setIdentificationNumber(obj.getIdentificationNumber());
				  newPatientRegistrationDto.setEmail(obj.getEmail());
				  newPatientRegistrationDto.setPatientCategoryId(obj.getPatientCategoryId());
				  newPatientRegistrationDto.setIsUnknownReg(obj.getIsUnknownReg());
				  newPatientRegistrationDto.setNationalityId(obj.getNationalityId());
				  newPatientRegistrationDto.setRaceId(obj.getRaceId());
				  newPatientRegistrationDto.setMaritalStatusId(obj.getMaritalStatusId());
				  newPatientRegistrationDto.setAddress(obj.getAddress());
				  newPatientRegistrationDto.setCountryId(obj.getCountryId());
				  newPatientRegistrationDto.setStateId(obj.getStateId());
				  newPatientRegistrationDto.setDistrictId(obj.getDistrictId());
				  newPatientRegistrationDto.setCityId(obj.getCityId());
				  newPatientRegistrationDto.setAreaId(obj.getAreaId());
				  newPatientRegistrationDto.setZipCode(obj.getZipCode());
				  newPatientRegistrationDto.setPhoneCode(obj.getPhoneCode());
				  newPatientRegistrationDto.setPhoneNumber(obj.getPhoneNumber());
				  newPatientRegistrationDto.setCompanyName(obj.getCompanyName());
				  newPatientRegistrationDto.setCompanyAddress(obj.getCompanyAddress());
				  newPatientRegistrationDto.setCompanyCountryId(obj.getCompanyCountryId());
				  newPatientRegistrationDto.setCompanyStateId(obj.getCompanyStateId());
				  newPatientRegistrationDto.setCompanyDistrictId(obj.getCompanyDistrictId());
				  newPatientRegistrationDto.setCompanyCityId(obj.getCompanyCityId());
				  newPatientRegistrationDto.setCompanyAreaId(obj.getCompanyAreaId());
				  newPatientRegistrationDto.setCompanyZipCode(obj.getCompanyZipCode());
				  newPatientRegistrationDto.setCompanyMobileNumber(obj.getCompanyMobileNumber());
				  newPatientRegistrationDto.setPermanentAddress(obj.getPermanentAddress());
				  newPatientRegistrationDto.setPermanentCountryId(obj.getPermanentCountryId());
				  newPatientRegistrationDto.setPermanentStateId(obj.getPermanentStateId());
				  newPatientRegistrationDto.setPermanentDistrictId(obj.getPermanentDistrictId());
				  newPatientRegistrationDto.setPermanentCityId(obj.getPermanentCityId());
				  newPatientRegistrationDto.setPermanentAreaId(obj.getPermanentAreaId());
				  newPatientRegistrationDto.setPermanentZipCode(obj.getPermanentZipCode());
				  newPatientRegistrationDto.setPermanentPhoneCode(obj.getPermanentPhoneCode());
				  newPatientRegistrationDto.setPermanentPhoneNumber(obj.getPermanentPhoneNumber());
				  newPatientRegistrationDto.setAliseName(obj.getAliseName());
				  newPatientRegistrationDto.setIdentificationExpiryDate(obj.getIdentificationExpiryDate());
				  newPatientRegistrationDto.setOccupationId(obj.getOccupationId());
				  newPatientRegistrationDto.setRegistrationTypeId(obj.getRegistrationTypeId());
				  newPatientRegistrationDto.setPreviousId(obj.getPreviousId());
				  newPatientRegistrationDto.setIsOtcReg(obj.getIsOtcReg());
				  newPatientRegistrationDto.setIsPreReg(obj.getIsPreReg());
				  newPatientRegistrationDto.setIsExpiryRequired(obj.getIsExpiryRequired());
				  newPatientRegistrationDto.setCreatedBy(patientRegistrationDto.getUpdatedBy());
				  newPatientRegistrationDto.setUpdatedBy(patientRegistrationDto.getUpdatedBy());
				  newPatientRegistrationDto.setCreatedDate(patientRegistrationDto.getUpdatedDate());
				  newPatientRegistrationDto.setUpdatedDate(patientRegistrationDto.getUpdatedDate());
				  
				  iPatientRegistrationDao.savePatientRegistrationLog(newPatientRegistrationDto);
				 
				  
				 if(patientRegistrationDto.getRegistrationTypeId()==4)
				 {
					 return savePatientRegistration(patientRegistrationDto);
				 }
				 patientRegistrationDto.setPatientDetailsId(obj.getPatientDetailsId());
				 patientRegistrationDto.setRegistrationTypeId(1);//Converted to normal patient
				 patientRegistrationDto.setUhidNumber(newPatientRegistrationDto.getUhidNumber());
				 
				 return iPatientRegistrationDao.updatePatient(patientRegistrationDto);
				  
			  }
				  
			
		}
		return new Response(ERROR, COMMON_ERROR_CODE, null, null, null);
	}

	@Transactional
	private String generateUhidForConversion(int registrationTypeId,int patientId) throws ApplicationException {
		try {
			
			Long incrementedId = null;
			
			if(registrationTypeId==4)
				incrementedId = iPatientRegistrationDao.getNextValPatientId();
			else
				incrementedId=(long) patientId;
			
			String uhidPrefix=null;
			if(registrationTypeId!=2)
				uhidPrefix = "PC-18-";
			
			/*if(registrationTypeId==5)
				uhidPrefix = "PR-18-";
			
			if(registrationTypeId==2)
				uhidPrefix = "O-18-";*/
			
			StringBuilder uhid=new StringBuilder(uhidPrefix);
			
				int length = Long.valueOf(incrementedId).toString().length();
				uhid = length == 1 ? (uhid.append("000000").append(incrementedId))
						: length == 2 ? (uhid.append("00000").append(incrementedId))
								: length == 3 ? (uhid.append("0000").append(incrementedId))
										: length == 4 ? (uhid.append("000").append(incrementedId))
												: length == 5 ? (uhid.append("00").append(incrementedId))
														: length == 6 ? (uhid.append("0").append(incrementedId))
																: length == 7 ? (uhid.append(incrementedId)) : uhid.append("");
				return uhid.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return "error";
	}
	
}